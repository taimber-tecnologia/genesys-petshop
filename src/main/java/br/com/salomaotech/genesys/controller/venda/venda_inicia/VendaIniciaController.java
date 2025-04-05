package br.com.salomaotech.genesys.controller.venda.venda_inicia;

import br.com.salomaotech.genesys.view.JFvendaInicia;
import br.com.salomaotech.sistema.swing.MudaIconeJframe;
import javax.swing.JFrame;

public class VendaIniciaController {

    private final JFvendaInicia view = new JFvendaInicia();
    private final VendaIniciaMetodos vendaIniciaMetodos = new VendaIniciaMetodos(view);
    private final VendaIniciaEventos vendaIniciaEventos = new VendaIniciaEventos(view);

    public void construir() {

        /* view */
        new MudaIconeJframe().alterar("venda64x", view);
        view.setVisible(true);
        view.setExtendedState(view.getExtendedState() | JFrame.MAXIMIZED_BOTH);

        /* metodos */
        vendaIniciaMetodos.pesquisarProdutos();
        vendaIniciaMetodos.habilitarCampos();

        /* eventos */
        vendaIniciaEventos.addEventos();
        vendaIniciaEventos.setVendaIniciaMetodos(vendaIniciaMetodos);

    }

    public JFvendaInicia abrirCadastro(long id) {

        construir();
        vendaIniciaMetodos.abrirCadastro(id);
        return view;

    }

}
