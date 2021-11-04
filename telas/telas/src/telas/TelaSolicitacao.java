package telas;

import dal.ModuloConexao;
import java.awt.Color;
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

public class TelaSolicitacao extends javax.swing.JFrame {

        Connection  conexao = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
    
    public TelaSolicitacao() {
        initComponents();
        conexao = ModuloConexao.connector();
    }

    private void pesquisar(){
        
        String sql = "select * from tb_aluno1 where nome_aluno like ?";
        
        try{
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtAluno.getText() + "%");
            rs = pst.executeQuery();
            tblAluno.setModel(DbUtils.resultSetToTableModel(rs));
            
        }catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
    }
    }
     private void setar_campos() {
        int setar = tblAluno.getSelectedRow();
        txtIdAluno.setText(tblAluno.getModel().getValueAt(setar, 0).toString());
        txtNome.setText(tblAluno.getModel().getValueAt(setar, 2).toString());
        txtSobrenome.setText(tblAluno.getModel().getValueAt(setar, 3).toString());
        txtData.setText(null);
        txtAcontecimento.setText(null);
       txtMotivos.setText(null);
     
     }
     private void adicionar(){
         
         String sql = "insert into solicitacao_aluno(motivos,data_acontecimento2,solicitacao,id_aluno) values(?,?,?,?)";
         
        try{
             pst = conexao.prepareStatement(sql);
             pst.setString(1, txtMotivos.getText());
             pst.setString(2, txtData.getText());
             pst.setString(3, txtAcontecimento.getText());
             pst.setString(4, txtIdAluno.getText());

             if((txtNome.getText().isEmpty()) || (txtData.getText().isEmpty()) ||(txtAcontecimento.getText().isEmpty()) ||(txtMotivos.getText().isEmpty())){

             JOptionPane.showMessageDialog(null, "Insira todos os dados");

             if((txtMotivos.getText().isEmpty())){
                 txtMotivos.setBorder(new LineBorder(Color.RED,1));
                 txtMotivos.requestFocus();
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
                     txtMotivos.setText(null);
                     txtAluno.setText(null);
                     txtSobrenome.setText(null);
                     txtIdSuspensao.setText(null);
                    txtAcontecimento.setBorder(new LineBorder(null, 1));
                    txtMotivos.setBorder(new LineBorder(null, 1));
                    txtData.setBorder(new LineBorder(null, 1));
                 }
             }
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
     }
     
    private void pesquisar_rel(){
        
        
        String sql = "select * from solicitacao_aluno where id_aluno=?";
        
        
        try{
            
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtIdAluno.getText());
            rs = pst.executeQuery();
            
            if(rs.next()){
                
                txtIdSuspensao.setText(rs.getString(1));
                   txtMotivos.setText(rs.getString(2));
                txtData.setText(rs.getString(3));
                txtAcontecimento.setText(rs.getString(4));
            
            }else{
                txtIdSuspensao.setText(null);
                txtData.setText(null);
                txtAcontecimento.setText(null);
                  txtMotivos.setText(null);
                JOptionPane.showMessageDialog(null, "Este aluno não possui solicitação");
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void editar(){
        
        String sql = "update solicitacao_aluno set motivos =?,data_acontecimento2 =?,solicitacao =?,id_aluno =? where id_sol_aluno =?";
    
       try{

        pst = conexao.prepareStatement(sql);
        pst.setString(1, txtMotivos.getText());
        pst.setString(2, txtData.getText());
        pst.setString(3, txtAcontecimento.getText());
        pst.setString(4, txtIdAluno.getText());
        pst.setString(5, txtIdSuspensao.getText());

        if((txtMotivos.getText().isEmpty()) || (txtData.getText().isEmpty()) || (txtAcontecimento.getText().isEmpty()) || (txtIdAluno.getText().isEmpty()) || (txtIdSuspensao.getText().isEmpty())){

            JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");

        //Código que verifica se tem algum campo sem informação, se tiver
        //a borda automaticamente ficará vermelha, indicando o campo que está 
        //faltando ser preenchido.
        if((txtMotivos.getText().isEmpty())){
            txtMotivos.requestFocus();
            txtMotivos.setBorder(new LineBorder(Color.RED, 1));

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
            txtMotivos.setBorder(new LineBorder(null, 1));
            txtData.setBorder(new LineBorder(null, 1));

        }
        }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void remover(){
          int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover esta solicitação?", "Atenção",JOptionPane.YES_NO_OPTION);
        if(confirma==JOptionPane.YES_OPTION){
        String sql = "delete from solicitacao_aluno where id_sol_aluno =?";
    
        try{
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtIdSuspensao.getText());

            int removido = pst.executeUpdate();
            
            if(removido > 0){
                
                JOptionPane.showMessageDialog(null, "SOLICITAÇÃO REMOVIDA COM SUCESSO!");
                
                txtAcontecimento.setText(null);
                   txtMotivos.setText(null);
                txtIdAluno.setText(null);
                txtSobrenome.setText(null);
                txtData.setText(null);
                txtNome.setText(null);
                txtIdSuspensao.setText(null);
            }
        }catch(Exception e){
            
            JOptionPane.showMessageDialog(null, e);
        }
    }
    }
    private void imprimir() {
            
     // imprimindo uma suspensão
     int confirma = JOptionPane.showConfirmDialog(null, "Confirma a impressão desta Solicitação?", "Atenção", JOptionPane.YES_NO_OPTION);
     if (confirma == JOptionPane.YES_OPTION) {
    
     try {
         
     HashMap filtro = new HashMap();
     filtro.put("id_aluno", Integer.parseInt(txtIdAluno.getText()));
     JasperReport report = JasperCompileManager.compileReport("C:\\Users\\jpgea\\JaspersoftWorkspace\\MyReports\\SuspensaoAluno.jrxml");
     JasperPrint print = JasperFillManager.fillReport(report, filtro, conexao);
     JasperViewer viewer = new JasperViewer(print, false);
     viewer.setVisible(true);
     viewer.setTitle("Suspensão");
     
     } catch (Exception e) {
     JOptionPane.showMessageDialog(null, e);
     }
     }
     }
    private void limpar_bordas(){
        txtData.setBorder(new LineBorder(null, 1));
        txtAcontecimento.setBorder(new LineBorder(null, 1));
        txtMotivos.setBorder(new LineBorder(null, 1));
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btPesquisarRel = new javax.swing.JButton();
        btSuspensao = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btAdicionar = new javax.swing.JButton();
        txtData = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAcontecimento = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        btRemover = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtIdAluno = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        try{
            javax.swing.text.MaskFormatter data= new javax.swing.text.MaskFormatter("##/##/####");
            txtData= new javax.swing.JFormattedTextField(data);
        }
        catch (Exception e){
        }
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtIdSuspensao = new javax.swing.JTextField();
        txtAluno = new javax.swing.JTextField();
        btPesquisar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAluno = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtSobrenome = new javax.swing.JTextField();
        txtMotivos = new javax.swing.JTextField();

        btPesquisarRel.setText("Pesquisar Relatório");
        btPesquisarRel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarRelActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Suspensão");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btSuspensao.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btSuspensao.setText("Gerar Suspensão");
        btSuspensao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSuspensaoActionPerformed(evt);
            }
        });
        getContentPane().add(btSuspensao, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 540, 210, 72));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel1.setText("Solicitação");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 13, 130, 33));

        btAdicionar.setText("Adicionar");
        btAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAdicionarActionPerformed(evt);
            }
        });
        getContentPane().add(btAdicionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 540, 140, 70));

        txtData.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        getContentPane().add(txtData, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 340, 150, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Relatório ");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 370, -1, -1));

        jButton1.setText("Editar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 540, 140, 70));

        txtAcontecimento.setColumns(20);
        txtAcontecimento.setRows(5);
        jScrollPane2.setViewportView(txtAcontecimento);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 900, 120));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("Data ");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, -1, -1));

        btRemover.setText("Remover");
        btRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRemoverActionPerformed(evt);
            }
        });
        getContentPane().add(btRemover, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 540, 140, 70));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Id do Aluno");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 120, 40));

        txtIdAluno.setEditable(false);
        txtIdAluno.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        getContentPane().add(txtIdAluno, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 300, 150, 30));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/fundo-azul-e-branco-abstrato (1).jpg"))); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 300, 930, 500));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Id");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 60, -1, 32));

        txtIdSuspensao.setEditable(false);
        txtIdSuspensao.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel1.add(txtIdSuspensao, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 60, 147, 32));

        txtAluno.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel1.add(txtAluno, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 62, 360, 33));

        btPesquisar.setText("Pesquisar");
        btPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarActionPerformed(evt);
            }
        });
        jPanel1.add(btPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(384, 61, -1, 37));

        tblAluno.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id", "Nome", "Sobrenome", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAluno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAlunoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblAluno);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 105, 900, 90));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Nome do Aluno");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 223, 170, 30));

        txtNome.setEditable(false);
        txtNome.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });
        jPanel1.add(txtNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 220, 390, 33));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Motivos");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 240, -1, 20));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Sobrenome do Aluno");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 210, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText("Sobrenome do Aluno");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 262, 200, 30));

        txtSobrenome.setEditable(false);
        txtSobrenome.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel1.add(txtSobrenome, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 260, 390, 30));

        txtMotivos.setPreferredSize(new java.awt.Dimension(6, 23));
        jPanel1.add(txtMotivos, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 210, 220, 90));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 300));

        setSize(new java.awt.Dimension(937, 673));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tblAlunoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAlunoMouseClicked
        setar_campos();
         pesquisar_rel();
        limpar_bordas();
    }//GEN-LAST:event_tblAlunoMouseClicked

    private void btPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarActionPerformed
       pesquisar();
    }//GEN-LAST:event_btPesquisarActionPerformed

    private void btAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAdicionarActionPerformed
        adicionar();
    }//GEN-LAST:event_btAdicionarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        editar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void btRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRemoverActionPerformed
       remover();
    }//GEN-LAST:event_btRemoverActionPerformed

    private void btSuspensaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSuspensaoActionPerformed
        imprimir();
    }//GEN-LAST:event_btSuspensaoActionPerformed

    private void btPesquisarRelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarRelActionPerformed

    }//GEN-LAST:event_btPesquisarRelActionPerformed

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
            java.util.logging.Logger.getLogger(TelaSolicitacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaSolicitacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaSolicitacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaSolicitacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaSolicitacao().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAdicionar;
    private javax.swing.JButton btPesquisar;
    private javax.swing.JButton btPesquisarRel;
    private javax.swing.JButton btRemover;
    private javax.swing.JButton btSuspensao;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JTextField txtIdAluno;
    private javax.swing.JTextField txtIdSuspensao;
    private javax.swing.JTextField txtMotivos;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtSobrenome;
    // End of variables declaration//GEN-END:variables
}
