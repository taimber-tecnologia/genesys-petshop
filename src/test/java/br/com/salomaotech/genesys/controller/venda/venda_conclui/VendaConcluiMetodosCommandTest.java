package br.com.salomaotech.genesys.controller.venda.venda_conclui;

import br.com.salomaotech.genesys.model.cliente.ComboBoxClientes;
import org.junit.Test;
import static org.junit.Assert.*;

public class VendaConcluiMetodosCommandTest {

    public VendaConcluiMetodosCommandTest() {

    }

    @Test
    public void testSetComboBoxClientes() {

        System.out.println("setComboBoxClientes");
        ComboBoxClientes comboBoxClientes = null;
        VendaConcluiMetodosCommand instance = null;
        instance.setComboBoxClientes(comboBoxClientes);

    }

    @Test
    public void testExecutar() {

        System.out.println("executar");
        Object arg = null;
        VendaConcluiMetodosCommand instance = null;
        instance.executar(arg);

    }

}
