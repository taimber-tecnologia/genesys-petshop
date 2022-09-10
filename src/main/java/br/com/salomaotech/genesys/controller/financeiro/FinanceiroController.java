package br.com.salomaotech.genesys.controller.financeiro;

import br.com.salomaotech.genesys.model.centro_custo.ComboBoxCentroCusto;
import br.com.salomaotech.genesys.model.financeiro.FinanceiroPesquisa;
import br.com.salomaotech.genesys.view.JFfinanceiro;
import br.com.salomaotech.sistema.swing.MudaIconeJframe;
import javax.swing.JFrame;

public class FinanceiroController {

    private final JFfinanceiro view = new JFfinanceiro();
    private final ComboBoxCentroCusto comboBoxCentroCusto = new ComboBoxCentroCusto(view.jCcadastroCentroCusto);
    private final FinanceiroMetodos financeiroMetodos = new FinanceiroMetodos(view);
    private final FinanceiroEventos financeiroEventos = new FinanceiroEventos(view);

    public FinanceiroController() {

        /* preenche comboboxes */
        comboBoxCentroCusto.preencher();

        /* metodos */
        financeiroMetodos.setComboBoxCentroCusto(comboBoxCentroCusto);

        /* eventos */
        financeiroEventos.setComboBoxCentroCusto(comboBoxCentroCusto);

    }

    public void construir() {

        /* view */
        new MudaIconeJframe().alterar("financeiro64x", view);
        view.setVisible(true);
        view.setExtendedState(view.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        view.jTcadastroValor.requestFocus();

        /* metodos */
        financeiroMetodos.addPopUpMenu();
        financeiroMetodos.habilitarCampos();

        /* eventos */
        financeiroEventos.setFinanceiroMetodos(financeiroMetodos);
        financeiroEventos.addEventos();

        /* exibe os dados */
        new FinanceiroPesquisa(view.jTresultados, view.jCpaginador, view.jLsaldo).pesquisar();

    }

}
