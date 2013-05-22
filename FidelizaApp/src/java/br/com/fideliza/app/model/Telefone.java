package br.com.fideliza.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "telefone")
public class Telefone extends AbstractEntity {

    @Column(name = "telefone")
    private String telefone;
    @Enumerated(EnumType.STRING)
    @Column(name = "telefone_tipo")
    private TelefoneType telefoneTipo;
    @JoinColumn(name = "empresa", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Empresa empresa;

    public Telefone() {
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public TelefoneType getTelefoneTipo() {
        return telefoneTipo;
    }

    public void setTelefoneTipo(TelefoneType telefoneTipo) {
        this.telefoneTipo = telefoneTipo;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}
