package br.com.fideliza.app.repository;

import br.com.fideliza.app.model.Cliente;
import java.util.List;

public interface FidelizadosRepository extends GenericRepository<Cliente> {

    List<Cliente> meusClientes(Long id);
    void zeraPontos(Long id);
    List<Cliente> buscar(String nome, Long id);
}
