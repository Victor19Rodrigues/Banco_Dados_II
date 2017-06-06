package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Item_venda extends DAO {

    int Cod_Prod, Cod_venda, Quantidade;

    public Item_venda(int pCod_Prod, int pCod_venda, int pQuantidade) {
        this.Cod_Prod = pCod_Prod;
        this.Cod_venda = pCod_venda;
        this.Quantidade = pQuantidade;
    }

    public Item_venda() {    }

    public int getCodVenda() {
        return Cod_venda;
    }

    public void setCodVenda(int pCod_Prod) {
        this.Cod_Prod = pCod_Prod;
    }

    public int getCodProduto() {
        return Cod_Prod;
    }

    public void setCodProduto(int pCod_venda) {
        this.Cod_venda = pCod_venda;
    }

    public int getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(int pQuantidade) {
        this.Quantidade = pQuantidade;
    }

    public int insereItem() throws SQLException {
        super.pstmtI1.setInt(1, this.getCodProduto());
        super.pstmtI1.setInt(2, this.getCodVenda());
        super.pstmtI1.setInt(3, this.getQuantidade());
        return super.pstmtI1.executeUpdate();
    }
    
    public int AtualizaItem(int pCod_Prod, int pCod_venda) throws SQLException {
        super.pstmtI1.setInt(1, this.getQuantidade());
        super.pstmtI1.setInt(2, pCod_Prod);
        super.pstmtI1.setInt(3, pCod_venda);
        return super.pstmtI1.executeUpdate();
    }
    
        
    public int deleteItem(int pCod_Prod, int pCod_venda) throws SQLException {
        super.pstmtI3.setInt(1, pCod_Prod);
        super.pstmtI3.setInt(2, pCod_venda);
        return super.pstmtI3.executeUpdate();
    }

    public ResultSet selecionarItem(int pcodigoprod, int pcodigovenda) throws SQLException {
        super.pstmtI4.setInt(1, pcodigoprod);
        super.pstmtI4.setInt(2, pcodigovenda);
        return super.pstmtP4.executeQuery();
    }
}
