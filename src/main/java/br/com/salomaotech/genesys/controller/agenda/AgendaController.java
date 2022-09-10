package br.com.salomaotech.genesys.controller.agenda;

import br.com.salomaotech.genesys.model.agenda.AgendaPesquisa;
import br.com.salomaotech.genesys.model.animal.ComboBoxAnimais;
import br.com.salomaotech.genesys.model.cliente.ComboBoxClientes;
import br.com.salomaotech.genesys.view.JFagenda;
import br.com.salomaotech.sistema.swing.MudaIconeJframe;
import javax.swing.JFrame;

public class AgendaController {

    private final JFagenda view = new JFagenda();
    private final ComboBoxAnimais comboBoxAnimaisCadastro = new ComboBoxAnimais(view.jCcadastroNomeAnimal);
    private final ComboBoxClientes comboBoxClientesCadastro = new ComboBoxClientes(view.jCcadastroNomeCliente, comboBoxAnimaisCadastro);
    private final ComboBoxClientes comboBoxClientesPesquisa = new ComboBoxClientes(view.jCpesquisaNomeCliente);
    private final AgendaMetodos agendaMetodos = new AgendaMetodos(view);
    private final AgendaEventos agendaEventos = new AgendaEventos(view);

    public AgendaController() {

        /* preenche comboboxes */
        comboBoxClientesCadastro.preencher();
        comboBoxClientesPesquisa.preencher();

        /* metodos */
        agendaMetodos.setComboBoxClientesCadastro(comboBoxClientesCadastro);
        agendaMetodos.setComboBoxClientesPesquisa(comboBoxClientesPesquisa);
        agendaMetodos.setComboBoxAnimaisCadastro(comboBoxAnimaisCadastro);

        /* eventos */
        agendaEventos.setAgendaMetodos(agendaMetodos);
        agendaEventos.setComboBoxClientesCadastro(comboBoxClientesCadastro);
        agendaEventos.setComboBoxClientesPesquisa(comboBoxClientesPesquisa);
        agendaEventos.setComboBoxAnimaisCadastro(comboBoxAnimaisCadastro);

    }

    public void construir() {

        /* view */
        new MudaIconeJframe().alterar("agenda64x", view);
        view.setVisible(true);
        view.setExtendedState(view.getExtendedState() | JFrame.MAXIMIZED_BOTH);

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
