package br.com.salomaotech.genesys.model.produto;

import br.com.salomaotech.genesys.model.configuracoes.PastasProduto;
import br.com.salomaotech.sistema.algoritmos.CopiaArquivosComDialogo;
import br.com.salomaotech.sistema.algoritmos.CopiarArquivosSemDialogo;
import br.com.salomaotech.sistema.algoritmos.ImagemExibe;
import br.com.salomaotech.sistema.algoritmos.ImagemRedimensiona;
import br.com.salomaotech.sistema.algoritmos.RemovePastaDeArquivos;
import java.io.File;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImagemProduto {

    public static boolean upload(String idProduto) {

        String pathDestino = new PastasProduto(idProduto).getSubPastaDeFotosDoPerfil();
        String pathTemporario = new PastasProduto(idProduto).getSubPastaDeTemporarios();

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
            ImagemRedimensiona imagemRedimensiona = new ImagemRedimensiona(arquivo.getAbsolutePath(), arquivo.getAbsolutePath(), 230);
            imagemRedimensiona.redimensionar();
            return arquivo.exists();

        } else {

            /* restaura os arquivos originais em caso de falha */
            CopiarArquivosSemDialogo.copiar(pathTemporario, pathDestino);
            RemovePastaDeArquivos.remover(pathTemporario);
            return false;

        }

    }

    public boolean exibir(String idProduto, JPanel painel) {

        try {

            String pathDestino = new PastasProduto(idProduto).getSubPastaDeFotosDoPerfil();
            ImagemExibe imagemExibe = new ImagemExibe(painel, painel.getWidth() - 12, painel.getHeight() - 12);
            return imagemExibe.exibir(new File(pathDestino).listFiles()[0].getAbsolutePath());

        } catch (Exception ex) {

            painel.removeAll();
            painel.repaint();
            return false;

        }

    }

    public static boolean remover(String idProduto, JPanel jPanel) {

        /* atualiza a view */
        jPanel.removeAll();
        jPanel.repaint();

        /* remove da view e do disco */
        return RemovePastaDeArquivos.remover(new PastasProduto(idProduto).getSubPastaDeFotosDoPerfil());

    }

}
