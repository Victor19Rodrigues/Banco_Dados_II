package view.ItemVenda;

import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author carlos
 */
public class ItemCadastraView {

    String codProdST, codVendST, quantidadeST;
    JTextField codProdTF, codVendTF, quantidadeTF;

    public ItemCadastraView() {
        codProdTF = new JTextField();
        codVendTF = new JTextField();
        quantidadeTF = new JTextField();
        Object[] message = {
            "Codigo Produto:", codProdTF,
            "Codigo Vendas:", codVendTF,
            "Quantidade do Produto:", quantidadeTF,};

        int option = JOptionPane.showConfirmDialog(null, message, "Inserir Itens", JOptionPane.YES_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            //pega os valores dos campos de texto
            codProdST = codProdTF.getText();
            codVendST = codVendTF.getText();
            quantidadeST = quantidadeTF.getText();
        }
    }

    /*envia os dados digitados no formulario*/
    public String[] EnviaForm() {
        String aDadosForm[] = new String[3];
        aDadosForm[0] = codProdST;
        aDadosForm[1] = codVendST;
        aDadosForm[2] = quantidadeST;
        return aDadosForm;
    }

    public void problemaInsere(SQLException ex) {
        JLabel msg = new JLabel("<html><H3 style=\"color:#FF0000;\">Erro na inserção</h3><br><h4style=\"color:#FF000;\"> motivo: " + ex + "</hyml>");
        JOptionPane.showMessageDialog(null, msg);
    }

    public void InseridoComSucesso() {
        JOptionPane.showMessageDialog(null, "Itens inseridos com sucesso inserido com sucesso!");
    }
}
