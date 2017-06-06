package view.Produto;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ProdutoAtualizaView implements ActionListener {

    JFrame frame;
    JPanel pbusca, pcampos, principal;
    JButton buscar, admitir;
    BoxLayout box;
    CardLayout card;

    //Gerar Strings constantes para cardLayout
    private final String busca = "BUSCA";
    private final String atualizacao = "ATUALIZACAO";

    public ProdutoAtualizaView() {
        //Criar text fields
        JTextField codigoTF = new JTextField(10);
        JTextField nomeTF = new JTextField(10);
        JTextField qtdestoqueTF = new JTextField(10);
        JTextField precoTF = new JTextField(10);

        //Criar JLabel's
        JLabel codigoJL = new JLabel("Codigo:");
        JLabel nomeJL = new JLabel("Nome:");
        JLabel qtdestoqueJL = new JLabel("Estoque:");
        JLabel precoJL = new JLabel("Preco:");

        //Criar JButton
        admitir = new JButton("Atualizar dados");
        buscar = new JButton("Buscar produto");
        buscar.addActionListener(this);
        admitir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                card = (CardLayout) principal.getLayout();
                card.show(principal, busca);
            }
        });

        //Criar JPanel's
        JPanel pcod = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JPanel pnome = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JPanel pqtd = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JPanel ppreco = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JPanel pbotao = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        pbusca = new JPanel(new BorderLayout(50, 50));
        pcampos = new JPanel();

        //criar gerenciador de layout
        box = new BoxLayout(pcampos, BoxLayout.Y_AXIS);
        pcampos.setLayout(box);

        //Adicionar componentes aos JPanels
        pcod.add(codigoJL);
        pcod.add(codigoTF);
        pcod.add(buscar);

        pbusca.add(pcod, BorderLayout.CENTER);

        ppreco.add(precoJL);
        ppreco.add(precoTF);

        pnome.add(nomeJL);
        pnome.add(nomeTF);

        pqtd.add(qtdestoqueJL);
        pqtd.add(qtdestoqueTF);

        pbotao.add(admitir);

        pcampos.add(Box.createVerticalGlue());
        pcampos.add(pnome);
        pcampos.add(pqtd);
        pcampos.add(ppreco);
        pcampos.add(pbotao);
        pcampos.add(Box.createVerticalGlue());

        //Criar e configurar painel com cardLayout
        principal = new JPanel(new CardLayout(20, 20));
        principal.add(pbusca, busca);
        principal.add(pcampos, atualizacao);

        //Gerar JFrame e definir configuracoes
        frame = new JFrame("Atualizaçao de produtos");
        frame.add(principal);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        card = (CardLayout) principal.getLayout();
        card.show(principal, atualizacao);
    }
}
