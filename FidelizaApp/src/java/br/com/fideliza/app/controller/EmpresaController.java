package br.com.fideliza.app.controller;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.interceptor.download.Download;
import br.com.caelum.vraptor.interceptor.download.FileDownload;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.fideliza.app.annotation.Permission;
import br.com.fideliza.app.annotation.Public;
import br.com.fideliza.app.component.EmpresaSession;
import br.com.fideliza.app.exception.CommonException;
import br.com.fideliza.app.model.Empresa;
import br.com.fideliza.app.model.common.PerfilType;
import br.com.fideliza.app.repository.EmpresaRepository;
import static br.com.fideliza.app.helper.Utils.*;
import br.com.fideliza.app.model.common.TelefoneType;
import java.util.Date;
import java.util.Locale;
import static br.com.caelum.vraptor.view.Results.referer;
import br.com.fideliza.app.helper.Seguranca;
import br.com.fideliza.app.model.common.WebEnderecoType;
import java.io.File;
import java.security.NoSuchAlgorithmException;

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
                .include("telefoneTypes", TelefoneType.values())
                .include("webTypes", WebEnderecoType.values());
    }

    @Get("/empresa/{entity.id}")
    public void exibir(Empresa entity) {
        entity = repository.find(entity.getId());
        result.include("entity", entity);
    }

    @Get("/empresa/{entity.id}/editar")
    public void editar(Empresa entity) {
        result.include("telefoneTypes", TelefoneType.values());
        entity = repository.find(entity.getId());
        result.include("entity", entity);
    }

    @Public
    @Post("/empresa")
    public void salvar(Empresa entity) throws NoSuchAlgorithmException {
        // dados default
        entity.setDataCadastro(new Date());
        entity.setAtivo(Boolean.TRUE);
        entity.setPerfil(PerfilType.MEMBRO);
        entity.setEmail(entity.getEmail().toLowerCase());
        entity.setLogo("default.jpg");
        entity.setPassword(Seguranca.criptografaSenha(entity.getPassword())); 

        validator.validate(entity);
        validator.onErrorRedirectTo(this).criar(entity);
        try {
            entity = repository.save(entity);
            // retorna tela de login, pois so salvar é apenas deslogado !
            result.include("message", i18n("empresa.salvo.sucesso")).redirectTo(IndexController.class).index();
        } catch (CommonException e) {
            result.include("error", i18n(e.getMessage())).redirectTo(this).criar(entity);
        }
    }

    @Put("/empresa/{entity.id}/atualizar")
    public void atualizar(Empresa entity) throws NoSuchAlgorithmException {
        entity.setAtivo(Boolean.TRUE);
        entity.setPerfil(PerfilType.MEMBRO);
        entity.setEmail(entity.getEmail().toLowerCase());
        entity.setPassword(Seguranca.criptografaSenha(entity.getPassword())); 
        
        validator.validate(entity);
        validator.onErrorRedirectTo(this).editar(entity);

        try {
            entity = repository.save(entity);
            result.include("message", i18n("empresa.atualizado.sucesso")).redirectTo(this).exibir(entity);
        } catch (CommonException e) {
            result.include("error", i18n(e.getMessage())).redirectTo(this).editar(entity);
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

    @Post("/empresa/{entity.id}/imagem")
    public void uploadImage(UploadedFile file, Empresa entity) {
        try {
            repository.uploadImage(file, entity);
        } catch (Exception e) {
            result.include("error", e.getMessage());
        }
        result.redirectTo(this).exibir(entity);
    }

    @Delete("/empresa/{entity.id}/imagem")
    public void removeImage(Empresa entity) {
        try {
            entity = repository.find(entity.getId());

            repository.removeImage(entity);
        } catch (Exception e) {
            result.include("error", e.getMessage());
        }
        result.redirectTo(this).exibir(entity);
    }

    @Get("/empresa/{entity.id}/imagem")
    public Download downloadImage(Empresa entity) {
        entity = repository.find(entity.getId());
        File file = new File(Empresa.IMAGE_PATH, entity.getLogo());

        if (!file.exists()) {
            return new FileDownload(new File(Empresa.IMAGE_PATH, "default.jpg"), "image/jpg", "default.jpg");
        }
        String fileName = entity.getNomeFantasia().replaceAll(" ", "-") + ".jpg";
        return new FileDownload(file, "image/jpg", fileName);
    }
}
