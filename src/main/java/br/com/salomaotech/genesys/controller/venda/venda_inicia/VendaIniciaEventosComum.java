package br.com.salomaotech.genesys.controller.venda.venda_inicia;

import br.com.salomaotech.genesys.model.venda.VendaModeloItem;
import br.com.salomaotech.genesys.view.JFvendaInicia;
import br.com.salomaotech.sistema.algoritmos.BigDecimais;
import br.com.salomaotech.sistema.algoritmos.ConverteNumeroParaMoedaBr;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import javax.swing.JOptionPane;

public class VendaIniciaEventosComum {

    private final JFvendaInicia view;
    private final VendaIniciaMetodosComum vendaIniciaMetodosComum;
    private final VendaModeloItem vendaModeloItem = new VendaModeloItem();

    public VendaIniciaEventosComum(JFvendaInicia view, VendaIniciaMetodosComum vendaIniciaMetodosComum) {
        this.view = view;
        this.vendaIniciaMetodosComum = vendaIniciaMetodosComum;
    }

    public void addEventos() {

        /* habilita campos para excluir item de venda selecionado */
        view.jTitensSelecionados.getSelectionModel().addListSelectionListener(e -> {

            int linha = view.jTitensSelecionados.getSelectedRow();

            if (linha >= 0) {

                vendaIniciaMetodosComum.habilitarCamposDeExcluirItemAdicionado();

            }

        });

        /* remover item de itens selecionados */
        view.jBremoveItemSelecionadoLista.addActionListener((ActionEvent e) -> {

            vendaIniciaMetodosComum.removerItemNaLista();

        });

        /* informa que é o produto que está selecionado */
        view.jTlistaDeProdutos.getSelectionModel().addListSelectionListener(e -> {

            int linha = view.jTlistaDeProdutos.getSelectedRow();

            if (linha >= 0) {

                long id = (long) view.jTlistaDeProdutos.getModel().getValueAt(linha, 0);
                vendaModeloItem.setIdProduto(id);
                vendaModeloItem.setIdServico(0);

            }

        });

        /* informa que é o serviço que está selecionado */
        view.jTlistaDeServicos.getSelectionModel().addListSelectionListener(e -> {

            int linha = view.jTlistaDeServicos.getSelectedRow();

            if (linha >= 0) {

                long id = (long) view.jTlistaDeServicos.getModel().getValueAt(linha, 0);
                vendaModeloItem.setIdProduto(0);
                vendaModeloItem.setIdServico(id);

            }

        });

        /* calcula valor total do itens selecionados */
        view.jTitemQuantidade.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == 10) {

                    // pega a quantidade informada pelo usuário
                    BigDecimal quantidade = BigDecimais.formatarParaBigDecimal(view.jTitemQuantidade.getText());

                    // se a quantidade for maior do que zero, então permite adicionar
                    if (quantidade.compareTo(BigDecimal.ZERO) > 0) {

                        vendaIniciaMetodosComum.addItemNaLista(vendaModeloItem);

                    } else {

                        JOptionPane.showMessageDialog(null, "Informe a quantidade.");
                        view.jTitemQuantidade.setText("");
                        view.jTitemQuantidade.requestFocus();

                    }

                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

                view.jTitemTotal.setText(ConverteNumeroParaMoedaBr.converter(vendaIniciaMetodosComum.calcularItemSelecionadao(vendaModeloItem).toString()));

            }

        });

    }

}
