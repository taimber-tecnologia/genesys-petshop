package br.com.salomaotech.genesys.controller.venda.venda_calcula;

import br.com.salomaotech.genesys.model.produto.ProdutoModelo;
import br.com.salomaotech.genesys.view.JFvendaCalcula;
import br.com.salomaotech.sistema.algoritmos.BigDecimais;
import java.math.BigDecimal;
import static java.util.Objects.isNull;

public class VendaCalculaMetodos {

    private final JFvendaCalcula view;
    private ProdutoModelo produtoModelo;
    private BigDecimal quantidade = new BigDecimal(0);

    public VendaCalculaMetodos(JFvendaCalcula view) {
        this.view = view;
    }

    public void setProdutoModelo(ProdutoModelo produtoModelo) {
        this.produtoModelo = produtoModelo;
    }

    private BigDecimal calcularQuantidade(BigDecimal valor, int acao) {

        BigDecimal resultado = new BigDecimal(0);

        switch (acao) {

            case 1:
                resultado = valor.divide(produtoModelo.getValorVenda());
                break;

            case 2:
                resultado = BigDecimais.dividir(produtoModelo.getValorVenda(), produtoModelo.getPeso());
                break;

        }

        return resultado;

    }

    public void calcular(int acao) {

        if (!isNull(produtoModelo)) {

            switch (acao) {

                case 1:
                    quantidade = calcularQuantidade(BigDecimais.formatarParaBigDecimal(view.jTvalorDesejado.getText()), acao);
                    break;

                case 2:
                    quantidade = calcularQuantidade(BigDecimais.formatarParaBigDecimal(view.jTpesoKg.getText()), acao);
                    break;

            }

            System.out.println(">>: " + quantidade);

        }

    }

}
