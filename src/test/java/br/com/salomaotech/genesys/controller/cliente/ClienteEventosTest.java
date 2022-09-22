package br.com.salomaotech.genesys.controller.cliente;

import br.com.salomaotech.genesys.view.JFcliente;
import org.junit.Test;
import static org.junit.Assert.*;

public class ClienteEventosTest {

    private final JFcliente view = new JFcliente();
    private final ClienteEventos clienteEventos = new ClienteEventos(view);

    @Test
    public void testSetClienteMetodos() {

    }

    @Test
    public void testAddEventos() {

        /* eventos */
        clienteEventos.addEventos();

        /* testa se os eventos foram adicionados */
        System.out.println("Testando classe ClienteEventos método: addEventos");
        assertEquals(true, view.jBatalhoCadastro.getActionListeners().length == 1);
        assertEquals(true, view.jBcadastroSalvar.getActionListeners().length == 1);
        assertEquals(true, view.jBcadastroExcluir.getActionListeners().length == 1);
        assertEquals(true, view.jTresultados.getMouseListeners().length == 3);
        assertEquals(true, view.jTresultados.getKeyListeners().length == 2);
        assertEquals(true, view.jBpesquisa.getActionListeners().length == 1);
        assertEquals(true, view.jBadicionaFoto.getActionListeners().length == 1);
        assertEquals(true, view.jBremoveFoto.getActionListeners().length == 1);
        assertEquals(true, view.jTpesquisaNome.getKeyListeners().length == 1);
        assertEquals(true, view.jTpesquisaCpf.getKeyListeners().length == 1);
        assertEquals(true, view.jBenderecoBuscarCep.getActionListeners().length == 1);
        assertEquals(true, view.jBatalhoPesquisa.getActionListeners().length == 1);

    }

}
