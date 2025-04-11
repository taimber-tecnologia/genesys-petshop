package br.com.salomaotech.genesys.model.vacina;

import br.com.salomaotech.genesys.model.animal.AnimalModelo;
import br.com.salomaotech.sistema.algoritmos.Datas;
import br.com.salomaotech.sistema.jpa.JPQL;
import br.com.salomaotech.sistema.jpa.Paginador;
import br.com.salomaotech.sistema.jpa.Repository;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class VacinaPesquisa {

    private final JTable jTresultados;
    private final AnimalModelo animalModelo;
    private final JComboBox jCpaginador;

    public VacinaPesquisa(JTable jTresultados, AnimalModelo animalModelo, JComboBox jCpaginador) {
        this.jTresultados = jTresultados;
        this.animalModelo = animalModelo;
        this.jCpaginador = jCpaginador;
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
                Datas.calendarParaStringBr(VacinaModelo.getDataAplicacao()),
                VacinaModelo.getNome(),
                VacinaModelo.getDoses(),
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
        Paginador paginador = new Paginador(jpql, jCpaginador, new VacinaModelo());
        Repository repository = new Repository(new VacinaModelo());
        List<VacinaModelo> vacinaModeloList = repository.getResultsComPaginador(jpql.construirSelect(), paginador.getPageNumber(), paginador.getPageSize());
        popularDados(vacinaModeloList);

    }

}
