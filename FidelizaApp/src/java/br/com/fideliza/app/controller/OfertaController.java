package br.com.fideliza.app.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.fideliza.app.annotation.Permission;
import br.com.fideliza.app.annotation.Public;
import br.com.fideliza.app.component.EmpresaSession;
import br.com.fideliza.app.model.Oferta;
import br.com.fideliza.app.model.common.CategoriaOfertaType;
import br.com.fideliza.app.model.common.PerfilType;
import br.com.fideliza.app.repository.OfertaRepository;

@Resource
@Permission({PerfilType.MEMBRO, PerfilType.MODERADOR, PerfilType.ADMINISTRADOR})
public class OfertaController {

    private final Result result;
    private final OfertaRepository repository;
    private final Validator validator;
    private final EmpresaSession session;

    public OfertaController(Result result, OfertaRepository repository, Validator validator, EmpresaSession session) {
        this.result = result;
        this.repository = repository;
        this.validator = validator;
        this.session = session;
    }

    @Public
    @Get("/oferta/criar")
    public void criar(Oferta entity) {
        result.include("entity", entity)
                .include("categoriaTypes", CategoriaOfertaType.values());
    }
}
