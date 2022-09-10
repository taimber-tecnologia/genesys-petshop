package br.com.salomaotech.genesys.controller.produto;

import br.com.salomaotech.genesys.controller.produto.ProdutoEventos;
import br.com.salomaotech.genesys.view.JFproduto;
import org.junit.Test;
import static org.junit.Assert.*;

public class ProdutoEventosTest {

    private final JFproduto view = new JFproduto();
    private final ProdutoEventos produtoEventos = new ProdutoEventos(view);

    @Test
    public void testSetComboBoxProdutoCategoriaCadastro() {

    }

    @Test
    public void testSetComboBoxProdutoCategoriaPesquisa() {

    }

    @Test
    public void testSetComboBoxFornecedores() {

    }

    @Test
    public void testSetProdutoMetodos() {

    }

    @Test
    public void testAddEventos() {

        /* adiciona eventos */
        produtoEventos.addEventos();

        /* testa se os eventos foram adicionados */
        System.out.println("Testando classe ProdutoEventos metodo: addEventos");
        assertEquals(true, view.jBcadastroSalvar.getActionListeners().length == 1);
        assertEquals(true, view.jBcadastroExcluir.getActionListeners().length == 1);
        assertEquals(true, view.jTresultados.getMouseListeners().length == 3);
        assertEquals(true, view.jBatalhoCadastro.getActionListeners().length == 1);
        assertEquals(true, view.jBatalhoPesquisa.getActionListeners().length == 1);
        assertEquals(true, view.jBpesquisa.getActionListeners().length == 1);
        assertEquals(true, view.jTpesquisaNome.getKeyListeners().length == 1);
        assertEquals(true, view.jBrefreshCategoria.getActionListeners().length == 1);
        assertEquals(true, view.jBrefreshPesquisaCategoria.getActionListeners().length == 1);
        assertEquals(true, view.jBrefreshFornecedor.getActionListeners().length == 1);
        assertEquals(true, view.jBpesquisaFornecedor.getActionListeners().length == 1);

    }

}
