package br.com.salomaotech.genesys.model.servico;

import br.com.salomaotech.genesys.view.JFservico;
import br.com.salomaotech.sistema.jpa.Repository;
import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ServicoPesquisaTest {

    private final JFservico view = new JFservico();
    private final ServicoModelo servicoModelo = new ServicoModelo();
    private final ServicoPesquisa servicoPesquisa = new ServicoPesquisa(view.jTresultados);

    public ServicoPesquisaTest() {

        /* simula cadastro de servico */
        new Repository(new ServicoModelo()).deleteTodos();
        servicoModelo.setNome("Teste");
        servicoModelo.setDescricao("Teste ABC");
        servicoModelo.setValor(new BigDecimal(15));
        new Repository(servicoModelo).save();

    }

    @Test
    public void testSetNome() {

        boolean isErro = false;

        try {

            servicoPesquisa.setNome("Teste");

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe ServicoPesquisa metodo: setNome");
        assertEquals(false, isErro);

    }

    @Test
    public void testPesquisar() {

        /* usando filtro: nenhum */
        System.out.println("Testando classe ServicoPesquisa metodo: pesquisar etapa 01");
        servicoPesquisa.setNome(null);
        servicoPesquisa.pesquisar();
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: nome */
        System.out.println("Testando classe ServicoPesquisa metodo: pesquisar etapa 02");
        servicoPesquisa.setNome(servicoModelo.getNome());
        servicoPesquisa.pesquisar();
        assertEquals(true, view.jTresultados.getRowCount() > 0);

    }

}
