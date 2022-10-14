package br.com.salomaotech.genesys.controller.principal;

import br.com.salomaotech.genesys.model.agenda.AgendaPesquisa;
import br.com.salomaotech.genesys.model.notificacoes.Notificacoes;
import br.com.salomaotech.genesys.view.JFprincipal;

public class PrincipalMetodos {

    private final JFprincipal view;
    private final AgendaPesquisa agendaPesquisa;

    public PrincipalMetodos(JFprincipal view) {
        this.view = view;
        agendaPesquisa = new AgendaPesquisa(view.jTagendaResultados, view.jCpaginador);
    }

    public void carregaAgendaDia() {

        agendaPesquisa.setDataInicialDate(view.jDagendaDataSemana.getCalendar());
        agendaPesquisa.setStatus(view.jCpesquisaStatus.getSelectedItem().toString());
        agendaPesquisa.pesquisar();

    }

    public void carregaNotificacoes() {

        Notificacoes notificacoes = new Notificacoes();
        view.jMagenda.setText("Agenda - " + notificacoes.getAgenda());
        view.jMestoque.setText("Estoque - " + notificacoes.getProdutoEstoqueMinimo());
        view.jMfinanceiroPagar.setText("Pagar - " + notificacoes.getFinanceiroPagar());
        view.jMfinanceiroReceber.setText("Receber - " + notificacoes.getFinanceiroReceber());
        view.jMnotificacoes.setText("Notificações - " + notificacoes.total());

    }

}
