package br.com.salomaotech.genesys.controller.venda.venda_calcula;

import br.com.salomaotech.genesys.model.venda.ItemVenda;
import br.com.salomaotech.genesys.controller.venda.venda_inicia.produto.VendaIniciaMetodosProdutos;
import br.com.salomaotech.genesys.view.JFvendaCalcula;
import br.com.salomaotech.sistema.algoritmos.BigDecimais;
import java.math.BigDecimal;
import static java.util.Objects.isNull;

public class VendaCalculaMetodosProdutos {

    private final JFvendaCalcula view;
    private ItemVenda itemVenda;
    private VendaIniciaMetodosProdutos vendaIniciaMetodosProdutos;

    public VendaCalculaMetodosProdutos(JFvendaCalcula view) {
        this.view = view;
    }

    public void setItemVenda(ItemVenda itemVenda) {
        this.itemVenda = itemVenda;
    }

    public void setVendaIniciaMetodosProdutos(VendaIniciaMetodosProdutos vendaIniciaMetodosProdutos) {
        this.vendaIniciaMetodosProdutos = vendaIniciaMetodosProdutos;
    }

    public void calcularPorValor(BigDecimal valorDesejado) {

        if (!isNull(itemVenda)) {

            /* divide o valor desejado pelo valor do produto */
            BigDecimal resultado = BigDecimais.dividir(valorDesejado, itemVenda.getValor());

            vendaIniciaMetodosProdutos.popularGranel(resultado, itemVenda);
            view.jTresultado.setText(resultado.toString());
            view.jTpesoKg.setText(null);

        }

    }

    public void calcularPorPeso(BigDecimal pesoDesejado) {

        if (!isNull(itemVenda)) {

            /* divide o peso desejado pelo peso do produto */
            BigDecimal resultado = BigDecimais.dividir(pesoDesejado, itemVenda.getPeso());

            vendaIniciaMetodosProdutos.popularGranel(resultado, itemVenda);
            view.jTresultado.setText(resultado.toString());
            view.jTvalorDesejado.setText(null);

        }

    }

}
