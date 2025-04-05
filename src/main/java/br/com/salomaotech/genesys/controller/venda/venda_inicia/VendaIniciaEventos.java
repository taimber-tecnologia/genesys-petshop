package br.com.salomaotech.genesys.controller.venda.venda_inicia;

import br.com.salomaotech.genesys.controller.venda.venda_calcula.VendaCalculaController;
import br.com.salomaotech.genesys.controller.venda.venda_pesquisa.VendaPesquisaController;
import br.com.salomaotech.genesys.model.configuracoes.PastasSistema;
import br.com.salomaotech.genesys.model.venda.VendaComprovantePdf;
import br.com.salomaotech.genesys.view.JFvendaInicia;
import br.com.salomaotech.sistema.algoritmos.BigDecimais;
import br.com.salomaotech.sistema.algoritmos.ConverteNumeroParaMoedaBr;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import javax.swing.JOptionPane;

public class VendaIniciaEventos {

    private final JFvendaInicia view;
    private VendaIniciaMetodos vendaIniciaMetodos;

    public VendaIniciaEventos(JFvendaInicia view) {
        this.view = view;
    }

    public void setVendaIniciaMetodos(VendaIniciaMetodos vendaIniciaMetodos) {
        this.vendaIniciaMetodos = vendaIniciaMetodos;
    }

    private void addProdutoLista() {

        int linha = view.jTprodutoListaDeProdutos.getSelectedRow();

        // valida se há um produto selecionado na lista
        if (linha >= 0) {

            // pega a quantidade informada pelo usuário
            BigDecimal quantidade = BigDecimais.formatarParaBigDecimal(view.jTprodutoQuantidade.getText());

            // se a quantidade for maior do que zero, então permite adicionar
            if (quantidade.compareTo(BigDecimal.ZERO) > 0) {

                long id = (long) view.jTprodutoListaDeProdutos.getModel().getValueAt(linha, 0);
                vendaIniciaMetodos.adicionarProdutoNaLista(id);

            } else {

                JOptionPane.showMessageDialog(null, "Informe a quantidade.");
                view.jTprodutoQuantidade.setText("");
                view.jTprodutoQuantidade.requestFocus();

            }

        }

    }

    public void addEventos() {

        /* calcula valor total do itens selecionados */
        view.jTprodutoQuantidade.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == 10) {

                    addProdutoLista();

                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

                int linha = view.jTprodutoListaDeProdutos.getSelectedRow();

                if (linha >= 0) {

                    long id = (long) view.jTprodutoListaDeProdutos.getModel().getValueAt(linha, 0);
                    view.jTprodutoTotal.setText(ConverteNumeroParaMoedaBr.converter(vendaIniciaMetodos.calcularProdutoSelecionado(id).toString()));

                }

            }

        });

        /* adiciona um item de produto */
        view.jBprodutoAdicionaItem.addActionListener((ActionEvent e) -> {

            addProdutoLista();

        });

        /* finalizar venda */
        view.jBvendaFinaliza.addActionListener((ActionEvent e) -> {

            vendaIniciaMetodos.finalizarVenda();

        });

        /* excluir */
        view.jBvendaExcluir.addActionListener((ActionEvent e) -> {

            if (JOptionPane.showConfirmDialog(null, "Excluir registro?") == 0) {

                /* valida se excluiu e atualiza os dados na view */
                if (vendaIniciaMetodos.excluir()) {

                    /* informa que foi excluido, e fecha a view */
                    JOptionPane.showMessageDialog(null, "Registro excluido! Acompanhe as contas a receber no seu financeiro.");
                    view.dispose();

                } else {

                    JOptionPane.showMessageDialog(null, "Registro não excluido!");

                }

            }

        });

        /* habilita campos para excluir item de venda selecionado */
        view.jTitensSelecionados.getSelectionModel().addListSelectionListener(e -> {

            int linha = view.jTitensSelecionados.getSelectedRow();

            if (linha >= 0) {

                vendaIniciaMetodos.habilitarCamposDeExcluirProdutoAdicionado();

            }

        });

        /* remover item de itens selecionados */
        view.jBprodutoSelecionadoRemoverItem.addActionListener((ActionEvent e) -> {

            vendaIniciaMetodos.removerProdutoNaLista();

        });

        /* botão imprimir */
        view.jBimprimir.addActionListener((ActionEvent e) -> {

            new VendaComprovantePdf(new PastasSistema().getSubPastaImpressao(), view.getId()).gerar();

        });

        /* botão calcular granel */
        view.jBcalcularGranel.addActionListener((ActionEvent e) -> {

            int linha = view.jTprodutoListaDeProdutos.getSelectedRow();

            if (linha >= 0) {

                long id = (long) view.jTprodutoListaDeProdutos.getModel().getValueAt(linha, 0);
                new VendaCalculaController(id, vendaIniciaMetodos).construir();

            }

        });

        /* pesquisa produto */
        view.jTpesquisaNomeProduto.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

                vendaIniciaMetodos.pesquisarProdutos();

            }

        });


        /* exibe os dados do itens selecionados */
        view.jTprodutoListaDeProdutos.getSelectionModel().addListSelectionListener(e -> {

            int linha = view.jTprodutoListaDeProdutos.getSelectedRow();

            if (linha >= 0) {

                long id = (long) view.jTprodutoListaDeProdutos.getModel().getValueAt(linha, 0);
                vendaIniciaMetodos.exibirProdutoSelecionado(id);
                view.jTprodutoQuantidade.requestFocus();

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

    }

}
