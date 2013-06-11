package br.com.fideliza.app.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.fideliza.app.annotation.Permission;
import br.com.fideliza.app.component.EmpresaSession;
import br.com.fideliza.app.exception.CommonException;
import br.com.fideliza.app.helper.Utils;
import br.com.fideliza.app.model.Oferta;
import br.com.fideliza.app.model.common.CategoriaOfertaType;
import br.com.fideliza.app.model.common.PerfilType;
import br.com.fideliza.app.repository.OfertaRepository;
import java.util.Collection;

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

    @Get("/oferta/criar")
    public void criar(Oferta entity) {
        result.include("entity", entity)
                .include("categoriaTypes", CategoriaOfertaType.values());
    }

    @Post("/oferta")
    public void salvar(Oferta entity) {
        // dados default
        entity.setIdEmpresa(session.getEmpresa());

        validator.validate(entity);
        validator.onErrorRedirectTo(this).criar(entity);
        try {
            entity = repository.save(entity);
            result.include("notice", Utils.i18n("oferta.salvo.sucesso")).redirectTo(this).exibir(entity);
        } catch (CommonException e) {
            result.include("error", Utils.i18n(e.getMessage())).redirectTo(this).criar(entity);
        }
    }

    @Get("/oferta")
    public void listagem() {
        Collection<Oferta> lista = repository.minhasOfertas(session.getEmpresa().getId());
        result.include("ofertaList", lista);
    }

    @Get("/oferta/{entity.id}")
    public void exibir(Oferta entity) {
        entity = repository.find(entity.getId());
        result.include("entity", entity);
    }
}
