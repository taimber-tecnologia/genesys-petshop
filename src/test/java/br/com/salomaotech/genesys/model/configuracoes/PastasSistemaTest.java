package br.com.salomaotech.genesys.model.configuracoes;

import br.com.salomaotech.sistema.algoritmos.ArquivoPropriedade;
import br.com.salomaotech.sistema.algoritmos.CaminhoArquivoNormalizado;
import br.com.salomaotech.sistema.jpa.ConfiguracoesConexao;
import static java.util.Objects.isNull;
import java.util.Properties;
import org.junit.Test;
import static org.junit.Assert.*;

public class PastasSistemaTest {

    private String pastaRaiz;

    public PastasSistemaTest() {

        ConfiguracoesConexao configuracoesConexao = new ConfiguracoesConexao();

        if (!isNull(configuracoesConexao.getPropriedades())) {

            Properties propriedades = new ArquivoPropriedade(configuracoesConexao.getPathArquivo()).getProperties();
            pastaRaiz = "//" + propriedades.get("servidor");

        } else {

            pastaRaiz = System.getProperty("user.dir");

        }

        pastaRaiz = CaminhoArquivoNormalizado.obterCaminhoArquivoNormalizado(pastaRaiz + "/target/arquivos");

    }

    @Test
    public void testGetPastaRaiz() {

        System.out.println("Testando pastas de sistema método: getPastaRaiz");
        assertEquals(true, new PastasSistema().getPastaRaiz().equals(CaminhoArquivoNormalizado.obterCaminhoArquivoNormalizado(pastaRaiz)));

    }

    @Test
    public void testGetSubPastaImpressao() {

        System.out.println("Testando pastas de sistema método: getSubPastaImpressao");
        assertEquals(true, new PastasSistema().getSubPastaImpressao().equals(CaminhoArquivoNormalizado.obterCaminhoArquivoNormalizado(pastaRaiz + "/impressao_temp/")));

    }

}
