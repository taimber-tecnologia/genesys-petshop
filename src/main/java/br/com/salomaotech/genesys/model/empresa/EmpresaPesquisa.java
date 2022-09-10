package br.com.salomaotech.genesys.model.empresa;

import br.com.salomaotech.sistema.jpa.JPQL;
import br.com.salomaotech.sistema.jpa.Repository;
import java.util.List;

public class EmpresaPesquisa {

    public static EmpresaModelo getDadosEmpresa() {

        JPQL jpql = new JPQL(new EmpresaModelo());
        Repository repository = new Repository(new EmpresaModelo());
        List<EmpresaModelo> empresaModeloList = repository.getResults(jpql.construirSelect());

        try {

            return empresaModeloList.get(0);

        } catch (Exception ex) {

            return new EmpresaModelo();

        }

    }

}
