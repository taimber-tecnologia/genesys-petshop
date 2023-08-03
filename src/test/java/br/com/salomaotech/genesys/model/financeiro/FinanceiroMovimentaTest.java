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

    private void simulaCadastroDeVendas(int numeroParcelas) {

        /* exclui todos os dados do financeiro */
        new Repository(new FinanceiroModelo()).deleteTodos();

        /* simula cadastro de venda */
        new Repository(new VendaModelo()).deleteTodos();
        vendaModelo = new VendaModelo();
        vendaModelo.setData(Calendar.getInstance());
        vendaModelo.setNumeroParcelas(numeroParcelas);
        new Repository(vendaModelo).save();

        /* movimentador financeiro */
        financeiroMovimenta = new FinanceiroMovimenta(vendaModelo);

        /* JPQL de pesquisa */
        jpql = new JPQL(new FinanceiroModelo());
        jpql.addParametroIgual("idVenda", vendaModelo.getId());

    }

    @Test
    public void testAdicionar() {

        /* simula cadastro de venda com apenas uma parcela */
        int numeroParcelas = 1;
        simulaCadastroDeVendas(numeroParcelas);
        financeiroMovimenta.adicionar();
        System.out.println("Testando classe FinanceiroMovimenta metodo: adicionar etapa 01");
        assertEquals(true, new Repository(new FinanceiroModelo()).countTodos(jpql.getCondicaoWhere()) == 1);

        /* simula cadastro de venda com várias parcelas porém só pode aparecer uma */
        numeroParcelas = 7;
        simulaCadastroDeVendas(numeroParcelas);
        financeiroMovimenta.adicionar();
        System.out.println("Testando classe FinanceiroMovimenta metodo: adicionar etapa 02");
        assertEquals(true, new Repository(new FinanceiroModelo()).countTodos(jpql.getCondicaoWhere()) == 1);

    }

    @Test
    public void testRemover() {

        /* simula cadastro de venda com várias parcelas */
        int numeroParcelas = 7;
        simulaCadastroDeVendas(numeroParcelas);

        /* informa os dados do venda e adiciona a movimentação financeira */
        financeiroMovimenta.adicionar();
        financeiroMovimenta.remover();

        /* é esperado apenas 0 resultado na contagem */
        System.out.println("Testando classe FinanceiroMovimenta metodo: remover");
        assertEquals(true, new Repository(new FinanceiroModelo()).countTodos(jpql.getCondicaoWhere()) == 0);

    }

}
