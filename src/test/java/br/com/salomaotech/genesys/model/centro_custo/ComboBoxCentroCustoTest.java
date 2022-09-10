package br.com.salomaotech.genesys.model.centro_custo;

import br.com.salomaotech.genesys.model.centro_custo.CentroCustoModelo;
import br.com.salomaotech.genesys.model.centro_custo.ComboBoxCentroCusto;
import br.com.salomaotech.sistema.jpa.Repository;
import javax.swing.JComboBox;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ComboBoxCentroCustoTest {

    private final JComboBox jComboBox = new JComboBox();
    private final CentroCustoModelo centroCustoModelo = new CentroCustoModelo();
    private final ComboBoxCentroCusto comboBoxCentroCusto = new ComboBoxCentroCusto(jComboBox);

    public ComboBoxCentroCustoTest() {

        /* simula cadastro de centro de custo */
        new Repository(new CentroCustoModelo()).deleteTodos();
        centroCustoModelo.setCodigo("1.0");
        centroCustoModelo.setNome("Teste");
        new Repository(centroCustoModelo).save();

    }

    @Test
    public void testPreencher() {

        comboBoxCentroCusto.preencher();
        System.out.println("Testando ComboBoxCentroCusto metodo: preencher");
        assertEquals(true, jComboBox.getItemCount() == 2);

    }

    @Test
    public void testSelecionarItemPorId() {

        comboBoxCentroCusto.selecionarItemPorId(centroCustoModelo.getId());
        System.out.println("Testando ComboBoxCentroCusto metodo: selecionarItemPorId");
        assertEquals(true, comboBoxCentroCusto.getIdSelecionado() == centroCustoModelo.getId());

    }

    @Test
    public void testGetIdSelecionado() {

        System.out.println("Testando ComboBoxCentroCusto metodo: getIdSelecionado etapa 01");
        assertEquals(true, comboBoxCentroCusto.getIdSelecionado() == 0);

        comboBoxCentroCusto.selecionarItemPorId(centroCustoModelo.getId());
        System.out.println("Testando ComboBoxCentroCusto metodo: getIdSelecionado etapa 02");
        assertEquals(true, comboBoxCentroCusto.getIdSelecionado() == centroCustoModelo.getId());

    }

}
