package br.com.fideliza.app.repository;

import br.com.fideliza.app.model.Cliente;
import java.util.Collection;
import java.util.List;

public interface FidelizadosRepository extends GenericRepository<Cliente> {

    List<Cliente> meusClientes(Long id);
    Collection<Cliente> relatorio(Long id);
    void trocarPontos(Long id);
    List<Cliente> buscar(String nome, Long id);
}
