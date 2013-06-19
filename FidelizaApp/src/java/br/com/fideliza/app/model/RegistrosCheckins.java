package br.com.fideliza.app.model;

import br.com.fideliza.app.model.common.AbstractEntity;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "fid_checkins")
public class RegistrosCheckins extends AbstractEntity {

    @Column(name = "data_registro")
    @Temporal(TemporalType.DATE)
    private Date dataRegistro;
    @Column(name = "hora")
    @Temporal(TemporalType.TIME)
    private Date hora;
    @JoinColumn(name = "id_empresa", referencedColumnName = "id")
    @ManyToOne
    private Empresa idEmpresa;

    public RegistrosCheckins() {
    }

    public Date getData() {
        return dataRegistro;
    }

    public void setData(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public Empresa getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Empresa idEmpresa) {
        this.idEmpresa = idEmpresa;
    }
}
