package br.com.salomaotech.genesys.controller.venda.venda_calcula;

import br.com.salomaotech.genesys.controller.venda.venda_inicia.VendaIniciaMetodos;
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

public class VendaCalculaMetodosTest {

    private final JFvendaCalcula view = new JFvendaCalcula();
    private final JFvendaInicia jFvendaInicia = new JFvendaInicia();
    private final ProdutoModelo produtoModelo = new ProdutoModelo();
    private final ItemVenda itemVenda;

    private final VendaCalculaMetodos vendaCalculaMetodos = new VendaCalculaMetodos(view);
    private final VendaIniciaMetodos vendaIniciaMetodos = new VendaIniciaMetodos(jFvendaInicia);

    public VendaCalculaMetodosTest() {

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

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe VendaCalculaMetodos metodo: setItemVenda");
        assertEquals(false, isErro);

    }

    @Test
    public void testSetVendaIniciaMetodos() {

        boolean isErro = false;

        try {

            vendaCalculaMetodos.setVendaIniciaMetodos(vendaIniciaMetodos);

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe VendaCalculaMetodos metodo: setVendaIniciaMetodos");
        assertEquals(false, isErro);

    }

    @Test
    public void testCalcular() {

        /* relacionado a valor */
        BigDecimal valorDesejado = new BigDecimal(150);
        BigDecimal resultadoEsperadoValor = BigDecimais.dividir(valorDesejado, itemVenda.getValor());

        /* relacionado a peso */
        BigDecimal pesoDesejado = new BigDecimal(35);
        BigDecimal resultadoEsperadoPeso = BigDecimais.dividir(pesoDesejado, itemVenda.getPeso());

        /* métodos */
        vendaCalculaMetodos.setItemVenda(itemVenda);
        vendaCalculaMetodos.setVendaIniciaMetodos(vendaIniciaMetodos);

        /* calcula por valor */
        vendaCalculaMetodos.calcularPorValor(valorDesejado);
        System.out.println("Testando classe VendaCalculaMetodos metodo: calcular");
        assertEquals(true, view.jTresultado.getText().equals(resultadoEsperadoValor.toString()));
        assertEquals(true, jFvendaInicia.jTprodutoQuantidade.getText().equals(resultadoEsperadoValor.toString()));

        /* calcula por peso */
        vendaCalculaMetodos.calcularPorPeso(pesoDesejado);
        assertEquals(true, view.jTresultado.getText().equals(resultadoEsperadoPeso.toString()));
        assertEquals(true, jFvendaInicia.jTprodutoQuantidade.getText().equals(resultadoEsperadoPeso.toString()));

    }

}
