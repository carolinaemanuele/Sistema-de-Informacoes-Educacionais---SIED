package telas;
import dal.ModuloConexao;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class TelaAdvertencia extends javax.swing.JFrame {

   Connection  conexao = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        
    public TelaAdvertencia() {
        initComponents();
        conexao = ModuloConexao.connector();
    }


    private void pesquisar(){
        
        String sql = "select * from tb_aluno where nome_aluno like?";
        
        try{
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtAluno.getText() + "%");
            rs=pst.executeQuery();
            tblAluno.setModel(DbUtils.resultSetToTableModel(rs));
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void setar_campos(){
        
       int setar = tblAluno.getSelectedRow();
       txtIdAluno.setText(tblAluno.getModel().getValueAt(setar, 0).toString());
       txtNome.setText(tblAluno.getModel().getValueAt(setar , 2).toString());
       txtSobrenome.setText(tblAluno.getModel().getValueAt(setar, 3).toString());
       txtData.setText(null);
       txtAcontecimento.setText(null);
       txtMotivo.setText(null);
    }
    
    private void adicionar(){
        
        String sql = "insert into adv_aluno (motivos,data_acontecimento,advertencia,id_aluno) values(?,?,?,?)";
        
        try{
             pst = conexao.prepareStatement(sql);
             pst.setString(1, txtMotivo.getText());
             pst.setString(2, txtData.getText());
             pst.setString(3, txtAcontecimento.getText());
             pst.setString(4, txtIdAluno.getText());
             
             if((txtNome.getText().isEmpty()) || (txtData.getText().isEmpty()) ||(txtAcontecimento.getText().isEmpty()) ||(txtMotivo.getText().isEmpty())){
                 
             JOptionPane.showMessageDialog(null, "Insira todos os dados");
             
             if((txtMotivo.getText().isEmpty())){
                 txtMotivo.setBorder(new LineBorder(Color.RED,1));
                 txtMotivo.requestFocus();
             }
             if((txtData.getText().isEmpty())){
                 txtData.setBorder(new LineBorder(Color.RED,1));
                 txtData.requestFocus();
             }
             if((txtAcontecimento.getText().isEmpty())){
                 txtAcontecimento.setBorder(new LineBorder(Color.RED, 1));
                 txtAcontecimento.requestFocus();
             }
             
             }else{
                 int adicionado = pst.executeUpdate();
                 
                 if(adicionado > 0){
                     JOptionPane.showMessageDialog(null, "Suspensão criada com sucesso!");
                     
                     txtNome.setText(null);
                     txtData.setText(null);
                     txtAcontecimento.setText(null);
                     txtIdAluno.setText(null);
                     txtMotivo.setText(null);
                     txtAluno.setText(null);
                     txtSobrenome.setText(null);
                     txtIdAdvertencia.setText(null);
                    txtAcontecimento.setBorder(new LineBorder(null, 1));
                    txtMotivo.setBorder(new LineBorder(null, 1));
                    txtData.setBorder(new LineBorder(null, 1));
                 }
             }
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
     }
     
    private void pesquisar_adv(){
        
        String sql = "select * from adv_aluno where id_aluno =?";

        try{
            
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtIdAluno.getText());
            rs = pst.executeQuery();
            
            if(rs.next()){
                
                txtIdAdvertencia.setText(rs.getString(1));
                txtMotivo.setText(rs.getString(2));
                txtData.setText(rs.getString(3));
                txtAcontecimento.setText(rs.getString(4));
                
            }else{
               
                JOptionPane.showMessageDialog(null, "Este aluno não possui advertência!");

            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void editar(){
        
        String sql = "update adv_aluno set motivos=?,data_acontecimento=?,advertencia =?,id_aluno =? where id_adv_aluno =?";
        
        
    try{
            
        pst = conexao.prepareStatement(sql);
        pst.setString(1, txtMotivo.getText());
        pst.setString(2, txtData.getText());
        pst.setString(3, txtAcontecimento.getText());
        pst.setString(4, txtIdAluno.getText());
        pst.setString(5, txtIdAdvertencia.getText());
        
        if((txtMotivo.getText().isEmpty()) || (txtData.getText().isEmpty()) || (txtAcontecimento.getText().isEmpty()) || (txtIdAluno.getText().isEmpty()) || (txtIdAdvertencia.getText().isEmpty())){
            
            JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");
         
        //Código que verifica se tem algum campo sem informação, se tiver
        //a borda automaticamente ficará vermelha, indicando o campo que está 
        //faltando ser preenchido.
        if((txtMotivo.getText().isEmpty())){
            txtMotivo.requestFocus();
            txtMotivo.setBorder(new LineBorder(Color.RED, 1));
            
       }

        if((txtAcontecimento.getText().isEmpty())){
            txtAcontecimento.setBorder(new LineBorder(Color.RED, 1));
            txtAcontecimento.requestFocus();
        }
        
        if((txtData.getText().isEmpty())){
          txtData.setBorder(new LineBorder(Color.RED, 1));
          txtData.requestFocus();
         
        }
        }
        else{
            int editado = pst.executeUpdate();
            
            // Se editado for maior que 0, o sistema dá continuidade e edita os
            //dados no banco de dados, além de resetar a cor da borda.
            if(editado >0){
            JOptionPane.showMessageDialog(null, "Advertência atualizada com sucesso!");

            txtAcontecimento.setBorder(new LineBorder(null, 1));
            txtMotivo.setBorder(new LineBorder(null, 1));
            txtData.setBorder(new LineBorder(null, 1));
             
        }
        }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void remover(){
        
        String sql = "delete from adv_aluno where id_adv_aluno =?";
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover essa Advertência?", "Atenção!", JOptionPane.YES_NO_OPTION);

        if (confirma == JOptionPane.YES_OPTION) {
           
        try{
            
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtIdAdvertencia.getText());

            int removido = pst.executeUpdate();
            
            if(removido > 0){
                JOptionPane.showMessageDialog(null, "Advertência removida com sucesso!");

                    txtNome.setText(null);
                    txtSobrenome.setText(null);
                    txtData.setText(null);
                    txtMotivo.setText(null);
                    txtAcontecimento.setText(null);
                    txtIdAluno.setText(null);
                    txtIdAdvertencia.setText(null);
                    txtData.setBorder(new LineBorder(null, 1));
                    txtAcontecimento.setBorder(new LineBorder(null, 1));
                    txtMotivo.setBorder(new LineBorder(null, 1));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Selecione o aluno");
        }
    }
}
    private void limpar_bordas(){
        txtData.setBorder(new LineBorder(null, 1));
        txtAcontecimento.setBorder(new LineBorder(null, 1));
        txtMotivo.setBorder(new LineBorder(null, 1));
    }
    
    private void limpar(){
        
        txtNome.setText(null);
        txtData.setText(null);
        txtIdAluno.setText(null);
        txtIdAdvertencia.setText(null);
        txtMotivo.setText(null);
        txtAluno.setText(null);
        txtSobrenome.setText(null);
        txtAcontecimento.setText(null);
        tblAluno.setModel(null);
        btAdicionar.setEnabled(true);
        btEditar.setEnabled(true);
        btRemover.setEnabled(true);
        txtData.setBorder(new LineBorder(null, 1));
        txtAcontecimento.setBorder(new LineBorder(null, 1));
        txtMotivo.setBorder(new LineBorder(null, 1));
        
    }
    private void imprimir() {
            
     // imprimindo uma Advertência
     int confirma = JOptionPane.showConfirmDialog(null, "Confirma a impressão desta Advertência?", "Atenção", JOptionPane.YES_NO_OPTION);
     if (confirma == JOptionPane.YES_OPTION) {
    
     try {
         
     HashMap filtro = new HashMap();
     filtro.put("id_adv_aluno", Integer.parseInt(txtIdAdvertencia.getText()));
     JasperReport report = JasperCompileManager.compileReport("C:\\Users\\Carlos\\JaspersoftWorkspace\\MyReports\\Advertencia_1.jrxml");
     JasperPrint print = JasperFillManager.fillReport(report, filtro, conexao);
     JasperViewer viewer = new JasperViewer(print, false);
     viewer.setVisible(true);
     viewer.setTitle("Suspensão");
     
     } catch (Exception e) {
     JOptionPane.showMessageDialog(null, e);
     }
     }
     }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblAluno = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        txtAluno = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        btPesquisar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtIdAluno = new javax.swing.JTextField();
        txtData = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAcontecimento = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        txtSobrenome = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtIdAdvertencia = new javax.swing.JTextField();
        txtMotivo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btAdicionar = new javax.swing.JLabel();
        btEditar = new javax.swing.JLabel();
        btRemover = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Advertência");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblAluno.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblAluno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAlunoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblAluno);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 137, 896, 96));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Limpar.png"))); // NOI18N
        jLabel13.setToolTipText("Limpar Campos");
        jLabel13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 550, -1, 50));

        txtAluno.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtAluno.setPreferredSize(new java.awt.Dimension(6, 30));
        txtAluno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAlunoKeyPressed(evt);
            }
        });
        getContentPane().add(txtAluno, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 93, 245, 31));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Caixinha vazia.png"))); // NOI18N
        jLabel12.setToolTipText("Limpar Campos");
        jLabel12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 520, 80, 90));

        btPesquisar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btPesquisar.setText("Pesquisar");
        btPesquisar.setToolTipText("Pesquisar Aluno");
        btPesquisar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarActionPerformed(evt);
            }
        });
        getContentPane().add(btPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 91, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("ID Aluno");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 336, -1, -1));

        txtIdAluno.setEditable(false);
        txtIdAluno.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtIdAluno.setPreferredSize(new java.awt.Dimension(6, 30));
        getContentPane().add(txtIdAluno, new org.netbeans.lib.awtextra.AbsoluteConstraints(133, 334, 142, -1));

        txtData.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtData.setPreferredSize(new java.awt.Dimension(6, 30));
        getContentPane().add(txtData, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 291, 270, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Data");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 280, -1, 30));

        txtAcontecimento.setColumns(20);
        txtAcontecimento.setRows(5);
        txtAcontecimento.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane2.setViewportView(txtAcontecimento);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 411, 896, 100));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Relatório");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(381, 382, 110, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Nome");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 248, -1, -1));

        txtNome.setEditable(false);
        txtNome.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtNome.setPreferredSize(new java.awt.Dimension(7, 30));
        getContentPane().add(txtNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(133, 246, 395, -1));

        txtSobrenome.setEditable(false);
        txtSobrenome.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtSobrenome.setPreferredSize(new java.awt.Dimension(6, 30));
        getContentPane().add(txtSobrenome, new org.netbeans.lib.awtextra.AbsoluteConstraints(133, 289, 395, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Sobrenome");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 291, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("ID");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(756, 102, -1, -1));

        txtIdAdvertencia.setEditable(false);
        txtIdAdvertencia.setPreferredSize(new java.awt.Dimension(6, 30));
        getContentPane().add(txtIdAdvertencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(791, 94, 117, -1));

        txtMotivo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtMotivo.setPreferredSize(new java.awt.Dimension(6, 30));
        getContentPane().add(txtMotivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 246, 270, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel8.setText("Advertência");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 13, 161, 28));

        jLabel9.setFont(new java.awt.Font("Tahoma", 2, 13)); // NOI18N
        jLabel9.setText("Todos os campos são obrigatórios");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 48, -1, -1));

        btAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Adicionar.png"))); // NOI18N
        btAdicionar.setToolTipText("Adicionar Advertência ao Aluno");
        btAdicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btAdicionar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btAdicionarMouseClicked(evt);
            }
        });
        getContentPane().add(btAdicionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 520, -1, 92));

        btEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Editar.png"))); // NOI18N
        btEditar.setToolTipText("Editar Advertência");
        btEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btEditar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btEditarMouseClicked(evt);
            }
        });
        getContentPane().add(btEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 520, 81, 92));

        btRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Excluir.png"))); // NOI18N
        btRemover.setToolTipText("Remover Advertência do Aluno");
        btRemover.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btRemover.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btRemoverMouseClicked(evt);
            }
        });
        getContentPane().add(btRemover, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 520, 81, 92));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/print.png"))); // NOI18N
        jLabel10.setToolTipText("Gerar Advertência");
        jLabel10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 520, 74, 92));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/background.png"))); // NOI18N
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(-6, 271, 930, 530));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Motivo");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(570, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(300, 300, 300))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 248, Short.MAX_VALUE)
                .addComponent(jLabel4))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 930, 270));

        setSize(new java.awt.Dimension(938, 668));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarActionPerformed
        pesquisar();
    }//GEN-LAST:event_btPesquisarActionPerformed

    private void tblAlunoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAlunoMouseClicked
        limpar_bordas();
        setar_campos();
        pesquisar_adv();
    }//GEN-LAST:event_tblAlunoMouseClicked

    private void btAdicionarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btAdicionarMouseClicked
        adicionar();
    }//GEN-LAST:event_btAdicionarMouseClicked

    private void btEditarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btEditarMouseClicked
        editar();
    }//GEN-LAST:event_btEditarMouseClicked

    private void btRemoverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btRemoverMouseClicked
        
        remover();
        
    }//GEN-LAST:event_btRemoverMouseClicked

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        imprimir();
    }//GEN-LAST:event_jLabel10MouseClicked

    private void txtAlunoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAlunoKeyPressed
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            pesquisar();
       }
    }//GEN-LAST:event_txtAlunoKeyPressed

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        limpar();
    }//GEN-LAST:event_jLabel13MouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        limpar();
    }//GEN-LAST:event_jLabel12MouseClicked


    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaAdvertencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaAdvertencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaAdvertencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaAdvertencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaAdvertencia().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btAdicionar;
    private javax.swing.JLabel btEditar;
    private javax.swing.JButton btPesquisar;
    private javax.swing.JLabel btRemover;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblAluno;
    private javax.swing.JTextArea txtAcontecimento;
    private javax.swing.JTextField txtAluno;
    private javax.swing.JTextField txtData;
    private javax.swing.JTextField txtIdAdvertencia;
    private javax.swing.JTextField txtIdAluno;
    private javax.swing.JTextField txtMotivo;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtSobrenome;
    // End of variables declaration//GEN-END:variables
}
