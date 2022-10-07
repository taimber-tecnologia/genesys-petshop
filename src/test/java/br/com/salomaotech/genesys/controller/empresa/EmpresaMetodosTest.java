package br.com.salomaotech.genesys.controller.empresa;

import br.com.salomaotech.genesys.model.empresa.EmpresaModelo;
import br.com.salomaotech.genesys.view.JFempresa;
import br.com.salomaotech.sistema.jpa.Repository;
import br.com.salomaotech.sistema.swing.PopUp;
import org.junit.Test;
import static org.junit.Assert.*;

public class EmpresaMetodosTest {

    private final JFempresa view = new JFempresa();
    private EmpresaModelo empresaModelo = new EmpresaModelo();
    private final EmpresaMetodos empresaMetodos = new EmpresaMetodos(view);

    public EmpresaMetodosTest() {

        /* simula cadastro de empresa */
        new Repository(new EmpresaModelo()).deleteTodos();
        empresaModelo.setNome("Teste");
        empresaModelo.setCnpj("78.498.603/0001-52");
        empresaModelo.setCep("75370-000");
        empresaModelo.setRua("01");
        empresaModelo.setQuadra("02");
        empresaModelo.setLote("03");
        empresaModelo.setNumero("04");
        empresaModelo.setUf("GO");
        empresaModelo.setBairro("A");
        empresaModelo.setCidade("B");
        empresaModelo.setComplemento("C");
        empresaModelo.setTelefone("62 0000-0000");
        empresaModelo.setEmail("abc123@email.com");

    }

    @Test
    public void testPopularFormulario() {

        /* popula os dados do modelo na view */
        empresaMetodos.popularFormulario(empresaModelo);

        /* testa se os dados populados são iguais aos dados no modelo */
        System.out.println("Testando classe EmpresaMetodos metodo: popularFormulario");
        assertEquals(true, view.getId() == empresaModelo.getId());
        assertEquals(true, view.jTbasicoNome.getText().equals(empresaModelo.getNome()));
        assertEquals(true, view.jFbasicoCnpj.getText().equals(empresaModelo.getCnpj()));
        assertEquals(true, view.jFenderecoCep.getText().equals(empresaModelo.getCep()));
        assertEquals(true, view.jTenderecoRua.getText().equals(empresaModelo.getRua()));
        assertEquals(true, view.jTenderecoQuadra.getText().equals(empresaModelo.getQuadra()));
        assertEquals(true, view.jTenderecoLote.getText().equals(empresaModelo.getLote()));
        assertEquals(true, view.jTenderecoNumero.getText().equals(empresaModelo.getNumero()));
        assertEquals(true, view.jCenderecoUf.getSelectedItem().toString().equals(empresaModelo.getUf()));
        assertEquals(true, view.jTenderecoBairro.getText().equals(empresaModelo.getBairro()));
        assertEquals(true, view.jTenderecoCidade.getText().equals(empresaModelo.getCidade()));
        assertEquals(true, view.jTenderecoComplemento.getText().equals(empresaModelo.getComplemento()));
        assertEquals(true, view.jTcontatoTelefone.getText().equals(empresaModelo.getTelefone()));
        assertEquals(true, view.jTcontatoEmail.getText().equals(empresaModelo.getEmail()));

    }

    @Test
    public void testResetarView() {

        /* reseta a view */
        empresaMetodos.resetarView();

        /* testa se os dados populados na view foram resetados */
        System.out.println("Testando classe EmpresaMetodos metodo: resetarView");
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

    }

    @Test
    public void testHabilitarCampos() {

        /* é esperado que alguns campos estejam desabilitados */
        empresaMetodos.habilitarCampos();
        System.out.println("Testando classe EmpresaMetodos metodo: habilitarCampos Etapa 01");
        assertEquals(false, view.jBcadastroExcluir.isEnabled());

        /* simula cadastro */
        empresaMetodos.popularFormulario(empresaModelo);
        empresaModelo = empresaMetodos.salvar();
        empresaMetodos.popularFormulario(empresaModelo);

        /* é esperado que alguns campos estejam habilitados */
        empresaMetodos.habilitarCampos();
        System.out.println("Testando classe EmpresaMetodos metodo: habilitarCampos Etapa 02");
        assertEquals(true, view.jBcadastroExcluir.isEnabled());

    }

    @Test
    public void testAddPopUpMenu() {

        /* adiciona menu de popup */
        empresaMetodos.addPopUpMenu();
        PopUp popUp = new PopUp();

        /* testa se os eventos de pop menu foram adicionados */
        System.out.println("Testando classe EmpresaMetodos metodo: addPopUpMenu");
        assertEquals(true, popUp.isMenuPopUpAdicionado(view.jTbasicoNome));
        assertEquals(true, popUp.isMenuPopUpAdicionado(view.jFbasicoCnpj));
        assertEquals(true, popUp.isMenuPopUpAdicionado(view.jTcontatoTelefone));
        assertEquals(true, popUp.isMenuPopUpAdicionado(view.jTcontatoEmail));
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

    }

    @Test
    public void testCarregaDadosEmpresa() {

        /* popula os dados do modelo na view e salva */
        empresaMetodos.popularFormulario(empresaModelo);
        empresaModelo = empresaMetodos.salvar();

        /* carrega os dados da empresa */
        empresaMetodos.carregaDadosEmpresa();

        /* testa se os dados populados são iguais aos dados no modelo */
        System.out.println("Testando classe EmpresaMetodos metodo: salvar");
        assertEquals(true, view.getId() == empresaModelo.getId());
        assertEquals(true, view.jTbasicoNome.getText().equals(empresaModelo.getNome()));
        assertEquals(true, view.jFbasicoCnpj.getText().equals(empresaModelo.getCnpj()));
        assertEquals(true, view.jFenderecoCep.getText().equals(empresaModelo.getCep()));
        assertEquals(true, view.jTenderecoRua.getText().equals(empresaModelo.getRua()));
        assertEquals(true, view.jTenderecoQuadra.getText().equals(empresaModelo.getQuadra()));
        assertEquals(true, view.jTenderecoLote.getText().equals(empresaModelo.getLote()));
        assertEquals(true, view.jTenderecoNumero.getText().equals(empresaModelo.getNumero()));
        assertEquals(true, view.jCenderecoUf.getSelectedItem().toString().equals(empresaModelo.getUf()));
        assertEquals(true, view.jTenderecoBairro.getText().equals(empresaModelo.getBairro()));
        assertEquals(true, view.jTenderecoCidade.getText().equals(empresaModelo.getCidade()));
        assertEquals(true, view.jTenderecoComplemento.getText().equals(empresaModelo.getComplemento()));
        assertEquals(true, view.jTcontatoTelefone.getText().equals(empresaModelo.getTelefone()));
        assertEquals(true, view.jTcontatoEmail.getText().equals(empresaModelo.getEmail()));

    }

    @Test
    public void testSalvar() {

        /* popula o formulário com os dados */
        empresaMetodos.popularFormulario(empresaModelo);

        /* salva para que a ID possa ser gerada e popula novamente para pegar a ID de cadastro */
        empresaModelo = empresaMetodos.salvar();
        empresaMetodos.popularFormulario(empresaModelo);

        /* testa se os dados populados são iguais aos dados no modelo */
        System.out.println("Testando classe EmpresaMetodos metodo: salvar");
        assertEquals(true, view.getId() == empresaModelo.getId());
        assertEquals(true, view.jTbasicoNome.getText().equals(empresaModelo.getNome()));
        assertEquals(true, view.jFbasicoCnpj.getText().equals(empresaModelo.getCnpj()));
        assertEquals(true, view.jFenderecoCep.getText().equals(empresaModelo.getCep()));
        assertEquals(true, view.jTenderecoRua.getText().equals(empresaModelo.getRua()));
        assertEquals(true, view.jTenderecoQuadra.getText().equals(empresaModelo.getQuadra()));
        assertEquals(true, view.jTenderecoLote.getText().equals(empresaModelo.getLote()));
        assertEquals(true, view.jTenderecoNumero.getText().equals(empresaModelo.getNumero()));
        assertEquals(true, view.jCenderecoUf.getSelectedItem().toString().equals(empresaModelo.getUf()));
        assertEquals(true, view.jTenderecoBairro.getText().equals(empresaModelo.getBairro()));
        assertEquals(true, view.jTenderecoCidade.getText().equals(empresaModelo.getCidade()));
        assertEquals(true, view.jTenderecoComplemento.getText().equals(empresaModelo.getComplemento()));
        assertEquals(true, view.jTcontatoTelefone.getText().equals(empresaModelo.getTelefone()));
        assertEquals(true, view.jTcontatoEmail.getText().equals(empresaModelo.getEmail()));

    }

    @Test
    public void testExcluir() {

        /* simula cadastro */
        empresaMetodos.popularFormulario(empresaModelo);
        empresaModelo = empresaMetodos.salvar();
        empresaMetodos.popularFormulario(empresaModelo);

        /* testa exclusão */
        System.out.println("Testando classe EmpresaMetodos metodo: excluir");
        assertEquals(true, empresaMetodos.excluir());

    }

}
