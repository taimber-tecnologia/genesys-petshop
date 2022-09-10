package br.com.salomaotech.genesys.model.empresa;

import br.com.salomaotech.genesys.model.configuracoes.PastasSistema;
import br.com.salomaotech.sistema.algoritmos.ImagemExibe;
import br.com.salomaotech.sistema.algoritmos.ImagemRedimensiona;
import br.com.salomaotech.sistema.algoritmos.CopiaArquivosComDialogo;
import br.com.salomaotech.sistema.algoritmos.CopiarArquivosSemDialogo;
import br.com.salomaotech.sistema.algoritmos.RemovePastaDeArquivos;
import java.io.File;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImagemEmpresa {

    /**
     * Faz o upload para a pasta da imagem de profile
     *
     * @return true para conseguiu fazer o upload
     */
    public static boolean upload() {

        String pathDestino = new PastasSistema().getSubPastaImagemLogotipoEmpresa();
        String pathTemporario = new PastasSistema().getSubPastaImagemLogotipoEmpresaTemporaria();

        /* faz o backup dos arquivos antigos e remove a pasta original */
        CopiarArquivosSemDialogo.copiar(pathDestino, pathTemporario);
        RemovePastaDeArquivos.remover(pathDestino);

        /* copiador de arquivos com diálogo */
        CopiaArquivosComDialogo copiaArquivosComDialogo = new CopiaArquivosComDialogo(pathDestino, false);
        copiaArquivosComDialogo.setFiltro(new FileNameExtensionFilter("Imagens", "jpg", "jpeg", "png"));

        /* copia os arquivos para a pasta de destino */
        if (copiaArquivosComDialogo.copiar()) {

            /* remove o backup antigo */
            RemovePastaDeArquivos.remover(pathTemporario);

            /* redimensiona a imagem copiada */
            File arquivo = new File(copiaArquivosComDialogo.getListaDeArquivosCopiados().get(0));
            ImagemRedimensiona imagemRedimensiona = new ImagemRedimensiona(arquivo.getAbsolutePath(), arquivo.getAbsolutePath(), 192);
            imagemRedimensiona.redimensionar();
            return arquivo.exists();

        } else {

            /* restaura os arquivos originais em caso de falha */
            CopiarArquivosSemDialogo.copiar(pathTemporario, pathDestino);
            RemovePastaDeArquivos.remover(pathTemporario);
            return false;

        }

    }

    /**
     * Exibe a imagem de perfil
     *
     * @param jPanel JPanel onde será exibida a imagem
     * @return true sucesso
     */
    public boolean exibir(JPanel jPanel) {

        String pathDestino = new PastasSistema().getSubPastaImagemLogotipoEmpresa();
        File arquivos = new File(pathDestino);
        ImagemExibe imagem = new ImagemExibe(jPanel, jPanel.getWidth() - 12, jPanel.getHeight() - 12);

        try {

            return imagem.exibir(arquivos.listFiles()[0].getAbsolutePath());

        } catch (Exception ex) {

            return false;

        }

    }

    /**
     * Remove a imagem do perfil
     *
     * @param jPanel
     * @return true sucesso
     */
    public static boolean remover(JPanel jPanel) {

        /* remove da view e do disco */
        jPanel.removeAll();
        jPanel.repaint();
        return RemovePastaDeArquivos.remover(new PastasSistema().getSubPastaImagemLogotipoEmpresa());

    }

    /**
     * Retorna o path do logotipo
     *
     * @return
     */
    public static String getPathLogotipo() {

        String pathDestino = new PastasSistema().getSubPastaImagemLogotipoEmpresa();

        try {

            return new File(pathDestino).listFiles()[0].getAbsolutePath();

        } catch (Exception ex) {

            return null;

        }

    }

}
