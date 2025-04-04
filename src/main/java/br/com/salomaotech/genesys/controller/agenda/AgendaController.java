package br.com.salomaotech.genesys.controller.agenda;

import br.com.salomaotech.genesys.model.cliente.ComboBoxClientes;
import br.com.salomaotech.genesys.view.JFagenda;
import br.com.salomaotech.sistema.swing.MudaIconeJframe;
import java.util.Calendar;
import javax.swing.JFrame;

public class AgendaController {

    private final JFagenda view = new JFagenda();
    private final ComboBoxClientes comboBoxClientes;
    private final AgendaMetodos agendaMetodos = new AgendaMetodos(view);
    private final AgendaEventos agendaEventos = new AgendaEventos(view);

    public AgendaController() {

        /* preenche comboboxes */
        comboBoxClientes = new ComboBoxClientes(view.jCcadastroNomeCliente, new AgendaClienteCommand(view));
        comboBoxClientes.preencher();

    }

    public void construir() {

        /* view */
        new MudaIconeJframe().alterar("agenda64x", view);
        view.setVisible(true);
        view.setExtendedState(view.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        view.jTabaPrincipal.setSelectedIndex(1);

        /* metodos */
        agendaMetodos.addPopUpMenu();
        agendaMetodos.habilitarCampos();
        agendaMetodos.pesquisar();

        /* eventos */
        agendaEventos.setComboBoxClientes(comboBoxClientes);
        agendaEventos.setAgendaMetodos(agendaMetodos);
        agendaEventos.addEventos();

    }

    public AgendaMetodos getMetodos() {
        return agendaMetodos;
    }

    public void carregarAgendaVencida() {

        construir();
        view.jDpesquisaDataInicio.setDate(Calendar.getInstance().getTime());
        view.jCpesquisaDataAnterior.setSelected(true);
        agendaMetodos.pesquisar();

    }

}
