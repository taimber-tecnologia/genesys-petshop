package br.com.salomaotech.genesys.controller.animal;

import br.com.salomaotech.genesys.model.animal.AnimalModelo;
import br.com.salomaotech.genesys.model.cliente.ClienteModelo;
import br.com.salomaotech.genesys.model.cliente.ComboBoxClientes;
import br.com.salomaotech.genesys.view.JFanimal;
import br.com.salomaotech.sistema.jpa.Repository;
import br.com.salomaotech.sistema.swing.PopUp;
import java.math.BigDecimal;
import java.util.Calendar;
import static java.util.Objects.isNull;
import javax.swing.JComboBox;
import org.junit.Test;
import static org.junit.Assert.*;

public class AnimalMetodosTest {

    private final JFanimal view = new JFanimal();
    private final Calendar calendar = Calendar.getInstance();
    private final ClienteModelo clienteModelo = new ClienteModelo();
    private AnimalModelo animalModelo = new AnimalModelo();
    private final ComboBoxClientes comboBoxClientesCadastro;
    private final ComboBoxClientes comboBoxClientesPesquisa;
    private final AnimalMetodos animalMetodos = new AnimalMetodos(view);

    public AnimalMetodosTest() {

        /* simula cadastro de cliente */
        new Repository(new ClienteModelo()).deleteTodos();
        clienteModelo.setNome("Teste");
        clienteModelo.setCpf("000.000.000-00");
        new Repository(clienteModelo).save();

        /* simula cadastro de animal */
        new Repository(new AnimalModelo()).deleteTodos();
        animalModelo.setNome("Teste");
        animalModelo.setSexo("Macho");
        animalModelo.setNascimento(calendar);
        animalModelo.setPeso(new BigDecimal(10));
        animalModelo.setEspecie("Cachorro");
        animalModelo.setRaca("Vira lata");
        animalModelo.setCaracteristica("Caramelo");
        animalModelo.setIdCliente(clienteModelo.getId());
        new Repository(animalModelo).save();

        /* combobox de cadastro com lista de clientes */
        comboBoxClientesCadastro = new ComboBoxClientes(view.jCnomeClienteCadastro);
        comboBoxClientesCadastro.preencher();

        /* combobox de pesquisa com lista de clientes */
        comboBoxClientesPesquisa = new ComboBoxClientes(view.jCpesquisaNomeCliente);
        comboBoxClientesPesquisa.preencher();

        /* metodos */
        animalMetodos.setComboBoxClientesCadastro(comboBoxClientesCadastro);
        animalMetodos.setComboBoxClientesPesquisa(comboBoxClientesPesquisa);

    }

    @Test
    public void testSetComboBoxClientesCadastro() {

        boolean isErro = false;

        try {

            animalMetodos.setComboBoxClientesCadastro(new ComboBoxClientes(new JComboBox()));

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe AnimalMetodos método: setComboBoxClientesCadastro");
        assertEquals(false, isErro);

    }

    @Test
    public void testSetComboBoxClientesPesquisa() {

        boolean isErro = false;

        try {

            animalMetodos.setComboBoxClientesPesquisa(new ComboBoxClientes(new JComboBox()));

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe AnimalMetodos método: setComboBoxClientesPesquisa");
        assertEquals(false, isErro);

    }

    @Test
    public void testPopularFormulario() {

        /* popula os dados do modelo na view */
        animalMetodos.popularFormulario(animalModelo);

        /* testa se os dados populados são iguais aos dados no modelo */
        System.out.println("Testando classe AnimalMetodos metodo: popularFormulario");
        assertEquals(true, view.getId() == animalModelo.getId());
        assertEquals(true, view.jTnomeCadastro.getText().equals(animalModelo.getNome()));
        assertEquals(true, view.jCsexoCadastro.getSelectedItem().equals(animalModelo.getSexo()));
        assertEquals(true, view.jDnascimentoCadastro.getCalendar().equals(animalModelo.getNascimento()));
        assertEquals(true, view.jTpesoCadastro.getText().equals(animalModelo.getPeso().toString()));
        assertEquals(true, view.jCespecieCadastro.getEditor().getItem().equals(animalModelo.getEspecie()));
        assertEquals(true, view.jCracaCadastro.getEditor().getItem().equals(animalModelo.getRaca()));
        assertEquals(true, view.jTcaracteristicasCadastro.getText().equals(animalModelo.getCaracteristica()));
        assertEquals(true, comboBoxClientesCadastro.getIdSelecionado() == animalModelo.getIdCliente());

    }

    @Test
    public void testResetarView() {

        /* reseta a view */
        animalMetodos.resetarView();

        /* testa se os dados populados na view foram resetados */
        System.out.println("Testando classe AnimalMetodos metodo: resetarView");
        assertEquals(true, view.getId() == 0);
        assertEquals(true, view.jTnomeCadastro.getText().equals(""));
        assertEquals(true, view.jCsexoCadastro.getSelectedIndex() == 0);
        assertEquals(true, isNull(view.jDnascimentoCadastro.getCalendar()));
        assertEquals(true, view.jTpesoCadastro.getText().equals("0"));
        assertEquals(true, view.jCespecieCadastro.getEditor().getItem().equals(""));
        assertEquals(true, view.jCracaCadastro.getEditor().getItem().equals(""));
        assertEquals(true, view.jTcaracteristicasCadastro.getText().equals(""));
        assertEquals(true, comboBoxClientesCadastro.getIdSelecionado() == 0);

    }

    @Test
    public void testHabilitarCampos() {

        /* esperado que os campos abaixo estejam desabilitados */
        animalMetodos.habilitarCampos();
        System.out.println("Testando classe AnimalMetodos metodo: habilitarCampos etapa 01");
        assertEquals(false, view.jBcadastroExcluir.isEnabled());
        assertEquals(false, view.jBatalhoVacinas.isEnabled());

        /* esperado que os campos abaixo estejam habilitados */
        animalMetodos.popularFormulario(animalModelo);
        animalMetodos.habilitarCampos();
        System.out.println("Testando classe AnimalMetodos metodo: habilitarCampos etapa 02");
        assertEquals(true, view.jBcadastroExcluir.isEnabled());
        assertEquals(true, view.jBatalhoVacinas.isEnabled());

    }

    @Test
    public void testAddPopUpMenu() {

        /* adiciona menu de popup */
        animalMetodos.addPopUpMenu();

        /* testa se o menu de popup foi adicionado */
        PopUp popUp = new PopUp();
        System.out.println("Testando classe AnimalMetodos metodo: addPopUpMenu");
        assertEquals(true, popUp.isMenuPopUpAdicionado(view.jTnomeCadastro));
        assertEquals(true, popUp.isMenuPopUpAdicionado(view.jTpesoCadastro));
        assertEquals(true, popUp.isMenuPopUpAdicionado(view.jTcaracteristicasCadastro));
        assertEquals(true, popUp.isMenuPopUpAdicionado(view.jTpesquisaNome));

    }

    @Test
    public void testAbrirCadastro() {

        /* abre o cadastro já realizado no construtor */
        animalMetodos.abrirCadastro(animalModelo.getId());

        /* testa se os dados populados são iguais aos dados no modelo */
        System.out.println("Testando classe AnimalMetodos metodo: abrirCadastro");
        assertEquals(true, view.getId() == animalModelo.getId());
        assertEquals(true, view.jTnomeCadastro.getText().equals(animalModelo.getNome()));
        assertEquals(true, view.jCsexoCadastro.getSelectedItem().equals(animalModelo.getSexo()));
        assertEquals(true, view.jDnascimentoCadastro.getCalendar().equals(animalModelo.getNascimento()));
        assertEquals(true, view.jTpesoCadastro.getText().equals(animalModelo.getPeso().toString()));
        assertEquals(true, view.jCespecieCadastro.getEditor().getItem().equals(animalModelo.getEspecie()));
        assertEquals(true, view.jCracaCadastro.getEditor().getItem().equals(animalModelo.getRaca()));
        assertEquals(true, view.jTcaracteristicasCadastro.getText().equals(animalModelo.getCaracteristica()));
        assertEquals(true, comboBoxClientesCadastro.getIdSelecionado() == animalModelo.getIdCliente());

    }

    @Test
    public void testPesquisar() {

        /* usando filtro: nenhum */
        view.jTpesquisaNome.setText(null);
        comboBoxClientesPesquisa.selecionarItemPorId(0);
        animalMetodos.pesquisar();
        System.out.println("Testando classe AnimalMetodos metodo: pesquisar etapa 01");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: nome do animal */
        view.jTpesquisaNome.setText(animalModelo.getNome());
        comboBoxClientesPesquisa.selecionarItemPorId(0);
        animalMetodos.pesquisar();
        System.out.println("Testando classe AnimalMetodos metodo: pesquisar etapa 02");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: nome do cliente por sua ID */
        view.jTpesquisaNome.setText(null);
        comboBoxClientesPesquisa.selecionarItemPorId(animalModelo.getIdCliente());
        animalMetodos.pesquisar();
        System.out.println("Testando classe AnimalMetodos metodo: pesquisar etapa 03");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: nome do animal, nome do cliente por sua ID */
        view.jTpesquisaNome.setText(animalModelo.getNome());
        comboBoxClientesPesquisa.selecionarItemPorId(animalModelo.getIdCliente());
        animalMetodos.pesquisar();
        System.out.println("Testando classe AnimalMetodos metodo: pesquisar etapa 04");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

    }

    @Test
    public void testSalvar() {

        /* popula o formulário simulando o preenchimento dos dados */
        animalMetodos.popularFormulario(animalModelo);

        /* salva para que a ID possa ser gerada e popula novamente para pegar a ID de cadastro */
        animalModelo = animalMetodos.salvar();
        animalMetodos.popularFormulario(animalModelo);

        /* testa se os dados populados são iguais aos dados no modelo */
        System.out.println("Testando classe AnimalMetodos metodo: salvar");
        assertEquals(true, view.getId() == animalModelo.getId());
        assertEquals(true, view.jTnomeCadastro.getText().equals(animalModelo.getNome()));
        assertEquals(true, view.jCsexoCadastro.getSelectedItem().equals(animalModelo.getSexo()));
        assertEquals(true, view.jDnascimentoCadastro.getCalendar().equals(animalModelo.getNascimento()));
        assertEquals(true, view.jTpesoCadastro.getText().equals(animalModelo.getPeso().toString()));
        assertEquals(true, view.jCespecieCadastro.getEditor().getItem().equals(animalModelo.getEspecie()));
        assertEquals(true, view.jCracaCadastro.getEditor().getItem().equals(animalModelo.getRaca()));
        assertEquals(true, view.jTcaracteristicasCadastro.getText().equals(animalModelo.getCaracteristica()));
        assertEquals(true, comboBoxClientesCadastro.getIdSelecionado() == animalModelo.getIdCliente());

    }

    @Test
    public void testExcluir() {

        /* popula os dados do modelo na view */
        animalMetodos.popularFormulario(animalModelo);

        /* testa exclusão */
        System.out.println("Testando classe AnimalMetodos metodo: Testando classe AnimalMetodos metodo: excluir");
        assertEquals(true, animalMetodos.excluir());

    }

}
