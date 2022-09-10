package br.com.salomaotech.genesys.controller.principal;

import br.com.salomaotech.genesys.model.agenda.AgendaPesquisa;
import br.com.salomaotech.genesys.view.JFprincipal;

public class PrincipalMetodos {

    private final JFprincipal view;
    private final AgendaPesquisa agendaPesquisa;

    public PrincipalMetodos(JFprincipal view) {
        this.view = view;
        agendaPesquisa = new AgendaPesquisa(view.jTagendaResultados, view.jCpaginador);
    }

    public void carregaAgendaDia() {

        agendaPesquisa.setDataAgenda(view.jDagendaDataSemana.getCalendar());
        agendaPesquisa.setStatus(view.jCpesquisaStatus.getSelectedItem().toString());
        agendaPesquisa.pesquisar();

    }

}
