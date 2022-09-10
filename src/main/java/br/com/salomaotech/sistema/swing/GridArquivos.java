package br.com.salomaotech.sistema.swing;

import br.com.salomaotech.sistema.algoritmos.ExecutaProgramaExterno;
import br.com.salomaotech.sistema.algoritmos.ValidaStringIsEmpty;
import br.com.salomaotech.sistema.patterns.Command;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Arrays;
import static java.util.Objects.isNull;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class GridArquivos {

    private final int largura;
    private final int altura;
    private final Command command;
    private final ImageIcon diretorioImageIcon;
    private final ImageIcon pdfImageIcon;
    private final ImageIcon indefinidoImageIcon;

    public GridArquivos(Command command) {

        this.largura = 128;
        this.altura = 128;
        this.command = command;
        this.diretorioImageIcon = construirImageIcon("/icones/arquivo128x.png");
        this.pdfImageIcon = construirImageIcon("/icones/arquivo_pdf128x.png");
        this.indefinidoImageIcon = construirImageIcon("/icones/arquivo_indefinido128x.png");

    }

    private ImageIcon construirImageIcon(String pathImagem) {

        return new ImageIcon(new ImageIcon(getClass().getResource(pathImagem)).getImage().getScaledInstance(this.largura, this.altura, Image.SCALE_DEFAULT));

    }

    /**
     * Exibe os arquivos na grid
     *
     * @param pathDestino Path de destino
     * @param jPanelPrincipal JPanel onde será carregado os arquivos
     * @param view JFrame da view
     * @param filtroNome Filtro pelo nome do arquivo
     * @return Retorna o número de arquivos exibidos
     */
    public int exibir(String pathDestino, JPanel jPanelPrincipal, JFrame view, String filtroNome) {

        /* reseta a view */
        jPanelPrincipal.removeAll();
        JPanel jPelementos = new JPanel();
        JScrollPane jScrollPane = new JScrollPane(jPelementos);
        jPanelPrincipal.add(jScrollPane);
        jScrollPane.setSize(jPanelPrincipal.getSize());

        /* grid */
        GridLayout gridLayout = new GridLayout(4, 4, 5, 10);
        jPelementos.setLayout(gridLayout);

        try {

            /* arquivos */
            File[] fileArquivosOrdenados = new File(pathDestino).listFiles();
            Arrays.sort(fileArquivosOrdenados);

            /* lista todos os arquivos ordenados */
            for (File fileArquivo : fileArquivosOrdenados) {

                boolean isPodeExibirArquivo = true;

                /* caso esteja usando filtro a exibição do arquivo depende do filtro */
                if (!ValidaStringIsEmpty.isEmpty(filtroNome)) {

                    isPodeExibirArquivo = fileArquivo.getName().toLowerCase().contains(filtroNome.toLowerCase());

                }

                /* valida se pode exibir o arquivo */
                if (isPodeExibirArquivo) {

                    /* icone do arquivo */
                    JLabel jLabelIconeDeArquivo = new JLabel(fileArquivo.getName());

                    /* valida se é um diretório ao inves de um arquivo */
                    if (fileArquivo.isDirectory()) {

                        jLabelIconeDeArquivo.setIcon(diretorioImageIcon);
                        jPelementos.add(jLabelIconeDeArquivo);

                    } else {

                        switch (fileArquivo.getName().substring(fileArquivo.getName().length() - 3, fileArquivo.getName().length())) {

                            case "png":
                                jLabelIconeDeArquivo.setIcon(new ImageIcon(new ImageIcon(fileArquivo.getAbsolutePath()).getImage().getScaledInstance(this.largura, this.altura, Image.SCALE_DEFAULT)));
                                jPelementos.add(jLabelIconeDeArquivo);
                                break;

                            case "jpg":
                                jLabelIconeDeArquivo.setIcon(new ImageIcon(new ImageIcon(fileArquivo.getAbsolutePath()).getImage().getScaledInstance(this.largura, this.altura, Image.SCALE_DEFAULT)));
                                jPelementos.add(jLabelIconeDeArquivo);
                                break;

                            case "peg":
                                jLabelIconeDeArquivo.setIcon(new ImageIcon(new ImageIcon(fileArquivo.getAbsolutePath()).getImage().getScaledInstance(this.largura, this.altura, Image.SCALE_DEFAULT)));
                                jPelementos.add(jLabelIconeDeArquivo);
                                break;

                            case "pdf":
                                jLabelIconeDeArquivo.setIcon(pdfImageIcon);
                                jPelementos.add(jLabelIconeDeArquivo);
                                break;

                            default:
                                jLabelIconeDeArquivo.setIcon(indefinidoImageIcon);
                                jPelementos.add(jLabelIconeDeArquivo);

                        }

                    }

                    /* adiciona evento de duplo clique do mouse no icone do arquivo */
                    jLabelIconeDeArquivo.addMouseListener(new MouseAdapter() {

                        @Override
                        public void mouseClicked(MouseEvent e) {

                            if (e.getClickCount() == 2 && !e.isConsumed()) {

                                /* se for uma pasta então carregar o grid novamente */
                                if (fileArquivo.isDirectory()) {

                                    GridArquivos grid = new GridArquivos(command);
                                    grid.exibir(fileArquivo.getAbsolutePath(), jPanelPrincipal, view, filtroNome);

                                } else {

                                    ExecutaProgramaExterno.executarModoDesktop(fileArquivo.getAbsolutePath());

                                }

                            }

                        }

                    });

                }

            }

            /* repinta a view */
            view.repaint();

            /* executa o command */
            if (!isNull(command)) {

                command.executar(pathDestino);

            }

            /* retorna a quantidade de arquivos listados */
            return fileArquivosOrdenados.length;

        } catch (Exception ex) {

            return 0;

        }

    }

}
