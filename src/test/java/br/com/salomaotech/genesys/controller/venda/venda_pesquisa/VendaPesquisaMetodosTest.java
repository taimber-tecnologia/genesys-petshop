package br.com.salomaotech.genesys.controller.venda.venda_pesquisa;

import br.com.salomaotech.genesys.model.cliente.ClienteModelo;
import br.com.salomaotech.genesys.model.cliente.ComboBoxClientes;
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
    private final ComboBoxClientes comboBoxClientesPesquisa = new ComboBoxClientes(view.jCpesquisaNomeCliente);

    private final ProdutoModelo produtoModelo = new ProdutoModelo();
    private final List<VendaModeloItem> vendaModeloItemList = new ArrayList();
    private final VendaModelo vendaModelo = new VendaModelo();
    private final ClienteModelo clienteModelo = new ClienteModelo();

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
        vendaModelo.setData(calendar);
        vendaModelo.setVendaModeloItemList(vendaModeloItemList);
        vendaModelo.setIdCliente(clienteModelo.getId());
        vendaModelo.setFormaPagamento("Credito");
        vendaModelo.setNumeroParcelas(3);
        new Repository(vendaModelo).save();

        /* preenche comboboxes */
        comboBoxClientesPesquisa.preencher();

        /* metodos */
        vendaPesquisaMetodos.setComboBoxClientesPesquisa(comboBoxClientesPesquisa);

    }

    @Test
    public void testSetComboBoxClientesPesquisa() {

        boolean isErro = false;

        try {

            vendaPesquisaMetodos.setComboBoxClientesPesquisa(comboBoxClientesPesquisa);

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe VendaPesquisaMetodos metodo: setComboBoxClientesPesquisa");
        assertEquals(false, isErro);

    }

    @Test
    public void testPesquisar() {

        /* usando filtro: nenhum */
        view.jDpesquisaData.setCalendar(null);
        comboBoxClientesPesquisa.selecionarItemPorId(0);
        vendaPesquisaMetodos.pesquisar();
        System.out.println("Testando classe VendaPesquisaMetodos metodo: pesquisar etapa 01");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: data */
        view.jDpesquisaData.setCalendar(calendar);
        comboBoxClientesPesquisa.selecionarItemPorId(0);
        vendaPesquisaMetodos.pesquisar();
        System.out.println("Testando classe VendaPesquisaMetodos metodo: pesquisar etapa 02");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: cliente */
        view.jDpesquisaData.setCalendar(null);
        comboBoxClientesPesquisa.selecionarItemPorId(clienteModelo.getId());
        vendaPesquisaMetodos.pesquisar();
        System.out.println("Testando classe VendaPesquisaMetodos metodo: pesquisar etapa 03");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: todos */
        view.jDpesquisaData.setCalendar(calendar);
        comboBoxClientesPesquisa.selecionarItemPorId(clienteModelo.getId());
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
