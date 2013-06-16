package br.com.fideliza.app.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.fideliza.app.annotation.Permission;
import br.com.fideliza.app.component.EmpresaSession;
import br.com.fideliza.app.exception.CommonException;
import br.com.fideliza.app.model.Cliente;
import br.com.fideliza.app.model.ClienteFidelidade;
import br.com.fideliza.app.model.common.PerfilType;
import br.com.fideliza.app.repository.FidelizadosRepository;
import java.util.Collection;

@Resource
@Permission({PerfilType.MEMBRO, PerfilType.ADMINISTRADOR, PerfilType.MODERADOR})
public class FidelizadosController {
    
    private final Result result;
    private final FidelizadosRepository repository;
    private final EmpresaSession session;
    
    public FidelizadosController(Result result, FidelizadosRepository repository, EmpresaSession session) {
        this.result = result;
        this.repository = repository;
        this.session = session;
    }
    
    @Get("/fidelizados")
    public void listagem() {
        Collection<Cliente> lista = repository.meusClientes(session.getEmpresa().getId());
        result.include("fidelizadosList", lista);
    }
    
    @Get("/fidelizados/busca")
    public void busca(String busca) {
        Collection<Cliente> lista = repository.buscar(busca, session.getEmpresa().getId());
        result.include("fidelizadosList", lista);
    }
    
    @Get("/fidelizados/{entity.id}")
    public void exibir(Cliente entity) {
        entity = repository.find(entity.getId());
        result.include("entity", entity);
    }
    
    @Get("/fidelizados/{entity.id}/baixa")
    public void baixa(ClienteFidelidade entity) throws CommonException {
        repository.trocarPontos(entity.getId());
        result.redirectTo(this).listagem();
    }
}
