package br.com.salomaotech.genesys.model.configuracoes;

public class PastasSistema {

    private final String pastaRaiz;
    private final String subPastaImpressao;
    private final String subPastaImagemLogotipoEmpresa;
    private final String subPastaImagemLogotipoEmpresaTemporaria;
    private final String subPastaEnderecoArquivoDeDocumentacao;

    public PastasSistema() {

        pastaRaiz = System.getProperty("user.dir") + "/target/arquivos/";
        subPastaImpressao = pastaRaiz + "impressao_temp/";
        subPastaImagemLogotipoEmpresa = pastaRaiz + "logotipo_empresa/";
        subPastaImagemLogotipoEmpresaTemporaria = pastaRaiz + "logotipo_empresa_temp/";
        subPastaEnderecoArquivoDeDocumentacao = pastaRaiz + "documentacao/ajuda.pdf";

    }

    public String getPastaRaiz() {
        return pastaRaiz;
    }

    public String getSubPastaImpressao() {
        return subPastaImpressao;
    }

    public String getSubPastaImagemLogotipoEmpresa() {
        return subPastaImagemLogotipoEmpresa;
    }

    public String getSubPastaImagemLogotipoEmpresaTemporaria() {
        return subPastaImagemLogotipoEmpresaTemporaria;
    }

    public String getSubPastaEnderecoArquivoDeDocumentacao() {
        return subPastaEnderecoArquivoDeDocumentacao;
    }

}
