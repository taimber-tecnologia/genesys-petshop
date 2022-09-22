package br.com.salomaotech.genesys.controller.cliente;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ClienteControllerTest {

    private final ClienteController clienteController = new ClienteController();

    @Test
    public void testConstruir() {

        boolean isErro = false;

        try {

            clienteController.construir();

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe ClienteController metodo: construir");
        assertEquals(false, isErro);

    }

}
