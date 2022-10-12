package br.com.salomaotech.genesys.controller.servico;

import org.junit.Test;
import static org.junit.Assert.*;

public class ServicoControllerTest {

    private final ServicoController servicoController = new ServicoController();

    @Test
    public void testConstruir() {

        boolean isErro = false;

        try {

            servicoController.construir();

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe ServicoController metodo: construir");
        assertEquals(false, isErro);

    }

}
