package br.com.salomaotech.genesys.controller.servico;

import br.com.salomaotech.genesys.view.JFservico;

public class ServicoValidador {

    private final JFservico view;
    private String mensagensErro = "";

    public ServicoValidador(JFservico view) {
        this.view = view;
    }

    public boolean isValido() {

        return true;

    }

    public String getMensagensErro() {
        return mensagensErro;
    }

}
