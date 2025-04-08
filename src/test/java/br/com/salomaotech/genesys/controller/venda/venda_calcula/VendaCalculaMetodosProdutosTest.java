package br.com.salomaotech.genesys.controller.venda.venda_calcula;

import br.com.salomaotech.genesys.controller.venda.venda_inicia.produto.VendaIniciaMetodosProdutos;
import br.com.salomaotech.genesys.model.produto.ProdutoModelo;
import br.com.salomaotech.genesys.model.venda.ItemVenda;
import br.com.salomaotech.genesys.model.venda.VendaModelo;
import br.com.salomaotech.genesys.view.JFvendaCalcula;
import br.com.salomaotech.genesys.view.JFvendaInicia;
import br.com.salomaotech.sistema.algoritmos.BigDecimais;
import br.com.salomaotech.sistema.jpa.Repository;
import java.math.BigDecimal;
import org.junit.Test;
import static org.junit.Assert.*;

public class VendaCalculaMetodosProdutosTest {

    private final JFvendaCalcula view = new JFvendaCalcula();
    private final JFvendaInicia jFvendaInicia = new JFvendaInicia();
    private final ProdutoModelo produtoModelo = new ProdutoModelo();
    private final ItemVenda itemVenda;

    private final VendaCalculaMetodosProdutos vendaCalculaMetodos = new VendaCalculaMetodosProdutos(view);
    private final VendaIniciaMetodosProdutos vendaIniciaMetodosProdutos = new VendaIniciaMetodosProdutos(jFvendaInicia);

    public VendaCalculaMetodosProdutosTest() {

        /* remove vendas antigas */
        new Repository(new VendaModelo()).deleteTodos();

        /* simula cadastro de produto */
        new Repository(new ProdutoModelo()).deleteTodos();
        produtoModelo.setNome("Teste");
        produtoModelo.setDescricao("Teste ABC");
        produtoModelo.setValorVenda(new BigDecimal(140));
        produtoModelo.setQuantidade(new BigDecimal(50));
        produtoModelo.setPeso(new BigDecimal(15));
        new Repository(produtoModelo).save();

        /* novo item de venda */
        itemVenda = new ItemVenda(produtoModelo.getId(), produtoModelo);

    }

    @Test
    public void testSetItemVenda() {

        boolean isErro = false;

        try {

            vendaCalculaMetodos.setItemVenda(itemVenda);
            vendaCalculaMetodos.setItemVenda(null);

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe VendaCalculaMetodosProdutos metodo: setItemVenda");
        assertEquals(false, isErro);

    }

    @Test
    public void testSetVendaIniciaMetodosProdutos() {

        boolean isErro = false;

        try {

            vendaCalculaMetodos.setVendaIniciaMetodosProdutos(vendaIniciaMetodosProdutos);
            vendaCalculaMetodos.setVendaIniciaMetodosProdutos(null);

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe VendaCalculaMetodosProdutos metodo: setVendaIniciaMetodosProdutos");
        assertEquals(false, isErro);

    }

    @Test
    public void testCalcularPorValor() {

        /* Caso válido */
        BigDecimal valorDesejado = new BigDecimal(150);
        BigDecimal resultadoEsperado = BigDecimais.dividir(valorDesejado, itemVenda.getValor());

        vendaCalculaMetodos.setItemVenda(itemVenda);
        vendaCalculaMetodos.setVendaIniciaMetodosProdutos(vendaIniciaMetodosProdutos);
        vendaCalculaMetodos.calcularPorValor(valorDesejado);

        System.out.println("Testando classe VendaCalculaMetodosProdutos metodo: calcularPorValor");
        assertEquals(resultadoEsperado.toString(), view.jTresultado.getText());
        assertEquals(resultadoEsperado.toString(), jFvendaInicia.jTitemQuantidade.getText());
        assertEquals(0, view.jTpesoKg.getText().length());

        /* Caso com valor zero */
        vendaCalculaMetodos.calcularPorValor(BigDecimal.ZERO);
        assertEquals("0.00", view.jTresultado.getText());

        /* Caso com valor nulo */
        vendaCalculaMetodos.calcularPorValor(null);
        assertEquals("0", view.jTresultado.getText());

    }

    @Test
    public void testCalcularPorPeso() {

        /* Caso válido */
        BigDecimal pesoDesejado = new BigDecimal(35);
        BigDecimal resultadoEsperado = BigDecimais.dividir(pesoDesejado, itemVenda.getPeso());

        vendaCalculaMetodos.setItemVenda(itemVenda);
        vendaCalculaMetodos.setVendaIniciaMetodosProdutos(vendaIniciaMetodosProdutos);
        vendaCalculaMetodos.calcularPorPeso(pesoDesejado);

        System.out.println("Testando classe VendaCalculaMetodosProdutos metodo: calcularPorPeso");
        assertEquals(resultadoEsperado.toString(), view.jTresultado.getText());
        assertEquals(resultadoEsperado.toString(), jFvendaInicia.jTitemQuantidade.getText());
        assertEquals(0, view.jTvalorDesejado.getText().length());

        /* Caso com peso zero */
        vendaCalculaMetodos.calcularPorPeso(BigDecimal.ZERO);
        assertEquals("0.00", view.jTresultado.getText());

        /* Caso com peso nulo */
        vendaCalculaMetodos.calcularPorPeso(null);
        assertEquals("0", view.jTresultado.getText());

    }

}
