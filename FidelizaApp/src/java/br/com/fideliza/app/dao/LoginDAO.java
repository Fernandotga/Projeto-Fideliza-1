package br.com.fideliza.app.dao;

import br.com.caelum.vraptor.ioc.Component;
import br.com.fideliza.app.helper.Seguranca;
import br.com.fideliza.app.model.Empresa;
import br.com.fideliza.app.repository.LoginRepository;
import java.security.NoSuchAlgorithmException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.apache.log4j.Logger;

@Component
public class LoginDAO implements LoginRepository {

    private EntityManager manager;
    private static Logger log = Logger.getLogger(LoginDAO.class);

    public LoginDAO(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public Empresa autenticar(String email, String senha) throws NoSuchAlgorithmException {
        try {
            Query query = manager.createQuery("from " + Empresa.class.getName() + " where email = :email and password = :senha");
            query.setParameter("email", email);
            query.setParameter("senha", Seguranca.criptografaSenha(senha));
            return (Empresa) query.getSingleResult();
        } catch (NoResultException ex) {
            log.error(ex);
            return null;
        }
    }
}
