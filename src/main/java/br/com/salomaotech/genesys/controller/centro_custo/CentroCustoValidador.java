package br.com.salomaotech.genesys.controller.centro_custo;

import br.com.salomaotech.genesys.model.centro_custo.CentroCustoModelo;
import br.com.salomaotech.genesys.view.JFcentroCusto;
import br.com.salomaotech.sistema.algoritmos.ValidaStringIsEmpty;
import br.com.salomaotech.sistema.jpa.JPQL;
import br.com.salomaotech.sistema.jpa.Repository;
import java.util.List;

public class CentroCustoValidador {

    private final JFcentroCusto view;
    private final long id;
    private String mensagensErro = "";

    public CentroCustoValidador(JFcentroCusto view, long id) {
        this.view = view;
        this.id = id;
    }

    public boolean isValido() {

        Repository repository = new Repository(new CentroCustoModelo());

        /* valida código */
        if (ValidaStringIsEmpty.isEmpty(view.jTcadastroCodigo.getText())) {

            mensagensErro = "Informe o código do centro de custo.";
            view.jTcadastroCodigo.requestFocus();
            return false;

        } else {

            JPQL jpql = new JPQL(new CentroCustoModelo());
            jpql.addParametroIgual("codigo", view.jTcadastroCodigo.getText());
            jpql.addParametroDiferente("id", id);
            List resultados = repository.getResults(jpql.construirSelect());

            if (!resultados.isEmpty()) {

                mensagensErro = "Código já em uso.";
                view.jTcadastroCodigo.requestFocus();
                return false;

            }

        }

        /* valida nome */
        if (ValidaStringIsEmpty.isEmpty(view.jTcadastroNome.getText())) {

            mensagensErro = "Informe o nome do centro de custo.";
            view.jTcadastroNome.requestFocus();
            return false;

        }

        return true;

    }

    public String getMensagensErro() {
        return mensagensErro;
    }

}
