package br.com.salomaotech.genesys.model.notificacoes;

import br.com.salomaotech.genesys.model.agenda.AgendaModelo;
import br.com.salomaotech.genesys.model.financeiro.FinanceiroModelo;
import br.com.salomaotech.sistema.jpa.Repository;
import java.math.BigDecimal;
import java.util.Calendar;
import org.junit.Test;
import static org.junit.Assert.*;

public class NotificacoesTest {

    private final Calendar calendar = Calendar.getInstance();
    private final Notificacoes notificacoes = new Notificacoes();

    public NotificacoesTest() {

        /* simula cadastro de agenda */
        new Repository(new AgendaModelo()).deleteTodos();
        AgendaModelo agendaModelo = new AgendaModelo();
        agendaModelo.setDataAgenda(calendar);
        agendaModelo.setDataHora("14");
        agendaModelo.setDataMinuto("30");
        agendaModelo.setObservacoes("Banho");
        agendaModelo.setNomeCliente("Teste");
        agendaModelo.setStatus("1 - Agendado");
        new Repository(agendaModelo).save();

        /* simula cadastro de financeiro */
        new Repository(new FinanceiroModelo()).deleteTodos();
        FinanceiroModelo financeiroModelo = new FinanceiroModelo();
        financeiroModelo.setData(calendar);
        financeiroModelo.setValor(new BigDecimal(100));
        financeiroModelo.setDescricao("Teste A");
        financeiroModelo.setIdCentroCusto(0);
        financeiroModelo.setIsPago(false);
        financeiroModelo.setIsDespesa(true);
        new Repository(financeiroModelo).save();

    }

    @Test
    public void testGetAgenda() {

        System.out.println("Testando classe Notificacoes metodo: getAgenda");
        assertEquals(true, notificacoes.getAgenda() == 1);

    }

    @Test
    public void testGetFinanceiroPagar() {

        System.out.println("Testando classe Notificacoes metodo: getFinanceiroPagar");
        assertEquals(true, notificacoes.getFinanceiroPagar() == 1);

    }

    @Test
    public void testGetFinanceiroReceber() {

        System.out.println("Testando classe Notificacoes metodo: getFinanceiroReceber");
        assertEquals(true, notificacoes.getFinanceiroReceber() == 0);

    }

    @Test
    public void testTotal() {

        System.out.println("Testando classe Notificacoes metodo: total");
        assertEquals(true, notificacoes.total() == 2);

    }

}
