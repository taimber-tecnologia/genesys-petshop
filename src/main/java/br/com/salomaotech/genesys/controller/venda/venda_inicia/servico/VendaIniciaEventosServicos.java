package br.com.salomaotech.genesys.controller.venda.venda_inicia.servico;

import br.com.salomaotech.genesys.controller.venda.venda_inicia.VendaIniciaMetodosComum;
import br.com.salomaotech.genesys.view.JFvendaInicia;
import br.com.salomaotech.sistema.algoritmos.BigDecimais;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import javax.swing.JOptionPane;

public class VendaIniciaEventosServicos {

    private final JFvendaInicia view;
    private final VendaIniciaMetodosComum vendaIniciaMetodosComum;
    private final VendaIniciaMetodosServicos vendaIniciaMetodosServicos;

    public VendaIniciaEventosServicos(JFvendaInicia view, VendaIniciaMetodosComum vendaIniciaMetodosComum, VendaIniciaMetodosServicos vendaIniciaMetodosServicos) {
        this.view = view;
        this.vendaIniciaMetodosComum = vendaIniciaMetodosComum;
        this.vendaIniciaMetodosServicos = vendaIniciaMetodosServicos;
    }

    private void addItemLista() {

        int linha = view.jTlistaDeServicos.getSelectedRow();

        if (linha >= 0) {

            BigDecimal quantidade = BigDecimais.formatarParaBigDecimal(view.jTitemQuantidade.getText());

            if (quantidade.compareTo(BigDecimal.ZERO) > 0) {

                vendaIniciaMetodosComum.addItemNaLista();

            } else {

                JOptionPane.showMessageDialog(null, "Informe a quantidade.");
                view.jTitemQuantidade.setText("");
                view.jTitemQuantidade.requestFocus();

            }

        }

    }

    public void addEventos() {

        view.jBservicoAdicionaItem.addActionListener((ActionEvent e) -> {

            addItemLista();

        });

        view.jTpesquisaNomeServico.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {

                vendaIniciaMetodosServicos.pesquisarServicos();

            }

        });

        view.jTlistaDeServicos.getSelectionModel().addListSelectionListener(e -> {

            int linha = view.jTlistaDeServicos.getSelectedRow();

            if (linha >= 0) {

                long id = (long) view.jTlistaDeServicos.getModel().getValueAt(linha, 0);
                vendaIniciaMetodosServicos.exibirServicoSelecionado(id);

            }

        });

        view.jTlistaDeServicos.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                int linha = view.jTlistaDeServicos.getSelectedRow();

                if (linha >= 0) {

                    long id = (long) view.jTlistaDeServicos.getModel().getValueAt(linha, 0);
                    vendaIniciaMetodosServicos.exibirServicoSelecionado(id);

                }

            }

        });

    }

}
