package br.com.salomaotech.genesys.controller.empresa;

import br.com.salomaotech.genesys.view.JFempresa;
import br.com.salomaotech.sistema.algoritmos.ValidaStringIsEmpty;

public class EmpresaValidador {

    private final JFempresa view;
    private String mensagensErro = "";

    public EmpresaValidador(JFempresa view) {
        this.view = view;
    }

    public boolean isValido() {

        /* valida nome */
        if (ValidaStringIsEmpty.isEmpty(view.jTbasicoNome.getText())) {

            mensagensErro = "Informe o nome.";
            view.jTabaCadastro.setSelectedIndex(0);
            view.jTbasicoNome.requestFocus();
            return false;

        }

        /* valida CNPJ */
        if (view.jFbasicoCnpj.getText().equals("  .   .   /    -  ")) {

            mensagensErro = "CNPJ inválido.";
            view.jTabaCadastro.setSelectedIndex(0);
            view.jFbasicoCnpj.requestFocus();
            return false;

        }

        return true;

    }

    public String getMensagensErro() {
        return mensagensErro;
    }

}
