package br.com.salomaotech.genesys.model.agenda;

import java.util.Calendar;
import static java.util.Objects.isNull;
import org.junit.Test;
import static org.junit.Assert.*;

public class AgendaModeloTest {

    private final AgendaModelo agendaModelo = new AgendaModelo();

    @Test
    public void testGetId() {

        System.out.println("Testando classe AgendaModelo metodo getId");
        assertEquals(true, agendaModelo.getId() == 0);

    }

    @Test
    public void testSetId() {

        agendaModelo.setId(1);
        System.out.println("Testando classe AgendaModelo metodo setId");
        assertEquals(true, agendaModelo.getId() == 1);

    }

    @Test
    public void testGetDataAgenda() {

        System.out.println("Testando classe AgendaModelo metodo getDataAgenda");
        assertEquals(true, isNull(agendaModelo.getDataAgenda()));

    }

    @Test
    public void testSetDataAgenda() {

        Calendar dataAgenda = Calendar.getInstance();
        agendaModelo.setDataAgenda(dataAgenda);
        System.out.println("Testando classe AgendaModelo metodo setDataAgenda");
        assertEquals(true, agendaModelo.getDataAgenda().equals(dataAgenda));

    }

    @Test
    public void testGetDataHora() {

        System.out.println("Testando classe AgendaModelo metodo getDataHora");
        assertEquals(true, agendaModelo.getDataHora().equals("0"));

    }

    @Test
    public void testSetDataHora() {

        agendaModelo.setDataHora("10");
        System.out.println("Testando classe AgendaModelo metodo setDataHora");
        assertEquals(true, agendaModelo.getDataHora().equals("10"));

    }

    @Test
    public void testGetDataMinuto() {

        System.out.println("Testando classe AgendaModelo metodo getDataMinuto");
        assertEquals(true, agendaModelo.getDataMinuto().equals("0"));

    }

    @Test
    public void testSetDataMinuto() {

        agendaModelo.setDataMinuto("30");
        System.out.println("Testando classe AgendaModelo metodo setDataMinuto");
        assertEquals(true, agendaModelo.getDataMinuto().equals("30"));

    }

    @Test
    public void testGetNomeCliente() {

        System.out.println("Testando classe AgendaModelo metodo getNomeCliente");
        assertEquals(true, isNull(agendaModelo.getNomeCliente()));

    }

    @Test
    public void testSetNomeCliente() {

        agendaModelo.setNomeCliente("João Silva");
        System.out.println("Testando classe AgendaModelo metodo setNomeCliente");
        assertEquals(true, agendaModelo.getNomeCliente().equals("João Silva"));

    }

    @Test
    public void testGetObservacoes() {

        System.out.println("Testando classe AgendaModelo metodo getObservacoes");
        assertEquals(true, isNull(agendaModelo.getObservacoes()));

    }

    @Test
    public void testSetObservacoes() {

        agendaModelo.setObservacoes("Consulta de rotina");
        System.out.println("Testando classe AgendaModelo metodo setObservacoes");
        assertEquals(true, agendaModelo.getObservacoes().equals("Consulta de rotina"));

    }

    @Test
    public void testGetStatus() {

        System.out.println("Testando classe AgendaModelo metodo getStatus");
        assertEquals(true, isNull(agendaModelo.getStatus()));

    }

    @Test
    public void testSetStatus() {
        agendaModelo.setStatus("Agendado");
        System.out.println("Testando classe AgendaModelo metodo setStatus");
        assertEquals(true, agendaModelo.getStatus().equals("Agendado"));
    }

    @Test
    public void testGetTelefone() {

        System.out.println("Testando classe AgendaModelo metodo getTelefone");
        assertEquals(true, isNull(agendaModelo.getTelefone()));

    }

    @Test
    public void testSetTelefone() {

        agendaModelo.setTelefone("11999999999");
        System.out.println("Testando classe AgendaModelo metodo setTelefone");
        assertEquals(true, agendaModelo.getTelefone().equals("11999999999"));

    }

}
