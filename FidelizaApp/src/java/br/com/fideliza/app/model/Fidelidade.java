package br.com.fideliza.app.model;

import br.com.fideliza.app.model.common.AbstractEntity;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "fid_fidelidades")
public class Fidelidade extends AbstractEntity {

    @Column(name = "maximo_pontos")
    private int maximoPontos;
    @Column(name = "recompensa")
    private String recompensa;
    @Column(name = "validade_dias")
    private int validadeDias;
    @Lob
    @Column(name = "termos_recompensa")
    private String termosRecompensa;
    @Column(name = "quantidade_limitada_checkin")
    private Integer quantidadePermitida;
    @Column(name = "ativo")
    private Boolean ativo;
    @OneToMany(mappedBy = "idFidelidade")
    private Collection<ClienteFidelidade> clientesFidelidades;
    @JoinColumn(name = "id_empresa", referencedColumnName = "id")
    @ManyToOne
    private Empresa idEmpresa;

    public Fidelidade() {
    }

    public int getMaximoPontos() {
        return maximoPontos;
    }

    public void setMaximoPontos(int maximoPontos) {
        this.maximoPontos = maximoPontos;
    }

    public String getRecompensa() {
        return recompensa;
    }

    public void setRecompensa(String recompensa) {
        this.recompensa = recompensa;
    }

    public int getValidadeDias() {
        return validadeDias;
    }

    public void setValidadeDias(int validadeDias) {
        this.validadeDias = validadeDias;
    }

    public String getTermosRecompensa() {
        return termosRecompensa;
    }

    public void setTermosRecompensa(String termosRecompensa) {
        this.termosRecompensa = termosRecompensa;
    }

    public Integer getQuantidadePermitida() {
        return quantidadePermitida;
    }

    public void setQuantidadePermitida(Integer quantidadePermitida) {
        this.quantidadePermitida = quantidadePermitida;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Empresa getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Empresa idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Collection<ClienteFidelidade> getClientesFidelidades() {
        return clientesFidelidades;
    }

    public void setClientesFidelidades(Collection<ClienteFidelidade> clientesFidelidades) {
        this.clientesFidelidades = clientesFidelidades;
    }
}
