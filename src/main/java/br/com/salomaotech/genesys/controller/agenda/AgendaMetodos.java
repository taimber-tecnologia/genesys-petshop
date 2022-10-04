package br.com.salomaotech.genesys.controller.agenda;

import br.com.salomaotech.genesys.model.agenda.AgendaModelo;
import br.com.salomaotech.genesys.model.agenda.AgendaPesquisa;
import br.com.salomaotech.genesys.view.JFagenda;
import br.com.salomaotech.sistema.jpa.Repository;
import br.com.salomaotech.sistema.swing.PopUp;

public class AgendaMetodos {

    private final JFagenda view;

    public AgendaMetodos(JFagenda view) {
        this.view = view;
    }

    public void popularFormulario(AgendaModelo agendaModelo) {

        view.setId(agendaModelo.getId());
        view.jDcadastroData.setCalendar(agendaModelo.getDataAgenda());
        view.jCcadastroHora.setSelectedItem(agendaModelo.getDataHora());
        view.jCcadastroMinuto.setSelectedItem(agendaModelo.getDataMinuto());
        view.jTcadastroNomeCliente.setText(agendaModelo.getNomeCliente());
        view.jTcadastroHistorico.setText(agendaModelo.getObservacoes());
        view.jCstatus.setSelectedItem(agendaModelo.getStatus());

    }

    public void resetarView() {

        AgendaModelo agendaModelo = new AgendaModelo();
        popularFormulario(agendaModelo);
        view.jCcadastroHora.setSelectedIndex(0);
        view.jCcadastroMinuto.setSelectedIndex(0);
        view.jCstatus.setSelectedIndex(0);

    }

    public void habilitarCampos() {

        view.jBcadastroExcluir.setEnabled(view.getId() != 0);

    }

    public void addPopUpMenu() {

        PopUp popUp = new PopUp();
        popUp.adicionarMenu(view.jTcadastroNomeCliente);
        popUp.adicionarMenu(view.jTcadastroHistorico);
        popUp.adicionarMenu(view.jTpesquisaNomeCliente);

    }

    public void abrirCadastro(long id) {

        Repository repository = new Repository(new AgendaModelo());
        AgendaModelo agendaModelo = (AgendaModelo) repository.findById(id);
        popularFormulario(agendaModelo);
        view.jTabaPrincipal.setSelectedIndex(0);
        habilitarCampos();

    }

    public void pesquisar() {

        AgendaPesquisa AgendaPesquisa = new AgendaPesquisa(view.jTresultados, view.jCpaginador);
        AgendaPesquisa.setDataInicialDate(view.jDpesquisaDataInicio.getCalendar());
        AgendaPesquisa.setDataFinalDate(view.jDPesquisaDataFim.getCalendar());
        AgendaPesquisa.setNomeCliente(view.jTpesquisaNomeCliente.getText());
        AgendaPesquisa.setStatus(view.jCpesquisaStatus.getSelectedItem().toString());
        AgendaPesquisa.pesquisar();

    }

    public AgendaModelo salvar() {

        AgendaModelo agendaModelo = new AgendaModelo();
        agendaModelo.setId(view.getId());
        agendaModelo.setNomeCliente(view.jTcadastroNomeCliente.getText());
        agendaModelo.setDataAgenda(view.jDcadastroData.getCalendar());
        agendaModelo.setDataHora(view.jCcadastroHora.getSelectedItem().toString());
        agendaModelo.setDataMinuto(view.jCcadastroMinuto.getSelectedItem().toString());
        agendaModelo.setObservacoes(view.jTcadastroHistorico.getText());
        agendaModelo.setStatus(view.jCstatus.getSelectedItem().toString());
        new Repository(agendaModelo).save();
        return agendaModelo;

    }

    public boolean excluir() {

        return new Repository(new AgendaModelo()).delete(view.getId());

    }

}
