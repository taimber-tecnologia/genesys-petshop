package br.com.salomaotech.genesys.controller.venda;

import br.com.salomaotech.genesys.model.animal.ComboBoxAnimais;
import br.com.salomaotech.genesys.model.centro_custo.ComboBoxCentroCusto;
import br.com.salomaotech.genesys.model.cliente.ComboBoxClientes;
import br.com.salomaotech.genesys.view.JFvenda;
import br.com.salomaotech.sistema.algoritmos.Datas;
import br.com.salomaotech.sistema.algoritmos.IsStringNumeroValido;

public class VendaValidador {

    private final JFvenda view;
    private final ComboBoxAnimais comboBoxAnimaisCadastro;
    private final ComboBoxClientes comboBoxClientesCadastro;
    private final ComboBoxCentroCusto comboBoxCentroCusto;
    private String mensagensErro = "";

    public VendaValidador(JFvenda view, ComboBoxAnimais comboBoxAnimaisCadastro, ComboBoxClientes comboBoxClientesCadastro, ComboBoxCentroCusto comboBoxCentroCusto) {
        this.view = view;
        this.comboBoxAnimaisCadastro = comboBoxAnimaisCadastro;
        this.comboBoxClientesCadastro = comboBoxClientesCadastro;
        this.comboBoxCentroCusto = comboBoxCentroCusto;
    }

    public boolean isValido() {

        /* valida data */
        if (!Datas.isCalendarioValido(view.jDcadastroData.getCalendar())) {

            mensagensErro = "Data inválida.";
            return false;

        }

        /* valida o cliente */
        if (comboBoxClientesCadastro.getIdSelecionado() == 0) {

            mensagensErro = "Informe o nome do cliente.";
            view.jCcadastroNomeCliente.requestFocus();
            return false;

        }

        /* valida o animal */
        if (comboBoxAnimaisCadastro.getIdAnimalSelecionado() == 0) {

            mensagensErro = "Informe o nome do animal.";
            view.jCcadastroNomeAnimal.requestFocus();
            return false;

        }

        /* valida centro de custo */
        if (comboBoxCentroCusto.getIdSelecionado() == 0) {

            mensagensErro = "Informe o centro de custo.";
            view.jCcadastroCentroCusto.requestFocus();
            return false;

        }

        /* valida se o juros é um numero válido */
        if (!IsStringNumeroValido.isNumeroValido(view.jTcadastroPorcentagemJuros.getText())) {

            mensagensErro = "Juros inválido.";
            view.jTcadastroPorcentagemJuros.requestFocus();
            return false;

        }

        /* valida se a entrada é um numero válido */
        if (!IsStringNumeroValido.isNumeroValido(view.jTcadastroValorEntrada.getText())) {

            mensagensErro = "Entrada inválida.";
            view.jTcadastroValorEntrada.requestFocus();
            return false;

        }

        /* valida se o desconto é um numero válido */
        if (!IsStringNumeroValido.isNumeroValido(view.jTcadastroValorDesconto.getText())) {

            mensagensErro = "Desconto inválido.";
            view.jTcadastroValorDesconto.requestFocus();
            return false;

        }

        /* valida se há itens de produto adicionados */
        if (view.jTvendaModeloItemSelecionados.getRowCount() == 0) {

            mensagensErro = "Nenhum produto adicionado.";
            view.jTabbedPane1.setSelectedIndex(0);
            return false;

        }

        return true;

    }

    public String getMensagensErro() {
        return mensagensErro;
    }

}
