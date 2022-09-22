package br.com.salomaotech.genesys.controller.empresa;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class EmpresaControllerTest {

    private final EmpresaController empresaController = new EmpresaController();

    @Test
    public void testConstruir() {

        boolean isErro = false;

        try {

            empresaController.construir();

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe EmpresaController metodo: construir");
        assertEquals(false, isErro);

    }

}
