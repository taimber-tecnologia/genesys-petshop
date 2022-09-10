package br.com.salomaotech.genesys.controller.vacina;

import br.com.salomaotech.genesys.controller.vacina.VacinaMetodos;
import br.com.salomaotech.genesys.model.animal.AnimalModelo;
import br.com.salomaotech.genesys.model.cliente.ClienteModelo;
import br.com.salomaotech.genesys.model.vacina.ComboBoxVacinas;
import br.com.salomaotech.genesys.model.vacina.VacinaModelo;
import br.com.salomaotech.genesys.view.JFvacina;
import br.com.salomaotech.sistema.jpa.Repository;
import br.com.salomaotech.sistema.swing.PopUp;
import java.math.BigDecimal;
import java.util.Calendar;
import static java.util.Objects.isNull;
import org.junit.Test;
import static org.junit.Assert.*;

public class VacinaMetodosTest {

    private final JFvacina view = new JFvacina();
    private final Calendar data = Calendar.getInstance();
    private VacinaMetodos vacinaMetodos;
    private VacinaModelo vacinaModelo = new VacinaModelo();
    private final AnimalModelo animalModelo = new AnimalModelo();
    private final ComboBoxVacinas comboBoxVacinas;
    private final ClienteModelo clienteModelo = new ClienteModelo();

    public VacinaMetodosTest() {

        /* simula cadastro de cliente */
        new Repository(new ClienteModelo()).deleteTodos();
        clienteModelo.setNome("Teste");
        clienteModelo.setCpf("000.000.000-00");
        new Repository(clienteModelo).save();

        /* simula cadastro de animal */
        new Repository(new AnimalModelo()).deleteTodos();
        animalModelo.setNome("Teste");
        animalModelo.setSexo("Macho");
        animalModelo.setNascimento(data);
        animalModelo.setPeso(new BigDecimal(10));
        animalModelo.setEspecie("Cachorro");
        animalModelo.setRaca("Vira lata");
        animalModelo.setCaracteristica("Caramelo");
        animalModelo.setIdCliente(clienteModelo.getId());
        new Repository(animalModelo).save();

        /* modelo de vacina */
        new Repository(new VacinaModelo()).deleteTodos();
        vacinaModelo.setNome("Vacina antirrábica");
        vacinaModelo.setIdCliente(clienteModelo.getId());
        vacinaModelo.setIdAnimal(animalModelo.getId());
        vacinaModelo.setDataAplicacao(data);
        vacinaModelo.setDataAplicacaoProxima(data);
        vacinaModelo.setDoses("2");
        vacinaModelo.setObservacoes("Teste");
        new Repository(vacinaModelo).save();

        /* metodos */
        vacinaMetodos = new VacinaMetodos(view, animalModelo);

        /* combobox vacinas */
        comboBoxVacinas = new ComboBoxVacinas(view.jCcadastroNome);
        comboBoxVacinas.preencher();

    }

    @Test
    public void testPopularFormulario() {

        /* popula os dados do modelo na view */
        vacinaMetodos.popularFormulario(vacinaModelo);

        /* testa se os dados populados são iguais aos dados no modelo */
        System.out.println("Testando classe VacinaMetodos metodo: popularFormulario");
        assertEquals(true, view.getId() == vacinaModelo.getId());
        assertEquals(true, view.jCcadastroNome.getEditor().getItem().equals(vacinaModelo.getNome()));
        assertEquals(true, view.jDcadastroDataAplicacao.getCalendar().equals(vacinaModelo.getDataAplicacao()));
        assertEquals(true, view.jDcadastroDataProxima.getCalendar().equals(vacinaModelo.getDataAplicacaoProxima()));
        assertEquals(true, view.jCcadastroNumeroDoses.getSelectedItem().equals(vacinaModelo.getDoses()));
        assertEquals(true, view.jTcadastroObservacoes.getText().equals(vacinaModelo.getObservacoes()));

    }

    @Test
    public void testResetarView() {

        /* reseta a view */
        vacinaMetodos.resetarView();

        /* testa se os dados populados na view foram resetados */
        System.out.println("Testando classe VacinaMetodos metodo: resetarView");
        assertEquals(true, view.getId() == 0);
        assertEquals(true, view.jCcadastroNome.getEditor().getItem().equals(""));
        assertEquals(true, isNull(view.jDcadastroDataAplicacao.getCalendar()));
        assertEquals(true, isNull(view.jDcadastroDataProxima.getCalendar()));
        assertEquals(true, view.jCcadastroNumeroDoses.getSelectedItem().equals("1"));
        assertEquals(true, view.jTcadastroObservacoes.getText().equals(""));

    }

    @Test
    public void testHabilitarCampos() {

        /* esperado que os campos abaixo estejam desabilitados */
        vacinaMetodos.habilitarCampos();
        System.out.println("Testando classe VacinaMetodos metodo: habilitarCampos Etapa 01");
        assertEquals(false, view.jBcadastroExcluir.isEnabled());

        /* esperado que os campos abaixo estejam habilitados */
        vacinaMetodos.popularFormulario(vacinaModelo);
        vacinaMetodos.habilitarCampos();
        System.out.println("Testando classe VacinaMetodos metodo: habilitarCampos Etapa 02");
        assertEquals(true, view.jBcadastroExcluir.isEnabled());

    }

    @Test
    public void testAddPopUpMenu() {

        /* adiciona menu de popup */
        vacinaMetodos.addPopUpMenu();
        PopUp popUp = new PopUp();

        /* testa se o menu de popup foi adicionado */
        System.out.println("Testando classe VacinaMetodos metodo: addPopUpMenu");
        assertEquals(true, popUp.isMenuPopUpAdicionado(view.jCcadastroNome));
        assertEquals(true, popUp.isMenuPopUpAdicionado(view.jTcadastroObservacoes));

    }

    @Test
    public void testAbrirCadastro() {

        /* abre o cadastro com o ID de cadastro já feito no construtor */
        vacinaMetodos.abrirCadastro(vacinaModelo.getId());

        /* testa se os dados populados são iguais aos dados no modelo */
        System.out.println("Testando classe VacinaMetodos metodo: abrirCadastro");
        assertEquals(true, view.getId() == vacinaModelo.getId());
        assertEquals(true, view.jCcadastroNome.getEditor().getItem().equals(vacinaModelo.getNome()));
        assertEquals(true, view.jDcadastroDataAplicacao.getCalendar().equals(vacinaModelo.getDataAplicacao()));
        assertEquals(true, view.jDcadastroDataProxima.getCalendar().equals(vacinaModelo.getDataAplicacaoProxima()));
        assertEquals(true, view.jCcadastroNumeroDoses.getSelectedItem().equals(vacinaModelo.getDoses()));
        assertEquals(true, view.jTcadastroObservacoes.getText().equals(vacinaModelo.getObservacoes()));

    }

    @Test
    public void testPesquisar() {

        /* pesquisa usando filtro: modelo de dados do animal vazio */
        vacinaMetodos = new VacinaMetodos(view, new AnimalModelo());
        vacinaMetodos.pesquisar();
        System.out.println("Testando classe VacinaMetodos metodo: pesquisar etapa 01");
        assertEquals(true, view.jTresultados.getRowCount() == 0);

        /* pesquisa usando filtro: modelo de dados do animal persistido */
        vacinaMetodos = new VacinaMetodos(view, animalModelo);
        vacinaMetodos.pesquisar();
        System.out.println("Testando classe VacinaMetodos metodo: pesquisar etapa 02");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

    }

    @Test
    public void testSalvar() {

        /* popula o formulário simulando o preenchimento dos dados */
        vacinaMetodos.popularFormulario(vacinaModelo);
        vacinaModelo = vacinaMetodos.salvar();
        vacinaMetodos.popularFormulario(vacinaModelo);

        /* testa se os dados populados são iguais aos dados no modelo */
        System.out.println("Testando classe VacinaMetodos metodo: salvar");
        assertEquals(true, view.getId() == vacinaModelo.getId());
        assertEquals(true, view.jCcadastroNome.getEditor().getItem().equals(vacinaModelo.getNome()));
        assertEquals(true, view.jDcadastroDataAplicacao.getCalendar().equals(vacinaModelo.getDataAplicacao()));
        assertEquals(true, view.jDcadastroDataProxima.getCalendar().equals(vacinaModelo.getDataAplicacaoProxima()));
        assertEquals(true, view.jCcadastroNumeroDoses.getSelectedItem().equals(vacinaModelo.getDoses()));
        assertEquals(true, view.jTcadastroObservacoes.getText().equals(vacinaModelo.getObservacoes()));

    }

    @Test
    public void testExcluir() {

        /* popula o formulário simulando o preenchimento dos dados */
        vacinaMetodos.popularFormulario(vacinaModelo);

        /* testa exclusão */
        System.out.println("Testando classe VacinaMetodos metodo: excluir");
        assertEquals(true, vacinaMetodos.excluir());

    }

}
