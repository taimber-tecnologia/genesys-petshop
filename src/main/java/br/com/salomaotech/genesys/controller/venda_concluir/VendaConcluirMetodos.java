package br.com.salomaotech.genesys.controller.venda_concluir;

import br.com.salomaotech.genesys.model.venda.VendaModelo;
import br.com.salomaotech.genesys.view.JFvendaConcluir;
import br.com.salomaotech.sistema.algoritmos.BigDecimais;
import br.com.salomaotech.sistema.algoritmos.ConverteNumeroParaMoedaBr;
import java.math.BigDecimal;

public class VendaConcluirMetodos {

    private final JFvendaConcluir view;
    private final VendaModelo vendaModelo;

    public VendaConcluirMetodos(JFvendaConcluir view, VendaModelo vendaModelo) {
        this.view = view;
        this.vendaModelo = vendaModelo;
    }

    public void exibirVenda() {

        view.jTvalorTotal.setText(ConverteNumeroParaMoedaBr.converter(vendaModelo.getValorTotal().toString()));

    }

    public void calcularTroco() {

        BigDecimal valorVenda = vendaModelo.getValor();
        BigDecimal valorRecebido = BigDecimais.formatarParaBigDecimal(view.jTvalorRecebido.getText());
        BigDecimal valorTroco = valorRecebido.subtract(valorVenda);
        view.jTvalorTroco.setText(ConverteNumeroParaMoedaBr.converter(valorTroco.toString()));

    }

}
