package br.com.salomaotech.genesys.controller.venda.venda_inicia;

import br.com.salomaotech.genesys.model.produto.ComboBoxProduto;
import br.com.salomaotech.genesys.model.produto.ImagemProduto;
import br.com.salomaotech.genesys.model.produto.ProdutoModelo;
import br.com.salomaotech.genesys.model.venda.VendaModelo;
import br.com.salomaotech.genesys.model.venda.VendaMovimenta;
import br.com.salomaotech.genesys.view.JFvendaInicia;
import br.com.salomaotech.sistema.algoritmos.ConverteNumeroParaMoedaBr;
import br.com.salomaotech.sistema.jpa.Repository;
import br.com.salomaotech.sistema.patterns.Command;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import static java.util.Objects.isNull;
import javax.swing.JOptionPane;

public class VendaIniciaEventos implements Command {

    private final JFvendaInicia view;
    private VendaIniciaMetodos vendaIniciaMetodos;
    private ComboBoxProduto comboBoxProduto;
    private ProdutoModelo produtoModelo = new ProdutoModelo();

    public VendaIniciaEventos(JFvendaInicia view) {
        this.view = view;
    }

    public void setVendaIniciaMetodos(VendaIniciaMetodos vendaIniciaMetodos) {
        this.vendaIniciaMetodos = vendaIniciaMetodos;
    }

    public void setComboBoxProduto(ComboBoxProduto comboBoxProduto) {
        this.comboBoxProduto = comboBoxProduto;
    }

    private void carregarProduto() {

        if (!isNull(comboBoxProduto)) {

            produtoModelo = (ProdutoModelo) new Repository(new ProdutoModelo()).findById(comboBoxProduto.getIdSelecionado());
            vendaIniciaMetodos.exibirProdutoSelecionado(produtoModelo);
            new ImagemProduto().exibir(String.valueOf(produtoModelo.getId()), view.jPdadosPerfilFoto);

        }

    }

    public void addEventos() {

        /* quantidade */
        view.jTprodutoQuantidade.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

                view.jTprodutoTotal.setText(ConverteNumeroParaMoedaBr.converter(vendaIniciaMetodos.calcularProdutoSelecionado(produtoModelo).toString()));

            }

        });

        /* desconto */
        view.jTprodutoDesconto.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

                view.jTprodutoTotal.setText(ConverteNumeroParaMoedaBr.converter(vendaIniciaMetodos.calcularProdutoSelecionado(produtoModelo).toString()));

            }

        });

        /* limpar produto selecionado */
        view.jBprodutoLimpaItem.addActionListener((ActionEvent e) -> {

            vendaIniciaMetodos.limparProdutoSelecionado();

        });

        /* adiciona um item de produto */
        view.jBprodutoAdicionaItem.addActionListener((ActionEvent e) -> {

            vendaIniciaMetodos.adicionarProdutoNaLista(produtoModelo);

        });

        /* finalizar venda */
        view.jBvendaFinaliza.addActionListener((ActionEvent e) -> {

            vendaIniciaMetodos.finalizarVenda();

        });

        /* excluir */
        view.jBvendaExcluir.addActionListener((ActionEvent e) -> {

            if (JOptionPane.showConfirmDialog(null, "Excluir registro?") == 0) {

                VendaModelo vendaModelo = (VendaModelo) new Repository(new VendaModelo()).findById(view.getId());

                /* valida se excluiu e atualiza os dados na view */
                if (vendaIniciaMetodos.excluir()) {

                    /* realiza a movimentação de uma venda */
                    VendaMovimenta vendaMovimenta = new VendaMovimenta(vendaModelo);
                    vendaMovimenta.excluir();

                    /* informa que foi excluido, e fecha a view */
                    JOptionPane.showMessageDialog(null, "Registro excluido! Acompanhe as contas a receber no seu financeiro.");
                    view.dispose();

                } else {

                    JOptionPane.showMessageDialog(null, "Registro não excluido!");

                }

            }

        });

        /* produto selecionado */
        view.jTprodutoSelecionado.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                vendaIniciaMetodos.habilitarCamposDeExcluirProdutoAdicionado();

            }

        });

        /* produto selecionado */
        view.jTprodutoSelecionado.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

                vendaIniciaMetodos.habilitarCamposDeExcluirProdutoAdicionado();

            }

        });

        /* remover item de produto selecionado */
        view.jBprodutoSelecionadoRemoverItem.addActionListener((ActionEvent e) -> {

            vendaIniciaMetodos.removerProdutoNaLista();

        });

        /* evento da view */
        view.addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {

                /* limpa o cache em memória */
                new Repository(new VendaModelo()).limparCache();

            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }

        });

    }

    @Override
    public void executar(Object arg) {

        carregarProduto();

    }

}
