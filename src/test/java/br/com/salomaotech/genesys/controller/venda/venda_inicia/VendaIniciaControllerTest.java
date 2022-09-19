/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package br.com.salomaotech.genesys.controller.venda.venda_inicia;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author usuario
 */
public class VendaIniciaControllerTest {
    
    public VendaIniciaControllerTest() {
    }

    @Test
    public void testConstruir() {
        System.out.println("construir");
        VendaIniciaController instance = new VendaIniciaController();
        instance.construir();
        fail("The test case is a prototype.");
    }

    @Test
    public void testAbrirCadastro() {
        System.out.println("abrirCadastro");
        long id = 0L;
        VendaIniciaController instance = new VendaIniciaController();
        instance.abrirCadastro(id);
        fail("The test case is a prototype.");
    }
    
}
