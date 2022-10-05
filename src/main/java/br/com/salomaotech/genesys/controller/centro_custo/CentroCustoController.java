package br.com.salomaotech.genesys.controller.centro_custo;

import br.com.salomaotech.genesys.model.centro_custo.CentroCustoPesquisa;
import br.com.salomaotech.genesys.view.JFcentroCusto;
import br.com.salomaotech.sistema.swing.MudaIconeJframe;
import javax.swing.JFrame;

public class CentroCustoController {

    private final JFcentroCusto view = new JFcentroCusto();
    private final CentroCustoMetodos centroCustoMetodos = new CentroCustoMetodos(view);
    private final CentroCustoEventos centroCustoEventos = new CentroCustoEventos(view);

    public void construir() {

        /* view */
        new MudaIconeJframe().alterar("centro-custo64x", view);
        view.setVisible(true);
        view.setExtendedState(view.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        view.jTabaPrincipal.setSelectedIndex(1);
        view.jTcadastroCodigo.requestFocus();

        /* metodos */
        centroCustoMetodos.addPopUpMenu();
        centroCustoMetodos.habilitarCampos();

        /* eventos */
        centroCustoEventos.setCentroCustoMetodos(centroCustoMetodos);
        centroCustoEventos.addEventos();

        /* exibe os dados */
        new CentroCustoPesquisa(view.jTresultados).pesquisar();

    }

}
