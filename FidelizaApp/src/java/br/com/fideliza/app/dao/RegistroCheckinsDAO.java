package br.com.fideliza.app.dao;

import br.com.caelum.vraptor.ioc.Component;
import br.com.fideliza.app.model.RegistrosCheckins;
import br.com.fideliza.app.repository.RegistroCheckinsRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.apache.log4j.Logger;

@Component
public class RegistroCheckinsDAO extends GenericDAO<RegistrosCheckins> implements RegistroCheckinsRepository {

    private static Logger log = Logger.getLogger(RegistroCheckinsDAO.class);
    public RegistroCheckinsDAO(EntityManager manager) {
        super(manager);
    }

    @Override
    public List<RegistrosCheckins> grafico(Long id) {
        String sql = "SELECT "
                + "    c.data_registro, "
                + "    count(c.data_registro) "
                + "FROM "
                + "    fid_checkins c "
                + "WHERE "
                + "    c.id_empresa = :id "
                + "    AND c.data_registro "
                + "        BETWEEN DATE_SUB(CURRENT_DATE(), INTERVAL 15 DAY) "
                + "        AND DATE_ADD(CURRENT_DATE(), INTERVAL 0 DAY)"
                + "        GROUP BY c.data_registro order by c.data_registro desc limit 30;";
        
        try {
            Query query = manager.createNativeQuery(sql);
            query.setParameter("id", id);
            return query.getResultList();

        } catch (NoResultException ex) {
            log.error(ex);
            return null;
        }
    }
}
