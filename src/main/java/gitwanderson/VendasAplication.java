package gitwanderson;

import gitwanderson.entity.Cliente;
import gitwanderson.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class VendasAplication {

//  Add onde esta o anotation @Bean com o nome status (MyConfiguration)
//    @Autowired
//    @Qualifier("status")
//    private String status;

//  Esta pegando os valores de resource->application.properties
    @Value("${application.name}")
    private String applicationName;

    @GetMapping("/hello")
    public String helloWorld() {
        return applicationName;
    }

    @Bean
    public CommandLineRunner init(@Autowired ClienteRepository clienteRepository){
        return args -> {
            Cliente cliente = new Cliente();
            cliente.setNome("Wanderson");
            clienteRepository.save(cliente);

//          usando o contrutor
            clienteRepository.save(new Cliente("Lucas"));

            List<Cliente> listClientes = clienteRepository.listar();
            listClientes.forEach(System.out::println);
        };
    }

//  Digite psvm para auto completar
    public static void main(String[] args) {
        SpringApplication.run(VendasAplication.class, args);
    }
}
