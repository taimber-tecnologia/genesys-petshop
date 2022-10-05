package br.com.salomaotech.genesys.controller.venda.venda_pesquisa;

import br.com.salomaotech.genesys.view.JFvendaPesquisa;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class VendaPesquisaEventosTest {

    private final JFvendaPesquisa view = new JFvendaPesquisa();
    private final VendaPesquisaEventos vendaPesquisaEventos = new VendaPesquisaEventos(view);

    @Test
    public void testSetVendaPesquisaMetodos() {

        boolean isErro = false;

        try {

            vendaPesquisaEventos.setVendaPesquisaMetodos(new VendaPesquisaMetodos(view));

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe VendaPesquisaEventos metodo: setVendaPesquisaMetodos");
        assertEquals(false, isErro);

    }

    @Test
    public void testAddEventos() {

        vendaPesquisaEventos.addEventos();
        System.out.println("Testando classe VendaPesquisaEventos metodo: addEventos");
        assertEquals(true, view.jBatalhoCadastro.getActionListeners().length == 1);
        assertEquals(true, view.jBpesquisa.getActionListeners().length == 1);
        assertEquals(true, view.jBpaginador.getActionListeners().length == 1);
        assertEquals(true, view.jTresultados.getMouseListeners().length == 3);

    }

}
