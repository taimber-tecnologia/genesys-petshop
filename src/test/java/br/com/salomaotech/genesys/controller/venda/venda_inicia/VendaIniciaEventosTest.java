/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package br.com.salomaotech.genesys.controller.venda.venda_inicia;

import br.com.salomaotech.genesys.model.produto.ComboBoxProduto;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author usuario
 */
public class VendaIniciaEventosTest {
    
    public VendaIniciaEventosTest() {
    }

    @Test
    public void testSetVendaIniciaMetodos() {
        System.out.println("setVendaIniciaMetodos");
        VendaIniciaMetodos vendaIniciaMetodos = null;
        VendaIniciaEventos instance = null;
        instance.setVendaIniciaMetodos(vendaIniciaMetodos);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetComboBoxProduto() {
        System.out.println("setComboBoxProduto");
        ComboBoxProduto comboBoxProduto = null;
        VendaIniciaEventos instance = null;
        instance.setComboBoxProduto(comboBoxProduto);
        fail("The test case is a prototype.");
    }

    @Test
    public void testAddEventos() {
        System.out.println("addEventos");
        VendaIniciaEventos instance = null;
        instance.addEventos();
        fail("The test case is a prototype.");
    }

    @Test
    public void testExecutar() {
        System.out.println("executar");
        Object arg = null;
        VendaIniciaEventos instance = null;
        instance.executar(arg);
        fail("The test case is a prototype.");
    }
    
}
