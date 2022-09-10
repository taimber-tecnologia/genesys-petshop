package br.com.salomaotech.genesys.controller.animal;

import br.com.salomaotech.genesys.view.JFanimal;
import br.com.salomaotech.sistema.algoritmos.IsStringNumeroValido;
import br.com.salomaotech.sistema.algoritmos.ValidaStringIsEmpty;

public class AnimalValidador {

    private final JFanimal view;
    private String mensagensErro = "";

    public AnimalValidador(JFanimal view) {
        this.view = view;
    }

    public boolean isValido() {

        /* valida nome */
        if (ValidaStringIsEmpty.isEmpty(view.jTnomeCadastro.getText())) {

            mensagensErro = "Informe o nome do animal!";
            view.jTnomeCadastro.requestFocus();
            return false;

        }

        /* valida se o peso é um numero válido */
        if (!IsStringNumeroValido.isNumeroValido(view.jTpesoCadastro.getText()) && !ValidaStringIsEmpty.isEmpty(view.jTpesoCadastro.getText())) {

            mensagensErro = "Informe um valor válido para o peso do animal!";
            view.jTpesoCadastro.requestFocus();
            return false;

        }

        /* valida especie */
        if (ValidaStringIsEmpty.isEmpty(view.jCespecieCadastro.getEditor().getItem().toString())) {

            mensagensErro = "Informe a espécie do animal!";
            view.jCespecieCadastro.requestFocus();
            return false;

        }

        /* valida raça */
        if (ValidaStringIsEmpty.isEmpty(view.jCracaCadastro.getEditor().getItem().toString())) {

            mensagensErro = "Informe a raça do animal!";
            view.jCracaCadastro.requestFocus();
            return false;

        }

        /* valida o nome do cliente */
        if (view.jCnomeClienteCadastro.getSelectedIndex() == 0) {

            mensagensErro = "Informe o nome do cliente!";
            view.jCnomeClienteCadastro.requestFocus();
            return false;

        }

        return true;

    }

    public String getMensagensErro() {
        return mensagensErro;
    }

}
