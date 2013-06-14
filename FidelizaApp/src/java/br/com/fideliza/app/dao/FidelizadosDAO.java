package br.com.fideliza.app.dao;

import br.com.caelum.vraptor.ioc.Component;
import br.com.fideliza.app.model.Cliente;
import br.com.fideliza.app.model.ClienteFidelidade;
import br.com.fideliza.app.repository.FidelizadosRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.apache.log4j.Logger;

@Component
public class FidelizadosDAO extends GenericDAO<Cliente> implements FidelizadosRepository {

    private static Logger log = Logger.getLogger(FidelizadosDAO.class);

    public FidelizadosDAO(EntityManager manager) {
        super(manager);
    }

    @Override
    public List<Cliente> meusClientes(Long id) {
        String sql = "select c, cf from Cliente c "
                + "join c.clientesFidelidades cf "
                + "join cf.idFidelidade f "
                + "join f.idEmpresa e "
                + "where e.id = :id order by c.nome asc";
        try {
            Query query = manager.createQuery(sql);
            query.setParameter("id", id);

            return query.getResultList();
        } catch (NoResultException ex) {
            log.error(ex);
            return null;
        }
    }

    @Override
    public void zeraPontos(Long id) {
        int zero = 0;
        Query query = manager.createQuery("update " + ClienteFidelidade.class.getName() + " set pontos = :pontos where id = :id");
        query.setParameter("pontos", zero);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public List<Cliente> buscar(String nome, Long id) {
        String sql = "select c, cf from Cliente c "
                + "join c.clientesFidelidades cf "
                + "join cf.idFidelidade f "
                + "join f.idEmpresa e "
                + "where e.id = :id and c.nome like :nome";
        try {
            Query query = manager.createQuery(sql);
            query.setParameter("id", id);
            query.setParameter("nome", "%" + nome + "%");

            return query.getResultList();
        } catch (NoResultException ex) {
            log.error(ex);
            return null;
        }
    }
}
