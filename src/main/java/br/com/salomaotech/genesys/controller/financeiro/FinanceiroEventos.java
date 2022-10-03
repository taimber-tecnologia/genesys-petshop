package br.com.salomaotech.genesys.controller.financeiro;

import br.com.salomaotech.genesys.controller.centro_custo.CentroCustoController;
import br.com.salomaotech.genesys.model.centro_custo.ComboBoxCentroCusto;
import br.com.salomaotech.genesys.model.financeiro.FinanceiroModelo;
import br.com.salomaotech.genesys.model.financeiro.FinanceiroPesquisa;
import br.com.salomaotech.genesys.view.JFfinanceiro;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

public class FinanceiroEventos {

    private final JFfinanceiro view;
    private FinanceiroMetodos financeiroMetodos;
    private ComboBoxCentroCusto comboBoxCentroCusto;

    public FinanceiroEventos(JFfinanceiro view) {
        this.view = view;
    }

    public void setFinanceiroMetodos(FinanceiroMetodos financeiroMetodos) {
        this.financeiroMetodos = financeiroMetodos;
    }

    public void setComboBoxCentroCusto(ComboBoxCentroCusto comboBoxCentroCusto) {
        this.comboBoxCentroCusto = comboBoxCentroCusto;
    }

    public void addEventos() {

        /* salvar */
        view.jBcadastroSalvar.addActionListener((ActionEvent e) -> {

            FinanceiroValidador financeiroValidador = new FinanceiroValidador(view);

            if (financeiroValidador.isValido()) {

                FinanceiroModelo financeiroModelo = financeiroMetodos.salvar();

                /* valida se salvou e atualiza os dados na view */
                if (financeiroModelo.getId() != 0) {

                    financeiroMetodos.popularFormulario(financeiroModelo);
                    financeiroMetodos.habilitarCampos();
                    new FinanceiroPesquisa(view.jTresultados, view.jCpaginador, view.jLsaldo).pesquisar();
                    JOptionPane.showMessageDialog(null, "Registro salvo com sucesso!");
                    view.jTcadastroValor.requestFocus();

                } else {

                    JOptionPane.showMessageDialog(null, "Falha ao tentar salvar registro.");

                }

            } else {

                JOptionPane.showMessageDialog(null, financeiroValidador.getMensagensErro());

            }

        });

        /* excluir */
        view.jBcadastroExcluir.addActionListener((ActionEvent e) -> {

            if (JOptionPane.showConfirmDialog(null, "Excluir registro?") == 0) {

                /* valida se excluiu e atualiza os dados na view */
                if (financeiroMetodos.excluir()) {

                    financeiroMetodos.resetarView();
                    financeiroMetodos.habilitarCampos();
                    new FinanceiroPesquisa(view.jTresultados, view.jCpaginador, view.jLsaldo).pesquisar();
                    JOptionPane.showMessageDialog(null, "Registro excluido!");
                    view.jTcadastroValor.requestFocus();

                } else {

                    JOptionPane.showMessageDialog(null, "Registro não excluido!");

                }

            }

        });

        /* abrir */
        view.jTresultados.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                if (e.getClickCount() == 2) {

                    financeiroMetodos.abrirCadastro((long) view.jTresultados.getModel().getValueAt(view.jTresultados.getSelectedRow(), 0));

                }

            }

        });

        /* novo */
        view.jBatalhoCadastro.addActionListener((ActionEvent e) -> {

            financeiroMetodos.resetarView();
            financeiroMetodos.habilitarCampos();
            view.jTabaPrincipal.setSelectedIndex(0);
            view.jTcadastroValor.requestFocus();

        });

        /* atalho para pesquisa */
        view.jBatalhoPesquisa.addActionListener((ActionEvent e) -> {

            view.jTabaPrincipal.setSelectedIndex(1);

        });

        /* pesquisa */
        view.jBpesquisa.addActionListener((ActionEvent e) -> {

            financeiroMetodos.pesquisar();

        });

        /* paginador e pesquisa */
        view.jBpaginador.addActionListener((ActionEvent e) -> {

            financeiroMetodos.pesquisar();

        });

        /* refresh combobox centro de custo de financeiro */
        view.jBrefreshCadastroCentroCusto.addActionListener((ActionEvent e) -> {

            comboBoxCentroCusto.preencher();

        });

        /* atalho para cadastrar centro de custo */
        view.jBatalhoCentroCusto.addActionListener((ActionEvent e) -> {

            new CentroCustoController().construir();

        });

        /* atalho para cadastrar centro de custo */
        view.jBpesquisaCentroCusto.addActionListener((ActionEvent e) -> {

            new CentroCustoController().construir();

        });

        /* opção de pesquisar data anterior */
        view.jCdataAnterior.addActionListener((ActionEvent e) -> {

            if (view.jCdataAnterior.isSelected()) {

                view.jDPesquisaDataFim.setDate(null);

            }

        });

    }

}
