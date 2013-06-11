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
import br.com.fideliza.app.component.EmpresaSession;
import br.com.fideliza.app.exception.CommonException;
import br.com.fideliza.app.helper.Utils;
import br.com.fideliza.app.model.Oferta;
import br.com.fideliza.app.model.common.CategoriaOfertaType;
import br.com.fideliza.app.model.common.PerfilType;
import br.com.fideliza.app.repository.OfertaRepository;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

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
        if (!repository.limitInserts(session.getEmpresa().getId())) {
            result.include("mensagem", Utils.i18n("oferta.limite")).redirectTo(this).listagem();
        } else {
            result.include("entity", entity)
                    .include("categoriaTypes", CategoriaOfertaType.values());
        }
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

    @Delete("/oferta/{entity.id}")
    public void remover(Oferta entity) {
        repository.remove(entity);
        result.include("notice", Utils.i18n("oferta.removido.sucesso")).redirectTo(this).listagem();
    }

    @Get("/oferta/{entity.id}/editar")
    public void editar(Oferta entity) throws ParseException {
        result.include("categoriaTypes", CategoriaOfertaType.values());
        result.include("entity", entity);
    }

    @Put("/oferta/{entity.id}/atualizar")
    public void atualizar(Oferta entity) throws ParseException {
        // dados default
        entity.setIdEmpresa(session.getEmpresa());

        validator.validate(entity);
        validator.onErrorRedirectTo(this).editar(entity);

        try {
            entity = repository.save(entity);
            result.include("notice", Utils.i18n("oferta.atualizado.sucesso")).redirectTo(this).exibir(entity);
        } catch (CommonException e) {
            result.include("error", Utils.i18n(e.getMessage())).redirectTo(this).editar(entity);
        }
    }

    @Post("/oferta/{entity.id}/imagem")
    public void uploadImage(UploadedFile file, Oferta entity) {
        try {
            repository.uploadImage(file, entity);
        } catch (Exception e) {
            result.include("error", e.getMessage());
        }
        result.redirectTo(this).exibir(entity);
    }

    @Delete("/oferta/{entity.id}/imagem")
    public void removeImage(Oferta entity) {
        try {
            entity = repository.find(entity.getId());

            repository.removeImage(entity);
        } catch (Exception e) {
            result.include("error", e.getMessage());
        }
        result.redirectTo(this).exibir(entity);
    }

    @Get("/oferta/{entity.id}/imagem")
    public Download downloadImage(Oferta entity) {
        entity = repository.find(entity.getId());
        File file = new File(Oferta.IMAGE_PATH, entity.getImg());

        if (!file.exists()) {
            return new FileDownload(new File(Oferta.IMAGE_PATH, "default.jpg"), "image/jpg", "default.jpg");
        }
        String fileName = entity.getDescricaoItem().replaceAll(" ", "-") + ".jpg";
        return new FileDownload(file, "image/jpg", fileName);
    }
}
