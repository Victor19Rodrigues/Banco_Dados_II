package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public abstract class DAO {

    static public PreparedStatement pstmtP1, pstmtP2, pstmtP3, pstmtP4, pstmtP5, pstmtV1, pstmtV2, pstmtV3,
            pstmtV4, pstmtV5, pstmtI1, pstmtI2, pstmtI3, pstmtI4;
    public void inicializaJdbc() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost/Loja", "root", "victor");
            pstmtP1 = c.prepareStatement("INSERT INTO Produtos(Nome, Quant_Estoque, Preco) VALUES(?, ?, ?)");
            pstmtP2 = c.prepareStatement("UPDATE Produtos SET Nome = ?, Quant_Estoque = ?, Preco = ? WHERE Codigo = ?");
            pstmtP3 = c.prepareStatement("DELETE FROM Produtos WHERE Codigo= ?");
            pstmtP4 = c.prepareStatement("SELECT * FROM Produtos WHERE codigo = ?");
            pstmtP5 = c.prepareStatement("SELECT * FROM Produtos");
            pstmtV1 = c.prepareStatement("INSERT INTO Vendas (Data, Valor_Total, CPF_Vendedor) VALUES (?, ?, ?)"); 
            pstmtV2 = c.prepareStatement("UPDATE Vendas SET Data= ?, CPF_Vendedor = ? WHERE Codigo = ?");
            pstmtV3 = c.prepareStatement("DELETE FROM Vendas WHERE Codigo= ?");
            pstmtV4 = c.prepareStatement("SELECT * FROM Vendas WHERE Codigo = ?");
            pstmtV5 = c.prepareStatement("SELECT * FROM Vendas");
            /*Precisa de todas as opções do itens vendas*/
            pstmtI1 = c.prepareStatement("INSERT INTO Itens_Venda VALUES (?, ?, ?)");
            pstmtI2 = c.prepareStatement("UPDATE Itens_Venda SET Quantidade= ? WHERE Cod_Prod= ? AND Cod_venda = ?"); 
            pstmtI3 = c.prepareStatement("DELETE FROM Itens_Venda WHERE Cod_Prod= ? AND Cod_venda= ?");  
            pstmtI4 = c.prepareStatement("SELECT * FROM Itens_Venda WHERE Cod_Prod = ? and Cod_venda = ?");
            System.out.println("[INFO]: Voce esta conectado");
        } catch (Exception ex) {
            /*Olhar a linha de comando quando rodar para ver se conectou ou não*/
            System.err.println("Problema.: "+ex.getMessage());
        }
    }

}
