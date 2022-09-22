package br.com.salomaotech.genesys.controller.principal;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class PrincipalControllerTest {

    private final PrincipalController principalController = new PrincipalController();

    @Test
    public void testConstruir() {

        boolean isErro = false;

        try {

            principalController.construir();

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe PrincipalController metodo: construir");
        assertEquals(false, isErro);

    }

}
