package br.com.salomaotech.genesys.controller.venda.venda_calcula;

import org.junit.Test;
import static org.junit.Assert.*;

public class VendaCalculaValidadorTest {

    public VendaCalculaValidadorTest() {

    }

    @Test
    public void testIsValido() {

        System.out.println("isValido");
        VendaCalculaValidador instance = null;
        boolean expResult = false;
        boolean result = instance.isValido();
        assertEquals(expResult, result);

    }

}
