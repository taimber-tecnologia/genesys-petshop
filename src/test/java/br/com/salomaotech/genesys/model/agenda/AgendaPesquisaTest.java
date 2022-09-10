package br.com.salomaotech.genesys.model.agenda;

import br.com.salomaotech.genesys.model.agenda.AgendaPesquisa;
import br.com.salomaotech.genesys.model.agenda.AgendaModelo;
import br.com.salomaotech.genesys.model.cliente.ClienteModelo;
import br.com.salomaotech.genesys.view.JFagenda;
import br.com.salomaotech.sistema.jpa.Repository;
import java.util.Calendar;
import org.junit.Test;
import static org.junit.Assert.*;

public class AgendaPesquisaTest {

    private JFagenda view = new JFagenda();
    private final Calendar calendar = Calendar.getInstance();
    private final ClienteModelo clienteModelo = new ClienteModelo();
    private final AgendaModelo agendaModelo = new AgendaModelo();
    private AgendaPesquisa agendaPesquisa = new AgendaPesquisa(view.jTresultados, view.jCpaginador);

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
    public void testSetDataAgenda() {

        agendaPesquisa.setDataAgenda(calendar);
        agendaPesquisa.pesquisar();
        System.out.println("Testando classe AgendaPesquisa método: setDataAgenda");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

    }

    @Test
    public void testSetIdCliente() {

        agendaPesquisa.setIdCliente(clienteModelo.getId());
        agendaPesquisa.pesquisar();
        System.out.println("Testando classe AgendaPesquisa método: setIdCliente");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

    }

    @Test
    public void testSetStatus() {

        agendaPesquisa.setStatus(agendaModelo.getStatus());
        agendaPesquisa.pesquisar();
        System.out.println("Testando classe AgendaPesquisa método: setStatus");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

    }

    @Test
    public void testPesquisar() {

        /* usando filtro: nenhum */
        view = new JFagenda();
        agendaPesquisa = new AgendaPesquisa(view.jTresultados, view.jCpaginador);
        agendaPesquisa.setDataAgenda(null);
        agendaPesquisa.setIdCliente(0);
        agendaPesquisa.setStatus(null);
        agendaPesquisa.pesquisar();
        System.out.println("Testando classe AgendaPesquisa método: pesquisar etapa 01");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: data */
        view = new JFagenda();
        agendaPesquisa = new AgendaPesquisa(view.jTresultados, view.jCpaginador);
        agendaPesquisa.setDataAgenda(calendar);
        agendaPesquisa.setIdCliente(0);
        agendaPesquisa.setStatus(null);
        agendaPesquisa.pesquisar();
        System.out.println("Testando classe AgendaPesquisa método: pesquisar etapa 02");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: cliente */
        view = new JFagenda();
        agendaPesquisa = new AgendaPesquisa(view.jTresultados, view.jCpaginador);
        agendaPesquisa.setDataAgenda(null);
        agendaPesquisa.setIdCliente(clienteModelo.getId());
        agendaPesquisa.setStatus(null);
        agendaPesquisa.pesquisar();
        System.out.println("Testando classe AgendaPesquisa método: pesquisar etapa 03");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: status */
        view = new JFagenda();
        agendaPesquisa = new AgendaPesquisa(view.jTresultados, view.jCpaginador);
        agendaPesquisa.setDataAgenda(null);
        agendaPesquisa.setIdCliente(0);
        agendaPesquisa.setStatus("Atendido");
        agendaPesquisa.pesquisar();
        System.out.println("Testando classe AgendaPesquisa método: pesquisar etapa 04");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: todos */
        view = new JFagenda();
        agendaPesquisa = new AgendaPesquisa(view.jTresultados, view.jCpaginador);
        agendaPesquisa.setDataAgenda(calendar);
        agendaPesquisa.setIdCliente(clienteModelo.getId());
        agendaPesquisa.setStatus("Atendido");
        agendaPesquisa.pesquisar();
        System.out.println("Testando classe AgendaPesquisa método: pesquisar etapa 05");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

    }

}
