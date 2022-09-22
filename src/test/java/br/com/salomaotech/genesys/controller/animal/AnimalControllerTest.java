package br.com.salomaotech.genesys.controller.animal;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class AnimalControllerTest {

    private final AnimalController animalController = new AnimalController();

    @Test
    public void testConstruir() {

        boolean isErro = false;

        try {

            animalController.construir();

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe AnimalController metodo: construir");
        assertEquals(false, isErro);

    }

}
