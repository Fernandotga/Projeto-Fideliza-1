package br.com.fideliza.app.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.fideliza.app.annotation.Permission;
import br.com.fideliza.app.component.EmpresaSession;
import br.com.fideliza.app.model.RegistrosCheckins;
import br.com.fideliza.app.model.common.PerfilType;
import br.com.fideliza.app.repository.FidelizadosRepository;
import br.com.fideliza.app.repository.RegistroCheckinsRepository;
import java.util.List;

@Resource
@Permission({PerfilType.MEMBRO, PerfilType.MODERADOR, PerfilType.ADMINISTRADOR})
public class PainelController {

    private final Result result;
    private final EmpresaSession session;
    private final FidelizadosRepository repository;
    private final RegistroCheckinsRepository dao;

    public PainelController(Result result, EmpresaSession session, FidelizadosRepository repository, RegistroCheckinsRepository dao) {
        this.result = result;
        this.session = session;
        this.repository = repository;
        this.dao = dao;
    }

    @Get("/painel/principal")
    public void principal() {
        int qnt = repository.countCustomer(session.getEmpresa().getId());
        result.include("qnt", qnt);
        List<RegistrosCheckins> grafico = dao.grafico(session.getEmpresa().getId());
        result.include("dadosGrafico", grafico);
    }
}
