package br.com.salomaotech.genesys.controller.configuracoes;

import org.junit.Test;
import static org.junit.Assert.*;

public class ConfiguracoesControllerTest {

    private final ConfiguracoesController configuracoesController = new ConfiguracoesController();

    @Test
    public void testConstruir() {

        boolean isErro = false;

        try {

            configuracoesController.construir();

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe ConfiguracoesController metodo: construir");
        assertEquals(false, isErro);

    }

}
