package br.com.salomaotech.genesys.controller.venda.venda_conclui;

import br.com.salomaotech.genesys.view.JFvendaConclui;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class VendaConcluiEventosTest {

    private final JFvendaConclui view = new JFvendaConclui();
    private final VendaConcluiEventos vendaConcluiEventos = new VendaConcluiEventos(view);

    @Test
    public void testSetVendaConcluiMetodos() {

    }

    @Test
    public void testAddEventos() {

        vendaConcluiEventos.addEventos();

        System.out.println("Testando classe VendaConcluiEventos metodo: addEventos");
        assertEquals(true, view.jTvalorRecebido.getKeyListeners().length == 1);
        assertEquals(true, view.jBcancelar.getActionListeners().length == 1);
        assertEquals(true, view.jBconcluir.getActionListeners().length == 1);
        assertEquals(true, view.jCforma.getActionListeners().length == 1);

    }

}
