package br.com.salomaotech.genesys.controller.empresa;

import br.com.salomaotech.genesys.view.JFempresa;
import br.com.salomaotech.sistema.swing.MudaIconeJframe;
import javax.swing.JFrame;

public class EmpresaController {

    private final JFempresa view = new JFempresa();
    private final EmpresaMetodos empresaMetodos = new EmpresaMetodos(view);
    private final EmpresaEventos empresaEventos = new EmpresaEventos(view);

    public void construir() {

        /* view */
        new MudaIconeJframe().alterar("empresa64x", view);
        view.setVisible(true);
        view.setExtendedState(view.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        view.jTabaCadastro.setSelectedIndex(0);

        /* metodos */
        empresaMetodos.addPopUpMenu();
        empresaMetodos.carregaDadosEmpresa();
        empresaMetodos.habilitarCampos();

        /* eventos */
        empresaEventos.setEmpresaMetodos(empresaMetodos);
        empresaEventos.addEventos();

    }

}
