package telas;

import dal.ModuloConexao;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class TelaProfessor extends javax.swing.JInternalFrame {

   Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public TelaProfessor() {
        conexao = ModuloConexao.connector();
        initComponents();
    }
     private void pesquisar_professor(){
     
        String sql = "select id_prof as Id,cpf_prof as CPF,nome_prof as Nome,sobrenome_prof as Sobrenome,turno_prof Turno,formacao_prof as 'Formação',matricula_prof as Matricula,id_turma 'Id da Turma' from tb_professor where nome_prof like ?";
        
     try{
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtPesquisar.getText() + "%");
            rs = pst.executeQuery();
            tblProfessor.setModel(DbUtils.resultSetToTableModel(rs));
            if(rs.next())
                setarOutros();
            if(rs.next())
                pesquisar_turma();
            
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
        
        int setar = tblProfessor.getSelectedRow();
        txtIdProfessor.setText(tblProfessor.getModel().getValueAt(setar, 0).toString());
        txtCpf.setText(tblProfessor.getModel().getValueAt(setar, 1).toString());
        txtNome.setText(tblProfessor.getModel().getValueAt(setar, 2).toString());
        txtSobrenome.setText(tblProfessor.getModel().getValueAt(setar, 3).toString());
        cboTurno.setSelectedItem(tblProfessor.getModel().getValueAt(setar, 4).toString());
        txtFormacao.setText(tblProfessor.getModel().getValueAt(setar, 5).toString());
        txtMatricula.setText(tblProfessor.getModel().getValueAt(setar, 6).toString());
        txtIdTurma.setText(tblProfessor.getModel().getValueAt(setar, 7).toString());
        btAdicionar.setEnabled(false);
        txtTurma.setText(null);
        txtTurnoTurma.setText(null);
        setarOutros();
        pesquisar_turma();
        }
        
        private void setarOutros(){

    String sql = "select * from tb_professor where id_prof = ?";

    try{
    pst = conexao.prepareStatement(sql);
    pst.setString(1, txtIdProfessor.getText());
    rs = pst.executeQuery();

    if(rs.next()){
    jDateNasc.setDate(rs.getDate(5));
    txtFone.setText(rs.getString(8));
    txtEmail.setText(rs.getString(9));
    txtRua.setText(rs.getString(10));
    txtNumero.setText(rs.getString(11));
    txtBairro.setText(rs.getString(12));
    txtCidade.setText(rs.getString(13));
    txtCep.setText(rs.getString(14));
    txtEstado.setText(rs.getString(15));
    txtVinculo.setText(rs.getString(17));
}
    }catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
    }
}

        private void adicionar(){
         
            String sql = "insert into tb_professor(cpf_prof,nome_prof,sobrenome_prof,data_de_nascimento_prof,turno_prof,formacao_prof,telefone_prof,email_prof,rua_prof,numero,bairro,cidade,cep,estado,matricula_prof,vinculo_prof,id_turma) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            
            try{
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtCpf.getText());
                pst.setString(2, txtNome.getText());
                pst.setString(3, txtSobrenome.getText());
                //pst.setString(4, txtDataNasc.getText());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat ("yyyy-MM-dd");
                String dataFormatada = simpleDateFormat.format(this.jDateNasc.getDate());
                pst.setString(4, dataFormatada);
                pst.setString(5, cboTurno.getSelectedItem().toString());
                pst.setString(6, txtFormacao.getText());
                pst.setString(7, txtFone.getText());
                pst.setString(8, txtEmail.getText());
                pst.setString(9, txtRua.getText());
                pst.setString(10, txtNumero.getText());
                pst.setString(11, txtBairro.getText());
                pst.setString(12, txtCidade.getText());
                pst.setString(13, txtCep.getText());
                pst.setString(14, txtEstado.getText());
                pst.setString(15, txtMatricula.getText());
                pst.setString(16, txtVinculo.getText());
                pst.setString(17, txtIdTurma.getText());
                
                if((txtCpf.getText().isEmpty()) || (txtNome.getText().isEmpty())|| (txtSobrenome.getText().isEmpty())|| (txtFone.getText().isEmpty()) || (txtRua.getText().isEmpty())|| (txtNumero.getText().isEmpty())|| (txtBairro.getText().isEmpty())|| (txtCidade.getText().isEmpty())|| (txtCep.getText().isEmpty())|| (txtEstado.getText().isEmpty())|| (txtMatricula.getText().isEmpty())|| (txtVinculo.getText().isEmpty())|| (txtIdTurma.getText().isEmpty())) {
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
                    //if((txtDataNasc.getText().length())>4){
                        
                        //txtDataNasc.setBorder(new LineBorder(Color.RED, 1));
                        //txtDataNasc.requestFocus(); 
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
                    if((txtVinculo.getText().isEmpty())){
                        
                        txtVinculo.setBorder(new LineBorder(Color.RED, 1));
                        txtVinculo.requestFocus(); 
                        txtObrigatorio.setText("Preencha todos os campos obrigatórios");
                    }
                    if((txtIdTurma.getText().isEmpty())){
                        
                        txtIdTurma.setBorder(new LineBorder(Color.RED, 1));
                        txtIdTurma.requestFocus();
                        txtObrigatorio.setText("Preencha todos os campos obrigatórios");
                    }
                    
                }else{
                    int adicionado = pst.executeUpdate();
                    if(adicionado > 0){
                        JOptionPane.showMessageDialog(null, "Professor adicionado!");
                   
                    txtIdProfessor.setText(null);
                    txtNome.setText(null);
                    jDateNasc.setDate(null);
                    //txtDataNasc.setText(null);
                    cboTurno.setSelectedItem(null);
                    txtRua.setText(null);
                    txtCep.setText(null);
                    txtPesquisar.setText(null);
                    txtCidade.setText(null);
                    txtEmail.setText(null);
                    txtVinculo.setText(null);
                    txtNumero.setText(null);
                    txtFormacao.setText(null);
                    txtBairro.setText(null);
                    txtEstado.setText(null);
                    txtFone.setText(null);
                    txtMatricula.setText(null);
                    txtCpf.setText(null);
                    txtIdTurma.setText(null);
                    txtTurma.setText(null);
                    txtTurnoTurma.setText(null);
                    txtSobrenome.setText(null);

                    }
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
        
        private void editar(){
        
        String sql = "update tb_professor set cpf_prof =?,nome_prof =?,sobrenome_prof =?,data_de_nascimento_prof =?,turno_prof =?,formacao_prof =?,telefone_prof =?,email_prof =?,rua_prof =?,numero =?,bairro =?,cidade =?,cep =?,estado =?,matricula_prof =?,vinculo_prof =?,id_turma =? where id_prof =?";
     try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtCpf.getText());
                pst.setString(2, txtNome.getText());
                pst.setString(3, txtSobrenome.getText());
                //pst.setString(4, txtDataNasc.getText());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat ("yyyy-MM-dd");
                String dataFormatada = simpleDateFormat.format(this.jDateNasc.getDate());
                pst.setString(4, dataFormatada);
                pst.setString(5, cboTurno.getSelectedItem().toString());
                pst.setString(6, txtFormacao.getText());
                pst.setString(7, txtFone.getText());
                pst.setString(8, txtEmail.getText());
                pst.setString(9, txtRua.getText());
                pst.setString(10, txtNumero.getText());
                pst.setString(11, txtBairro.getText());
                pst.setString(12, txtCidade.getText());
                pst.setString(13, txtCep.getText());
                pst.setString(14, txtEstado.getText());
                pst.setString(15, txtMatricula.getText());
                pst.setString(16, txtVinculo.getText());
                pst.setString(17, txtIdTurma.getText());
                pst.setString(18, txtIdProfessor.getText());
                
            
           if((txtIdProfessor.getText().isEmpty()) || (txtCpf.getText().isEmpty()) || (txtNome.getText().isEmpty())|| (txtSobrenome.getText().isEmpty())|| (txtFone.getText().isEmpty()) || (txtRua.getText().isEmpty())|| (txtNumero.getText().isEmpty())|| (txtBairro.getText().isEmpty())|| (txtCidade.getText().isEmpty())|| (txtCep.getText().isEmpty())|| (txtEstado.getText().isEmpty())|| (txtMatricula.getText().isEmpty())|| (txtVinculo.getText().isEmpty())|| (txtIdTurma.getText().isEmpty())) {
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
                    //if((txtDataNasc.getText().length())>4){
                        
                        //txtDataNasc.setBorder(new LineBorder(Color.RED, 1));
                        //txtDataNasc.requestFocus(); 
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
                    if((txtVinculo.getText().isEmpty())){
                        
                        txtVinculo.setBorder(new LineBorder(Color.RED, 1));
                        txtVinculo.requestFocus(); 
                        txtObrigatorio.setText("Preencha todos os campos obrigatórios");
                    }
                    if((txtIdTurma.getText().isEmpty())){
                        
                        txtIdTurma.setBorder(new LineBorder(Color.RED, 1));
                        txtIdTurma.requestFocus();
                        txtObrigatorio.setText("Preencha todos os campos obrigatórios");
                    }
                    
                
            } else {
                int adicionado = pst.executeUpdate();

                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados do professor atualizados!");
                   
                   txtCpf.setText(null);
                   txtNome.setText(null);
                   jDateNasc.setDate(null);
                   txtSobrenome.setText(null);
                   //txtDataNasc.setText(null);
                   cboTurno.setSelectedItem(null);
                   txtFormacao.setText(null);
                   txtFone.setText(null);
                   txtEmail.setText(null);
                   txtRua.setText(null);
                   txtNumero.setText(null);
                   txtBairro.setText(null);
                   txtCidade.setText(null);
                   txtCep.setText(null);
                   txtEstado.setText(null);
                   txtMatricula.setText(null);
                   txtVinculo.setText(null);
                   txtIdTurma.setText(null);
                   txtTurma.setText(null);
                   txtTurnoTurma.setText(null);
                }
            }

        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
        private void limpar(){
        
        txtIdProfessor.setText(null);
        txtNome.setText(null);
        jDateNasc.setDate(null);
        //txtDataNasc.setText(null);
        cboTurno.setSelectedItem(null);
        txtRua.setText(null);
        txtCep.setText(null);
        txtPesquisar.setText(null);
        txtCidade.setText(null);
        txtEmail.setText(null);
        txtVinculo.setText(null);
        txtNumero.setText(null);
        txtFormacao.setText(null);
        txtBairro.setText(null);
        txtEstado.setText(null);
        txtFone.setText(null);
        txtMatricula.setText(null);
        txtCpf.setText(null);
        txtIdTurma.setText(null);
        txtTurma.setText(null);
        txtTurnoTurma.setText(null);
        txtSobrenome.setText(null);
        btAdicionar.setEnabled(true);
        btImprimir.setEnabled(true);
        //btPesquisarAluno.setEnabled(true);
        tblProfessor.setVisible(true);
        ((DefaultTableModel) tblProfessor.getModel()).setRowCount(0);
    }
         private void remover() {
        String sql = "delete from tb_professor where id_prof = ? ";
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover esse Professor?", "Atenção!", JOptionPane.YES_NO_OPTION);

        if (confirma == JOptionPane.YES_OPTION) {

            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtIdProfessor.getText());
                int apagado = pst.executeUpdate();

                if (apagado > 0) {
                JOptionPane.showMessageDialog(null, "Professor removido com sucesso!");
                
                txtIdProfessor.setText(null);
                txtNome.setText(null);
                cboTurno.setSelectedItem(null);
                jDateNasc.setDate(null);
                //txtDataNasc.setText(null);
                
                txtRua.setText(null);
                txtCep.setText(null);
                jDateNasc.setDate(null);
                txtPesquisar.setText(null);
                txtCidade.setText(null);
                txtEmail.setText(null);
                txtVinculo.setText(null);
                txtNumero.setText(null);
                txtFormacao.setText(null);
                txtBairro.setText(null);
                txtEstado.setText(null);
                txtFone.setText(null);
                txtMatricula.setText(null);
                txtCpf.setText(null);
                txtIdTurma.setText(null);
                txtSobrenome.setText(null);
                }

            }catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
        private void pesquisar_turma(){

        String sql = "select * from tb_turma where id_turma =?";
        
        try{
            
            pst = conexao.prepareStatement(sql);
            pst.setString(1,  txtIdTurma.getText());
            rs = pst.executeQuery();
            if(rs.next()){
                txtTurma.setText(rs.getString(2));
                txtTurnoTurma.setText(rs.getString(3));

            }else{
                txtTurma.setText(null);
                txtTurnoTurma.setText(null);
                JOptionPane.showMessageDialog(null, "Turma não encontrada");
            }
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
    }
    }  
         private void imprimir(){
           try{
        JasperReport report = JasperCompileManager.compileReport("C:\\Users\\Carlos\\JaspersoftWorkspace\\MyReports\\ProfessorTurma.jrxml");
        
        JasperPrint print = JasperFillManager.fillReport(report, null, conexao);
        
        JasperViewer viewer = new JasperViewer(print, false);
        viewer.setVisible(true);
        viewer.setTitle("Lista de Professores e suas Turmas");
        
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

        jLabel1 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtSobrenome = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jDateNasc = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        txtFone = new javax.swing.JFormattedTextField();
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
        txtEstado = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtCpf = new javax.swing.JFormattedTextField();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProfessor = new javax.swing.JTable();
        txtPesquisar = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtFormacao = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtVinculo = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtTurma = new javax.swing.JTextField();
        txtMatricula = new javax.swing.JTextField();
        txtIdTurma = new javax.swing.JTextField();
        btLimpar2 = new javax.swing.JLabel();
        btLimpar = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        btEditar = new javax.swing.JLabel();
        btImprimir = new javax.swing.JLabel();
        btExcluir = new javax.swing.JLabel();
        btAdicionar = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        txtTurnoTurma = new javax.swing.JTextField();
        cboTurno = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        btPesquisar = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        txtIdProfessor = new javax.swing.JTextField();
        txtObrigatorio = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Professor");
        setPreferredSize(new java.awt.Dimension(880, 700));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Nome");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 60, 25));
        getContentPane().add(txtNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 270, 140, 25));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Sobrenome");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 90, 25));
        getContentPane().add(txtSobrenome, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 310, 140, 25));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Data de Nasc.");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 90, 25));
        getContentPane().add(jDateNasc, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 350, 140, 25));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Telefone");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 430, 60, 25));

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
        getContentPane().add(txtFone, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 430, 140, 25));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Email (opcional)");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 100, 25));
        getContentPane().add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 390, 140, 25));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Rua");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 270, 30, 25));

        txtRua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRuaActionPerformed(evt);
            }
        });
        getContentPane().add(txtRua, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 270, 140, 25));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Nº");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 310, -1, 25));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Bairro");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 350, -1, 25));
        getContentPane().add(txtBairro, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 350, 140, 25));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Cidade");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 390, -1, 25));
        getContentPane().add(txtCidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 390, 140, 25));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("CEP");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 230, 30, 25));

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
        getContentPane().add(txtCep, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 230, 140, 25));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Estado");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 430, 50, 25));
        getContentPane().add(txtEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 430, 140, 25));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Matrícula");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 230, 60, 25));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("CPF");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 30, 25));

        txtCpf.setForeground(new java.awt.Color(255, 255, 255));
        try {
            txtCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCpf.setText("");
        txtCpf.setToolTipText("");
        txtCpf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCpfMouseClicked(evt);
            }
        });
        getContentPane().add(txtCpf, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, 140, 25));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setText("Id da Turma");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 350, 80, 25));

        tblProfessor = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        }
        ;
        tblProfessor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "CPF", "Nome", "Sobrenome", "Turno", "Formação", "Matricula", "Id da Turma"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProfessor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProfessorMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProfessor);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 820, 100));

        txtPesquisar.setToolTipText("Digite o nome do professor que deseja visualizar");
        getContentPane().add(txtPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 62, 270, 40));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Formação (opcional)");
        jLabel3.setToolTipText("Formação");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 270, 130, 25));
        getContentPane().add(txtFormacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 270, 140, 25));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setText("Vínculo");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 310, 50, 25));
        getContentPane().add(txtVinculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 310, 140, 25));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setText("Nome da Turma");
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 390, -1, 25));

        txtTurma.setEditable(false);
        getContentPane().add(txtTurma, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 390, 140, 25));
        getContentPane().add(txtMatricula, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 230, 140, 25));
        getContentPane().add(txtIdTurma, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 350, 140, 25));

        btLimpar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/limpar-limpo2.png"))); // NOI18N
        btLimpar2.setToolTipText("Limpar os campos");
        btLimpar2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btLimpar2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btLimpar2MouseClicked(evt);
            }
        });
        getContentPane().add(btLimpar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 570, 50, 50));

        btLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/fileicon.png"))); // NOI18N
        btLimpar.setToolTipText("Limpar os campos");
        btLimpar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btLimparMouseClicked(evt);
            }
        });
        getContentPane().add(btLimpar, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 540, 80, 80));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setText("Turno da Turma");
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 430, 120, 25));

        btEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Editar.png"))); // NOI18N
        btEditar.setToolTipText("Editar Professor");
        btEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btEditar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btEditarMouseClicked(evt);
            }
        });
        getContentPane().add(btEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 540, 80, 80));

        btImprimir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/print.png"))); // NOI18N
        btImprimir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btImprimirMouseClicked(evt);
            }
        });
        getContentPane().add(btImprimir, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 540, 72, 72));

        btExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Excluir.png"))); // NOI18N
        btExcluir.setToolTipText("Excluir Professor");
        btExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btExcluir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btExcluirMouseClicked(evt);
            }
        });
        getContentPane().add(btExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 540, 80, 80));

        btAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Adicionar.png"))); // NOI18N
        btAdicionar.setToolTipText("Adicionar Professor");
        btAdicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btAdicionar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btAdicionarMouseClicked(evt);
            }
        });
        getContentPane().add(btAdicionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 540, 70, 80));
        getContentPane().add(txtNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 310, 140, 25));

        txtTurnoTurma.setEditable(false);
        getContentPane().add(txtTurnoTurma, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 430, 140, 25));

        cboTurno.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cboTurno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Matutino", "Vespertino", "Integral", "Noturno" }));
        cboTurno.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(cboTurno, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 470, 140, 25));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Turno");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 470, 120, 25));

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/background.png"))); // NOI18N
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 870, 480));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel22.setText("ADMINISTRAÇÃO DE PROFESSORES");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 370, 40));

        btPesquisar.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Pesquisar(pequeno).png"))); // NOI18N
        btPesquisar.setText("Pesquisar");
        btPesquisar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btPesquisar.setOpaque(false);
        btPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarActionPerformed(evt);
            }
        });
        jPanel1.add(btPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 62, -1, 40));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("Id do Professor(a)");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 70, 120, 25));

        txtIdProfessor.setEditable(false);
        jPanel1.add(txtIdProfessor, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 70, 80, 25));

        txtObrigatorio.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        txtObrigatorio.setForeground(new java.awt.Color(255, 102, 102));
        jPanel1.add(txtObrigatorio, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 10, 180, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 870, 190));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtRuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRuaActionPerformed

    private void tblProfessorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProfessorMouseClicked
       setar_campos();
    }//GEN-LAST:event_tblProfessorMouseClicked

    private void btAdicionarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btAdicionarMouseClicked
        adicionar();
        soma_prof();
        soma_alunos();
        soma_turma();
    }//GEN-LAST:event_btAdicionarMouseClicked

    private void btEditarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btEditarMouseClicked
        editar();
    }//GEN-LAST:event_btEditarMouseClicked

    private void btExcluirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btExcluirMouseClicked
       remover();
       soma_alunos();
       soma_prof();
       soma_turma();
    }//GEN-LAST:event_btExcluirMouseClicked

    private void btLimparMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btLimparMouseClicked
        limpar();
    }//GEN-LAST:event_btLimparMouseClicked

    private void btLimpar2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btLimpar2MouseClicked
        limpar();
    }//GEN-LAST:event_btLimpar2MouseClicked

    private void btImprimirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btImprimirMouseClicked
    imprimir(); 
    }//GEN-LAST:event_btImprimirMouseClicked

    private void btPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarActionPerformed
       pesquisar_professor();
    }//GEN-LAST:event_btPesquisarActionPerformed

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
    private javax.swing.JLabel btLimpar;
    private javax.swing.JLabel btLimpar2;
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
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblProfessor;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JFormattedTextField txtCep;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JFormattedTextField txtCpf;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JFormattedTextField txtFone;
    private javax.swing.JTextField txtFormacao;
    private javax.swing.JTextField txtIdProfessor;
    private javax.swing.JTextField txtIdTurma;
    private javax.swing.JTextField txtMatricula;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JLabel txtObrigatorio;
    private javax.swing.JTextField txtPesquisar;
    private javax.swing.JTextField txtRua;
    private javax.swing.JTextField txtSobrenome;
    private javax.swing.JTextField txtTurma;
    private javax.swing.JTextField txtTurnoTurma;
    private javax.swing.JTextField txtVinculo;
    // End of variables declaration//GEN-END:variables
}
