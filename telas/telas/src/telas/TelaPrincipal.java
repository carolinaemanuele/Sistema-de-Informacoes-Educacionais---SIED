package telas;

import dal.ModuloConexao;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class TelaPrincipal extends javax.swing.JFrame {
        Connection  conexao = null;
        PreparedStatement pst = null;
        ResultSet rs = null;    
    
    public TelaPrincipal() {
        initComponents();
        conexao = ModuloConexao.connector();
    }
    private void imprimir(){
           try{
       JasperReport report = JasperCompileManager.compileReport("C:\\relatorios\\RelatorioEscola.jrxml");
        
        JasperPrint print = JasperFillManager.fillReport(report, null, conexao);
        
        JasperViewer viewer = new JasperViewer(print, false);
        viewer.setVisible(true);
        viewer.setTitle("Relatório da escola");
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
     }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktop = new javax.swing.JDesktopPane();
        jPanel4 = new javax.swing.JPanel();
        txtTurmas = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtProfessores = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtData = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtAluno = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuCadUser = new javax.swing.JMenuItem();
        menuEscola = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        menuRel = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SIED - Tela Principal");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        desktop.setBackground(new java.awt.Color(0, 153, 255));

        javax.swing.GroupLayout desktopLayout = new javax.swing.GroupLayout(desktop);
        desktop.setLayout(desktopLayout);
        desktopLayout.setHorizontalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 880, Short.MAX_VALUE)
        );
        desktopLayout.setVerticalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );

        getContentPane().add(desktop, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 880, 700));

        jPanel4.setBackground(new java.awt.Color(255, 153, 0));
        jPanel4.setToolTipText("Quantidade de Turmas");

        txtTurmas.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        txtTurmas.setForeground(new java.awt.Color(255, 255, 255));
        txtTurmas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTurmas.setText("000");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("TURMAS");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTurmas, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTurmas, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 360, 160, 120));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel3.setText("SIED");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 660, 60, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/GRADUACAO.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 550, 130, 110));

        jPanel3.setBackground(new java.awt.Color(0, 153, 153));
        jPanel3.setForeground(new java.awt.Color(0, 153, 0));
        jPanel3.setToolTipText("Quantidade de Professores");

        txtProfessores.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        txtProfessores.setForeground(new java.awt.Color(255, 255, 255));
        txtProfessores.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtProfessores.setText("00");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("PROFESSORES");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                    .addComponent(txtProfessores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtProfessores, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 230, 160, 120));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/fundo-azul-e-branco-abstrato (1).jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 280, 220, 420));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtData.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtData.setText("Data");
        jPanel1.add(txtData, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 120, -1));

        jPanel2.setBackground(new java.awt.Color(51, 153, 255));
        jPanel2.setToolTipText("Quantidade de Alunos");
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtAluno.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        txtAluno.setForeground(new java.awt.Color(255, 255, 255));
        txtAluno.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtAluno.setText("0");
        jPanel2.add(txtAluno, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 140, 50));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("ALUNOS");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 140, 20));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 160, 120));

        txtUsuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtUsuario.setText("User");
        jPanel1.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 0, 220, 280));

        jMenuBar1.setToolTipText("");

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/cadastrar.png"))); // NOI18N
        jMenu1.setText("Cadastro");

        menuCadUser.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.ALT_MASK));
        menuCadUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icone usuario.png"))); // NOI18N
        menuCadUser.setText("Usuários");
        menuCadUser.setEnabled(false);
        menuCadUser.setSelected(true);
        menuCadUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCadUserActionPerformed(evt);
            }
        });
        jMenu1.add(menuCadUser);

        menuEscola.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.ALT_MASK));
        menuEscola.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icone escola.png"))); // NOI18N
        menuEscola.setText("Escolas");
        menuEscola.setEnabled(false);
        menuEscola.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuEscolaActionPerformed(evt);
            }
        });
        jMenu1.add(menuEscola);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icone turma.png"))); // NOI18N
        jMenuItem5.setText("Turmas");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icone professor.png"))); // NOI18N
        jMenuItem6.setText("Professores");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icone aluno.png"))); // NOI18N
        jMenuItem2.setText("Alunos");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/documento.png"))); // NOI18N
        jMenu4.setText("Relatório");

        menuRel.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.ALT_MASK));
        menuRel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/relatorio (1).png"))); // NOI18N
        menuRel.setText("Relatório");
        menuRel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRelActionPerformed(evt);
            }
        });
        jMenu4.add(menuRel);

        jMenuBar1.add(jMenu4);

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/relatorio-de-negocios.png"))); // NOI18N
        jMenu5.setText("Ocorrência");

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/escrito.png"))); // NOI18N
        jMenuItem4.setText("Ocorrências");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem4);

        jMenuBar1.add(jMenu5);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/mais-informacoes.png"))); // NOI18N
        jMenu3.setText("Opções");

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_J, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/ajudando.png"))); // NOI18N
        jMenuItem7.setText("Ajuda");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem7);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F8, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Sobre icon.png"))); // NOI18N
        jMenuItem3.setText("Sobre");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/botao-x.png"))); // NOI18N
        jMenuItem1.setText("Sair");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        Date data = new Date();
        DateFormat formatador = DateFormat.getDateInstance(DateFormat.SHORT);
        txtData.setText(formatador.format(data));
        
    }//GEN-LAST:event_formWindowActivated

    private void menuCadUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCadUserActionPerformed
        TelaUsuario telausuario = new TelaUsuario();
        telausuario.setVisible(true);
        desktop.add(telausuario);
    }//GEN-LAST:event_menuCadUserActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
       int sair = JOptionPane.showConfirmDialog(null, "Deseja realmente sair?", "Atenção", JOptionPane.YES_NO_OPTION);
       
        if(sair == JOptionPane.YES_OPTION){
            this.dispose();
         TelaLogin login = new TelaLogin();
         login.setVisible(true);
         
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        TelaSobre telasobre = new TelaSobre();
        telasobre.setVisible(true);
        
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        TelaAluno telaaluno = new TelaAluno();
        telaaluno.setVisible(true);
        desktop.add(telaaluno);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void menuEscolaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuEscolaActionPerformed
        TelaEscola telaescola= new TelaEscola();
        telaescola.setVisible(true);
        desktop.add(telaescola);
    }//GEN-LAST:event_menuEscolaActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
       TelaTurma telaturma = new TelaTurma();
        telaturma.setVisible(true);
        desktop.add(telaturma);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
      TelaProfessor telaprofessor = new TelaProfessor();
      telaprofessor.setVisible(true);
      desktop.add(telaprofessor);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void menuRelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRelActionPerformed
    imprimir();
        
    }//GEN-LAST:event_menuRelActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        TelaOcorrencias  telaocorrencia = new TelaOcorrencias();
        telaocorrencia.setVisible(true);
        desktop.add(telaocorrencia);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        TelaPrototipo  telaprototipo = new TelaPrototipo();
        telaprototipo.setVisible(true);
        desktop.add(telaprototipo);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

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
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktop;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    public static javax.swing.JMenuItem menuCadUser;
    public static javax.swing.JMenuItem menuEscola;
    private javax.swing.JMenuItem menuRel;
    public static javax.swing.JLabel txtAluno;
    public static javax.swing.JLabel txtData;
    public static javax.swing.JLabel txtProfessores;
    public static javax.swing.JLabel txtTurmas;
    public static javax.swing.JLabel txtUsuario;
    // End of variables declaration//GEN-END:variables
}
