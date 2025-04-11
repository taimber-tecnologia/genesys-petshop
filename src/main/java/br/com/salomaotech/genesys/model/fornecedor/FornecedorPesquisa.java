package br.com.salomaotech.genesys.model.fornecedor;

import br.com.salomaotech.sistema.jpa.Repository;
import br.com.salomaotech.sistema.jpa.JPQL;
import br.com.salomaotech.sistema.jpa.Paginador;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class FornecedorPesquisa {

    private final JTable jTresultados;
    private final JComboBox jCpaginador;
    private String nome;
    private String cnpj;

    public FornecedorPesquisa(JTable jTresultados, JComboBox jCpaginador) {
        this.jTresultados = jTresultados;
        this.jCpaginador = jCpaginador;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    private void popularDados(List<FornecedorModelo> fornecedorModeloList) {

        DefaultTableModel defaultTableModel = (DefaultTableModel) jTresultados.getModel();
        defaultTableModel.setNumRows(0);
        int contador = 0;

        /* itera a lista de dados do modelo */
        for (FornecedorModelo fornecedorModelo : fornecedorModeloList) {

            /* popula linhas da DefaultTableModel */
            Object[] linhaDefaultTableModel = new Object[]{
                fornecedorModelo.getId(),
                fornecedorModelo.getNome(),
                fornecedorModelo.getTelefone(),
                fornecedorModelo.getEmail(),
                fornecedorModelo.getCnpj()

            };

            defaultTableModel.insertRow(contador, linhaDefaultTableModel);
            contador++;

        }

    }

    public void pesquisar() {

        JPQL jpql = new JPQL(new FornecedorModelo());
        jpql.addParametroLike("nome", nome);
        jpql.addParametroLike("cnpj", cnpj);
        jpql.addOrderBy("nome", "ASC");
        jpql.addOrderBy("id", "ASC");

        /* pesquisa os dados */
        Paginador paginador = new Paginador(jpql, jCpaginador, new FornecedorModelo());
        Repository repository = new Repository(new FornecedorModelo());
        List<FornecedorModelo> fornecedorModeloList = repository.getResultsComPaginador(jpql.construirSelect(), paginador.getPageNumber(), paginador.getPageSize());
        popularDados(fornecedorModeloList);

    }

}
