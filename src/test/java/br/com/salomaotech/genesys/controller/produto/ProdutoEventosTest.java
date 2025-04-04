package br.com.salomaotech.genesys.controller.produto;

import br.com.salomaotech.genesys.model.produto.ComboBoxProdutoCategoria;
import br.com.salomaotech.genesys.view.JFproduto;
import javax.swing.JComboBox;
import org.junit.Test;
import static org.junit.Assert.*;

public class ProdutoEventosTest {

    private final JFproduto view = new JFproduto();
    private final ProdutoEventos produtoEventos = new ProdutoEventos(view);

    @Test
    public void testSetComboBoxProdutoCategoriaCadastro() {

        boolean isErro = false;

        try {

            produtoEventos.setComboBoxProdutoCategoriaCadastro(new ComboBoxProdutoCategoria(new JComboBox()));

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe ProdutoEventos metodo: setComboBoxProdutoCategoriaCadastro");
        assertEquals(false, isErro);

    }

    @Test
    public void testSetComboBoxProdutoCategoriaPesquisa() {

        boolean isErro = false;

        try {

            produtoEventos.setComboBoxProdutoCategoriaPesquisa(new ComboBoxProdutoCategoria(new JComboBox()));

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe ProdutoEventos metodo: setComboBoxProdutoCategoriaPesquisa");
        assertEquals(false, isErro);

    }

    @Test
    public void testSetComboBoxFornecedores() {

        boolean isErro = false;

        try {

            produtoEventos.setComboBoxProdutoCategoriaPesquisa(new ComboBoxProdutoCategoria(new JComboBox()));

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe ProdutoEventos metodo: setComboBoxProdutoCategoriaPesquisa");
        assertEquals(false, isErro);

    }

    @Test
    public void testSetProdutoMetodos() {

        boolean isErro = false;

        try {

            produtoEventos.setProdutoMetodos(new ProdutoMetodos(view));

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe ProdutoEventos metodo: setProdutoMetodos");
        assertEquals(false, isErro);

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
        assertEquals(true, view.jBadicionaFoto.getActionListeners().length == 1);
        assertEquals(true, view.jBremoveFoto.getActionListeners().length == 1);
        assertEquals(true, view.jBpaginador.getActionListeners().length == 1);

    }

}
