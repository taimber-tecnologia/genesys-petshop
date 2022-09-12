package br.com.salomaotech.genesys.controller.venda_concluir;

import br.com.salomaotech.genesys.model.venda.VendaModelo;
import br.com.salomaotech.genesys.view.JFvendaConcluir;
import br.com.salomaotech.sistema.swing.MudaIconeJframe;

public class VendaConcluirController {

    private final JFvendaConcluir view = new JFvendaConcluir();
    private final VendaConcluirEventos vendaConcluirEventos = new VendaConcluirEventos(view);
    private final VendaConcluirMetodos vendaConcluirMetodos;

    public VendaConcluirController(VendaModelo vendaModelo) {
        vendaConcluirMetodos = new VendaConcluirMetodos(view, vendaModelo);
    }

    public void construir() {

        /* view */
        new MudaIconeJframe().alterar("venda64x", view);
        view.setVisible(true);

        /* eventos */
        vendaConcluirEventos.setVendaConcluirMetodos(vendaConcluirMetodos);
        vendaConcluirEventos.addEventos();

        /* metodos */
        vendaConcluirMetodos.exibirVenda();

    }

}
