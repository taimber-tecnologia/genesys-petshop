package br.com.salomaotech.genesys.model.animal;

import br.com.salomaotech.genesys.model.animal.AnimalModelo;
import br.com.salomaotech.genesys.model.animal.ComboBoxAnimalRaca;
import br.com.salomaotech.sistema.jpa.Repository;
import javax.swing.JComboBox;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ComboBoxAnimalRacaTest {

    private final JComboBox jComboBox = new JComboBox();
    private final AnimalModelo animalModelo = new AnimalModelo();
    private final ComboBoxAnimalRaca comboBoxAnimalRaca = new ComboBoxAnimalRaca(jComboBox);

    public ComboBoxAnimalRacaTest() {

        /* simula cadastro de animal */
        new Repository(new AnimalModelo()).deleteTodos();
        animalModelo.setNome("Teste");
        animalModelo.setEspecie("Cachorro");
        animalModelo.setRaca("Vira Lata");
        new Repository(animalModelo).save();

    }

    @Test
    public void testPreencher() {

        comboBoxAnimalRaca.preencher();
        System.out.println("Testando classe ComboBoxAnimalRaca metodo: preencher");
        assertEquals(true, jComboBox.getItemCount() == 2);

    }

}
