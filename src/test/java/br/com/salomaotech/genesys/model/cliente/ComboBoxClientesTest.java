package br.com.salomaotech.genesys.model.cliente;

import br.com.salomaotech.genesys.model.cliente.ComboBoxClientes;
import br.com.salomaotech.genesys.model.cliente.ClienteModelo;
import br.com.salomaotech.sistema.jpa.Repository;
import javax.swing.JComboBox;
import org.junit.Test;
import static org.junit.Assert.*;

public class ComboBoxClientesTest {

    private final JComboBox jComboBox = new JComboBox();
    private final ClienteModelo clienteModelo = new ClienteModelo();
    private final ComboBoxClientes comboBoxClientes = new ComboBoxClientes(jComboBox);

    public ComboBoxClientesTest() {

        new Repository(new ClienteModelo()).deleteTodos();
        clienteModelo.setNome("Teste");
        clienteModelo.setCpf("000.000.000-00");
        new Repository(clienteModelo).save();

    }

    @Test
    public void testPreencher() {

        comboBoxClientes.preencher();
        System.out.println("Testando ComboBoxClientes metodo: preencher");
        assertEquals(true, jComboBox.getItemCount() == 2);

    }

    @Test
    public void testSelecionarItemPorId() {

        comboBoxClientes.selecionarItemPorId(clienteModelo.getId());
        System.out.println("Testando ComboBoxClientes metodo: selecionarItemPorId");
        assertEquals(true, comboBoxClientes.getIdSelecionado() == clienteModelo.getId());

    }

    @Test
    public void testGetIdSelecionado() {

        System.out.println("Testando ComboBoxClientes metodo: getIdSelecionado");
        assertEquals(true, comboBoxClientes.getIdSelecionado() == 0);

    }

}
