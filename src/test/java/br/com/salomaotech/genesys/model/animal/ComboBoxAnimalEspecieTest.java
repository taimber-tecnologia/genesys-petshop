package br.com.salomaotech.genesys.model.animal;

import br.com.salomaotech.sistema.jpa.Repository;
import javax.swing.JComboBox;
import org.junit.Test;
import static org.junit.Assert.*;

public class ComboBoxAnimalEspecieTest {

    private final JComboBox jComboBox = new JComboBox();
    private final AnimalModelo animalModelo = new AnimalModelo();
    private final ComboBoxAnimalEspecie comboBoxAnimalEspecie = new ComboBoxAnimalEspecie(jComboBox);

    public ComboBoxAnimalEspecieTest() {

        /* simula cadastro de animal */
        new Repository(new AnimalModelo()).deleteTodos();
        animalModelo.setNome("Teste");
        animalModelo.setEspecie("Cachorro");
        new Repository(animalModelo).save();

    }

    @Test
    public void testPreencher() {

        comboBoxAnimalEspecie.preencher();
        System.out.println("Testando classe ComboBoxAnimalEspecie metodo: preencher");
        assertEquals(true, jComboBox.getItemCount() == 2);

    }

}
