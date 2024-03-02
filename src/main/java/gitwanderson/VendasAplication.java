package gitwanderson;

import gitwanderson.domain.entity.Cliente;
import gitwanderson.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VendasAplication {

    @Bean // Para reconhecer como um comando gerenciavel
    public CommandLineRunner init(
            @Autowired ClienteRepository clienteRepository
            ){
        return args -> {
            Cliente c = new Cliente("Wandinelson");
            clienteRepository.save(c);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasAplication.class, args);
    }

}
