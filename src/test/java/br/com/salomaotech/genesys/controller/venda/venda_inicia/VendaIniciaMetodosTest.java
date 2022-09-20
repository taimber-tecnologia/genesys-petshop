package br.com.salomaotech.genesys.controller.venda.venda_inicia;

import br.com.salomaotech.genesys.model.cliente.ClienteModelo;
import br.com.salomaotech.genesys.model.produto.ProdutoModelo;
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
    private final ClienteModelo clienteModelo = new ClienteModelo();

    public VendaIniciaMetodosTest() {

        /* remove vendas antigas */
        new Repository(new VendaModelo()).deleteTodos();

        /* simula cadastro de produto */
        new Repository(new ProdutoModelo()).deleteTodos();
        produtoModelo.setNome("Teste");
        produtoModelo.setValorVenda(new BigDecimal(100));
        produtoModelo.setQuantidade(new BigDecimal(50));
        produtoModelo.setDescricao("Teste ABC");
        new Repository(produtoModelo).save();

        /* simula seleção de item de produto */
        VendaModeloItem vendaModeloItem = new VendaModeloItem();
        vendaModeloItem.setIdProduto(produtoModelo.getId());
        vendaModeloItem.setValor(produtoModelo.getValorVenda());
        vendaModeloItem.setDesconto(new BigDecimal(5));
        vendaModeloItem.setQuantidade(new BigDecimal(1));
        vendaModeloItemList.add(vendaModeloItem);

        /* simula cadastro de cliente */
        new Repository(new ClienteModelo()).deleteTodos();
        clienteModelo.setNome("Teste");
        clienteModelo.setCpf("000.000.000-00");
        new Repository(clienteModelo).save();

        /* simula cadastro de venda */
        new Repository(new VendaModelo()).deleteTodos();
        vendaModelo.setData(Calendar.getInstance());
        vendaModelo.setVendaModeloItemList(vendaModeloItemList);
        vendaModelo.setIdCliente(clienteModelo.getId());
        vendaModelo.setFormaPagamento("Credito");
        vendaModelo.setNumeroParcelas(3);
        new Repository(vendaModelo).save();

        /* simula o carregamento de produtos selecionados */
        simularExibirProdutosSelecionados();

    }

    private void simularExibirProdutosSelecionados() {

        DefaultTableModel defaultTableModel = (DefaultTableModel) view.jTprodutoSelecionado.getModel();
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

        vendaIniciaMetodos.exibirProdutoSelecionado(produtoModelo);

        System.out.println("Testando classe VendaIniciaMetodos metodo: exibirProdutoSelecionado");
        assertEquals(true, view.jTprodutoCodigo.getText().equals(String.valueOf(produtoModelo.getId())));
        assertEquals(true, view.jTprodutoDescricao.getText().equals(produtoModelo.getDescricao()));
        assertEquals(true, view.jTprodutoPreco.getText().equals(ConverteNumeroParaMoedaBr.converter(produtoModelo.getValorVenda().toString())));
        assertEquals(true, view.jTprodutoTotal.getText().equals(ConverteNumeroParaMoedaBr.converter(vendaIniciaMetodos.calcularProdutoSelecionado(produtoModelo).toString())));
        assertEquals(true, view.jBprodutoAdicionaItem.isVisible());
        assertEquals(true, view.jBprodutoLimpaItem.isVisible());

    }

    @Test
    public void testLimparProdutoSelecionado() {

        vendaIniciaMetodos.limparProdutoSelecionado();

        System.out.println("Testando classe VendaIniciaMetodos metodo: limparProdutoSelecionado");
        assertEquals(true, view.jCprodutoLista.getSelectedIndex() == 0);
        assertEquals(true, view.jTprodutoQuantidade.getText().equals(""));
        assertEquals(true, view.jTprodutoDesconto.getText().equals(""));
        assertEquals(true, view.jTprodutoTotal.getText().equals(""));

    }

    @Test
    public void testAdicionarProdutoNaLista() {

        vendaIniciaMetodos.adicionarProdutoNaLista(produtoModelo);

        System.out.println("Testando classe VendaIniciaMetodos metodo: adicionarProdutoNaLista");
        assertEquals(true, view.jTprodutoSelecionado.getRowCount() == 1);

    }

    @Test
    public void testRemoverProdutoNaLista() {

        vendaIniciaMetodos.removerProdutoNaLista();

        System.out.println("Testando classe VendaIniciaMetodos metodo: removerProdutoNaLista");
        assertEquals(true, view.jTprodutoSelecionado.getRowCount() == 0);
        assertEquals(false, view.jBprodutoSelecionadoRemoverItem.isEnabled());

    }

    @Test
    public void testCalcularProdutoSelecionado() {

        view.jTprodutoQuantidade.setText("2");
        view.jTprodutoDesconto.setText("5");
        BigDecimal valorEsperado = produtoModelo.getValorVenda().multiply(new BigDecimal(view.jTprodutoQuantidade.getText())).subtract(new BigDecimal(view.jTprodutoDesconto.getText()));

        System.out.println("Testando classe VendaIniciaMetodos metodo: calcularProdutoSelecionado");
        assertEquals(true, vendaIniciaMetodos.calcularProdutoSelecionado(produtoModelo).equals(valorEsperado));

    }

    @Test
    public void testHabilitarCamposDeAdicionarProduto() {

        /* esperado todos os campos visiveis */
        vendaIniciaMetodos.habilitarCamposDeAdicionarProduto(produtoModelo);
        System.out.println("Testando classe VendaIniciaMetodos metodo: habilitarCamposDeAdicionarProduto etapa 01");
        assertEquals(true, view.jBprodutoAdicionaItem.isVisible());
        assertEquals(true, view.jBprodutoLimpaItem.isVisible());

        /* esperados todos os campos não visiveis */
        vendaIniciaMetodos.habilitarCamposDeAdicionarProduto(new ProdutoModelo());
        System.out.println("Testando classe VendaIniciaMetodos metodo: habilitarCamposDeAdicionarProduto etapa 02");
        assertEquals(false, view.jBprodutoAdicionaItem.isVisible());
        assertEquals(false, view.jBprodutoLimpaItem.isVisible());

    }

    @Test
    public void testHabilitarCamposDeExcluirVenda() {

        /* esperados todos os campos não visiveis */
        vendaIniciaMetodos.habilitarCamposDeExcluirVenda();
        System.out.println("Testando classe VendaIniciaMetodos metodo: habilitarCamposDeExcluirVenda etapa 01");
        assertEquals(false, view.jBvendaExcluir.isVisible());

        /* esperado todos os campos visiveis */
        vendaIniciaMetodos.abrirCadastro(vendaModelo.getId());
        vendaIniciaMetodos.habilitarCamposDeExcluirVenda();
        System.out.println("Testando classe VendaIniciaMetodos metodo: habilitarCamposDeExcluirVenda etapa 02");
        assertEquals(true, view.jBvendaExcluir.isVisible());

    }

    @Test
    public void testHabilitarCamposDeExcluirProdutoAdicionado() {

        /* campo desabilitado */
        vendaIniciaMetodos.habilitarCamposDeExcluirProdutoAdicionado();
        System.out.println("Testando classe VendaIniciaMetodos metodo: habilitarCamposDeExcluirProdutoAdicionado etapa 01");
        assertEquals(false, view.jBprodutoSelecionadoRemoverItem.isEnabled());

        /* campo habilitado */
        view.jTprodutoSelecionado.setRowSelectionInterval(0, 0);
        vendaIniciaMetodos.habilitarCamposDeExcluirProdutoAdicionado();
        System.out.println("Testando classe VendaIniciaMetodos metodo: habilitarCamposDeExcluirProdutoAdicionado etapa 02");
        assertEquals(true, view.jBprodutoSelecionadoRemoverItem.isEnabled());

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
        assertEquals(true, view.jTprodutoSelecionado.getRowCount() > 0);

    }

}
