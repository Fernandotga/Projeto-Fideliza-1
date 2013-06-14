package br.com.fideliza.app.dao;

import br.com.caelum.vraptor.ioc.Component;
import br.com.fideliza.app.model.Fidelidade;
import br.com.fideliza.app.repository.FidelidadeRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.apache.log4j.Logger;

@Component
public class FidelidadeDAO extends GenericDAO<Fidelidade> implements FidelidadeRepository {

    private static Logger log = Logger.getLogger(GenericDAO.class);

    public FidelidadeDAO(EntityManager manager) {
        super(manager);
    }

    @Override
    public Boolean limitInserts(Long id) {
        long limit = 1;
        boolean ativo = true;
        try {
            Query query = manager.createQuery("select count(n) from " + Fidelidade.class.getName() + " n where id_empresa = :id and ativo = :ativo");
            query.setParameter("id", id);
            query.setParameter("ativo", ativo);
            long result = (Long) query.getSingleResult();
            if (result >= limit) {
                return false;
            }
            return true;
        } catch (NoResultException ex) {
            log.error(ex);
            return false;
        }
    }

    @Override
    public List<Fidelidade> minhasFidelidades(Long id) {
        try {
            Query query = manager.createQuery("from " + Fidelidade.class.getName() + " where id_empresa = :id order by ativo desc");
            query.setParameter("id", id);

            return query.getResultList();
        } catch (NoResultException ex) {
            log.error(ex);
            return null;
        }
    }
}
