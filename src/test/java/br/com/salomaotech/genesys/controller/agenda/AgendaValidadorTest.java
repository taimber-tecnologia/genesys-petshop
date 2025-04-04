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

        /* Etapa 01 - Todos campos inválidos */
        view.jDcadastroData.setCalendar(null);
        view.jCcadastroNomeCliente.getEditor().setItem("");
        view.jTcadastroTelefone.setText("");
        agendaValidador = new AgendaValidador(view);
        System.out.println("Testando isValido - Etapa 01: Todos inválidos");
        assertEquals(false, agendaValidador.isValido());

        /* Etapa 02 - Data OK, nome e telefone vazios */
        view.jDcadastroData.setCalendar(Calendar.getInstance());
        view.jCcadastroNomeCliente.getEditor().setItem("");
        view.jTcadastroTelefone.setText("");
        agendaValidador = new AgendaValidador(view);
        System.out.println("Testando isValido - Etapa 02: Data OK, outros inválidos");
        assertEquals(false, agendaValidador.isValido());

        /* Etapa 03 - Data e nome OK, telefone vazio */
        view.jDcadastroData.setCalendar(Calendar.getInstance());
        view.jCcadastroNomeCliente.getEditor().setItem("Cliente Teste");
        view.jTcadastroTelefone.setText("");
        agendaValidador = new AgendaValidador(view);
        System.out.println("Testando isValido - Etapa 03: Telefone vazio");
        assertEquals(false, agendaValidador.isValido());

        /* Etapa 04 - Todos válidos */
        view.jDcadastroData.setCalendar(Calendar.getInstance());
        view.jCcadastroNomeCliente.getEditor().setItem("Cliente Teste");
        view.jTcadastroTelefone.setText("11999999999");
        agendaValidador = new AgendaValidador(view);
        System.out.println("Testando isValido - Etapa 04: Todos válidos");
        assertEquals(true, agendaValidador.isValido());

    }

    @Test
    public void testGetMensagensErro() {

        /* Etapa 01 - Erro de data */
        view.jDcadastroData.setCalendar(null);
        view.jCcadastroNomeCliente.getEditor().setItem("");
        view.jTcadastroTelefone.setText("");
        agendaValidador = new AgendaValidador(view);
        agendaValidador.isValido();
        System.out.println("Testando getMensagensErro - Etapa 01");
        assertEquals(true, agendaValidador.getMensagensErro().length() > 0);

        /* Etapa 02 - Erro de nome */
        view.jDcadastroData.setCalendar(Calendar.getInstance());
        view.jCcadastroNomeCliente.getEditor().setItem("");
        view.jTcadastroTelefone.setText("");
        agendaValidador = new AgendaValidador(view);
        agendaValidador.isValido();
        System.out.println("Testando getMensagensErro - Etapa 02");
        assertEquals(true, agendaValidador.getMensagensErro().length() > 0);

        /* Etapa 03 - Erro de telefone */
        view.jDcadastroData.setCalendar(Calendar.getInstance());
        view.jCcadastroNomeCliente.getEditor().setItem("Cliente Teste");
        view.jTcadastroTelefone.setText("");
        agendaValidador = new AgendaValidador(view);
        agendaValidador.isValido();
        System.out.println("Testando getMensagensErro - Etapa 03");
        assertEquals(true, agendaValidador.getMensagensErro().length() > 0);

        /* Etapa 04 - Sem erros */
        view.jDcadastroData.setCalendar(Calendar.getInstance());
        view.jCcadastroNomeCliente.getEditor().setItem("Cliente Teste");
        view.jTcadastroTelefone.setText("11999999999");
        agendaValidador = new AgendaValidador(view);
        agendaValidador.isValido();
        System.out.println("Testando getMensagensErro - Etapa 04");
        assertEquals(true, agendaValidador.getMensagensErro().length() == 0);

    }

}
