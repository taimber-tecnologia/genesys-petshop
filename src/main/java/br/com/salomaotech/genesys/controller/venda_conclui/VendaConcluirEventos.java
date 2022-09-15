package br.com.salomaotech.genesys.controller.venda_conclui;

import br.com.salomaotech.genesys.view.JFvendaConcluir;
import br.com.salomaotech.sistema.algoritmos.ConverteNumeroParaMoedaBr;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;

public class VendaConcluirEventos {

    private final JFvendaConcluir view;
    private VendaConcluirMetodos vendaConcluirMetodos;

    public VendaConcluirEventos(JFvendaConcluir view) {
        this.view = view;
    }

    public void setVendaConcluirMetodos(VendaConcluirMetodos vendaConcluirMetodos) {
        this.vendaConcluirMetodos = vendaConcluirMetodos;
    }

    public void addEventos() {

        /* valor recebido */
        view.jTvalorRecebido.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

                view.jTvalorTroco.setText(ConverteNumeroParaMoedaBr.converter(vendaConcluirMetodos.calcularTroco().toString()));

            }

        });

        /* botão cancelar */
        view.jBcancelar.addActionListener((ActionEvent e) -> {

            view.dispose();

        });

        /* botão concluir */
        view.jBconcluir.addActionListener((ActionEvent e) -> {

            VendaConcluirValidador vendaConcluirValidador = new VendaConcluirValidador(vendaConcluirMetodos.getVendaModelo());

            /* valida se a venda pode ser finalizada */
            if (vendaConcluirValidador.isValido()) {

                vendaConcluirMetodos.finalizarVenda();

            } else {

                JOptionPane.showMessageDialog(null, vendaConcluirValidador.getMensagensErro());

            }

        });

        /* forma de pagamento */
        view.jCforma.addActionListener((ActionEvent e) -> {

            vendaConcluirMetodos.habilitarCampos();

        });

    }

}
