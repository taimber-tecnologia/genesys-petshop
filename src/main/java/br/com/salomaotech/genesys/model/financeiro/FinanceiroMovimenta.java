package br.com.salomaotech.genesys.model.financeiro;

import br.com.salomaotech.genesys.model.venda.cliente.VendaModelo;
import br.com.salomaotech.sistema.algoritmos.BigDecimais;
import br.com.salomaotech.sistema.algoritmos.Datas;
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

    private void cadastrarFinanceiro(Calendar data, BigDecimal valor, String descricao) {

        FinanceiroModelo financeiroModelo = new FinanceiroModelo();
        financeiroModelo.setData(data);
        financeiroModelo.setValor(valor);
        financeiroModelo.setDescricao(descricao);
        financeiroModelo.setIdCentroCusto(vendaModelo.getIdCentroCusto());
        financeiroModelo.setIsDespesa(false);
        financeiroModelo.setIsPago(false);
        financeiroModelo.setIdVenda(vendaModelo.getId());
        financeiroModelo.setIsPago(vendaModelo.isIsPago());
        financeiroModelo.setIdCliente(vendaModelo.getIdCliente());
        new Repository(financeiroModelo).save();

    }

    private void gerarParcelas(VendaModelo vendaModelo) {

        BigDecimal numeroParcelas = BigDecimais.formatarParaBigDecimal(String.valueOf(vendaModelo.getNumeroParcelas()));
        BigDecimal valorParcela = BigDecimais.dividir(vendaModelo.getValorTotal(), numeroParcelas);
        BigDecimal valorDiferenca = vendaModelo.getValorTotal().subtract(valorParcela.multiply(numeroParcelas));

        /* itera parcelas */
        for (int i = 1; i <= vendaModelo.getNumeroParcelas(); i++) {

            BigDecimal valorCalculado = valorParcela;

            /* na última parcela adicione o valor da diferença */
            if (i == vendaModelo.getNumeroParcelas()) {

                valorCalculado = valorCalculado.add(valorDiferenca);

            }

            String descricao = "Parcela nº " + i + "/" + vendaModelo.getNumeroParcelas() + " - Venda de nº " + vendaModelo.getId();

            /* cadastra o parcelamento */
            cadastrarFinanceiro(Datas.adicionarMesCalendar(vendaModelo.getData(), i), valorCalculado, descricao);

        }

    }

    public void adicionar() {

        /* valida se o venda existe */
        if (!isNull(new Repository(new VendaModelo()).findById(vendaModelo.getId()))) {

            if (vendaModelo.getNumeroParcelas() > 1) {

                /* movimenta o financeiro com parcelamentos */
                gerarParcelas(vendaModelo);

            } else {

                /* movimenta o financeiro com apenas 1 lançamento */
                cadastrarFinanceiro(vendaModelo.getData(), vendaModelo.getValorTotal(), "Venda de nº " + vendaModelo.getId());

            }

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
