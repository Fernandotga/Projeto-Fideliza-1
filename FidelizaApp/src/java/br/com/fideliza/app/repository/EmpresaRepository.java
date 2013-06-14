package br.com.fideliza.app.repository;

import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.fideliza.app.model.Empresa;

public interface EmpresaRepository extends GenericRepository<Empresa> {

    Boolean isMailExist(Empresa entity);

    void removeImage(Empresa oferta) throws Exception;

    void updateImage(Empresa oferta);

    void uploadImage(UploadedFile file, Empresa oferta) throws Exception;
}
