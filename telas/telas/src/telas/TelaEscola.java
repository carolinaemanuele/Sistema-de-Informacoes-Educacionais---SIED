package telas;
import java.awt.event.KeyEvent;
import dal.ModuloConexao;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;



public class TelaEscola extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public TelaEscola() {
        initComponents();
        conexao = ModuloConexao.connector();
    }
    
        private void pesquisar_escola(){
     
        String sql = "select id_escola as Id,nome_escola as Nome,codigo_inep as CodigoInep,rua_escola as Rua,numero_escola as Numero,bairro_escola as Bairro,cidade_escola as Cidade,cep_escola as CEP, estado_escola as Estado from tb_escola where nome_escola  like ?";
        
     try{
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtPesquisar.getText() + "%");
            rs = pst.executeQuery();
            tblEscola.setModel(DbUtils.resultSetToTableModel(rs));
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
        
    private void setar_campos() {
        int setar = tblEscola.getSelectedRow();
        txtId.setText(tblEscola.getModel().getValueAt(setar, 0).toString());
        txtNome.setText(tblEscola.getModel().getValueAt(setar, 1).toString());
        txtCodigo.setText(tblEscola.getModel().getValueAt(setar, 2).toString());
        txtRua.setText(tblEscola.getModel().getValueAt(setar, 3).toString());
        txtNumero.setText(tblEscola.getModel().getValueAt(setar, 4).toString());
        txtBairro.setText(tblEscola.getModel().getValueAt(setar, 5).toString());
        txtCidade.setText(tblEscola.getModel().getValueAt(setar, 6).toString());
        txtCep.setText(tblEscola.getModel().getValueAt(setar, 7).toString());
        txtEstado.setText(tblEscola.getModel().getValueAt(setar, 8).toString());
        btAdicionar.setEnabled(false);

    }
    
   
        private void adicionar(){
        
        String sql = "insert into tb_escola(nome_escola,codigo_inep,rua_escola,numero_escola,bairro_escola,cidade_escola,cep_escola,estado_escola) values (?,?,?,?,?,?,?,?)";
        
        try{
            
            pst = conexao.prepareStatement(sql); 
            //pst.setString(1, txtId.getText());
            pst.setString(1, txtNome.getText());
            pst.setString(2, txtCodigo.getText());
            pst.setString(3, txtRua.getText());
            pst.setString(4, txtNumero.getText());
            pst.setString(5, txtBairro.getText());
            pst.setString(6, txtCidade.getText());
            pst.setString(7, txtCep.getText());
            pst.setString(8, txtEstado.getText());
            
           if((txtNome.getText().isEmpty()) || (txtCodigo.getText().isEmpty()) || (txtRua.getText().isEmpty())|| (txtNumero.getText().isEmpty())|| (txtBairro.getText().isEmpty())|| (txtCidade.getText().isEmpty())|| (txtCep.getText().isEmpty())|| (txtEstado.getText().isEmpty())) {
               JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
           
           if((txtNome.getText().isEmpty())){
               txtNome.setBorder(new LineBorder(Color.RED, 1));
               txtNome.requestFocus();
           }
           if ((txtCodigo.getText().isEmpty())){
               txtCodigo.setBorder(new LineBorder(Color.RED, 1));
               txtCodigo.requestFocus();
           }
           if ((txtRua.getText().isEmpty())){
               txtRua.setBorder(new LineBorder(Color.RED, 1));
               txtRua.requestFocus();
           }
           if ((txtNumero.getText().isEmpty())){
               txtNumero.setBorder(new LineBorder(Color.RED, 1));
               txtNumero.requestFocus();
           }
           if ((txtBairro.getText().isEmpty())){
               txtBairro.setBorder(new LineBorder(Color.RED, 1));
               txtBairro.requestFocus();
           }
           if ((txtCidade.getText().isEmpty())){
               txtCidade.setBorder(new LineBorder(Color.RED, 1));
               txtCidade.requestFocus();
           }
           if ((txtCep.getText().isEmpty())){
               txtCep.setBorder(new LineBorder(Color.RED, 1));
               txtCep.requestFocus();
           }
           if ((txtEstado.getText().isEmpty())){
               txtEstado.setBorder(new LineBorder(Color.RED, 1));
               txtEstado.requestFocus();
           }
           
           }else{
               int adicionado = pst.executeUpdate();
               if(adicionado > 0){
                   
                JOptionPane.showMessageDialog(null, "Escola adicionada com sucesso!");   
                   //txtId.setText(null);
                   txtNome.setText(null);
                   txtCodigo.setText(null);
                   txtRua.setText(null);
                   txtNumero.setText(null);
                   txtBairro.setText(null);
                   txtCidade.setText(null);
                   txtCep.setText(null);
                   txtEstado.setText(null);
               }
           }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
         private void alterar() {
        
         String sql = "update tb_escola set nome_escola =?,codigo_inep =?,rua_escola = ?,numero_escola = ?,bairro_escola =?,cidade_escola =?,cep_escola =?,estado_escola =? where id_escola = ?";

       try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNome.getText());
            pst.setString(2, txtCodigo.getText());
            pst.setString(3, txtRua.getText());
            pst.setString(4, txtNumero.getText());
            pst.setString(5, txtBairro.getText());
            pst.setString(6, txtCidade.getText());
            pst.setString(7, txtCep.getText());
            pst.setString(8, txtEstado.getText());
            pst.setString(9, txtId.getText());

            if ((txtId.getText().isEmpty()) || (txtNome.getText().isEmpty()) || (txtRua.getText().isEmpty()) || (txtNumero.getText().isEmpty())|| (txtBairro.getText().isEmpty())|| (txtCidade.getText().isEmpty())|| (txtCep.getText().isEmpty())|| (txtEstado.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
            } else {
                int adicionado = pst.executeUpdate();

                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados do usuário atualizados!");
                    txtNome.setText(null);
                    txtCodigo.setText(null);
                    txtRua.setText(null);
                    txtNumero.setText(null);
                    txtBairro.setText(null);
                    txtCidade.setText(null);
                    txtCep.setText(null);
                    txtEstado.setText(null);
                    txtId.setText(null);
                }
            }

        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

         
    private void excluir_escola(){
    
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir esta Escola?", "Atenção", JOptionPane. YES_NO_OPTION);
    if(confirma == JOptionPane. YES_OPTION){
    String sql = "delete from tb_escola where id_escola=?";
    
    try{
    pst=conexao.prepareStatement(sql);
    pst.setString(1, txtId.getText());
    
    int apagado=pst.executeUpdate();
    if(apagado > 0){
    JOptionPane.showMessageDialog(null, "Escola excluida com sucesso!");
    
    txtId.setText(null);
    txtNome.setText(null);
    txtCodigo.setText(null);
    txtRua.setText(null);
    txtNumero.setText(null);
    txtBairro.setText(null);
    txtCidade.setText(null);
    txtCep.setText(null);
    txtEstado.setText(null);
    btAdicionar.setEnabled(true);
    btPesquisar.setEnabled(true);
    tblEscola.setVisible(true);
    }


    }catch(Exception e){
    JOptionPane.showMessageDialog(null, e);
    }
    }
}
    
    private void limpar(){
        
        txtId.setText(null);
        txtNome.setText(null);
        txtCodigo.setText(null);
        txtRua.setText(null);
        txtNumero.setText(null);
        txtBairro.setText(null);
        txtCidade.setText(null);
        txtCep.setText(null);
        txtEstado.setText(null);
        tblEscola.setVisible(true);
        ((DefaultTableModel) tblEscola.getModel()).setRowCount(0);
        btAdicionar.setEnabled(true);
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        txtCodigo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtRua = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtBairro = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtCidade = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtEstado = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEscola = new javax.swing.JTable();
        btAdicionar = new javax.swing.JLabel();
        btEditar = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btLimpar2 = new javax.swing.JLabel();
        btLimpar = new javax.swing.JLabel();
        txtCep = new javax.swing.JFormattedTextField();
        txtNumero = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        btPesquisar = new javax.swing.JButton();
        txtPesquisar = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Escola");
        setToolTipText("");
        setPreferredSize(new java.awt.Dimension(880, 700));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Nome");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 60, 50));
        getContentPane().add(txtNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 290, 680, 25));
        getContentPane().add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 330, 340, 25));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Código Inep");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 110, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Rua");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, 60, 30));
        getContentPane().add(txtRua, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 410, 340, 25));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("N°");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 410, -1, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Bairro");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, 90, 30));
        getContentPane().add(txtBairro, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 450, 340, 25));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Cidade");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, 90, 30));
        getContentPane().add(txtCidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 370, 340, 25));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("CEP");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 370, 60, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Estado");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 480, 60, 40));
        getContentPane().add(txtEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 490, 340, 25));

        tblEscola = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        }
        ;
        tblEscola.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Nome", "Código Inep", "Rua", "Número", "Bairro", "Cidade", "CEP", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblEscola.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblEscola.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEscolaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblEscola);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 800, 100));

        btAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Adicionar.png"))); // NOI18N
        btAdicionar.setToolTipText("Adicionar Escola");
        btAdicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btAdicionar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btAdicionarMouseClicked(evt);
            }
        });
        getContentPane().add(btAdicionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 550, 80, 90));

        btEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Editar.png"))); // NOI18N
        btEditar.setToolTipText("Editar Dados da Escola");
        btEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btEditar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btEditarMouseClicked(evt);
            }
        });
        getContentPane().add(btEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 550, 80, 90));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Excluir.png"))); // NOI18N
        jLabel10.setToolTipText("Excluir Escola");
        jLabel10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 550, 90, 90));

        btLimpar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/MicrosoftTeaage.png"))); // NOI18N
        btLimpar2.setToolTipText("Limpar os campos");
        btLimpar2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btLimpar2.setPreferredSize(new java.awt.Dimension(50, 45));
        btLimpar2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btLimpar2MouseClicked(evt);
            }
        });
        getContentPane().add(btLimpar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 590, 50, 45));

        btLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/MicrosoftTeams.png"))); // NOI18N
        btLimpar.setToolTipText("Limpar os campos");
        btLimpar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btLimpar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btLimparMouseClicked(evt);
            }
        });
        btLimpar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btLimparKeyPressed(evt);
            }
        });
        getContentPane().add(btLimpar, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 560, -1, -1));

        try {
            txtCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        getContentPane().add(txtCep, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 370, 220, 25));
        getContentPane().add(txtNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 410, 220, 25));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/background.png"))); // NOI18N
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 870, 510));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setText("Id ");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel12.setText("ADMINISTRAÇÃO DE ESCOLA");

        txtId.setEditable(false);

        btPesquisar.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Pesquisar(pequeno).png"))); // NOI18N
        btPesquisar.setText("Pesquisar");
        btPesquisar.setToolTipText("Pesquisar Escola");
        btPesquisar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarActionPerformed(evt);
            }
        });

        txtPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPesquisarKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(517, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(btPesquisar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 870, 170));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarActionPerformed
       pesquisar_escola();
    }//GEN-LAST:event_btPesquisarActionPerformed

    private void tblEscolaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEscolaMouseClicked
       setar_campos();
    }//GEN-LAST:event_tblEscolaMouseClicked

    private void txtPesquisarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            pesquisar_escola();
        }
    }//GEN-LAST:event_txtPesquisarKeyPressed

    private void btAdicionarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btAdicionarMouseClicked
        adicionar();
    }//GEN-LAST:event_btAdicionarMouseClicked

    private void btEditarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btEditarMouseClicked
        alterar();
    }//GEN-LAST:event_btEditarMouseClicked

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        excluir_escola();
    }//GEN-LAST:event_jLabel10MouseClicked

    private void btLimparKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btLimparKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btLimparKeyPressed

    private void btLimparMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btLimparMouseClicked
        limpar();
        
    }//GEN-LAST:event_btLimparMouseClicked

    private void btLimpar2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btLimpar2MouseClicked
        limpar();
    }//GEN-LAST:event_btLimpar2MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btAdicionar;
    private javax.swing.JLabel btEditar;
    private javax.swing.JLabel btLimpar;
    private javax.swing.JLabel btLimpar2;
    private javax.swing.JButton btPesquisar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblEscola;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JFormattedTextField txtCep;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtPesquisar;
    private javax.swing.JTextField txtRua;
    // End of variables declaration//GEN-END:variables
}
