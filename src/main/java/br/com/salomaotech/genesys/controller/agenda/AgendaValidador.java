package br.com.salomaotech.genesys.controller.agenda;

import br.com.salomaotech.genesys.view.JFagenda;
import br.com.salomaotech.sistema.algoritmos.Datas;

public class AgendaValidador {

    private final JFagenda view;
    private String mensagensErro = "";

    public AgendaValidador(JFagenda view) {
        this.view = view;
    }

    public boolean isValido() {

        /* valida data de agendamento */
        if (!Datas.isCalendarioValido(view.jDcadastroData.getCalendar())) {

            mensagensErro = "Data de agendamento inválida.";
            return false;

        }

        /* valida cliente */
        if (view.jCcadastroNomeCliente.getSelectedIndex() == 0) {

            mensagensErro = "Informe o nome do cliente.";
            view.jCcadastroNomeCliente.requestFocus();
            return false;

        }

        /* valida animal */
        if (view.jCcadastroNomeAnimal.getSelectedIndex() == 0) {

            mensagensErro = "Informe o nome do animal.";
            view.jCcadastroNomeAnimal.requestFocus();
            return false;

        }

        return true;

    }

    public String getMensagensErro() {
        return mensagensErro;
    }

}
