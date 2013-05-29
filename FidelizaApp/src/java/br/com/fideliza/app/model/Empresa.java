package br.com.fideliza.app.model;

import br.com.fideliza.app.model.common.AbstractEntity;
import br.com.fideliza.app.model.common.PerfilType;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.validator.Email;
import org.hibernate.validator.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "empresa")
public class Empresa extends AbstractEntity {

    @Email
    @NotNull
    @NotEmpty
    @Column(name = "email")
    private String email;
    @NotNull
    @NotEmpty
    @Column(name = "password")
    private String password;
    @NotNull
    @Column(name = "nome_fantasia")
    private String nomeFantasia;
    @Column(name = "razao_social")
    private String razaoSocial;
    @Column(name = "cnpj")
    private String cnpj;
    @NotNull
    @Column(name = "responsavel")
    private String responsavel;
    @Column(name = "endereco")
    private String endereco;
    @Column(name = "latitude")
    private Double latitude;
    @Column(name = "longitude")
    private Double longitude;
    @Column(name = "url_logo")
    private String urlLogo;
    @Column(name = "data_cadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;
    @NotNull
    @Column(name = "ativo")
    private Boolean ativo;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "perfil")
    private PerfilType perfil;
    @OneToMany(mappedBy = "empresa")
    private List<Fidelidade> fidelidadeList;
    @OneToMany(mappedBy = "empresa")
    private List<Menssagem> menssagemList;
    @OneToMany(mappedBy = "empresa")
    private List<Telefone> telefoneList;
    @OneToMany(mappedBy = "empresa")
    private List<Oferta> ofertaList;
    @OneToMany(mappedBy = "empresa")
    private List<Web> webList;

    public Empresa() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getUrlLogo() {
        return urlLogo;
    }

    public void setUrlLogo(String urlLogo) {
        this.urlLogo = urlLogo;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public PerfilType getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilType perfil) {
        this.perfil = perfil;
    }

    public List<Fidelidade> getFidelidadeList() {
        return fidelidadeList;
    }

    public void setFidelidadeList(List<Fidelidade> fidelidadeList) {
        this.fidelidadeList = fidelidadeList;
    }

    public List<Menssagem> getMenssagemList() {
        return menssagemList;
    }

    public void setMenssagemList(List<Menssagem> menssagemList) {
        this.menssagemList = menssagemList;
    }

    public List<Telefone> getTelefoneList() {
        return telefoneList;
    }

    public void setTelefoneList(List<Telefone> telefoneList) {
        this.telefoneList = telefoneList;
    }

    public List<Oferta> getOfertaList() {
        return ofertaList;
    }

    public void setOfertaList(List<Oferta> ofertaList) {
        this.ofertaList = ofertaList;
    }

    public List<Web> getWebList() {
        return webList;
    }

    public void setWebList(List<Web> webList) {
        this.webList = webList;
    }
}
