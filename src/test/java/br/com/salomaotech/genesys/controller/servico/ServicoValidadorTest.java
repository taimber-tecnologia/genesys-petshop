package br.com.salomaotech.genesys.controller.servico;

import org.junit.Test;
import static org.junit.Assert.*;

public class ServicoValidadorTest {

    public ServicoValidadorTest() {

    }

    @Test
    public void testIsValido() {

        System.out.println("isValido");
        ServicoValidador instance = null;
        boolean expResult = false;
        boolean result = instance.isValido();
        assertEquals(expResult, result);

    }

    @Test
    public void testGetMensagensErro() {

        System.out.println("getMensagensErro");
        ServicoValidador instance = null;
        String expResult = "";
        String result = instance.getMensagensErro();
        assertEquals(expResult, result);

    }

}
