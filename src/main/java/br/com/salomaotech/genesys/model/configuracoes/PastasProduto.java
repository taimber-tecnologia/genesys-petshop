package br.com.salomaotech.genesys.model.configuracoes;

public class PastasProduto {

    private final String pastaRaiz;
    private final String subPastaDeFotosDoPerfil;
    private final String subPastaDeTemporarios;

    /**
     * Construtor
     *
     * @param idProduto ID do produto
     */
    public PastasProduto(String idProduto) {

        /* pasta raiz */
        pastaRaiz = new PastasSistema().getPastaRaiz() + "produto/" + idProduto + "/";

        /* subpastas */
        subPastaDeFotosDoPerfil = pastaRaiz + "foto_produto/";
        subPastaDeTemporarios = pastaRaiz + "temporario/";

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
