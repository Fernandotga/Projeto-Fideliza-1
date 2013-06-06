package br.com.fideliza.app.model;

import br.com.fideliza.app.model.common.WebEnderecoType;
import br.com.fideliza.app.model.common.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "fid_web")
public class Web extends AbstractEntity {

    @Column(name = "url")
    private String url;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private WebEnderecoType tipo;
    @JoinColumn(name = "id_empresa", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Empresa idEmpresa;

    public Web() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public WebEnderecoType getTipo() {
        return tipo;
    }

    public void setTipo(WebEnderecoType tipo) {
        this.tipo = tipo;
    }

    public Empresa getId_empresa() {
        return idEmpresa;
    }

    public void setId_empresa(Empresa id_empresa) {
        this.idEmpresa = id_empresa;
    }

}
