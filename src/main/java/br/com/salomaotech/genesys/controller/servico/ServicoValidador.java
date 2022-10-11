package br.com.salomaotech.genesys.controller.servico;

import br.com.salomaotech.genesys.view.JFservico;
import br.com.salomaotech.sistema.algoritmos.IsStringNumeroValido;
import br.com.salomaotech.sistema.algoritmos.ValidaStringIsEmpty;

public class ServicoValidador {

    private final JFservico view;
    private String mensagensErro = "";

    public ServicoValidador(JFservico view) {
        this.view = view;
    }

    public boolean isValido() {

        /* valida nome */
        if (ValidaStringIsEmpty.isEmpty(view.jTnome.getText())) {

            mensagensErro = "Informe um nome.";
            view.jTnome.requestFocus();
            return false;

        }

        /* valida o valor */
        if (!IsStringNumeroValido.isNumeroValido(view.jTvalor.getText())) {

            mensagensErro = "Valor inválido.";
            view.jTvalor.requestFocus();
            return false;

        }

        return true;

    }

    public String getMensagensErro() {
        return mensagensErro;
    }

}
