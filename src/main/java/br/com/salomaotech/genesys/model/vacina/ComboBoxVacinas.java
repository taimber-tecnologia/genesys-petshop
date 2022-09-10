package br.com.salomaotech.genesys.model.vacina;

import br.com.salomaotech.sistema.algoritmos.ValidaStringIsEmpty;
import br.com.salomaotech.sistema.jpa.JPQL;
import br.com.salomaotech.sistema.jpa.Repository;
import java.util.LinkedHashSet;
import java.util.List;
import javax.swing.JComboBox;

public class ComboBoxVacinas {

    private LinkedHashSet<String> nomesDeVacinasSet;
    private final JComboBox jComboBox;

    public ComboBoxVacinas(JComboBox jComboBox) {

        this.jComboBox = jComboBox;

    }

    private JPQL popularJpql() {

        JPQL jpql = new JPQL(new VacinaModelo());
        jpql.addOrderBy("nome", "ASC");
        return jpql;

    }

    public void preencher() {

        jComboBox.removeAllItems();
        jComboBox.addItem("");

        List<VacinaModelo> vacinaModeloList = new Repository(new VacinaModelo()).getResults(popularJpql().construirSelect());
        nomesDeVacinasSet = new LinkedHashSet();

        vacinaModeloList.forEach(vacina -> {

            if (!ValidaStringIsEmpty.isEmpty(vacina.getNome())) {

                nomesDeVacinasSet.add(vacina.getNome());

            }

        });

        nomesDeVacinasSet.forEach(nome -> {

            jComboBox.addItem(nome);

        });

    }

}
