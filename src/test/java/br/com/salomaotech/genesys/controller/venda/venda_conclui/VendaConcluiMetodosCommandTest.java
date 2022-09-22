package br.com.salomaotech.genesys.controller.venda.venda_conclui;

import br.com.salomaotech.genesys.model.cliente.ClienteModelo;
import br.com.salomaotech.genesys.model.cliente.ComboBoxClientes;
import br.com.salomaotech.genesys.view.JFvendaConclui;
import br.com.salomaotech.sistema.jpa.Repository;
import org.junit.Test;
import static org.junit.Assert.*;

public class VendaConcluiMetodosCommandTest {

    private final JFvendaConclui view = new JFvendaConclui();
    private final ClienteModelo clienteModelo = new ClienteModelo();
    private final VendaConcluiMetodosCommand vendaConcluiMetodosCommand = new VendaConcluiMetodosCommand(view);
    private final ComboBoxClientes comboBoxClientes = new ComboBoxClientes(view.jCcliente, vendaConcluiMetodosCommand);

    public VendaConcluiMetodosCommandTest() {

        /* simula cadastro de cliente */
        new Repository(new ClienteModelo()).deleteTodos();
        clienteModelo.setNome("Teste");
        clienteModelo.setCpf("000.000.000-00");
        new Repository(clienteModelo).save();

        /* preenche comboboxes */
        comboBoxClientes.preencher();

        /* inicializa commands */
        vendaConcluiMetodosCommand.setComboBoxClientes(comboBoxClientes);

    }

    @Test
    public void testSetComboBoxClientes() {

        boolean isErro = false;

        try {

            vendaConcluiMetodosCommand.setComboBoxClientes(comboBoxClientes);

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe VendaConcluiMetodosCommand metodo: setComboBoxClientes");
        assertEquals(false, isErro);

    }

    @Test
    public void testExecutar() {

        comboBoxClientes.selecionarItemPorId(clienteModelo.getId());
        vendaConcluiMetodosCommand.executar(null);

        System.out.println("Testando classe VendaConcluiMetodosCommand metodo: executar");
        assertEquals(true, view.jTclienteCpf.getText().equals(clienteModelo.getCpf()));

    }

}
