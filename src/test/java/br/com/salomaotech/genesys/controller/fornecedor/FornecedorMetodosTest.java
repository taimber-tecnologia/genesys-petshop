package br.com.salomaotech.genesys.controller.fornecedor;

import br.com.salomaotech.genesys.model.fornecedor.FornecedorModelo;
import br.com.salomaotech.genesys.view.JFfornecedor;
import br.com.salomaotech.sistema.jpa.Repository;
import br.com.salomaotech.sistema.swing.PopUp;
import org.junit.Test;
import static org.junit.Assert.*;

public class FornecedorMetodosTest {

    private final JFfornecedor view = new JFfornecedor();
    private FornecedorModelo fornecedorModelo = new FornecedorModelo();
    private final FornecedorMetodos fornecedorMetodos = new FornecedorMetodos(view);

    public FornecedorMetodosTest() {

        /* simula cadastro de fornecedor */
        new Repository(new FornecedorModelo()).deleteTodos();
        fornecedorModelo.setNome("Teste");
        fornecedorModelo.setCnpj("00.000.000/0001-00");
        fornecedorModelo.setCep("75370-000");
        fornecedorModelo.setRua("01");
        fornecedorModelo.setQuadra("02");
        fornecedorModelo.setLote("03");
        fornecedorModelo.setNumero("04");
        fornecedorModelo.setUf("GO");
        fornecedorModelo.setBairro("A");
        fornecedorModelo.setCidade("B");
        fornecedorModelo.setComplemento("C");
        fornecedorModelo.setTelefone("62 0000-0000");
        fornecedorModelo.setEmail("abc123@email.com");
        fornecedorModelo.setContato("Fulano de tal");
        new Repository(fornecedorModelo).save();

    }

    @Test
    public void testPopularFormulario() {

        /* popula os dados do modelo na view */
        fornecedorMetodos.popularFormulario(fornecedorModelo);

        /* testa se os dados populados são iguais aos dados no modelo */
        System.out.println("Testando classe FornecedorMetodos metodo: popularFormulario");
        assertEquals(true, view.getId() == fornecedorModelo.getId());
        assertEquals(true, view.jTbasicoNome.getText().equals(fornecedorModelo.getNome()));
        assertEquals(true, view.jFbasicoCnpj.getText().equals(fornecedorModelo.getCnpj()));
        assertEquals(true, view.jFenderecoCep.getText().equals(fornecedorModelo.getCep()));
        assertEquals(true, view.jTenderecoRua.getText().equals(fornecedorModelo.getRua()));
        assertEquals(true, view.jTenderecoQuadra.getText().equals(fornecedorModelo.getQuadra()));
        assertEquals(true, view.jTenderecoLote.getText().equals(fornecedorModelo.getLote()));
        assertEquals(true, view.jTenderecoNumero.getText().equals(fornecedorModelo.getNumero()));
        assertEquals(true, view.jCenderecoUf.getSelectedItem().toString().equals(fornecedorModelo.getUf()));
        assertEquals(true, view.jTenderecoBairro.getText().equals(fornecedorModelo.getBairro()));
        assertEquals(true, view.jTenderecoCidade.getText().equals(fornecedorModelo.getCidade()));
        assertEquals(true, view.jTenderecoComplemento.getText().equals(fornecedorModelo.getComplemento()));
        assertEquals(true, view.jTcontatoTelefone.getText().equals(fornecedorModelo.getTelefone()));
        assertEquals(true, view.jTcontatoEmail.getText().equals(fornecedorModelo.getEmail()));
        assertEquals(true, view.jTcontato.getText().equals(fornecedorModelo.getContato()));

    }

    @Test
    public void testResetarView() {

        /* reseta a view */
        fornecedorMetodos.resetarView();

        /* testa se os dados populados na view foram resetados */
        System.out.println("Testando classe FornecedorMetodos metodo: resetarView");
        assertEquals(true, view.getId() == 0);
        assertEquals(true, view.jTbasicoNome.getText().equals(""));
        assertEquals(true, view.jFbasicoCnpj.getText().equals("  .   .   /    -  "));
        assertEquals(true, view.jFenderecoCep.getText().equals("     -   "));
        assertEquals(true, view.jTenderecoRua.getText().equals(""));
        assertEquals(true, view.jTenderecoQuadra.getText().equals(""));
        assertEquals(true, view.jTenderecoLote.getText().equals(""));
        assertEquals(true, view.jTenderecoNumero.getText().equals(""));
        assertEquals(true, view.jCenderecoUf.getSelectedIndex() == 0);
        assertEquals(true, view.jTenderecoBairro.getText().equals(""));
        assertEquals(true, view.jTenderecoCidade.getText().equals(""));
        assertEquals(true, view.jTenderecoComplemento.getText().equals(""));
        assertEquals(true, view.jTcontatoTelefone.getText().equals(""));
        assertEquals(true, view.jTcontatoEmail.getText().equals(""));
        assertEquals(true, view.jTcontato.getText().equals(""));

    }

    @Test
    public void testHabilitarCampos() {

        /* é esperado que alguns campos estejam desabilitados */
        fornecedorMetodos.habilitarCampos();

        /* testa se os campos estão desabilitados */
        System.out.println("Testando classe FornecedorMetodos metodo: habilitarCampos Etapa 01");
        assertEquals(false, view.jBcadastroExcluir.isEnabled());

        /* é esperado que alguns campos estejam habilitados */
        fornecedorMetodos.popularFormulario(fornecedorModelo);
        fornecedorMetodos.habilitarCampos();

        /* é esperado que alguns campos estejam habilitados */
        System.out.println("Testando classe FornecedorMetodos metodo: habilitarCampos Etapa 02");
        assertEquals(true, view.jBcadastroExcluir.isEnabled());

    }

    @Test
    public void testAddPopUpMenu() {

        /* adiciona menu de popup */
        fornecedorMetodos.addPopUpMenu();
        PopUp popUp = new PopUp();

        /* testa se os eventos de pop menu foram adicionados */
        System.out.println("Testando classe FornecedorMetodos metodo: addPopUpMenu");
        assertEquals(true, popUp.isMenuPopUpAdicionado(view.jTbasicoNome));
        assertEquals(true, popUp.isMenuPopUpAdicionado(view.jFbasicoCnpj));
        assertEquals(true, popUp.isMenuPopUpAdicionado(view.jFenderecoCep));
        assertEquals(true, popUp.isMenuPopUpAdicionado(view.jTenderecoRua));
        assertEquals(true, popUp.isMenuPopUpAdicionado(view.jTenderecoQuadra));
        assertEquals(true, popUp.isMenuPopUpAdicionado(view.jTenderecoLote));
        assertEquals(true, popUp.isMenuPopUpAdicionado(view.jTenderecoNumero));
        assertEquals(true, popUp.isMenuPopUpAdicionado(view.jTenderecoBairro));
        assertEquals(true, popUp.isMenuPopUpAdicionado(view.jTenderecoCidade));
        assertEquals(true, popUp.isMenuPopUpAdicionado(view.jTenderecoComplemento));
        assertEquals(true, popUp.isMenuPopUpAdicionado(view.jTcontatoTelefone));
        assertEquals(true, popUp.isMenuPopUpAdicionado(view.jTcontatoEmail));
        assertEquals(true, popUp.isMenuPopUpAdicionado(view.jTpesquisaNome));
        assertEquals(true, popUp.isMenuPopUpAdicionado(view.jTpesquisaCnpj));
        assertEquals(true, popUp.isMenuPopUpAdicionado(view.jTcontato));

    }

    @Test
    public void testAbrirCadastro() {

        /* abre o cadastro já realizado no construtor */
        fornecedorMetodos.abrirCadastro(fornecedorModelo.getId());

        /* testa se os dados populados são iguais aos dados no modelo */
        System.out.println("Testando classe FornecedorMetodos metodo: abrirCadastro");
        assertEquals(true, view.getId() == fornecedorModelo.getId());
        assertEquals(true, view.jTbasicoNome.getText().equals(fornecedorModelo.getNome()));
        assertEquals(true, view.jFbasicoCnpj.getText().equals(fornecedorModelo.getCnpj()));
        assertEquals(true, view.jFenderecoCep.getText().equals(fornecedorModelo.getCep()));
        assertEquals(true, view.jTenderecoRua.getText().equals(fornecedorModelo.getRua()));
        assertEquals(true, view.jTenderecoQuadra.getText().equals(fornecedorModelo.getQuadra()));
        assertEquals(true, view.jTenderecoLote.getText().equals(fornecedorModelo.getLote()));
        assertEquals(true, view.jTenderecoNumero.getText().equals(fornecedorModelo.getNumero()));
        assertEquals(true, view.jCenderecoUf.getSelectedItem().toString().equals(fornecedorModelo.getUf()));
        assertEquals(true, view.jTenderecoBairro.getText().equals(fornecedorModelo.getBairro()));
        assertEquals(true, view.jTenderecoCidade.getText().equals(fornecedorModelo.getCidade()));
        assertEquals(true, view.jTenderecoComplemento.getText().equals(fornecedorModelo.getComplemento()));
        assertEquals(true, view.jTcontatoTelefone.getText().equals(fornecedorModelo.getTelefone()));
        assertEquals(true, view.jTcontatoEmail.getText().equals(fornecedorModelo.getEmail()));
        assertEquals(true, view.jTcontato.getText().equals(fornecedorModelo.getContato()));

    }

    @Test
    public void testPesquisar() {

        /* usando filtro: nenhum */
        view.jTpesquisaNome.setText(null);
        view.jTpesquisaCnpj.setText(null);
        fornecedorMetodos.pesquisar();
        System.out.println("Testando classe FornecedorMetodos metodo: pesquisar etapa 01");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: nome */
        view.jTpesquisaNome.setText(fornecedorModelo.getNome());
        view.jTpesquisaCnpj.setText(null);
        fornecedorMetodos.pesquisar();
        System.out.println("Testando classe FornecedorMetodos metodo: pesquisar etapa 02");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: cnpj */
        view.jTpesquisaNome.setText(null);
        view.jTpesquisaCnpj.setText(fornecedorModelo.getCnpj());
        fornecedorMetodos.pesquisar();
        System.out.println("Testando classe FornecedorMetodos metodo: pesquisar etapa 03");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: todos */
        view.jTpesquisaNome.setText(fornecedorModelo.getNome());
        view.jTpesquisaCnpj.setText(fornecedorModelo.getCnpj());
        fornecedorMetodos.pesquisar();
        System.out.println("Testando classe FornecedorMetodos metodo: pesquisar etapa 04");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

    }

    @Test
    public void testSalvar() {

        /* popula o formulário simulando o preenchimento dos dados */
        fornecedorMetodos.popularFormulario(fornecedorModelo);

        /* salva para que a ID possa ser gerada e popula novamente para pegar a ID de cadastro */
        fornecedorModelo = fornecedorMetodos.salvar();
        fornecedorMetodos.popularFormulario(fornecedorModelo);

        /* testa se os dados populados são iguais aos dados no modelo */
        System.out.println("Testando classe FornecedorMetodos metodo: salvar");
        assertEquals(true, view.getId() == fornecedorModelo.getId());
        assertEquals(true, view.jTbasicoNome.getText().equals(fornecedorModelo.getNome()));
        assertEquals(true, view.jFbasicoCnpj.getText().equals(fornecedorModelo.getCnpj()));
        assertEquals(true, view.jFenderecoCep.getText().equals(fornecedorModelo.getCep()));
        assertEquals(true, view.jTenderecoRua.getText().equals(fornecedorModelo.getRua()));
        assertEquals(true, view.jTenderecoQuadra.getText().equals(fornecedorModelo.getQuadra()));
        assertEquals(true, view.jTenderecoLote.getText().equals(fornecedorModelo.getLote()));
        assertEquals(true, view.jTenderecoNumero.getText().equals(fornecedorModelo.getNumero()));
        assertEquals(true, view.jCenderecoUf.getSelectedItem().toString().equals(fornecedorModelo.getUf()));
        assertEquals(true, view.jTenderecoBairro.getText().equals(fornecedorModelo.getBairro()));
        assertEquals(true, view.jTenderecoCidade.getText().equals(fornecedorModelo.getCidade()));
        assertEquals(true, view.jTenderecoComplemento.getText().equals(fornecedorModelo.getComplemento()));
        assertEquals(true, view.jTcontatoTelefone.getText().equals(fornecedorModelo.getTelefone()));
        assertEquals(true, view.jTcontatoEmail.getText().equals(fornecedorModelo.getEmail()));
        assertEquals(true, view.jTcontato.getText().equals(fornecedorModelo.getContato()));

    }

    @Test
    public void testExcluir() {

        /* popula os dados do modelo na view */
        fornecedorMetodos.popularFormulario(fornecedorModelo);

        /* testa exclusão */
        System.out.println("Testando classe FornecedorMetodos metodo: excluir");
        assertEquals(true, fornecedorMetodos.excluir());

    }

}
