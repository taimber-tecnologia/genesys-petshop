package br.com.salomaotech.genesys.model.venda;

import br.com.salomaotech.genesys.model.financeiro.FinanceiroModelo;
import br.com.salomaotech.genesys.model.produto.ProdutoModelo;
import br.com.salomaotech.sistema.jpa.JPQL;
import br.com.salomaotech.sistema.jpa.Repository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class VendaMovimentaTest {

    private ProdutoModelo produtoModelo = new ProdutoModelo();
    private final List<VendaModeloItem> vendaModeloItemList = new ArrayList();
    private final List<VendaModeloItem> vendaModeloItemBaixaList = new ArrayList();
    private final VendaModelo vendaModelo = new VendaModelo();
    private final VendaMovimenta vendaMovimenta;

    public VendaMovimentaTest() {

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
        new Repository(vendaModelo).save();

        /* atualiza lista */
        vendaModeloItemBaixaList.add(vendaModeloItem);

        /* seta venda */
        vendaMovimenta = new VendaMovimenta(vendaModelo, vendaModeloItemBaixaList);

    }

    @Test
    public void testFinalizar() {

        vendaMovimenta.finalizar();
        produtoModelo = (ProdutoModelo) new Repository(new ProdutoModelo()).findById(produtoModelo.getId());

        /* testa a baixa no estoque */
        System.out.println("Testando classe VendaMovimenta metodo: finalizar checa baixa em estoque");
        assertEquals(true, produtoModelo.getQuantidade().equals(new BigDecimal("49.00")));

        /* testa lançamentos financeiros */
        System.out.println("Testando classe VendaMovimenta metodo: finalizar checa lançamentos em financeiro");
        JPQL jpql = new JPQL(new FinanceiroModelo());
        jpql.addParametroIgual("idVenda", vendaModelo.getId());
        assertEquals(true, new Repository(new FinanceiroModelo()).countTodos(jpql.getCondicaoWhere()) == 1);

    }

    @Test
    public void testReabrir() {

        vendaMovimenta.finalizar();
        vendaMovimenta.reabrir();
        produtoModelo = (ProdutoModelo) new Repository(new ProdutoModelo()).findById(produtoModelo.getId());

        /* testa se excluiu lançamentos financeiros */
        System.out.println("Testando classe VendaMovimenta metodo: reabrir checa lançamentos em financeiro");
        JPQL jpql = new JPQL(new FinanceiroModelo());
        jpql.addParametroIgual("idVenda", vendaModelo.getId());
        assertEquals(true, new Repository(new FinanceiroModelo()).countTodos(jpql.getCondicaoWhere()) == 0);

    }

    @Test
    public void testExcluir() {

        vendaMovimenta.finalizar();
        vendaMovimenta.excluir();
        produtoModelo = (ProdutoModelo) new Repository(new ProdutoModelo()).findById(produtoModelo.getId());

        /* testa se excluiu lançamentos financeiros */
        System.out.println("Testando classe VendaMovimenta metodo: excluir checa lançamentos em financeiro");
        JPQL jpql = new JPQL(new FinanceiroModelo());
        jpql.addParametroIgual("idVenda", vendaModelo.getId());
        assertEquals(true, new Repository(new FinanceiroModelo()).countTodos(jpql.getCondicaoWhere()) == 0);

    }

}
