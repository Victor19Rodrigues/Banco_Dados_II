package view.Produto;


import java.sql.SQLException;
import javax.swing.*;

public class ProdutoCadastraView {

    String qtdST, nomeST, precoST;
    JTextField qtdTF, nomeTF, precoTF;

    public ProdutoCadastraView() {
        qtdTF = new JTextField();
        nomeTF = new JTextField();
        precoTF = new JTextField();
        Object[] message = {
            "Quantidade:", qtdTF,
            "Nome:", nomeTF,
            "Preco:", precoTF,
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Cadastrar", JOptionPane.YES_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            //pega os valores dos campos de texto
            qtdST = qtdTF.getText();
            nomeST = nomeTF.getText();
            precoST = precoTF.getText();
        }
    }

    /*envia os dados digitados no formulario*/
    public String[] EnviaForm() {
        String aDadosForm[] = new String[3];
        aDadosForm[0] = qtdST;
        aDadosForm[1] = nomeST;
        aDadosForm[2] = precoST;
        return aDadosForm;
    }

    public void problemaInsere(SQLException ex) {
        JLabel msg = new JLabel("<html><H3 style=\"color:#FF0000;\">Erro na inserção</h3><br><h4style=\"color:#FF000;\"> motivo: " + ex + "</hyml>");
        JOptionPane.showMessageDialog(null, msg);
    }

    public void InseridoComSucesso() {
        JOptionPane.showMessageDialog(null, "Produto inserido com sucesso!");
    }
}
