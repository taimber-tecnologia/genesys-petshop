package br.com.salomaotech.genesys.model.venda;

import br.com.salomaotech.genesys.model.produto.ProdutoModelo;
import br.com.salomaotech.genesys.view.JFvendaPesquisa;
import br.com.salomaotech.sistema.jpa.Repository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class VendaPesquisaTest {

    private final JFvendaPesquisa view = new JFvendaPesquisa();
    private final Calendar calendar = Calendar.getInstance();
    private final VendaPesquisa vendaPesquisa = new VendaPesquisa(view.jTresultados, view.jCpaginador);

    private final ProdutoModelo produtoModelo = new ProdutoModelo();
    private final List<VendaModeloItem> vendaModeloItemList = new ArrayList();
    private final VendaModelo vendaModelo = new VendaModelo();

    public VendaPesquisaTest() {

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
    public void testSetData() {

        boolean isErro = false;

        try {

            vendaPesquisa.setData(calendar);

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe VendaPesquisa metodo: setData");
        assertEquals(false, isErro);

    }

    @Test
    public void testSetIdPesquisa() {

        boolean isErro = false;

        try {

            vendaPesquisa.setIdPesquisa(0);

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe VendaPesquisa metodo: setIdPesquisa");
        assertEquals(false, isErro);

    }

    @Test
    public void testPesquisar() {

        /* usando filtro: nenhum */
        view.jDpesquisaData.setCalendar(null);
        vendaPesquisa.setIdPesquisa(0L);
        vendaPesquisa.pesquisar();
        System.out.println("Testando classe VendaPesquisa metodo: pesquisar etapa 01 - nenhum filtro");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: data */
        view.jDpesquisaData.setCalendar(calendar);
        vendaPesquisa.setIdPesquisa(0L);
        vendaPesquisa.pesquisar();
        System.out.println("Testando classe VendaPesquisa metodo: pesquisar etapa 02 - apenas data");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: apenas ID */
        view.jDpesquisaData.setCalendar(null);
        vendaPesquisa.setIdPesquisa(vendaModelo.getId());
        vendaPesquisa.pesquisar();
        System.out.println("Testando classe VendaPesquisa metodo: pesquisar etapa 03 - apenas ID");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: ID e data */
        view.jDpesquisaData.setCalendar(calendar);
        vendaPesquisa.setIdPesquisa(vendaModelo.getId());
        vendaPesquisa.pesquisar();
        System.out.println("Testando classe VendaPesquisa metodo: pesquisar etapa 04 - ID + data");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

    }

}
