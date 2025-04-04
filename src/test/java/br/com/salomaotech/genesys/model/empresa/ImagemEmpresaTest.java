package br.com.salomaotech.genesys.model.empresa;

import br.com.salomaotech.genesys.model.configuracoes.PastasEmpresa;
import br.com.salomaotech.genesys.view.JFempresa;
import br.com.salomaotech.sistema.algoritmos.RemovePastaDeArquivos;
import javax.swing.JPanel;
import org.junit.Test;
import static org.junit.Assert.*;

public class ImagemEmpresaTest {

    private final String idEmpresa = "1";

    public ImagemEmpresaTest() {

        RemovePastaDeArquivos.remover(new PastasEmpresa(idEmpresa).getSubPastaDeFotosDoPerfil());

    }

    @Test
    public void testUpload() {

        System.out.println("Testando ImagemEmpresa metodo: upload");
        assertEquals(true, ImagemEmpresa.upload(idEmpresa));

    }

    @Test
    public void testExibir() {

        ImagemEmpresa.upload(idEmpresa);
        System.out.println("Testando ImagemEmpresa metodo: exibir");
        assertEquals(true, ImagemEmpresa.exibir(idEmpresa, new JPanel()));

    }

    @Test
    public void testRemover() {

        System.out.println("Testando ImagemEmpresa metodo: remover");
        assertEquals(true, ImagemEmpresa.remover(idEmpresa, new JFempresa().jPdadosPerfilFoto));

    }

    @Test
    public void testGetImagemLogotipo() {

        if (ImagemEmpresa.upload(idEmpresa)) {

            System.out.println("Testando ImagemEmpresa metodo: getImagemLogotipo com imagem");
            assertEquals(true, ImagemEmpresa.getImagemLogotipo(idEmpresa) != null);

        } else {

            System.out.println("Testando ImagemEmpresa metodo: getImagemLogotipo sem imagem");
            assertEquals(true, ImagemEmpresa.getImagemLogotipo(idEmpresa) == null);

        }

    }

}
