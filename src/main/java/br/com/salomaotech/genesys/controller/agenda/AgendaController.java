package br.com.salomaotech.genesys.controller.agenda;

import br.com.salomaotech.genesys.model.agenda.AgendaPesquisa;
import br.com.salomaotech.genesys.view.JFagenda;
import br.com.salomaotech.sistema.swing.MudaIconeJframe;
import javax.swing.JFrame;

public class AgendaController {

    private final JFagenda view = new JFagenda();
    private final AgendaMetodos agendaMetodos = new AgendaMetodos(view);
    private final AgendaEventos agendaEventos = new AgendaEventos(view);

    public AgendaController() {

        /* eventos */
        agendaEventos.setAgendaMetodos(agendaMetodos);

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

        /* eventos */
        agendaEventos.addEventos();

        /* exibe os dados */
        new AgendaPesquisa(view.jTresultados, view.jCpaginador).pesquisar();

    }

    public AgendaMetodos getMetodos() {
        return agendaMetodos;
    }

}
