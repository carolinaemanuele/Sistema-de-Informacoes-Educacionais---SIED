package telas;

import java.awt.event.KeyEvent;
import java.sql.*;
import dal.ModuloConexao;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import static telas.TelaPrincipal.txtAluno;

public class TelaLogin extends javax.swing.JFrame {
    
        Connection  conexao = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        
    public void logar(){
            
        String sql = "select * from tb_usuarios where login_usuario = ? and senha_usuario =?";
        
        try{
        
        pst = conexao.prepareStatement(sql);
        pst.setString(1, txtLogin.getText());
        pst.setString(2, txtSenha.getText());
        
        rs = pst.executeQuery();
        
        if(rs.next()){
            
           String perfil_usuario = rs.getString(5); 
           
           if(perfil_usuario.equals ("admin")){
            
            TelaPrincipal principal = new TelaPrincipal();
            principal.setVisible(true);   
            TelaPrincipal.menuCadUser.setEnabled(true);
            
            TelaPrincipal.txtUsuario.setText(rs.getString(2));
            TelaPrincipal.txtUsuario.setForeground(Color.blue);
            
            
            
            TelaPrincipal.menuEscola.setEnabled(true);
           
            
            this.dispose();    
           }
           if(perfil_usuario.equals ("user")){
            
            TelaPrincipal principal = new TelaPrincipal();
            principal.setVisible(true);
            
           }
        }else{
         
        JOptionPane.showMessageDialog(null, "Usuário/Senha inválidos!")  ;  
            
        if((txtLogin.getText().isEmpty())){
            
            
            txtLogin.setBorder(new LineBorder(Color.RED, 1));
            
            
            
            txtLogin.requestFocus();
        
          
        }
        if((txtSenha.getText().isEmpty())){
            txtSenha.setBorder(new LineBorder(Color.RED, 1));
            txtSenha.requestFocus();
        
          
        }
        txtLogin.setText(null);
        txtSenha.setText(null);
        
        
       
        }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    

    public TelaLogin() {
        initComponents();
        
       conexao = ModuloConexao.connector();
        
        
    }

        private void soma_de_alunos(){
            
            String sql = "select count(*) from tb_aluno";
            
            try{
                pst = conexao.prepareStatement(sql);
                rs = pst.executeQuery();
                
                if(rs.next()){
                    
                    String soma = rs.getString("count(*)");
                TelaPrincipal.txtAluno.setText(soma);
                }
                
            }catch(Exception e){
               
            }
        }
        
        
        private void soma_prof(){
            
            String sql = "select count(*) from tb_professor";
            
            try{
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            
            if(rs.next()){
                
                String contar = rs.getString("count(*)");
                
                TelaPrincipal.txtProfessores.setText(contar);
            }
            }catch(Exception e){
                
            }
            }
        
        private void soma_turma(){
            
            String sql = "select count(*) from tb_turma";
            
            try{
                pst = conexao.prepareStatement(sql);
                rs = pst.executeQuery();
                
                if(rs.next()){
                    
                    String contar = rs.getString("count(*)");
                    TelaPrincipal.txtTurmas.setText(contar);
                    
                }
                
            }catch(Exception e){
               
            }
        }
            
            private void limpar_campos(){
               txtLogin.setText(null);
               txtSenha.setText(null);
               
              
            
            
        }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btOlhoOculto = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btOlho = new javax.swing.JLabel();
        txtLimpar = new javax.swing.JButton();
        btLogar4 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtSenha = new javax.swing.JPasswordField();
        txtLogin = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        initLabel = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();

        jLabel7.setText("jLabel7");

        jLabel10.setText("jLabel10");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/fundo-azul-e-branco-abstrato (1).jpg"))); // NOI18N

        btOlhoOculto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Oculto.png"))); // NOI18N
        btOlhoOculto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btOlhoOcultoMouseClicked(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tela de Login");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/education_computer_school_graduate_cap_monitor_icon_149680.png"))); // NOI18N
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, 70, 70));

        btOlho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Oculto.png"))); // NOI18N
        btOlho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btOlhoMouseClicked(evt);
            }
        });
        btOlho.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btOlhoKeyPressed(evt);
            }
        });
        getContentPane().add(btOlho, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 230, -1, -1));

        txtLimpar.setBackground(new java.awt.Color(0, 0, 0));
        txtLimpar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/apagador (1).png"))); // NOI18N
        txtLimpar.setText("Limpar");
        txtLimpar.setToolTipText("Limpar");
        txtLimpar.setBorder(null);
        txtLimpar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLimparActionPerformed(evt);
            }
        });
        getContentPane().add(txtLimpar, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 270, 110, 40));

        btLogar4.setBackground(new java.awt.Color(0, 0, 0));
        btLogar4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btLogar4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/enter (1).png"))); // NOI18N
        btLogar4.setText("Entrar");
        btLogar4.setToolTipText("Entrar");
        btLogar4.setBorder(null);
        btLogar4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btLogar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLogar4ActionPerformed(evt);
            }
        });
        getContentPane().add(btLogar4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, 110, 40));

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel3.setText("BEM-VINDO!");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 170, 40));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("SIED");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 80, 40, -1));

        jLabel4.setForeground(new java.awt.Color(0, 0, 255));
        jLabel4.setText("Não possui usuário ou esqueceu a senha?");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 330, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/cadeado.png"))); // NOI18N
        jLabel2.setToolTipText("SENHA");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, 50));

        txtSenha.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSenha.setForeground(new java.awt.Color(153, 153, 153));
        txtSenha.setText("senha");
        txtSenha.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtSenha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSenhaMouseClicked(evt);
            }
        });
        txtSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSenhaActionPerformed(evt);
            }
        });
        txtSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSenhaKeyPressed(evt);
            }
        });
        getContentPane().add(txtSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 280, 31));

        txtLogin.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtLogin.setForeground(new java.awt.Color(153, 153, 153));
        txtLogin.setText("digite o seu usúario");
        txtLogin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtLoginMouseClicked(evt);
            }
        });
        txtLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLoginActionPerformed(evt);
            }
        });
        txtLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLoginKeyPressed(evt);
            }
        });
        getContentPane().add(txtLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, 280, 33));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/usuario.png"))); // NOI18N
        jLabel1.setToolTipText("USUÁRIO");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 30, 33));

        jLabel5.setText("Informe usuário e senha para continuar!");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 46, 250, 30));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/fundo-azul-e-branco-abstrato (1).jpg"))); // NOI18N
        jLabel8.setText("jLabel8");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(-100, -10, 520, 390));

        initLabel.setEditable(false);
        initLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                initLabelActionPerformed(evt);
            }
        });
        jPanel1.add(initLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 40, -1));
        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 190, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 370, 380));

        setSize(new java.awt.Dimension(375, 406));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtSenhaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSenhaKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            logar();
            soma_de_alunos();
            soma_prof();
            soma_turma();
        
    }
        


    }//GEN-LAST:event_txtSenhaKeyPressed

    private void txtSenhaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSenhaMouseClicked
    txtSenha.setText("");
    txtSenha.setForeground(Color.BLACK);
       
    }//GEN-LAST:event_txtSenhaMouseClicked

    private void txtLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLoginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLoginActionPerformed

    private void txtLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLimparActionPerformed
        // TODO add your handling code here:
        limpar_campos();
    }//GEN-LAST:event_txtLimparActionPerformed

    private void btLogar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLogar4ActionPerformed
        // TODO add your handling code here:
        logar();
        
        int a=0,b=0;
        
        String login=txtLogin.getText();
        String senha=txtSenha.getText();
        
        soma_turma();
        soma_de_alunos();
        soma_prof();
        
        
        
        //if((txtSenha.getText().isEmpty())){
            //txtSenha.setBorder(new LineBorder(Color.RED, 2));
           // txtSenha.requestFocus();
       // }
                
       // else{
            //b=1;
            //txtSenha.setBorder(new LineBorder(Color.BLACK, 2));
          
       // }
        //if(a==1 & b==1){
            
       // }
        
        
       
    }//GEN-LAST:event_btLogar4ActionPerformed

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
      
        TelaEsqueceu esqueceu = new TelaEsqueceu();
        esqueceu.setVisible(true);
        
    }//GEN-LAST:event_jLabel4MouseClicked

    private void txtLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtLoginMouseClicked
        // TODO add your handling code here:
        txtLogin.setText(null);
        txtLogin.setForeground(Color.BLACK);
    }//GEN-LAST:event_txtLoginMouseClicked

    private void initLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_initLabelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_initLabelActionPerformed
    int x = 0;
    private void btOlhoOcultoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btOlhoOcultoMouseClicked
       
    }//GEN-LAST:event_btOlhoOcultoMouseClicked

    private void txtLoginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLoginKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_TAB){
            txtSenha.setText(null);
            txtSenha.setForeground(Color.BLACK);
            txtSenha.requestFocus();
        
        }
    }//GEN-LAST:event_txtLoginKeyPressed

    private void btOlhoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btOlhoKeyPressed
        // TODO add your handling code here:
       

    }//GEN-LAST:event_btOlhoKeyPressed

    private void btOlhoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btOlhoMouseClicked
        // TODO add your handling code here:
         if (x == 0) {
        ImageIcon img;
        img = new ImageIcon(getClass().getResource("/imagens/oculto.png"));
        btOlho.setIcon(img);
                
        txtSenha.setEchoChar('\u0000');
        x += 1;
        }
        else {
            
         ImageIcon img;
         img = new ImageIcon(getClass().getResource("/imagens/view.png"));
         btOlho.setIcon(img);
                
        txtSenha.setEchoChar('●');
        x -= 1;
        
        }
    }//GEN-LAST:event_btOlhoMouseClicked

    private void txtSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSenhaActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        initLabel.setVisible(false);
        
    }//GEN-LAST:event_formWindowOpened

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btLogar4;
    private javax.swing.JLabel btOlho;
    private javax.swing.JLabel btOlhoOculto;
    private javax.swing.JTextField initLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton txtLimpar;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JPasswordField txtSenha;
    // End of variables declaration//GEN-END:variables
}
