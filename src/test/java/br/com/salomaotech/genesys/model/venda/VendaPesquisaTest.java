/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package br.com.salomaotech.genesys.model.venda;

import java.util.Calendar;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author usuario
 */
public class VendaPesquisaTest {
    
    public VendaPesquisaTest() {
    }

    @Test
    public void testSetData() {
        System.out.println("setData");
        Calendar data = null;
        VendaPesquisa instance = null;
        instance.setData(data);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetIdCliente() {
        System.out.println("setIdCliente");
        long idCliente = 0L;
        VendaPesquisa instance = null;
        instance.setIdCliente(idCliente);
        fail("The test case is a prototype.");
    }

    @Test
    public void testPesquisar() {
        System.out.println("pesquisar");
        VendaPesquisa instance = null;
        instance.pesquisar();
        fail("The test case is a prototype.");
    }
    
}
