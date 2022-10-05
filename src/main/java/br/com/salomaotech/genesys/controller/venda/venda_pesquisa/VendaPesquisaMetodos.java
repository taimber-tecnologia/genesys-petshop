package br.com.salomaotech.genesys.controller.venda.venda_pesquisa;

import br.com.salomaotech.genesys.controller.venda.venda_inicia.VendaIniciaController;
import br.com.salomaotech.genesys.model.venda.VendaPesquisa;
import br.com.salomaotech.genesys.view.JFvendaPesquisa;

public class VendaPesquisaMetodos {

    final JFvendaPesquisa view;

    public VendaPesquisaMetodos(JFvendaPesquisa view) {
        this.view = view;
    }

    public void pesquisar() {

        VendaPesquisa vendaPesquisa = new VendaPesquisa(view.jTresultados, view.jCpaginador);
        vendaPesquisa.setData(view.jDpesquisaData.getCalendar());
        vendaPesquisa.pesquisar();

    }

    public void addPopUpMenu() {

    }

    public void abrirCadastro(long id) {

        new VendaIniciaController().abrirCadastro(id);

    }

}
