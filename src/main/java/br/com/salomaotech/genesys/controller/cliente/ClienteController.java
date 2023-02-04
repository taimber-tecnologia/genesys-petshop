package br.com.salomaotech.genesys.controller.cliente;

import br.com.salomaotech.genesys.view.JFcliente;
import br.com.salomaotech.sistema.swing.MudaIconeJframe;
import javax.swing.JFrame;

public class ClienteController {

    private final JFcliente view = new JFcliente();
    private final ClienteMetodos clienteMetodos = new ClienteMetodos(view);
    private final ClienteEventos clienteEventos = new ClienteEventos(view);

    public void construir() {

        /* view */
        new MudaIconeJframe().alterar("add-cliente64x", view);
        view.setVisible(true);
        view.setExtendedState(view.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        view.jTabaPrincipal.setSelectedIndex(1);
        view.jTbasicoNome.requestFocus();

        /* metodos */
        clienteMetodos.addPopUpMenu();
        clienteMetodos.habilitarCampos();

        /* eventos */
        clienteEventos.setClienteMetodos(clienteMetodos);
        clienteEventos.addEventos();

        /* exibe os dados */
        clienteMetodos.pesquisar();

    }

}
