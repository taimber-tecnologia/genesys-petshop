package br.com.salomaotech.genesys.model.venda;

import br.com.salomaotech.genesys.model.servico.ServicoModelo;
import br.com.salomaotech.sistema.algoritmos.ConverteNumeroParaMoedaBr;
import br.com.salomaotech.sistema.jpa.JPQL;
import br.com.salomaotech.sistema.jpa.Repository;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class VendaServicoPesquisa {

    private final JTable jTresultados;
    private String nome;

    public VendaServicoPesquisa(JTable jTresultados) {
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
                ConverteNumeroParaMoedaBr.converter(servicoModelo.getValor().toString())

            };

            defaultTableModel.insertRow(contador, linhaDefaultTableModel);
            contador++;

        }

    }

    public void pesquisar() {

        JPQL jpql = new JPQL(new ServicoModelo());
        jpql.addParametroLike("nome", nome);
        jpql.addOrderBy("nome", "ASC");
        jpql.addOrderBy("id", "ASC");

        Repository repository = new Repository(new ServicoModelo());
        List<ServicoModelo> servicoModeloList = repository.getResults(jpql.construirSelect());
        popularDados(servicoModeloList);

    }

}
