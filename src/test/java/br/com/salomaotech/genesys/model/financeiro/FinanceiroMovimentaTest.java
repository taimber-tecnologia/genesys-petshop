package br.com.salomaotech.genesys.model.financeiro;

import br.com.salomaotech.genesys.model.venda.VendaModelo;
import br.com.salomaotech.sistema.jpa.JPQL;
import br.com.salomaotech.sistema.jpa.Repository;
import java.util.Calendar;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class FinanceiroMovimentaTest {

    private VendaModelo vendaModelo;
    private FinanceiroMovimenta financeiroMovimenta;
    private JPQL jpql;

    private void simulaCadastroDeVendas() {

        /* exclui dados antigos */
        new Repository(new VendaModelo()).deleteTodos();
        new Repository(new FinanceiroModelo()).deleteTodos();

        /* simula cadastro de venda */
        vendaModelo = new VendaModelo();
        vendaModelo.setData(Calendar.getInstance());
        new Repository(vendaModelo).save();

        /* movimenta o financeiro */
        financeiroMovimenta = new FinanceiroMovimenta(vendaModelo);

        /* JPQL de pesquisa */
        jpql = new JPQL(new FinanceiroModelo());

    }

    @Test
    public void testAdicionar() {

        simulaCadastroDeVendas();
        financeiroMovimenta.adicionar();
        System.out.println("Testando classe FinanceiroMovimenta metodo: adicionar");
        assertEquals(true, new Repository(new FinanceiroModelo()).countTodos(jpql.getCondicaoWhere()) == 1);

    }

    @Test
    public void testRemover() {

        simulaCadastroDeVendas();

        /* informa os dados do venda e adiciona a movimentação financeira */
        financeiroMovimenta.adicionar();
        financeiroMovimenta.remover();

        /* é esperado apenas 0 resultado na contagem */
        System.out.println("Testando classe FinanceiroMovimenta metodo: remover");
        assertEquals(true, new Repository(new FinanceiroModelo()).countTodos(jpql.getCondicaoWhere()) == 0);

    }

}
