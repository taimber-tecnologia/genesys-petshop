package br.com.salomaotech.genesys.model.configuracoes;

import br.com.salomaotech.sistema.algoritmos.ArquivoPropriedade;
import br.com.salomaotech.sistema.algoritmos.CaminhoArquivoNormalizado;
import br.com.salomaotech.sistema.jpa.ConfiguracoesConexao;
import static java.util.Objects.isNull;
import java.util.Properties;

public class PastasSistema {

    private String pastaRaiz = "";
    private final String subPastaImpressao;

    public PastasSistema() {

        ConfiguracoesConexao configuracoesConexao = new ConfiguracoesConexao();

        if (!isNull(configuracoesConexao.getPropriedades())) {

            Properties propriedades = new ArquivoPropriedade(configuracoesConexao.getPathArquivo()).getProperties();
            pastaRaiz = propriedades.getProperty("pasta_raiz");

        } else {

            pastaRaiz = System.getProperty("user.dir");

        }

        /* pasta raiz */
        pastaRaiz = CaminhoArquivoNormalizado.obterCaminhoArquivoNormalizado(pastaRaiz + "/target/arquivos");

        /* subpastas */
        subPastaImpressao = CaminhoArquivoNormalizado.obterCaminhoArquivoNormalizado(pastaRaiz + "/impressao_temp/");

    }

    public String getPastaRaiz() {
        return pastaRaiz;
    }

    public String getSubPastaImpressao() {
        return subPastaImpressao;
    }

}
