package br.com.salomaotech.genesys.model.cliente;

import br.com.salomaotech.genesys.model.cliente.ClientePesquisa;
import br.com.salomaotech.genesys.model.cliente.ClienteModelo;
import br.com.salomaotech.genesys.view.JFcliente;
import br.com.salomaotech.sistema.jpa.Repository;
import org.junit.Test;
import static org.junit.Assert.*;

public class ClientePesquisaTest {

    private final JFcliente view = new JFcliente();
    private final ClienteModelo clienteModelo = new ClienteModelo();
    private ClientePesquisa clientePesquisa = new ClientePesquisa(view.jTresultados);

    public ClientePesquisaTest() {

        /* simula cadastro de cliente */
        new Repository(new ClienteModelo()).deleteTodos();
        clienteModelo.setNome("Teste");
        clienteModelo.setCpf("000.000.000-00");
        new Repository(clienteModelo).save();

    }

    @Test
    public void testSetNome() {

        clientePesquisa.setNome(clienteModelo.getNome());
        clientePesquisa.pesquisar();
        System.out.println("Testando classe ClientePesquisa metodo: setNome");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

    }

    @Test
    public void testSetCpf() {

        clientePesquisa.setCpf(clienteModelo.getCpf());
        clientePesquisa.pesquisar();
        System.out.println("Testando classe ClientePesquisa metodo: setCpf");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

    }

    @Test
    public void testPesquisar() {

        /* utilizando filtro: nenhum */
        clientePesquisa = new ClientePesquisa(view.jTresultados);
        clientePesquisa.setNome(null);
        clientePesquisa.setCpf(null);
        clientePesquisa.pesquisar();
        System.out.println("Testando classe ClientePesquisa metodo: pesquisar etapa 01");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* utilizando filtro: nome */
        clientePesquisa = new ClientePesquisa(view.jTresultados);
        clientePesquisa.setNome(clienteModelo.getNome());
        clientePesquisa.setCpf(null);
        clientePesquisa.pesquisar();
        System.out.println("Testando classe ClientePesquisa metodo: pesquisar etapa 02");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* utilizando filtro: CPF */
        clientePesquisa = new ClientePesquisa(view.jTresultados);
        clientePesquisa.setNome(null);
        clientePesquisa.setCpf(clienteModelo.getCpf());
        clientePesquisa.pesquisar();
        System.out.println("Testando classe ClientePesquisa metodo: pesquisar etapa 03");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* utilizando filtro: todos */
        clientePesquisa = new ClientePesquisa(view.jTresultados);
        clientePesquisa.setNome(clienteModelo.getNome());
        clientePesquisa.setCpf(clienteModelo.getCpf());
        clientePesquisa.pesquisar();
        System.out.println("Testando classe ClientePesquisa metodo: pesquisar etapa 04");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

    }

}
