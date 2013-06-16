package br.com.fideliza.app.dao;

import br.com.fideliza.app.model.ClienteFidelidadeHistorico;
import br.com.fideliza.app.repository.HistoricoRepository;
import javax.persistence.EntityManager;

public class HistoricoDAO extends GenericDAO<ClienteFidelidadeHistorico> implements HistoricoRepository {

    public HistoricoDAO(EntityManager manager) {
        super(manager);
    }
}
