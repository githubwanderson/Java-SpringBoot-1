package gitwanderson;

import gitwanderson.entity.Cliente;
import gitwanderson.entity.Pedido;
import gitwanderson.repository.ClienteRepository;
import gitwanderson.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
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
    public CommandLineRunner init(
            @Autowired ClienteRepository clienteRepository,
            @Autowired PedidoRepository pedidoRepository
    ){
        return args -> {

            System.out.println("Salvando clientes");
            Cliente cliente1 = new Cliente("Wanderson");
            clienteRepository.save(cliente1);

            Pedido p1 = new Pedido();
            p1.setCliente(cliente1);
            p1.setData(LocalDate.now());
            p1.setTotal(BigDecimal.valueOf(220));

            pedidoRepository.save(p1);

//            Cliente cliente = clienteRepository.findClienteFetchPedido(cliente1.getId());
//            System.out.println(cliente);
//            System.out.println(cliente.getPedidos());

            List<Pedido> pedidos = pedidoRepository.findByCliente(cliente1);
            pedidos.forEach( p -> {
                System.out.println(p);
            });

            pedidoRepository.findByCliente(cliente1).forEach(System.out::println);




           /*

           ** PRIMEIRO SCRIPT **

            Cliente cliente = new Cliente();
            cliente.setNome("Wanderson");
            clienteRepository.save(cliente);

//          usando o contrutor
            clienteRepository.save(new Cliente("Lucas"));

            List<Cliente> listClientes = clienteRepository.findAll();
            listClientes.forEach(System.out::println);

            listClientes.forEach( c -> {
                c.setNome(c.getNome() + " Atualizado");
                clienteRepository.save(c);
            });

            listClientes = clienteRepository.findAll();
            listClientes.forEach(System.out::println);

            listClientes = clienteRepository.findByNomeLike("Joao");
            listClientes.forEach(System.out::println);

            if(listClientes.isEmpty()){
                System.out.println("Nenhum cliente encontrado.");
            } else  {
                listClientes.forEach(c -> {
                    clienteRepository.deleteById(c.getId());
                });
            }
            listClientes = clienteRepository.findAll();
            listClientes.forEach(System.out::println);


            */



        };
    }

//  Digite psvm para auto completar
    public static void main(String[] args) {
        SpringApplication.run(VendasAplication.class, args);
    }
}
