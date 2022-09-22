package br.com.salomaotech.genesys.controller.financeiro;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class FinanceiroControllerTest {

    private final FinanceiroController financeiroController = new FinanceiroController();

    @Test
    public void testConstruir() {

        boolean isErro = false;

        try {

            financeiroController.construir();

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe FinanceiroController metodo: construir");
        assertEquals(false, isErro);

    }

}
