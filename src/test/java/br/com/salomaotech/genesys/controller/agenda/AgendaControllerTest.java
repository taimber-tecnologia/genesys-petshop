package br.com.salomaotech.genesys.controller.agenda;

import br.com.salomaotech.genesys.controller.agenda.AgendaController;
import static java.util.Objects.isNull;
import org.junit.Test;
import static org.junit.Assert.*;

public class AgendaControllerTest {

    private final AgendaController agendaController = new AgendaController();

    @Test
    public void testConstruir() {

    }

    @Test
    public void testGetMetodos() {

        System.out.println("Testando a classe AgendaController metodo: getMetodos");
        assertEquals(false, isNull(agendaController.getMetodos()));

    }

}
