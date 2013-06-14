package br.com.fideliza.app.dao;

import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.caelum.vraptor.ioc.Component;
import br.com.fideliza.app.exception.CommonException;
import br.com.fideliza.app.helper.Upload;
import br.com.fideliza.app.model.Empresa;
import br.com.fideliza.app.repository.EmpresaRepository;
import java.io.File;
import java.io.FileOutputStream;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.apache.commons.io.IOUtils;

@Component
public class EmpresaDAO extends GenericDAO<Empresa> implements EmpresaRepository {

    public EmpresaDAO(EntityManager manager) {
        super(manager);
    }

    @Override
    public Boolean isMailExist(Empresa entity) {
        try {
            Query query = manager.createQuery("select id from " + Empresa.class.getName() + " where email = :email and (:id is null or id != :id)");
            query.setParameter("email", entity.getEmail());
            query.setParameter("id", entity.getId());
            return (query.getSingleResult() != null);
        } catch (NoResultException e) {
            return false;
        }
    }

    @Override
    public Empresa save(Empresa entity) throws CommonException {
        if (this.isMailExist(entity)) {
            throw new CommonException("email.ja.cadastrado");
        }

        return super.save(entity);
    }

@Override
    public void removeImage(Empresa empresa) throws Exception {
        if (!empresa.getLogo().equals("default.jpg")) {
            File file = new File(Empresa.IMAGE_PATH, empresa.getLogo());
            if (file.exists() && !file.delete()) {
                throw new Exception("Não foi possível apagar a imagem.");
            }
            empresa.setLogo("default.jpg");
            this.updateImage(empresa);
        }
    }

    @Override
    public void updateImage(Empresa empresa) {
        Query query = manager.createQuery("update " + Empresa.class.getName()+ " set logo = :imagem where id = :id");
        query.setParameter("imagem", empresa.getLogo());
        query.setParameter("id", empresa.getId());
        query.executeUpdate();
    }

    @Override
    public void uploadImage(UploadedFile file, Empresa empresa) throws Exception {
        String extension = Upload.getExtension(file.getFileName());
        if (!Upload.isValidExtension(extension)) {
            throw new Exception("Tipo de arquivo não permitido!\nUse: JPG, JPEG, GIF, BMP ou PNG.");
        }
        empresa.setLogo(empresa.getId() + extension);
        File diretorio = new File(Empresa.IMAGE_PATH);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }
        IOUtils.copy(file.getFile(), new FileOutputStream(new File(diretorio, empresa.getLogo())));
        this.updateImage(empresa);
    }
}
