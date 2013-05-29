package br.com.fideliza.app.model.common;

import java.io.UnsupportedEncodingException;

public enum TelefoneType {

    EMPRESARIAL("Empresarial"),
    RESIDENCIAL("Residencial"),
    CELULAR("Celular"),
    FAX("Fax"),
    FIXO("Fixo");
    public String label;

    private TelefoneType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return this.label.toString();
    }
}
