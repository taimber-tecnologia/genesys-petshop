package br.com.salomaotech.genesys.controller.venda;

import br.com.salomaotech.genesys.controller.venda.VendaController;
import static java.util.Objects.isNull;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class VendaControllerTest {

    private final VendaController vendaController = new VendaController();

    @Test
    public void testConstruir() {

    }

    @Test
    public void testGetVendaMetodos() {

        System.out.println("Testando a classe VendaController metodo: getVendaMetodos");
        assertEquals(false, isNull(vendaController.getVendaMetodos()));

    }

}
