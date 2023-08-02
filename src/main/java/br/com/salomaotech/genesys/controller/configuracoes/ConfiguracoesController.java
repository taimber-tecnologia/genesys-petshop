package br.com.salomaotech.genesys.controller.configuracoes;

import br.com.salomaotech.genesys.view.JFconfiguracoes;
import br.com.salomaotech.sistema.swing.MudaIconeJframe;
import javax.swing.JFrame;

public class ConfiguracoesController {

    private final JFconfiguracoes view = new JFconfiguracoes();
    private final ConfiguracoesMetodos configuracoesMetodos = new ConfiguracoesMetodos(view);
    private final ConfiguracoesEventos configuracoesEventos = new ConfiguracoesEventos(view);

    public ConfiguracoesController() {

        /* eventos */
        configuracoesEventos.setConfiguracoesMetodos(configuracoesMetodos);

    }

    public void construir() {

        /* view */
        new MudaIconeJframe().alterar("configurar24x", view);
        view.setVisible(true);
        view.setExtendedState(view.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        view.jTservidor.requestFocus();

        /* metodos */
        configuracoesMetodos.addPopUpMenu();
        configuracoesMetodos.popularFormulario();
        configuracoesMetodos.habilitarCampos();

        /* eventos */
        configuracoesEventos.addEventos();

    }

}
