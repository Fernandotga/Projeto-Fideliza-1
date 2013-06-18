package br.com.fideliza.app.repository;

import br.com.fideliza.app.model.Empresa;
import java.security.NoSuchAlgorithmException;

public interface LoginRepository {

    Empresa autenticar(String email, String senha) throws NoSuchAlgorithmException;
}
