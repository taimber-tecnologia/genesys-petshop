package br.com.salomaotech.genesys.controller.agenda;

import br.com.salomaotech.genesys.model.agenda.AgendaModelo;
import br.com.salomaotech.genesys.model.animal.AnimalModelo;
import br.com.salomaotech.genesys.model.animal.ComboBoxAnimais;
import br.com.salomaotech.genesys.model.cliente.ClienteModelo;
import br.com.salomaotech.genesys.model.cliente.ComboBoxClientes;
import br.com.salomaotech.genesys.view.JFagenda;
import br.com.salomaotech.sistema.jpa.Repository;
import br.com.salomaotech.sistema.swing.PopUp;
import java.util.Calendar;
import static java.util.Objects.isNull;
import javax.swing.JComboBox;
import org.junit.Test;
import static org.junit.Assert.*;

public class AgendaMetodosTest {

    private final JFagenda view = new JFagenda();
    private final Calendar calendar = Calendar.getInstance();
    private final ClienteModelo clienteModelo = new ClienteModelo();
    private final AnimalModelo animalModelo = new AnimalModelo();
    private AgendaModelo agendaModelo = new AgendaModelo();
    private final ComboBoxAnimais comboBoxAnimaisCadastro;
    private final ComboBoxClientes comboBoxClientesCadastro;
    private final ComboBoxClientes comboBoxClientesPesquisa;
    private final AgendaMetodos agendaMetodos = new AgendaMetodos(view);

    public AgendaMetodosTest() {

        /* simula cadastro de cliente */
        new Repository(new ClienteModelo()).deleteTodos();
        clienteModelo.setNome("Teste");
        clienteModelo.setCpf("000.000.000-00");
        new Repository(clienteModelo).save();

        /* simula cadastro de animal */
        new Repository(new AnimalModelo()).deleteTodos();
        animalModelo.setNome("Felix");
        animalModelo.setEspecie("Felino");
        animalModelo.setIdCliente(clienteModelo.getId());
        new Repository(animalModelo).save();

        /* simula cadastro de agenda */
        new Repository(new AgendaModelo()).deleteTodos();
        agendaModelo.setDataAgenda(calendar);
        agendaModelo.setDataHora("14");
        agendaModelo.setDataMinuto("30");
        agendaModelo.setObservacoes("Banho");
        agendaModelo.setIdCliente(clienteModelo.getId());
        agendaModelo.setIdAnimal(animalModelo.getId());
        agendaModelo.setStatus("1 - Agendado");
        new Repository(agendaModelo).save();

        /* comboBox */
        comboBoxAnimaisCadastro = new ComboBoxAnimais(view.jCcadastroNomeAnimal, clienteModelo.getId());
        comboBoxClientesCadastro = new ComboBoxClientes(view.jCcadastroNomeCliente, comboBoxAnimaisCadastro);
        comboBoxClientesPesquisa = new ComboBoxClientes(view.jCpesquisaNomeCliente);

        /* metodos */
        agendaMetodos.setComboBoxClientesCadastro(comboBoxClientesCadastro);
        agendaMetodos.setComboBoxClientesPesquisa(comboBoxClientesPesquisa);
        agendaMetodos.setComboBoxAnimaisCadastro(comboBoxAnimaisCadastro);

    }

    @Test
    public void testSetComboBoxClientesCadastro() {

        boolean isErro = false;

        try {

            agendaMetodos.setComboBoxClientesCadastro(new ComboBoxClientes(new JComboBox()));

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe AgendaMetodos método: setComboBoxClientesCadastro");
        assertEquals(false, isErro);

    }

    @Test
    public void testSetComboBoxClientesPesquisa() {

        boolean isErro = false;

        try {

            agendaMetodos.setComboBoxClientesPesquisa(new ComboBoxClientes(new JComboBox()));

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe AgendaMetodos método: setComboBoxClientesPesquisa");
        assertEquals(false, isErro);

    }

    @Test
    public void testSetComboBoxAnimaisCadastro() {

        boolean isErro = false;

        try {

            agendaMetodos.setComboBoxAnimaisCadastro(new ComboBoxAnimais(new JComboBox()));

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe AgendaMetodos método: setComboBoxAnimaisCadastro");
        assertEquals(false, isErro);

    }

    @Test
    public void testPopularFormulario() {

        /* popula os dados do modelo na view */
        agendaMetodos.popularFormulario(agendaModelo);

        /* testa se os dados populados são iguais aos dados no modelo */
        System.out.println("Testando classe AgendaMetodos metodo: popularFormulario");
        assertEquals(true, view.getId() == agendaModelo.getId());
        assertEquals(true, view.jDcadastroData.getCalendar().equals(agendaModelo.getDataAgenda()));
        assertEquals(true, view.jCcadastroHora.getSelectedItem().toString().equals(agendaModelo.getDataHora()));
        assertEquals(true, view.jCcadastroMinuto.getSelectedItem().toString().equals(agendaModelo.getDataMinuto()));
        assertEquals(true, comboBoxClientesCadastro.getIdSelecionado() == agendaModelo.getIdCliente());
        assertEquals(true, comboBoxAnimaisCadastro.getIdAnimalSelecionado() == agendaModelo.getIdAnimal());
        assertEquals(true, view.jTcadastroHistorico.getText().equals(agendaModelo.getObservacoes()));
        assertEquals(true, view.jCstatus.getSelectedItem().toString().equals(agendaModelo.getStatus()));

    }

    @Test
    public void testResetarView() {

        /* reseta a view */
        agendaMetodos.resetarView();

        /* testa se os dados populados na view foram resetados */
        System.out.println("Testando classe AgendaMetodos metodo: resetarView");
        assertEquals(true, view.getId() == 0);
        assertEquals(true, isNull(view.jDcadastroData.getDate()));
        assertEquals(true, view.jCcadastroHora.getSelectedItem().toString().equals("00"));
        assertEquals(true, view.jCcadastroMinuto.getSelectedItem().toString().equals("00"));
        assertEquals(true, view.jCcadastroNomeCliente.getSelectedIndex() == 0);
        assertEquals(true, comboBoxAnimaisCadastro.getIdAnimalSelecionado() == 0);
        assertEquals(true, view.jTcadastroHistorico.getText().equals(""));
        assertEquals(true, view.jCstatus.getSelectedIndex() == 0);

    }

    @Test
    public void testHabilitarCampos() {

        /* é esperado que o botão de excluir esteja desabilitado */
        agendaMetodos.habilitarCampos();
        System.out.println("Testando classe AgendaMetodos metodo: habilitarCampos Etapa 01");
        assertEquals(false, view.jBcadastroExcluir.isEnabled());

        /* é esperado que o botão de excluir esteja habilitado */
        agendaMetodos.popularFormulario(agendaModelo);
        agendaMetodos.habilitarCampos();
        System.out.println("Testando classe AgendaMetodos metodo: habilitarCampos Etapa 02");
        assertEquals(true, view.jBcadastroExcluir.isEnabled());

    }

    @Test
    public void testAddPopUpMenu() {

        /* adiciona menu de popup */
        agendaMetodos.addPopUpMenu();

        /* testa se o menu de popup foi adicionado */
        PopUp popUp = new PopUp();
        System.out.println("Testando classe AgendaMetodos metodo: addPopUpMenu");
        assertEquals(true, popUp.isMenuPopUpAdicionado(view.jTcadastroHistorico));

    }

    @Test
    public void testAbrirCadastro() {

        /* abre o cadastro já realizado no construtor */
        agendaMetodos.abrirCadastro(agendaModelo.getId());

        /* testa se os dados populados são iguais aos dados no modelo */
        System.out.println("Testando classe AgendaMetodos metodo: abrirCadastro");
        assertEquals(true, view.getId() == agendaModelo.getId());
        assertEquals(true, view.jDcadastroData.getCalendar().equals(agendaModelo.getDataAgenda()));
        assertEquals(true, view.jCcadastroHora.getSelectedItem().toString().equals(agendaModelo.getDataHora()));
        assertEquals(true, view.jCcadastroMinuto.getSelectedItem().toString().equals(agendaModelo.getDataMinuto()));
        assertEquals(true, comboBoxClientesCadastro.getIdSelecionado() == agendaModelo.getIdCliente());
        assertEquals(true, comboBoxAnimaisCadastro.getIdAnimalSelecionado() == agendaModelo.getIdAnimal());
        assertEquals(true, view.jTcadastroHistorico.getText().equals(agendaModelo.getObservacoes()));
        assertEquals(true, view.jCstatus.getSelectedItem().toString().equals(agendaModelo.getStatus()));

    }

    @Test
    public void testPesquisar() {

        /* usando filtro: nenhum */
        view.jDpesquisaDataInicio.setCalendar(null);
        view.jDPesquisaDataFim.setCalendar(null);
        comboBoxClientesCadastro.selecionarItemPorId(0);
        view.jCpesquisaStatus.setSelectedItem("");
        agendaMetodos.pesquisar();
        System.out.println("Testando classe AgendaMetodos metodo: pesquisar etapa 01");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: data inicial */
        view.jDpesquisaDataInicio.setCalendar(agendaModelo.getDataAgenda());
        view.jDPesquisaDataFim.setCalendar(null);
        comboBoxClientesCadastro.selecionarItemPorId(0);
        view.jCpesquisaStatus.setSelectedItem("");
        agendaMetodos.pesquisar();
        System.out.println("Testando classe AgendaMetodos metodo: pesquisar etapa 02");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: data final */
        view.jDpesquisaDataInicio.setCalendar(null);
        view.jDPesquisaDataFim.setCalendar(agendaModelo.getDataAgenda());
        comboBoxClientesCadastro.selecionarItemPorId(0);
        view.jCpesquisaStatus.setSelectedItem("");
        agendaMetodos.pesquisar();
        System.out.println("Testando classe AgendaMetodos metodo: pesquisar etapa 03");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: ID do cliente */
        view.jDpesquisaDataInicio.setCalendar(null);
        view.jDPesquisaDataFim.setCalendar(null);
        comboBoxClientesCadastro.selecionarItemPorId(agendaModelo.getIdCliente());
        view.jCpesquisaStatus.setSelectedItem("");
        agendaMetodos.pesquisar();
        System.out.println("Testando classe AgendaMetodos metodo: pesquisar etapa 04");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: status */
        view.jDpesquisaDataInicio.setCalendar(null);
        view.jDPesquisaDataFim.setCalendar(null);
        comboBoxClientesCadastro.selecionarItemPorId(0);
        view.jCpesquisaStatus.setSelectedItem(agendaModelo.getStatus());
        agendaMetodos.pesquisar();
        System.out.println("Testando classe AgendaMetodos metodo: pesquisar etapa 05");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: todos */
        view.jDpesquisaDataInicio.setCalendar(agendaModelo.getDataAgenda());
        view.jDPesquisaDataFim.setCalendar(null);
        comboBoxClientesCadastro.selecionarItemPorId(agendaModelo.getIdCliente());
        view.jCpesquisaStatus.setSelectedItem(agendaModelo.getStatus());
        agendaMetodos.pesquisar();
        System.out.println("Testando classe AgendaMetodos metodo: pesquisar etapa 06");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

    }

    @Test
    public void testSalvar() {

        /* popula o formulário simulando o preenchimento dos dados */
        agendaMetodos.popularFormulario(agendaModelo);

        /* salva para que a ID possa ser gerada e popula novamente para pegar a ID de cadastro */
        agendaModelo = agendaMetodos.salvar();
        agendaMetodos.popularFormulario(agendaModelo);

        /* testa se os dados populados são iguais aos dados no modelo */
        System.out.println("Testando classe AgendaMetodos metodo: salvar");
        assertEquals(true, view.getId() == agendaModelo.getId());
        assertEquals(true, view.jDcadastroData.getCalendar().equals(agendaModelo.getDataAgenda()));
        assertEquals(true, view.jCcadastroHora.getSelectedItem().toString().equals(agendaModelo.getDataHora()));
        assertEquals(true, view.jCcadastroMinuto.getSelectedItem().toString().equals(agendaModelo.getDataMinuto()));
        assertEquals(true, comboBoxClientesCadastro.getIdSelecionado() == agendaModelo.getIdCliente());
        assertEquals(true, comboBoxAnimaisCadastro.getIdAnimalSelecionado() == agendaModelo.getIdAnimal());
        assertEquals(true, view.jTcadastroHistorico.getText().equals(agendaModelo.getObservacoes()));
        assertEquals(true, view.jCstatus.getSelectedItem().toString().equals(agendaModelo.getStatus()));

    }

    @Test
    public void testExcluir() {

        /* popula os dados do modelo na view */
        agendaMetodos.popularFormulario(agendaModelo);

        /* testa exclusão */
        System.out.println("Testando classe AgendaMetodos metodo: excluir");
        assertEquals(true, agendaMetodos.excluir());

    }

}
