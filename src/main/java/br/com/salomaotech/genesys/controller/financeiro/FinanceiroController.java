package br.com.salomaotech.genesys.controller.financeiro;

import br.com.salomaotech.genesys.model.centro_custo.ComboBoxCentroCusto;
import br.com.salomaotech.genesys.model.financeiro.FinanceiroPesquisa;
import br.com.salomaotech.genesys.view.JFfinanceiro;
import br.com.salomaotech.sistema.swing.MudaIconeJframe;
import java.util.Calendar;
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
        view.jTabaPrincipal.setSelectedIndex(1);
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

    public void construirContasPagar() {

        construir();
        view.jDpesquisaDataInicio.setDate(Calendar.getInstance().getTime());
        view.jCpesquisaPago.setSelectedItem("Nao");
        view.jCpesquisaDataAnterior.setSelected(true);
        view.jCpesquisaIsDespesa.setSelectedItem("Pagar");
        financeiroMetodos.pesquisar();

    }

    public void construirContasReceber() {

        construir();
        view.jDpesquisaDataInicio.setDate(Calendar.getInstance().getTime());
        view.jCpesquisaPago.setSelectedItem("Nao");
        view.jCpesquisaDataAnterior.setSelected(true);
        view.jCpesquisaIsDespesa.setSelectedItem("Receber");
        financeiroMetodos.pesquisar();

    }

}
