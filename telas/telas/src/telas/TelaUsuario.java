package telas;

import java.awt.event.KeyEvent;
import dal.ModuloConexao;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

public class TelaUsuario extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public TelaUsuario() {
        initComponents();
        conexao = ModuloConexao.connector();
    }
    
    private void adicionar(){
        
        String sql = "insert into tb_usuarios(nome_usuario,login_usuario,senha_usuario,perfil_usuario) values (?,?,?,?)";
        
        try{
            
            pst = conexao.prepareStatement(sql); 
            //pst.setString(1, txtIdUser.getText());
            pst.setString(1, txtNome.getText());
            pst.setString(2, txtLogin.getText());
            pst.setString(3, txtSenha.getText());
            pst.setString(4, ComboPerfil.getSelectedItem().toString());
            
           if((txtNome.getText().isEmpty()) || (txtLogin.getText().isEmpty()) || (txtSenha.getText().isEmpty())) {
               JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
           
           if((txtNome.getText().isEmpty())){
               txtNome.setBorder(new LineBorder(Color.RED, 1));
               txtNome.requestFocus();
           }
           if ((txtLogin.getText().isEmpty())){
               txtLogin.setBorder(new LineBorder(Color.RED, 1));
               txtLogin.requestFocus();
           }
           if ((txtSenha.getText().isEmpty())){
               txtSenha.setBorder(new LineBorder(Color.RED, 1));
               txtSenha.requestFocus();
           }
           
           }else{
               int adicionado = pst.executeUpdate();
               if(adicionado > 0){
                   
                JOptionPane.showMessageDialog(null, "Usuário adicionado com sucesso!");   
                   //txtIdUser.setText(null);
                   txtNome.setText(null);
                   ComboPerfil.setSelectedItem(null);
                   txtLogin.setText(null);
                   txtSenha.setText(null);
               }
           }
           
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void pesquisar(){
     
String sql = "select id_usuario as Id,nome_usuario as Nome,login_usuario as Login,senha_usuario as Senha,perfil_usuario as Perfil from tb_usuarios where nome_usuario  like ?";        
        try{
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtPesquisar.getText() + "%");
            rs = pst.executeQuery();
            tblUsuarios.setModel(DbUtils.resultSetToTableModel(rs));
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    private void setar_campos() {
        int setar = tblUsuarios.getSelectedRow();
        txtIdUser.setText(tblUsuarios.getModel().getValueAt(setar, 0).toString());
        txtNome.setText(tblUsuarios.getModel().getValueAt(setar, 1).toString());
        txtLogin.setText(tblUsuarios.getModel().getValueAt(setar, 2).toString());
        txtSenha.setText(tblUsuarios.getModel().getValueAt(setar, 3).toString());
        ComboPerfil.setSelectedItem(tblUsuarios.getModel().getValueAt(setar, 4).toString());
        
        btAdicionar.setEnabled(false);
    }
    private void alterar() {
        
         String sql = "update tb_usuarios set nome_usuario =?,login_usuario =?,senha_usuario = ?,perfil_usuario = ? where id_usuario = ?";

       try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNome.getText());
            pst.setString(2, txtLogin.getText());
            pst.setString(3, txtSenha.getText());
            pst.setString(4, ComboPerfil.getSelectedItem().toString());
            pst.setString(5, txtIdUser.getText());

            if ((txtIdUser.getText().isEmpty()) || (txtNome.getText().isEmpty()) || (txtLogin.getText().isEmpty()) || (txtSenha.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
            } else {
                int adicionado = pst.executeUpdate();

                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados do usuário atualizados!");
                    txtNome.setText(null);
                    ComboPerfil.setSelectedItem(null);
                    txtLogin.setText(null);
                    txtSenha.setText(null);
                }
            }

        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void remover() {
        String sql = "delete from tb_usuarios where id_usuario = ? ";
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover esse usuário?", "Atenção!", JOptionPane.YES_NO_OPTION);

        if (confirma == JOptionPane.YES_OPTION) {

            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtIdUser.getText());
                int apagado = pst.executeUpdate();

                if (apagado > 0) {
                JOptionPane.showMessageDialog(null, "Usuário removido com sucesso!");
                txtNome.setText(null);
                ComboPerfil.setSelectedItem(null);
                txtLogin.setText(null);
                txtSenha.setText(null);
                }

            }catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            }
            }
    
    private void limpar(){
        
        txtIdUser.setText(null);
        txtNome.setText(null);
        txtLogin.setText(null);
        txtSenha.setText(null);
        txtPesquisar.setText(null);
        ComboPerfil.setSelectedItem(null);
        btPesquisar.setEnabled(true);
        btAdicionar.setEnabled(true);
        tblUsuarios.setVisible(true);
        ((DefaultTableModel) tblUsuarios.getModel()).setRowCount(0);
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblUsuarios = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtLogin = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtSenha = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        ComboPerfil = new javax.swing.JComboBox<>();
        btExcluir = new javax.swing.JLabel();
        btEditar = new javax.swing.JLabel();
        btAdicionar = new javax.swing.JLabel();
        btLimpar2 = new javax.swing.JLabel();
        btLimpar = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btPesquisar = new javax.swing.JButton();
        txtPesquisar = new javax.swing.JTextField();
        txtIdUser = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Usuário");
        setPreferredSize(new java.awt.Dimension(884, 700));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblUsuarios = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        }
        ;
        tblUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "Login", "Senha", "Perfil"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUsuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblUsuarios);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 800, 100));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Nome");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, -1, 30));

        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });
        getContentPane().add(txtNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 320, 730, 25));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Login");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, -1, 30));
        getContentPane().add(txtLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 390, 390, 25));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Senha");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 460, -1, 32));
        getContentPane().add(txtSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 460, 390, 25));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Perfil");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 390, -1, 30));

        ComboPerfil.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ComboPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "admin", "user" }));
        ComboPerfil.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ComboPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboPerfilActionPerformed(evt);
            }
        });
        getContentPane().add(ComboPerfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 390, 120, 25));

        btExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Excluir.png"))); // NOI18N
        btExcluir.setToolTipText("Excluir Usuário");
        btExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btExcluir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btExcluirMouseClicked(evt);
            }
        });
        getContentPane().add(btExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 560, 80, -1));

        btEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Editar.png"))); // NOI18N
        btEditar.setToolTipText("Editar Usuário");
        btEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btEditar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btEditarMouseClicked(evt);
            }
        });
        getContentPane().add(btEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 560, -1, -1));

        btAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Adicionar.png"))); // NOI18N
        btAdicionar.setToolTipText("Adicionar Usuário");
        btAdicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btAdicionar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btAdicionarMouseClicked(evt);
            }
        });
        getContentPane().add(btAdicionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 550, 80, 90));

        btLimpar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/MicrosoftTeaage.png"))); // NOI18N
        btLimpar2.setToolTipText("Limpar campos");
        btLimpar2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btLimpar2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btLimpar2MouseClicked(evt);
            }
        });
        getContentPane().add(btLimpar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 590, -1, -1));

        btLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/MicrosoftTeams.png"))); // NOI18N
        btLimpar.setToolTipText("Limpar campos");
        btLimpar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btLimpar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btLimparMouseClicked(evt);
            }
        });
        getContentPane().add(btLimpar, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 560, -1, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/background.png"))); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 870, 470));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btPesquisar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Pesquisar(pequeno).png"))); // NOI18N
        btPesquisar.setText("Pesquisar");
        btPesquisar.setToolTipText("PESQUISAR USUÁRIO");
        btPesquisar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarActionPerformed(evt);
            }
        });

        txtPesquisar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPesquisarKeyPressed(evt);
            }
        });

        txtIdUser.setEditable(false);
        txtIdUser.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtIdUser.setMinimumSize(new java.awt.Dimension(6, 23));
        txtIdUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIdUserKeyPressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Id");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel7.setText("ADMINISTRAÇÃO DE USUÁRIOS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(btPesquisar)
                        .addGap(163, 163, 163)
                        .addComponent(jLabel1)
                        .addGap(10, 10, 10)
                        .addComponent(txtIdUser, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btPesquisar)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdUser, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 870, 190));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ComboPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboPerfilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboPerfilActionPerformed

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void btPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarActionPerformed
        pesquisar();
    }//GEN-LAST:event_btPesquisarActionPerformed

    private void txtIdUserKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdUserKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
        pesquisar();
        }
    }//GEN-LAST:event_txtIdUserKeyPressed

    private void tblUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUsuariosMouseClicked
       setar_campos();
    }//GEN-LAST:event_tblUsuariosMouseClicked

    private void txtPesquisarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            pesquisar();
        }
    }//GEN-LAST:event_txtPesquisarKeyPressed

    private void btEditarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btEditarMouseClicked
        alterar();
    }//GEN-LAST:event_btEditarMouseClicked

    private void btAdicionarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btAdicionarMouseClicked
        adicionar();
    }//GEN-LAST:event_btAdicionarMouseClicked

    private void btExcluirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btExcluirMouseClicked
        remover();
    }//GEN-LAST:event_btExcluirMouseClicked

    private void btLimparMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btLimparMouseClicked
        // TODO add your handling code here:
        limpar();
    }//GEN-LAST:event_btLimparMouseClicked

    private void btLimpar2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btLimpar2MouseClicked
        // TODO add your handling code here:
        limpar();
    }//GEN-LAST:event_btLimpar2MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboPerfil;
    private javax.swing.JLabel btAdicionar;
    private javax.swing.JLabel btEditar;
    private javax.swing.JLabel btExcluir;
    private javax.swing.JLabel btLimpar;
    private javax.swing.JLabel btLimpar2;
    private javax.swing.JButton btPesquisar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblUsuarios;
    private javax.swing.JTextField txtIdUser;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtPesquisar;
    private javax.swing.JTextField txtSenha;
    // End of variables declaration//GEN-END:variables
}
