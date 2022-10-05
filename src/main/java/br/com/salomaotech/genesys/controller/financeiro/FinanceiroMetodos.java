package br.com.salomaotech.genesys.controller.financeiro;

import br.com.salomaotech.genesys.model.centro_custo.ComboBoxCentroCusto;
import br.com.salomaotech.genesys.model.financeiro.FinanceiroModelo;
import br.com.salomaotech.genesys.model.financeiro.FinanceiroPesquisa;
import br.com.salomaotech.genesys.view.JFfinanceiro;
import br.com.salomaotech.sistema.algoritmos.BigDecimais;
import br.com.salomaotech.sistema.jpa.Repository;
import br.com.salomaotech.sistema.swing.PopUp;

public class FinanceiroMetodos {

    private final JFfinanceiro view;
    private ComboBoxCentroCusto comboBoxCentroCusto;

    public FinanceiroMetodos(JFfinanceiro view) {
        this.view = view;
    }

    public void setComboBoxCentroCusto(ComboBoxCentroCusto comboBoxCentroCusto) {
        this.comboBoxCentroCusto = comboBoxCentroCusto;
    }

    public void popularFormulario(FinanceiroModelo financeiroModelo) {

        view.setId(financeiroModelo.getId());
        view.jDcadastroData.setCalendar(financeiroModelo.getData());
        view.jTcadastroValor.setText(financeiroModelo.getValor().toString());
        view.jTcadastroDescricao.setText(financeiroModelo.getDescricao());
        comboBoxCentroCusto.selecionarItemPorId(financeiroModelo.getIdCentroCusto());
        view.jCcadastroPago.setSelected(financeiroModelo.isIsPago());
        view.setIdVenda(financeiroModelo.getIdVenda());

        /* valida se é uma despesa */
        if (financeiroModelo.isIsDespesa()) {

            view.jCcadastroIsDespesa.setSelectedIndex(0);

        } else {

            view.jCcadastroIsDespesa.setSelectedIndex(1);

        }

    }

    public void resetarView() {

        popularFormulario(new FinanceiroModelo());
        view.jTcadastroValor.setText(null);
        view.jCcadastroIsDespesa.setSelectedIndex(0);

    }

    public void habilitarCampos() {

        boolean isIdAberto = view.getId() != 0;
        view.jBcadastroExcluir.setEnabled(isIdAberto);

    }

    public void addPopUpMenu() {

        PopUp popUp = new PopUp();
        popUp.adicionarMenu(view.jTcadastroValor);
        popUp.adicionarMenu(view.jTcadastroDescricao);

    }

    public void abrirCadastro(long id) {

        Repository repository = new Repository(new FinanceiroModelo());
        FinanceiroModelo financeiroModelo = (FinanceiroModelo) repository.findById(id);
        popularFormulario(financeiroModelo);
        habilitarCampos();
        view.jTabaPrincipal.setSelectedIndex(0);

    }

    public FinanceiroModelo salvar() {

        FinanceiroModelo financeiroModelo = new FinanceiroModelo();
        financeiroModelo.setId(view.getId());
        financeiroModelo.setData(view.jDcadastroData.getCalendar());
        financeiroModelo.setValor(BigDecimais.formatarParaBigDecimal(view.jTcadastroValor.getText()));
        financeiroModelo.setDescricao(view.jTcadastroDescricao.getText());
        financeiroModelo.setIdCentroCusto(comboBoxCentroCusto.getIdSelecionado());
        financeiroModelo.setIsDespesa(view.jCcadastroIsDespesa.getSelectedIndex() == 0);
        financeiroModelo.setIsPago(view.jCcadastroPago.isSelected());
        financeiroModelo.setIdVenda(view.getIdVenda());
        new Repository(financeiroModelo).save();
        return financeiroModelo;

    }

    public boolean excluir() {

        return new Repository(new FinanceiroModelo()).delete(view.getId());

    }

    public void pesquisar() {

        FinanceiroPesquisa financeiroPesquisa = new FinanceiroPesquisa(view.jTresultados, view.jCpaginador, view.jLsaldo);
        financeiroPesquisa.setDataInicialDate(view.jDpesquisaDataInicio.getCalendar());
        financeiroPesquisa.setDataFinalDate(view.jDPesquisaDataFim.getCalendar());
        financeiroPesquisa.setPagamentoRealizado(view.jCpesquisaPago.getSelectedItem().toString());
        financeiroPesquisa.setPagamentoDespesa(view.jCpesquisaIsDespesa.getSelectedItem().toString());
        financeiroPesquisa.setIsDataAnterior(view.jCdataAnterior.isSelected());
        financeiroPesquisa.pesquisar();

    }

}
