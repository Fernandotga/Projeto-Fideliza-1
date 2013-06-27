package br.com.fideliza.app.repository;

import br.com.fideliza.app.model.Cliente;
import java.util.Collection;
import java.util.List;

public interface FidelizadosRepository extends GenericRepository<Cliente> {

    List<Cliente> meusClientes(Long id);
    Collection<Cliente> relatorio(Long id);
    void trocarPontos(Long id);
    List<Cliente> buscar(String nome, Long id);
    int countCustomer(Long id);
    Collection<Cliente> trocas(Long id);
    Cliente autenticar(String email, String password);
    void checkin(Long id);
    boolean primeiroCheckin(Long cliente, Long fidelidade);
    void vincular(Long cliente, Long fidelidade);
    void plus(Long cliente, Long fidelidade);
}
