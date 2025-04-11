package br.com.salomaotech.genesys.controller.produto;

import br.com.salomaotech.genesys.model.produto.ProdutoModelo;
import br.com.salomaotech.genesys.view.JFproduto;
import br.com.salomaotech.sistema.jpa.Repository;
import br.com.salomaotech.sistema.swing.PopUp;
import java.math.BigDecimal;
import org.junit.Test;
import static org.junit.Assert.*;

public class ProdutoMetodosTest {

    private final JFproduto view = new JFproduto();
    private ProdutoModelo produtoModelo = new ProdutoModelo();
    private ProdutoMetodos produtoMetodos = new ProdutoMetodos(view);

    public ProdutoMetodosTest() {

        /* simula cadastro de produtos */
        new Repository(new ProdutoModelo()).deleteTodos();
        produtoModelo.setNome("Teste");
        produtoModelo.setValorVenda(new BigDecimal(0));
        produtoModelo.setDescricao("Teste descrição");
        produtoModelo.setCategoria("Teste categoria");
        produtoModelo.setQuantidade(new BigDecimal(0));
        produtoModelo.setPeso(new BigDecimal(10));
        produtoModelo.setMedida("UNID");
        new Repository(produtoModelo).save();

    }

    @Test
    public void testPopularFormulario() {

        /* popula os dados do modelo na view */
        produtoMetodos.popularFormulario(produtoModelo);

        /* testa se os dados populados são iguais aos dados no modelo */
        System.out.println("Testando classe ProdutoMetodos metodo: popularFormulario");
        assertEquals(true, view.getId() == produtoModelo.getId());
        assertEquals(true, view.jTnome.getText().equals(produtoModelo.getNome()));
        assertEquals(true, view.jTvalorVenda.getText().equals(produtoModelo.getValorVenda().toString()));
        assertEquals(true, view.jTdescricao.getText().equals(produtoModelo.getDescricao()));
        assertEquals(true, view.jCcategoria.getEditor().getItem().equals(produtoModelo.getCategoria()));
        assertEquals(true, view.jTquantidade.getText().equals(produtoModelo.getQuantidade().toString()));
        assertEquals(true, view.jTpeso.getText().equals(produtoModelo.getPeso().toString()));
        assertEquals(true, view.jCmedida.getSelectedItem().equals(produtoModelo.getMedida()));

    }

    @Test
    public void testResetarView() {

        /* reseta a view */
        produtoMetodos.resetarView();

        /* testa se os dados populados na view foram resetados */
        System.out.println("Testando classe ProdutoMetodos metodo: resetarView");
        assertEquals(true, view.getId() == 0);
        assertEquals(true, view.jTnome.getText().equals(""));
        assertEquals(true, view.jTvalorVenda.getText().equals("0"));
        assertEquals(true, view.jTdescricao.getText().equals(""));
        assertEquals(true, view.jCcategoria.getEditor().getItem().equals(""));
        assertEquals(true, view.jTquantidade.getText().equals("0"));
        assertEquals(true, view.jTpeso.getText().equals("0"));
        assertEquals(true, view.jCmedida.getSelectedItem().equals("UNID"));

    }

    @Test
    public void testResetarViewPesquisa() {

        /* reseta a view */
        produtoMetodos.resetarViewPesquisa();

        /* testa se os dados populados na view foram resetados */
        System.out.println("Testando classe ProdutoMetodos metodo: resetarViewPesquisa");
        assertEquals(true, view.jTpesquisaNome.getText().equals(""));
        assertEquals(true, view.jCpesquisaCategoria.getSelectedIndex() == -1);
        assertEquals(true, view.jCpaginador.getSelectedIndex() == 0);

    }

    @Test
    public void testHabilitarCampos() {

        /* é esperado que alguns campos estejam desabilitados */
        produtoMetodos.habilitarCampos();

        /* testa se os campos estão desabilitados */
        System.out.println("Testando classe ProdutoMetodos metodo: habilitarCampos etapa 1");
        assertEquals(false, view.jBcadastroExcluir.isEnabled());
        assertEquals(false, view.jBadicionaFoto.isEnabled());
        assertEquals(false, view.jBremoveFoto.isEnabled());

        /* é esperado que alguns campos estejam habilitados */
        produtoMetodos.popularFormulario(produtoModelo);
        produtoMetodos.habilitarCampos();

        /* é esperado que alguns campos estejam habilitados */
        System.out.println("Testando classe ProdutoMetodos metodo: habilitarCampos etapa 2");
        assertEquals(true, view.jBcadastroExcluir.isEnabled());
        assertEquals(true, view.jBadicionaFoto.isEnabled());
        assertEquals(true, view.jBremoveFoto.isEnabled());

    }

    @Test
    public void testAddPopUpMenu() {

        /* adiciona menu de popup */
        produtoMetodos.addPopUpMenu();
        PopUp popUp = new PopUp();

        /* testa se os eventos de pop menu foram adicionados */
        System.out.println("Testando classe ProdutoMetodos metodo: addPopUpMenu");
        assertEquals(true, popUp.isMenuPopUpAdicionado(view.jTnome));
        assertEquals(true, popUp.isMenuPopUpAdicionado(view.jTvalorVenda));
        assertEquals(true, popUp.isMenuPopUpAdicionado(view.jTdescricao));
        assertEquals(true, popUp.isMenuPopUpAdicionado(view.jTquantidade));
        assertEquals(true, popUp.isMenuPopUpAdicionado(view.jTpeso));
        assertEquals(true, popUp.isMenuPopUpAdicionado(view.jTpesquisaNome));

    }

    @Test
    public void testAbrirCadastro() {

        /* abre o cadastro já realizado no construtor */
        produtoMetodos.abrirCadastro(produtoModelo.getId());

        /* testa se os dados populados são iguais aos dados no modelo */
        System.out.println("Testando classe ProdutoMetodos metodo: abrirCadastro");
        assertEquals(true, view.getId() == produtoModelo.getId());
        assertEquals(true, view.jTnome.getText().equals(produtoModelo.getNome()));
        assertEquals(true, view.jTvalorVenda.getText().equals(produtoModelo.getValorVenda().toString()));
        assertEquals(true, view.jTdescricao.getText().equals(produtoModelo.getDescricao()));
        assertEquals(true, view.jCcategoria.getEditor().getItem().equals(produtoModelo.getCategoria()));
        assertEquals(true, view.jTquantidade.getText().equals(produtoModelo.getQuantidade().toString()));
        assertEquals(true, view.jTpeso.getText().equals(produtoModelo.getPeso().toString()));
        assertEquals(true, view.jCmedida.getSelectedItem().equals(produtoModelo.getMedida()));

        /* foto do produto */
        assertEquals(true, view.jPdadosPerfilFoto.getComponents().length == 0);

    }

    @Test
    public void testSalvar() {

        /* popula o formulário com os dados */
        produtoMetodos.popularFormulario(produtoModelo);

        /* salva para que a ID possa ser gerada e popula novamente para pegar a ID de cadastro */
        produtoModelo = produtoMetodos.salvar();
        produtoMetodos.popularFormulario(produtoModelo);

        /* testa se os dados populados são iguais aos dados no modelo */
        System.out.println("Testando classe ProdutoMetodos metodo: salvar");
        assertEquals(true, view.getId() == produtoModelo.getId());
        assertEquals(true, view.jTnome.getText().equals(produtoModelo.getNome()));
        assertEquals(true, view.jTvalorVenda.getText().equals(produtoModelo.getValorVenda().toString()));
        assertEquals(true, view.jTdescricao.getText().equals(produtoModelo.getDescricao()));
        assertEquals(true, view.jCcategoria.getEditor().getItem().equals(produtoModelo.getCategoria()));
        assertEquals(true, view.jTquantidade.getText().equals(produtoModelo.getQuantidade().toString()));
        assertEquals(true, view.jTpeso.getText().equals(produtoModelo.getPeso().toString()));
        assertEquals(true, view.jCmedida.getSelectedItem().equals(produtoModelo.getMedida()));

    }

    @Test
    public void testExcluir() {

        /* popula os dados do modelo na view */
        produtoMetodos.popularFormulario(produtoModelo);

        /* testa exclusão */
        System.out.println("Testando classe ProdutoMetodos metodo: excluir");
        assertEquals(true, produtoMetodos.excluir());

    }

    @Test
    public void testPesquisar() {

        /* utilizando filtro: nenhum */
        view.jTpesquisaNome.setText(null);
        view.jCpesquisaCategoria.getEditor().setItem("");
        produtoMetodos = new ProdutoMetodos(view);
        produtoMetodos.pesquisar();
        System.out.println("Testando classe ProdutoMetodos metodo: pesquisar etapa 01");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* utilizando filtro: nome */
        view.jTpesquisaNome.setText(produtoModelo.getNome());
        view.jCpesquisaCategoria.getEditor().setItem("");
        produtoMetodos = new ProdutoMetodos(view);
        produtoMetodos.pesquisar();
        System.out.println("Testando classe ProdutoMetodos metodo: pesquisar etapa 02");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* utilizando filtro: categoria */
        view.jTpesquisaNome.setText(null);
        view.jCpesquisaCategoria.getEditor().setItem(produtoModelo.getCategoria());
        produtoMetodos = new ProdutoMetodos(view);
        produtoMetodos.pesquisar();
        System.out.println("Testando classe ProdutoMetodos metodo: pesquisar etapa 03");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* utilizando filtro: todos */
        view.jTpesquisaNome.setText(produtoModelo.getNome());
        view.jCpesquisaCategoria.getEditor().setItem(produtoModelo.getCategoria());
        produtoMetodos = new ProdutoMetodos(view);
        produtoMetodos.pesquisar();
        System.out.println("Testando classe ProdutoMetodos metodo: pesquisar etapa 04");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

    }

}
