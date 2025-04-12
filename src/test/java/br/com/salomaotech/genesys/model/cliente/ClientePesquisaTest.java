package br.com.salomaotech.genesys.model.cliente;

import br.com.salomaotech.genesys.view.JFcliente;
import br.com.salomaotech.sistema.jpa.Repository;
import org.junit.Test;
import static org.junit.Assert.*;

public class ClientePesquisaTest {

    private final JFcliente view = new JFcliente();
    private final ClienteModelo clienteModelo = new ClienteModelo();
    private ClientePesquisa clientePesquisa = new ClientePesquisa(view.jTresultados, view.jCpaginador);

    public ClientePesquisaTest() {

        /* simula cadastro de cliente */
        new Repository(new ClienteModelo()).deleteTodos();
        clienteModelo.setNome("Teste");
        clienteModelo.setTelefone("62 0000-0000");
        new Repository(clienteModelo).save();

    }

    @Test
    public void testSetNome() {

        boolean isErro = false;

        try {

            clientePesquisa.setNome(clienteModelo.getNome());

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe ClientePesquisa metodo: setNome");
        assertEquals(false, isErro);

    }

    @Test
    public void testSetTelefone() {

        boolean isErro = false;

        try {

            clientePesquisa.setTelefone(clienteModelo.getTelefone());

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe ClientePesquisa metodo: setTelefone");
        assertEquals(false, isErro);

    }

    @Test
    public void testPesquisar() {

        /* utilizando filtro: nenhum */
        clientePesquisa = new ClientePesquisa(view.jTresultados, view.jCpaginador);
        clientePesquisa.setNome(null);
        clientePesquisa.setTelefone(null);
        clientePesquisa.pesquisar();
        System.out.println("Testando classe ClientePesquisa metodo: pesquisar etapa 01");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* utilizando filtro: nome */
        clientePesquisa = new ClientePesquisa(view.jTresultados, view.jCpaginador);
        clientePesquisa.setNome(clienteModelo.getNome());
        clientePesquisa.setTelefone(null);
        clientePesquisa.pesquisar();
        System.out.println("Testando classe ClientePesquisa metodo: pesquisar etapa 02");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* utilizando filtro: telefone */
        clientePesquisa = new ClientePesquisa(view.jTresultados, view.jCpaginador);
        clientePesquisa.setNome(null);
        clientePesquisa.setTelefone(clienteModelo.getTelefone());
        clientePesquisa.pesquisar();
        System.out.println("Testando classe ClientePesquisa metodo: pesquisar etapa 03");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* utilizando filtro: todos */
        clientePesquisa = new ClientePesquisa(view.jTresultados, view.jCpaginador);
        clientePesquisa.setNome(clienteModelo.getNome());
        clientePesquisa.setTelefone(clienteModelo.getTelefone());
        clientePesquisa.pesquisar();
        System.out.println("Testando classe ClientePesquisa metodo: pesquisar etapa 04");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

    }

}
