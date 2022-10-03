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

    @Test
    public void testConstruirContasPagar() {

        boolean isErro = false;

        try {

            financeiroController.construirContasPagar();

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe FinanceiroController metodo: construirContasPagar");
        assertEquals(false, isErro);

    }

    @Test
    public void testConstruirContasReceber() {

        boolean isErro = false;

        try {

            financeiroController.construirContasReceber();

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe FinanceiroController metodo: construirContasReceber");
        assertEquals(false, isErro);

    }

}
