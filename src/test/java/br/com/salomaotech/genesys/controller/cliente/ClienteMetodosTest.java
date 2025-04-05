package br.com.salomaotech.genesys.controller.cliente;

import br.com.salomaotech.genesys.model.cliente.ClienteModelo;
import br.com.salomaotech.genesys.view.JFcliente;
import br.com.salomaotech.sistema.jpa.Repository;
import br.com.salomaotech.sistema.swing.PopUp;
import java.util.Calendar;
import static java.util.Objects.isNull;
import org.junit.Test;
import static org.junit.Assert.*;

public class ClienteMetodosTest {

    private final JFcliente view = new JFcliente();
    private final Calendar calendar = Calendar.getInstance();
    private ClienteModelo clienteModelo = new ClienteModelo();
    private final ClienteMetodos clienteMetodos = new ClienteMetodos(view);

    public ClienteMetodosTest() {

        /* simula cadastro de cliente */
        new Repository(new ClienteModelo()).deleteTodos();
        clienteModelo.setNome("Teste");
        clienteModelo.setCpf("000.000.000-00");
        clienteModelo.setSexo("Feminino");
        clienteModelo.setNascimento(calendar);
        clienteModelo.setNacionalidade("Brasileiro");
        clienteModelo.setNomePai("Teste");
        clienteModelo.setNomeMae("Teste");
        clienteModelo.setCep("75370-000");
        clienteModelo.setRua("01");
        clienteModelo.setQuadra("02");
        clienteModelo.setLote("03");
        clienteModelo.setNumero("04");
        clienteModelo.setUf("GO");
        clienteModelo.setBairro("A");
        clienteModelo.setCidade("B");
        clienteModelo.setComplemento("C");
        clienteModelo.setTelefone("62 0000-0000");
        clienteModelo.setEmail("abc123@email.com");
        new Repository(clienteModelo).save();

    }

    @Test
    public void testPopularFormulario() {

        /* popula os dados do modelo na view */
        clienteMetodos.popularFormulario(clienteModelo);

        /* testa se os dados populados são iguais aos dados no modelo */
        System.out.println("Testando classe ClienteMetodos metodo: popularFormulario");
        assertEquals(true, view.getId() == clienteModelo.getId());
        assertEquals(true, view.jTbasicoNome.getText().equals(clienteModelo.getNome()));
        assertEquals(true, view.jFbasicoCpf.getText().equals(clienteModelo.getCpf()));
        assertEquals(true, view.jDbasicoDataNascimento.getCalendar().equals(clienteModelo.getNascimento()));
        assertEquals(true, view.jCbasicoSexo.getSelectedItem().toString().equals(clienteModelo.getSexo()));
        assertEquals(true, view.jTbasicoNomePai.getText().equals(clienteModelo.getNomePai()));
        assertEquals(true, view.jTbasicoNomeMae.getText().equals(clienteModelo.getNomeMae()));
        assertEquals(true, view.jFenderecoCep.getText().equals(clienteModelo.getCep()));
        assertEquals(true, view.jTenderecoRua.getText().equals(clienteModelo.getRua()));
        assertEquals(true, view.jTenderecoQuadra.getText().equals(clienteModelo.getQuadra()));
        assertEquals(true, view.jTenderecoLote.getText().equals(clienteModelo.getLote()));
        assertEquals(true, view.jTenderecoNumero.getText().equals(clienteModelo.getNumero()));
        assertEquals(true, view.jCenderecoUf.getSelectedItem().toString().equals(clienteModelo.getUf()));
        assertEquals(true, view.jTenderecoBairro.getText().equals(clienteModelo.getBairro()));
        assertEquals(true, view.jTenderecoCidade.getText().equals(clienteModelo.getCidade()));
        assertEquals(true, view.jTenderecoComplemento.getText().equals(clienteModelo.getComplemento()));
        assertEquals(true, view.jTcontatoTelefone.getText().equals(clienteModelo.getTelefone()));
        assertEquals(true, view.jTcontatoEmail.getText().equals(clienteModelo.getEmail()));

    }

    @Test
    public void testResetarView() {

        /* reseta a view */
        clienteMetodos.resetarView();

        /* testa se os dados populados na view foram resetados */
        System.out.println("Testando classe ClienteMetodos metodo: resetarView");
        assertEquals(true, view.getId() == 0);
        assertEquals(true, view.jTbasicoNome.getText().equals(""));
        assertEquals(true, view.jFbasicoCpf.getText().equals("   .   .   -  "));
        assertEquals(true, isNull(view.jDbasicoDataNascimento.getDate()));
        assertEquals(true, view.jCbasicoSexo.getSelectedIndex() == 0);
        assertEquals(true, view.jTbasicoNomePai.getText().equals(""));
        assertEquals(true, view.jTbasicoNomeMae.getText().equals(""));
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
        clienteMetodos.habilitarCampos();

        /* testa se os campos estão desabilitados */
        System.out.println("Testando classe ClienteMetodos metodo: habilitarCampos Etapa 01");
        assertEquals(false, view.jBcadastroExcluir.isEnabled());


        /* é esperado que alguns campos estejam habilitados */
        clienteMetodos.popularFormulario(clienteModelo);
        clienteMetodos.habilitarCampos();

        /* é esperado que alguns campos estejam habilitados */
        System.out.println("Testando classe ClienteMetodos metodo: habilitarCampos Etapa 02");
        assertEquals(true, view.jBcadastroExcluir.isEnabled());

    }

    @Test
    public void testAddPopUpMenu() {

        /* adiciona menu de popup */
        clienteMetodos.addPopUpMenu();
        PopUp popUp = new PopUp();

        /* testa se os eventos de pop menu foram adicionados */
        System.out.println("Testando classe ClienteMetodos metodo: addPopUpMenu");
        assertEquals(true, popUp.isMenuPopUpAdicionado(view.jTbasicoNome));
        assertEquals(true, popUp.isMenuPopUpAdicionado(view.jFbasicoCpf));
        assertEquals(true, popUp.isMenuPopUpAdicionado(view.jTbasicoNomePai));
        assertEquals(true, popUp.isMenuPopUpAdicionado(view.jTbasicoNomeMae));
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
        assertEquals(true, popUp.isMenuPopUpAdicionado(view.jTpesquisaCpf));

    }

    @Test
    public void testAbrirCadastro() {

        /* abre o cadastro já realizado no construtor */
        clienteMetodos.abrirCadastro(clienteModelo.getId());

        /* testa se os dados populados são iguais aos dados no modelo */
        System.out.println("Testando classe ClienteMetodos metodo: abrirCadastro");
        assertEquals(true, view.getId() == clienteModelo.getId());
        assertEquals(true, view.jTbasicoNome.getText().equals(clienteModelo.getNome()));
        assertEquals(true, view.jFbasicoCpf.getText().equals(clienteModelo.getCpf()));
        assertEquals(true, view.jDbasicoDataNascimento.getCalendar().equals(clienteModelo.getNascimento()));
        assertEquals(true, view.jCbasicoSexo.getSelectedItem().toString().equals(clienteModelo.getSexo()));
        assertEquals(true, view.jTbasicoNomePai.getText().equals(clienteModelo.getNomePai()));
        assertEquals(true, view.jTbasicoNomeMae.getText().equals(clienteModelo.getNomeMae()));
        assertEquals(true, view.jFenderecoCep.getText().equals(clienteModelo.getCep()));
        assertEquals(true, view.jTenderecoRua.getText().equals(clienteModelo.getRua()));
        assertEquals(true, view.jTenderecoQuadra.getText().equals(clienteModelo.getQuadra()));
        assertEquals(true, view.jTenderecoLote.getText().equals(clienteModelo.getLote()));
        assertEquals(true, view.jTenderecoNumero.getText().equals(clienteModelo.getNumero()));
        assertEquals(true, view.jCenderecoUf.getSelectedItem().toString().equals(clienteModelo.getUf()));
        assertEquals(true, view.jTenderecoBairro.getText().equals(clienteModelo.getBairro()));
        assertEquals(true, view.jTenderecoCidade.getText().equals(clienteModelo.getCidade()));
        assertEquals(true, view.jTenderecoComplemento.getText().equals(clienteModelo.getComplemento()));
        assertEquals(true, view.jTcontatoTelefone.getText().equals(clienteModelo.getTelefone()));
        assertEquals(true, view.jTcontatoEmail.getText().equals(clienteModelo.getEmail()));

    }

    @Test
    public void testSalvar() {

        /* popula o formulário simulando o preenchimento dos dados */
        clienteMetodos.popularFormulario(clienteModelo);

        /* salva para que a ID possa ser gerada e popula novamente para pegar a ID de cadastro */
        clienteModelo = clienteMetodos.salvar();
        clienteMetodos.popularFormulario(clienteModelo);

        /* testa se os dados populados são iguais aos dados no modelo */
        System.out.println("Testando classe ClienteMetodos metodo: salvar");
        assertEquals(true, view.getId() == clienteModelo.getId());
        assertEquals(true, view.jTbasicoNome.getText().equals(clienteModelo.getNome()));
        assertEquals(true, view.jFbasicoCpf.getText().equals(clienteModelo.getCpf()));
        assertEquals(true, view.jDbasicoDataNascimento.getCalendar().equals(clienteModelo.getNascimento()));
        assertEquals(true, view.jCbasicoSexo.getSelectedItem().toString().equals(clienteModelo.getSexo()));
        assertEquals(true, view.jTbasicoNomePai.getText().equals(clienteModelo.getNomePai()));
        assertEquals(true, view.jTbasicoNomeMae.getText().equals(clienteModelo.getNomeMae()));
        assertEquals(true, view.jFenderecoCep.getText().equals(clienteModelo.getCep()));
        assertEquals(true, view.jTenderecoRua.getText().equals(clienteModelo.getRua()));
        assertEquals(true, view.jTenderecoQuadra.getText().equals(clienteModelo.getQuadra()));
        assertEquals(true, view.jTenderecoLote.getText().equals(clienteModelo.getLote()));
        assertEquals(true, view.jTenderecoNumero.getText().equals(clienteModelo.getNumero()));
        assertEquals(true, view.jCenderecoUf.getSelectedItem().toString().equals(clienteModelo.getUf()));
        assertEquals(true, view.jTenderecoBairro.getText().equals(clienteModelo.getBairro()));
        assertEquals(true, view.jTenderecoCidade.getText().equals(clienteModelo.getCidade()));
        assertEquals(true, view.jTenderecoComplemento.getText().equals(clienteModelo.getComplemento()));
        assertEquals(true, view.jTcontatoTelefone.getText().equals(clienteModelo.getTelefone()));
        assertEquals(true, view.jTcontatoEmail.getText().equals(clienteModelo.getEmail()));

    }

    @Test
    public void testExcluir() {

        /* popula os dados do modelo na view */
        clienteMetodos.popularFormulario(clienteModelo);

        /* testa exclusão */
        System.out.println("Testando classe ClienteMetodos metodo: excluir");
        assertEquals(true, clienteMetodos.excluir());

    }

    @Test
    public void testPesquisar() {

        /* usando filtro: nenhum */
        view.jTpesquisaNome.setText(null);
        view.jTpesquisaCpf.setText(null);
        clienteMetodos.pesquisar();
        System.out.println("Testando classe ClienteMetodos metodo: pesquisar etapa 01");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: nome */
        view.jTpesquisaNome.setText(clienteModelo.getNome());
        view.jTpesquisaCpf.setText(null);
        clienteMetodos.pesquisar();
        System.out.println("Testando classe ClienteMetodos metodo: pesquisar etapa 02");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: cpf */
        view.jTpesquisaNome.setText(null);
        view.jTpesquisaCpf.setText(clienteModelo.getCpf());
        clienteMetodos.pesquisar();
        System.out.println("Testando classe ClienteMetodos metodo: pesquisar etapa 03");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: todos */
        view.jTpesquisaNome.setText(clienteModelo.getNome());
        view.jTpesquisaCpf.setText(clienteModelo.getCpf());
        clienteMetodos.pesquisar();
        System.out.println("Testando classe ClienteMetodos metodo: pesquisar etapa 04");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

    }

}
