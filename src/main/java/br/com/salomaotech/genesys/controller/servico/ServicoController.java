package br.com.salomaotech.genesys.controller.servico;

import br.com.salomaotech.genesys.model.servico.ServicoPesquisa;
import br.com.salomaotech.genesys.view.JFservico;
import br.com.salomaotech.sistema.swing.MudaIconeJframe;
import javax.swing.JFrame;

public class ServicoController {

    private final JFservico view = new JFservico();
    private final ServicoMetodos servicoMetodos = new ServicoMetodos(view);
    private final ServicoEventos servicoEventos = new ServicoEventos(view);

    public void construir() {

        /* view */
        new MudaIconeJframe().alterar("servico64x", view);
        view.setVisible(true);
        view.setExtendedState(view.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        view.jTabaPrincipal.setSelectedIndex(1);
        view.jTnome.requestFocus();

        /* metodos */
        servicoMetodos.addPopUpMenu();
        servicoMetodos.habilitarCampos();

        /* eventos */
        servicoEventos.setServicoMetodos(servicoMetodos);
        servicoEventos.addEventos();

        /* exibe os dados */
        new ServicoPesquisa(view.jTresultados).pesquisar();

    }

}
