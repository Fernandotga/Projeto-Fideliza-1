package br.com.fideliza.app.model;

import br.com.fideliza.app.model.common.AbstractEntity;
import br.com.fideliza.app.model.common.PerfilType;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "fid_empresas")
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
    @Column(name = "logo")
    private String logo;
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
    @OneToMany(mappedBy = "idEmpresa")
    private Collection<Telefone> telefones;
    @OneToMany(mappedBy = "idEmpresa")
    private Collection<Web> webs;
    @OneToMany(mappedBy = "idEmpresa")
    private Collection<Fidelidade> fidelidades;
    @OneToMany(mappedBy = "idEmpresa")
    private Collection<RegistrosCheckins> checkins;
    public static final String IMAGE_PATH = System.getProperty("user.dir") + "/sistemas/movy/uploads/img/empresa";

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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
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

    public Collection<Telefone> getTelefone() {
        return telefones;
    }

    public void setTelefone(Collection<Telefone> telefones) {
        this.telefones = telefones;
    }

    public Collection<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(Collection<Telefone> telefones) {
        this.telefones = telefones;
    }

    public Collection<Fidelidade> getFidelidades() {
        return fidelidades;
    }

    public void setFidelidades(Collection<Fidelidade> fidelidades) {
        this.fidelidades = fidelidades;
    }
}