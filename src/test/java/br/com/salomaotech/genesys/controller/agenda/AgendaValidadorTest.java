package br.com.salomaotech.genesys.controller.agenda;

import br.com.salomaotech.genesys.model.animal.AnimalModelo;
import br.com.salomaotech.genesys.model.animal.ComboBoxAnimais;
import br.com.salomaotech.genesys.model.cliente.ClienteModelo;
import br.com.salomaotech.genesys.model.cliente.ComboBoxClientes;
import br.com.salomaotech.genesys.view.JFagenda;
import br.com.salomaotech.sistema.jpa.Repository;
import java.util.Calendar;
import org.junit.Test;
import static org.junit.Assert.*;

public class AgendaValidadorTest {

    private final JFagenda view = new JFagenda();
    private final ComboBoxAnimais comboBoxAnimais;
    private final ComboBoxClientes comboBoxClientes;
    private final ClienteModelo clienteModelo = new ClienteModelo();
    private final AnimalModelo animalModelo = new AnimalModelo();
    private AgendaValidador agendaValidador = new AgendaValidador(view);

    public AgendaValidadorTest() {

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

        /* carrega lista de animais */
        comboBoxAnimais = new ComboBoxAnimais(view.jCcadastroNomeAnimal, clienteModelo.getId());
        comboBoxAnimais.preencher();

        /* carrega a lista de clientes */
        comboBoxClientes = new ComboBoxClientes(view.jCcadastroNomeCliente, comboBoxAnimais);
        comboBoxClientes.preencher();

    }

    @Test
    public void testIsValido() {

        /* usando filtro: nenhum */
        view.jDcadastroData.setCalendar(null);
        view.jCcadastroNomeCliente.setSelectedIndex(0);
        view.jCcadastroNomeAnimal.setSelectedIndex(0);
        agendaValidador = new AgendaValidador(view);
        System.out.println("Testando classe AgendaValidador metodo: isValido etapa 01");
        assertEquals(false, agendaValidador.isValido());

        /* usando filtro: data */
        view.jDcadastroData.setCalendar(Calendar.getInstance());
        view.jCcadastroNomeCliente.setSelectedIndex(0);
        view.jCcadastroNomeAnimal.setSelectedIndex(0);
        agendaValidador = new AgendaValidador(view);
        System.out.println("Testando classe AgendaValidador metodo: isValido etapa 02");
        assertEquals(false, agendaValidador.isValido());

        /* usando filtro: ID do cliente */
        view.jDcadastroData.setDate(null);
        comboBoxClientes.selecionarItemPorId(clienteModelo.getId());
        view.jCcadastroNomeAnimal.setSelectedIndex(0);
        agendaValidador = new AgendaValidador(view);
        System.out.println("Testando classe AgendaValidador metodo: isValido etapa 03");
        assertEquals(false, agendaValidador.isValido());

        /* usando filtro: ID do animal */
        view.jDcadastroData.setDate(null);
        view.jCcadastroNomeCliente.setSelectedIndex(0);
        comboBoxAnimais.selecionarItemComBox(animalModelo.getId());
        agendaValidador = new AgendaValidador(view);
        System.out.println("Testando classe AgendaValidador metodo: isValido etapa 04");
        assertEquals(false, agendaValidador.isValido());

        /* usando filtro: todos */
        view.jDcadastroData.setCalendar(Calendar.getInstance());
        comboBoxClientes.selecionarItemPorId(clienteModelo.getId());
        comboBoxAnimais.selecionarItemComBox(animalModelo.getId());
        agendaValidador = new AgendaValidador(view);
        System.out.println("Testando classe AgendaValidador metodo: isValido etapa 05");
        assertEquals(true, agendaValidador.isValido());

    }

    @Test
    public void testGetMensagensErro() {

        /* usando filtro: nenhum */
        view.jDcadastroData.setCalendar(null);
        view.jCcadastroNomeCliente.setSelectedIndex(0);
        view.jCcadastroNomeAnimal.setSelectedIndex(0);
        agendaValidador = new AgendaValidador(view);
        agendaValidador.isValido();
        System.out.println("Testando classe AgendaValidador metodo: getMensagensErro etapa 01");
        assertEquals(true, agendaValidador.getMensagensErro().length() != 0);

        /* usando filtro: data */
        view.jDcadastroData.setCalendar(Calendar.getInstance());
        view.jCcadastroNomeCliente.setSelectedIndex(0);
        view.jCcadastroNomeAnimal.setSelectedIndex(0);
        agendaValidador = new AgendaValidador(view);
        agendaValidador.isValido();
        System.out.println("Testando classe AgendaValidador metodo: getMensagensErro etapa 02");
        assertEquals(true, agendaValidador.getMensagensErro().length() != 0);

        /* usando filtro: ID do cliente */
        view.jDcadastroData.setDate(null);
        comboBoxClientes.selecionarItemPorId(clienteModelo.getId());
        view.jCcadastroNomeAnimal.setSelectedIndex(0);
        agendaValidador = new AgendaValidador(view);
        agendaValidador.isValido();
        System.out.println("Testando classe AgendaValidador metodo: getMensagensErro etapa 03");
        assertEquals(true, agendaValidador.getMensagensErro().length() != 0);

        /* usando filtro: ID do animal */
        view.jDcadastroData.setDate(null);
        view.jCcadastroNomeCliente.setSelectedIndex(0);
        comboBoxAnimais.selecionarItemComBox(animalModelo.getId());
        agendaValidador = new AgendaValidador(view);
        agendaValidador.isValido();
        System.out.println("Testando classe AgendaValidador metodo: getMensagensErro etapa 04");
        assertEquals(true, agendaValidador.getMensagensErro().length() != 0);

        /* usando filtro: todos */
        view.jDcadastroData.setCalendar(Calendar.getInstance());
        comboBoxClientes.selecionarItemPorId(clienteModelo.getId());
        comboBoxAnimais.selecionarItemComBox(animalModelo.getId());
        agendaValidador = new AgendaValidador(view);
        agendaValidador.isValido();
        System.out.println("Testando classe AgendaValidador metodo: getMensagensErro etapa 05");
        assertEquals(true, agendaValidador.getMensagensErro().length() == 0);

    }

}
