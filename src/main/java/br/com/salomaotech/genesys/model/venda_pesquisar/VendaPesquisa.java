package br.com.salomaotech.genesys.model.venda_pesquisar;

import br.com.salomaotech.genesys.model.cliente.ClienteModelo;
import br.com.salomaotech.genesys.model.venda.VendaModelo;
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
    private long idCliente;

    public VendaPesquisa(JTable jTresultados, JComboBox jCpaginador) {
        this.jTresultados = jTresultados;
        this.jCpaginador = jCpaginador;
    }

    public void setData(Calendar data) {

        if (!isNull(data)) {

            this.data = data.getTime();

        }

    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    private void popularDados(List<VendaModelo> vendaModeloList) {

        DefaultTableModel defaultTableModel = (DefaultTableModel) jTresultados.getModel();
        defaultTableModel.setNumRows(0);
        int contador = 0;

        /* itera a lista de dados do modelo */
        for (VendaModelo vendaModelo : vendaModeloList) {

            /* modelos auxiliares */
            ClienteModelo clienteModelo = (ClienteModelo) new Repository(new ClienteModelo()).findById(vendaModelo.getIdCliente());

            /* popula linhas da DefaultTableModel */
            Object[] linhaDefaultTableModel = new Object[]{
                vendaModelo.getId(),
                Datas.calendarParaStringBr(vendaModelo.getData()),
                clienteModelo.getNome(),
                clienteModelo.getTelefone(),
                ConverteNumeroParaMoedaBr.converter(vendaModelo.getValor().toString()),};

            defaultTableModel.insertRow(contador, linhaDefaultTableModel);
            contador++;

        }

    }

    public void pesquisar() {

        JPQL jpql = new JPQL(new VendaModelo());
        jpql.addParametroIgual("data", data);
        jpql.addOrderBy("data", "ASC");
        jpql.addOrderBy("id", "ASC");

        /* valida ID de cliente */
        if (idCliente != 0) {

            jpql.addParametroIgual("idCliente", idCliente);

        }

        /* pesquisa os dados */
        Paginador paginador = new Paginador(jpql, jCpaginador, new VendaModelo());
        Repository repository = new Repository(new VendaModelo());
        List<VendaModelo> vendaModeloList = repository.getResultsComPaginador(jpql.construirSelect(), paginador.getPageNumber(), paginador.getPageSize());
        popularDados(vendaModeloList);

    }

}
