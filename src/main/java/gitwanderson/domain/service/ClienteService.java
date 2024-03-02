package gitwanderson.service;

import gitwanderson.entity.Cliente;
import gitwanderson.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

//  Cliente service vai precisar da classe ClienteRepository
    @Autowired
    private ClienteRepository clienteRepository;

    public void salvarCliente(Cliente cliente){
        validarCliente(cliente);
        this.clienteRepository.save(cliente);
    }

    private void validarCliente(Cliente cliente) {
        // aplica validações
    }

}
