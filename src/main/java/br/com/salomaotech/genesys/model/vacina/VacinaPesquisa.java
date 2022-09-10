package br.com.salomaotech.genesys.model.vacina;

import br.com.salomaotech.genesys.model.animal.AnimalModelo;
import br.com.salomaotech.sistema.algoritmos.Datas;
import br.com.salomaotech.sistema.jpa.JPQL;
import br.com.salomaotech.sistema.jpa.Repository;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class VacinaPesquisa {

    private final JTable jTresultados;
    private final AnimalModelo animalModelo;

    public VacinaPesquisa(JTable jTresultados, AnimalModelo animalModelo) {
        this.jTresultados = jTresultados;
        this.animalModelo = animalModelo;
    }

    private void popularDados(List<VacinaModelo> vacinaModeloList) {

        DefaultTableModel defaultTableModel = (DefaultTableModel) jTresultados.getModel();
        defaultTableModel.setNumRows(0);
        int contador = 0;

        /* itera a lista de dados do modelo */
        for (VacinaModelo VacinaModelo : vacinaModeloList) {

            /* popula linhas da DefaultTableModel */
            Object[] linhaDefaultTableModel = new Object[]{
                VacinaModelo.getId(),
                VacinaModelo.getNome(),
                VacinaModelo.getDoses(),
                Datas.calendarParaStringBr(VacinaModelo.getDataAplicacao()),
                Datas.calendarParaStringBr(VacinaModelo.getDataAplicacaoProxima())

            };

            defaultTableModel.insertRow(contador, linhaDefaultTableModel);
            contador++;

        }

    }

    public void pesquisar() {

        JPQL jpql = new JPQL(new VacinaModelo());
        jpql.addParametroIgual("idCliente", animalModelo.getIdCliente());
        jpql.addParametroIgual("idAnimal", animalModelo.getId());
        jpql.addOrderBy("dataAplicacao", "ASC");
        jpql.addOrderBy("id", "ASC");

        /* pesquisa os dados */
        Repository repository = new Repository(new VacinaModelo());
        List<VacinaModelo> vacinaModeloList = repository.getResults(jpql.construirSelect());
        popularDados(vacinaModeloList);

    }

}
