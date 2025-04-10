package br.com.salomaotech.genesys.model.venda;

import br.com.salomaotech.genesys.model.servico.ServicoModelo;
import br.com.salomaotech.sistema.jpa.Repository;
import javax.swing.JTable;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class VendaServicoPesquisaTest {

    private final JTable jTresultados = new JTable();
    private final VendaServicoPesquisa vendaServicoPesquisa = new VendaServicoPesquisa(jTresultados);

    @Test
    public void testSetNome() {

        boolean isErro = false;

        try {

            vendaServicoPesquisa.setNome("Teste");

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe VendaServicoPesquisa método setNome");
        assertEquals(false, isErro);

    }

    @Test
    public void testPesquisar() {

        /* remove cadastros antigos */
        new Repository(new ServicoModelo()).deleteTodos();

        // Simula cadastro de servicos
        for (int i = 1; i <= 5; i++) {

            ServicoModelo servicoModelo = new ServicoModelo();
            servicoModelo.setNome("Teste");
            new Repository(servicoModelo).save();

        }

        // Simula cadastro de servicos
        for (int i = 1; i <= 6; i++) {

            ServicoModelo servicoModelo = new ServicoModelo();
            servicoModelo.setNome("Outro");
            new Repository(servicoModelo).save();

        }

        /* filtro: nenhum */
        vendaServicoPesquisa.setNome(null);
        vendaServicoPesquisa.pesquisar();
        System.out.println("Testando classe VendaServicoPesquisa método pesquisar etapa 01");
        assertEquals(11, jTresultados.getRowCount());

        /* filtro: nome = Teste */
        vendaServicoPesquisa.setNome("Teste");
        vendaServicoPesquisa.pesquisar();
        System.out.println("Testando classe VendaServicoPesquisa método pesquisar etapa 02");
        assertEquals(5, jTresultados.getRowCount());

        /* filtro: nome = Outro */
        vendaServicoPesquisa.setNome("Outro");
        vendaServicoPesquisa.pesquisar();
        System.out.println("Testando classe VendaServicoPesquisa método pesquisar etapa 03");
        assertEquals(6, jTresultados.getRowCount());

    }

}
