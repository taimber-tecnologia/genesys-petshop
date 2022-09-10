package br.com.salomaotech.genesys.controller.vacina;

import br.com.salomaotech.genesys.view.JFvacina;
import br.com.salomaotech.sistema.algoritmos.Datas;
import br.com.salomaotech.sistema.algoritmos.ValidaStringIsEmpty;

public class VacinaValidador {

    private final JFvacina view;
    private String mensagensErro = "";

    public VacinaValidador(JFvacina view) {
        this.view = view;
    }

    public boolean isValido() {

        /* o nome da vacina não pode ficar em branco */
        if (ValidaStringIsEmpty.isEmpty(view.jCcadastroNome.getEditor().getItem().toString())) {

            mensagensErro = "Informe o nome da vacina!";
            view.jCcadastroNome.requestFocus();
            return false;

        }

        /* valida data de aplicação, ela não pode ser nula */
        if (!Datas.isCalendarioValido(view.jDcadastroDataAplicacao.getCalendar())) {

            mensagensErro = "Data da aplicação inválida!";
            return false;

        }

        return true;

    }

    public String getMensagensErro() {
        return mensagensErro;
    }

}
