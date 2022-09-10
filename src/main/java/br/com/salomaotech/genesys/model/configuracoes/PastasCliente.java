package br.com.salomaotech.genesys.model.configuracoes;

public class PastasCliente {

    private final String pastaRaiz;
    private final String subPastaDeFotosDoPerfil;
    private final String subPastaDeTemporarios;
    private final String subPastaDeAnexosVenda;
    private final String subPastaDeAnexosDebito;
    private final String subPastaDeAnexosAnamnese;

    /**
     * Construtor
     *
     * @param idCliente ID do cliente
     */
    public PastasCliente(String idCliente) {

        /* pasta raiz */
        pastaRaiz = new PastasSistema().getPastaRaiz() + "cliente/" + idCliente + "/";

        /* subpastas */
        subPastaDeFotosDoPerfil = pastaRaiz + "foto_perfil/";
        subPastaDeTemporarios = pastaRaiz + "temporario/";
        subPastaDeAnexosVenda = pastaRaiz + "venda/";
        subPastaDeAnexosDebito = pastaRaiz + "debito/";
        subPastaDeAnexosAnamnese = pastaRaiz + "anamnese/";

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

    public String getSubPastaDeAnexosVenda(String id) {
        return subPastaDeAnexosVenda + id;
    }

    public String getSubPastaDeAnexosDebito(String id) {
        return subPastaDeAnexosDebito + id;
    }

    public String getSubPastaDeAnexosAnamnese(String id) {
        return subPastaDeAnexosAnamnese + id;
    }

}
