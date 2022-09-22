package br.com.salomaotech.genesys.controller.vacina;

import br.com.salomaotech.genesys.model.animal.AnimalModelo;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class VacinaControllerTest {

    private final VacinaController vacinaController = new VacinaController(new AnimalModelo());

    @Test
    public void testConstruir() {

        boolean isErro = false;

        try {

            vacinaController.construir();

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe VacinaController metodo: construir");
        assertEquals(false, isErro);

    }

}
