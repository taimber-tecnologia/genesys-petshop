package br.com.salomaotech.genesys.model.venda;

import br.com.salomaotech.sistema.algoritmos.Datas;
import br.com.salomaotech.sistema.algoritmos.ConverteNumeroParaMoedaBr;
import br.com.salomaotech.sistema.jpa.JPQL;
import br.com.salomaotech.sistema.jpa.Paginador;
import br.com.salomaotech.sistema.jpa.Repository;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import static java.util.Objects.isNull;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class VendaPesquisa {

    private final JTable jTresultados;
    private final JComboBox jCpaginador;
    private Date data;
    private long idPesquisa;

    public VendaPesquisa(JTable jTresultados, JComboBox jCpaginador) {
        this.jTresultados = jTresultados;
        this.jCpaginador = jCpaginador;
    }

    public void setData(Calendar data) {

        if (!isNull(data)) {

            this.data = data.getTime();

        }

    }

    public void setIdPesquisa(long idPesquisa) {

        this.idPesquisa = idPesquisa;

    }

    private void popularDados(List<VendaModelo> vendaModeloList) {

        DefaultTableModel defaultTableModel = (DefaultTableModel) jTresultados.getModel();
        defaultTableModel.setNumRows(0);
        int contador = 0;

        /* itera a lista de dados do modelo */
        for (VendaModelo vendaModelo : vendaModeloList) {

            /* popula linhas da DefaultTableModel */
            Object[] linhaDefaultTableModel = new Object[]{
                vendaModelo.getId(),
                Datas.calendarParaStringBr(vendaModelo.getData()),
                ConverteNumeroParaMoedaBr.converter(vendaModelo.getValor().toString())

            };

            defaultTableModel.insertRow(contador, linhaDefaultTableModel);
            contador++;

        }

    }

    public void pesquisar() {

        JPQL jpql = new JPQL(new VendaModelo());

        // Valida ID (Código de cadastro) de pesquisa
        if (idPesquisa > 0) {

            jpql.addParametroIgual("id", idPesquisa);

        }

        jpql.addParametroIgual("data", data);
        jpql.addOrderBy("data", "ASC");
        jpql.addOrderBy("id", "ASC");

        /* pesquisa os dados */
        Paginador paginador = new Paginador(jpql, jCpaginador, new VendaModelo());
        Repository repository = new Repository(new VendaModelo());
        List<VendaModelo> vendaModeloList = repository.getResultsComPaginador(jpql.construirSelect(), paginador.getPageNumber(), paginador.getPageSize());
        popularDados(vendaModeloList);

    }

}
