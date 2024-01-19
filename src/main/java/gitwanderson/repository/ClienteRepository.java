package gitwanderson.repository;

import gitwanderson.entity.Cliente;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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

    @Transactional
    public Cliente update(Cliente cliente){
        entityManager.merge(cliente);
        return cliente;
    }

    @Transactional
    public void delete(Cliente cliente){
        if(!entityManager.contains(cliente)){
            cliente = entityManager.merge(cliente);
        }
        entityManager.remove(cliente);
    }

    @Transactional
    public void deleteById(Integer id){
        Cliente cliente = entityManager.find(Cliente.class, id);
        delete(cliente);
    }

    @Transactional(readOnly = true)
    public List<Cliente> listarByName(String nome){
        // Select referenciando a classe
        String jpql = " select c from Cliente c where c.nome like :nome ";
        // Passo a instrução e a classe referenciada que vai me retornar um typedquery (query tipada para entidade Cliente)
        TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class);
        query.setParameter("nome","%" + nome + "%");
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public List<Cliente> listar(){
        return entityManager
                .createQuery("from Cliente", Cliente.class)
                .getResultList();
    }
}
