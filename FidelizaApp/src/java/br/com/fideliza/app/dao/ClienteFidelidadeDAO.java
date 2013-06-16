package br.com.fideliza.app.dao;

import br.com.fideliza.app.model.ClienteFidelidade;
import br.com.fideliza.app.repository.ClienteFidelidadeRepository;
import javax.persistence.EntityManager;

public class ClienteFidelidadeDAO extends GenericDAO<ClienteFidelidade> implements ClienteFidelidadeRepository {

    public ClienteFidelidadeDAO(EntityManager manager) {
        super(manager);
    }
}
