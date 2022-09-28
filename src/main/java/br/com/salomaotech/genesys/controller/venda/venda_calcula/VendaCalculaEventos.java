package br.com.salomaotech.genesys.controller.venda.venda_calcula;

import br.com.salomaotech.genesys.view.JFvendaCalcula;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class VendaCalculaEventos {

    private final JFvendaCalcula view;
    private VendaCalculaMetodos vendaCalculaMetodos;

    public VendaCalculaEventos(JFvendaCalcula view) {
        this.view = view;
    }

    public void setVendaCalculaMetodos(VendaCalculaMetodos vendaCalculaMetodos) {
        this.vendaCalculaMetodos = vendaCalculaMetodos;
    }

    public void addEventos() {

        /* valor */
        view.jTvalorDesejado.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

                vendaCalculaMetodos.calcular(1);

            }

        });

        /* peso KG */
        view.jTpesoKg.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

                vendaCalculaMetodos.calcular(2);

            }

        });

    }

}
