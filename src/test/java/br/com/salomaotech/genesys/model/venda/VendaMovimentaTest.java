package br.com.salomaotech.genesys.model.venda;

import br.com.salomaotech.genesys.model.venda.VendaModelo;
import br.com.salomaotech.genesys.model.venda.VendaModeloItem;
import br.com.salomaotech.genesys.model.venda.VendaMovimenta;
import br.com.salomaotech.genesys.model.animal.AnimalModelo;
import br.com.salomaotech.genesys.model.centro_custo.CentroCustoModelo;
import br.com.salomaotech.genesys.model.cliente.ClienteModelo;
import br.com.salomaotech.genesys.model.financeiro.FinanceiroModelo;
import br.com.salomaotech.genesys.model.produto.ProdutoModelo;
import br.com.salomaotech.sistema.jpa.JPQL;
import br.com.salomaotech.sistema.jpa.Repository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class VendaMovimentaTest {

    private final List<VendaModeloItem> vendaModeloItemList = new ArrayList();
    private final Calendar calendar = Calendar.getInstance();
    private final ClienteModelo clienteModelo = new ClienteModelo();
    private final AnimalModelo animalModelo = new AnimalModelo();
    private final ProdutoModelo produtoModelo = new ProdutoModelo();
    private final CentroCustoModelo centroCustoModelo = new CentroCustoModelo();
    private final VendaModeloItem vendaModeloItem = new VendaModeloItem();
    private final VendaModelo vendaModelo;
    private final VendaMovimenta vendaMovimenta;

    public VendaMovimentaTest() {

        /* remove todos os dados do financeiro */
        new Repository(new FinanceiroModelo()).deleteTodos();

        /* simula cadastro de cliente */
        new Repository(new ClienteModelo()).deleteTodos();
        clienteModelo.setNome("Teste");
        clienteModelo.setCpf("000.000.000-00");
        new Repository(clienteModelo).save();

        /* simula cadastro de animais */
        new Repository(new AnimalModelo()).deleteTodos();
        animalModelo.setNome("Felix");
        animalModelo.setEspecie("Felino");
        animalModelo.setIdCliente(clienteModelo.getId());
        new Repository(animalModelo).save();

        /* simula cadastro de produto */
        new Repository(new ProdutoModelo()).deleteTodos();
        produtoModelo.setNome("Teste");
        produtoModelo.setValorVenda(new BigDecimal(100));
        produtoModelo.setQuantidade(new BigDecimal(10));
        new Repository(produtoModelo).save();

        /* simula o cadastro de item de venda */
        vendaModeloItem.setIdProduto(produtoModelo.getId());
        vendaModeloItem.setDescricao("Teste");
        vendaModeloItem.setValor(new BigDecimal(100));
        vendaModeloItem.setQuantidade(new BigDecimal(2));
        new Repository(vendaModeloItem).save();
        vendaModeloItemList.add(vendaModeloItem);

        /* simula cadastro de centro de custo */
        new Repository(new CentroCustoModelo()).deleteTodos();
        centroCustoModelo.setCodigo("1.0");
        centroCustoModelo.setNome("Teste");
        new Repository(centroCustoModelo).save();

        /* simula cadastro de venda */
        new Repository(new VendaModelo()).deleteTodos();
        vendaModelo = new VendaModelo();
        vendaModelo.setData(calendar);
        vendaModelo.setIdAnimal(animalModelo.getId());
        vendaModelo.setIdCliente(clienteModelo.getId());
        vendaModelo.setVendaModeloItemList(vendaModeloItemList);
        vendaModelo.setDesconto(new BigDecimal(0));
        vendaModelo.setFormaPagamento("Credito");
        vendaModelo.setJuros(new BigDecimal(0));
        vendaModelo.setFinalizado(false);
        vendaModelo.setNumeroParcelas(10);
        vendaModelo.setIdCentroCusto(centroCustoModelo.getId());
        new Repository(vendaModelo).save();

        /* movimentador de vendas */
        vendaMovimenta = new VendaMovimenta(vendaModelo);

    }

    @Test
    public void testFinalizar() {

        /* quantidade final esperada no estoque */
        BigDecimal quantidadeEsperadaEstoque = produtoModelo.getQuantidade();
        quantidadeEsperadaEstoque = quantidadeEsperadaEstoque.subtract(vendaModeloItem.getQuantidade());

        /* finaliza a venda */
        vendaMovimenta.finalizar();

        /* etapa: testa se a venda está finalizada */
        System.out.println("Testando classe VendaMovimenta metodo: finalizar etapa: venda finalizada");
        assertEquals(true, vendaModelo.isFinalizado());

        /* etapa: testa se a quantidade em estoque foi atualizada */
        System.out.println("Testando classe VendaMovimenta metodo: finalizar etapa: checa quantidade em estoque");
        assertEquals(true, produtoModelo.getQuantidade().equals(quantidadeEsperadaEstoque));

        /* etapa: testa se os registros no financeiro foram lançados */
        JPQL jpql = new JPQL(new FinanceiroModelo());
        jpql.addParametroIgual("idVenda", vendaModelo.getId());
        long quantidadeResultados = new Repository(new FinanceiroModelo()).countTodos(jpql.getCondicaoWhere());
        System.out.println("Testando classe VendaMovimenta metodo: finalizar etapa: checa quantidade de lançamentos no financeiro");
        assertEquals(true, quantidadeResultados == vendaModelo.getNumeroParcelas());

    }

    @Test
    public void testReabrir() {

        /* quantidade final esperada no estoque */
        BigDecimal quantidadeEsperadaEstoque = produtoModelo.getQuantidade();

        /* finaliza a venda e reabre a venda */
        vendaMovimenta.finalizar();
        vendaMovimenta.reabrir();

        /* etapa: testa se a venda está aberta */
        System.out.println("Testando classe VendaMovimenta metodo: reabrir etapa: venda finalizada");
        assertEquals(false, vendaModelo.isFinalizado());

        /* etapa: testa se a quantidade em estoque foi atualizada */
        System.out.println("Testando classe VendaMovimenta metodo: reabrir etapa: checa quantidade em estoque");
        assertEquals(true, produtoModelo.getQuantidade().equals(quantidadeEsperadaEstoque));

        /* etapa: testa se os registros no financeiro foram removidos */
        JPQL jpql = new JPQL(new FinanceiroModelo());
        jpql.addParametroIgual("idVenda", vendaModelo.getId());
        long quantidadeResultados = new Repository(new FinanceiroModelo()).countTodos(jpql.getCondicaoWhere());
        System.out.println("Testando classe VendaMovimenta metodo: reabrir etapa: checa quantidade de lançamentos no financeiro");
        assertEquals(true, quantidadeResultados == 0);

    }

    @Test
    public void testExcluir() {

        /* quantidade final esperada no estoque */
        BigDecimal quantidadeEsperadaEstoque = produtoModelo.getQuantidade();

        /* exclui a venda */
        vendaMovimenta.excluir();

        /* etapa: testa se a quantidade em estoque foi atualizada */
        System.out.println("Testando classe VendaMovimenta metodo: excluir etapa: checa quantidade em estoque");
        assertEquals(true, produtoModelo.getQuantidade().equals(quantidadeEsperadaEstoque));

        /* etapa: testa se os registros no financeiro foram lançados */
        JPQL jpql = new JPQL(new FinanceiroModelo());
        jpql.addParametroIgual("idVenda", vendaModelo.getId());
        long quantidadeResultados = new Repository(new FinanceiroModelo()).countTodos(jpql.getCondicaoWhere());
        System.out.println("Testando classe VendaMovimenta metodo: excluir etapa: checa quantidade de lançamentos no financeiro");
        assertEquals(true, quantidadeResultados == 0);

    }

}
