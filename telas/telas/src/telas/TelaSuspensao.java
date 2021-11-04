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

public class TelaSuspensao extends javax.swing.JFrame {

        Connection  conexao = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
    
    public TelaSuspensao() {
        initComponents();
        conexao = ModuloConexao.connector();
    }

    private void pesquisar(){
        
        String sql = "select * from tb_aluno where nome_aluno like ?";
        
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
        txtDataInicio.setText(tblAluno.getModel().getValueAt(setar,4).toString());
        txtDataFim.setText(tblAluno.getModel().getValueAt(setar,5).toString());
        txtData.setText(null);
        txtAcontecimento.setText(null);
        txtMotivo.setText(null);
     
     }
     private void adicionar(){
         
         String sql = "insert into susp_aluno(motivos,data_acontecimento,suspensao,data_inicio_susp,data_fim_susp,id_aluno) values(?,?,?,?,?,?)";
         
         try{
             pst = conexao.prepareStatement(sql);
             pst.setString(1, txtMotivo.getText());
             pst.setString(2, txtData.getText());
             pst.setString(3, txtAcontecimento.getText());
             pst.setString(4, txtDataInicio.getText());
             pst.setString(5, txtDataFim.getText());
             pst.setString(6, txtIdAluno.getText());
             if((txtNome.getText().isEmpty()) || (txtData.getText().isEmpty()) ||(txtAcontecimento.getText().isEmpty()) ||(txtMotivo.getText().isEmpty())||(txtDataInicio.getText().isEmpty())||(txtDataFim.getText().isEmpty())){
                 
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
             if((txtDataInicio.getText().isEmpty())){
                 txtDataInicio.setBorder(new LineBorder(Color.RED, 1));
                 txtDataInicio.requestFocus();
             }
             if((txtDataFim.getText().isEmpty())){
                 txtDataFim.setBorder(new LineBorder(Color.RED, 1));
                 txtDataFim.requestFocus();
             }
             }else{
                 int adicionado = pst.executeUpdate();
                 
                 if(adicionado > 0){
                     JOptionPane.showMessageDialog(null, "Suspensão criada com sucesso!");
                     
                     txtNome.setText(null);
                     txtData.setText(null);
                     txtDataInicio.setText(null);
                     txtDataFim.setText(null);
                     txtAcontecimento.setText(null);
                     txtIdAluno.setText(null);
                     txtMotivo.setText(null);
                     txtAluno.setText(null);
                     txtSobrenome.setText(null);
                     txtIdSuspensao.setText(null);
                    txtAcontecimento.setBorder(new LineBorder(null, 1));
                    txtMotivo.setBorder(new LineBorder(null, 1));
                    txtData.setBorder(new LineBorder(null, 1));
                    txtDataInicio.setBorder(new LineBorder(null, 1));
                    txtDataFim.setBorder(new LineBorder(null, 1));
                 }
             }
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
     }
     
    private void pesquisar_rel(){
        
        //String num_os = JOptionPane.showInputDialog("Número da Suspensão");
        
       // String sql = "select * from susp_aluno where id_sup_aluno=" + num_os;
        
        String sql = "select * from susp_aluno where id_aluno=?";
        
        
        try{
            
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtIdAluno.getText());
            rs = pst.executeQuery();
            
            if(rs.next()){
                
                txtIdSuspensao.setText(rs.getString(1));
                txtMotivo.setText(rs.getString(2));
                txtData.setText(rs.getString(3));
                txtAcontecimento.setText(rs.getString(4));
                txtDataInicio.setText(rs.getString(5));
                txtDataFim.setText(rs.getString(6));
            
            }else{
                txtIdSuspensao.setText(null);
                txtData.setText(null);
                txtDataInicio.setText(null);
                txtDataFim.setText(null);
                txtAcontecimento.setText(null);
                txtMotivo.setText(null);
                JOptionPane.showMessageDialog(null, "Este aluno não possui suspensão!");
            
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void editar(){
        
        String sql = "update susp_aluno set motivos =?,data_acontecimento =?,suspensao =?,data_inicio_susp =?,data_fim_susp =?,id_aluno =? where id_sup_aluno =?";
    
        try{
            
        pst = conexao.prepareStatement(sql);
        pst.setString(1, txtMotivo.getText());
        pst.setString(2, txtData.getText());
        pst.setString(3, txtAcontecimento.getText());
        pst.setString(4, txtDataInicio.getText());
        pst.setString(5, txtDataFim.getText());
        pst.setString(6, txtIdAluno.getText());
        pst.setString(7, txtIdSuspensao.getText());
        
        if((txtMotivo.getText().isEmpty()) || (txtData.getText().isEmpty()) || (txtAcontecimento.getText().isEmpty()) || (txtIdAluno.getText().isEmpty()) || (txtIdSuspensao.getText().isEmpty())|| (txtDataInicio.getText().isEmpty())|| (txtDataFim.getText().isEmpty())){
            
            JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");
         
        //Código que verifica se tem algum campo sem informação, se tiver
        //a borda automaticamente ficará vermelha, indicando o campo que está 
        //faltando ser preenchido.
        if((txtMotivo.getText().isEmpty())){
            
            txtMotivo.setBorder(new LineBorder(Color.RED, 1));
            txtMotivo.requestFocus();
       }

        if((txtAcontecimento.getText().isEmpty())){
            txtAcontecimento.setBorder(new LineBorder(Color.RED, 1));
            txtAcontecimento.requestFocus();
        }
        
        if((txtData.getText().isEmpty())){
          txtData.setBorder(new LineBorder(Color.RED, 1));
          txtData.requestFocus();
         
        }
        if((txtDataInicio.getText().isEmpty())){
                 txtDataInicio.setBorder(new LineBorder(Color.RED, 1));
                 txtDataInicio.requestFocus();
             }
             if((txtDataFim.getText().isEmpty())){
                 txtDataFim.setBorder(new LineBorder(Color.RED, 1));
                 txtDataFim.requestFocus();
             }
        }
        else{
            int editado = pst.executeUpdate();
            
            // Se editado for maior que 0, o sistema dá continuidade e edita os
            //dados no banco de dados, além de resetar a cor da borda.
            if(editado >0){
            JOptionPane.showMessageDialog(null, "Advertência atualizada com sucesso!");

            txtData.setBorder(new LineBorder(null, 1));
        txtAcontecimento.setBorder(new LineBorder(null, 1));
        txtDataInicio.setBorder(new LineBorder(null, 1));
        txtDataFim.setBorder(new LineBorder(null, 1));
        txtMotivo.setBorder(new LineBorder(null, 1));
             
        }
        }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void remover(){
        
        String sql = "delete from susp_aluno where id_sup_aluno =?";
        
        try{
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtIdSuspensao.getText());

            int removido = pst.executeUpdate();
            
            if(removido > 0){
                
                JOptionPane.showMessageDialog(null, "SUSPENSÃO REMOVIDA COM SUCESSO!");
                
                txtAcontecimento.setText(null);
                txtMotivo.setText(null);
                txtIdAluno.setText(null);
                txtSobrenome.setText(null);
                txtData.setText(null);
                txtDataInicio.setText(null);
                txtDataFim.setText(null);
                txtNome.setText(null);
                txtIdSuspensao.setText(null);
                txtData.setBorder(new LineBorder(null, 1));
                txtAcontecimento.setBorder(new LineBorder(null, 1));
                txtDataInicio.setBorder(new LineBorder(null, 1));
                txtDataFim.setBorder(new LineBorder(null, 1));
                txtMotivo.setBorder(new LineBorder(null, 1));
            }
        }catch(Exception e){
            
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void limpar(){
        
        txtNome.setText(null);
        txtAluno.setText(null);
        txtData.setText(null);
        txtDataInicio.setText(null);
        txtDataFim.setText(null);
        txtIdAluno.setText(null);
        txtIdSuspensao.setText(null);
        txtMotivo.setText(null);
        txtSobrenome.setText(null);
        txtAcontecimento.setText(null);
        tblAluno.setModel(null);
        btAdicionar.setEnabled(true);
        btEditar.setEnabled(true);
        btRemover.setEnabled(true);
        txtData.setBorder(new LineBorder(null, 1));
        txtAcontecimento.setBorder(new LineBorder(null, 1));
        txtDataInicio.setBorder(new LineBorder(null, 1));
        txtDataFim.setBorder(new LineBorder(null, 1));
        txtMotivo.setBorder(new LineBorder(null, 1));
    }
    
    private void limpar_bordas(){
        txtData.setBorder(new LineBorder(null, 1));
        txtAcontecimento.setBorder(new LineBorder(null, 1));
        txtDataInicio.setBorder(new LineBorder(null, 1));
        txtDataFim.setBorder(new LineBorder(null, 1));
        txtMotivo.setBorder(new LineBorder(null, 1));
    }
    
    private void imprimir() {
            
     // imprimindo uma suspensão
     int confirma = JOptionPane.showConfirmDialog(null, "Confirma a impressão desta Suspensão?", "Atenção", JOptionPane.YES_NO_OPTION);
     if (confirma == JOptionPane.YES_OPTION) {
    
     try {
         
     HashMap filtro = new HashMap();
     filtro.put("id_sup_aluno", Integer.parseInt(txtIdSuspensao.getText()));
     JasperReport report = JasperCompileManager.compileReport("C:\\relatorios\\SuspensaoAluno.jrxml");
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

        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btAdicionar = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtDataInicio = new javax.swing.JTextField();
        txtData = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        btEditar = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAcontecimento = new javax.swing.JTextArea();
        btRemover = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtIdAluno = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtIdSuspensao = new javax.swing.JTextField();
        txtAluno = new javax.swing.JTextField();
        btPesquisar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAluno = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtSobrenome = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtDataFim = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtMotivo = new javax.swing.JTextField();
        btImprimir = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Suspensão");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Limpar.png"))); // NOI18N
        jLabel12.setToolTipText("Limpar Campos");
        jLabel12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 590, 50, 60));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Caixinha vazia.png"))); // NOI18N
        jLabel11.setToolTipText("Limpar Campos");
        jLabel11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 560, 90, 90));

        btAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Adicionar.png"))); // NOI18N
        btAdicionar.setToolTipText("Adicionar Suspensão ");
        btAdicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btAdicionar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btAdicionarMouseClicked(evt);
            }
        });
        getContentPane().add(btAdicionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 560, -1, 90));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setText("Data de Inicio da Susp.");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 220, 30));

        txtDataInicio.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDataInicio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(txtDataInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 340, 160, 30));

        txtData.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtData.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(txtData, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 300, 160, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("Data do Ocorrido ");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 170, 30));

        btEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Editar.png"))); // NOI18N
        btEditar.setToolTipText("Editar Suspensão");
        btEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btEditar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btEditarMouseClicked(evt);
            }
        });
        getContentPane().add(btEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 560, 70, 90));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel1.setText("Suspensão");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 13, 130, 33));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Observações");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 410, -1, -1));

        txtAcontecimento.setColumns(20);
        txtAcontecimento.setRows(5);
        txtAcontecimento.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane2.setViewportView(txtAcontecimento);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 900, 100));

        btRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Excluir.png"))); // NOI18N
        btRemover.setToolTipText("Remover Suspensão");
        btRemover.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btRemover.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btRemoverMouseClicked(evt);
            }
        });
        getContentPane().add(btRemover, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 560, -1, 90));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Id da Susp.");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 60, 110, 32));

        txtIdAluno.setEditable(false);
        txtIdAluno.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtIdAluno.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(txtIdAluno, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 20, 150, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Id do Aluno");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 20, 110, 30));

        txtIdSuspensao.setEditable(false);
        txtIdSuspensao.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtIdSuspensao.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(txtIdSuspensao, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 60, 147, 30));

        txtAluno.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtAluno.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtAluno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAlunoKeyPressed(evt);
            }
        });
        jPanel1.add(txtAluno, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 62, 360, 33));

        btPesquisar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Pesquisar(pequeno).png"))); // NOI18N
        btPesquisar.setText("Pesquisar");
        btPesquisar.setToolTipText("Pesquisar Aluno");
        btPesquisar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 105, 900, 100));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Nome do Aluno");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 213, 170, 40));

        txtNome.setEditable(false);
        txtNome.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtNome.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });
        jPanel1.add(txtNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 220, 340, 33));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Sobrenome do Aluno");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 210, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText("Sobrenome do Aluno");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 252, 200, 40));

        txtSobrenome.setEditable(false);
        txtSobrenome.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtSobrenome.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(txtSobrenome, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 260, 340, 30));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel14.setText("Data Fim");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 222, -1, 30));

        txtDataFim.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDataFim.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtDataFim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDataFimActionPerformed(evt);
            }
        });
        jPanel1.add(txtDataFim, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 220, 190, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Motivo");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 260, 80, 30));

        txtMotivo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtMotivo.setPreferredSize(new java.awt.Dimension(6, 23));
        txtMotivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMotivoActionPerformed(evt);
            }
        });
        jPanel1.add(txtMotivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 260, 190, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 300));

        btImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/print.png"))); // NOI18N
        btImprimir.setToolTipText("Gerar Suspensão");
        btImprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btImprimir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btImprimirMouseClicked(evt);
            }
        });
        getContentPane().add(btImprimir, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 560, 74, 90));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/background.png"))); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 920, 500));

        setSize(new java.awt.Dimension(938, 702));
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

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void btAdicionarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btAdicionarMouseClicked
        adicionar();
    }//GEN-LAST:event_btAdicionarMouseClicked

    private void txtMotivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMotivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMotivoActionPerformed

    private void btEditarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btEditarMouseClicked
        editar();
    }//GEN-LAST:event_btEditarMouseClicked

    private void btImprimirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btImprimirMouseClicked
       imprimir();
    }//GEN-LAST:event_btImprimirMouseClicked

    private void btRemoverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btRemoverMouseClicked
        remover();
        
    }//GEN-LAST:event_btRemoverMouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        limpar();
    }//GEN-LAST:event_jLabel12MouseClicked

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
       limpar();
    }//GEN-LAST:event_jLabel11MouseClicked

    private void txtDataFimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDataFimActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDataFimActionPerformed

    private void txtAlunoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAlunoKeyPressed
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            pesquisar();
       }
    }//GEN-LAST:event_txtAlunoKeyPressed

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
            java.util.logging.Logger.getLogger(TelaSuspensao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaSuspensao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaSuspensao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaSuspensao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaSuspensao().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btAdicionar;
    private javax.swing.JLabel btEditar;
    private javax.swing.JLabel btImprimir;
    private javax.swing.JButton btPesquisar;
    private javax.swing.JLabel btRemover;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JTextField txtDataFim;
    private javax.swing.JTextField txtDataInicio;
    private javax.swing.JTextField txtIdAluno;
    private javax.swing.JTextField txtIdSuspensao;
    private javax.swing.JTextField txtMotivo;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtSobrenome;
    // End of variables declaration//GEN-END:variables
}
