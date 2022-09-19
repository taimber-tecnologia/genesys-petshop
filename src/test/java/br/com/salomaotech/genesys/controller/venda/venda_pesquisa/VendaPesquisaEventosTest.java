/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package br.com.salomaotech.genesys.controller.venda.venda_pesquisa;

import br.com.salomaotech.genesys.model.cliente.ComboBoxClientes;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author usuario
 */
public class VendaPesquisaEventosTest {
    
    public VendaPesquisaEventosTest() {
    }

    @Test
    public void testSetVendaPesquisaMetodos() {
        System.out.println("setVendaPesquisaMetodos");
        VendaPesquisaMetodos vendaPesquisaMetodos = null;
        VendaPesquisaEventos instance = null;
        instance.setVendaPesquisaMetodos(vendaPesquisaMetodos);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetComboBoxClientesPesquisa() {
        System.out.println("setComboBoxClientesPesquisa");
        ComboBoxClientes comboBoxClientesPesquisa = null;
        VendaPesquisaEventos instance = null;
        instance.setComboBoxClientesPesquisa(comboBoxClientesPesquisa);
        fail("The test case is a prototype.");
    }

    @Test
    public void testAddEventos() {
        System.out.println("addEventos");
        VendaPesquisaEventos instance = null;
        instance.addEventos();
        fail("The test case is a prototype.");
    }
    
}
