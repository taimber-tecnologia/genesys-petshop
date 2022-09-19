/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package br.com.salomaotech.genesys.model.venda;

import java.math.BigDecimal;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author usuario
 */
public class VendaModeloItemTest {
    
    public VendaModeloItemTest() {
    }

    @Test
    public void testGetId() {
        System.out.println("getId");
        VendaModeloItem instance = new VendaModeloItem();
        long expResult = 0L;
        long result = instance.getId();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetId() {
        System.out.println("setId");
        long id = 0L;
        VendaModeloItem instance = new VendaModeloItem();
        instance.setId(id);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetIdProduto() {
        System.out.println("getIdProduto");
        VendaModeloItem instance = new VendaModeloItem();
        long expResult = 0L;
        long result = instance.getIdProduto();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetIdProduto() {
        System.out.println("setIdProduto");
        long idProduto = 0L;
        VendaModeloItem instance = new VendaModeloItem();
        instance.setIdProduto(idProduto);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetValor() {
        System.out.println("getValor");
        VendaModeloItem instance = new VendaModeloItem();
        BigDecimal expResult = null;
        BigDecimal result = instance.getValor();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetValor() {
        System.out.println("setValor");
        BigDecimal valor = null;
        VendaModeloItem instance = new VendaModeloItem();
        instance.setValor(valor);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetQuantidade() {
        System.out.println("getQuantidade");
        VendaModeloItem instance = new VendaModeloItem();
        BigDecimal expResult = null;
        BigDecimal result = instance.getQuantidade();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetQuantidade() {
        System.out.println("setQuantidade");
        BigDecimal quantidade = null;
        VendaModeloItem instance = new VendaModeloItem();
        instance.setQuantidade(quantidade);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetDesconto() {
        System.out.println("getDesconto");
        VendaModeloItem instance = new VendaModeloItem();
        BigDecimal expResult = null;
        BigDecimal result = instance.getDesconto();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetDesconto() {
        System.out.println("setDesconto");
        BigDecimal desconto = null;
        VendaModeloItem instance = new VendaModeloItem();
        instance.setDesconto(desconto);
        fail("The test case is a prototype.");
    }
    
}
