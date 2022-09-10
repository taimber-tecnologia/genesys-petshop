package br.com.salomaotech.genesys.controller.fornecedor;

import br.com.salomaotech.genesys.model.fornecedor.FornecedorPesquisa;
import br.com.salomaotech.genesys.view.JFfornecedor;
import br.com.salomaotech.sistema.swing.MudaIconeJframe;
import javax.swing.JFrame;

public class FornecedorController {

    private final JFfornecedor view = new JFfornecedor();
    private final FornecedorMetodos fornecedorMetodos = new FornecedorMetodos(view);
    private final FornecedorEventos fornecedorEventos = new FornecedorEventos(view);

    public void construir() {

        /* view */
        new MudaIconeJframe().alterar("add-fornecedor64x", view);
        view.setVisible(true);
        view.setExtendedState(view.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        view.jTbasicoNome.requestFocus();

        /* metodos */
        fornecedorMetodos.addPopUpMenu();
        fornecedorMetodos.habilitarCampos();

        /* eventos */
        fornecedorEventos.setFornecedorMetodos(fornecedorMetodos);
        fornecedorEventos.addEventos();

        /* exibe os dados */
        new FornecedorPesquisa(view.jTresultados).pesquisar();

    }

}
