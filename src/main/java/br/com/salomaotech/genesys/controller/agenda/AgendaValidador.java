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

            mensagensErro = "Data inválida.";
            view.jTabaPrincipal.setSelectedIndex(0);
            return false;

        }

        /* valida nome do cliente */
        if (view.jCcadastroNomeCliente.getEditor().getItem().toString().equals("")) {

            mensagensErro = "Informe o nome do cliente.";
            view.jTabaPrincipal.setSelectedIndex(0);
            return false;

        }

        /* valida o telefone */
        if (view.jTcadastroTelefone.getText().equals("")) {

            mensagensErro = "Informe o número do telefone.";
            view.jTabaPrincipal.setSelectedIndex(0);
            view.jTcadastroTelefone.requestFocus();
            return false;

        }

        return true;

    }

    public String getMensagensErro() {
        return mensagensErro;
    }

}
