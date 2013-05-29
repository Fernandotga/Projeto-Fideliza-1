package br.com.fideliza.app.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.fideliza.app.annotation.Permission;
import br.com.fideliza.app.annotation.Public;
import br.com.fideliza.app.component.EmpresaSession;
import br.com.fideliza.app.exception.CommonException;
import br.com.fideliza.app.model.Empresa;
import br.com.fideliza.app.model.common.PerfilType;
import br.com.fideliza.app.repository.EmpresaRepository;
import br.com.fideliza.app.helper.Utils;
import br.com.fideliza.app.model.common.TelefoneType;
import java.util.Date;
import java.util.Locale;
import static br.com.caelum.vraptor.view.Results.referer;

@Resource
@Permission({PerfilType.MEMBRO, PerfilType.MODERADOR, PerfilType.ADMINISTRADOR})
public class EmpresaController {

    private final Result result;
    private final EmpresaRepository repository;
    private final Validator validator;
    private final EmpresaSession session;

    public EmpresaController(Result result, EmpresaRepository repository, Validator validator, EmpresaSession session) {
        this.result = result;
        this.repository = repository;
        this.validator = validator;
        this.session = session;
    }

    @Public
    @Get("/empresa/criar")
    public void criar(Empresa entity) {
        result.include("entity", entity)
                .include("telefoneTypes", TelefoneType.values());
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

            session.setLanguage(language + "_" + country.toUpperCase());

            result.use(referer()).redirect();
        } catch (IllegalStateException ex) {
            result.redirectTo(IndexController.class).index();
        }
    }

    @Get("/negado")
    public void negado() {
    }
}
