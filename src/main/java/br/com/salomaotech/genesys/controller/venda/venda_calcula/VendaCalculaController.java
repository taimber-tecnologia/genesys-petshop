package br.com.salomaotech.genesys.controller.venda.venda_calcula;

import br.com.salomaotech.genesys.model.produto.ProdutoModelo;
import br.com.salomaotech.genesys.view.JFvendaCalcula;
import br.com.salomaotech.sistema.swing.MudaIconeJframe;

public class VendaCalculaController {

    private final JFvendaCalcula view = new JFvendaCalcula();
    private final VendaCalculaEventos vendaCalculaEventos = new VendaCalculaEventos(view);
    private final VendaCalculaMetodos vendaCalculaMetodos = new VendaCalculaMetodos(view);

    public VendaCalculaController(ProdutoModelo produtoModelo) {

        /* metodos */
        vendaCalculaMetodos.setProdutoModelo(produtoModelo);

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
