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
import java.util.Date;

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
    @Get("/empresa/criar")
    public void criar(Empresa entity) {
        result.include("entity", entity);
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
        // dados default
        entity.setDataCadastro(new Date());
        entity.setAtivo(Boolean.TRUE);
        entity.setPerfil(PerfilType.MEMBRO);
        entity.setEmail(entity.getEmail().toLowerCase());
        
        validator.validate(entity);
        validator.onErrorRedirectTo(this).criar(entity);
        try {
            entity = repository.save(entity);
            // retorna tela de login, pois so salvar é apenas deslogado !
            result.include("notice", Utils.i18n("empresa.salvo.sucesso")).redirectTo(IndexController.class).index();
        } catch (CommonException e) {
            result.include("error", Utils.i18n(e.getMessage())).redirectTo(this).criar(entity);
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
