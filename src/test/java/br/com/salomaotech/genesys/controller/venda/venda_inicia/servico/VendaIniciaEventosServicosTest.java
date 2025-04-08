package br.com.salomaotech.genesys.controller.venda.venda_inicia.servico;

import br.com.salomaotech.genesys.controller.venda.venda_inicia.VendaIniciaMetodosComum;
import br.com.salomaotech.genesys.model.venda.VendaModeloItem;
import br.com.salomaotech.genesys.view.JFvendaInicia;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListSelectionModel;
import org.junit.Test;
import static org.junit.Assert.*;

public class VendaIniciaEventosServicosTest {

    private final JFvendaInicia view = new JFvendaInicia();
    private final List<VendaModeloItem> vendaModeloItemListCompartilhado = new ArrayList();
    private final VendaModeloItem vendaModeloItemCompartilhado = new VendaModeloItem();

    private final VendaIniciaMetodosComum vendaIniciaMetodosComum = new VendaIniciaMetodosComum(view, vendaModeloItemListCompartilhado, vendaModeloItemCompartilhado);
    private final VendaIniciaMetodosServicos vendaIniciaMetodosServicos = new VendaIniciaMetodosServicos(view);
    private final VendaIniciaEventosServicos vendaIniciaEventosServicos = new VendaIniciaEventosServicos(view, vendaIniciaMetodosComum, vendaIniciaMetodosServicos);

    @Test
    public void testAddEventos() {

        /* adiciona eventos */
        vendaIniciaEventosServicos.addEventos();

        /* testa se os eventos foram adicionados */
        System.out.println("Testando classe VendaIniciaEventosServicos metodo: addEventos");
        assertEquals(1, view.jBservicoAdicionaItem.getActionListeners().length);
        assertEquals(1, view.jTpesquisaNomeServico.getKeyListeners().length);
        assertEquals(2, ((DefaultListSelectionModel) view.jTlistaDeServicos.getSelectionModel()).getListSelectionListeners().length);

    }

}
