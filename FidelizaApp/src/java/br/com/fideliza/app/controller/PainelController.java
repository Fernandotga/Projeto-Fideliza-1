package br.com.fideliza.app.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.fideliza.app.annotation.Permission;
import br.com.fideliza.app.component.EmpresaSession;
import br.com.fideliza.app.model.common.PerfilType;

@Resource
@Permission({PerfilType.MEMBRO, PerfilType.MODERADOR, PerfilType.ADMINISTRADOR})
public class PainelController {

    private final Result result;
    private final Validator validator;
    private final EmpresaSession session;

    public PainelController(Result result, Validator validator, EmpresaSession session) {
        this.result = result;
        this.session = session;
        this.validator = validator;
    }

    @Get("/painel/principal")
    public void principal() {
    }
}
