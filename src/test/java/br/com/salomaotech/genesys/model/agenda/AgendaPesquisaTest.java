package br.com.salomaotech.genesys.model.agenda;

import br.com.salomaotech.genesys.view.JFagenda;
import br.com.salomaotech.sistema.jpa.Repository;
import java.util.Calendar;
import org.junit.Test;
import static org.junit.Assert.*;

public class AgendaPesquisaTest {

    private final JFagenda view = new JFagenda();
    private final Calendar calendar = Calendar.getInstance();
    private final AgendaModelo agendaModelo = new AgendaModelo();
    private final AgendaPesquisa agendaPesquisa = new AgendaPesquisa(view.jTresultados, view.jCpaginador);

    public AgendaPesquisaTest() {

        /* simula cadastro de agenda */
        new Repository(new AgendaModelo()).deleteTodos();
        agendaModelo.setDataAgenda(calendar);
        agendaModelo.setNomeCliente("Teste");
        agendaModelo.setStatus("Atendido");
        new Repository(agendaModelo).save();

    }

    @Test
    public void testSetDataInicialDate() {

        boolean isErro = false;

        try {

            agendaPesquisa.setDataInicialDate(calendar.getTime());

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe AgendaPesquisa método: setDataInicialDate");
        assertEquals(false, isErro);

    }

    @Test
    public void testSetDataFinalDate() {

        boolean isErro = false;

        try {

            agendaPesquisa.setDataFinalDate(calendar.getTime());

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe AgendaPesquisa metodo: setDataFinalDate");
        assertEquals(false, isErro);

    }

    @Test
    public void testSetNomeCliente() {

        boolean isErro = false;

        try {

            agendaPesquisa.setNomeCliente(null);

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe AgendaPesquisa método: setNomeCliente");
        assertEquals(false, isErro);

    }

    @Test
    public void testSetStatus() {

        boolean isErro = false;

        try {

            agendaPesquisa.setStatus(agendaModelo.getStatus());

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe AgendaPesquisa método: setStatus");
        assertEquals(false, isErro);

    }

    @Test
    public void testSetIsDataAnterior() {

        boolean isErro = false;

        try {

            agendaPesquisa.setIsDataAnterior(true);

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe AgendaPesquisa metodo: setIsDataAnterior");
        assertEquals(false, isErro);

    }

    @Test
    public void testPesquisar() {

        /* usando filtro: nenhum */
        agendaPesquisa.setDataInicialDate(null);
        agendaPesquisa.setDataFinalDate(null);
        agendaPesquisa.setNomeCliente(null);
        agendaPesquisa.setStatus(null);
        agendaPesquisa.pesquisar();
        System.out.println("Testando classe AgendaPesquisa método: pesquisar etapa 01 - sem filtros");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: data inicial */
        agendaPesquisa.setDataInicialDate(calendar.getTime());
        agendaPesquisa.setDataFinalDate(null);
        agendaPesquisa.setNomeCliente(null);
        agendaPesquisa.setStatus(null);
        agendaPesquisa.pesquisar();
        System.out.println("Testando classe AgendaPesquisa método: pesquisar etapa 02 - data inicial");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: data final */
        agendaPesquisa.setDataInicialDate(null);
        agendaPesquisa.setDataFinalDate(calendar.getTime());
        agendaPesquisa.setNomeCliente(null);
        agendaPesquisa.setStatus(null);
        agendaPesquisa.pesquisar();
        System.out.println("Testando classe AgendaPesquisa método: pesquisar etapa 03 - data final");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: cliente */
        agendaPesquisa.setDataInicialDate(null);
        agendaPesquisa.setDataFinalDate(null);
        agendaPesquisa.setNomeCliente(agendaModelo.getNomeCliente());
        agendaPesquisa.setStatus(null);
        agendaPesquisa.pesquisar();
        System.out.println("Testando classe AgendaPesquisa método: pesquisar etapa 04 - por cliente");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: status */
        agendaPesquisa.setDataInicialDate(null);
        agendaPesquisa.setDataFinalDate(null);
        agendaPesquisa.setNomeCliente(null);
        agendaPesquisa.setStatus("Atendido");
        agendaPesquisa.pesquisar();
        System.out.println("Testando classe AgendaPesquisa método: pesquisar etapa 05 - por status");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: todos */
        agendaPesquisa.setDataInicialDate(calendar.getTime());
        agendaPesquisa.setDataFinalDate(calendar.getTime());
        agendaPesquisa.setNomeCliente(agendaModelo.getNomeCliente());
        agendaPesquisa.setStatus("Atendido");
        agendaPesquisa.pesquisar();
        System.out.println("Testando classe AgendaPesquisa método: pesquisar etapa 06 - todos filtros");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* usando filtro: data anterior */
        agendaPesquisa.setDataInicialDate(calendar.getTime());
        agendaPesquisa.setDataFinalDate(null);
        agendaPesquisa.setNomeCliente(null);
        agendaPesquisa.setStatus(null);
        agendaPesquisa.setIsDataAnterior(true);
        agendaPesquisa.pesquisar();
        System.out.println("Testando classe AgendaPesquisa método: pesquisar etapa 07 - data anterior");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* TESTES ADICIONAIS PARA DATAS SEM RESULTADOS */
        Calendar dataFutura = Calendar.getInstance();
        dataFutura.add(Calendar.YEAR, 1);
        agendaPesquisa.setDataInicialDate(dataFutura.getTime());
        agendaPesquisa.setDataFinalDate(null);
        agendaPesquisa.setNomeCliente(null);
        agendaPesquisa.setStatus(null);
        agendaPesquisa.setIsDataAnterior(false);
        agendaPesquisa.pesquisar();
        System.out.println("Testando classe AgendaPesquisa método: pesquisar etapa 08 - data futura (sem resultados)");
        assertEquals(0, view.jTresultados.getRowCount());

        /* intervalo de datas sem resultados */
        Calendar dataPassada = Calendar.getInstance();
        dataPassada.add(Calendar.YEAR, -1);
        agendaPesquisa.setDataInicialDate(dataPassada.getTime());
        agendaPesquisa.setDataFinalDate(dataPassada.getTime());
        agendaPesquisa.setNomeCliente(null);
        agendaPesquisa.setStatus(null);
        agendaPesquisa.setIsDataAnterior(false);
        agendaPesquisa.pesquisar();
        System.out.println("Testando classe AgendaPesquisa método: pesquisar etapa 09 - intervalo sem resultados");
        assertEquals(0, view.jTresultados.getRowCount());

        /* data anterior mas com data inicial muito antiga */
        Calendar dataMuitoAntiga = Calendar.getInstance();
        dataMuitoAntiga.add(Calendar.YEAR, -10);
        agendaPesquisa.setDataInicialDate(dataMuitoAntiga.getTime());
        agendaPesquisa.setDataFinalDate(null);
        agendaPesquisa.setNomeCliente(null);
        agendaPesquisa.setStatus(null);
        agendaPesquisa.setIsDataAnterior(true);
        agendaPesquisa.pesquisar();
        System.out.println("Testando classe AgendaPesquisa método: pesquisar etapa 10 - data anterior muito antiga (sem resultados)");
        assertEquals(0, view.jTresultados.getRowCount());

    }

}
