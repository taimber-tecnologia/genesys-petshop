package br.com.salomaotech.genesys.controller.venda.venda_conclui;

import br.com.salomaotech.genesys.model.financeiro.FinanceiroModelo;
import br.com.salomaotech.genesys.model.produto.ProdutoModelo;
import br.com.salomaotech.genesys.model.venda.VendaModelo;
import br.com.salomaotech.genesys.model.venda.VendaModeloItem;
import br.com.salomaotech.genesys.view.JFvendaConclui;
import br.com.salomaotech.genesys.view.JFvendaInicia;
import br.com.salomaotech.sistema.algoritmos.ConverteNumeroParaMoedaBr;
import br.com.salomaotech.sistema.jpa.JPQL;
import br.com.salomaotech.sistema.jpa.Repository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class VendaConcluiMetodosTest {

    private final JFvendaConclui view = new JFvendaConclui();
    private final JFvendaInicia viewVenda = new JFvendaInicia();

    private ProdutoModelo produtoModelo = new ProdutoModelo();
    private final VendaModelo vendaModelo = new VendaModelo();
    private final List<VendaModeloItem> vendaModeloItemList = new ArrayList();
    private final List<VendaModeloItem> vendaModeloItemBaixaList = new ArrayList();
    private final List<VendaModeloItem> vendaModeloItemDevolveList = new ArrayList();
    private final VendaConcluiMetodos vendaConcluiMetodos;

    public VendaConcluiMetodosTest() {

        /* remove vendas antigas */
        new Repository(new VendaModelo()).deleteTodos();

        /* simula cadastro de produto */
        new Repository(new ProdutoModelo()).deleteTodos();
        produtoModelo.setNome("Teste");
        produtoModelo.setValorVenda(new BigDecimal(100));
        produtoModelo.setQuantidade(new BigDecimal(50));
        new Repository(produtoModelo).save();

        /* simula seleção de item de produto */
        VendaModeloItem vendaModeloItem = new VendaModeloItem();
        vendaModeloItem.setIdProduto(produtoModelo.getId());
        vendaModeloItem.setValor(produtoModelo.getValorVenda());
        vendaModeloItem.setDesconto(new BigDecimal(5));
        vendaModeloItem.setQuantidade(new BigDecimal(1));
        vendaModeloItemList.add(vendaModeloItem);

        /* adiciona os dados da venda */
        new Repository(new VendaModelo()).deleteTodos();
        vendaModelo.setData(Calendar.getInstance());
        vendaModelo.setVendaModeloItemList(vendaModeloItemList);
        vendaModelo.setFormaPagamento("Credito");
        vendaModelo.setNumeroParcelas(3);

        /* atualiza listas */
        vendaModeloItemBaixaList.add(vendaModeloItem);
        vendaModeloItemDevolveList.add(vendaModeloItem);

        /* metodos */
        vendaConcluiMetodos = new VendaConcluiMetodos(view, vendaModelo, viewVenda, vendaModeloItemBaixaList, vendaModeloItemDevolveList);

    }

    @Test
    public void testGetVendaModelo() {

        System.out.println("Testando classe VendaConcluiMetodos metodo: getVendaModelo");
        assertEquals(true, vendaConcluiMetodos.getVendaModelo().equals(vendaModelo));

    }

    @Test
    public void testExibirVenda() {

        vendaConcluiMetodos.exibirVenda();

        System.out.println("Testando classe VendaConcluiMetodos metodo: exibirVenda");
        assertEquals(true, view.jDdata.getCalendar().equals(vendaModelo.getData()));
        assertEquals(true, view.jTvalorTotal.getText().equals(ConverteNumeroParaMoedaBr.converter(vendaModelo.getValor().toString())));
        assertEquals(true, view.jTvalorRecebido.getText().equals(ConverteNumeroParaMoedaBr.converter(vendaModelo.getValor().toString())));
        assertEquals(true, view.jTvalorTroco.getText().equals(ConverteNumeroParaMoedaBr.converter("0")));
        assertEquals(true, view.jCforma.getSelectedItem().equals(vendaModelo.getFormaPagamento()));
        assertEquals(true, view.jCparcela.getSelectedItem().equals(String.valueOf(vendaModelo.getNumeroParcelas())));

    }

    @Test
    public void testCalcularTroco() {

        BigDecimal valorRecebido = new BigDecimal(205);
        BigDecimal valorTroco = valorRecebido.subtract(vendaModelo.getValor());
        view.jTvalorRecebido.setText(valorRecebido.toString());

        System.out.println("Testando classe VendaConcluiMetodos metodo: calcularTroco");
        assertEquals(true, vendaConcluiMetodos.calcularTroco().equals(valorTroco));

    }

    @Test
    public void testHabilitarCampos() {

        view.jCforma.setSelectedItem("Dinheiro");
        vendaConcluiMetodos.habilitarCampos();
        System.out.println("Testando classe VendaConcluiMetodos metodo: habilitarCampos etapa 01");
        assertEquals(true, view.jTvalorRecebido.isEditable());
        assertEquals(true, view.jTvalorRecebido.getText().equals(""));
        assertEquals(true, view.jTvalorTroco.getText().equals(ConverteNumeroParaMoedaBr.converter("0")));
        assertEquals(false, view.jLparcela.isVisible());
        assertEquals(false, view.jCparcela.isVisible());
        assertEquals(true, view.jCforma.getSelectedIndex() == 2);

        view.jCforma.setSelectedItem("Debito");
        vendaConcluiMetodos.habilitarCampos();
        System.out.println("Testando classe VendaConcluiMetodos metodo: habilitarCampos etapa 02");
        assertEquals(false, view.jTvalorRecebido.isEditable());
        assertEquals(true, view.jTvalorRecebido.getText().equals(ConverteNumeroParaMoedaBr.converter(vendaModelo.getValor().toString())));
        assertEquals(true, view.jTvalorTroco.getText().equals(ConverteNumeroParaMoedaBr.converter("0")));
        assertEquals(false, view.jLparcela.isVisible());
        assertEquals(false, view.jCparcela.isVisible());
        assertEquals(true, view.jCforma.getSelectedIndex() == 0);

        view.jCforma.setSelectedItem("Pix");
        vendaConcluiMetodos.habilitarCampos();
        System.out.println("Testando classe VendaConcluiMetodos metodo: habilitarCampos etapa 03");
        assertEquals(false, view.jTvalorRecebido.isEditable());
        assertEquals(true, view.jTvalorRecebido.getText().equals(ConverteNumeroParaMoedaBr.converter(vendaModelo.getValor().toString())));
        assertEquals(true, view.jTvalorTroco.getText().equals(ConverteNumeroParaMoedaBr.converter("0")));
        assertEquals(false, view.jLparcela.isVisible());
        assertEquals(false, view.jCparcela.isVisible());
        assertEquals(true, view.jCforma.getSelectedIndex() == 3);

        view.jCforma.setSelectedItem("Credito");
        vendaConcluiMetodos.habilitarCampos();
        System.out.println("Testando classe VendaConcluiMetodos metodo: habilitarCampos etapa 04");
        assertEquals(false, view.jTvalorRecebido.isEditable());
        assertEquals(true, view.jTvalorRecebido.getText().equals(ConverteNumeroParaMoedaBr.converter(vendaModelo.getValor().toString())));
        assertEquals(true, view.jTvalorTroco.getText().equals(ConverteNumeroParaMoedaBr.converter("0")));
        assertEquals(true, view.jLparcela.isVisible());
        assertEquals(true, view.jCparcela.isVisible());
        assertEquals(true, view.jCforma.getSelectedIndex() == 1);

    }

    @Test
    public void testExecutarAposFinalizarVenda() {

        vendaConcluiMetodos.executarAposFinalizarVenda();

        System.out.println("Testando classe VendaConcluiMetodos metodo: executarAposFinalizarVenda");
        assertEquals(false, viewVenda.isVisible());
        assertEquals(false, view.jBconcluir.isEnabled());
        assertEquals(false, view.jBcancelar.isEnabled());
        assertEquals(true, view.jBimprimir.isEnabled());
        assertEquals(false, view.jCforma.isEnabled());
        assertEquals(false, view.jTvalorRecebido.isEnabled());
        assertEquals(false, view.jCparcela.isEnabled());
        assertEquals(true, view.jTvalorRecebido.getText().equals(ConverteNumeroParaMoedaBr.converter(view.jTvalorRecebido.getText())));

    }

    @Test
    public void testFinalizarVenda() {

        view.jCforma.setSelectedItem("Credito");
        vendaConcluiMetodos.finalizarVenda();
        produtoModelo = (ProdutoModelo) new Repository(new ProdutoModelo()).findById(produtoModelo.getId());

        /* testa o número de revisões */
        System.out.println("Testando classe VendaConcluiMetodos metodo: finalizarVenda checa número de revisões");
        assertEquals(true, vendaModelo.getRevisoes() == 1);

        /* testa a baixa no estoque */
        System.out.println("Testando classe VendaConcluiMetodos metodo: finalizarVenda checa baixa em estoque");
        assertEquals(true, produtoModelo.getQuantidade().equals(new BigDecimal("49.00")));

        /* testa lançamentos financeiros */
        System.out.println("Testando classe VendaConcluiMetodos metodo: finalizarVenda checa lançamentos em financeiro");
        JPQL jpql = new JPQL(new FinanceiroModelo());
        jpql.addParametroIgual("idVenda", vendaModelo.getId());
        assertEquals(true, new Repository(new FinanceiroModelo()).countTodos(jpql.getCondicaoWhere()) == vendaModelo.getNumeroParcelas());

    }

}
