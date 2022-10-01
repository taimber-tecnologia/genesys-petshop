package br.com.salomaotech.genesys.controller.principal;

import br.com.salomaotech.genesys.model.agenda.AgendaModelo;
import br.com.salomaotech.genesys.view.JFprincipal;
import br.com.salomaotech.sistema.jpa.Repository;
import java.util.Calendar;
import org.junit.Test;
import static org.junit.Assert.*;

public class PrincipalMetodosTest {

    private final JFprincipal view = new JFprincipal();
    private final AgendaModelo agendaModelo = new AgendaModelo();
    private final Calendar calendar = Calendar.getInstance();
    private PrincipalMetodos principalMetodos = new PrincipalMetodos(view);

    public PrincipalMetodosTest() {

        /* simula cadastro de agenda */
        new Repository(new AgendaModelo()).deleteTodos();
        agendaModelo.setDataAgenda(calendar);
        agendaModelo.setDataHora("14");
        agendaModelo.setDataMinuto("30");
        agendaModelo.setObservacoes("Avaliação");
        agendaModelo.setNomeCliente("Teste");
        agendaModelo.setStatus("1 - Agendado");
        new Repository(agendaModelo).save();

    }

    /* OBS: é obrigatório usar o filtro data neste caso de teste */
    @Test
    public void testCarregaAgendaDia() {

        /* pesquisa usando filtro: nenhum */
        principalMetodos = new PrincipalMetodos(view);
        principalMetodos.carregaAgendaDia();
        System.out.println("Testando classe PrincipalMetodos metodo: carregaAgendaDia etapa 01");
        assertEquals(true, view.jTagendaResultados.getRowCount() > 0);

        /* pesquisa usando filtro: status */
        principalMetodos = new PrincipalMetodos(view);
        view.jCpesquisaStatus.setSelectedItem("Todos");
        view.jDagendaDataSemana.setCalendar(agendaModelo.getDataAgenda());
        principalMetodos.carregaAgendaDia();
        System.out.println("Testando classe PrincipalMetodos metodo: carregaAgendaDia etapa 02");
        assertEquals(true, view.jTagendaResultados.getRowCount() > 0);

        /* pesquisa usando filtro: todos */
        principalMetodos = new PrincipalMetodos(view);
        view.jCpesquisaStatus.setSelectedItem(agendaModelo.getStatus());
        view.jDagendaDataSemana.setCalendar(agendaModelo.getDataAgenda());
        principalMetodos.carregaAgendaDia();
        System.out.println("Testando classe PrincipalMetodos metodo: carregaAgendaDia etapa 03");
        assertEquals(true, view.jTagendaResultados.getRowCount() > 0);

    }

}
