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
@Table(name = "fid_clientes_fidelidades_historicos")
public class ClienteFidelidadeHistorico extends AbstractEntity {

    @Column(name = "status_fidelidade")
    private String statusFidelidade;
    @Column(name = "data_troca_vencimento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataTroca;
    @Column(name = "pontos_acumulados")
    private int pontosAcumulados;
    @JoinColumn(name = "id_cliente_fidelidade", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ClienteFidelidade idClienteFidelidade;

    public ClienteFidelidadeHistorico() {
    }

    public String getStatusFidelidade() {
        return statusFidelidade;
    }

    public void setStatusFidelidade(String statusFidelidade) {
        this.statusFidelidade = statusFidelidade;
    }

    public Date getDataTroca() {
        return dataTroca;
    }

    public void setDataTroca(Date dataTroca) {
        this.dataTroca = dataTroca;
    }

    public int getPontosAcumulados() {
        return pontosAcumulados;
    }

    public void setPontosAcumulados(int pontosAcumulados) {
        this.pontosAcumulados = pontosAcumulados;
    }

    public ClienteFidelidade getIdClienteFidelidade() {
        return idClienteFidelidade;
    }

    public void setIdClienteFidelidade(ClienteFidelidade idClienteFidelidade) {
        this.idClienteFidelidade = idClienteFidelidade;
    }
}
