package br.com.salomaotech.genesys.model.financeiro;

import br.com.salomaotech.genesys.model.centro_custo.CentroCustoModelo;
import br.com.salomaotech.genesys.model.venda.VendaModelo;
import br.com.salomaotech.sistema.jpa.JPQL;
import br.com.salomaotech.sistema.jpa.Repository;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import static java.util.Objects.isNull;

public class FinanceiroMovimenta {

    private final VendaModelo vendaModelo;

    public FinanceiroMovimenta(VendaModelo vendaModelo) {
        this.vendaModelo = vendaModelo;
    }

    private long retornaIdCentroCustoVenda() {

        try {

            JPQL jpql = new JPQL(new CentroCustoModelo());
            jpql.addParametroIgual("codigo", "2.1.2");
            List<CentroCustoModelo> centroCustoModelo = new Repository(new CentroCustoModelo()).getResults(jpql.construirSelect());
            return centroCustoModelo.get(0).getId();

        } catch (Exception ex) {

            return 0;

        }

    }

    private void cadastrarFinanceiro(Calendar data, BigDecimal valor, String descricao) {

        FinanceiroModelo financeiroModelo = new FinanceiroModelo();
        financeiroModelo.setData(data);
        financeiroModelo.setValor(valor);
        financeiroModelo.setDescricao(descricao);
        financeiroModelo.setIsDespesa(false);
        financeiroModelo.setIdVenda(vendaModelo.getId());
        financeiroModelo.setIsPago(true);
        financeiroModelo.setIdCentroCusto(retornaIdCentroCustoVenda());
        new Repository(financeiroModelo).save();

    }

    public void adicionar() {

        /* valida se o venda existe */
        if (!isNull(new Repository(new VendaModelo()).findById(vendaModelo.getId()))) {

            cadastrarFinanceiro(vendaModelo.getData(), vendaModelo.getValor(), "Venda código: " + vendaModelo.getId());

        }

    }

    public void remover() {

        /* valida se o venda existe */
        if (!isNull(new Repository(new VendaModelo()).findById(vendaModelo.getId()))) {

            JPQL jpql = new JPQL(new FinanceiroModelo());
            jpql.addParametroIgual("idVenda", vendaModelo.getId());

            /* lista os vendas com o ID de venda informado */
            List<FinanceiroModelo> financeiroModeloList = new Repository(new FinanceiroModelo()).getResults(jpql.construirSelect());

            for (FinanceiroModelo financeiroModelo : financeiroModeloList) {

                /* movimenta o financeiro removendo */
                new Repository(new FinanceiroModelo()).delete(financeiroModelo.getId());

            }

        }

    }

}
