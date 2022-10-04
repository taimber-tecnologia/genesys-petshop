package br.com.salomaotech.genesys.controller.venda.venda_pesquisa;

import br.com.salomaotech.genesys.controller.venda.venda_inicia.VendaIniciaController;
import br.com.salomaotech.genesys.model.venda.VendaPesquisa;
import br.com.salomaotech.genesys.view.JFvendaPesquisa;
import br.com.salomaotech.sistema.swing.PopUp;

public class VendaPesquisaMetodos {

    final JFvendaPesquisa view;

    public VendaPesquisaMetodos(JFvendaPesquisa view) {
        this.view = view;
    }

    public void pesquisar() {

        VendaPesquisa vendaPesquisa = new VendaPesquisa(view.jTresultados, view.jCpaginador);
        vendaPesquisa.setData(view.jDpesquisaData.getCalendar());
        vendaPesquisa.setCpfCliente(view.jTpesquisaCpf.getText());
        vendaPesquisa.pesquisar();

    }

    public void addPopUpMenu() {

        PopUp popUp = new PopUp();
        popUp.adicionarMenu(view.jTpesquisaCpf);

    }

    public void abrirCadastro(long id) {

        new VendaIniciaController().abrirCadastro(id);

    }

}
