package br.com.salomaotech.genesys.model.vacina;

import br.com.salomaotech.genesys.model.animal.AnimalModelo;
import br.com.salomaotech.genesys.model.cliente.ClienteModelo;
import br.com.salomaotech.sistema.jpa.Repository;
import javax.swing.JComboBox;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ComboBoxVacinasTest {

    private final JComboBox jComboBox = new JComboBox();
    private final VacinaModelo vacinaModelo = new VacinaModelo();
    private final ComboBoxVacinas comboBoxVacinas = new ComboBoxVacinas(jComboBox);

    public ComboBoxVacinasTest() {

        /* simula cadastro de vacina */
        new Repository(new VacinaModelo()).deleteTodos();
        vacinaModelo.setNome("Teste");
        vacinaModelo.setIdCliente(new Repository(new ClienteModelo()).save());
        vacinaModelo.setIdAnimal(new Repository(new AnimalModelo()).save());
        new Repository(vacinaModelo).save();

    }

    @Test
    public void testPreencher() {

        comboBoxVacinas.preencher();
        System.out.println("Testando classe ComboBoxVacinas metodo: preencher");
        assertEquals(true, jComboBox.getItemCount() == 2);

    }

}
