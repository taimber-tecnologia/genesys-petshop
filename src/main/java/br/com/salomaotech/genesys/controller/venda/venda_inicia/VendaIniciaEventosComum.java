package br.com.salomaotech.genesys.controller.venda.venda_inicia;

import br.com.salomaotech.genesys.controller.venda.venda_pesquisa.VendaPesquisaController;
import br.com.salomaotech.genesys.model.configuracoes.PastasSistema;
import br.com.salomaotech.genesys.model.venda.VendaComprovantePdf;
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
    private final VendaModeloItem vendaModeloItemCompartilhado;

    public VendaIniciaEventosComum(JFvendaInicia view, VendaIniciaMetodosComum vendaIniciaMetodosComum, VendaModeloItem vendaModeloItemCompartilhado) {
        this.view = view;
        this.vendaIniciaMetodosComum = vendaIniciaMetodosComum;
        this.vendaModeloItemCompartilhado = vendaModeloItemCompartilhado;
    }

    public void addEventos() {

        /* habilita campo de exclusão de item de venda selecionado na lista */
        view.jTitensSelecionados.getSelectionModel().addListSelectionListener(e -> {

            int linha = view.jTitensSelecionados.getSelectedRow();

            if (linha >= 0) {

                vendaIniciaMetodosComum.habilitarCamposDeExcluirItemAdicionado();

            }

        });

        /* remove item de venda selecionado na lista */
        view.jBremoveItemSelecionadoLista.addActionListener((ActionEvent e) -> {

            vendaIniciaMetodosComum.removerItemNaLista();

        });

        /* informa que é um produto selecionado */
        view.jTlistaDeProdutos.getSelectionModel().addListSelectionListener(e -> {

            int linha = view.jTlistaDeProdutos.getSelectedRow();

            if (linha >= 0) {

                long id = (long) view.jTlistaDeProdutos.getModel().getValueAt(linha, 0);
                vendaModeloItemCompartilhado.setIdProduto(id);
                vendaModeloItemCompartilhado.setIdServico(0);

            }

        });

        /* informa que é um serviço selecionado */
        view.jTlistaDeServicos.getSelectionModel().addListSelectionListener(e -> {

            int linha = view.jTlistaDeServicos.getSelectedRow();

            if (linha >= 0) {

                long id = (long) view.jTlistaDeServicos.getModel().getValueAt(linha, 0);
                vendaModeloItemCompartilhado.setIdProduto(0);
                vendaModeloItemCompartilhado.setIdServico(id);

            }

        });

        /* adiciona um item de venda a lista de itens adicionados */
        view.jTitemQuantidade.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

                /* adiciona um item a lista de itens selecionados */
                if (e.getKeyCode() == 10) {

                    if (BigDecimais.formatarParaBigDecimal(view.jTitemQuantidade.getText()).compareTo(BigDecimal.ZERO) > 0) {

                        vendaIniciaMetodosComum.addItemNaLista();

                    } else {

                        JOptionPane.showMessageDialog(null, "Informe a quantidade.");
                        view.jTitemQuantidade.setText("");
                        view.jTitemQuantidade.requestFocus();

                    }

                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

                /* faz cálculo com base na quantidade de itens adicionados */
                view.jTitemTotal.setText(ConverteNumeroParaMoedaBr.converter(vendaIniciaMetodosComum.calcularItemSelecionadao(vendaModeloItemCompartilhado).toString()));

            }

        });

        /* finalizar venda */
        view.jBvendaFinaliza.addActionListener((ActionEvent e) -> {

            vendaIniciaMetodosComum.finalizarVenda();

        });

        /* excluir */
        view.jBvendaExcluir.addActionListener((ActionEvent e) -> {

            if (JOptionPane.showConfirmDialog(null, "Excluir registro?") == 0) {

                /* valida se excluiu e atualiza os dados na view */
                if (vendaIniciaMetodosComum.excluir()) {

                    /* informa que foi excluido, e fecha a view */
                    JOptionPane.showMessageDialog(null, "Registro excluido! Acompanhe as contas a receber no seu financeiro.");
                    new VendaPesquisaController().construir();
                    view.dispose();

                } else {

                    JOptionPane.showMessageDialog(null, "Registro não excluido!");

                }

            }

        });

        /* atalho para venda */
        view.jBatalhoCadastro.addActionListener((ActionEvent e) -> {

            new VendaIniciaController().construir();
            view.dispose();

        });

        /* atalho para histórico de vendas */
        view.jBatalhoPesquisa.addActionListener((ActionEvent e) -> {

            new VendaPesquisaController().construir();
            view.dispose();

        });

        /* botão imprimir */
        view.jBimprimir.addActionListener((ActionEvent e) -> {

            new VendaComprovantePdf(new PastasSistema().getSubPastaImpressao(), view.getId()).gerar();

        });

    }

}
