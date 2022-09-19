package br.com.salomaotech.genesys.controller.venda.venda_conclui;

import org.junit.Test;
import static org.junit.Assert.*;

public class VendaConcluiValidadorTest {

    public VendaConcluiValidadorTest() {

    }

    @Test
    public void testIsValido() {

        System.out.println("isValido");
        VendaConcluiValidador instance = null;
        boolean expResult = false;
        boolean result = instance.isValido();
        assertEquals(expResult, result);

    }

    @Test
    public void testGetMensagensErro() {

        System.out.println("getMensagensErro");
        VendaConcluiValidador instance = null;
        String expResult = "";
        String result = instance.getMensagensErro();
        assertEquals(expResult, result);

    }

}
