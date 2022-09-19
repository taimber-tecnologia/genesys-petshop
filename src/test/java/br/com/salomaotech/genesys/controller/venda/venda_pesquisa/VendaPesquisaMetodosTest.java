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
public class VendaPesquisaMetodosTest {
    
    public VendaPesquisaMetodosTest() {
    }

    @Test
    public void testSetComboBoxClientesPesquisa() {
        System.out.println("setComboBoxClientesPesquisa");
        ComboBoxClientes comboBoxClientesPesquisa = null;
        VendaPesquisaMetodos instance = null;
        instance.setComboBoxClientesPesquisa(comboBoxClientesPesquisa);
        fail("The test case is a prototype.");
    }

    @Test
    public void testPesquisar() {
        System.out.println("pesquisar");
        VendaPesquisaMetodos instance = null;
        instance.pesquisar();
        fail("The test case is a prototype.");
    }

    @Test
    public void testAbrirCadastro() {
        System.out.println("abrirCadastro");
        long id = 0L;
        VendaPesquisaMetodos instance = null;
        instance.abrirCadastro(id);
        fail("The test case is a prototype.");
    }
    
}
