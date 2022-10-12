package br.com.salomaotech.genesys.controller.agenda;

import static java.util.Objects.isNull;
import org.junit.Test;
import static org.junit.Assert.*;

public class AgendaControllerTest {

    private final AgendaController agendaController = new AgendaController();

    @Test
    public void testConstruir() {

        boolean isErro = false;

        try {

            agendaController.construir();

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe AgendaController metodo: construir");
        assertEquals(false, isErro);

    }

    @Test
    public void testGetMetodos() {

        System.out.println("Testando a classe AgendaController metodo: getMetodos");
        assertEquals(false, isNull(agendaController.getMetodos()));

    }

    @Test
    public void testCarregarAgendaVencida() {

        boolean isErro = false;

        try {

            agendaController.carregarAgendaVencida();

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe AgendaController metodo: carregarAgendaVencida");
        assertEquals(false, isErro);

    }

}
