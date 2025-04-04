package br.com.salomaotech.genesys.controller.venda.venda_conclui;

import br.com.salomaotech.genesys.model.venda.VendaModelo;
import br.com.salomaotech.genesys.model.venda.VendaModeloItem;
import br.com.salomaotech.genesys.model.venda.VendaMovimenta;
import br.com.salomaotech.genesys.view.JFvendaInicia;
import br.com.salomaotech.genesys.view.JFvendaConclui;
import br.com.salomaotech.sistema.algoritmos.BigDecimais;
import br.com.salomaotech.sistema.algoritmos.ConverteNumeroParaMoedaBr;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import static java.util.Objects.isNull;

public class VendaConcluiMetodos {

    private final JFvendaConclui view;
    private final VendaModelo vendaModelo;
    private final JFvendaInicia viewVenda;
    private final List<VendaModeloItem> vendaModeloItemBaixaList;

    public VendaConcluiMetodos(JFvendaConclui view, VendaModelo vendaModelo, JFvendaInicia viewVenda, List<VendaModeloItem> vendaModeloItemBaixaList) {
        this.view = view;
        this.vendaModelo = vendaModelo;
        this.viewVenda = viewVenda;
        this.vendaModeloItemBaixaList = vendaModeloItemBaixaList;
    }

    public VendaModelo getVendaModelo() {
        return vendaModelo;
    }

    public void exibirVenda() {

        /* valida a data, se não houver uma defina a atual */
        if (!isNull(vendaModelo.getData())) {

            view.jDdata.setCalendar(vendaModelo.getData());

        } else {

            view.jDdata.setCalendar(Calendar.getInstance());

        }

        view.jTvalorTotal.setText(ConverteNumeroParaMoedaBr.converter(vendaModelo.getValor().toString()));
        view.jTvalorRecebido.setText(ConverteNumeroParaMoedaBr.converter(vendaModelo.getValor().toString()));
        view.jTvalorTroco.setText(ConverteNumeroParaMoedaBr.converter("0"));
        view.jCforma.setSelectedItem(vendaModelo.getFormaPagamento());

    }

    public BigDecimal calcularTroco() {

        BigDecimal valorVenda = vendaModelo.getValor();
        BigDecimal valorRecebido = BigDecimais.formatarParaBigDecimal(view.jTvalorRecebido.getText());
        BigDecimal valorTroco = valorRecebido.subtract(valorVenda);
        return valorTroco;

    }

    public void habilitarCampos() {

        /* evita erro de index */
        if (view.jCforma.getSelectedIndex() == -1) {

            view.jCforma.setSelectedIndex(0);

        }

        switch (view.jCforma.getSelectedItem().toString()) {

            case "Dinheiro":
                view.jTvalorRecebido.setEditable(true);
                view.jTvalorRecebido.setText(null);
                view.jTvalorTroco.setText(ConverteNumeroParaMoedaBr.converter("0"));

                break;

            case "Debito":
                view.jTvalorRecebido.setEditable(false);
                view.jTvalorRecebido.setText(ConverteNumeroParaMoedaBr.converter(vendaModelo.getValor().toString()));
                view.jTvalorTroco.setText(ConverteNumeroParaMoedaBr.converter("0"));

                break;

            case "Pix":
                view.jTvalorRecebido.setEditable(false);
                view.jTvalorRecebido.setText(ConverteNumeroParaMoedaBr.converter(vendaModelo.getValor().toString()));
                view.jTvalorTroco.setText(ConverteNumeroParaMoedaBr.converter("0"));

                break;

            default:
                view.jTvalorRecebido.setEditable(false);
                view.jTvalorRecebido.setText(ConverteNumeroParaMoedaBr.converter(vendaModelo.getValor().toString()));
                view.jTvalorTroco.setText(ConverteNumeroParaMoedaBr.converter("0"));

        }

    }

    public void executarAposFinalizarVenda() {

        viewVenda.dispose();
        view.jBconcluir.setEnabled(false);
        view.jBcancelar.setEnabled(false);
        view.jBimprimir.setEnabled(true);
        view.jCforma.setEnabled(false);
        view.jTvalorRecebido.setEnabled(false);
        view.jTvalorRecebido.setText(ConverteNumeroParaMoedaBr.converter(view.jTvalorRecebido.getText()));

    }

    public void finalizarVenda() {

        vendaModelo.setData(view.jDdata.getCalendar());
        vendaModelo.setHora(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" + Calendar.getInstance().get(Calendar.MINUTE));
        vendaModelo.setFormaPagamento(view.jCforma.getSelectedItem().toString());

        VendaMovimenta vendaMovimenta = new VendaMovimenta(vendaModelo, vendaModeloItemBaixaList);

        /* se o ID for diferente de zero então está atualizando */
        if (vendaModelo.getId() == 0) {

            vendaMovimenta.finalizar();

        } else {

            vendaMovimenta.reabrir();
            vendaMovimenta.finalizar();

        }

        executarAposFinalizarVenda();

    }

}
