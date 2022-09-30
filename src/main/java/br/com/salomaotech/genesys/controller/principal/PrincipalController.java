package br.com.salomaotech.genesys.controller.principal;

import br.com.salomaotech.genesys.view.JFprincipal;
import br.com.salomaotech.sistema.swing.MudaIconeJframe;
import javax.swing.JFrame;

public class PrincipalController {

    private final JFprincipal view = new JFprincipal();
    private final PrincipalMetodos principalMetodos = new PrincipalMetodos(view);
    private final PrincipalEventos principalEventos = new PrincipalEventos(view);

    public void construir() {

        /* view */
        new MudaIconeJframe().alterar("animal64x", view);
        view.setVisible(true);
        view.setExtendedState(view.getExtendedState() | JFrame.MAXIMIZED_BOTH);

        /* metodos */
        principalMetodos.carregaAgendaDia();
        principalMetodos.carregaNotificacoes();

        /* eventos */
        principalEventos.setPrincipalMetodos(principalMetodos);
        principalEventos.addEventos();

    }

}
