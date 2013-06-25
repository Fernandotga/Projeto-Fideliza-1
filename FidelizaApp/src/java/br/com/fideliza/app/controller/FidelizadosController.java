package br.com.fideliza.app.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.download.Download;
import br.com.fideliza.app.annotation.Permission;
import br.com.fideliza.app.annotation.Public;
import br.com.fideliza.app.component.EmpresaSession;
import br.com.fideliza.app.component.JasperMaker;
import br.com.fideliza.app.exception.CommonException;
import br.com.fideliza.app.model.Cliente;
import br.com.fideliza.app.model.ClienteFidelidade;
import br.com.fideliza.app.model.common.PerfilType;
import br.com.fideliza.app.repository.FidelizadosRepository;
import java.util.Collection;
import java.util.List;

@Resource
@Permission({PerfilType.MEMBRO, PerfilType.ADMINISTRADOR, PerfilType.MODERADOR})
public class FidelizadosController {

    private final Result result;
    private final FidelizadosRepository repository;
    private final EmpresaSession session;
    private final JasperMaker jasperMaker;

    public FidelizadosController(Result result, FidelizadosRepository repository, EmpresaSession session, JasperMaker jasperMaker) {
        this.result = result;
        this.repository = repository;
        this.session = session;
        this.jasperMaker = jasperMaker;
    }

    @Get("/fidelizados")
    public void listagem() {
        Collection<Cliente> lista = repository.meusClientes(session.getEmpresa().getId());
        result.include("fidelizadosList", lista);
    }

    public List<Cliente> busca(String busca) {
        result.include("busca", busca);
        List<Cliente> lista = repository.buscar(busca, session.getEmpresa().getId());
        result.include("fidelizadosList", lista);
        return lista;
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

    @Path("/fidelizados/pdf")
    public Download pdfReport() {
        Collection<Cliente> rel = repository.relatorio(session.getEmpresa().getId());
        return jasperMaker.makePdf("Fidelizados.jasper",
                rel, "Fidelizados.pdf", false);
    }

    @Path("/fidelizados/pdf/trocas")
    public Download pdfTrocas() {
        Collection<Cliente> rel = repository.trocas(session.getEmpresa().getId());
        return jasperMaker.makePdf("Troca.jasper",
                rel, "Troca.pdf", false);
    }
    
    //REST Android
    @Public
    @Path("/fidelizados/salvar")
    public void salvar(Cliente cliente) {
        try {
            cliente = repository.save(cliente);
        } catch (CommonException e) {
            
        }
    }
    
    @Public
    @Path("/fidelizados/login")
    public Cliente login(String email, String password) {
        return repository.autenticar(email, password);
    }
}
