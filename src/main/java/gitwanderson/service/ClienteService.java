package gitwanderson.service;

import gitwanderson.entity.Cliente;
import gitwanderson.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    @Autowired
    public ClienteService( ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    public void salvarCliente(Cliente cliente){
        validarCliente(cliente);
        this.clienteRepository.save(cliente);
    }

    private void validarCliente(Cliente cliente) {
        // aplica validações
    }

}
