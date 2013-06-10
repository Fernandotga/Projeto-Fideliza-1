package br.com.fideliza.app.dao;

import br.com.caelum.vraptor.ioc.Component;
import br.com.fideliza.app.model.Oferta;
import br.com.fideliza.app.repository.OfertaRepository;
import javax.persistence.EntityManager;

@Component
public class OfertaDAO extends GenericDAO<Oferta> implements OfertaRepository {

    public OfertaDAO(EntityManager manager) {
        super(manager);
    }
}
