package br.com.salomaotech.genesys.controller.venda.venda_calcula;

import br.com.salomaotech.genesys.controller.venda.venda_inicia.VendaIniciaMetodos;
import br.com.salomaotech.genesys.model.produto.ProdutoModelo;
import br.com.salomaotech.genesys.view.JFvendaCalcula;
import br.com.salomaotech.sistema.algoritmos.BigDecimais;
import java.math.BigDecimal;
import static java.util.Objects.isNull;

public class VendaCalculaMetodos {

    private final JFvendaCalcula view;
    private ProdutoModelo produtoModelo;
    private VendaIniciaMetodos vendaIniciaMetodos;

    public VendaCalculaMetodos(JFvendaCalcula view) {
        this.view = view;
    }

    public void setProdutoModelo(ProdutoModelo produtoModelo) {
        this.produtoModelo = produtoModelo;
    }

    public void setVendaIniciaMetodos(VendaIniciaMetodos vendaIniciaMetodos) {
        this.vendaIniciaMetodos = vendaIniciaMetodos;
    }

    public void calcularPorValor(BigDecimal valorDesejado) {

        if (!isNull(produtoModelo)) {

            /* divide o valor desejado pelo valor do produto */
            BigDecimal resultado = BigDecimais.dividir(valorDesejado, produtoModelo.getValorVenda());

            vendaIniciaMetodos.popularGranel(resultado, produtoModelo);
            view.jTresultado.setText(resultado.toString());
            view.jTpesoKg.setText(null);

        }

    }

    public void calcularPorPeso(BigDecimal pesoDesejado) {

        if (!isNull(produtoModelo)) {

            /* divide o peso desejado pelo peso do produto */
            BigDecimal resultado = BigDecimais.dividir(pesoDesejado, produtoModelo.getPeso());

            vendaIniciaMetodos.popularGranel(resultado, produtoModelo);
            view.jTresultado.setText(resultado.toString());
            view.jTvalorDesejado.setText(null);

        }

    }

}
