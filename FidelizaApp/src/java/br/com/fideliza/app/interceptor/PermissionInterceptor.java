package br.com.fideliza.app.interceptor;

import java.util.Arrays;
import java.util.Collection;

import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.fideliza.app.annotation.Permission;
import br.com.fideliza.app.component.EmpresaSession;
import br.com.fideliza.app.controller.EmpresaController;
import br.com.fideliza.app.controller.LoginController;
import br.com.fideliza.app.model.Empresa;
import br.com.fideliza.app.model.common.PerfilType;

@Intercepts(after = LoginInterceptor.class)
public class PermissionInterceptor implements Interceptor {

    private final Result result;
    private final EmpresaSession session;

    public PermissionInterceptor(Result result, EmpresaSession session) {
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
        Permission controllerList = method.getResource().getType().getAnnotation(Permission.class);
        Permission metodoList = method.getMethod().getAnnotation(Permission.class);

        if (this.hasAccess(metodoList) && this.hasAccess(controllerList)) {
            stack.next(method, resource);
        } else {
            result.redirectTo(EmpresaController.class).negado();
        }
    }

    private boolean hasAccess(Permission permissaoList) {
        if (permissaoList == null) {
            return true;
        }

        Empresa user = session.getEmpresa();

        Collection<PerfilType> perfilList = Arrays.asList(permissaoList.value());

        return perfilList.contains(user.getPerfil());
    }
}