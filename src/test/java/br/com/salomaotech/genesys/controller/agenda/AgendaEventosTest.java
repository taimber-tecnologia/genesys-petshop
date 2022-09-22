package br.com.salomaotech.genesys.controller.agenda;

import br.com.salomaotech.genesys.model.animal.ComboBoxAnimais;
import br.com.salomaotech.genesys.model.cliente.ComboBoxClientes;
import br.com.salomaotech.genesys.view.JFagenda;
import javax.swing.JComboBox;
import org.junit.Test;
import static org.junit.Assert.*;

public class AgendaEventosTest {

    private final JFagenda view = new JFagenda();
    private final AgendaEventos agendaEventos = new AgendaEventos(view);

    @Test
    public void testSetAgendaMetodos() {

        boolean isErro = false;

        try {

            agendaEventos.setAgendaMetodos(new AgendaMetodos(view));

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe AgendaEventos método: setAgendaMetodos");
        assertEquals(false, isErro);

    }

    @Test
    public void testSetComboBoxClientesCadastro() {

        boolean isErro = false;

        try {

            agendaEventos.setComboBoxClientesCadastro(new ComboBoxClientes(new JComboBox()));

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe AgendaEventos método: setComboBoxClientesCadastro");
        assertEquals(false, isErro);

    }

    @Test
    public void testSetComboBoxClientesPesquisa() {

        boolean isErro = false;

        try {

            agendaEventos.setComboBoxClientesPesquisa(new ComboBoxClientes(new JComboBox()));

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe AgendaEventos método: setComboBoxClientesPesquisa");
        assertEquals(false, isErro);

    }

    @Test
    public void testSetComboBoxAnimaisCadastro() {

        boolean isErro = false;

        try {

            agendaEventos.setComboBoxAnimaisCadastro(new ComboBoxAnimais(new JComboBox()));

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe AgendaEventos método: setComboBoxAnimaisCadastro");
        assertEquals(false, isErro);

    }

    @Test
    public void testAddEventos() {

        /* adiciona eventos */
        agendaEventos.addEventos();

        /* testa se os eventos foram adicionados */
        System.out.println("Testando classe AgendaEventos método: addEventos");
        assertEquals(true, view.jBcadastroSalvar.getActionListeners().length == 1);
        assertEquals(true, view.jBcadastroExcluir.getActionListeners().length == 1);
        assertEquals(true, view.jTresultados.getMouseListeners().length == 3);
        assertEquals(true, view.jBatalhoCadastro.getActionListeners().length == 1);
        assertEquals(true, view.jBatalhoPesquisa.getActionListeners().length == 1);
        assertEquals(true, view.jBpesquisa.getActionListeners().length == 1);
        assertEquals(true, view.jBpaginador.getActionListeners().length == 1);
        assertEquals(true, view.jBpesquisaCliente.getActionListeners().length == 1);
        assertEquals(true, view.jBrefreshCliente.getActionListeners().length == 1);
        assertEquals(true, view.jBpesquisaNomeClienteRefresh.getActionListeners().length == 1);
        assertEquals(true, view.jBpesquisaAnimal.getActionListeners().length == 1);
        assertEquals(true, view.jBrefreshAnimais.getActionListeners().length == 1);

    }

}
