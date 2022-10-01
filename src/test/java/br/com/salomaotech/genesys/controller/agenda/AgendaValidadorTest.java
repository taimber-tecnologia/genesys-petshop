package br.com.salomaotech.genesys.controller.agenda;

import br.com.salomaotech.genesys.view.JFagenda;
import java.util.Calendar;
import org.junit.Test;
import static org.junit.Assert.*;

public class AgendaValidadorTest {

    private final JFagenda view = new JFagenda();
    private AgendaValidador agendaValidador = new AgendaValidador(view);

    @Test
    public void testIsValido() {

        /* usando filtro: nenhum */
        view.jDcadastroData.setCalendar(null);
        agendaValidador = new AgendaValidador(view);
        System.out.println("Testando classe AgendaValidador metodo: isValido etapa 01");
        assertEquals(false, agendaValidador.isValido());

        /* usando filtro: data */
        view.jDcadastroData.setCalendar(Calendar.getInstance());
        agendaValidador = new AgendaValidador(view);
        System.out.println("Testando classe AgendaValidador metodo: isValido etapa 02");
        assertEquals(true, agendaValidador.isValido());

    }

    @Test
    public void testGetMensagensErro() {

        /* usando filtro: nenhum */
        view.jDcadastroData.setCalendar(null);
        agendaValidador = new AgendaValidador(view);
        agendaValidador.isValido();
        System.out.println("Testando classe AgendaValidador metodo: getMensagensErro etapa 01");
        assertEquals(true, agendaValidador.getMensagensErro().length() != 0);

        /* usando filtro: data */
        view.jDcadastroData.setCalendar(Calendar.getInstance());
        agendaValidador = new AgendaValidador(view);
        agendaValidador.isValido();
        System.out.println("Testando classe AgendaValidador metodo: getMensagensErro etapa 02");
        assertEquals(true, agendaValidador.getMensagensErro().length() == 0);

    }

}
