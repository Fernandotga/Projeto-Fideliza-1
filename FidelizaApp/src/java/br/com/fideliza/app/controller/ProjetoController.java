package br.com.fideliza.app.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.fideliza.app.annotation.Public;

@Public
@Resource
public class ProjetoController {

    @Get("/projeto/sobre")
    public void sobre() {
    }
}
