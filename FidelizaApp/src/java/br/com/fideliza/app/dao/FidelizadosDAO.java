package br.com.fideliza.app.dao;

import br.com.caelum.vraptor.ioc.Component;
import br.com.fideliza.app.model.Cliente;
import br.com.fideliza.app.model.ClienteFidelidade;
import br.com.fideliza.app.repository.FidelizadosRepository;
import java.util.Date;
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
    public void trocarPontos(Long id) {
        int zero = 0;
        Query query = null;
        // historico
        query = manager.createNativeQuery("insert into fid_clientes_fidelidades_historicos(status_fidelidade, data_troca_vencimento, pontos_acumulados, id_cliente_fidelidade) values (:status, :data, :pontos, :id)");
        query.setParameter("pontos", null);
        query.setParameter("id", id);
        query.setParameter("status", "T");
        query.setParameter("data", new Date());
        query.executeUpdate();
        query = null;
        // zerando ...
        query = manager.createQuery("update " + ClienteFidelidade.class.getName() + " set pontos = :pontos where id = :id");
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
