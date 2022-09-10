package br.com.salomaotech.genesys.controller.centro_custo;

import br.com.salomaotech.genesys.controller.centro_custo.CentroCustoMetodos;
import br.com.salomaotech.genesys.model.centro_custo.CentroCustoModelo;
import br.com.salomaotech.genesys.view.JFcentroCusto;
import br.com.salomaotech.sistema.jpa.Repository;
import br.com.salomaotech.sistema.swing.PopUp;
import org.junit.Test;
import static org.junit.Assert.*;

public class CentroCustoMetodosTest {

    private final JFcentroCusto view = new JFcentroCusto();
    private CentroCustoModelo centroCustoModelo = new CentroCustoModelo();
    private final CentroCustoMetodos centroCustoMetodos = new CentroCustoMetodos(view);

    public CentroCustoMetodosTest() {

        /* simula cadastro de centro de custo */
        new Repository(new CentroCustoModelo()).deleteTodos();
        centroCustoModelo.setCodigo("1.1");
        centroCustoModelo.setNome("Teste");
        new Repository(centroCustoModelo).save();

    }

    @Test
    public void testPopularFormulario() {

        /* popula os dados do modelo na view */
        centroCustoMetodos.popularFormulario(centroCustoModelo);

        /* testa se os dados populados são iguais aos dados no modelo */
        System.out.println("Testando classe CentroCustoMetodos metodo: popularFormulario");
        assertEquals(true, view.getId() == centroCustoModelo.getId());
        assertEquals(true, view.jTcadastroCodigo.getText().equals(centroCustoModelo.getCodigo()));
        assertEquals(true, view.jTcadastroNome.getText().equals(centroCustoModelo.getNome()));

    }

    @Test
    public void testResetarView() {

        /* reseta a view */
        centroCustoMetodos.resetarView();

        /* testa se os dados populados na view foram resetados */
        System.out.println("Testando classe CentroCustoMetodos metodo: resetarView");
        assertEquals(true, view.getId() == 0);
        assertEquals(true, view.jTcadastroCodigo.getText().equals(""));
        assertEquals(true, view.jTcadastroNome.getText().equals(""));

    }

    @Test
    public void testHabilitarCampos() {

        /* esperado que os campos abaixo estejam desabilitados */
        centroCustoMetodos.habilitarCampos();
        System.out.println("Testando classe CentroCustoMetodos metodo: habilitarCampos etapa 01");
        assertEquals(false, view.jBcadastroExcluir.isEnabled());

        /* esperado que os campos abaixo estejam habilitados */
        centroCustoMetodos.popularFormulario(centroCustoModelo);
        centroCustoMetodos.habilitarCampos();
        System.out.println("Testando classe CentroCustoMetodos metodo: habilitarCampos etapa 02");
        assertEquals(true, view.jBcadastroExcluir.isEnabled());

    }

    @Test
    public void testAddPopUpMenu() {

        /* adiciona menu de popup */
        centroCustoMetodos.addPopUpMenu();

        /* testa se o menu de popup foi adicionado */
        PopUp popUp = new PopUp();
        System.out.println("Testando classe CentroCustoMetodos metodo: addPopUpMenu");
        assertEquals(true, popUp.isMenuPopUpAdicionado(view.jTcadastroCodigo));
        assertEquals(true, popUp.isMenuPopUpAdicionado(view.jTcadastroNome));
        assertEquals(true, popUp.isMenuPopUpAdicionado(view.jTpesquisaNome));

    }

    @Test
    public void testAbrirCadastro() {

        /* abre o cadastro já realizado no construtor */
        centroCustoMetodos.abrirCadastro(centroCustoModelo.getId());

        /* testa se os dados populados são iguais aos dados no modelo */
        System.out.println("Testando classe CentroCustoMetodos metodo: abrirCadastro");
        assertEquals(true, view.getId() == centroCustoModelo.getId());
        assertEquals(true, view.jTcadastroCodigo.getText().equals(centroCustoModelo.getCodigo()));
        assertEquals(true, view.jTcadastroNome.getText().equals(centroCustoModelo.getNome()));

    }

    @Test
    public void testSalvar() {

        /* popula o formulário simulando o preenchimento dos dados */
        centroCustoMetodos.popularFormulario(centroCustoModelo);

        /* salva para que a ID possa ser gerada e popula novamente para pegar a ID de cadastro */
        centroCustoModelo = centroCustoMetodos.salvar();
        centroCustoMetodos.popularFormulario(centroCustoModelo);

        /* testa se os dados populados são iguais aos dados no modelo */
        System.out.println("Testando classe CentroCustoMetodos metodo: salvar");
        assertEquals(true, view.getId() == centroCustoModelo.getId());
        assertEquals(true, view.jTcadastroCodigo.getText().equals(centroCustoModelo.getCodigo()));
        assertEquals(true, view.jTcadastroNome.getText().equals(centroCustoModelo.getNome()));

    }

    @Test
    public void testExcluir() {

        /* popula os dados do modelo na view */
        centroCustoMetodos.popularFormulario(centroCustoModelo);

        /* testa exclusão */
        System.out.println("Testando classe CentroCustoMetodos metodo: excluir");
        assertEquals(true, centroCustoMetodos.excluir());

    }

    @Test
    public void testPesquisar() {

        /* usando filtro: nenhum */
        view.jTpesquisaNome.setText("");
        centroCustoMetodos.pesquisar();
        System.out.println("Testando classe CentroCustoMetodos metodo: pesquisar etapa 01");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: nome */
        view.jTpesquisaNome.setText(centroCustoModelo.getNome());
        centroCustoMetodos.pesquisar();
        System.out.println("Testando classe CentroCustoMetodos metodo: pesquisar etapa 02");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

    }

}
