
package view.Venda;


import java.sql.SQLException;
import javax.swing.*;

public class VendaCadastraView {

    String dataST, CPF_VendedorST;
    JTextField dataTF, CPF_VendedorTF;
    public VendaCadastraView() {
        dataTF = new JTextField();
        CPF_VendedorTF = new JTextField();
        Object[] message = {
            "Data:", dataTF,
            "CPF vendedor:", CPF_VendedorTF,
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Cadastrar", JOptionPane.PLAIN_MESSAGE);
        if (option == JOptionPane.OK_OPTION) {
            //pega os valores dos campos de texto
            dataST = dataTF.getText();
            CPF_VendedorST = CPF_VendedorTF.getText();
        }
    }

    /*envia os dados digitados no formulario*/
    public String[] EnviaForm() {
        String aDadosForm[] = new String[2];
        aDadosForm[0] = dataST;
        aDadosForm[1] = CPF_VendedorST;
        return aDadosForm;
    }

    public void problemaInsere(SQLException ex) {
        JLabel msg = new JLabel("<html><H3 style=\"color:#FF0000;\">Erro na inserção</h3><br><h4style=\"color:#FF000;\"> motivo: " + ex + "</hyml>");
        JOptionPane.showMessageDialog(null, msg);
    }

    public void InseridoComSucesso() {
        JOptionPane.showMessageDialog(null, "Venda inserido com sucesso!");
    }

    public void DataErrada(Exception e) {
        JLabel msg = new JLabel("<html><H3 style=\"color:#FF0000;\">Erro na inserção</h3><br><h4style=\"color:#FF000;\"> motivo: " + e + "</hyml>");
        JOptionPane.showMessageDialog(null, msg, "teste", JOptionPane.PLAIN_MESSAGE);
    }

    public void problemaInsereData() {
        JOptionPane.showMessageDialog(null, "Erro com a data", "teste", JOptionPane.PLAIN_MESSAGE);
    }
}
