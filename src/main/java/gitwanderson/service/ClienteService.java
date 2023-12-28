package gitwanderson.service;

import gitwanderson.entity.Cliente;
import gitwanderson.repository.ClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    public void salvarCliente(Cliente cliente){
        validarCliente(cliente);
        ClienteRepository clienteRepository = new ClienteRepository();
        clienteRepository.save(cliente);
    }

    private void validarCliente(Cliente cliente) {
        // aplica validações
    }

}
