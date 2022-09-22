package br.com.salomaotech.genesys.model.animal;

import br.com.salomaotech.genesys.model.cliente.ClienteModelo;
import br.com.salomaotech.genesys.view.JFanimal;
import br.com.salomaotech.sistema.jpa.Repository;
import org.junit.Test;
import static org.junit.Assert.*;

public class AnimalPesquisaTest {

    private JFanimal view = new JFanimal();
    private final ClienteModelo clienteModelo = new ClienteModelo();
    private final AnimalModelo animalModelo = new AnimalModelo();
    private AnimalPesquisa animalPesquisa = new AnimalPesquisa(view.jTresultados, view.jCpaginador);

    public AnimalPesquisaTest() {

        /* simula cadastro de cliente */
        new Repository(new ClienteModelo()).deleteTodos();
        clienteModelo.setNome("Teste");
        clienteModelo.setCpf("000.000.000-00");
        new Repository(clienteModelo).save();

        /* simula o cadastro de animal */
        new Repository(new AnimalModelo()).deleteTodos();
        animalModelo.setNome("Teste");
        animalModelo.setIdCliente(clienteModelo.getId());
        new Repository(animalModelo).save();

    }

    @Test
    public void testSetNome() {

        boolean isErro = false;

        try {

            animalPesquisa.setNome("Teste");

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe AnimalPesquisa metodo: setNome");
        assertEquals(false, isErro);

    }

    @Test
    public void testSetIdCliente() {

        boolean isErro = false;

        try {

            animalPesquisa.setIdCliente(clienteModelo.getId());

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe AnimalPesquisa metodo: setIdCliente");
        assertEquals(false, isErro);

    }

    @Test
    public void testPesquisar() {

        /* pesquisa usando filtro: nenhum */
        view = new JFanimal();
        animalPesquisa = new AnimalPesquisa(view.jTresultados, view.jCpaginador);
        animalPesquisa.setIdCliente(0);
        animalPesquisa.setNome(null);
        animalPesquisa.pesquisar();
        System.out.println("Testando classe AnimalPesquisa metodo: usando senhum filtro etapa 01");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* pesquisa usando filtro: ID do cliente */
        view = new JFanimal();
        animalPesquisa = new AnimalPesquisa(view.jTresultados, view.jCpaginador);
        animalPesquisa.setIdCliente(clienteModelo.getId());
        animalPesquisa.setNome(null);
        animalPesquisa.pesquisar();
        System.out.println("Testando classe AnimalPesquisa metodo: usando senhum filtro etapa 02");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* pesquisa usando filtro: nome do animal */
        view = new JFanimal();
        animalPesquisa = new AnimalPesquisa(view.jTresultados, view.jCpaginador);
        animalPesquisa.setIdCliente(0);
        animalPesquisa.setNome(animalModelo.getNome());
        animalPesquisa.pesquisar();
        System.out.println("Testando classe AnimalPesquisa metodo: usando senhum filtro etapa 03");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* pesquisa usando filtro: todos */
        view = new JFanimal();
        animalPesquisa = new AnimalPesquisa(view.jTresultados, view.jCpaginador);
        animalPesquisa.setIdCliente(clienteModelo.getId());
        animalPesquisa.setNome(animalModelo.getNome());
        animalPesquisa.pesquisar();
        System.out.println("Testando classe AnimalPesquisa metodo: usando senhum filtro etapa 04");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

    }

}
