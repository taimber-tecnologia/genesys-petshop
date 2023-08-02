package br.com.salomaotech.genesys.controller.agenda;

import br.com.salomaotech.genesys.model.cliente.ClienteModelo;
import br.com.salomaotech.genesys.view.JFagenda;
import br.com.salomaotech.sistema.jpa.Repository;
import br.com.salomaotech.sistema.patterns.Command;

public class AgendaClienteCommand implements Command {

    private final JFagenda view;

    public AgendaClienteCommand(JFagenda view) {
        this.view = view;
    }

    @Override
    public void executar(Object arg) {

        try {

            long idCliente = Long.parseLong(arg.toString());
            ClienteModelo clienteModelo = (ClienteModelo) new Repository(new ClienteModelo()).findById(idCliente);
            view.jTcadastroTelefone.setText(clienteModelo.getTelefone());

        } catch (NumberFormatException ex) {

            view.jTcadastroTelefone.setText("");

        }

    }

}
