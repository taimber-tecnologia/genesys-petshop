package br.com.salomaotech.genesys.controller.venda.venda_conclui;

import br.com.salomaotech.genesys.model.cliente.ComboBoxClientes;
import br.com.salomaotech.genesys.model.venda.VendaModelo;
import br.com.salomaotech.genesys.model.venda.VendaMovimenta;
import br.com.salomaotech.genesys.view.JFvendaInicia;
import br.com.salomaotech.genesys.view.JFvendaConclui;
import br.com.salomaotech.sistema.algoritmos.BigDecimais;
import br.com.salomaotech.sistema.algoritmos.ConverteNumeroParaMoedaBr;
import java.math.BigDecimal;
import java.util.Calendar;

public class VendaConcluiMetodos {

    private final JFvendaConclui view;
    private final VendaModelo vendaModelo;
    private final JFvendaInicia viewVenda;
    private final ComboBoxClientes comboBoxClientes;

    public VendaConcluiMetodos(JFvendaConclui view, VendaModelo vendaModelo, JFvendaInicia viewVenda, ComboBoxClientes comboBoxClientes) {
        this.view = view;
        this.vendaModelo = vendaModelo;
        this.viewVenda = viewVenda;
        this.comboBoxClientes = comboBoxClientes;
    }

    public VendaModelo getVendaModelo() {
        return vendaModelo;
    }

    public void exibirVenda() {

        view.jTvalorTotal.setText(ConverteNumeroParaMoedaBr.converter(vendaModelo.getValor().toString()));
        view.jTvalorRecebido.setText(ConverteNumeroParaMoedaBr.converter(vendaModelo.getValor().toString()));
        view.jTvalorTroco.setText(ConverteNumeroParaMoedaBr.converter("0"));

    }

    public BigDecimal calcularTroco() {

        BigDecimal valorVenda = vendaModelo.getValor();
        BigDecimal valorRecebido = BigDecimais.formatarParaBigDecimal(view.jTvalorRecebido.getText());
        BigDecimal valorTroco = valorRecebido.subtract(valorVenda);
        return valorTroco;

    }

    public void habilitarCampos() {

        switch (view.jCforma.getSelectedItem().toString()) {

            case "Dinheiro":
                view.jTvalorRecebido.setEditable(true);
                view.jTvalorRecebido.setText("");
                view.jTvalorTroco.setText(ConverteNumeroParaMoedaBr.converter("0"));
                view.jLparcela.setVisible(false);
                view.jCparcela.setVisible(false);
                break;

            case "Debito":
                view.jTvalorRecebido.setEditable(false);
                view.jTvalorRecebido.setText(ConverteNumeroParaMoedaBr.converter(vendaModelo.getValor().toString()));
                view.jTvalorTroco.setText(ConverteNumeroParaMoedaBr.converter("0"));
                view.jLparcela.setVisible(false);
                view.jCparcela.setVisible(false);
                break;

            case "Pix":
                view.jTvalorRecebido.setEditable(false);
                view.jTvalorRecebido.setText(ConverteNumeroParaMoedaBr.converter(vendaModelo.getValor().toString()));
                view.jTvalorTroco.setText(ConverteNumeroParaMoedaBr.converter("0"));
                view.jLparcela.setVisible(false);
                view.jCparcela.setVisible(false);
                break;

            default:
                view.jTvalorRecebido.setEditable(false);
                view.jTvalorRecebido.setText(ConverteNumeroParaMoedaBr.converter(vendaModelo.getValor().toString()));
                view.jTvalorTroco.setText(ConverteNumeroParaMoedaBr.converter("0"));
                view.jLparcela.setVisible(true);
                view.jCparcela.setVisible(true);
                view.jCparcela.setSelectedIndex(0);

        }

    }

    public void executarAposFinalizarVenda() {

        viewVenda.dispose();
        view.jBconcluir.setVisible(false);
        view.jBcancelar.setVisible(false);
        view.jBimprimir.setEnabled(true);
        view.jCforma.setEnabled(false);
        view.jTvalorRecebido.setEnabled(false);
        view.jCparcela.setEnabled(false);
        view.jCcliente.setEnabled(false);
        view.jTvalorRecebido.setText(ConverteNumeroParaMoedaBr.converter(view.jTvalorRecebido.getText()));

    }

    public void finalizarVenda() {

        vendaModelo.setData(Calendar.getInstance());
        vendaModelo.setFormaPagamento(view.jCforma.getSelectedItem().toString());
        vendaModelo.setNumeroParcelas(Integer.valueOf(view.jCparcela.getSelectedItem().toString()));
        vendaModelo.setIsPago(false);
        vendaModelo.setIdCliente(comboBoxClientes.getIdSelecionado());

        switch (view.jCforma.getSelectedItem().toString()) {

            case "Dinheiro":
                vendaModelo.setIsPago(true);
                vendaModelo.setNumeroParcelas(0);
                break;

            case "Debito":
                vendaModelo.setIsPago(true);
                vendaModelo.setNumeroParcelas(0);
                break;

        }

        /* finaliza a venda */
        new VendaMovimenta(vendaModelo).finalizar();
        executarAposFinalizarVenda();

    }

}
