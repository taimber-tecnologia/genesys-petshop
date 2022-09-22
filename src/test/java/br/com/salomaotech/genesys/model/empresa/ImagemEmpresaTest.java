package br.com.salomaotech.genesys.model.empresa;

import static java.util.Objects.isNull;
import javax.swing.JPanel;
import org.junit.Test;
import static org.junit.Assert.*;

public class ImagemEmpresaTest {

    @Test
    public void testUpload() {

        System.out.println("Testando ImagemEmpresa metodo: upload");
        assertEquals(true, ImagemEmpresa.upload());

    }

    @Test
    public void testExibir() {

        ImagemEmpresa.upload();
        System.out.println("Testando ImagemEmpresa metodo: exibir");
        assertEquals(true, new ImagemEmpresa().exibir(new JPanel()));

    }

    @Test
    public void testRemover() {

        System.out.println("Testando ImagemEmpresa metodo: remover");
        assertEquals(true, ImagemEmpresa.remover(new JPanel()));

    }

    @Test
    public void testGetPathLogotipo() {

        ImagemEmpresa.upload();
        System.out.println("Testando ImagemEmpresa metodo: getPathLogotipo");
        assertEquals(false, isNull(ImagemEmpresa.getPathLogotipo()));

    }

}
