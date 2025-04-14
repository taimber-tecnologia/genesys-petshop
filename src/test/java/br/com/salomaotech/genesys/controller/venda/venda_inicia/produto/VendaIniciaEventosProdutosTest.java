package br.com.salomaotech.genesys.controller.venda.venda_inicia.produto;

import br.com.salomaotech.genesys.controller.venda.venda_inicia.VendaIniciaMetodosComum;
import br.com.salomaotech.genesys.model.venda.VendaModeloItem;
import br.com.salomaotech.genesys.view.JFvendaInicia;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListSelectionModel;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class VendaIniciaEventosProdutosTest {

    private final JFvendaInicia view = new JFvendaInicia();
    private final List<VendaModeloItem> vendaModeloItemListCompartilhado = new ArrayList();
    private final VendaModeloItem vendaModeloItemCompartilhado = new VendaModeloItem();

    private final VendaIniciaMetodosComum vendaIniciaMetodosComum = new VendaIniciaMetodosComum(view, vendaModeloItemListCompartilhado, vendaModeloItemCompartilhado);
    private final VendaIniciaMetodosProdutos vendaIniciaMetodosProdutos = new VendaIniciaMetodosProdutos(view);
    private final VendaIniciaEventosProdutos vendaIniciaEventosProdutos = new VendaIniciaEventosProdutos(view, vendaIniciaMetodosComum, vendaIniciaMetodosProdutos);

    @Test
    public void testAddEventos() {

        /* adiciona eventos */
        vendaIniciaEventosProdutos.addEventos();

        /* testa se os eventos foram adicionados */
        System.out.println("Testando classe VendaIniciaEventosProdutos metodo: addEventos");
        assertEquals(1, view.jBprodutoAdicionaItem.getActionListeners().length);
        assertEquals(1, view.jBcalcularGranel.getActionListeners().length);
        assertEquals(1, view.jTpesquisaNomeProduto.getKeyListeners().length);
        assertEquals(2, ((DefaultListSelectionModel) view.jTlistaDeProdutos.getSelectionModel()).getListSelectionListeners().length);
        assertEquals(3, view.jTlistaDeProdutos.getMouseListeners().length);

    }

}
