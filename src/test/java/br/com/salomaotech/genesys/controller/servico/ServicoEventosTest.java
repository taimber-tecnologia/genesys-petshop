package br.com.salomaotech.genesys.controller.servico;

import br.com.salomaotech.genesys.view.JFservico;
import org.junit.Test;
import static org.junit.Assert.*;

public class ServicoEventosTest {

    private final JFservico view = new JFservico();
    private final ServicoMetodos servicoMetodos = new ServicoMetodos(view);
    private final ServicoEventos servicoEventos = new ServicoEventos(view);

    @Test
    public void testSetServicoMetodos() {

        boolean isErro = false;

        try {

            servicoEventos.setServicoMetodos(servicoMetodos);

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe ServicoEventos metodo: setServicoMetodos");
        assertEquals(false, isErro);

    }

    @Test
    public void testAddEventos() {

        servicoEventos.addEventos();
        System.out.println("Testando classe ServicoEventos metodo: addEventos");
        assertEquals(true, view.jBcadastroSalvar.getActionListeners().length == 1);
        assertEquals(true, view.jBcadastroExcluir.getActionListeners().length == 1);
        assertEquals(true, view.jTresultados.getMouseListeners().length == 3);
        assertEquals(true, view.jBatalhoCadastro.getActionListeners().length == 1);
        assertEquals(true, view.jBatalhoPesquisa.getActionListeners().length == 1);
        assertEquals(true, view.jBpesquisa.getActionListeners().length == 1);
        assertEquals(true, view.jTpesquisaNome.getKeyListeners().length == 1);

    }

}
