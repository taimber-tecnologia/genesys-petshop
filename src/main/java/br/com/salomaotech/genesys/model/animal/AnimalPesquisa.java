package br.com.salomaotech.genesys.model.animal;

import br.com.salomaotech.genesys.model.cliente.ClienteModelo;
import br.com.salomaotech.sistema.algoritmos.Datas;
import br.com.salomaotech.sistema.jpa.JPQL;
import br.com.salomaotech.sistema.jpa.Paginador;
import br.com.salomaotech.sistema.jpa.Repository;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AnimalPesquisa {

    private String nome;
    private long idCliente;
    private final JTable jTresultados;
    private final JComboBox jCpaginador;

    public AnimalPesquisa(JTable jTresultados, JComboBox jCpaginador) {
        this.jTresultados = jTresultados;
        this.jCpaginador = jCpaginador;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    private void popularDados(List<AnimalModelo> animalModeloList) {

        DefaultTableModel defaultTableModel = (DefaultTableModel) jTresultados.getModel();
        defaultTableModel.setNumRows(0);
        int contador = 0;

        /* itera a lista de dados do modelo */
        for (AnimalModelo animalModelo : animalModeloList) {

            /* modelos auxiliares */
            ClienteModelo clienteModelo = (ClienteModelo) new Repository(new ClienteModelo()).findById(animalModelo.getIdCliente());

            /* popula linhas da DefaultTableModel */
            Object[] linhaDefaultTableModel = new Object[]{
                animalModelo.getId(),
                animalModelo.getNome(),
                animalModelo.getEspecie(),
                animalModelo.getRaca(),
                animalModelo.getSexo(),
                Datas.calendarParaStringBr(animalModelo.getNascimento()),
                clienteModelo.getNome(),
                clienteModelo.getTelefone()

            };

            defaultTableModel.insertRow(contador, linhaDefaultTableModel);
            contador++;

        }

    }

    public void pesquisar() {

        JPQL jpql = new JPQL(new AnimalModelo());
        jpql.addParametroLike("nome", nome);
        jpql.addOrderBy("nome", "ASC");
        jpql.addOrderBy("id", "ASC");

        /* valida ID de cliente */
        if (idCliente != 0) {

            jpql.addParametroIgual("idCliente", idCliente);

        }

        /* pesquisa os dados */
        Paginador paginador = new Paginador(jpql, jCpaginador, new AnimalModelo());
        Repository repository = new Repository(new AnimalModelo());
        List<AnimalModelo> animalModeloList = repository.getResultsComPaginador(jpql.construirSelect(), paginador.getPageNumber(), paginador.getPageSize());
        popularDados(animalModeloList);

    }

}
