package br.com.salomaotech.genesys.controller.venda.venda_calcula;

import org.junit.Test;
import static org.junit.Assert.*;

public class VendaCalculaEventosTest {

    public VendaCalculaEventosTest() {

    }

    @Test
    public void testSetVendaCalculaMetodos() {

        System.out.println("setVendaCalculaMetodos");
        VendaCalculaMetodos vendaCalculaMetodos = null;
        VendaCalculaEventos instance = null;
        instance.setVendaCalculaMetodos(vendaCalculaMetodos);

    }

    @Test
    public void testAddEventos() {

        System.out.println("addEventos");
        VendaCalculaEventos instance = null;
        instance.addEventos();

    }

}
