/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package br.com.salomaotech.genesys.controller.venda.venda_inicia;

import br.com.salomaotech.genesys.model.produto.ProdutoModelo;
import java.math.BigDecimal;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author usuario
 */
public class VendaIniciaMetodosTest {
    
    public VendaIniciaMetodosTest() {
    }

    @Test
    public void testExibirProdutoSelecionado() {
        System.out.println("exibirProdutoSelecionado");
        ProdutoModelo produtoModelo = null;
        VendaIniciaMetodos instance = null;
        instance.exibirProdutoSelecionado(produtoModelo);
        fail("The test case is a prototype.");
    }

    @Test
    public void testLimparProdutoSelecionado() {
        System.out.println("limparProdutoSelecionado");
        VendaIniciaMetodos instance = null;
        instance.limparProdutoSelecionado();
        fail("The test case is a prototype.");
    }

    @Test
    public void testAdicionarProdutoNaLista() {
        System.out.println("adicionarProdutoNaLista");
        ProdutoModelo produtoModelo = null;
        VendaIniciaMetodos instance = null;
        instance.adicionarProdutoNaLista(produtoModelo);
        fail("The test case is a prototype.");
    }

    @Test
    public void testRemoverProdutoNaLista() {
        System.out.println("removerProdutoNaLista");
        VendaIniciaMetodos instance = null;
        instance.removerProdutoNaLista();
        fail("The test case is a prototype.");
    }

    @Test
    public void testCalcularProdutoSelecionado() {
        System.out.println("calcularProdutoSelecionado");
        ProdutoModelo produtoModelo = null;
        VendaIniciaMetodos instance = null;
        BigDecimal expResult = null;
        BigDecimal result = instance.calcularProdutoSelecionado(produtoModelo);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testHabilitarCamposDeAdicionarProduto() {
        System.out.println("habilitarCamposDeAdicionarProduto");
        ProdutoModelo produtoModelo = null;
        VendaIniciaMetodos instance = null;
        instance.habilitarCamposDeAdicionarProduto(produtoModelo);
        fail("The test case is a prototype.");
    }

    @Test
    public void testHabilitarCamposDeExcluirVenda() {
        System.out.println("habilitarCamposDeExcluirVenda");
        VendaIniciaMetodos instance = null;
        instance.habilitarCamposDeExcluirVenda();
        fail("The test case is a prototype.");
    }

    @Test
    public void testHabilitarCamposDeExcluirProdutoAdicionado() {
        System.out.println("habilitarCamposDeExcluirProdutoAdicionado");
        VendaIniciaMetodos instance = null;
        instance.habilitarCamposDeExcluirProdutoAdicionado();
        fail("The test case is a prototype.");
    }

    @Test
    public void testFinalizarVenda() {
        System.out.println("finalizarVenda");
        VendaIniciaMetodos instance = null;
        instance.finalizarVenda();
        fail("The test case is a prototype.");
    }

    @Test
    public void testExcluir() {
        System.out.println("excluir");
        VendaIniciaMetodos instance = null;
        boolean expResult = false;
        boolean result = instance.excluir();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testAbrirCadastro() {
        System.out.println("abrirCadastro");
        long id = 0L;
        VendaIniciaMetodos instance = null;
        instance.abrirCadastro(id);
        fail("The test case is a prototype.");
    }
    
}
