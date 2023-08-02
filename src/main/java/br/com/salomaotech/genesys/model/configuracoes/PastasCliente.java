package br.com.salomaotech.genesys.model.configuracoes;

import br.com.salomaotech.sistema.algoritmos.CaminhoArquivoNormalizado;

public class PastasCliente {

    private final String pastaRaiz;
    private final String subPastaDeFotosDoPerfil;
    private final String subPastaDeTemporarios;

    public PastasCliente(String idCliente) {

        pastaRaiz = CaminhoArquivoNormalizado.obterCaminhoArquivoNormalizado(new PastasSistema().getPastaRaiz() + "/cliente/" + idCliente);
        subPastaDeFotosDoPerfil = CaminhoArquivoNormalizado.obterCaminhoArquivoNormalizado(pastaRaiz + "/foto_perfil");
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
