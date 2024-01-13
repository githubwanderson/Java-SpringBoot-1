package gitwanderson;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.batch.JobLauncherCommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
// Posso colocar uma configuracao exclusiva para um profile add a linha abaixo
@Profile("development")
public class MyConfiguration {

    @Bean
    public CommandLineRunner executar(){
        return args -> {
            System.out.println("Rodando a config do profile de desenvolvimento");
        };
    }

    // Criando uma configuração que pode ser usada em qualquer parte do codigo
    // Essa config pode ser uma conexao com a base de dados etc
    // Se não passar o nome ele pega o nome do metodo
//    @Bean(name = "status")
//    public String status(){
//        return "Online";
//    }
}
