package br.com.salomaotech.genesys.model.agenda;

import br.com.salomaotech.genesys.model.cliente.ClienteModelo;
import br.com.salomaotech.genesys.view.JFagenda;
import br.com.salomaotech.sistema.jpa.Repository;
import java.util.Calendar;
import org.junit.Test;
import static org.junit.Assert.*;

public class AgendaPesquisaTest {

    private final JFagenda view = new JFagenda();
    private final Calendar calendar = Calendar.getInstance();
    private final ClienteModelo clienteModelo = new ClienteModelo();
    private final AgendaModelo agendaModelo = new AgendaModelo();
    private final AgendaPesquisa agendaPesquisa = new AgendaPesquisa(view.jTresultados, view.jCpaginador);

    public AgendaPesquisaTest() {

        /* simula cadastro de cliente */
        new Repository(new ClienteModelo()).deleteTodos();
        clienteModelo.setNome("Teste");
        clienteModelo.setCpf("000.000.000-00");
        new Repository(clienteModelo).save();

        /* simula cadastro de agenda */
        new Repository(new AgendaModelo()).deleteTodos();
        agendaModelo.setDataAgenda(calendar);
        agendaModelo.setIdCliente(clienteModelo.getId());
        agendaModelo.setStatus("Atendido");
        new Repository(agendaModelo).save();

    }

    @Test
    public void testSetDataInicialDate() {

        boolean isErro = false;

        try {

            agendaPesquisa.setDataInicialDate(calendar);

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe AgendaPesquisa método: setDataInicialDate");
        assertEquals(false, isErro);

    }

    @Test
    public void testSetDataFinalDate() {

        boolean isErro = false;

        try {

            agendaPesquisa.setDataFinalDate(calendar);

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe AgendaPesquisa metodo: setDataFinalDate");
        assertEquals(false, isErro);

    }

    @Test
    public void testSetIdCliente() {

        boolean isErro = false;

        try {

            agendaPesquisa.setIdCliente(clienteModelo.getId());

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe AgendaPesquisa método: setIdCliente");
        assertEquals(false, isErro);

    }

    @Test
    public void testSetStatus() {

        boolean isErro = false;

        try {

            agendaPesquisa.setStatus(agendaModelo.getStatus());

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe AgendaPesquisa método: setStatus");
        assertEquals(false, isErro);

    }

    @Test
    public void testPesquisar() {

        /* usando filtro: nenhum */
        agendaPesquisa.setDataInicialDate(null);
        agendaPesquisa.setDataFinalDate(null);
        agendaPesquisa.setIdCliente(0);
        agendaPesquisa.setStatus(null);
        agendaPesquisa.pesquisar();
        System.out.println("Testando classe AgendaPesquisa método: pesquisar etapa 01");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: data inicial */
        agendaPesquisa.setDataInicialDate(calendar);
        agendaPesquisa.setDataFinalDate(null);
        agendaPesquisa.setIdCliente(0);
        agendaPesquisa.setStatus(null);
        agendaPesquisa.pesquisar();
        System.out.println("Testando classe AgendaPesquisa método: pesquisar etapa 02");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: data final */
        agendaPesquisa.setDataInicialDate(null);
        agendaPesquisa.setDataFinalDate(calendar);
        agendaPesquisa.setIdCliente(0);
        agendaPesquisa.setStatus(null);
        agendaPesquisa.pesquisar();
        System.out.println("Testando classe AgendaPesquisa método: pesquisar etapa 03");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: cliente */
        agendaPesquisa.setDataInicialDate(null);
        agendaPesquisa.setDataFinalDate(null);
        agendaPesquisa.setIdCliente(clienteModelo.getId());
        agendaPesquisa.setStatus(null);
        agendaPesquisa.pesquisar();
        System.out.println("Testando classe AgendaPesquisa método: pesquisar etapa 04");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: status */
        agendaPesquisa.setDataInicialDate(null);
        agendaPesquisa.setDataFinalDate(null);
        agendaPesquisa.setIdCliente(0);
        agendaPesquisa.setStatus("Atendido");
        agendaPesquisa.pesquisar();
        System.out.println("Testando classe AgendaPesquisa método: pesquisar etapa 05");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: todos */
        agendaPesquisa.setDataInicialDate(calendar);
        agendaPesquisa.setDataFinalDate(calendar);
        agendaPesquisa.setIdCliente(clienteModelo.getId());
        agendaPesquisa.setStatus("Atendido");
        agendaPesquisa.pesquisar();
        System.out.println("Testando classe AgendaPesquisa método: pesquisar etapa 06");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

    }

}
