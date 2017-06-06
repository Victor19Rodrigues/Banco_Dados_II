package view.Venda;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;

public class VendaDeleteView {

    JTextField codigoTF;
    String codigoST;

    public VendaDeleteView() {
        codigoTF = new JTextField();
        Object[] message = {
            "Código:", codigoTF,
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Apagar", JOptionPane.OK_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            //pega os valores dos campos de texto
            codigoST = codigoTF.getText();
        }
    }

    public String EnviaForm() {
        String aDadosForm;
        aDadosForm = codigoST;
        return aDadosForm;
    }


    public void DeletadoComSucesso() {
        JOptionPane.showMessageDialog(null, "Produto deleteado com sucesso!");
    }

    public void problemaDelete(SQLException ex) {
        JLabel msg = new JLabel("<html><H3 style=\"color:#FF0000;\">Erro em deletar</h3><br><h4style=\"color:#FF000;\"> motivo: " + ex + "</hyml>");
        JOptionPane.showMessageDialog(null, msg);    
    }

    public void naoDeletou() {
        JOptionPane.showMessageDialog(null, "Registro com o código n]ao encontrado"); 
    }

}
