package br.com.salomaotech.genesys.controller.venda.venda_conclui;

import br.com.salomaotech.genesys.model.venda.VendaModelo;
import br.com.salomaotech.genesys.model.venda.VendaModeloItem;
import br.com.salomaotech.genesys.view.JFvendaInicia;
import br.com.salomaotech.genesys.view.JFvendaConclui;
import br.com.salomaotech.sistema.swing.MudaIconeJframe;
import java.util.List;

public class VendaConcluiController {

    private final JFvendaConclui view = new JFvendaConclui();
    private final VendaConcluiMetodos vendaConcluiMetodos;
    private final VendaConcluiEventos vendaConcluiEventos;

    public VendaConcluiController(VendaModelo vendaModelo, JFvendaInicia viewVenda, List<VendaModeloItem> vendaModeloItemBaixaList, List<VendaModeloItem> vendaModeloItemDevolveList) {

        /* metodos */
        vendaConcluiMetodos = new VendaConcluiMetodos(view, vendaModelo, viewVenda, vendaModeloItemBaixaList, vendaModeloItemDevolveList);

        /* eventos */
        vendaConcluiEventos = new VendaConcluiEventos(view, vendaModelo);

    }

    public void construir() {

        /* view */
        new MudaIconeJframe().alterar("venda64x", view);
        view.setVisible(true);

        /* eventos */
        vendaConcluiEventos.setVendaConcluiMetodos(vendaConcluiMetodos);
        vendaConcluiEventos.addEventos();

        /* metodos */
        vendaConcluiMetodos.habilitarCampos();
        vendaConcluiMetodos.exibirVenda();

    }

}
