package br.com.salomaotech.genesys.controller.produto;

import br.com.salomaotech.genesys.model.produto.ProdutoModelo;
import br.com.salomaotech.genesys.view.JFproduto;
import br.com.salomaotech.sistema.algoritmos.IsStringNumeroValido;
import br.com.salomaotech.sistema.algoritmos.ValidaStringIsEmpty;
import br.com.salomaotech.sistema.jpa.JPQL;
import br.com.salomaotech.sistema.jpa.Repository;

public class ProdutoValidador {

    private final JFproduto view;
    private String mensagensErro = "";

    public ProdutoValidador(JFproduto view) {
        this.view = view;
    }

    public boolean isValido() {

        /* valida se o nome não está em branco */
        if (ValidaStringIsEmpty.isEmpty(view.jTnome.getText())) {

            mensagensErro = "Informe um nome.";
            view.jTnome.requestFocus();
            return false;

        } else {

            JPQL jpql = new JPQL(new ProdutoModelo());
            jpql.addParametroDiferente("id", view.getId());
            jpql.addParametroIgual("nome", view.jTnome.getText());

            /* valida se o nome já está sendo utilizado */
            if (!new Repository(new ProdutoModelo()).getResults(jpql.construirSelect()).isEmpty()) {

                mensagensErro = "O nome já está sendo utilizado.";
                view.jTnome.requestFocus();
                return false;

            }

        }

        /* valida unidade de medida */
        if (view.jCmedida.getSelectedIndex() == -1) {

            mensagensErro = "Informe a unidade de medida.";
            view.jCmedida.requestFocus();
            return false;

        }

        /* valida valor de custo */
        if (!IsStringNumeroValido.isNumeroValido(view.jTvalorCusto.getText()) && !ValidaStringIsEmpty.isEmpty(view.jTvalorCusto.getText())) {

            mensagensErro = "Valor de custo inválido! Use ponto para separar casas decimais exemplo 1.99.";
            view.jTvalorCusto.requestFocus();
            return false;

        }

        /* valida se o valor de venda é válido */
        if (!IsStringNumeroValido.isNumeroValido(view.jTvalorVenda.getText())) {

            mensagensErro = "Valor de venda inválido! Use ponto para separar casas decimais exemplo 1.99.";
            view.jTvalorVenda.requestFocus();
            return false;

        }

        /* valida quantidade */
        if (!IsStringNumeroValido.isNumeroValido(view.jTquantidade.getText()) && !ValidaStringIsEmpty.isEmpty(view.jTquantidade.getText())) {

            mensagensErro = "Quantidade inválida.";
            view.jTquantidade.requestFocus();
            return false;

        }

        /* valida estoque mínimo */
        if (!IsStringNumeroValido.isNumeroValido(view.jTestoqueMinimo.getText()) && !ValidaStringIsEmpty.isEmpty(view.jTestoqueMinimo.getText())) {

            mensagensErro = "Estoque mínimo inválido.";
            view.jTestoqueMinimo.requestFocus();
            return false;

        }

        return true;

    }

    public String getMensagensErro() {
        return mensagensErro;
    }

}
