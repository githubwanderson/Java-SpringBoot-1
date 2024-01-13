package gitwanderson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

//  Digite psvm para auto completar
    public static void main(String[] args) {
        SpringApplication.run(VendasAplication.class, args);
    }
}
