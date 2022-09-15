package br.com.salomaotech.genesys.controller.venda.venda_inicia;

import br.com.salomaotech.genesys.model.produto.ComboBoxProduto;
import br.com.salomaotech.genesys.model.produto.ProdutoModelo;
import br.com.salomaotech.genesys.view.JFvendaInicia;
import br.com.salomaotech.sistema.algoritmos.ConverteNumeroParaMoedaBr;
import br.com.salomaotech.sistema.jpa.Repository;
import br.com.salomaotech.sistema.patterns.Command;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static java.util.Objects.isNull;

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

    }

    @Override
    public void executar(Object arg) {

        carregarProduto();

    }

}
