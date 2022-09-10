package br.com.salomaotech.genesys.model.cliente;

import br.com.salomaotech.sistema.jpa.Repository;
import br.com.salomaotech.sistema.jpa.JPQL;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ClientePesquisa {

    private final JTable jTresultados;
    private String nome;
    private String cpf;

    public ClientePesquisa(JTable jTresultados) {
        this.jTresultados = jTresultados;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    private void popularDados(List<ClienteModelo> clienteModeloList) {

        DefaultTableModel defaultTableModel = (DefaultTableModel) jTresultados.getModel();
        defaultTableModel.setNumRows(0);
        int contador = 0;

        /* itera a lista de dados do modelo */
        for (ClienteModelo clienteModelo : clienteModeloList) {

            /* popula linhas da DefaultTableModel */
            Object[] linhaDefaultTableModel = new Object[]{
                clienteModelo.getId(),
                clienteModelo.getNome(),
                clienteModelo.getCpf(),
                clienteModelo.getTelefone()

            };

            defaultTableModel.insertRow(contador, linhaDefaultTableModel);
            contador++;

        }

    }

    public void pesquisar() {

        JPQL jpql = new JPQL(new ClienteModelo());
        jpql.addParametroLike("nome", nome);
        jpql.addParametroLike("cpf", cpf);
        jpql.addOrderBy("nome", "ASC");
        jpql.addOrderBy("id", "ASC");

        /* pesquisa os dados */
        Repository repository = new Repository(new ClienteModelo());
        List<ClienteModelo> clienteModeloList = repository.getResults(jpql.construirSelect());
        popularDados(clienteModeloList);

    }

}
