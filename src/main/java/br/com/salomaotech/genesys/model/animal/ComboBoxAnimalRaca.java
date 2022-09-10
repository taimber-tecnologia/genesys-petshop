package br.com.salomaotech.genesys.model.animal;

import br.com.salomaotech.sistema.algoritmos.ValidaStringIsEmpty;
import br.com.salomaotech.sistema.jpa.JPQL;
import br.com.salomaotech.sistema.jpa.Repository;
import java.util.LinkedHashSet;
import java.util.List;
import javax.swing.JComboBox;

public class ComboBoxAnimalRaca {

    private LinkedHashSet<String> nomesDeRacasSet;
    private final JComboBox jComboBox;

    public ComboBoxAnimalRaca(JComboBox jComboBox) {

        this.jComboBox = jComboBox;

    }

    private JPQL popularJpql() {

        JPQL jpql = new JPQL(new AnimalModelo());
        jpql.addOrderBy("raca", "ASC");
        return jpql;

    }

    public void preencher() {

        jComboBox.removeAllItems();
        jComboBox.addItem("");

        List<AnimalModelo> animalModeloList = new Repository(new AnimalModelo()).getResults(popularJpql().construirSelect());
        nomesDeRacasSet = new LinkedHashSet();

        animalModeloList.forEach(animal -> {

            if (!ValidaStringIsEmpty.isEmpty(animal.getRaca())) {

                nomesDeRacasSet.add(animal.getRaca());

            }

        });

        nomesDeRacasSet.forEach(raca -> {

            jComboBox.addItem(raca);

        });

    }

}
