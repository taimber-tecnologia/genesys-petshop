package br.com.salomaotech.genesys.model.animal;

import br.com.salomaotech.sistema.algoritmos.ValidaStringIsEmpty;
import br.com.salomaotech.sistema.jpa.JPQL;
import br.com.salomaotech.sistema.jpa.Repository;
import java.util.LinkedHashSet;
import java.util.List;
import javax.swing.JComboBox;

public class ComboBoxAnimalEspecie {

    private LinkedHashSet<String> nomesDeEspeciesSet;
    private final JComboBox jComboBox;

    public ComboBoxAnimalEspecie(JComboBox jComboBox) {

        this.jComboBox = jComboBox;

    }

    private JPQL popularJpql() {

        JPQL jpql = new JPQL(new AnimalModelo());
        jpql.addOrderBy("especie", "ASC");
        return jpql;

    }

    public void preencher() {

        jComboBox.removeAllItems();
        jComboBox.addItem("");

        List<AnimalModelo> animalModeloList = new Repository(new AnimalModelo()).getResults(popularJpql().construirSelect());
        nomesDeEspeciesSet = new LinkedHashSet();

        animalModeloList.forEach(animal -> {

            if (!ValidaStringIsEmpty.isEmpty(animal.getEspecie())) {

                nomesDeEspeciesSet.add(animal.getEspecie());

            }

        });

        nomesDeEspeciesSet.forEach(especie -> {

            jComboBox.addItem(especie);

        });

    }

}
