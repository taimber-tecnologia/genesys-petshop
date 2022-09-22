package br.com.salomaotech.genesys.controller.venda.venda_conclui;

import br.com.salomaotech.genesys.model.cliente.ComboBoxClientes;
import br.com.salomaotech.genesys.model.venda.VendaModelo;
import br.com.salomaotech.genesys.view.JFvendaConclui;
import br.com.salomaotech.genesys.view.JFvendaInicia;
import javax.swing.JComboBox;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class VendaConcluiEventosTest {

    private final JFvendaConclui view = new JFvendaConclui();
    private final JFvendaInicia jFvendaInicia = new JFvendaInicia();
    private final VendaConcluiEventos vendaConcluiEventos = new VendaConcluiEventos(view);
    private final VendaModelo vendaModelo = new VendaModelo();
    private final ComboBoxClientes comboBoxClientes = new ComboBoxClientes(new JComboBox());

    @Test
    public void testSetVendaConcluiMetodos() {

        boolean isErro = false;

        try {

            vendaConcluiEventos.setVendaConcluiMetodos(new VendaConcluiMetodos(view, vendaModelo, jFvendaInicia, comboBoxClientes));

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe VendaConcluiEventos metodo: setVendaConcluiMetodos");
        assertEquals(false, isErro);

    }

    @Test
    public void testAddEventos() {

        vendaConcluiEventos.addEventos();

        System.out.println("Testando classe VendaConcluiEventos metodo: addEventos");
        assertEquals(true, view.jTvalorRecebido.getKeyListeners().length == 1);
        assertEquals(true, view.jBcancelar.getActionListeners().length == 1);
        assertEquals(true, view.jBconcluir.getActionListeners().length == 1);
        assertEquals(true, view.jCforma.getActionListeners().length == 1);

    }

}
