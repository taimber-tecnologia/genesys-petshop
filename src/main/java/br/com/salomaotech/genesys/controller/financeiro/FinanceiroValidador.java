package br.com.salomaotech.genesys.controller.financeiro;

import br.com.salomaotech.genesys.view.JFfinanceiro;
import br.com.salomaotech.sistema.algoritmos.Datas;
import br.com.salomaotech.sistema.algoritmos.IsStringNumeroValido;

public class FinanceiroValidador {

    private final JFfinanceiro view;
    private String mensagensErro = "";

    public FinanceiroValidador(JFfinanceiro view) {
        this.view = view;
    }

    public boolean isValido() {

        /* valida data */
        if (!Datas.isCalendarioValido(view.jDcadastroData.getCalendar())) {

            mensagensErro = "Data inválida.";
            return false;

        }

        /* valida o valor */
        if (!IsStringNumeroValido.isNumeroValido(view.jTcadastroValor.getText())) {

            mensagensErro = "Valor inválido.";
            view.jTcadastroValor.requestFocus();
            return false;

        }

        /* valida centro de custo */
        if (view.jCcadastroCentroCusto.getSelectedIndex() == 0) {

            mensagensErro = "Informe o centro de custo.";
            view.jCcadastroCentroCusto.requestFocus();
            return false;

        }

        return true;

    }

    public String getMensagensErro() {
        return mensagensErro;
    }

}
