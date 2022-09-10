package br.com.salomaotech.genesys.model.animal;

import br.com.salomaotech.sistema.jpa.JPQL;
import br.com.salomaotech.sistema.jpa.Repository;
import br.com.salomaotech.sistema.patterns.Command;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.swing.JComboBox;

public class ComboBoxAnimais implements Command {

    private final JComboBox jComboBox;
    private List<AnimalModelo> animalModeloList = new ArrayList();
    private final LinkedHashMap cadastroDeAnimaisMap = new LinkedHashMap();
    private long idCliente;
    private long idAnimalSelecionado;

    public ComboBoxAnimais(JComboBox jComboBox) {

        this(jComboBox, 0);

    }

    public ComboBoxAnimais(JComboBox jComboBox, long idCliente) {

        this.jComboBox = jComboBox;
        this.idCliente = idCliente;
        addEventos();

    }

    private JPQL popularJpql() {

        JPQL jpql = new JPQL(new AnimalModelo());
        jpql.addParametroIgual("idCliente", idCliente);
        jpql.addOrderBy("nome", "ASC");

        return jpql;

    }

    private void addEventos() {

        jComboBox.addActionListener((ActionEvent e) -> {

            try {

                idAnimalSelecionado = (long) cadastroDeAnimaisMap.get(jComboBox.getSelectedItem());

            } catch (Exception ex) {

                idAnimalSelecionado = 0;

            }

        });

    }

    public void preencher() {

        jComboBox.removeAllItems();
        jComboBox.addItem("");

        animalModeloList = new Repository(new AnimalModelo()).getResults(popularJpql().construirSelect());
        animalModeloList.forEach(animal -> {

            jComboBox.addItem(animal.getNome());
            cadastroDeAnimaisMap.put(animal.getNome(), animal.getId());

        });

    }

    public void selecionarItemComBox(long idAnimal) {

        jComboBox.setSelectedItem("");

        animalModeloList = new Repository(new AnimalModelo()).getResults(popularJpql().construirSelect());
        animalModeloList.forEach(animal -> {

            if (animal.getId() == idAnimal) {

                jComboBox.setSelectedItem(animal.getNome());
                idAnimalSelecionado = idAnimal;

            }

        });

    }

    @Override
    public void executar(Object idCliente) {

        this.idCliente = (long) idCliente;
        preencher();

    }

    public long getIdAnimalSelecionado() {
        return idAnimalSelecionado;
    }

}
