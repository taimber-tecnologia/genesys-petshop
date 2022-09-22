package br.com.salomaotech.genesys.controller.fornecedor;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class FornecedorControllerTest {

    private final FornecedorController fornecedorController = new FornecedorController();

    @Test
    public void testConstruir() {

        boolean isErro = false;

        try {

            fornecedorController.construir();

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe FornecedorController metodo: construir");
        assertEquals(false, isErro);

    }

}
