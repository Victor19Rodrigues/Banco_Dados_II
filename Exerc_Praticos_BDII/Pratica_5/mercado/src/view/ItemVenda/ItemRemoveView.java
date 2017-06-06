package view.ItemVenda;

import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author carlos
 */
public class ItemRemoveView {
    String codProdST, codVendST;
    JTextField codProdTF, codVendTF;
    public ItemRemoveView() {
        codProdTF = new JTextField();
        codVendTF = new JTextField();
        Object[] message = {
            "Codigo Produto:", codProdTF,
            "Codigo Vendas:", codVendTF,
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Remover Itens", JOptionPane.YES_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            //pega os valores dos campos de texto
            codProdST = codProdTF.getText();
            codVendST = codVendTF.getText();
        }
    }

    /*envia os dados digitados no formulario*/
    public String[] EnviaForm() {
        String aDadosForm[] = new String[2];
        aDadosForm[0] = codProdST;
        aDadosForm[1] = codVendST;
        return aDadosForm;
    }

    public void problemaRemover(SQLException ex) {
        JLabel msg = new JLabel("<html><H3 style=\"color:#FF0000;\">Erro na inserção</h3><br><h4style=\"color:#FF000;\"> motivo: " + ex + "</hyml>");
        JOptionPane.showMessageDialog(null, msg);
    }

    public void RemovidoComSucesso() {
        JOptionPane.showMessageDialog(null, "Itens Removido com sucesso!");
    }
    
    public void FalhaRemocao()
    {
        JOptionPane.showMessageDialog(null, "Falha na remoçao!");
    }
}