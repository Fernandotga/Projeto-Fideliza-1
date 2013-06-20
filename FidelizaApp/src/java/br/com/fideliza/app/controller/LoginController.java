package br.com.fideliza.app.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.fideliza.app.annotation.Public;
import br.com.fideliza.app.component.EmpresaSession;
import static br.com.fideliza.app.helper.Utils.*;
import br.com.fideliza.app.model.Empresa;
import br.com.fideliza.app.repository.LoginRepository;
import java.security.NoSuchAlgorithmException;
import org.apache.log4j.Logger;

@Resource
public class LoginController {

    private final Result result;
    private final LoginRepository repository;
    private final EmpresaSession session;
    private static Logger log = Logger.getLogger(LoginController.class);

    public LoginController(Result result, LoginRepository repository, EmpresaSession session) {
        this.result = result;
        this.repository = repository;
        this.session = session;
    }

    @Public
    @Post("/autenticar")
    public void autenticar(String email, String password) throws NoSuchAlgorithmException {
        Empresa empresa = repository.autenticar(email, password);

        if (empresa != null) {
            session.setEmpresa(empresa);

            try {
                result.redirectTo(PainelController.class).principal();
            } catch (IllegalStateException ex) {
                log.error(ex);
                result.redirectTo(IndexController.class).index();
            }
        } else {
            result.include("error", i18n("email.senha.incorreta")).redirectTo(IndexController.class).index();
        }
    }

    @Get("/logout")
    public void logout() {
        session.logout();
        result.redirectTo(IndexController.class).index();
    }
}
