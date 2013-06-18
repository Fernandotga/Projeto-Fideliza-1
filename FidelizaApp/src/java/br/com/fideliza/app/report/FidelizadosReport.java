package br.com.fideliza.app.report;

import br.com.caelum.vraptor.jasperreports.Report;
import br.com.fideliza.app.model.Cliente;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class FidelizadosReport implements Report {

    private final Collection<Cliente> data;
    private Map<String, Object> parameters;

    public FidelizadosReport(Collection<Cliente> data) {
        this.data = data;
        this.parameters = new HashMap<>();
    }

    @Override
    public Report addParameter(String key, Object value) {
        this.parameters.put(key, value);
        return this;
    }

    @Override
    public Collection<Cliente> getData() {
        return data;
    }

    @Override
    public String getFileName() {
        return "Fidelizados" + System.currentTimeMillis();
    }

    @Override
    public Map<String, Object> getParameters() {
        return this.parameters;
    }

    @Override
    public String getTemplate() {
        return "/Fidelizados.jasper";
    }

    @Override
    public boolean isCacheable() {
        return true;
    }
}
