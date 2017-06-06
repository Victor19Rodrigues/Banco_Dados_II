package view.Venda;
import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.*;
import model.Venda;

public class VendaConsultaView {

    JFrame frame;
    JPanel exibirJTable;
    JTable tabela;
    JButton codigo;
    JScrollPane painelrolagem;

    public VendaConsultaView(ArrayList<Venda> todasVendas) {
        //Criar JTable
        Object colunas[] = {"Código", "Data", "Valor total", "CPF_vendedor"};
        Object dados[][] = new Object[todasVendas.size()][4];
        for (int i = 0; i< todasVendas.size(); i++) {
            dados[i][0] = todasVendas.get(i).getCodigo();
            dados[i][1] = todasVendas.get(i).getData();
            dados[i][2] = todasVendas.get(i).getValor_total();
            dados[i][3] = todasVendas.get(i).getCpf_vendedor();
            
        }
        tabela = new JTable(dados, colunas);

        //Criar Painel de rolagem
        painelrolagem = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        painelrolagem.getViewport().add(tabela);

        //Criar JPanel's
        exibirJTable = new JPanel(new BorderLayout(20, 20));

        //Gerar JFrame
        frame = new JFrame("Listagem de vendas");

        //Adicionar ao painel
        exibirJTable.add(painelrolagem);

        frame.add(exibirJTable);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);

    }
}