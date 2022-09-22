package br.com.salomaotech.genesys.controller.centro_custo;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CentroCustoControllerTest {

    private final CentroCustoController centroCustoController = new CentroCustoController();

    @Test
    public void testConstruir() {

        boolean isErro = false;

        try {

            centroCustoController.construir();

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe CentroCustoController metodo: construir");
        assertEquals(false, isErro);

    }

}
