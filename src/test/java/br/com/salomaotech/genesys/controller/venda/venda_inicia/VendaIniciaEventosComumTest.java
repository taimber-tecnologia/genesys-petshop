package br.com.salomaotech.genesys.controller.venda.venda_inicia;

import br.com.salomaotech.genesys.model.venda.VendaModeloItem;
import br.com.salomaotech.genesys.view.JFvendaInicia;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListSelectionModel;
import org.junit.Test;
import static org.junit.Assert.*;

public class VendaIniciaEventosComumTest {

    private final JFvendaInicia view = new JFvendaInicia();
    private final VendaModeloItem vendaModeloItem = new VendaModeloItem();
    private final List<VendaModeloItem> vendaModeloItemListCompartilhado = new ArrayList();
    private final VendaIniciaMetodosComum metodos = new VendaIniciaMetodosComum(view, vendaModeloItemListCompartilhado, vendaModeloItem);
    private final VendaIniciaEventosComum eventos = new VendaIniciaEventosComum(view, metodos, vendaModeloItem);

    @Test
    public void testAddEventos() {

        /* adiciona eventos */
        eventos.addEventos();

        /* testa se os eventos foram adicionados */
        System.out.println("Testando classe VendaIniciaEventosComum método: addEventos");
        assertEquals(1, view.jBremoveItemSelecionadoLista.getActionListeners().length);
        assertEquals(1, view.jBvendaFinaliza.getActionListeners().length);
        assertEquals(1, view.jBvendaExcluir.getActionListeners().length);
        assertEquals(1, view.jBatalhoCadastro.getActionListeners().length);
        assertEquals(1, view.jBatalhoPesquisa.getActionListeners().length);
        assertEquals(1, view.jBimprimir.getActionListeners().length);
        assertEquals(1, view.jTitemQuantidade.getKeyListeners().length);
        assertEquals(2, ((DefaultListSelectionModel) view.jTitensSelecionados.getSelectionModel()).getListSelectionListeners().length);
        assertEquals(2, ((DefaultListSelectionModel) view.jTlistaDeProdutos.getSelectionModel()).getListSelectionListeners().length);
        assertEquals(2, ((DefaultListSelectionModel) view.jTlistaDeServicos.getSelectionModel()).getListSelectionListeners().length);

    }

}
