package br.com.salomaotech.genesys.controller.venda.venda_conclui;

import br.com.salomaotech.genesys.model.venda.VendaModelo;
import br.com.salomaotech.genesys.view.JFvendaInicia;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class VendaConcluiControllerTest {

    private final VendaConcluiController vendaConcluiController = new VendaConcluiController(new VendaModelo(), new JFvendaInicia(), null, null);

    @Test
    public void testConstruir() {

        boolean isErro = false;

        try {

            vendaConcluiController.construir();

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe VendaConcluiController metodo: construir");
        assertEquals(false, isErro);

    }

}
