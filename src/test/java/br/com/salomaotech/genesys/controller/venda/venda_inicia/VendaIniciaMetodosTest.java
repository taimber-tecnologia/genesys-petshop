package br.com.salomaotech.genesys.controller.venda.venda_inicia;

import br.com.salomaotech.genesys.model.produto.ProdutoModelo;
import br.com.salomaotech.genesys.model.venda.ItemVenda;
import br.com.salomaotech.genesys.model.venda.VendaModelo;
import br.com.salomaotech.genesys.model.venda.VendaModeloItem;
import br.com.salomaotech.genesys.view.JFvendaInicia;
import br.com.salomaotech.sistema.algoritmos.ConverteNumeroParaMoedaBr;
import br.com.salomaotech.sistema.jpa.Repository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class VendaIniciaMetodosTest {

    private final JFvendaInicia view = new JFvendaInicia();
    private final VendaIniciaMetodos vendaIniciaMetodos = new VendaIniciaMetodos(view);
    private final ProdutoModelo produtoModelo = new ProdutoModelo();
    private final List<VendaModeloItem> vendaModeloItemList = new ArrayList();
    private final VendaModelo vendaModelo = new VendaModelo();
    private final ItemVenda itemVenda;

    public VendaIniciaMetodosTest() {

        /* remove vendas antigas */
        new Repository(new VendaModelo()).deleteTodos();

        /* simula cadastro de produto */
        new Repository(new ProdutoModelo()).deleteTodos();
        produtoModelo.setNome("Teste");
        produtoModelo.setValorVenda(new BigDecimal(100));
        produtoModelo.setQuantidade(new BigDecimal(50));
        produtoModelo.setDescricao("Teste ABC");
        produtoModelo.setPeso(new BigDecimal(5));
        new Repository(produtoModelo).save();

        /* simula seleção de item de produto */
        VendaModeloItem vendaModeloItem = new VendaModeloItem();
        vendaModeloItem.setIdProduto(produtoModelo.getId());
        vendaModeloItem.setValor(produtoModelo.getValorVenda());
        vendaModeloItem.setDesconto(new BigDecimal(5));
        vendaModeloItem.setQuantidade(new BigDecimal(1));
        vendaModeloItemList.add(vendaModeloItem);

        /* simula cadastro de venda */
        new Repository(new VendaModelo()).deleteTodos();
        vendaModelo.setData(Calendar.getInstance());
        vendaModelo.setVendaModeloItemList(vendaModeloItemList);
        vendaModelo.setFormaPagamento("Credito");
        new Repository(vendaModelo).save();

        /* novo item de venda */
        itemVenda = new ItemVenda(produtoModelo.getId(), produtoModelo);

        /* simula o carregamento de produtos selecionados */
        simularExibirProdutosSelecionados();

    }

    private void simularExibirProdutosSelecionados() {

        DefaultTableModel defaultTableModel = (DefaultTableModel) view.jTitensSelecionados.getModel();
        defaultTableModel.setNumRows(0);
        int contador = 0;

        for (VendaModeloItem vendaModeloItem : vendaModeloItemList) {

            Object[] linhaDefaultTableModel = new Object[]{
                vendaModeloItem.getQuantidade(),
                produtoModelo.getNome(),};

            defaultTableModel.insertRow(contador, linhaDefaultTableModel);
            contador++;

        }

    }

    @Test
    public void testExibirProdutoSelecionado() {

        vendaIniciaMetodos.exibirProdutoSelecionado(itemVenda);

        System.out.println("Testando classe VendaIniciaMetodos metodo: exibirProdutoSelecionado");
        assertEquals(true, view.jTprodutoCodigo.getText().equals(String.valueOf(produtoModelo.getId())));
        assertEquals(true, view.jTprodutoPreco.getText().equals(ConverteNumeroParaMoedaBr.converter(produtoModelo.getValorVenda().toString())));
        assertEquals(true, view.jTitemTotal.getText().equals(ConverteNumeroParaMoedaBr.converter(vendaIniciaMetodos.calcularProdutoSelecionado(itemVenda).toString())));
        assertEquals(true, view.jBprodutoAdicionaItem.isVisible());
        assertEquals(true, view.jBprodutoLimpaItem.isVisible());

    }

    @Test
    public void testLimparCalculosProdutoSelecionado() {

        /* simula a inserção de dados */
        view.jTitemQuantidade.setText("A");
        view.jTprodutoDesconto.setText("B");
        view.jTitemTotal.setText("C");

        /* limpa os calculos do produto selecionado */
        vendaIniciaMetodos.limparCalculosProdutoSelecionado();

        System.out.println("Testando classe VendaIniciaMetodos metodo: limparCalculosProdutoSelecionado");
        assertEquals(true, view.jTitemQuantidade.getText().length() == 0);
        assertEquals(true, view.jTprodutoDesconto.getText().length() == 0);
        assertEquals(true, view.jTitemTotal.getText().length() == 0);

    }

    @Test
    public void testLimparProdutoSelecionado() {

        vendaIniciaMetodos.limparProdutoSelecionado();

        System.out.println("Testando classe VendaIniciaMetodos metodo: limparProdutoSelecionado");
        assertEquals(true, view.jCprodutoLista.getSelectedIndex() == 0);
        assertEquals(true, view.jTitemQuantidade.getText().equals(""));
        assertEquals(true, view.jTprodutoDesconto.getText().equals(""));
        assertEquals(true, view.jTitemTotal.getText().equals(""));

    }

    @Test
    public void testAdicionarProdutoNaLista() {

        vendaIniciaMetodos.adicionarProdutoNaLista(itemVenda);

        System.out.println("Testando classe VendaIniciaMetodos metodo: adicionarProdutoNaLista");
        assertEquals(true, view.jTitensSelecionados.getRowCount() == 1);

    }

    @Test
    public void testRemoverProdutoNaLista() {

        vendaIniciaMetodos.removerProdutoNaLista();

        System.out.println("Testando classe VendaIniciaMetodos metodo: removerProdutoNaLista");
        assertEquals(true, view.jTitensSelecionados.getRowCount() == 0);
        assertEquals(false, view.jBremoveItemSelecionadoLista.isEnabled());

    }

    @Test
    public void testCalcularProdutoSelecionado() {

        view.jTitemQuantidade.setText("2");
        view.jTprodutoDesconto.setText("5");
        BigDecimal valorEsperado = produtoModelo.getValorVenda().multiply(new BigDecimal(view.jTitemQuantidade.getText())).subtract(new BigDecimal(view.jTprodutoDesconto.getText()));

        System.out.println("Testando classe VendaIniciaMetodos metodo: calcularProdutoSelecionado");
        assertEquals(true, vendaIniciaMetodos.calcularProdutoSelecionado(itemVenda).equals(valorEsperado));

    }

    @Test
    public void TestHabilitarCampos() {

        // Teste 1: Quando vendaModelo.getId() == 0 (nova venda)
        vendaIniciaMetodos.abrirCadastro(0);
        vendaIniciaMetodos.habilitarCampos();

        System.out.println("Testando classe VendaIniciaMetodos metodo: habilitarCampos - Nova Venda (ID=0)");
        assertEquals(false, view.jBvendaExcluir.isEnabled());
        assertEquals(false, view.jBimprimir.isEnabled());
        assertEquals(true, view.jBvendaFinaliza.isEnabled());
        assertEquals(true, view.jCprodutoLista.isEnabled());
        assertEquals(true, view.jBprodutoAdicionaItem.isEnabled());
        assertEquals(true, view.jBprodutoLimpaItem.isEnabled());
        assertEquals(true, view.jBremoveItemSelecionadoLista.isEnabled());
        assertEquals(true, view.jTitemQuantidade.isEnabled());

        // Teste 2: Quando vendaModelo.getId() != 0 (venda existente)
        vendaIniciaMetodos.abrirCadastro(vendaModelo.getId());
        vendaIniciaMetodos.habilitarCampos();

        System.out.println("Testando classe VendaIniciaMetodos metodo: habilitarCampos - Venda Existente (ID!=0)");
        assertEquals(true, view.jBvendaExcluir.isEnabled());
        assertEquals(true, view.jBimprimir.isEnabled());
        assertEquals(false, view.jBvendaFinaliza.isEnabled());
        assertEquals(false, view.jCprodutoLista.isEnabled());
        assertEquals(false, view.jBprodutoAdicionaItem.isEnabled());
        assertEquals(false, view.jBprodutoLimpaItem.isEnabled());
        assertEquals(false, view.jBremoveItemSelecionadoLista.isEnabled());
        assertEquals(false, view.jTitemQuantidade.isEnabled());

    }

    @Test
    public void testHabilitarCamposDeAdicionarProduto() {

        /* esperado todos os campos visiveis */
        vendaIniciaMetodos.habilitarCamposDeAdicionarProduto(itemVenda);
        System.out.println("Testando classe VendaIniciaMetodos metodo: habilitarCamposDeAdicionarProduto etapa 01");
        assertEquals(true, view.jBprodutoAdicionaItem.isEnabled());
        assertEquals(true, view.jBprodutoLimpaItem.isEnabled());
        assertEquals(true, view.jBcalcularGranel.isEnabled());

        /* esperados todos os campos não visiveis */
        vendaIniciaMetodos.habilitarCamposDeAdicionarProduto(new ItemVenda());
        System.out.println("Testando classe VendaIniciaMetodos metodo: habilitarCamposDeAdicionarProduto etapa 02");
        assertEquals(false, view.jBprodutoAdicionaItem.isEnabled());
        assertEquals(false, view.jBprodutoLimpaItem.isEnabled());
        assertEquals(false, view.jBcalcularGranel.isEnabled());

    }

    @Test
    public void testHabilitarCamposDeExcluirProdutoAdicionado() {

        /* campo desabilitado */
        vendaIniciaMetodos.habilitarCamposDeExcluirProdutoAdicionado();
        System.out.println("Testando classe VendaIniciaMetodos metodo: habilitarCamposDeExcluirProdutoAdicionado etapa 01");
        assertEquals(false, view.jBremoveItemSelecionadoLista.isEnabled());

        /* campo habilitado */
        view.jTitensSelecionados.setRowSelectionInterval(0, 0);
        vendaIniciaMetodos.habilitarCamposDeExcluirProdutoAdicionado();
        System.out.println("Testando classe VendaIniciaMetodos metodo: habilitarCamposDeExcluirProdutoAdicionado etapa 02");
        assertEquals(true, view.jBremoveItemSelecionadoLista.isEnabled());

    }

    @Test
    public void testFinalizarVenda() {

        boolean isErro = false;

        try {

            vendaIniciaMetodos.finalizarVenda();

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe VendaIniciaMetodos metodo: finalizarVenda");
        assertEquals(false, isErro);

    }

    @Test
    public void testExcluir() {

        System.out.println("Testando classe VendaIniciaMetodos metodo: excluir etapa 01");
        assertEquals(false, vendaIniciaMetodos.excluir());

        vendaIniciaMetodos.abrirCadastro(vendaModelo.getId());
        System.out.println("Testando classe VendaIniciaMetodos metodo: excluir etapa 02");
        assertEquals(true, vendaIniciaMetodos.excluir());

    }

    @Test
    public void testAbrirCadastro() {

        vendaIniciaMetodos.abrirCadastro(vendaModelo.getId());

        System.out.println("Testando classe VendaIniciaMetodos metodo: abrirCadastro");
        assertEquals(true, view.getId() == vendaModelo.getId());
        assertEquals(true, view.jTitensSelecionados.getRowCount() > 0);

    }

}
