package br.com.fideliza.app.dao;

import br.com.fideliza.app.model.Telefone;
import br.com.fideliza.app.repository.TelefoneRepository;
import javax.persistence.EntityManager;

public class TelefoneDAO extends GenericDAO<Telefone> implements TelefoneRepository {

    public TelefoneDAO(EntityManager manager) {
        super(manager);
    }
}
