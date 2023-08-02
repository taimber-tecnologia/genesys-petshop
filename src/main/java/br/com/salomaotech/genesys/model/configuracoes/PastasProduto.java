package br.com.salomaotech.genesys.model.configuracoes;

import br.com.salomaotech.sistema.algoritmos.CaminhoArquivoNormalizado;

public class PastasProduto {

    private final String pastaRaiz;
    private final String subPastaDeFotosDoPerfil;
    private final String subPastaDeTemporarios;

    public PastasProduto(String idProduto) {

        pastaRaiz = CaminhoArquivoNormalizado.obterCaminhoArquivoNormalizado(new PastasSistema().getPastaRaiz() + "/produto/" + idProduto);
        subPastaDeFotosDoPerfil = CaminhoArquivoNormalizado.obterCaminhoArquivoNormalizado(pastaRaiz + "/foto_produto");
        subPastaDeTemporarios = CaminhoArquivoNormalizado.obterCaminhoArquivoNormalizado(pastaRaiz + "/temporario");

    }

    public String getPastaRaiz() {
        return pastaRaiz;
    }

    public String getSubPastaDeFotosDoPerfil() {
        return subPastaDeFotosDoPerfil;
    }

    public String getSubPastaDeTemporarios() {
        return subPastaDeTemporarios;
    }

}
