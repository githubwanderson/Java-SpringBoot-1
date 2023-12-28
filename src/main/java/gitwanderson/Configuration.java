package gitwanderson;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class MinhaConfiguration {

    // Criando uma configuração que pode ser usada em qualquer parte do codigo
    // Essa config pode ser uma conexao com a base de dados etc
    // Se não passar o nome ele pega o nome do metodo
    @Bean(name = "applicationName")
    public String applicationName(){
        return "Sistema de Vendas";
    }
}
