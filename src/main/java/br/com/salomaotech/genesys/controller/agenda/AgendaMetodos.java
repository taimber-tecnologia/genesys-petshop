package br.com.salomaotech.genesys.controller.agenda;

import br.com.salomaotech.genesys.model.agenda.AgendaModelo;
import br.com.salomaotech.genesys.model.agenda.AgendaPesquisa;
import br.com.salomaotech.genesys.model.animal.ComboBoxAnimais;
import br.com.salomaotech.genesys.model.cliente.ComboBoxClientes;
import br.com.salomaotech.genesys.view.JFagenda;
import br.com.salomaotech.sistema.jpa.Repository;
import br.com.salomaotech.sistema.swing.PopUp;

public class AgendaMetodos {

    private final JFagenda view;
    private ComboBoxClientes comboBoxClientesCadastro;
    private ComboBoxClientes comboBoxClientesPesquisa;
    private ComboBoxAnimais comboBoxAnimaisCadastro;

    public AgendaMetodos(JFagenda view) {
        this.view = view;
    }

    public void setComboBoxClientesCadastro(ComboBoxClientes comboBoxClientesCadastro) {
        this.comboBoxClientesCadastro = comboBoxClientesCadastro;
    }

    public void setComboBoxClientesPesquisa(ComboBoxClientes comboBoxClientesPesquisa) {
        this.comboBoxClientesPesquisa = comboBoxClientesPesquisa;
    }

    public void setComboBoxAnimaisCadastro(ComboBoxAnimais comboBoxAnimaisCadastro) {
        this.comboBoxAnimaisCadastro = comboBoxAnimaisCadastro;
    }

    public void popularFormulario(AgendaModelo agendaModelo) {

        view.setId(agendaModelo.getId());
        view.jDcadastroData.setCalendar(agendaModelo.getDataAgenda());
        view.jCcadastroHora.setSelectedItem(agendaModelo.getDataHora());
        view.jCcadastroMinuto.setSelectedItem(agendaModelo.getDataMinuto());
        comboBoxClientesCadastro.selecionarItemPorId(agendaModelo.getIdCliente());
        comboBoxAnimaisCadastro.selecionarItemComBox(agendaModelo.getIdAnimal());
        view.jTcadastroHistorico.setText(agendaModelo.getObservacoes());
        view.jCstatus.setSelectedItem(agendaModelo.getStatus());

    }

    public void resetarView() {

        AgendaModelo agendaModelo = new AgendaModelo();
        popularFormulario(agendaModelo);
        view.jCcadastroHora.setSelectedIndex(0);
        view.jCcadastroMinuto.setSelectedIndex(0);
        view.jCstatus.setSelectedIndex(0);

        /* evita erro de indexOfBounds no select */
        try {

            view.jCcadastroNomeCliente.setSelectedIndex(0);

        } catch (Exception ex) {

            view.jCcadastroNomeCliente.addItem("");
            view.jCcadastroNomeCliente.setSelectedIndex(0);

        }

        /* evita erro de indexOfBounds no select */
        try {

            view.jCcadastroNomeAnimal.setSelectedIndex(0);

        } catch (Exception ex) {

            view.jCcadastroNomeAnimal.addItem("");
            view.jCcadastroNomeAnimal.setSelectedIndex(0);

        }

    }

    public void habilitarCampos() {

        view.jBcadastroExcluir.setEnabled(view.getId() != 0);

    }

    public void addPopUpMenu() {

        PopUp popUp = new PopUp();
        popUp.adicionarMenu(view.jTcadastroHistorico);

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
        AgendaPesquisa.setDataAgenda(view.jDpesquisaData.getCalendar());
        AgendaPesquisa.setIdCliente(comboBoxClientesPesquisa.getIdSelecionado());
        AgendaPesquisa.setStatus(view.jCpesquisaStatus.getSelectedItem().toString());
        AgendaPesquisa.pesquisar();

    }

    public AgendaModelo salvar() {

        AgendaModelo agendaModelo = new AgendaModelo();
        agendaModelo.setId(view.getId());
        agendaModelo.setIdCliente(comboBoxClientesCadastro.getIdSelecionado());
        agendaModelo.setIdAnimal(comboBoxAnimaisCadastro.getIdAnimalSelecionado());
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
