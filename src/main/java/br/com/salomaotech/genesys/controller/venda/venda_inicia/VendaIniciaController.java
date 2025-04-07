package br.com.salomaotech.genesys.controller.venda.venda_inicia;

import br.com.salomaotech.genesys.model.venda.VendaModelo;
import br.com.salomaotech.genesys.model.venda.VendaModeloItem;
import br.com.salomaotech.genesys.view.JFvendaInicia;
import br.com.salomaotech.sistema.swing.MudaIconeJframe;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

public class VendaIniciaController {

    private final JFvendaInicia view = new JFvendaInicia();
    private final List<VendaModeloItem> vendaModeloItemList = new ArrayList();
    private final VendaModelo vendaModelo = new VendaModelo();
    private final VendaIniciaMetodosComum vendaIniciaMetodosComum = new VendaIniciaMetodosComum(view, vendaModeloItemList, vendaModelo);
    private final VendaIniciaEventosComum vendaIniciaEventosComum = new VendaIniciaEventosComum(view, vendaIniciaMetodosComum);
    private final VendaIniciaMetodosProdutos vendaIniciaMetodosProdutos = new VendaIniciaMetodosProdutos(view, vendaModeloItemList, vendaIniciaMetodosComum);
    private final VendaIniciaMetodosServicos vendaIniciaMetodosServicos = new VendaIniciaMetodosServicos(view, vendaModeloItemList, vendaIniciaMetodosComum);
    private final VendaIniciaEventosProdutos vendaIniciaEventosProdutos = new VendaIniciaEventosProdutos(view, vendaIniciaMetodosProdutos);
    private final VendaIniciaEventosServicos vendaIniciaEventosServicos = new VendaIniciaEventosServicos(view, vendaIniciaMetodosServicos);

    public void construir() {

        /* view */
        new MudaIconeJframe().alterar("venda64x", view);
        view.setVisible(true);
        view.setExtendedState(view.getExtendedState() | JFrame.MAXIMIZED_BOTH);

        /* metodos */
        vendaIniciaMetodosProdutos.pesquisarProdutos();
        vendaIniciaMetodosServicos.pesquisarServicos();
        vendaIniciaMetodosComum.habilitarCampos();
        vendaIniciaMetodosComum.setVendaIniciaMetodosProdutos(vendaIniciaMetodosProdutos);
        vendaIniciaMetodosComum.setVendaIniciaMetodosServicos(vendaIniciaMetodosServicos);

        /* eventos */
        vendaIniciaEventosProdutos.addEventos();
        vendaIniciaEventosServicos.addEventos();
        vendaIniciaEventosComum.addEventos();

    }

    public JFvendaInicia abrirCadastro(long id) {

        construir();
        vendaIniciaMetodosComum.abrirCadastro(id);
        return view;

    }

}
