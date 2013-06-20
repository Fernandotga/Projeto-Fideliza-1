package br.com.fideliza.app.controller;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.interceptor.download.ByteArrayDownload;
import br.com.caelum.vraptor.interceptor.download.Download;
import br.com.fideliza.app.annotation.Permission;
import br.com.fideliza.app.component.EmpresaSession;
import br.com.fideliza.app.exception.CommonException;
import static br.com.fideliza.app.helper.Utils.*;
import br.com.fideliza.app.model.Fidelidade;
import br.com.fideliza.app.model.common.PerfilType;
import br.com.fideliza.app.repository.FidelidadeRepository;
import java.io.ByteArrayOutputStream;
import java.util.Collection;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

@Resource
@Permission({PerfilType.MEMBRO, PerfilType.MODERADOR, PerfilType.ADMINISTRADOR})
public class FidelidadeController {

    private final Result result;
    private final FidelidadeRepository repository;
    private final Validator validator;
    private final EmpresaSession session;

    public FidelidadeController(Result result, FidelidadeRepository repository, Validator validator, EmpresaSession session) {
        this.result = result;
        this.repository = repository;
        this.validator = validator;
        this.session = session;
    }

    @Get("/fidelidade/criar")
    public void criar(Fidelidade entity) {
        if (!repository.limitInserts(session.getEmpresa().getId())) {
            result.include("mensagem", i18n("fidelidade.limite")).redirectTo(this).listagem();
        } else {
            result.include("entity", entity);
        }
    }

    @Post("/fidelidade")
    public void salvar(Fidelidade entity) {
        // dados default
        entity.setIdEmpresa(session.getEmpresa());

        validator.validate(entity);
        validator.onErrorRedirectTo(this).criar(entity);
        try {
            entity = repository.save(entity);
            result.include("notice", i18n("fidelidade.salvo.sucesso")).redirectTo(this).exibir(entity);
        } catch (CommonException e) {
            result.include("error", i18n(e.getMessage())).redirectTo(this).criar(entity);
        }
    }

    @Get("/fidelidade")
    public void listagem() {
        Collection<Fidelidade> lista = repository.minhasFidelidades(session.getEmpresa().getId());
        result.include("fidelidadeList", lista);
    }

    @Get("/fidelidade/{entity.id}")
    public void exibir(Fidelidade entity) {
        entity = repository.find(entity.getId());
        result.include("entity", entity);
    }

    @Delete("/fidelidade/{entity.id}")
    public void remover(Fidelidade entity) {
        repository.remove(entity);
        result.include("notice", i18n("fidelidade.removido.sucesso")).redirectTo(this).listagem();
    }

    @Get("/fidelidade/{entity.id}/editar")
    public void editar(Fidelidade entity) {
        entity = repository.find(entity.getId());
        result.include("entity", entity);
    }

    @Put("/fidelidade/{entity.id}/atualizar")
    public void atualizar(Fidelidade entity) {
        // dados default
        entity.setIdEmpresa(session.getEmpresa());

        validator.validate(entity);
        validator.onErrorRedirectTo(this).editar(entity);

        try {
            entity = repository.save(entity);
            result.include("notice", i18n("fidelidade.atualizado.sucesso")).redirectTo(this).exibir(entity);
        } catch (CommonException e) {
            result.include("error", i18n(e.getMessage())).redirectTo(this).editar(entity);
        }
    }

    @Get("/fidelidade/qrcode")
    public Download qrcode(String qrText) {
        ByteArrayOutputStream out = QRCode.from(qrText).to(ImageType.PNG).withSize(400, 300).stream();
        return new ByteArrayDownload(out.toByteArray(), "image/png", "qrcode.png");
    }
}
