package br.com.salomaotech.genesys.controller.venda.venda_calcula;

import br.com.salomaotech.genesys.view.JFvendaCalcula;
import org.junit.Test;
import static org.junit.Assert.*;

public class VendaCalculaEventosProdutosTest {

    private final JFvendaCalcula view = new JFvendaCalcula();
    private final VendaCalculaMetodosProdutos vendaCalculaMetodos = new VendaCalculaMetodosProdutos(view);
    private final VendaCalculaEventosProdutos vendaCalculaEventos = new VendaCalculaEventosProdutos(view);

    @Test
    public void testSetVendaCalculaMetodos() {

        boolean isErro = false;

        try {

            vendaCalculaEventos.setVendaCalculaMetodos(vendaCalculaMetodos);

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe VendaCalculaEventos metodo: setVendaCalculaMetodos");
        assertEquals(false, isErro);

    }

    @Test
    public void testAddEventos() {

        /* adiciona eventos */
        vendaCalculaEventos.addEventos();

        /* testa se os eventos foram adicionados */
        System.out.println("Testando classe VendaCalculaEventos metodo: addEventos");
        assertEquals(true, view.jTvalorDesejado.getKeyListeners().length == 1);
        assertEquals(true, view.jTpesoKg.getKeyListeners().length == 1);
        assertEquals(true, view.jBfechar.getActionListeners().length == 1);

    }

}
