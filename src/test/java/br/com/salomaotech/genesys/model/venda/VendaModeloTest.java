/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package br.com.salomaotech.genesys.model.venda;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author usuario
 */
public class VendaModeloTest {
    
    public VendaModeloTest() {
    }

    @Test
    public void testGetId() {
        System.out.println("getId");
        VendaModelo instance = new VendaModelo();
        long expResult = 0L;
        long result = instance.getId();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetId() {
        System.out.println("setId");
        long id = 0L;
        VendaModelo instance = new VendaModelo();
        instance.setId(id);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetData() {
        System.out.println("getData");
        VendaModelo instance = new VendaModelo();
        Calendar expResult = null;
        Calendar result = instance.getData();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetData() {
        System.out.println("setData");
        Calendar data = null;
        VendaModelo instance = new VendaModelo();
        instance.setData(data);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetVendaModeloItemList() {
        System.out.println("getVendaModeloItemList");
        VendaModelo instance = new VendaModelo();
        List<VendaModeloItem> expResult = null;
        List<VendaModeloItem> result = instance.getVendaModeloItemList();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetVendaModeloItemList() {
        System.out.println("setVendaModeloItemList");
        List<VendaModeloItem> vendaModeloItemList = null;
        VendaModelo instance = new VendaModelo();
        instance.setVendaModeloItemList(vendaModeloItemList);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetFormaPagamento() {
        System.out.println("getFormaPagamento");
        VendaModelo instance = new VendaModelo();
        String expResult = "";
        String result = instance.getFormaPagamento();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetFormaPagamento() {
        System.out.println("setFormaPagamento");
        String formaPagamento = "";
        VendaModelo instance = new VendaModelo();
        instance.setFormaPagamento(formaPagamento);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetNumeroParcelas() {
        System.out.println("getNumeroParcelas");
        VendaModelo instance = new VendaModelo();
        int expResult = 0;
        int result = instance.getNumeroParcelas();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetNumeroParcelas() {
        System.out.println("setNumeroParcelas");
        int numeroParcelas = 0;
        VendaModelo instance = new VendaModelo();
        instance.setNumeroParcelas(numeroParcelas);
        fail("The test case is a prototype.");
    }

    @Test
    public void testIsIsPago() {
        System.out.println("isIsPago");
        VendaModelo instance = new VendaModelo();
        boolean expResult = false;
        boolean result = instance.isIsPago();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetIsPago() {
        System.out.println("setIsPago");
        boolean isPago = false;
        VendaModelo instance = new VendaModelo();
        instance.setIsPago(isPago);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetIdCliente() {
        System.out.println("getIdCliente");
        VendaModelo instance = new VendaModelo();
        long expResult = 0L;
        long result = instance.getIdCliente();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetIdCliente() {
        System.out.println("setIdCliente");
        long idCliente = 0L;
        VendaModelo instance = new VendaModelo();
        instance.setIdCliente(idCliente);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetRevisoes() {
        System.out.println("getRevisoes");
        VendaModelo instance = new VendaModelo();
        int expResult = 0;
        int result = instance.getRevisoes();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetRevisoes() {
        System.out.println("setRevisoes");
        int revisoes = 0;
        VendaModelo instance = new VendaModelo();
        instance.setRevisoes(revisoes);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetValor() {
        System.out.println("getValor");
        VendaModelo instance = new VendaModelo();
        BigDecimal expResult = null;
        BigDecimal result = instance.getValor();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
