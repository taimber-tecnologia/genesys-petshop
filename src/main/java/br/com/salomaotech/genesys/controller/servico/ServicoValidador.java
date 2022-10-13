package br.com.salomaotech.genesys.controller.servico;

import br.com.salomaotech.genesys.model.servico.ServicoModelo;
import br.com.salomaotech.genesys.view.JFservico;
import br.com.salomaotech.sistema.algoritmos.IsStringNumeroValido;
import br.com.salomaotech.sistema.algoritmos.ValidaStringIsEmpty;
import br.com.salomaotech.sistema.jpa.JPQL;
import br.com.salomaotech.sistema.jpa.Repository;

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

        } else {

            JPQL jpql = new JPQL(new ServicoModelo());
            jpql.addParametroDiferente("id", view.getId());
            jpql.addParametroIgual("nome", view.jTnome.getText());

            /* valida se o nome já está sendo utilizado */
            if (!new Repository(new ServicoModelo()).getResults(jpql.construirSelect()).isEmpty()) {

                mensagensErro = "O nome já está sendo utilizado.";
                view.jTnome.requestFocus();
                return false;

            }

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
