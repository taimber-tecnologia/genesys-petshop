package br.com.salomaotech.genesys.model.agenda;

import java.util.Calendar;
import static java.util.Objects.isNull;
import org.junit.Test;
import static org.junit.Assert.*;

public class AgendaModeloTest {

    private final AgendaModelo agendaModelo = new AgendaModelo();
    private final Calendar calendar = Calendar.getInstance();

    @Test
    public void testGetId() {

        System.out.println("Testando classe AgendaModelo método getId");
        assertEquals(true, agendaModelo.getId() == 0);

    }

    @Test
    public void testSetId() {

        agendaModelo.setId(1);
        System.out.println("Testando classe AgendaModelo método setId");
        assertEquals(true, agendaModelo.getId() == 1);

    }

    @Test
    public void testGetDataAgenda() {

        System.out.println("Testando classe AgendaModelo método getDataAgenda");
        assertEquals(true, isNull(agendaModelo.getDataAgenda()));

    }

    @Test
    public void testSetDataAgenda() {

        agendaModelo.setDataAgenda(calendar);
        System.out.println("Testando classe AgendaModelo método setDataAgenda");
        assertEquals(true, agendaModelo.getDataAgenda().equals(calendar));

    }

    @Test
    public void testGetNomeCliente() {

        System.out.println("Testando classe AgendaModelo método getNomeCliente");
        assertEquals(true, isNull(agendaModelo.getNomeCliente()));

    }

    @Test
    public void testSetNomeCliente() {

        agendaModelo.setNomeCliente("Teste");
        System.out.println("Testando classe AgendaModelo método setIdCliente");
        assertEquals(true, agendaModelo.getNomeCliente().equals("Teste"));

    }

    @Test
    public void testGetDataHora() {

        System.out.println("Testando classe AgendaModelo método getDataHora");
        assertEquals(true, agendaModelo.getDataHora().equals("0"));

    }

    @Test
    public void testSetDataHora() {

        agendaModelo.setDataHora("0");
        System.out.println("Testando classe AgendaModelo método setDataHora");
        assertEquals(true, agendaModelo.getDataHora().equals("0"));

    }

    @Test
    public void testGetDataMinuto() {

        System.out.println("Testando classe AgendaModelo método getDataMinuto");
        assertEquals(true, agendaModelo.getDataMinuto().equals("0"));

    }

    @Test
    public void testSetDataMinuto() {

        agendaModelo.setDataMinuto("01");
        System.out.println("Testando classe AgendaModelo método setDataMinuto");
        assertEquals(true, agendaModelo.getDataMinuto().equals("01"));

    }

    @Test
    public void testGetObservacoes() {

        System.out.println("Testando classe AgendaModelo método getObservacoes");
        assertEquals(true, isNull(agendaModelo.getObservacoes()));

    }

    @Test
    public void testSetObservacoes() {

        agendaModelo.setObservacoes("Teste");
        System.out.println("Testando classe AgendaModelo método setObservacoes");
        assertEquals(true, agendaModelo.getObservacoes().equals("Teste"));

    }

    @Test
    public void testGetStatus() {

        System.out.println("Testando classe AgendaModelo método getStatus");
        assertEquals(true, isNull(agendaModelo.getStatus()));

    }

    @Test
    public void testSetStatus() {

        agendaModelo.setStatus("Concluido");
        System.out.println("Testando classe AgendaModelo método setStatus");
        assertEquals(true, agendaModelo.getStatus().equals("Concluido"));

    }

    @Test
    public void testGetTelefone() {

        System.out.println("Testando classe AgendaModelo método getTelefone");
        assertEquals(true, isNull(agendaModelo.getTelefone()));

    }

    @Test
    public void testSetTelefone() {

        agendaModelo.setTelefone("Concluido");
        System.out.println("Testando classe AgendaModelo método setTelefone");
        assertEquals(true, agendaModelo.getTelefone().equals("Concluido"));

    }

}
