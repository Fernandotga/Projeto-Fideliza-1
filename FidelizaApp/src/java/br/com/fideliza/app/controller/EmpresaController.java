package br.com.fideliza.app.controller;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.fideliza.app.annotation.Public;
import br.com.fideliza.app.component.EmpresaSession;
import br.com.fideliza.app.exception.CommonException;
import br.com.fideliza.app.model.Empresa;
import br.com.fideliza.app.model.PerfilType;
import br.com.fideliza.app.repository.EmpresaRepository;
import br.com.fideliza.app.helper.Utils;
import java.util.Locale;
import static br.com.caelum.vraptor.view.Results.referer;

@Resource
public class EmpresaController {

    private final Result result;
    private final EmpresaRepository repository;
    private final EmpresaSession empresaSession;
    private final Validator validator;

    public EmpresaController(Result result, EmpresaRepository repository, EmpresaSession empresaSession, Validator validator) {
        this.result = result;
        this.repository = repository;
        this.empresaSession = empresaSession;
        this.validator = validator;
    }

    @Public
    //@Get("/empresa/criarEmpresa")
    public void novo(Empresa entity) {
        result.include("entity", entity);
    }
    
    @Public
    @Get("/empresa/criarEmpresa")
    public void form(){
        
    }

    @Put("/empresa/{entity.id}")
    public void atualizar(Empresa entity) {
        validator.validate(entity);
        validator.onErrorRedirectTo(this).editar(entity);

        try {
            entity = repository.save(entity);
            result.include("notice", Utils.i18n("empresa.atualizada.sucesso")).redirectTo(this).listagem();
        } catch (CommonException e) {
            result.include("error", Utils.i18n(e.getMessage())).redirectTo(this).editar(entity);
        }
    }

    @Public
    @Get("/empresa/{entity.id}/editarEmpresa")
    public void editar(Empresa entity) {
        result.include("perfilList", PerfilType.values());

        if (entity.getEmail() == null) {
            result.include("entity", repository.find(entity.getId()));
        } else {
            result.include("entity", entity);
        }
    }

    @Get("/empresa")
    public void listagem() {
        result.include("entityList", repository.all());
    }

    @Delete("/empresa/{entity.id}")
    public void remover(Empresa entity) {
        repository.remove(entity);

        result
                .include("notice", Utils.i18n("empresa.removido.sucesso"))
                .redirectTo(this).listagem();
    }

    @Public
    @Post("/empresa")
    public void salvar(Empresa entity) {
        validator.validate(entity);
        validator.onErrorRedirectTo(this).novo(entity);
        try {
            entity = repository.save(entity);
            result.include("notice", Utils.i18n("empresa.salvo.sucesso")).redirectTo(this).listagem();
        } catch (CommonException e) {
            result.include("error", Utils.i18n(e.getMessage())).redirectTo(this).novo(entity);
        }
    }

    @Public
    @Get("/translate/{language}/{country}")
    public void translateTo(String language, String country) {
        try {
            Locale.setDefault(new Locale(language, country));

            empresaSession.setLanguage(language + "_" + country.toUpperCase());

            result.use(referer()).redirect();
        } catch (IllegalStateException e) {
            result.redirectTo(IndexController.class).index();
        }
    }
}
