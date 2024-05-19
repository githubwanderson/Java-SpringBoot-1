package gitwanderson.rest.controller;

import gitwanderson.exception.PedidoNaoEncontradoException;
import gitwanderson.exception.RegraNegocioException;
import gitwanderson.rest.ApiErros;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    /**
     * Tratamento de exceções
     * toda vez que  api lançar uma exception de RegraNegocioException.class o @ExceptionHandler pega ela e vai efetuar o tratamento aqui
     * @param ex
     * @return
     */
    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErros handleRegraNegocioException(RegraNegocioException ex){
        String msg = ex.getMessage();
        return new ApiErros(msg);
    }

    @ExceptionHandler(PedidoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErros handlePedidoNaoEncontradoException(PedidoNaoEncontradoException ex){
        return new ApiErros(ex.getMessage());
    }
}
