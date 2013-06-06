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
@Table(name = "fid_clientes_fidelidades")
public class ClienteFidelidade extends AbstractEntity {

    @Column(name = "pontos")
    private int pontos;
    @Column(name = "data_fidelidade")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataFidelidade;
    @JoinColumn(name = "id_fidelidade", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Fidelidade idFidelidade;
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cliente idCliente;

    public ClienteFidelidade() {
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public Date getDataFidelidade() {
        return dataFidelidade;
    }

    public void setDataFidelidade(Date dataFidelidade) {
        this.dataFidelidade = dataFidelidade;
    }

    public Fidelidade getIdFidelidade() {
        return idFidelidade;
    }

    public void setIdFidelidade(Fidelidade idFidelidade) {
        this.idFidelidade = idFidelidade;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }
}
