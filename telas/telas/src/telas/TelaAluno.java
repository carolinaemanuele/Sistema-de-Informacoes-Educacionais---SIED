package telas;
import java.sql.*;
import dal.ModuloConexao;
import java.awt.Color;
import java.text.SimpleDateFormat;
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

public class TelaAluno extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public TelaAluno() {
        initComponents();
        
        conexao = ModuloConexao.connector();
    }
            
    private void adicionar(){
                
        String sql = "insert into tb_aluno(cpf_aluno,nome_aluno,sobrenome_aluno,data_nasc_aluno,turno_aluno,telefone_aluno,email_aluno,rua_aluno,numero,bairro,cidade,cep,estado,matricula_aluno,id_prof,id_turma) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try{  
        pst = conexao.prepareStatement(sql);
        //pst.setString(1,  txtId.getText());
        pst.setString(1,  txtCpf.getText());
        pst.setString(2,  txtNome.getText());
        pst.setString(3,  txtSobrenome.getText());
        //pst.setString(4,  txtDataNasc2.getText());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat ("yyyy-MM-dd");
        String dataFormatada = simpleDateFormat.format(this.jDateNasc.getDate());
        pst.setString(4, dataFormatada);
        pst.setString(5,  cboTurno.getSelectedItem().toString());
        pst.setString(6,  txtFone.getText());
        pst.setString(7,  txtEmail.getText());
        pst.setString(8,  txtRua.getText());
        pst.setString(9,  txtNumero.getText());
        pst.setString(10, txtBairro.getText());
        pst.setString(11, txtCidade.getText());
        pst.setString(12, txtCep.getText());
        pst.setString(13, txtEstado.getText());
        pst.setString(14, txtMatricula.getText());
        pst.setString(15, txtIdProfessor.getText());
        pst.setString(16, txtIdTurma.getText());
       
        
        if((txtNome.getText().isEmpty()) || (txtCpf.getText().isEmpty()) || (txtSobrenome.getText().isEmpty()) ||(cboTurno.getSelectedItem().toString().isEmpty()) ||(txtFone.getText().isEmpty()) ||(txtBairro.getText().isEmpty()) ||(txtRua.getText().isEmpty()) ||(txtNumero.getText().isEmpty())||(txtCidade.getText().isEmpty()) ||(txtCep.getText().isEmpty())||(txtEstado.getText().isEmpty()) ||(txtMatricula.getText().isEmpty())) {
               JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
                    if((txtCpf.getText().length())>3){
                        
                        txtCpf.setBorder(new LineBorder(Color.RED, 1));
                        txtCpf.requestFocus();
                        txtObrigatorio.setText("Preencha todos os campos obrigatórios");
                        
                    }
                    if((txtNome.getText().isEmpty())){
                        
                        txtNome.setBorder(new LineBorder(Color.RED, 1));
                        txtNome.requestFocus();
                        txtObrigatorio.setText("Preencha todos os campos obrigatórios");
                    }
                    if((txtSobrenome.getText().isEmpty())){
                        
                        txtSobrenome.setBorder(new LineBorder(Color.RED, 1));
                        txtSobrenome.requestFocus(); 
                        txtObrigatorio.setText("Preencha todos os campos obrigatórios");
                    }
                    if((txtFone.getText().length())>4){
                        
                        txtFone.setBorder(new LineBorder(Color.RED, 1));
                        txtFone.requestFocus();
                        txtObrigatorio.setText("Preencha todos os campos obrigatórios");
                    }
                    if((txtCep.getText().length())>1){
                        
                        txtCep.setBorder(new LineBorder(Color.RED, 1));
                        txtCep.requestFocus(); 
                        txtObrigatorio.setText("Preencha todos os campos obrigatórios");
                    }
                    if((txtNumero.getText().isEmpty())){
                        
                        txtNumero.setBorder(new LineBorder(Color.RED, 1));
                        txtNumero.requestFocus(); 
                        txtObrigatorio.setText("Preencha todos os campos obrigatórios");
                    }
                    if((txtRua.getText().isEmpty())){
                        
                        txtRua.setBorder(new LineBorder(Color.RED, 1));
                        txtRua.requestFocus(); 
                        txtObrigatorio.setText("Preencha todos os campos obrigatórios");
                    }
                    if((txtBairro.getText().isEmpty())){
                        
                        txtBairro.setBorder(new LineBorder(Color.RED, 1));
                        txtBairro.requestFocus();
                        txtObrigatorio.setText("Preencha todos os campos obrigatórios");
                    }
                    if((txtCidade.getText().isEmpty())){
                        
                        txtCidade.setBorder(new LineBorder(Color.RED, 1));
                        txtCidade.requestFocus(); 
                        txtObrigatorio.setText("Preencha todos os campos obrigatórios");
                    }
                    if((txtEstado.getText().isEmpty())){
                        
                        txtEstado.setBorder(new LineBorder(Color.RED, 1));
                        txtEstado.requestFocus(); 
                        txtObrigatorio.setText("Preencha todos os campos obrigatórios");
                    }
                    if((txtMatricula.getText().isEmpty())){
                        
                        txtMatricula.setBorder(new LineBorder(Color.RED, 1));
                        txtMatricula.requestFocus();
                        txtObrigatorio.setText("Preencha todos os campos obrigatórios");
                    
                    }
                    if((txtIdProfessor.getText().isEmpty())){
                        
                        txtIdProfessor.setBorder(new LineBorder(Color.RED, 1));
                        txtIdProfessor.requestFocus(); 
                        txtObrigatorio.setText("Preencha todos os campos obrigatórios");
                    }
                    if((txtIdTurma.getText().isEmpty())){
                        
                        txtIdTurma.setBorder(new LineBorder(Color.RED, 1));
                        txtIdTurma.requestFocus(); 
                        txtObrigatorio.setText("Preencha todos os campos obrigatórios");
                    }
        
        }else{
            int adicionado = pst.executeUpdate();
            
            if(adicionado>0){
            
            JOptionPane.showMessageDialog(null, "Aluno adicionado com sucesso!");   
                    
                    txtCpf.setText(null);
                    txtNome.setText(null);
                    txtSobrenome.setText(null);
                    //txtDataNasc2.setText(null);
                    jDateNasc.setDate(null);
                    
                    cboTurno.setSelectedItem(null);
                    txtNomeTurma.setText(null);
                    txtFone.setText(null);
                    txtEmail.setText(null);
                    txtRua.setText(null);
                    txtNumero.setText(null);
                    txtBairro.setText(null);
                    txtCidade.setText(null);
                    txtCep.setText(null);
                    txtEstado.setText(null);
                    txtMatricula.setText(null);
                    txtIdProfessor.setText(null);
                    txtIdTurma.setText(null);
                    txtNomeProf.setText(null);
                   
        } 
        }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void pesquisar(){
     
        String sql = "select id_aluno as Id,cpf_aluno as CPF,nome_aluno as Nome,sobrenome_aluno as Sobrenome,turno_aluno Turno,matricula_aluno as Matricula,id_prof 'Id do(a) Profª',id_turma 'Id da Turma' from tb_aluno where nome_aluno  like ?";
        
        try{
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtPesquisar.getText() + "%");
            rs = pst.executeQuery();
            tblAluno.setModel(DbUtils.resultSetToTableModel(rs));
            if(rs.next())
                setarOutros();
            if(rs.next())
                consultar();
            if(rs.next())
                consultar_turma();
            

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
     private void setar_campos() {
         
        txtCpf.setText(null);
        txtCpf.setForeground(Color.black);
        txtFone.setText(null);
        txtFone.setForeground(Color.black);
        txtCep.setText(null);
        txtCep.setForeground(Color.black);
         
        int setar = tblAluno.getSelectedRow();
        txtId.setText(tblAluno.getModel().getValueAt(setar, 0).toString());
        txtCpf.setText(tblAluno.getModel().getValueAt(setar, 1).toString());
        txtNome.setText(tblAluno.getModel().getValueAt(setar, 2).toString());
        txtSobrenome.setText(tblAluno.getModel().getValueAt(setar, 3).toString());
        cboTurno.setSelectedItem(tblAluno.getModel().getValueAt(setar, 4).toString());
        txtMatricula.setText(tblAluno.getModel().getValueAt(setar, 5).toString());
        txtIdProfessor.setText(tblAluno.getModel().getValueAt(setar, 6).toString());
        txtIdTurma.setText(tblAluno.getModel().getValueAt(setar, 7).toString());
        btAdicionar.setEnabled(false);
        txtNomeProf.setText(null);
        txtNomeTurma.setText(null);
        setarOutros();
        consultar();
        consultar_turma();
        
     }
    private void setarOutros(){

    String sql = "select * from tb_aluno where id_aluno = ?";

    try{
    pst = conexao.prepareStatement(sql);
    pst.setString(1, txtId.getText());
    rs = pst.executeQuery();

    if(rs.next()){
    //txtDataNasc2.setText(rs.getString(5));
    jDateNasc.setDate(rs.getDate(5));
    txtFone.setText(rs.getString(7));
    txtEmail.setText(rs.getString(8));
    txtRua.setText(rs.getString(9));
    txtNumero.setText(rs.getString(10));
    txtBairro.setText(rs.getString(11));
    txtCidade.setText(rs.getString(12));
    txtCep.setText(rs.getString(13));
    txtEstado.setText(rs.getString(14));
}
    }catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
    }
        }
     private void consultar() {
        String sql = "select * from tb_professor where id_prof =?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtIdProfessor.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                txtNomeProf.setText(rs.getString(3));

            } else {
                txtNomeProf.setText(null);
                JOptionPane.showMessageDialog(null, "Professor não cadastrado!");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
      private void consultar_turma() {
        String sql = "select * from tb_turma where id_turma =?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtIdTurma.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                txtNomeTurma.setText(rs.getString(2));

            } else {
                txtNomeTurma.setText(null);
                JOptionPane.showMessageDialog(null, "Turma não encontrada!");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
      private void editar(){
        
        String sql = "update tb_aluno set cpf_aluno =?,nome_aluno =?,sobrenome_aluno =?,data_nasc_aluno =?,turno_aluno =?,telefone_aluno =?,email_aluno =?,rua_aluno =?,numero =?,bairro =?,cidade =?,cep =?,estado =?,matricula_aluno =?,id_prof =?,id_turma =? where id_aluno =?";
     try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtCpf.getText());
                pst.setString(2, txtNome.getText());
                pst.setString(3, txtSobrenome.getText());
               // pst.setString(4, txtDataNasc2.getText());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat ("yyyy-MM-dd");
                String dataFormatada = simpleDateFormat.format(this.jDateNasc.getDate());
                pst.setString(4,dataFormatada);
                pst.setString(5, cboTurno.getSelectedItem().toString());
                pst.setString(6, txtFone.getText());
                pst.setString(7, txtEmail.getText());
                pst.setString(8, txtRua.getText());
                pst.setString(9, txtNumero.getText());
                pst.setString(10, txtBairro.getText());
                pst.setString(11, txtCidade.getText());
                pst.setString(12, txtCep.getText());
                pst.setString(13, txtEstado.getText());
                pst.setString(14, txtMatricula.getText());
                pst.setString(15, txtIdProfessor.getText());
                pst.setString(16, txtIdTurma.getText());
                pst.setString(17, txtId.getText());
            
            
           if((txtId.getText().isEmpty()) || (txtCpf.getText().isEmpty()) || (txtNome.getText().isEmpty())|| (txtSobrenome.getText().isEmpty())|| (txtFone.getText().isEmpty()) || (txtRua.getText().isEmpty())|| (txtNumero.getText().isEmpty())|| (txtBairro.getText().isEmpty())|| (txtCidade.getText().isEmpty())|| (txtCep.getText().isEmpty())|| (txtEstado.getText().isEmpty())|| (txtMatricula.getText().isEmpty())|| (txtIdTurma.getText().isEmpty())|| (txtIdProfessor.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
                
                if((txtCpf.getText().length())>3){
                        
                        txtCpf.setBorder(new LineBorder(Color.RED, 1));
                        txtCpf.requestFocus();
                        txtObrigatorio.setText("Preencha todos os campos obrigatórios");
                        
                    }
                    if((txtNome.getText().isEmpty())){
                        
                        txtNome.setBorder(new LineBorder(Color.RED, 1));
                        txtNome.requestFocus();
                        txtObrigatorio.setText("Preencha todos os campos obrigatórios");
                        
                    }
                    if((txtSobrenome.getText().isEmpty())){
                        
                        txtSobrenome.setBorder(new LineBorder(Color.RED, 1));
                        txtSobrenome.requestFocus(); 
                        txtObrigatorio.setText("Preencha todos os campos obrigatórios");
                    }
                    //if((txtDataNasc2.getText().length())>4){
                        
                        //txtDataNasc2.setBorder(new LineBorder(Color.RED, 1));
                        //txtDataNasc2.requestFocus(); 
                        //txtObrigatorio.setText("Preencha todos os campos obrigatórios");
                    //}
                    if((txtFone.getText().length())>4){
                        
                        txtFone.setBorder(new LineBorder(Color.RED, 1));
                        txtFone.requestFocus(); 
                        txtObrigatorio.setText("Preencha todos os campos obrigatórios");
                    }
                    if((txtCep.getText().length())>1){
                        
                        txtCep.setBorder(new LineBorder(Color.RED, 1));
                        txtCep.requestFocus(); 
                        txtObrigatorio.setText("Preencha todos os campos obrigatórios");
                    }
                    if((txtNumero.getText().isEmpty())){
                        
                        txtNumero.setBorder(new LineBorder(Color.RED, 1));
                        txtNumero.requestFocus(); 
                        txtObrigatorio.setText("Preencha todos os campos obrigatórios");
                    }
                    if((txtRua.getText().isEmpty())){
                        
                        txtRua.setBorder(new LineBorder(Color.RED, 1));
                        txtRua.requestFocus(); 
                        txtObrigatorio.setText("Preencha todos os campos obrigatórios");
                    }
                    if((txtBairro.getText().isEmpty())){
                        
                        txtBairro.setBorder(new LineBorder(Color.RED, 1));
                        txtBairro.requestFocus(); 
                        txtObrigatorio.setText("Preencha todos os campos obrigatórios");
                    }
                    if((txtCidade.getText().isEmpty())){
                        
                        txtCidade.setBorder(new LineBorder(Color.RED, 1));
                        txtCidade.requestFocus(); 
                        txtObrigatorio.setText("Preencha todos os campos obrigatórios");
                    }
                    if((txtEstado.getText().isEmpty())){
                        
                        txtEstado.setBorder(new LineBorder(Color.RED, 1));
                        txtEstado.requestFocus(); 
                        txtObrigatorio.setText("Preencha todos os campos obrigatórios");
                    }
                    if((txtMatricula.getText().isEmpty())){
                        
                        txtMatricula.setBorder(new LineBorder(Color.RED, 1));
                        txtMatricula.requestFocus(); 
                        txtObrigatorio.setText("Preencha todos os campos obrigatórios");
                    
                    }
                    if((txtIdTurma.getText().isEmpty())){
                        
                        txtIdTurma.setBorder(new LineBorder(Color.RED, 1));
                        txtIdTurma.requestFocus();
                        txtObrigatorio.setText("Preencha todos os campos obrigatórios");
                    }
                    
            } else {
                int editado = pst.executeUpdate();

                if (editado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados do aluno atualizados!");
                   
                    txtCpf.setText(null);
                    txtNome.setText(null);
                    txtSobrenome.setText(null);
                    jDateNasc.setDate(null);
                    //txtDataNasc2.setText(null);
                    cboTurno.setSelectedItem(null);
                    txtNomeTurma.setText(null);
                    txtFone.setText(null);
                    txtEmail.setText(null);
                    txtRua.setText(null);
                    txtNumero.setText(null);
                    txtBairro.setText(null);
                    txtCidade.setText(null);
                    txtCep.setText(null);
                    txtEstado.setText(null);
                    txtMatricula.setText(null);
                    txtIdProfessor.setText(null);
                    txtIdTurma.setText(null);
                    txtNomeProf.setText(null);
                }
            }

        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
      private void limpar(){
        
        txtId.setText(null);
        txtIdProfessor.setText(null);
        txtNome.setText(null);
        //txtDataNasc2.setText(null);
        jDateNasc.setDate(null);
        cboTurno.setSelectedItem(null);
        txtRua.setText(null);
        txtCep.setText(null);
        txtPesquisar.setText(null);
        txtCidade.setText(null);
        txtEmail.setText(null);
        txtNomeTurma.setText(null);
        txtNumero.setText(null);
        txtNomeProf.setText(null);
        txtBairro.setText(null);
        txtEstado.setText(null);
        txtFone.setText(null);
        txtMatricula.setText(null);
        txtCpf.setText(null);
        txtIdTurma.setText(null);
        txtSobrenome.setText(null);
        btAdicionar.setEnabled(true);
        btPesquisar.setEnabled(true);
        //btPesquisarAluno.setEnabled(true);
        tblAluno.setVisible(true);
        ((DefaultTableModel) tblAluno.getModel()).setRowCount(0);
    }
      
      private void remover(){
          
          String sql = "delete from tb_aluno where id_aluno =?";
          
          try{
              pst = conexao.prepareStatement(sql);
              pst.setString(1, txtId.getText());
              
              int apagado = pst.executeUpdate();
              
              if (apagado > 0){
                  JOptionPane.showMessageDialog(null, "Aluno removido com sucesso!");
                
                  txtId.setText(null);
                txtIdProfessor.setText(null);
                txtNome.setText(null);
                //txtDataNasc2.setText(null);
                jDateNasc.setDate(null);
                cboTurno.setSelectedItem(null);
                txtRua.setText(null);
                txtCep.setText(null);
                txtPesquisar.setText(null);
                txtCidade.setText(null);
                txtEmail.setText(null);
                txtNomeTurma.setText(null);
                txtNumero.setText(null);
                txtNomeProf.setText(null);
                txtBairro.setText(null);
                txtEstado.setText(null);
                txtFone.setText(null);
                txtMatricula.setText(null);
                txtCpf.setText(null);
                txtIdTurma.setText(null);
                txtSobrenome.setText(null);
                tblAluno.setVisible(true);
        ((DefaultTableModel) tblAluno.getModel()).setRowCount(0);
          }
              
              
              
          }catch(Exception e){
              JOptionPane.showMessageDialog(null, e);
          }
      }
      
       private void imprimir(){
           try{
       JasperReport report = JasperCompileManager.compileReport("C:\\Users\\Carlos\\JaspersoftWorkspace\\MyReports\\ProfessorAluno.jrxml");
        
        JasperPrint print = JasperFillManager.fillReport(report, null, conexao);
        
        JasperViewer viewer = new JasperViewer(print, false);
        viewer.setVisible(true);
        viewer.setTitle("Lista de Professores e Alunos");
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
     }
       private void soma_alunos(){
            
            String sql = "select count(*) from tb_aluno";
            
            try{
                pst = conexao.prepareStatement(sql);
                rs = pst.executeQuery();
                
                if(rs.next()){
                    
                    String soma = rs.getString("count(*)");
                TelaPrincipal.txtAluno.setText(soma);
                }
                
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
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
                JOptionPane.showMessageDialog(null, e);
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
                JOptionPane.showMessageDialog(null, e);
            }
        }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel22 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtSobrenome = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtRua = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtBairro = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtCidade = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtCep = new javax.swing.JFormattedTextField();
        jLabel13 = new javax.swing.JLabel();
        jDateNasc = new com.toedter.calendar.JDateChooser();
        txtEstado = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtCpf = new javax.swing.JFormattedTextField();
        txtFone = new javax.swing.JFormattedTextField();
        btPesquisar = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        txtIdTurma = new javax.swing.JTextField();
        txtNumero = new javax.swing.JTextField();
        txtMatricula = new javax.swing.JTextField();
        txtIdProfessor = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAluno = new javax.swing.JTable();
        txtPesquisar = new javax.swing.JTextField();
        btAdicionar = new javax.swing.JLabel();
        cboTurno = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        btEditar = new javax.swing.JLabel();
        btExcluir = new javax.swing.JLabel();
        txtNomeProf = new javax.swing.JTextField();
        btImprimir = new javax.swing.JLabel();
        txtNomeTurma = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtObrigatorio = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/background.png"))); // NOI18N

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Aluno");
        setPreferredSize(new java.awt.Dimension(880, 700));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Nome");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 60, 25));
        getContentPane().add(txtNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 290, 140, 25));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Sobrenome");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 80, 25));

        txtSobrenome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSobrenomeActionPerformed(evt);
            }
        });
        getContentPane().add(txtSobrenome, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 330, 140, 25));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Data de Nasc.");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, -1, 25));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Turno");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 450, 40, 25));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Telefone");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, 60, 25));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Email (Opcional)");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 100, 25));
        getContentPane().add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 410, 140, 25));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Rua");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 290, 30, 25));
        getContentPane().add(txtRua, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 290, 140, 25));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Nº");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 330, 20, 25));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Bairro");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 370, 40, 25));
        getContentPane().add(txtBairro, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 370, 140, 25));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Cidade");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 410, 50, 25));
        getContentPane().add(txtCidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 410, 140, 25));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("CEP");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 250, 30, 25));

        txtCep.setForeground(new java.awt.Color(255, 255, 255));
        try {
            txtCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCep.setText("");
        txtCep.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCepMouseClicked(evt);
            }
        });
        getContentPane().add(txtCep, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 250, 140, 25));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Estado");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 450, 50, 25));
        getContentPane().add(jDateNasc, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 370, 140, 25));
        getContentPane().add(txtEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 450, 140, 25));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Matrícula");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 250, 60, 25));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("CPF");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 50, 25));

        txtCpf.setForeground(new java.awt.Color(255, 255, 255));
        try {
            txtCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCpf.setText("");
        txtCpf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCpfMouseClicked(evt);
            }
        });
        getContentPane().add(txtCpf, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 250, 140, 25));

        txtFone.setForeground(new java.awt.Color(255, 255, 255));
        try {
            txtFone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtFone.setText("");
        txtFone.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtFoneMouseClicked(evt);
            }
        });
        getContentPane().add(txtFone, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 450, 140, 25));

        btPesquisar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Pesquisar(pequeno).png"))); // NOI18N
        btPesquisar.setText("Pesquisar");
        btPesquisar.setToolTipText("Pesquisar Aluno");
        btPesquisar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarActionPerformed(evt);
            }
        });
        getContentPane().add(btPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 80, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setText("Id da Turma");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 330, 80, 25));
        getContentPane().add(txtIdTurma, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 330, 140, 25));
        getContentPane().add(txtNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 330, 140, 25));
        getContentPane().add(txtMatricula, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 250, 140, 25));
        getContentPane().add(txtIdProfessor, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 290, 140, 25));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("Id do(a) Professorª");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 290, -1, 25));

        tblAluno = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        }
        ;
        tblAluno.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Nome", "Sobrenome", "CPF", "Turno", "Matrícula", "Id do(a) Profª", "Id da Turma"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
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
        if (tblAluno.getColumnModel().getColumnCount() > 0) {
            tblAluno.getColumnModel().getColumn(0).setResizable(false);
            tblAluno.getColumnModel().getColumn(1).setResizable(false);
            tblAluno.getColumnModel().getColumn(2).setResizable(false);
            tblAluno.getColumnModel().getColumn(3).setResizable(false);
            tblAluno.getColumnModel().getColumn(4).setResizable(false);
            tblAluno.getColumnModel().getColumn(5).setResizable(false);
            tblAluno.getColumnModel().getColumn(6).setResizable(false);
            tblAluno.getColumnModel().getColumn(7).setResizable(false);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 820, 100));
        getContentPane().add(txtPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 330, 40));

        btAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Adicionar.png"))); // NOI18N
        btAdicionar.setToolTipText("Adicionar Aluno");
        btAdicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btAdicionar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btAdicionarMouseClicked(evt);
            }
        });
        getContentPane().add(btAdicionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 550, 70, 80));

        cboTurno.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cboTurno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Matutino", "Vespertino", "Integral", "Noturno" }));
        getContentPane().add(cboTurno, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 450, 80, 25));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setText("Nome da Turma");
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 410, -1, 25));

        btEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Editar.png"))); // NOI18N
        btEditar.setToolTipText("Editar dados do Aluno");
        btEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btEditar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btEditarMouseClicked(evt);
            }
        });
        getContentPane().add(btEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 550, 80, 80));

        btExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Excluir.png"))); // NOI18N
        btExcluir.setToolTipText("Excluir Aluno");
        btExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btExcluir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btExcluirMouseClicked(evt);
            }
        });
        getContentPane().add(btExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 550, 80, 80));

        txtNomeProf.setEditable(false);
        txtNomeProf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeProfActionPerformed(evt);
            }
        });
        getContentPane().add(txtNomeProf, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 370, 140, 25));

        btImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/print.png"))); // NOI18N
        btImprimir.setToolTipText("Imprimir");
        btImprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btImprimir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btImprimirMouseClicked(evt);
            }
        });
        getContentPane().add(btImprimir, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 550, 70, 80));

        txtNomeTurma.setEditable(false);
        getContentPane().add(txtNomeTurma, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 410, 140, 25));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setText("Professor(a)");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 370, 80, 25));

        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/limpar-limpo2.png"))); // NOI18N
        jLabel26.setToolTipText("Limpar os campos");
        jLabel26.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 570, 50, 70));

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/fileicon.png"))); // NOI18N
        jLabel25.setToolTipText("Limpar os campos");
        jLabel25.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 550, 80, 80));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Id Aluno");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 90, 60, 25));

        txtId.setEditable(false);
        jPanel1.add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 90, 160, 25));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel24.setText("GERENCIAMENTO DE ALUNOS");
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        txtObrigatorio.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        txtObrigatorio.setForeground(new java.awt.Color(255, 102, 102));
        jPanel1.add(txtObrigatorio, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 30, 180, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 870, 220));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/background.png"))); // NOI18N
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 870, 490));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarActionPerformed
        pesquisar();
        
    }//GEN-LAST:event_btPesquisarActionPerformed

    private void txtSobrenomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSobrenomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSobrenomeActionPerformed

    private void btAdicionarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btAdicionarMouseClicked
       adicionar();
       soma_alunos();
       soma_prof();
       soma_turma();
    }//GEN-LAST:event_btAdicionarMouseClicked

    private void tblAlunoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAlunoMouseClicked
        setar_campos();
    }//GEN-LAST:event_tblAlunoMouseClicked

    private void txtNomeProfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeProfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeProfActionPerformed

    private void btEditarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btEditarMouseClicked
       editar();
       limpar();
    }//GEN-LAST:event_btEditarMouseClicked

    private void btImprimirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btImprimirMouseClicked
        imprimir();
    }//GEN-LAST:event_btImprimirMouseClicked

    private void btExcluirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btExcluirMouseClicked
       remover();
       soma_alunos();
       soma_prof();
       soma_turma();
       
    }//GEN-LAST:event_btExcluirMouseClicked

    private void txtCpfMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCpfMouseClicked
        txtCpf.setText(null);
        txtCpf.setForeground(Color.black);
    }//GEN-LAST:event_txtCpfMouseClicked

    private void txtFoneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFoneMouseClicked
        txtFone.setText(null);
        txtFone.setForeground(Color.black);
    }//GEN-LAST:event_txtFoneMouseClicked

    private void txtCepMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCepMouseClicked
        txtCep.setText(null);
        txtCep.setForeground(Color.black);
    }//GEN-LAST:event_txtCepMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btAdicionar;
    private javax.swing.JLabel btEditar;
    private javax.swing.JLabel btExcluir;
    private javax.swing.JLabel btImprimir;
    private javax.swing.JButton btPesquisar;
    private javax.swing.JComboBox<String> cboTurno;
    private com.toedter.calendar.JDateChooser jDateNasc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblAluno;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JFormattedTextField txtCep;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JFormattedTextField txtCpf;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JFormattedTextField txtFone;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtIdProfessor;
    private javax.swing.JTextField txtIdTurma;
    private javax.swing.JTextField txtMatricula;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtNomeProf;
    private javax.swing.JTextField txtNomeTurma;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JLabel txtObrigatorio;
    private javax.swing.JTextField txtPesquisar;
    private javax.swing.JTextField txtRua;
    private javax.swing.JTextField txtSobrenome;
    // End of variables declaration//GEN-END:variables
}
