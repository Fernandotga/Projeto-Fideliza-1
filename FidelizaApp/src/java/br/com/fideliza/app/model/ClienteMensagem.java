package br.com.fideliza.app.model;

import br.com.fideliza.app.model.common.AbstractEntity;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "fid_clientes_mensagens")
public class ClienteMensagem extends AbstractEntity {

    @JoinColumn(name = "id_mensagem", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Menssagem idMensagem;
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cliente idCliente;

    public ClienteMensagem() {
    }

    public Menssagem getIdMensagem() {
        return idMensagem;
    }

    public void setIdMensagem(Menssagem idMensagem) {
        this.idMensagem = idMensagem;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }
}
