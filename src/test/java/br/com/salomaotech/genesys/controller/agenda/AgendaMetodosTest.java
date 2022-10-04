package br.com.salomaotech.genesys.controller.agenda;

import br.com.salomaotech.genesys.model.agenda.AgendaModelo;
import br.com.salomaotech.genesys.view.JFagenda;
import br.com.salomaotech.sistema.jpa.Repository;
import br.com.salomaotech.sistema.swing.PopUp;
import java.util.Calendar;
import static java.util.Objects.isNull;
import org.junit.Test;
import static org.junit.Assert.*;

public class AgendaMetodosTest {

    private final JFagenda view = new JFagenda();
    private final Calendar calendar = Calendar.getInstance();
    private AgendaModelo agendaModelo = new AgendaModelo();
    private final AgendaMetodos agendaMetodos = new AgendaMetodos(view);

    public AgendaMetodosTest() {

        /* simula cadastro de agenda */
        new Repository(new AgendaModelo()).deleteTodos();
        agendaModelo.setDataAgenda(calendar);
        agendaModelo.setDataHora("14");
        agendaModelo.setDataMinuto("30");
        agendaModelo.setObservacoes("Banho");
        agendaModelo.setNomeCliente("Teste");
        agendaModelo.setStatus("1 - Agendado");
        new Repository(agendaModelo).save();

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
        assertEquals(true, view.jTcadastroNomeCliente.getText().equals(agendaModelo.getNomeCliente()));
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
        assertEquals(true, view.jTcadastroNomeCliente.getText().equals(""));
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
        assertEquals(true, popUp.isMenuPopUpAdicionado(view.jTcadastroNomeCliente));
        assertEquals(true, popUp.isMenuPopUpAdicionado(view.jTcadastroHistorico));
        assertEquals(true, popUp.isMenuPopUpAdicionado(view.jTpesquisaNomeCliente));

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
        assertEquals(true, view.jTcadastroNomeCliente.getText().equals(agendaModelo.getNomeCliente()));
        assertEquals(true, view.jTcadastroHistorico.getText().equals(agendaModelo.getObservacoes()));
        assertEquals(true, view.jCstatus.getSelectedItem().toString().equals(agendaModelo.getStatus()));

    }

    @Test
    public void testPesquisar() {

        /* usando filtro: nenhum */
        view.jDpesquisaDataInicio.setCalendar(null);
        view.jDPesquisaDataFim.setCalendar(null);
        view.jTcadastroNomeCliente.setText(null);
        view.jCpesquisaStatus.setSelectedItem("");
        agendaMetodos.pesquisar();
        System.out.println("Testando classe AgendaMetodos metodo: pesquisar etapa 01");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: data inicial */
        view.jDpesquisaDataInicio.setCalendar(agendaModelo.getDataAgenda());
        view.jDPesquisaDataFim.setCalendar(null);
        view.jTcadastroNomeCliente.setText(null);
        view.jCpesquisaStatus.setSelectedItem("");
        agendaMetodos.pesquisar();
        System.out.println("Testando classe AgendaMetodos metodo: pesquisar etapa 02");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: data final */
        view.jDpesquisaDataInicio.setCalendar(null);
        view.jDPesquisaDataFim.setCalendar(agendaModelo.getDataAgenda());
        view.jTcadastroNomeCliente.setText(null);
        view.jCpesquisaStatus.setSelectedItem("");
        agendaMetodos.pesquisar();
        System.out.println("Testando classe AgendaMetodos metodo: pesquisar etapa 03");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: ID do cliente */
        view.jDpesquisaDataInicio.setCalendar(null);
        view.jDPesquisaDataFim.setCalendar(null);
        view.jTcadastroNomeCliente.setText(agendaModelo.getNomeCliente());
        view.jCpesquisaStatus.setSelectedItem("");
        agendaMetodos.pesquisar();
        System.out.println("Testando classe AgendaMetodos metodo: pesquisar etapa 04");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: status */
        view.jDpesquisaDataInicio.setCalendar(null);
        view.jDPesquisaDataFim.setCalendar(null);
        view.jTcadastroNomeCliente.setText(null);
        view.jCpesquisaStatus.setSelectedItem(agendaModelo.getStatus());
        agendaMetodos.pesquisar();
        System.out.println("Testando classe AgendaMetodos metodo: pesquisar etapa 05");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: todos */
        view.jDpesquisaDataInicio.setCalendar(agendaModelo.getDataAgenda());
        view.jDPesquisaDataFim.setCalendar(null);
        view.jTcadastroNomeCliente.setText(agendaModelo.getNomeCliente());
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
        assertEquals(true, view.jTcadastroNomeCliente.getText().equals(agendaModelo.getNomeCliente()));
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
