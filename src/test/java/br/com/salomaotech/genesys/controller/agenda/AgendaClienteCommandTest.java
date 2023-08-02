package br.com.salomaotech.genesys.controller.agenda;

import br.com.salomaotech.genesys.model.cliente.ClienteModelo;
import br.com.salomaotech.genesys.view.JFagenda;
import br.com.salomaotech.sistema.jpa.Repository;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class AgendaClienteCommandTest {

    private final JFagenda view = new JFagenda();
    private final ClienteModelo clienteModelo = new ClienteModelo();
    private final AgendaClienteCommand agendaClienteCommand = new AgendaClienteCommand(view);

    public AgendaClienteCommandTest() {

        new Repository(new ClienteModelo()).deleteTodos();
        clienteModelo.setNome("Teste");
        clienteModelo.setTelefone("0000-0000");
        new Repository(clienteModelo).save();

    }

    @Test
    public void testExecutar() {

        agendaClienteCommand.executar(clienteModelo.getId());

        System.out.println("Testando classe AgendaClienteCommand metodo: executar");
        assertEquals(true, view.jTcadastroTelefone.getText().equals(clienteModelo.getTelefone()));

    }

}
