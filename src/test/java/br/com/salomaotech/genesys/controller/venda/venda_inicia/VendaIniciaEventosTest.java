package br.com.salomaotech.genesys.controller.venda.venda_inicia;

import br.com.salomaotech.genesys.view.JFvendaInicia;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class VendaIniciaEventosTest {

    private final JFvendaInicia view = new JFvendaInicia();
    private final VendaIniciaEventos vendaIniciaEventos = new VendaIniciaEventos(view);

    public VendaIniciaEventosTest() {

    }

    @Test
    public void testSetVendaIniciaMetodos() {

    }

    @Test
    public void testSetComboBoxProduto() {

    }

    @Test
    public void testAddEventos() {

        vendaIniciaEventos.addEventos();

        System.out.println("Testando classe VendaIniciaEventos metodo: addEventos");
        assertEquals(true, view.jTprodutoQuantidade.getKeyListeners().length == 1);
        assertEquals(true, view.jTprodutoDesconto.getKeyListeners().length == 1);
        assertEquals(true, view.jBprodutoLimpaItem.getActionListeners().length == 1);
        assertEquals(true, view.jBprodutoAdicionaItem.getActionListeners().length == 1);
        assertEquals(true, view.jBvendaFinaliza.getActionListeners().length == 1);
        assertEquals(true, view.jBvendaExcluir.getActionListeners().length == 1);
        assertEquals(true, view.jTprodutoSelecionado.getMouseListeners().length == 3);
        assertEquals(true, view.jTprodutoSelecionado.getKeyListeners().length == 2);
        assertEquals(true, view.jBprodutoSelecionadoRemoverItem.getActionListeners().length == 1);
        assertEquals(true, view.getWindowListeners().length == 1);

    }

    @Test
    public void testExecutar() {

    }

}
