package br.com.salomaotech.genesys.model.notificacoes;

import br.com.salomaotech.genesys.model.agenda.AgendaModelo;
import br.com.salomaotech.genesys.model.financeiro.FinanceiroModelo;
import br.com.salomaotech.sistema.jpa.JPQL;
import br.com.salomaotech.sistema.jpa.Repository;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Notificacoes {

    private final Date dataHoje;

    public Notificacoes() {

        dataHoje = new Date(new SimpleDateFormat("yyyy/MM/dd").format(new Date()));

    }

    public long getAgenda() {

        JPQL jpql = new JPQL(new AgendaModelo());
        jpql.addParametroMenorIgual("dataAgenda", dataHoje);
        jpql.addParametroIgual("status", "1 - Agendado");

        Repository repository = new Repository(new AgendaModelo());
        return repository.countTodos(jpql.getCondicaoWhere());

    }

    public long getFinanceiroPagar() {

        JPQL jpql = new JPQL(new FinanceiroModelo());
        jpql.addParametroMenorIgual("data", dataHoje);
        jpql.addParametroIgual("isPago", false);
        jpql.addParametroIgual("isDespesa", true);

        Repository repository = new Repository(new FinanceiroModelo());
        return repository.countTodos(jpql.getCondicaoWhere());

    }

    public long getFinanceiroReceber() {

        JPQL jpql = new JPQL(new FinanceiroModelo());
        jpql.addParametroMenorIgual("data", dataHoje);
        jpql.addParametroIgual("isPago", false);
        jpql.addParametroIgual("isDespesa", false);

        Repository repository = new Repository(new FinanceiroModelo());
        return repository.countTodos(jpql.getCondicaoWhere());

    }

    public int total() {

        int retorno = 0;
        retorno += getAgenda();
        retorno += getFinanceiroPagar();
        retorno += getFinanceiroReceber();
        return retorno;

    }

}
