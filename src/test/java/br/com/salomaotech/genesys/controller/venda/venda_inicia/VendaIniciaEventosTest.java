package br.com.salomaotech.genesys.controller.venda.venda_inicia;

import br.com.salomaotech.genesys.model.venda.ComboBoxItemVenda;
import br.com.salomaotech.genesys.view.JFvendaInicia;
import javax.swing.JComboBox;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class VendaIniciaEventosTest {

    private final JFvendaInicia view = new JFvendaInicia();
    private final VendaIniciaEventos vendaIniciaEventos = new VendaIniciaEventos(view);

    @Test
    public void testSetVendaIniciaMetodos() {

        boolean isErro = false;

        try {

            vendaIniciaEventos.setVendaIniciaMetodos(new VendaIniciaMetodos(view));

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe VendaIniciaEventos metodo: setVendaIniciaMetodos");
        assertEquals(false, isErro);

    }

    @Test
    public void testSetComboBoxProduto() {

        boolean isErro = false;

        try {

            vendaIniciaEventos.setComboBoxItemVenda(new ComboBoxItemVenda(new JComboBox(), null));

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe VendaIniciaEventos metodo: setComboBoxItemVenda");
        assertEquals(false, isErro);

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
        assertEquals(true, view.jTitensSelecionados.getMouseListeners().length == 3);
        assertEquals(true, view.jTitensSelecionados.getKeyListeners().length == 2);
        assertEquals(true, view.jBprodutoSelecionadoRemoverItem.getActionListeners().length == 1);
        assertEquals(true, view.jBimprimir.getActionListeners().length == 1);
        assertEquals(true, view.jBcalcularGranel.getActionListeners().length == 1);

    }

    @Test
    public void testExecutar() {

        boolean isErro = false;

        try {

            vendaIniciaEventos.executar(null);

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe VendaIniciaEventos metodo: executar");
        assertEquals(false, isErro);

    }

}
