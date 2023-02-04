package br.com.salomaotech.genesys.controller.servico;

import br.com.salomaotech.genesys.model.servico.ServicoModelo;
import br.com.salomaotech.genesys.view.JFservico;
import br.com.salomaotech.sistema.jpa.Repository;
import br.com.salomaotech.sistema.swing.PopUp;
import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ServicoMetodosTest {

    private final JFservico view = new JFservico();
    private ServicoModelo servicoModelo = new ServicoModelo();
    private final ServicoMetodos servicoMetodos = new ServicoMetodos(view);

    public ServicoMetodosTest() {

        /* simula cadastro de servico */
        new Repository(new ServicoModelo()).deleteTodos();
        servicoModelo.setNome("Teste");
        servicoModelo.setDescricao("Teste ABC");
        servicoModelo.setValor(new BigDecimal(15));
        new Repository(servicoModelo).save();

    }

    @Test
    public void testPopularFormulario() {

        /* popula os dados do modelo na view */
        servicoMetodos.popularFormulario(servicoModelo);

        /* testa se os dados populados são iguais aos dados no modelo */
        System.out.println("Testando classe ServicoMetodos metodo: popularFormulario");
        assertEquals(true, view.getId() == servicoModelo.getId());
        assertEquals(true, view.jTnome.getText().equals(servicoModelo.getNome()));
        assertEquals(true, view.jTdescricao.getText().equals(servicoModelo.getDescricao()));
        assertEquals(true, view.jTvalor.getText().equals(servicoModelo.getValor().toString()));

    }

    @Test
    public void testResetarView() {

        /* reseta a view */
        servicoMetodos.resetarView();

        /* testa se os dados populados na view foram resetados */
        System.out.println("Testando classe ServicoMetodos metodo: resetarView");
        assertEquals(true, view.getId() == 0);
        assertEquals(true, view.jTnome.getText().equals(""));
        assertEquals(true, view.jTdescricao.getText().equals(""));
        assertEquals(true, view.jTvalor.getText().equals("0"));

    }

    @Test
    public void testHabilitarCampos() {

        /* é esperado que o botão de excluir esteja desabilitado */
        servicoMetodos.habilitarCampos();
        System.out.println("Testando classe ServicoMetodos metodo: habilitarCampos Etapa 01");
        assertEquals(false, view.jBcadastroExcluir.isEnabled());

        /* é esperado que o botão de excluir esteja habilitado */
        servicoMetodos.popularFormulario(servicoModelo);
        servicoMetodos.habilitarCampos();
        System.out.println("Testando classe ServicoMetodos metodo: habilitarCampos Etapa 02");
        assertEquals(true, view.jBcadastroExcluir.isEnabled());

    }

    @Test
    public void testAddPopUpMenu() {

        /* adiciona menu de popup */
        servicoMetodos.addPopUpMenu();

        /* testa se o menu de popup foi adicionado */
        PopUp popUp = new PopUp();
        System.out.println("Testando classe ServicoMetodos metodo: addPopUpMenu");
        assertEquals(true, popUp.isMenuPopUpAdicionado(view.jTnome));
        assertEquals(true, popUp.isMenuPopUpAdicionado(view.jTdescricao));
        assertEquals(true, popUp.isMenuPopUpAdicionado(view.jTvalor));
        assertEquals(true, popUp.isMenuPopUpAdicionado(view.jTpesquisaNome));

    }

    @Test
    public void testAbrirCadastro() {

        /* abre o cadastro já realizado no construtor */
        servicoMetodos.abrirCadastro(servicoModelo.getId());

        /* testa se os dados populados são iguais aos dados no modelo */
        System.out.println("Testando classe ServicoMetodos metodo: abrirCadastro");
        assertEquals(true, view.getId() == servicoModelo.getId());
        assertEquals(true, view.jTnome.getText().equals(servicoModelo.getNome()));
        assertEquals(true, view.jTdescricao.getText().equals(servicoModelo.getDescricao()));
        assertEquals(true, view.jTvalor.getText().equals(servicoModelo.getValor().toString()));

    }

    @Test
    public void testPesquisar() {

        /* usando filtro: nenhum */
        view.jTpesquisaNome.setText(null);
        servicoMetodos.pesquisar();
        System.out.println("Testando classe ServicoMetodos metodo: pesquisar etapa 01");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: nome */
        view.jTpesquisaNome.setText(servicoModelo.getNome());
        servicoMetodos.pesquisar();
        System.out.println("Testando classe ServicoMetodos metodo: pesquisar etapa 02");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: nome nao existe */
        view.jTpesquisaNome.setText("NomeNaoExiste");
        servicoMetodos.pesquisar();
        System.out.println("Testando classe ServicoMetodos metodo: pesquisar etapa 03");
        assertEquals(true, view.jTresultados.getRowCount() == 0);

    }

    @Test
    public void testSalvar() {

        /* popula o formulário simulando o preenchimento dos dados */
        servicoMetodos.popularFormulario(servicoModelo);

        /* salva para que a ID possa ser gerada e popula novamente para pegar a ID de cadastro */
        servicoModelo = servicoMetodos.salvar();
        servicoMetodos.popularFormulario(servicoModelo);

        /* testa se os dados populados são iguais aos dados no modelo */
        System.out.println("Testando classe ServicoMetodos metodo: salvar");
        assertEquals(true, view.getId() == servicoModelo.getId());
        assertEquals(true, view.jTnome.getText().equals(servicoModelo.getNome()));
        assertEquals(true, view.jTdescricao.getText().equals(servicoModelo.getDescricao()));
        assertEquals(true, view.jTvalor.getText().equals(servicoModelo.getValor().toString()));

    }

    @Test
    public void testExcluir() {

        /* popula os dados do modelo na view */
        servicoMetodos.popularFormulario(servicoModelo);

        /* testa exclusão */
        System.out.println("Testando classe ServicoMetodos metodo: excluir");
        assertEquals(true, servicoMetodos.excluir());

    }

}
