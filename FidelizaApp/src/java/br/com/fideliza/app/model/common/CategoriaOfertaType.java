package br.com.fideliza.app.model.common;

public enum CategoriaOfertaType {

    ALIMENTACAO("Alimentação"),
    SAUDE("Saude"),
    BELEZA("Beleza"),
    CURSOS("Cursos"),
    LIVRARIA("Livraria"),
    VESTUARIO("Vestuario"),
    LAZER("Lazer"),
    DIVERSOS("Diversos");
    public String label;

    private CategoriaOfertaType(String label) {
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
