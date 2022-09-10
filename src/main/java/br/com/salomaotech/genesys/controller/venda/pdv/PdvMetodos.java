package br.com.salomaotech.genesys.controller.venda.pdv;

import br.com.salomaotech.genesys.model.produto.ProdutoModelo;
import br.com.salomaotech.genesys.view.JFpdv;
import br.com.salomaotech.sistema.algoritmos.BigDecimais;
import br.com.salomaotech.sistema.algoritmos.ConverteNumeroParaMoedaBr;
import java.math.BigDecimal;

public class PdvMetodos {

    private final JFpdv view;

    public PdvMetodos(JFpdv view) {
        this.view = view;
    }

    public void apresentarItem(ProdutoModelo produtoModelo) {

        view.jTprodutoCodigo.setText(String.valueOf(produtoModelo.getId()));
        view.jTprodutoDescricao.setText(produtoModelo.getDescricao());
        view.jTprodutoPreco.setText(ConverteNumeroParaMoedaBr.converter(produtoModelo.getValorVenda().toString()));

    }

    public void calcularItem(ProdutoModelo produtoModelo) {

        BigDecimal quantidade = BigDecimais.formatarParaBigDecimal(view.jTprodutoQuantidade.getText());
        BigDecimal desconto = BigDecimais.formatarParaBigDecimal(view.jTprodutoDesconto.getText());
        BigDecimal total = (produtoModelo.getValorVenda().multiply(quantidade)).subtract(desconto);

        view.jTprodutoTotal.setText(ConverteNumeroParaMoedaBr.converter(total.toString()));

    }

    public void habilitarCampos() {

    }

    public void addPopUpMenu() {

    }

}
