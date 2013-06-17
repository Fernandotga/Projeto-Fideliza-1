package br.com.fideliza.app.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.download.Download;
import br.com.caelum.vraptor.jasperreports.Report;
import br.com.caelum.vraptor.jasperreports.download.ReportDownload;
import br.com.caelum.vraptor.jasperreports.formats.ExportFormats;
import static br.com.caelum.vraptor.jasperreports.formats.ExportFormats.*;
import br.com.fideliza.app.annotation.Permission;
import br.com.fideliza.app.component.EmpresaSession;
import br.com.fideliza.app.exception.CommonException;
import br.com.fideliza.app.model.Cliente;
import br.com.fideliza.app.model.ClienteFidelidade;
import br.com.fideliza.app.model.common.PerfilType;
import br.com.fideliza.app.report.FidelizadosReport;
import br.com.fideliza.app.repository.FidelizadosRepository;
import java.util.Collection;
import java.util.List;

@Resource
@Permission({PerfilType.MEMBRO, PerfilType.ADMINISTRADOR, PerfilType.MODERADOR})
public class FidelizadosController {

    private final Result result;
    private final FidelizadosRepository repository;
    private final EmpresaSession session;
    private final ExportFormats formats;

    public FidelizadosController(Result result, FidelizadosRepository repository, EmpresaSession session, ExportFormats formats) {
        this.result = result;
        this.repository = repository;
        this.session = session;
        this.formats = formats;
    }

    @Get("/fidelizados")
    public void listagem() {
        Collection<Cliente> lista = repository.meusClientes(session.getEmpresa().getId());
        result.include("fidelizadosList", lista);
    }

    @Get("/fidelizados/busca")
    public void busca(String busca) {
        Collection<Cliente> lista = repository.buscar(busca, session.getEmpresa().getId());
        result.include("fidelizadosList", lista);
    }

    @Get("/fidelizados/{entity.id}")
    public void exibir(Cliente entity) {
        entity = repository.find(entity.getId());
        result.include("entity", entity);
    }

    @Get("/fidelizados/{entity.id}/baixa")
    public void baixa(ClienteFidelidade entity) throws CommonException {
        repository.trocarPontos(entity.getId());
        result.redirectTo(this).listagem();
    }

    @Path("/fidelizados/pdf")
    public Download pdfReport() {
        Report report = generateReport();
        return new ReportDownload(report, pdf());
    }

//    @Path("/fidelizados/csv")
//    public Download csvReport() {
//        Report report = generateReport();
//        return new ReportDownload(report, csv());
//    }
//
//    @Path("/fidelizados/xls")
//    public Download xlsReport() {
//        Report report = generateReport();
//        return new ReportDownload(report, xls());
//    }
//
//    @Path("/fidelizados/docx")
//    public Download docxReport() {
//        Report report = generateReport();
//        return new ReportDownload(report, docx());
//    }
//
//    @Path("/fidelizados/txt")
//    public Download txtReport() {
//        Report report = generateReport();
//        return new ReportDownload(report, txt());
//    }
//
//    @Path("/fidelizados/odt")
//    public Download odtReport() {
//        Report report = generateReport();
//        return new ReportDownload(report, odt());
//    }
//
//    @Path("/fidelizados/rtf")
//    public Download rtfReport() {
//        Report report = generateReport();
//        return new ReportDownload(report, rtf());
//    }

//    @Path("/fidelizados/report/preview")
//    public Download previewReport() {
//        Report report = generateReport();
//        return new ReportDownload(report, png(), false);
//    }
//
//    @Path("/fidelizados/report/{format}")
//    public Download report(String format) {
//        Report report = generateReport();
//        return new ReportDownload(report, formats.byExtension(format));
//    }

    private Report generateReport() {
        Collection<Cliente> data = repository.all();
        return new FidelizadosReport((List<Cliente>) data);
    }
}
