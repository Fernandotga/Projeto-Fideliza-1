package br.com.fideliza.app.dao;

import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.caelum.vraptor.ioc.Component;
import br.com.fideliza.app.helper.Upload;
import br.com.fideliza.app.model.Oferta;
import br.com.fideliza.app.repository.OfertaRepository;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

@Component
public class OfertaDAO extends GenericDAO<Oferta> implements OfertaRepository {

    private static Logger log = Logger.getLogger(OfertaDAO.class);

    public OfertaDAO(EntityManager manager) {
        super(manager);
    }

    @Override
    public Boolean limitInserts(Long id) {
        // Regra de 4 ofertas por empresa
        long limit = 4;
        try {
            Query query = manager.createQuery("select count(n) from " + Oferta.class.getName() + " n where id_empresa = :id");
            query.setParameter("id", id);
            long result = (Long) query.getSingleResult();
            if (result >= limit) {
                return false;
            }
            return true;
        } catch (NoResultException ex) {
            log.error(ex);
            return false;
        }
    }

    @Override
    public List<Oferta> minhasOfertas(Long id) {
        try {
            Query query = manager.createQuery("from " + Oferta.class.getName() + " where id_empresa = :id");
            query.setParameter("id", id);

            return query.getResultList();
        } catch (NoResultException ex) {
            log.error(ex);
            return null;
        }
    }

    @Override
    public void removeImage(Oferta oferta) throws Exception {
        if (!oferta.getImg().equals("default.jpg")) {
            File file = new File(Oferta.IMAGE_PATH, oferta.getImg());
            if (file.exists() && !file.delete()) {
                throw new Exception("Não foi possível apagar a imagem.");
            }
            oferta.setImg("default.jpg");
            this.updateImage(oferta);
        }
    }

    @Override
    public void updateImage(Oferta oferta) {
        Query query = manager.createQuery("update " + Oferta.class.getName()+ " set img = :imagem where id = :id");
        query.setParameter("imagem", oferta.getImg());
        query.setParameter("id", oferta.getId());
        query.executeUpdate();
    }

    @Override
    public void uploadImage(UploadedFile file, Oferta oferta) throws Exception {
        String extension = Upload.getExtension(file.getFileName());
        if (!Upload.isValidExtension(extension)) {
            throw new Exception("Tipo de arquivo não permitido!\nUse: JPG, JPEG, GIF, BMP ou PNG.");
        }
        oferta.setImg(oferta.getId() + extension);
        File diretorio = new File(Oferta.IMAGE_PATH);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }
        IOUtils.copy(file.getFile(), new FileOutputStream(new File(diretorio, oferta.getImg())));
        this.updateImage(oferta);
    }
}
