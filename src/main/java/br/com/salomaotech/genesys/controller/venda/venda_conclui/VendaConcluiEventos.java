package br.com.salomaotech.genesys.controller.venda.venda_conclui;

import br.com.salomaotech.genesys.view.JFvendaConclui;
import br.com.salomaotech.sistema.algoritmos.ConverteNumeroParaMoedaBr;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;

public class VendaConcluiEventos {

    private final JFvendaConclui view;
    private VendaConcluiMetodos vendaConcluiMetodos;

    public VendaConcluiEventos(JFvendaConclui view) {
        this.view = view;
    }

    public void setVendaConcluiMetodos(VendaConcluiMetodos vendaConcluiMetodos) {
        this.vendaConcluiMetodos = vendaConcluiMetodos;
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

                view.jTvalorTroco.setText(ConverteNumeroParaMoedaBr.converter(vendaConcluiMetodos.calcularTroco().toString()));

            }

        });

        /* botão cancelar */
        view.jBcancelar.addActionListener((ActionEvent e) -> {

            view.dispose();

        });

        /* botão concluir */
        view.jBconcluir.addActionListener((ActionEvent e) -> {

            VendaConcluiValidador vendaConcluirValidador = new VendaConcluiValidador(vendaConcluiMetodos.getVendaModelo());

            /* valida se a venda pode ser finalizada */
            if (vendaConcluirValidador.isValido()) {

                vendaConcluiMetodos.finalizarVenda();

            } else {

                JOptionPane.showMessageDialog(null, vendaConcluirValidador.getMensagensErro());

            }

        });

        /* forma de pagamento */
        view.jCforma.addActionListener((ActionEvent e) -> {

            vendaConcluiMetodos.habilitarCampos();

        });

    }

}
