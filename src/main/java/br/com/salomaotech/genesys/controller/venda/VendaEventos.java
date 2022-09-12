package br.com.salomaotech.genesys.controller.venda;

import br.com.salomaotech.genesys.model.produto.ComboBoxProduto;
import br.com.salomaotech.genesys.model.produto.ProdutoModelo;
import br.com.salomaotech.genesys.view.JFvenda;
import br.com.salomaotech.sistema.algoritmos.ConverteNumeroParaMoedaBr;
import br.com.salomaotech.sistema.jpa.Repository;
import br.com.salomaotech.sistema.patterns.Command;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static java.util.Objects.isNull;

public class VendaEventos implements Command {

    private final JFvenda view;
    private VendaMetodos vendaMetodos;
    private ComboBoxProduto comboBoxProduto;
    private ProdutoModelo produtoModelo = new ProdutoModelo();

    public VendaEventos(JFvenda view) {
        this.view = view;
    }

    public void setVendaMetodos(VendaMetodos vendaMetodos) {
        this.vendaMetodos = vendaMetodos;
    }

    public void setComboBoxProduto(ComboBoxProduto comboBoxProduto) {
        this.comboBoxProduto = comboBoxProduto;
    }

    private void carregarProduto() {

        if (!isNull(comboBoxProduto)) {

            produtoModelo = (ProdutoModelo) new Repository(new ProdutoModelo()).findById(comboBoxProduto.getIdSelecionado());
            vendaMetodos.exibirProdutoSelecionado(produtoModelo);

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

                view.jTprodutoTotal.setText(ConverteNumeroParaMoedaBr.converter(vendaMetodos.calcularProdutoSelecionado(produtoModelo).toString()));

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

                view.jTprodutoTotal.setText(ConverteNumeroParaMoedaBr.converter(vendaMetodos.calcularProdutoSelecionado(produtoModelo).toString()));

            }

        });

        /* limpar produto selecionado */
        view.jBprodutoLimpaItem.addActionListener((ActionEvent e) -> {

            vendaMetodos.limparProdutoSelecionado();

        });

        /* adiciona um item de produto */
        view.jBprodutoAdicionaItem.addActionListener((ActionEvent e) -> {

            vendaMetodos.adicionarProdutoNaLista(produtoModelo);

        });

        /* finalizar venda */
        view.jBvendaFinaliza.addActionListener((ActionEvent e) -> {

            vendaMetodos.finalizarVenda();

        });

    }

    @Override
    public void executar(Object arg) {

        carregarProduto();

    }

}
