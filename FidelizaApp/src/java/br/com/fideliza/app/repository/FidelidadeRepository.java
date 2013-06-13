package br.com.fideliza.app.repository;

import br.com.fideliza.app.model.Fidelidade;
import java.util.List;

public interface FidelidadeRepository extends GenericRepository<Fidelidade> {

    Boolean limitInserts(Long id);

    List<Fidelidade> minhasFidelidades(Long id);
}
