package view.Produto;

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.*;
import model.Produto;

public class ProdutoConsultaView {

    JFrame frame;
    JPanel exibirJTable;
    JTable tabela;
    JButton codigo;
    JScrollPane painelrolagem;

    public ProdutoConsultaView(ArrayList<Produto> todosProdutos){
        //Criar JTable
        Object colunas[] = {"Código", "Nome", "Quantidade", "Preço"};
        Object dados[][] = new Object[todosProdutos.size()][4];
        
        for (int i = 0; i< todosProdutos.size(); i++) {
            dados[i][0] = todosProdutos.get(i).getCodigo();
            dados[i][1] = todosProdutos.get(i).getNome();
            dados[i][2] = todosProdutos.get(i).getQuant_estoque();
            dados[i][3] = todosProdutos.get(i).getPreco();
            
        }
        tabela = new JTable(dados, colunas);

        //Criar Painel de rolagem
        painelrolagem = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        painelrolagem.getViewport().add(tabela);

        //Criar JPanel's
        exibirJTable = new JPanel(new BorderLayout(20, 20));

        //Gerar JFrame
        frame = new JFrame("Listagem de produtos");

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
