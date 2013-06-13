package br.com.fideliza.app.dao;

import br.com.caelum.vraptor.ioc.Component;
import br.com.fideliza.app.model.Cliente;
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
                + "where e.id = :id";
        try {
            Query query = manager.createQuery(sql);
            query.setParameter("id", id);

            return query.getResultList();
        } catch (NoResultException ex) {
            log.error(ex);
            return null;
        }
    }
}
