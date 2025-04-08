package br.com.salomaotech.genesys.controller.venda.venda_calcula;

import br.com.salomaotech.genesys.model.venda.ItemVenda;
import br.com.salomaotech.genesys.controller.venda.venda_inicia.produto.VendaIniciaMetodosProdutos;
import br.com.salomaotech.genesys.model.produto.ProdutoModelo;
import br.com.salomaotech.genesys.view.JFvendaCalcula;
import br.com.salomaotech.sistema.swing.MudaIconeJframe;

public class VendaCalculaControllerProdutos {

    private final JFvendaCalcula view = new JFvendaCalcula();
    private final VendaCalculaMetodosProdutos vendaCalculaMetodos = new VendaCalculaMetodosProdutos(view);
    private final VendaCalculaEventosProdutos vendaCalculaEventos = new VendaCalculaEventosProdutos(view);

    public VendaCalculaControllerProdutos(long id, VendaIniciaMetodosProdutos vendaIniciaMetodosProdutos) {

        /* metodos */
        vendaCalculaMetodos.setItemVenda(new ItemVenda(id, new ProdutoModelo()));
        vendaCalculaMetodos.setVendaIniciaMetodosProdutos(vendaIniciaMetodosProdutos);

        /* eventos */
        vendaCalculaEventos.setVendaCalculaMetodos(vendaCalculaMetodos);

    }

    public void construir() {

        /* view */
        new MudaIconeJframe().alterar("calcular32x", view);
        view.setVisible(true);

        /* eventos */
        vendaCalculaEventos.addEventos();

    }

}
