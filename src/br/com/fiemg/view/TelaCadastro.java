/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fiemg.view;

import br.com.fiemg.conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class TelaCadastro extends javax.swing.JInternalFrame {
    
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public TelaCadastro() {
        initComponents();
        //iniciar conexão com a classe conexão
        con = Conexao.conectar();
        desabilitarCampos();
        
    }
    //método de pesquisa no banco;
    private void consultar(){
        //Comando para consulta no banco;
        String sql = "Select * from usuario where id = ?";
        try {
            //Envia a consulta para p bancp;
            pst = con.prepareStatement(sql);
            //Seta o valor de ID para o primeiro indice do comando;
            pst.setString(1, txtUsuId.getText());
            //Recebe a consulta com o executeQuery que será usado somente para o select;
            rs = pst.executeQuery();
            if(rs.next()){
                txtUsuNome.setText(rs.getString(2));
                txtUsuLogin.setText(rs.getString(3));
                txtUsuSenha.setText(rs.getString(4));
                cbUsuPerfil.setSelectedItem(rs.getString(5));
                
            }else{
                desabilitarCampos();
                JOptionPane.showMessageDialog(null, "Usuário no encontrado!!!");
            }
                
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
/*----------------------------------------------------------------------------*/    
        private void inserir(){
            String sql = "Insert into usuario(nome, login, senha, perfil)"
                    +"values(?,?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, txtUsuNome.getText());
            pst.setString(2, txtUsuLogin.getText());
            pst.setString(3, txtUsuSenha.getText());
            pst.setString(4, cbUsuPerfil.getSelectedItem().toString());
            int op = pst.executeUpdate();
            if(op>0)
                JOptionPane.showMessageDialog(null,"Inserido com sucesso!!!");
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null,ex);
        }
        }
/*----------------------------------------------------------------------------*/        
        private void update(){
            String sql = "UPDATE usuario SET nome=?, login=?, senha=?, perfil=? WHERE id=?";
            
        try {
            if (txtUsuNome.getText().isEmpty()|| txtUsuLogin.getText().isEmpty()
                    || txtUsuSenha.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"Preencha todos os campos!!!");
            }else{
                
            
            pst = con.prepareStatement(sql);
            pst.setString(1, txtUsuNome.getText());
            pst.setString(2, txtUsuLogin.getText());
            pst.setString(3, txtUsuSenha.getText());
            pst.setString(4, cbUsuPerfil.getSelectedItem().toString());
            pst.setString(5, txtUsuId.getText());
            int op = pst.executeUpdate();
            if(op>0)
                JOptionPane.showMessageDialog(null,"Alterado com sucesso!!!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        }
/*----------------------------------------------------------------------------*/       
        private void deletar(){
            String sql = "DELETE FROM usuario WHERE id=?";
            int confrima = JOptionPane.showConfirmDialog(null, "Deseja realmente deletar???", "Atenção",
                            JOptionPane.YES_NO_OPTION);
            if(confrima == JOptionPane.YES_OPTION){
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, txtUsuId.getText());
            int op = pst.executeUpdate();
            if(op>0)
                JOptionPane.showMessageDialog(null, "Deletado com sucesso!!!");
            desabilitarCampos();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
        }
            }
        }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbUsuPerfil = new javax.swing.JComboBox<>();
        txtUsuLogin = new javax.swing.JTextField();
        txtUsuNome = new javax.swing.JTextField();
        btnUsuInseriir = new javax.swing.JButton();
        btnUsuPesquisar = new javax.swing.JButton();
        btnUsuEditar = new javax.swing.JButton();
        btnUsuExcluir = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtUsuId = new javax.swing.JTextField();
        txtUsuSenha = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Cadastro de Usuario");
        setPreferredSize(new java.awt.Dimension(430, 350));

        jLabel1.setText("Nome:");

        jLabel2.setText("Login:");

        jLabel3.setText("Senha:");

        jLabel4.setText("Perfil:");

        cbUsuPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "administrador", "usuario" }));
        cbUsuPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbUsuPerfilActionPerformed(evt);
            }
        });

        btnUsuInseriir.setText("Inserir");
        btnUsuInseriir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuInseriirActionPerformed(evt);
            }
        });

        btnUsuPesquisar.setText("Pesquisar");
        btnUsuPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuPesquisarActionPerformed(evt);
            }
        });

        btnUsuEditar.setText("Editar");
        btnUsuEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuEditarActionPerformed(evt);
            }
        });

        btnUsuExcluir.setText("Excluir");
        btnUsuExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuExcluirActionPerformed(evt);
            }
        });

        jLabel5.setText("ID:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(cbUsuPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnUsuInseriir, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnUsuPesquisar)
                        .addGap(18, 18, 18)
                        .addComponent(btnUsuEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnUsuExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUsuId, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtUsuNome)
                                .addComponent(txtUsuSenha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                                .addComponent(txtUsuLogin, javax.swing.GroupLayout.Alignment.LEADING)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuId, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuNome, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtUsuLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbUsuPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUsuInseriir)
                    .addComponent(btnUsuPesquisar)
                    .addComponent(btnUsuEditar)
                    .addComponent(btnUsuExcluir))
                .addGap(49, 49, 49))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbUsuPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbUsuPerfilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbUsuPerfilActionPerformed

    private void btnUsuPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuPesquisarActionPerformed
        consultar();
    }//GEN-LAST:event_btnUsuPesquisarActionPerformed

    private void btnUsuInseriirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuInseriirActionPerformed
       inserir();
    }//GEN-LAST:event_btnUsuInseriirActionPerformed

    private void btnUsuEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuEditarActionPerformed
        update();
    }//GEN-LAST:event_btnUsuEditarActionPerformed

    private void btnUsuExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuExcluirActionPerformed
        deletar();
    }//GEN-LAST:event_btnUsuExcluirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUsuEditar;
    private javax.swing.JButton btnUsuExcluir;
    private javax.swing.JButton btnUsuInseriir;
    private javax.swing.JButton btnUsuPesquisar;
    private javax.swing.JComboBox<String> cbUsuPerfil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField txtUsuId;
    private javax.swing.JTextField txtUsuLogin;
    private javax.swing.JTextField txtUsuNome;
    private javax.swing.JTextField txtUsuSenha;
    // End of variables declaration//GEN-END:variables

    private void desabilitarCampos() {
        
        txtUsuNome.setText(null);
        txtUsuLogin.setText(null);
        txtUsuSenha.setText(null);
        cbUsuPerfil.setSelectedItem(null);
    }
}
