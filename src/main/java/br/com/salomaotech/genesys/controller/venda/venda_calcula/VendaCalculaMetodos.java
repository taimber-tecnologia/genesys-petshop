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

    private BigDecimal calcularQuantidade(BigDecimal valor, int acao) {

        BigDecimal resultado = new BigDecimal(0);

        switch (acao) {

            case 1:
                resultado = BigDecimais.dividir(valor, produtoModelo.getValorVenda());
                break;

            case 2:
                resultado = BigDecimais.dividir(valor, produtoModelo.getPeso());
                break;

        }

        return resultado;

    }

    public void calcular(int acao) {

        BigDecimal quantidade = new BigDecimal(0);

        if (!isNull(produtoModelo)) {

            switch (acao) {

                case 1:
                    quantidade = calcularQuantidade(BigDecimais.formatarParaBigDecimal(view.jTvalorDesejado.getText()), acao);
                    view.jTpesoKg.setText(null);
                    break;

                case 2:
                    quantidade = calcularQuantidade(BigDecimais.formatarParaBigDecimal(view.jTpesoKg.getText()), acao);
                    view.jTvalorDesejado.setText(null);
                    break;

            }

            /* repassa a quantidade para a tela inicial */
            vendaIniciaMetodos.popularGranel(quantidade, produtoModelo);

        }

    }

}
