package br.com.salomaotech.genesys.model.produto;

import br.com.salomaotech.sistema.algoritmos.ConverteNumeroParaMoedaBr;
import br.com.salomaotech.sistema.jpa.JPQL;
import br.com.salomaotech.sistema.jpa.Repository;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class JtableProduto {

    private final JTable jTresultados;

    public JtableProduto(JTable jTresultados) {
        this.jTresultados = jTresultados;
    }

    private JPQL popularJpql() {

        JPQL jpql = new JPQL(new ProdutoModelo());
        jpql.addOrderBy("nome", "ASC");
        return jpql;

    }

    public void preencher() {

        DefaultTableModel defaultTableModel = (DefaultTableModel) jTresultados.getModel();
        defaultTableModel.setNumRows(0);
        int contador = 0;

        /* pesquisa pelos dados do modelo e popula a JTable */
        List<ProdutoModelo> produtoModeloList = new Repository(new ProdutoModelo()).getResults(popularJpql().construirSelect());
        for (ProdutoModelo produtoModelo : produtoModeloList) {

            Object[] linhaDefaultTableModel = new Object[]{
                produtoModelo.getId(),
                produtoModelo.getNome(),
                produtoModelo.getQuantidade(),
                ConverteNumeroParaMoedaBr.converter(produtoModelo.getValorVenda().toString())
            };

            defaultTableModel.insertRow(contador, linhaDefaultTableModel);
            contador++;

        }

    }

}
