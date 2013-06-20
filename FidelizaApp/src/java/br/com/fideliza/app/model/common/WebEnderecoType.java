package br.com.fideliza.app.model.common;

public enum WebEnderecoType {

    WEBSITE("Web Site"),
    EMAIL("E-mail"),
    FACEBOOK("Facebook"),
    TWITTER("Twitter"),
    GOOGLEPLUS("Google+");
    public String label;

    private WebEnderecoType(String label) {
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
