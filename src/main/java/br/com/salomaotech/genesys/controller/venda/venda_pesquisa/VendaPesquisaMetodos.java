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

        // Valida ID (Código de cadastro) de pesquisa
        try {

            vendaPesquisa.setIdPesquisa(Long.parseLong(view.jTpesquisaCodigo.getText()));

        } catch (NumberFormatException ex) {

        }

        vendaPesquisa.pesquisar();

    }

    public void resetarViewPesquisa() {

        view.jTpesquisaCodigo.setText(null);
        view.jDpesquisaData.setDate(null);
        view.jCpaginador.setSelectedIndex(-1);
        pesquisar();

    }

    public void abrirCadastro(long id) {

        new VendaIniciaController().abrirCadastro(id);

    }

}
