/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.salomaotech.genesys.view;

/**
 *
 * @author usuario
 */
public class JFvendaInicia extends javax.swing.JFrame {

    private long id;

    /**
     * Creates new form JFvendaVenda
     */
    public JFvendaInicia() {
        initComponents();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jTvendaValorTotal = new javax.swing.JTextField();
        jBvendaFinaliza = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        jBatalhoCadastro = new javax.swing.JButton();
        jBatalhoPesquisa = new javax.swing.JButton();
        jBimprimir = new javax.swing.JButton();
        jBvendaExcluir = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPdadosPerfilFoto = new javax.swing.JPanel();
        jTitemTotal = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTitemQuantidade = new javax.swing.JTextField();
        jBcalcularGranel = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTitensSelecionados = new javax.swing.JTable();
        jBremoveItemSelecionadoLista = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTlistaDeProdutos = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jTpesquisaNomeProduto = new javax.swing.JTextField();
        jBprodutoAdicionaItem = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jTpesquisaNomeServico = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTlistaDeServicos = new javax.swing.JTable();
        jBservicoAdicionaItem = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Vendas - Genesys PetShop");
        setMinimumSize(new java.awt.Dimension(1024, 694));

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel10.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel10.setText("TOTAL:");

        jTvendaValorTotal.setEditable(false);
        jTvendaValorTotal.setBackground(new java.awt.Color(0, 0, 0));
        jTvendaValorTotal.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        jTvendaValorTotal.setForeground(new java.awt.Color(0, 204, 153));
        jTvendaValorTotal.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTvendaValorTotal.setBorder(null);

        jBvendaFinaliza.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jBvendaFinaliza.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/concluir32x.png"))); // NOI18N
        jBvendaFinaliza.setText("FINALIZAR");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTvendaValorTotal)
                .addGap(18, 18, 18)
                .addComponent(jBvendaFinaliza)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jBvendaFinaliza, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTvendaValorTotal, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        jBatalhoCadastro.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jBatalhoCadastro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/adicionar32x.png"))); // NOI18N
        jBatalhoCadastro.setText("Nova venda");
        jBatalhoCadastro.setFocusable(false);
        jBatalhoCadastro.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jBatalhoCadastro.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jBatalhoCadastro);

        jBatalhoPesquisa.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jBatalhoPesquisa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/pesquisa32x.png"))); // NOI18N
        jBatalhoPesquisa.setText("Pesquisar");
        jBatalhoPesquisa.setFocusable(false);
        jBatalhoPesquisa.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jBatalhoPesquisa.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jBatalhoPesquisa);

        jBimprimir.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jBimprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/impressora32x.png"))); // NOI18N
        jBimprimir.setText("Imprimir");
        jBimprimir.setEnabled(false);
        jBimprimir.setFocusable(false);
        jBimprimir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jBimprimir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jBimprimir);

        jBvendaExcluir.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jBvendaExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/excluir32x.png"))); // NOI18N
        jBvendaExcluir.setText("Excluir");
        jBvendaExcluir.setEnabled(false);
        jBvendaExcluir.setFocusable(false);
        jBvendaExcluir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jBvendaExcluir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jBvendaExcluir);

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPdadosPerfilFoto.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPdadosPerfilFoto.setPreferredSize(new java.awt.Dimension(250, 250));

        javax.swing.GroupLayout jPdadosPerfilFotoLayout = new javax.swing.GroupLayout(jPdadosPerfilFoto);
        jPdadosPerfilFoto.setLayout(jPdadosPerfilFotoLayout);
        jPdadosPerfilFotoLayout.setHorizontalGroup(
            jPdadosPerfilFotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 255, Short.MAX_VALUE)
        );
        jPdadosPerfilFotoLayout.setVerticalGroup(
            jPdadosPerfilFotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 276, Short.MAX_VALUE)
        );

        jTitemTotal.setEditable(false);
        jTitemTotal.setBackground(new java.awt.Color(0, 0, 0));
        jTitemTotal.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jTitemTotal.setForeground(new java.awt.Color(0, 204, 153));
        jTitemTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTitemTotal.setBorder(null);

        jLabel4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("TOTAL:");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("QUANT:");

        jTitemQuantidade.setBackground(new java.awt.Color(0, 51, 102));
        jTitemQuantidade.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jTitemQuantidade.setForeground(new java.awt.Color(255, 255, 255));
        jTitemQuantidade.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTitemQuantidade.setBorder(null);
        jTitemQuantidade.setCaretColor(new java.awt.Color(255, 255, 255));

        jBcalcularGranel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jBcalcularGranel.setText("GRANEL");
        jBcalcularGranel.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPdadosPerfilFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTitemTotal, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTitemQuantidade))
                        .addGap(18, 18, 18)
                        .addComponent(jBcalcularGranel)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPdadosPerfilFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTitemQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBcalcularGranel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTitemTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTitensSelecionados.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTitensSelecionados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Quantidade", "Item", "Preço"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTitensSelecionados);
        if (jTitensSelecionados.getColumnModel().getColumnCount() > 0) {
            jTitensSelecionados.getColumnModel().getColumn(0).setMinWidth(85);
            jTitensSelecionados.getColumnModel().getColumn(0).setPreferredWidth(85);
            jTitensSelecionados.getColumnModel().getColumn(0).setMaxWidth(85);
            jTitensSelecionados.getColumnModel().getColumn(1).setMinWidth(380);
            jTitensSelecionados.getColumnModel().getColumn(1).setPreferredWidth(380);
            jTitensSelecionados.getColumnModel().getColumn(1).setMaxWidth(380);
            jTitensSelecionados.getColumnModel().getColumn(2).setMinWidth(100);
            jTitensSelecionados.getColumnModel().getColumn(2).setPreferredWidth(100);
            jTitensSelecionados.getColumnModel().getColumn(2).setMaxWidth(100);
        }

        jBremoveItemSelecionadoLista.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jBremoveItemSelecionadoLista.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/remove-item32x.png"))); // NOI18N
        jBremoveItemSelecionadoLista.setText("REMOVER ITEM");
        jBremoveItemSelecionadoLista.setEnabled(false);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jBremoveItemSelecionadoLista)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jBremoveItemSelecionadoLista)
                .addContainerGap())
        );

        jTabbedPane1.addTab("LISTA DE ITENS ADICIONADOS", jPanel9);

        jTlistaDeProdutos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTlistaDeProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome", "Preço"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTlistaDeProdutos);
        if (jTlistaDeProdutos.getColumnModel().getColumnCount() > 0) {
            jTlistaDeProdutos.getColumnModel().getColumn(0).setMinWidth(85);
            jTlistaDeProdutos.getColumnModel().getColumn(0).setPreferredWidth(85);
            jTlistaDeProdutos.getColumnModel().getColumn(0).setMaxWidth(85);
            jTlistaDeProdutos.getColumnModel().getColumn(1).setMinWidth(380);
            jTlistaDeProdutos.getColumnModel().getColumn(1).setPreferredWidth(380);
            jTlistaDeProdutos.getColumnModel().getColumn(1).setMaxWidth(380);
            jTlistaDeProdutos.getColumnModel().getColumn(2).setMinWidth(100);
            jTlistaDeProdutos.getColumnModel().getColumn(2).setPreferredWidth(100);
            jTlistaDeProdutos.getColumnModel().getColumn(2).setMaxWidth(100);
        }

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setText("Nome:");

        jTpesquisaNomeProduto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTpesquisaNomeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTpesquisaNomeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jBprodutoAdicionaItem.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jBprodutoAdicionaItem.setText("ADICIONA PRODUTO");
        jBprodutoAdicionaItem.setEnabled(false);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jBprodutoAdicionaItem)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jBprodutoAdicionaItem, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("PRODUTOS", jPanel8);

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setText("Nome:");

        jTpesquisaNomeServico.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTpesquisaNomeServico, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTpesquisaNomeServico, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jTlistaDeServicos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTlistaDeServicos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome", "Preço"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTlistaDeServicos);
        if (jTlistaDeServicos.getColumnModel().getColumnCount() > 0) {
            jTlistaDeServicos.getColumnModel().getColumn(0).setMinWidth(85);
            jTlistaDeServicos.getColumnModel().getColumn(0).setPreferredWidth(85);
            jTlistaDeServicos.getColumnModel().getColumn(0).setMaxWidth(85);
            jTlistaDeServicos.getColumnModel().getColumn(1).setMinWidth(380);
            jTlistaDeServicos.getColumnModel().getColumn(1).setPreferredWidth(380);
            jTlistaDeServicos.getColumnModel().getColumn(1).setMaxWidth(380);
            jTlistaDeServicos.getColumnModel().getColumn(2).setMinWidth(100);
            jTlistaDeServicos.getColumnModel().getColumn(2).setPreferredWidth(100);
            jTlistaDeServicos.getColumnModel().getColumn(2).setMaxWidth(100);
        }

        jBservicoAdicionaItem.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jBservicoAdicionaItem.setText("ADICIONA SERVIÇO");
        jBservicoAdicionaItem.setEnabled(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jBservicoAdicionaItem)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jBservicoAdicionaItem, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("SERVIÇOS", jPanel5);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFvendaInicia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFvendaInicia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFvendaInicia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFvendaInicia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFvendaInicia().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton jBatalhoCadastro;
    public javax.swing.JButton jBatalhoPesquisa;
    public javax.swing.JButton jBcalcularGranel;
    public javax.swing.JButton jBimprimir;
    public javax.swing.JButton jBprodutoAdicionaItem;
    public javax.swing.JButton jBremoveItemSelecionadoLista;
    public javax.swing.JButton jBservicoAdicionaItem;
    public javax.swing.JButton jBvendaExcluir;
    public javax.swing.JButton jBvendaFinaliza;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    public javax.swing.JPanel jPdadosPerfilFoto;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    public javax.swing.JTabbedPane jTabbedPane1;
    public javax.swing.JTextField jTitemQuantidade;
    public javax.swing.JTextField jTitemTotal;
    public javax.swing.JTable jTitensSelecionados;
    public javax.swing.JTable jTlistaDeProdutos;
    public javax.swing.JTable jTlistaDeServicos;
    private javax.swing.JToolBar jToolBar1;
    public javax.swing.JTextField jTpesquisaNomeProduto;
    public javax.swing.JTextField jTpesquisaNomeServico;
    public javax.swing.JTextField jTvendaValorTotal;
    // End of variables declaration//GEN-END:variables
}
