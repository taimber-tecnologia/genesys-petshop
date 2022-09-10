package br.com.salomaotech.genesys.model.configuracoes;

import br.com.salomaotech.genesys.model.configuracoes.PastasSistema;
import org.junit.Test;
import static org.junit.Assert.*;

public class PastasSistemaTest {

    private final String pathLocal = System.getProperty("user.dir");

    @Test
    public void testGetPastaRaiz() {

        System.out.println("Testando pastas de sistema método: getPastaRaiz");
        assertEquals(true, new PastasSistema().getPastaRaiz().equals(pathLocal + "/target/arquivos/"));

    }

    @Test
    public void testGetSubPastaImpressao() {

        System.out.println("Testando pastas de sistema método: getSubPastaImpressao");
        assertEquals(true, new PastasSistema().getSubPastaImpressao().equals(pathLocal + "/target/arquivos/impressao_temp/"));

    }

    @Test
    public void testGetSubPastaImagemLogotipoEmpresa() {

        System.out.println("Testando pastas de sistema método: getSubPastaImagemLogotipoEmpresa");
        assertEquals(true, new PastasSistema().getSubPastaImagemLogotipoEmpresa().equals(pathLocal + "/target/arquivos/logotipo_empresa/"));

    }

    @Test
    public void testGetSubPastaImagemLogotipoEmpresaTemporaria() {

        System.out.println("Testando pastas de sistema método: getSubPastaImagemLogotipoEmpresaTemporaria");
        assertEquals(true, new PastasSistema().getSubPastaImagemLogotipoEmpresaTemporaria().equals(pathLocal + "/target/arquivos/logotipo_empresa_temp/"));

    }

    @Test
    public void testGetSubPastaEnderecoArquivoDeDocumentacao() {

        System.out.println("Testando pastas de sistema método: getSubPastaEnderecoArquivoDeDocumentacao");
        assertEquals(true, new PastasSistema().getSubPastaEnderecoArquivoDeDocumentacao().equals(pathLocal + "/target/arquivos/documentacao/ajuda.pdf"));

    }

}
