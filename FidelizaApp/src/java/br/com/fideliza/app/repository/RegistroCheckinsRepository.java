package br.com.fideliza.app.repository;

import br.com.fideliza.app.model.RegistrosCheckins;
import java.util.List;

public interface RegistroCheckinsRepository extends GenericRepository<RegistrosCheckins> {
    List<RegistrosCheckins> grafico(Long id);
}
