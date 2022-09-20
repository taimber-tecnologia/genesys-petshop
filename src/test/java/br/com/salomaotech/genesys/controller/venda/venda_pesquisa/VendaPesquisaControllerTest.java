package br.com.salomaotech.genesys.controller.venda.venda_pesquisa;

import org.junit.Test;
import static org.junit.Assert.*;

public class VendaPesquisaControllerTest {

    private final VendaPesquisaController vendaPesquisaController = new VendaPesquisaController();

    @Test
    public void testConstruir() {

        boolean isErro = false;

        try {

            vendaPesquisaController.construir();

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe VendaPesquisaController metodo: construir");
        assertEquals(false, isErro);

    }

}
