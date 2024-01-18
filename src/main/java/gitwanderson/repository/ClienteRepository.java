package gitwanderson.repository;

import gitwanderson.entity.Cliente;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class ClienteRepository {

    private static String INSERT = "insert into cliente (nome) values (?)";
    private static String SELECT_ALL = "select * from cliente";
    private static String UPDATE = "update cliente set nome = ? where id = ?";
    private static String DELETE = "delete cliente where id = ?";
    private static String SELECT = "select * from cliente where id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Cliente save(Cliente cliente) {
        entityManager.persist(cliente);
        return cliente;
    }

    public Cliente update(Cliente cliente){
        jdbcTemplate.update( UPDATE, new Object[]{cliente.getNome(),cliente.getId()});
        return cliente;
    }

    public void delete(Cliente cliente){
        deleteById(cliente.getId());
    }

    public void deleteById(Integer id){
        jdbcTemplate.update(DELETE, new Object[]{id});
    }

//    public Cliente getById(Integer id){
//        return jdbcTemplate.update(SELECT, new Object[]{id}, getClienteMapper());
//    }

    public List<Cliente> listarByName(String nome){
        return jdbcTemplate.query(SELECT_ALL.concat(" where nome like ? "), new Object[]{"%" + nome + "%"}, getClienteMapper());
    }

    public List<Cliente> listar(){
        return jdbcTemplate.query(SELECT_ALL, getClienteMapper());
    }

    // Criando um metodo a partir de uma seleção Ctrl + Alt + m
    private RowMapper<Cliente> getClienteMapper() {
        return new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet resultSet, int i) throws SQLException {
                return new Cliente(resultSet.getInt("id"),resultSet.getString("nome"));
            }
        };
    }
}
