package view;

import view.ItemVenda.*;
import control.ctrPrincipal;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import view.Venda.VendaAtualizaView;

public class viewPrincipal {

    // Declara componentes
    private ctrPrincipal ObjPrincipal;

    private JLabel texto, imagem;
    private JMenuBar barraMenu;
    private JMenu menuProd, menuVenda, menuSair;
    private JMenuItem menuCadastraProd, menuConsultaProd, menuDeletaProd,
            menuCadastraVenda, menuAtualizaVenda, menuConsultaVenda, menuDeletaVenda, menuInsereItem, menuRemoveItem;
    private JFrame frame;
    private JPanel panel;
    private ImageIcon icon;

    // Cria o menu completo
    public viewPrincipal(ctrPrincipal pObjPrincipal) {
        // Declara componentes do menu genericamente
        ObjPrincipal = pObjPrincipal;
        frame = new JFrame("Mercado Camavi");

        panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(255, 211, 155));

        icon = new ImageIcon(getClass().getResource("img.png"));
        imagem = new JLabel(icon);
        imagem.setBounds(20, 30, 70, 96);
        imagem.setSize(70, 96);
        imagem.setVisible(true);
        
        panel.add(imagem, BorderLayout.CENTER);

        // Cria barra do Menu
        barraMenu = new JMenuBar();
        barraMenu.setBackground(new Color(255, 130, 71));

        // Cria menu Produto
        menuProd = new JMenu("Produto");

        // Cria item do menu Produto
        menuCadastraProd = new JMenuItem("Cadastrar", KeyEvent.VK_C);
        menuCadastraProd.setBackground(new Color(255, 130, 71));
        menuCadastraProd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    /*TEM QUE SER!*/
                    pObjPrincipal.getObjCtrProduto().insereProduto();
                } catch (SQLException ex) {
                    pObjPrincipal.getObjCtrProduto().problemeInsere(ex);
                }
            }
        });

        menuDeletaProd = new JMenuItem("Deletar", KeyEvent.VK_D);
        menuDeletaProd.setBackground(new Color(255, 130, 71));
        menuDeletaProd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pObjPrincipal.getObjCtrProduto().removeProduto();
                } catch (SQLException ex) {
                    pObjPrincipal.getObjCtrProduto().problemeDeletar(ex);
                }
            }
        });

        menuConsultaProd = new JMenuItem("Consultar", KeyEvent.VK_B);
        menuConsultaProd.setBackground(new Color(255, 130, 71));
        menuConsultaProd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pObjPrincipal.getObjCtrProduto().listaProdutos();
                } catch (SQLException ex) {
                    pObjPrincipal.getObjCtrProduto().problemeInsere(ex);
                }
            }
        });

        // Adiciona item ao Menu
        menuProd.add(menuCadastraProd);
        menuProd.addSeparator();
        menuProd.add(menuDeletaProd);
        menuProd.addSeparator();
        menuProd.add(menuConsultaProd);

        // Cria menu Venda
        menuVenda = new JMenu("Venda");

        // Cria item do menu Venda
        menuCadastraVenda = new JMenuItem("Cadastrar", KeyEvent.VK_C);
        menuCadastraVenda.setBackground(new Color(255, 130, 71));
        menuCadastraVenda.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    /*TEM QUE SER!*/
                    pObjPrincipal.getObjCtrVenda().insereVenda();
                } catch (SQLException ex) {
                    pObjPrincipal.getObjCtrVenda().problemeInsere(ex);
                }
            }
        });

        menuDeletaVenda = new JMenuItem("Deletar", KeyEvent.VK_D);
        menuDeletaVenda.setBackground(new Color(255, 130, 71));
        menuDeletaVenda.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pObjPrincipal.getObjCtrVenda().removeVenda();
                } catch (SQLException ex) {
                    pObjPrincipal.getObjCtrVenda().problemeDeletar(ex);
                }
            }
        });

        menuConsultaVenda = new JMenuItem("Consultar", KeyEvent.VK_B);
        menuConsultaVenda.setBackground(new Color(255, 130, 71));
        menuConsultaVenda.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pObjPrincipal.getObjCtrVenda().listaVenda();
                } catch (SQLException ex) {
                    pObjPrincipal.getObjCtrProduto().problemeInsere(ex);
                }
            }
        });

        menuAtualizaVenda = new JMenuItem("Atualizar", KeyEvent.VK_A);
        menuAtualizaVenda.setBackground(new Color(255, 130, 71));
        menuAtualizaVenda.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pObjPrincipal.getObjCtrVenda().atualizaVenda();
                } catch (SQLException ex) {
                    Logger.getLogger(viewPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        menuInsereItem = new JMenuItem("Inserir item", KeyEvent.VK_I);
        menuInsereItem.setBackground(new Color(255, 130, 71));
        menuInsereItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    /*TEM QUE SER!*/
                    pObjPrincipal.getObjCtrItem().insereItem();
                    pObjPrincipal.getObjCtrItem().InseridoSucesso();
                } catch (SQLException ex) {
                    pObjPrincipal.getObjCtrItem().problemeInsere(ex);
                }
            }
        });

        menuRemoveItem = new JMenuItem("Remover item", KeyEvent.VK_I);
        menuRemoveItem.setBackground(new Color(255, 130, 71));
        menuRemoveItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ObjPrincipal.getObjCtrItem().RemoveItem();
                } catch (SQLException ex) {
                    ObjPrincipal.getObjCtrItem().problemeInsere(ex);
                }
            }
        });

        // Adiciona item ao Menu
        menuVenda.add(menuCadastraVenda);
        menuVenda.addSeparator();
        menuVenda.add(menuDeletaVenda);
        menuVenda.addSeparator();
        menuVenda.add(menuConsultaVenda);
        menuVenda.addSeparator();
        menuVenda.add(menuAtualizaVenda);
        menuVenda.addSeparator();
        menuVenda.add(menuInsereItem);
        menuVenda.addSeparator();
        menuVenda.add(menuRemoveItem);

        // Adiciona menu a barra
        barraMenu.add(menuProd);
        barraMenu.add(menuVenda);

        frame.setJMenuBar(barraMenu);
        frame.add(panel);
        // retorna menu completo
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(frame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }
}
