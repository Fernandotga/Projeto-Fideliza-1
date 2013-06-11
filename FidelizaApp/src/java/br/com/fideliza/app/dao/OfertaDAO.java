package br.com.fideliza.app.dao;

import br.com.caelum.vraptor.ioc.Component;
import br.com.fideliza.app.model.Oferta;
import br.com.fideliza.app.repository.OfertaRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.apache.log4j.Logger;

@Component
public class OfertaDAO extends GenericDAO<Oferta> implements OfertaRepository {

    private static Logger log = Logger.getLogger(OfertaDAO.class);

    public OfertaDAO(EntityManager manager) {
        super(manager);
    }

    @Override
    public Boolean limitInserts(Long id) {
        // Regra de 4 ofertas por empresa
        Integer limit = 4;
        try {
            Query query = manager.createQuery("select count(n) from " + Oferta.class.getName() + " n where id_empresa = :id");
            query.setParameter("id", id);
            int result = (Integer) query.getSingleResult();
            if (result > limit) {
                return false;
            }         
            return true;
        } catch (NoResultException ex) {
            log.error(ex);
            return false;
        }
    }

    @Override
    public List<Oferta> minhasOfertas(Long id) {
        try {
            Query query = manager.createQuery("from " + Oferta.class.getName() + " where id_empresa = :id");
            query.setParameter("id", id);

            return query.getResultList();
        } catch (NoResultException ex) {
            log.error(ex);
            return null;
        }
    }
}
