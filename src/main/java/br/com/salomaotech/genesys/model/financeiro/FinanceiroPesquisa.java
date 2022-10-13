package br.com.salomaotech.genesys.model.financeiro;

import br.com.salomaotech.genesys.model.centro_custo.CentroCustoModelo;
import br.com.salomaotech.sistema.algoritmos.BigDecimais;
import br.com.salomaotech.sistema.algoritmos.ConverteNumeroParaMoedaBr;
import br.com.salomaotech.sistema.algoritmos.Datas;
import br.com.salomaotech.sistema.jpa.JPQL;
import br.com.salomaotech.sistema.jpa.Paginador;
import br.com.salomaotech.sistema.jpa.Repository;
import br.com.salomaotech.sistema.swing.MudaCorLinhaJtable;
import java.awt.Color;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import static java.util.Objects.isNull;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class FinanceiroPesquisa {

    private final JTable jTresultados;
    private final JComboBox jCpaginador;
    private final JLabel labelSaldo;
    private Date dataInicialDate;
    private Date dataFinalDate;
    private String pagamentoRealizado;
    private String pagamentoDespesa;
    private boolean isDataAnterior;

    public FinanceiroPesquisa(JTable jTresultados, JComboBox jCpaginador, JLabel labelSaldo) {
        this.jTresultados = jTresultados;
        this.jCpaginador = jCpaginador;
        this.labelSaldo = labelSaldo;
    }

    public void setDataInicialDate(Calendar dataInicialCalendar) {

        if (!isNull(dataInicialCalendar)) {

            this.dataInicialDate = dataInicialCalendar.getTime();

        }

    }

    public void setDataFinalDate(Calendar dataFinalCalendar) {

        if (!isNull(dataFinalCalendar)) {

            this.dataFinalDate = dataFinalCalendar.getTime();

        }

    }

    public void setPagamentoRealizado(String pagamentoRealizado) {

        this.pagamentoRealizado = pagamentoRealizado;

    }

    public void setPagamentoDespesa(String pagamentoDespesa) {

        this.pagamentoDespesa = pagamentoDespesa;

    }

    public void setIsDataAnterior(boolean isDataAnterior) {

        this.isDataAnterior = isDataAnterior;

    }

    private void popularDados(List<FinanceiroModelo> financeiroModeloList) {

        DefaultTableModel defaultTableModel = (DefaultTableModel) jTresultados.getModel();
        defaultTableModel.setNumRows(0);
        List<Color> colorList = new ArrayList();
        int contador = 0;

        /* saldo total */
        BigDecimal saldoTotal = new BigDecimal(0);

        /* itera a lista de dados do modelo */
        for (FinanceiroModelo financeiroModelo : financeiroModeloList) {

            /* modelos auxiliares */
            CentroCustoModelo centroCustoModelo = (CentroCustoModelo) new Repository(new CentroCustoModelo()).findById(financeiroModelo.getIdCentroCusto());

            BigDecimal valor;
            String situacao;
            String descricao = financeiroModelo.getDescricao();

            /* valida se é uma despesa */
            if (financeiroModelo.isIsDespesa()) {

                valor = financeiroModelo.getValor().negate();
                colorList.add(Color.decode("#e4b7bc"));

            } else {

                valor = financeiroModelo.getValor();
                colorList.add(Color.decode("#b2ddc3"));

            }

            /* atualiza o saldo */
            saldoTotal = saldoTotal.add(valor);

            /* valida se foi pago */
            if (financeiroModelo.isIsPago()) {

                if (financeiroModelo.isIsDespesa()) {

                    situacao = "Pago";

                } else {

                    situacao = "Recebido";

                }

            } else {

                situacao = "Em aberto";

            }

            Object[] linhaDefaultTableModel = new Object[]{
                financeiroModelo.getId(),
                Datas.calendarParaStringBr(financeiroModelo.getData()),
                descricao,
                centroCustoModelo.getNomeCompleto(),
                situacao,
                ConverteNumeroParaMoedaBr.converter(valor.toString())

            };

            defaultTableModel.insertRow(contador, linhaDefaultTableModel);
            contador++;

        }

        /* popula cores */
        MudaCorLinhaJtable.mudar(this.jTresultados, colorList);

        /* seta o saldo */
        labelSaldo.setText(ConverteNumeroParaMoedaBr.converter(saldoTotal.toString()));

        if (BigDecimais.isNegativo(saldoTotal)) {

            labelSaldo.setForeground(Color.RED);

        } else {

            labelSaldo.setForeground(Color.decode("#008744"));

        }

    }

    public void pesquisar() {

        JPQL jpql = new JPQL(new FinanceiroModelo());
        jpql.addOrderBy("data", "ASC");
        jpql.addOrderBy("idVenda", "ASC");

        if (isDataAnterior) {

            jpql.addParametroMenorIgual("data", dataInicialDate);

        } else {

            if (!isNull(dataInicialDate) && !isNull(dataFinalDate)) {

                /* pesquisa entre datas */
                jpql.addParametroMaiorIgual("data", dataInicialDate);
                jpql.addParametroMenorIgual("data", dataFinalDate);

            } else {

                /* pesquisa exatamente a data inicial */
                if (!isNull(dataInicialDate)) {

                    jpql.addParametroIgual("data", dataInicialDate);

                }

                /* pesquisa da data final para trás */
                if (!isNull(dataFinalDate)) {

                    jpql.addParametroMenorIgual("data", dataFinalDate);

                }

            }

        }

        /* valida pagamento */
        if (!isNull(pagamentoRealizado)) {

            switch (pagamentoRealizado) {

                case "Sim":
                    jpql.addParametroIgual("isPago", true);
                    break;

                case "Nao":
                    jpql.addParametroIgual("isPago", false);
                    break;

            }

        }

        /* valida despesa */
        if (!isNull(pagamentoDespesa)) {

            switch (pagamentoDespesa) {

                case "Pagar":
                    jpql.addParametroIgual("isDespesa", true);
                    break;

                case "Receber":
                    jpql.addParametroIgual("isDespesa", false);
                    break;

            }

        }

        /* pesquisa os dados */
        Paginador paginador = new Paginador(jpql, jCpaginador, new FinanceiroModelo());
        Repository repository = new Repository(new FinanceiroModelo());
        List<FinanceiroModelo> financeiroModeloList = repository.getResultsComPaginador(jpql.construirSelect(), paginador.getPageNumber(), paginador.getPageSize());
        popularDados(financeiroModeloList);

    }

}
