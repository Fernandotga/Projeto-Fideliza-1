package br.com.fideliza.app.interceptor;

import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.fideliza.app.component.EmpresaSession;
import br.com.fideliza.app.controller.IndexController;
import br.com.fideliza.app.controller.LoginController;
import java.util.Arrays;

@Intercepts(before = PermissionInterceptor.class)
public class LoginInterceptor implements Interceptor {

    private final Result result;
    private final EmpresaSession session;

    public LoginInterceptor(Result result, EmpresaSession session) {
        this.result = result;
        this.session = session;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean accepts(ResourceMethod method) {
        return !Arrays.asList(LoginController.class).contains(method.getMethod().getDeclaringClass());
    }

    @Override
    public void intercept(InterceptorStack stack, ResourceMethod method, Object resource) {
        if (session.isLogged()) {
            stack.next(method, resource);
        } else {
            result.redirectTo(IndexController.class).index();
        }
    }
}