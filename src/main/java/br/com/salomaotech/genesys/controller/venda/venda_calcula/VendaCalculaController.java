package br.com.salomaotech.genesys.controller.venda.venda_calcula;

import br.com.salomaotech.genesys.model.venda.ItemVenda;
import br.com.salomaotech.genesys.controller.venda.venda_inicia.VendaIniciaMetodos;
import br.com.salomaotech.genesys.model.produto.ProdutoModelo;
import br.com.salomaotech.genesys.view.JFvendaCalcula;
import br.com.salomaotech.sistema.swing.MudaIconeJframe;

public class VendaCalculaController {

    private final JFvendaCalcula view = new JFvendaCalcula();
    private final VendaCalculaMetodos vendaCalculaMetodos = new VendaCalculaMetodos(view);
    private final VendaCalculaEventos vendaCalculaEventos = new VendaCalculaEventos(view);

    public VendaCalculaController(long id, VendaIniciaMetodos vendaIniciaMetodos) {

        /* metodos */
        vendaCalculaMetodos.setItemVenda(new ItemVenda(id, new ProdutoModelo()));
        vendaCalculaMetodos.setVendaIniciaMetodos(vendaIniciaMetodos);

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
