package br.com.salomaotech.genesys.controller.fornecedor;

import br.com.salomaotech.genesys.model.fornecedor.FornecedorModelo;
import br.com.salomaotech.genesys.view.JFfornecedor;
import br.com.salomaotech.sistema.algoritmos.ValidaStringIsEmpty;
import br.com.salomaotech.sistema.jpa.JPQL;
import br.com.salomaotech.sistema.jpa.Repository;
import java.util.List;

public class FornecedorValidador {

    private final JFfornecedor view;
    private final long id;
    private String mensagensErro = "";

    public FornecedorValidador(JFfornecedor view, long id) {
        this.view = view;
        this.id = id;
    }

    public boolean isValido() {

        Repository repository = new Repository(new FornecedorModelo());

        /* valida nome */
        if (ValidaStringIsEmpty.isEmpty(view.jTbasicoNome.getText())) {

            mensagensErro = "Informe o nome do fornecedor.";
            view.jTbasicoNome.requestFocus();
            return false;

        }

        /* valida CNPJ */
        if (view.jFbasicoCnpj.getText().equals("  .   .   /    -  ")) {

            mensagensErro = "CNPJ inválido.";
            view.jFbasicoCnpj.requestFocus();
            return false;

        } else {

            JPQL jpql = new JPQL(new FornecedorModelo());
            jpql.addParametroIgual("cnpj", view.jFbasicoCnpj.getText());
            jpql.addParametroDiferente("id", id);
            List resultados = repository.getResults(jpql.construirSelect());

            if (resultados.size() > 0) {

                mensagensErro = "CNPJ já em uso.";
                view.jFbasicoCnpj.requestFocus();
                return false;

            }

        }

        return true;

    }

    public String getMensagensErro() {
        return mensagensErro;
    }

}
