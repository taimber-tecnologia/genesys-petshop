package br.com.salomaotech.genesys.model.venda;

import br.com.salomaotech.genesys.model.venda.VendaPesquisa;
import br.com.salomaotech.genesys.model.venda.VendaModelo;
import br.com.salomaotech.genesys.model.cliente.ClienteModelo;
import br.com.salomaotech.genesys.view.JFvenda;
import br.com.salomaotech.sistema.jpa.Repository;
import java.util.Calendar;
import org.junit.Test;
import static org.junit.Assert.*;

public class VendaPesquisaTest {

    private final JFvenda view = new JFvenda();
    private final ClienteModelo clienteModelo = new ClienteModelo();
    private final VendaModelo vendaModelo = new VendaModelo();
    private final Calendar calendar = Calendar.getInstance();
    private final VendaPesquisa vendaPesquisa = new VendaPesquisa(view.jTresultados, view.jCpaginador);

    public VendaPesquisaTest() {

        /* simula cadastro de cliente */
        new Repository(new ClienteModelo()).deleteTodos();
        clienteModelo.setNome("Teste");
        clienteModelo.setCpf("000.000.000-00");
        new Repository(clienteModelo).save();

        /* simula cadastro de venda */
        new Repository(new VendaModelo()).deleteTodos();
        vendaModelo.setData(calendar);
        vendaModelo.setIdCliente(clienteModelo.getId());
        new Repository(vendaModelo).save();

    }

    @Test
    public void testSetData() {

        vendaPesquisa.setData(calendar);
        vendaPesquisa.pesquisar();
        System.out.println("Testando classe VendaPesquisa metodo: setData");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

    }

    @Test
    public void testSetIdCliente() {

        vendaPesquisa.setIdCliente(clienteModelo.getId());
        vendaPesquisa.pesquisar();
        System.out.println("Testando classe VendaPesquisa método: setIdCliente");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

    }

    @Test
    public void testPesquisar() {

        /* usando filtro: nenhum */
        vendaPesquisa.setData(null);
        vendaPesquisa.setIdCliente(0);
        vendaPesquisa.pesquisar();
        System.out.println("Testando classe VendaPesquisa metodo: pesquisar etapa 01");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: data */
        vendaPesquisa.setData(calendar);
        vendaPesquisa.setIdCliente(0);
        vendaPesquisa.pesquisar();
        System.out.println("Testando classe VendaPesquisa metodo: pesquisar etapa 02");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: cliente */
        vendaPesquisa.setData(null);
        vendaPesquisa.setIdCliente(clienteModelo.getId());
        vendaPesquisa.pesquisar();
        System.out.println("Testando classe VendaPesquisa metodo: pesquisar etapa 03");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: todos */
        vendaPesquisa.setData(calendar);
        vendaPesquisa.setIdCliente(clienteModelo.getId());
        vendaPesquisa.pesquisar();
        System.out.println("Testando classe VendaPesquisa metodo: pesquisar etapa 04");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

    }

}
