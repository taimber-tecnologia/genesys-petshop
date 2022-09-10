package br.com.salomaotech.genesys.model.animal;

import br.com.salomaotech.genesys.model.animal.AnimalModelo;
import java.math.BigDecimal;
import java.util.Calendar;
import static java.util.Objects.isNull;
import org.junit.Test;
import static org.junit.Assert.*;

public class AnimalModeloTest {

    private final AnimalModelo animalModelo = new AnimalModelo();

    @Test
    public void testGetId() {

        System.out.println("Testando classe AnimalModelo metodo getId");
        assertEquals(true, animalModelo.getId() == 0);

    }

    @Test
    public void testSetId() {

        animalModelo.setId(1);
        System.out.println("Testando classe AnimalModelo metodo setId");
        assertEquals(true, animalModelo.getId() == 1);

    }

    @Test
    public void testGetIdCliente() {

        System.out.println("Testando classe AnimalModelo metodo getIdCliente");
        assertEquals(true, animalModelo.getIdCliente() == 0);

    }

    @Test
    public void testSetIdCliente() {

        animalModelo.setIdCliente(1);
        System.out.println("Testando classe AnimalModelo metodo setIdCliente");
        assertEquals(true, animalModelo.getIdCliente() == 1);

    }

    @Test
    public void testGetNome() {

        System.out.println("Testando classe AnimalModelo metodo getNome");
        assertEquals(true, isNull(animalModelo.getNome()));

    }

    @Test
    public void testSetNome() {

        animalModelo.setNome("Teste");
        System.out.println("Testando classe AnimalModelo metodo setNome");
        assertEquals(true, animalModelo.getNome().equals("Teste"));

    }

    @Test
    public void testGetSexo() {

        System.out.println("Testando classe AnimalModelo metodo getSexo");
        assertEquals(true, isNull(animalModelo.getSexo()));

    }

    @Test
    public void testSetSexo() {

        animalModelo.setSexo("Macho");
        System.out.println("Testando classe AnimalModelo metodo setSexo");
        assertEquals(true, animalModelo.getSexo().equals("Macho"));

    }

    @Test
    public void testGetNascimento() {

        System.out.println("Testando classe AnimalModelo metodo getNascimento");
        assertEquals(true, isNull(animalModelo.getNascimento()));

    }

    @Test
    public void testSetNascimento() {

        Calendar dataNascimento = Calendar.getInstance();
        animalModelo.setNascimento(dataNascimento);
        System.out.println("Testando classe AnimalModelo metodo setNascimento");
        assertEquals(true, animalModelo.getNascimento().equals(dataNascimento));

    }

    @Test
    public void testGetPeso() {

        System.out.println("Testando classe AnimalModelo metodo getPeso");
        assertEquals(true, animalModelo.getPeso().equals(new BigDecimal("0")));

    }

    @Test
    public void testSetPeso() {

        animalModelo.setPeso(new BigDecimal("3"));
        System.out.println("Testando classe AnimalModelo metodo setPeso");
        assertEquals(true, animalModelo.getPeso().equals(new BigDecimal("3")));

    }

    @Test
    public void testGetEspecie() {

        System.out.println("Testando classe AnimalModelo metodo getEspecie");
        assertEquals(true, isNull(animalModelo.getEspecie()));

    }

    @Test
    public void testSetEspecie() {

        animalModelo.setEspecie("Felino");
        System.out.println("Testando classe AnimalModelo metodo setEspecie");
        assertEquals(true, animalModelo.getEspecie().equals("Felino"));

    }

    @Test
    public void testGetRaca() {

        System.out.println("Testando classe AnimalModelo metodo getRaca");
        assertEquals(true, isNull(animalModelo.getRaca()));

    }

    @Test
    public void testSetRaca() {

        animalModelo.setRaca("Persa");
        System.out.println("Testando classe AnimalModelo metodo setRaca");
        assertEquals(true, animalModelo.getRaca().equals("Persa"));

    }

    @Test
    public void testGetCaracteristica() {

        System.out.println("Testando classe AnimalModelo metodo getCaracteristica");
        assertEquals(true, isNull(animalModelo.getCaracteristica()));

    }

    @Test
    public void testSetCaracteristica() {

        animalModelo.setCaracteristica("Listrado");
        System.out.println("Testando classe AnimalModelo metodo setCaracteristica");
        assertEquals(true, animalModelo.getCaracteristica().equals("Listrado"));

    }

}
