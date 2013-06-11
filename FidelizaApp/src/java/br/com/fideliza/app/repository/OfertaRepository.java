package br.com.fideliza.app.repository;

import br.com.fideliza.app.model.Oferta;
import java.util.List;

public interface OfertaRepository extends GenericRepository<Oferta> {

    Boolean limitInserts(Long id);
    List<Oferta> minhasOfertas(Long id);
}
