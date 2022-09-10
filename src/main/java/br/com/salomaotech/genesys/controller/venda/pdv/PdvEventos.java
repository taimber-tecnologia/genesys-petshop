package br.com.salomaotech.genesys.controller.venda.pdv;

import br.com.salomaotech.genesys.model.produto.ComboBoxProduto;
import br.com.salomaotech.genesys.model.produto.ProdutoModelo;
import br.com.salomaotech.genesys.view.JFpdv;
import br.com.salomaotech.sistema.jpa.Repository;
import br.com.salomaotech.sistema.patterns.Command;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static java.util.Objects.isNull;

public class PdvEventos implements Command {

    private final JFpdv view;
    private PdvMetodos pdvMetodos;
    private ComboBoxProduto comboBoxProduto;
    private ProdutoModelo produtoModelo = new ProdutoModelo();

    public PdvEventos(JFpdv view) {
        this.view = view;
    }

    public void setPdvMetodos(PdvMetodos pdvMetodos) {
        this.pdvMetodos = pdvMetodos;
    }

    public void setComboBoxProduto(ComboBoxProduto comboBoxProduto) {
        this.comboBoxProduto = comboBoxProduto;
    }

    private void carregarProduto() {

        if (!isNull(comboBoxProduto)) {

            produtoModelo = (ProdutoModelo) new Repository(new ProdutoModelo()).findById(comboBoxProduto.getIdSelecionado());
            pdvMetodos.apresentarItem(produtoModelo);

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

                pdvMetodos.calcularItem(produtoModelo);

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

                pdvMetodos.calcularItem(produtoModelo);

            }

        });

    }

    @Override
    public void executar(Object arg) {

        carregarProduto();

    }

}
