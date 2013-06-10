package br.com.fideliza.app.model;

import br.com.fideliza.app.model.common.AbstractEntity;
import br.com.fideliza.app.model.common.CategoriaOfertaType;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;

@Entity
@Table(name = "fid_ofertas")
public class Oferta extends AbstractEntity {

    @Column(name = "categoria")
    @Enumerated(EnumType.STRING)
    private CategoriaOfertaType categoria;
    @Column(name = "descricao_item")
    private String descricaoItem;
    @Lob
    @Column(name = "img")
    private byte[] img;
    @Lob
    @Column(name = "descricao_detalhe")
    private String descricaoDetalhe;
    @Min(0)
    @Column(name = "preco")
    private double preco;
    @Column(name = "data_inicio_oferta")
    @Temporal(TemporalType.DATE)
    private Date dataInicioOferta;
    @Column(name = "data_final_oferta")
    @Temporal(TemporalType.DATE)
    private Date dataFinalOferta;
    @JoinColumn(name = "id_empresa", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Empresa idEmpresa;

    public Oferta() {
    }

    public CategoriaOfertaType getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaOfertaType categoria) {
        this.categoria = categoria;
    }

    public String getDescricaoItem() {
        return descricaoItem;
    }

    public void setDescricaoItem(String descricaoItem) {
        this.descricaoItem = descricaoItem;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public Empresa getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Empresa idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getDescricaoDetalhe() {
        return descricaoDetalhe;
    }

    public void setDescricaoDetalhe(String descricaoDetalhe) {
        this.descricaoDetalhe = descricaoDetalhe;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Date getDataInicioOferta() {
        return dataInicioOferta;
    }

    public void setDataInicioOferta(Date dataInicioOferta) {
        this.dataInicioOferta = dataInicioOferta;
    }

    public Date getDataFinalOferta() {
        return dataFinalOferta;
    }

    public void setDataFinalOferta(Date dataFinalOferta) {
        this.dataFinalOferta = dataFinalOferta;
    }
}
