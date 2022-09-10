package br.com.salomaotech.genesys.controller.venda;

import br.com.salomaotech.genesys.model.animal.ComboBoxAnimais;
import br.com.salomaotech.genesys.model.centro_custo.ComboBoxCentroCusto;
import br.com.salomaotech.genesys.model.venda.VendaModelo;
import br.com.salomaotech.genesys.model.venda.VendaPesquisa;
import br.com.salomaotech.genesys.model.cliente.ComboBoxClientes;
import br.com.salomaotech.genesys.model.venda.VendaModeloItem;
import br.com.salomaotech.genesys.model.produto.JtableProduto;
import br.com.salomaotech.genesys.model.produto.ProdutoModelo;
import br.com.salomaotech.genesys.view.JFvenda;
import br.com.salomaotech.sistema.algoritmos.BigDecimais;
import br.com.salomaotech.sistema.algoritmos.ConverteNumeroParaMoedaBr;
import br.com.salomaotech.sistema.jpa.Repository;
import br.com.salomaotech.sistema.swing.PopUp;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class VendaMetodos {

    private final JFvenda view;
    private ComboBoxAnimais comboBoxAnimaisCadastro;
    private ComboBoxClientes comboBoxClientesCadastro;
    private ComboBoxClientes comboBoxClientesPesquisa;
    private ComboBoxCentroCusto comboBoxCentroCusto;
    private JtableProduto jtableProduto;
    private List<VendaModeloItem> vendaModeloItemList = new ArrayList();
    private boolean isAprovarVenda = false;

    public VendaMetodos(JFvenda view) {
        this.view = view;
    }

    public void setComboBoxAnimaisCadastro(ComboBoxAnimais comboBoxAnimaisCadastro) {
        this.comboBoxAnimaisCadastro = comboBoxAnimaisCadastro;
    }

    public void setComboBoxClientesCadastro(ComboBoxClientes comboBoxClientesCadastro) {
        this.comboBoxClientesCadastro = comboBoxClientesCadastro;
    }

    public void setComboBoxClientesPesquisa(ComboBoxClientes comboBoxClientesPesquisa) {
        this.comboBoxClientesPesquisa = comboBoxClientesPesquisa;
    }

    public void setComboBoxCentroCusto(ComboBoxCentroCusto comboBoxCentroCusto) {
        this.comboBoxCentroCusto = comboBoxCentroCusto;
    }

    public void setJtableProduto(JtableProduto jtableProduto) {
        this.jtableProduto = jtableProduto;
    }

    public void setIsAprovarVenda(boolean isAprovarVenda) {
        this.isAprovarVenda = isAprovarVenda;
    }

    public void popularFormulario(VendaModelo vendaModelo) {

        view.setId(vendaModelo.getId());
        view.jDcadastroData.setCalendar(vendaModelo.getData());
        comboBoxClientesCadastro.selecionarItemPorId(vendaModelo.getIdCliente());
        comboBoxAnimaisCadastro.selecionarItemComBox(vendaModelo.getIdAnimal());
        view.jTcadastroValorDesconto.setText(vendaModelo.getDesconto().toString());
        view.jCcadastroFormaPagamento.setSelectedItem(vendaModelo.getFormaPagamento());
        view.jTcadastroPorcentagemJuros.setText(vendaModelo.getJuros().toString());
        view.jCnumeroDeParcelas.setSelectedItem(String.valueOf(vendaModelo.getNumeroParcelas()));
        view.jCcadastroPago.setSelected(vendaModelo.isIsPago());
        view.jTcadastroValorEntrada.setText(vendaModelo.getEntrada().toString());
        comboBoxCentroCusto.selecionarItemPorId(vendaModelo.getIdCentroCusto());

        /* limpa a lista antiga de itens de produto adicionados */
        vendaModeloItemList = new ArrayList();
        vendaModelo.getVendaModeloItemList().forEach(vendaModeloItemTemp -> {

            VendaModeloItem vendaModeloItem = new VendaModeloItem();
            vendaModeloItem.setDescricao(vendaModeloItemTemp.getDescricao());
            vendaModeloItem.setIdProduto(vendaModeloItemTemp.getIdProduto());
            vendaModeloItem.setQuantidade(vendaModeloItemTemp.getQuantidade());
            vendaModeloItem.setValor(vendaModeloItemTemp.getValor());
            vendaModeloItem.setMedida(vendaModeloItemTemp.getMedida());
            vendaModeloItemList.add(vendaModeloItem);

        });

        /* agora exibe os itens de venda selecionados e recalcula o valor total */
        exibirItensDeVendaSelecionadosView(vendaModeloItemList);
        recalcularValorTotal();

    }

    public void resetarView() {

        popularFormulario(new VendaModelo());
        jtableProduto.preencher();
        view.jCcadastroFormaPagamento.setSelectedIndex(0);
        view.jCnumeroDeParcelas.setSelectedIndex(0);
        view.jTabbedPane1.setSelectedIndex(0);
        view.jTcadastroQuantidadeProduto.setText("0");
        view.jCnumeroDeParcelas.setEnabled(true);
        vendaModeloItemList = new ArrayList();
        exibirItensDeVendaSelecionadosView(vendaModeloItemList);
        recalcularValorTotal();
        habilitarCamposSelecaoProdutos();

    }

    public void habilitarCampos() {

        VendaModelo vendaModelo = (VendaModelo) new Repository(new VendaModelo()).findById(view.getId());
        view.jBcadastroExcluir.setEnabled(vendaModelo.getId() != 0);
        view.jBimprimir.setEnabled(vendaModelo.getId() != 0);
        view.jBcadastroSalvar.setEnabled(!vendaModelo.isFinalizado());

        if (vendaModelo.getId() != 0) {

            view.jBfinalizarVenda.setEnabled(!vendaModelo.isFinalizado());
            view.jBreabrirVenda.setEnabled(vendaModelo.isFinalizado());

        } else {

            view.jBfinalizarVenda.setEnabled(false);
            view.jBreabrirVenda.setEnabled(false);

        }

    }

    public void habilitarCamposSelecaoProdutos() {

        boolean isHabilitado = view.jTvendaProdutosDisponiveis.getSelectedRowCount() > 0;
        view.jBcadastroSelecionarProduto.setEnabled(isHabilitado);
        view.jTcadastroQuantidadeProduto.setEnabled(isHabilitado);

    }

    public void habilitarCamposSelecaoProdutosSelecionados() {

        boolean isHabilitado = view.jTvendaModeloItemSelecionados.getSelectedRowCount() > 0;
        view.jBcadastroRemoverItem.setEnabled(isHabilitado);

    }

    public void addPopUpMenu() {

        PopUp popUp = new PopUp();
        popUp.adicionarMenu(view.jTcadastroQuantidadeProduto);
        popUp.adicionarMenu(view.jTcadastroPorcentagemJuros);
        popUp.adicionarMenu(view.jTcadastroValorEntrada);
        popUp.adicionarMenu(view.jTcadastroValorDesconto);

    }

    public void abrirCadastro(long id) {

        VendaModelo vendaModelo = (VendaModelo) new Repository(new VendaModelo()).findById(id);
        popularFormulario(vendaModelo);
        habilitarCampos();
        view.jTabaPrincipal.setSelectedIndex(0);
        view.jTabbedPane1.setSelectedIndex(0);

    }

    public VendaModelo salvar() {

        VendaModelo vendaModelo = new VendaModelo();
        vendaModelo.setId(view.getId());
        vendaModelo.setData(view.jDcadastroData.getCalendar());
        vendaModelo.setIdCliente(comboBoxClientesCadastro.getIdSelecionado());
        vendaModelo.setIdAnimal(comboBoxAnimaisCadastro.getIdAnimalSelecionado());
        vendaModelo.setVendaModeloItemList(vendaModeloItemList);
        vendaModelo.setEntrada(BigDecimais.formatarParaBigDecimal(view.jTcadastroValorEntrada.getText()));
        vendaModelo.setDesconto(BigDecimais.formatarParaBigDecimal(view.jTcadastroValorDesconto.getText()));
        vendaModelo.setJuros(BigDecimais.formatarParaBigDecimal(view.jTcadastroPorcentagemJuros.getText()));
        vendaModelo.setFormaPagamento(view.jCcadastroFormaPagamento.getSelectedItem().toString());
        vendaModelo.setFinalizado(isAprovarVenda);
        vendaModelo.setNumeroParcelas(Integer.valueOf(view.jCnumeroDeParcelas.getSelectedItem().toString()));
        vendaModelo.setIsPago(view.jCcadastroPago.isSelected());
        vendaModelo.setIdCentroCusto(comboBoxCentroCusto.getIdSelecionado());
        new Repository(vendaModelo).save();
        return vendaModelo;

    }

    public boolean excluir() {

        return new Repository(new VendaModelo()).delete(view.getId());

    }

    public void pesquisar() {

        VendaPesquisa vendaPesquisa = new VendaPesquisa(view.jTresultados, view.jCpaginador);
        vendaPesquisa.setData(view.jDpesquisaData.getCalendar());
        vendaPesquisa.setIdCliente(comboBoxClientesPesquisa.getIdSelecionado());
        vendaPesquisa.pesquisar();

    }

    private void exibirItensDeVendaSelecionadosView(List<VendaModeloItem> vendaModeloItemSelecionadosList) {

        DefaultTableModel defaultTableModel = (DefaultTableModel) view.jTvendaModeloItemSelecionados.getModel();
        defaultTableModel.setNumRows(0);
        int contador = 0;

        for (VendaModeloItem vendaModeloItem : vendaModeloItemSelecionadosList) {

            Object[] linhaDefaultTableModel = new Object[]{
                vendaModeloItem.getDescricao(),
                vendaModeloItem.getMedida(),
                vendaModeloItem.getQuantidade(),
                ConverteNumeroParaMoedaBr.converter(vendaModeloItem.getValor().toString()),
                ConverteNumeroParaMoedaBr.converter(vendaModeloItem.getQuantidade().multiply(vendaModeloItem.getValor()).toString())

            };

            defaultTableModel.insertRow(contador, linhaDefaultTableModel);
            contador++;

        }

    }

    public void recalcularValorTotal() {

        VendaModelo vendaModelo = new VendaModelo();
        vendaModelo.setVendaModeloItemList(vendaModeloItemList);
        vendaModelo.setEntrada(BigDecimais.formatarParaBigDecimal(view.jTcadastroValorEntrada.getText()));
        vendaModelo.setDesconto(BigDecimais.formatarParaBigDecimal(view.jTcadastroValorDesconto.getText()));
        vendaModelo.setJuros(BigDecimais.formatarParaBigDecimal(view.jTcadastroPorcentagemJuros.getText()));
        view.jTcadastroValor.setText(ConverteNumeroParaMoedaBr.converter(vendaModelo.getValorTotal().toString()));

    }

    public void adicionarItemDeProduto() {

        /* carrega os dados do produto */
        long id = (long) view.jTvendaProdutosDisponiveis.getModel().getValueAt(view.jTvendaProdutosDisponiveis.getSelectedRow(), 0);
        ProdutoModelo produtoModelo = (ProdutoModelo) new Repository(new ProdutoModelo()).findById(id);

        /* popula um item de venda */
        VendaModeloItem vendaModeloItem = new VendaModeloItem();
        vendaModeloItem.setDescricao(produtoModelo.getNome());
        vendaModeloItem.setValor(produtoModelo.getValorVenda());
        vendaModeloItem.setIdProduto(produtoModelo.getId());
        vendaModeloItem.setQuantidade(BigDecimais.formatarParaBigDecimal(view.jTcadastroQuantidadeProduto.getText()));
        vendaModeloItem.setMedida(produtoModelo.getMedida());

        /* popula o item de venda na lista */
        vendaModeloItemList.add(vendaModeloItem);

        /* agora exibe os itens de venda selecionados */
        exibirItensDeVendaSelecionadosView(vendaModeloItemList);

        /* recalcula o valor total */
        recalcularValorTotal();

        /* limpa a quantidade de itens selecionados */
        view.jTabbedPane1.setSelectedIndex(0);
        view.jTcadastroQuantidadeProduto.setText("0");

    }

    public void removerItemDeProduto() {

        /* remove o elemento por seu index da lista exibe os itens de venda selecionados restantes */
        vendaModeloItemList.remove(view.jTvendaModeloItemSelecionados.getSelectedRow());
        exibirItensDeVendaSelecionadosView(vendaModeloItemList);

        /* recalcula o valor total */
        recalcularValorTotal();

    }

}
