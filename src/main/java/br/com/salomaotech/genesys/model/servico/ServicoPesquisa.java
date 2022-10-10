package br.com.salomaotech.genesys.model.servico;

import br.com.salomaotech.sistema.algoritmos.ConverteNumeroParaMoedaBr;
import br.com.salomaotech.sistema.jpa.JPQL;
import br.com.salomaotech.sistema.jpa.Repository;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ServicoPesquisa {

    private final JTable jTresultados;
    private String nome;

    public ServicoPesquisa(JTable jTresultados) {
        this.jTresultados = jTresultados;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    private void popularDados(List<ServicoModelo> servicoModeloList) {

        DefaultTableModel defaultTableModel = (DefaultTableModel) jTresultados.getModel();
        defaultTableModel.setNumRows(0);
        int contador = 0;

        for (ServicoModelo servicoModelo : servicoModeloList) {

            Object[] linhaDefaultTableModel = new Object[]{
                servicoModelo.getId(),
                servicoModelo.getNome(),
                ConverteNumeroParaMoedaBr.converter(servicoModelo.getValor().toString()),
                servicoModelo.getDescricao(),
                servicoModelo.getNome()
            };

            defaultTableModel.insertRow(contador, linhaDefaultTableModel);
            contador++;

        }

    }

    public void pesquisar() {

        JPQL jpql = new JPQL(new ServicoModelo());
        jpql.addParametroLike("nome", nome);
        jpql.addOrderBy("nome", "ASC");

        /* pesquisa os dados */
        Repository repository = new Repository(new ServicoModelo());
        List<ServicoModelo> servicoModeloList = repository.getResults(jpql.construirSelect());
        popularDados(servicoModeloList);

    }

}
