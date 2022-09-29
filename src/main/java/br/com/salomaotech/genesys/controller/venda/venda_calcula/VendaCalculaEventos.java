package br.com.salomaotech.genesys.controller.venda.venda_calcula;

import br.com.salomaotech.genesys.view.JFvendaCalcula;
import br.com.salomaotech.sistema.algoritmos.BigDecimais;
import java.awt.event.ActionEvent;
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

                vendaCalculaMetodos.calcularPorValor(BigDecimais.formatarParaBigDecimal(view.jTvalorDesejado.getText()));

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

                vendaCalculaMetodos.calcularPorPeso(BigDecimais.formatarParaBigDecimal(view.jTpesoKg.getText()));

            }

        });

        /* fecha */
        view.jBfechar.addActionListener((ActionEvent e) -> {

            view.dispose();

        });

    }

}
