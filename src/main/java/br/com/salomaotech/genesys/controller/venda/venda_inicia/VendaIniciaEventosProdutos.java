package br.com.salomaotech.genesys.controller.venda.venda_inicia;

import br.com.salomaotech.genesys.controller.venda.venda_calcula.VendaCalculaControllerProdutos;
import br.com.salomaotech.genesys.view.JFvendaInicia;
import br.com.salomaotech.sistema.algoritmos.BigDecimais;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;

public class VendaIniciaEventosProdutos {

    private final JFvendaInicia view;
    private final VendaIniciaMetodosProdutos vendaIniciaMetodosProdutos;

    public VendaIniciaEventosProdutos(JFvendaInicia view, VendaIniciaMetodosProdutos vendaIniciaMetodosProdutos) {

        this.view = view;
        this.vendaIniciaMetodosProdutos = vendaIniciaMetodosProdutos;

    }

    private void addItemLista() {

        int linha = view.jTlistaDeProdutos.getSelectedRow();

        if (linha >= 0) {

            BigDecimal quantidade = BigDecimais.formatarParaBigDecimal(view.jTitemQuantidade.getText());

            if (quantidade.compareTo(BigDecimal.ZERO) > 0) {

                long id = (long) view.jTlistaDeProdutos.getModel().getValueAt(linha, 0);
                vendaIniciaMetodosProdutos.adicionarProdutoNaLista(id);

            } else {

                javax.swing.JOptionPane.showMessageDialog(null, "Informe a quantidade.");
                view.jTitemQuantidade.setText("");
                view.jTitemQuantidade.requestFocus();

            }

        }

    }

    public void addEventos() {

        view.jBprodutoAdicionaItem.addActionListener((ActionEvent e) -> {

            addItemLista();

        });

        view.jBcalcularGranel.addActionListener((ActionEvent e) -> {

            int linha = view.jTlistaDeProdutos.getSelectedRow();

            if (linha >= 0) {

                long id = (long) view.jTlistaDeProdutos.getModel().getValueAt(linha, 0);
                new VendaCalculaControllerProdutos(id, vendaIniciaMetodosProdutos).construir();

            }

        });

        view.jTpesquisaNomeProduto.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {

                vendaIniciaMetodosProdutos.pesquisarProdutos();

            }

        });

        view.jTlistaDeProdutos.getSelectionModel().addListSelectionListener(e -> {

            int linha = view.jTlistaDeProdutos.getSelectedRow();

            if (linha >= 0) {

                long id = (long) view.jTlistaDeProdutos.getModel().getValueAt(linha, 0);
                vendaIniciaMetodosProdutos.exibirProdutoSelecionado(id);

            }

        });

    }

}
