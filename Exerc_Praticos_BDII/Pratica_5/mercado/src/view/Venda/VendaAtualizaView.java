package view.Venda;

import java.util.Date;
import javax.swing.*;

public class VendaAtualizaView
{
    JTextField codigoTF,dataTF,cpfTF;
    JLabel codigoJL,dataJL,cpfJL;
    JButton codigoJB;
    String codigoString,dataString,cpfString;

    public VendaAtualizaView() {
        codigoTF = new JTextField(10);
        codigoJL = new JLabel("Codigo da venda: ");
        
        
        Object layout[] = {codigoJL,codigoTF};
        
        int res = JOptionPane.showConfirmDialog(null,layout,"Buscar venda",JOptionPane.YES_NO_OPTION);
        
        if(res == JOptionPane.OK_OPTION)
        {
            if (codigoTF.getText().isEmpty())
                JOptionPane.showMessageDialog(null, "Voce deve informar o codigo!");
            else
            {
                codigoString =  codigoTF.getText();
            }
        }
    }
    
    public String getText()
    {        
        return codigoString;
    }
    
    public void erroAoEncontrar()
    {
        JOptionPane.showMessageDialog(null, "Produto nao encontrado!");
    }
    
    public void atualiza(Date data, String cpf)
    {
        dataJL = new JLabel("Data:");
        dataTF = new JTextField(10);
        cpfJL = new JLabel("CPF :");
        cpfTF = new JTextField(10);
        dataTF.setText(data.getYear()+"/"+data.getMonth()+"/"+data.getDate());
        cpfTF.setText(cpf);
        
        Object campos[] = {dataJL,dataTF,cpfJL,cpfTF};
        int op = JOptionPane.showConfirmDialog(null, campos,"Atualizar venda",JOptionPane.YES_NO_OPTION);
        
        if(op == JOptionPane.OK_OPTION)
        {
            dataString = dataTF.getText();
            cpfString = cpfTF.getText();
        }
    }
    
    public String[] getCampos()
    {
        String venda[] = new String[2];
        venda[0] = dataString;
        venda[1] = cpfString;
            
        return venda;
    }
    
    public void confirmacao()
    {
        JOptionPane.showMessageDialog(null, "Venda atualizada!");
    }
    
}
