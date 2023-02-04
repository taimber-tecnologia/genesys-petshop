package br.com.salomaotech.genesys.model.vacina;

import br.com.salomaotech.genesys.model.animal.AnimalModelo;
import br.com.salomaotech.genesys.model.cliente.ClienteModelo;
import br.com.salomaotech.genesys.view.JFvacina;
import br.com.salomaotech.sistema.jpa.Repository;
import java.util.Calendar;
import org.junit.Test;
import static org.junit.Assert.*;

public class VacinaPesquisaTest {

    private final JFvacina view = new JFvacina();
    private final AnimalModelo animalModelo = new AnimalModelo();
    private final ClienteModelo clienteModelo = new ClienteModelo();
    private final VacinaModelo vacinaModelo = new VacinaModelo();
    private VacinaPesquisa vacinaPesquisa;
    private final Calendar calendar = Calendar.getInstance();

    public VacinaPesquisaTest() {

        /* simula cadastro de cliente */
        new Repository(new ClienteModelo()).deleteTodos();
        clienteModelo.setNome("Teste");
        clienteModelo.setCpf("000.000.000-00");
        new Repository(clienteModelo).save();

        /* simula cadastro de animal */
        new Repository(new AnimalModelo()).deleteTodos();
        animalModelo.setNome("Teste");
        animalModelo.setIdCliente(clienteModelo.getId());
        new Repository(animalModelo).save();

        /* simula cadastro de vacina */
        new Repository(new VacinaModelo()).deleteTodos();
        vacinaModelo.setNome("Teste");
        vacinaModelo.setIdCliente(clienteModelo.getId());
        vacinaModelo.setIdAnimal(animalModelo.getId());
        vacinaModelo.setDataAplicacao(calendar);
        vacinaModelo.setDataAplicacaoProxima(calendar);
        vacinaModelo.setDoses("1");
        vacinaModelo.setObservacoes("Nenhuma");
        new Repository(vacinaModelo).save();

    }

    @Test
    public void testPesquisar() {

        /* usando filtro: nenhum */
        vacinaPesquisa = new VacinaPesquisa(view.jTresultados, new AnimalModelo(), view.jCpaginador);
        vacinaPesquisa.pesquisar();
        System.out.println("Testando classe VacinaPesquisa metodo: pesquisar etapa 01");
        assertEquals(true, view.jTresultados.getRowCount() == 0);

        /* usando filtro: animal cadastrado no construtor */
        vacinaPesquisa = new VacinaPesquisa(view.jTresultados, animalModelo, view.jCpaginador);
        vacinaPesquisa.pesquisar();
        System.out.println("Testando classe VacinaPesquisa metodo: pesquisar etapa 02");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

    }

}
