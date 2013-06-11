package br.com.fideliza.app.repository;

import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.fideliza.app.model.Oferta;
import java.util.List;

public interface OfertaRepository extends GenericRepository<Oferta> {

    Boolean limitInserts(Long id);

    List<Oferta> minhasOfertas(Long id);

    void removeImage(Oferta oferta) throws Exception;

    void updateImage(Oferta oferta);

    void uploadImage(UploadedFile file, Oferta oferta) throws Exception;
}
