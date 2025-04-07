package br.com.salomaotech.genesys.controller.venda.venda_calcula;

import br.com.salomaotech.genesys.controller.venda.venda_inicia.VendaIniciaMetodos;
import br.com.salomaotech.genesys.model.venda.ItemVenda;
import br.com.salomaotech.genesys.view.JFvendaInicia;
import org.junit.Test;
import static org.junit.Assert.*;

public class VendaCalculaControllerProdutosTest {

    private final JFvendaInicia view = new JFvendaInicia();
    private final VendaIniciaMetodos vendaIniciaMetodos = new VendaIniciaMetodos(view);
    private final VendaCalculaControllerProdutos vendaCalculaController;

    public VendaCalculaControllerProdutosTest() {

        vendaCalculaController = new VendaCalculaControllerProdutos(new ItemVenda(), vendaIniciaMetodos);

    }

    @Test
    public void testConstruir() {

        boolean isErro = false;

        try {

            vendaCalculaController.construir();

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe VendaCalculaController metodo: construir");
        assertEquals(false, isErro);

    }

}
