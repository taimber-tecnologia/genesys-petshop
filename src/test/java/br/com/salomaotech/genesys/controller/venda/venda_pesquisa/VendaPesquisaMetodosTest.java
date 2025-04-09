package br.com.salomaotech.genesys.controller.venda.venda_pesquisa;

import br.com.salomaotech.genesys.model.produto.ProdutoModelo;
import br.com.salomaotech.genesys.model.venda.VendaModelo;
import br.com.salomaotech.genesys.model.venda.VendaModeloItem;
import br.com.salomaotech.genesys.view.JFvendaPesquisa;
import br.com.salomaotech.sistema.jpa.Repository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class VendaPesquisaMetodosTest {

    private final JFvendaPesquisa view = new JFvendaPesquisa();
    private final Calendar calendar = Calendar.getInstance();
    private final VendaPesquisaMetodos vendaPesquisaMetodos = new VendaPesquisaMetodos(view);

    private final ProdutoModelo produtoModelo = new ProdutoModelo();
    private final List<VendaModeloItem> vendaModeloItemList = new ArrayList();
    private final VendaModelo vendaModelo = new VendaModelo();

    public VendaPesquisaMetodosTest() {

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
        vendaModeloItem.setQuantidade(new BigDecimal(1));
        vendaModeloItemList.add(vendaModeloItem);

        /* simula cadastro de venda */
        new Repository(new VendaModelo()).deleteTodos();
        vendaModelo.setData(calendar);
        vendaModelo.setVendaModeloItemList(vendaModeloItemList);
        vendaModelo.setFormaPagamento("Credito");
        new Repository(vendaModelo).save();

    }

    @Test
    public void testPesquisar() {

        /* usando filtro: nenhum */
        view.jDpesquisaData.setCalendar(null);
        vendaPesquisaMetodos.pesquisar();
        System.out.println("Testando classe VendaPesquisaMetodos metodo: pesquisar etapa 01");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: data */
        view.jDpesquisaData.setCalendar(calendar);
        vendaPesquisaMetodos.pesquisar();
        System.out.println("Testando classe VendaPesquisaMetodos metodo: pesquisar etapa 02");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: cliente */
        view.jDpesquisaData.setCalendar(null);
        vendaPesquisaMetodos.pesquisar();
        System.out.println("Testando classe VendaPesquisaMetodos metodo: pesquisar etapa 03");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: todos */
        view.jDpesquisaData.setCalendar(calendar);
        vendaPesquisaMetodos.pesquisar();
        System.out.println("Testando classe VendaPesquisaMetodos metodo: pesquisar etapa 04");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

    }

    @Test
    public void testAbrirCadastro() {

        boolean isErro = false;

        try {

            vendaPesquisaMetodos.abrirCadastro(vendaModelo.getId());

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe VendaPesquisaMetodos metodo: abrirCadastro");
        assertEquals(false, isErro);

    }

}
