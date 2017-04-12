
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.ScrollPaneConstants;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Guilherme Bacca, Peterson Boni
 */
public class TelaCarregamento extends javax.swing.JFrame {

    ArrayList<String> arrayInstrucoes;
    private static File program = new File("program.txt");
    /**
     * Creates new form TelaCarregamento
     */
    public TelaCarregamento() {
        initComponents();
        arrayInstrucoes = new ArrayList();
        
        scrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        textArea1.setBorder(new NumberedBorder());
        scrollPane1.setViewportView(textArea1);
        
        
        
        this.setLocationByPlatform(true);
        this.setTitle("MVM");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCarregar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnExecutar = new javax.swing.JButton();
        scrollPane1 = new javax.swing.JScrollPane();
        textArea1 = new javax.swing.JTextArea();
        edtEntraCodigo = new javax.swing.JTextField();
        btnAddCodigo = new javax.swing.JButton();
        edtLimparTexto = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnCarregar.setText("Carregar");
        btnCarregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCarregarActionPerformed(evt);
            }
        });

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnExecutar.setText("Executar");
        btnExecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExecutarActionPerformed(evt);
            }
        });

        textArea1.setEditable(false);
        textArea1.setColumns(20);
        textArea1.setRows(5);
        scrollPane1.setViewportView(textArea1);

        btnAddCodigo.setText("Adicionar linha");
        btnAddCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCodigoActionPerformed(evt);
            }
        });

        edtLimparTexto.setText("Limpar texto");
        edtLimparTexto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtLimparTextoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCarregar, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(btnSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(54, 54, 54)
                        .addComponent(btnExecutar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(edtEntraCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAddCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(edtLimparTexto)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnExecutar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCarregar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(scrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(edtLimparTexto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edtEntraCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddCodigo))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCarregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCarregarActionPerformed
        // TODO add your handling code here:
        String sArquivo = "";
        boolean bEscolheu = false;
        while(!bEscolheu){
            JFileChooser chooserArquivo = new JFileChooser();
            int iEscolha = chooserArquivo.showOpenDialog(chooserArquivo);
            if(iEscolha == 1){
                int iContinuar = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja cancelar a ação?\nClique em 'não' para continuar a escolher.");
                if(iContinuar == 1 || iContinuar == -1)
                    continue;
                else
                    return;
            }
            else{
                bEscolheu = true;
                textArea1.setText("");
                arrayInstrucoes.clear();
            }
            
            sArquivo = chooserArquivo.getSelectedFile().getAbsolutePath();
        }

        Path pathArquivo = Paths.get(sArquivo);
        BufferedReader ler = null;
        try {
            ler = new BufferedReader(new FileReader(pathArquivo.toFile()));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TelaCarregamento.class.getName()).log(Level.SEVERE, null, ex);
        }
        LineNumberReader lnr = null;
        try {
            lnr = new LineNumberReader(new FileReader(pathArquivo.toFile()));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TelaCarregamento.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            lnr.skip(Long.MAX_VALUE);
        } catch (IOException ex) {
            Logger.getLogger(TelaCarregamento.class.getName()).log(Level.SEVERE, null, ex);
        }
        int f = lnr.getLineNumber()+1;
        if (Files.exists(pathArquivo)){
            try {
                while(ler.ready()) {
                    String sLinha = null;
                    try {
                        sLinha = ler.readLine();
                    } catch (IOException ex) {
                        Logger.getLogger(TelaCarregamento.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if(sLinha == null){
                        continue;
                    }
                    textArea1.append(sLinha+"\n");
                    arrayInstrucoes.add(sLinha);
                }
            } catch (IOException ex) {
                Logger.getLogger(TelaCarregamento.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        textArea1.replaceRange("", textArea1.getText().length()-1, textArea1.getText().length());
    }//GEN-LAST:event_btnCarregarActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        // TODO add your handling code here:
        FileWriter fw = null;
        try {
            fw = new FileWriter(program, false);
        } catch (IOException ex) {
            Logger.getLogger(TelaCarregamento.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedWriter bw = new BufferedWriter(fw);
        
        String sLinha = "";
        sLinha = textArea1.getText();
        if(sLinha.equals("") || sLinha == null){
            JOptionPane.showMessageDialog(this, "Não foi possível salvar o código.\nCampo de texto vazio.");
        }else{
            int iPos = 0;
            String sAux = "";
            try {
                while(iPos < sLinha.length()){
                    sAux += sLinha.charAt(iPos);
                    if(iPos == sLinha.length()-1 || sLinha.charAt(iPos) == '\n'){
                        bw.write(sAux);
                        if(iPos != sLinha.length()-1)
                            bw.newLine();                        
                        sAux = "";
                    }
                    iPos++;
                }
                System.out.println(iPos);
            } catch (IOException ex) {
                Logger.getLogger(TelaCarregamento.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(this, "Salvo em: "+program.getAbsolutePath());
        }
        
        try {
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(TelaCarregamento.class.getName()).log(Level.SEVERE, null, ex);
        }
                      
        System.out.println(sLinha.length());
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnAddCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCodigoActionPerformed
        // TODO add your handling code here:
        String codigoLinha = edtEntraCodigo.getText();
        if(!codigoLinha.equals("")){
            if(textArea1.getText().equals(""))
                textArea1.append(codigoLinha);
            else
                textArea1.append("\n"+codigoLinha);
            
            arrayInstrucoes.add(codigoLinha);
        }
        
        edtEntraCodigo.setText("");
    }//GEN-LAST:event_btnAddCodigoActionPerformed

    private void edtLimparTextoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtLimparTextoActionPerformed
        // TODO add your handling code here:
        textArea1.removeAll();
        textArea1.setText("");
        arrayInstrucoes.clear();
    }//GEN-LAST:event_edtLimparTextoActionPerformed

    private void btnExecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExecutarActionPerformed
        // TODO add your handling code here:
        if(arrayInstrucoes.size() > 0){
            TelaExecucao telaExecucao = new TelaExecucao();
            telaExecucao.setVisible(true);
            telaExecucao.setaArray(arrayInstrucoes);
        }else{
            JOptionPane.showMessageDialog(this, "Deve conter um código para executar!");
        }
    }//GEN-LAST:event_btnExecutarActionPerformed

    
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
            java.util.logging.Logger.getLogger(TelaCarregamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCarregamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCarregamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCarregamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCarregamento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddCodigo;
    private javax.swing.JButton btnCarregar;
    private javax.swing.JButton btnExecutar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JTextField edtEntraCodigo;
    private javax.swing.JButton edtLimparTexto;
    private javax.swing.JScrollPane scrollPane1;
    private javax.swing.JTextArea textArea1;
    // End of variables declaration//GEN-END:variables
}
