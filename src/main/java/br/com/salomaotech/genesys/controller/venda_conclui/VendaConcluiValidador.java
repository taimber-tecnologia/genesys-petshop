package br.com.salomaotech.genesys.controller.venda_conclui;

import br.com.salomaotech.genesys.model.venda.VendaModelo;
import java.math.BigDecimal;

public class VendaConcluiValidador {

    private final VendaModelo vendaModelo;
    private String mensagensErro = "";

    public VendaConcluiValidador(VendaModelo vendaModelo) {
        this.vendaModelo = vendaModelo;
    }

    public boolean isValido() {

        if (vendaModelo.getValor().equals(new BigDecimal(0))) {

            mensagensErro = "A venda não possui um valor.";
            return false;

        }

        return true;

    }

    public String getMensagensErro() {
        return mensagensErro;
    }

}
