package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Produto extends DAO {
    int Codigo, Quant_Estoque;
    String Nome;
    double Preco;
    /*Tomar cuidado pois o codigo está em auto incremento*/
    /*Necessário quando pegar dados do banco mas quando for inserir não*/
    public Produto(int pCodigo, int pQuant_Estoque, String pNome, double pPreco) {
        this.Codigo = pCodigo;/*CUIDADO*/
        this.Quant_Estoque = pQuant_Estoque;
        this.Nome = pNome;
        this.Preco = pPreco;
    }
    /*Quando for inserir usar esse metodo construtor aqui*/
    public Produto(int pQuant_Estoque, String pNome, double pPreco) {
        this.Quant_Estoque = pQuant_Estoque;
        this.Nome = pNome;
        this.Preco = pPreco;
    }

    public Produto() {
    /*Contrutor só para usar os metodos da classe*/    
    }

    public int getCodigo() {
        return Codigo;
    }
    /*NUNCA USAR*/
    public void setCodigo(int pCodigo) {
        this.Codigo = pCodigo;
    }

    public int getQuant_estoque() {
        return Quant_Estoque;
    }

    public void setQuant_estoque(int pQuant_Estoque) {
        this.Quant_Estoque = pQuant_Estoque;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String pNome) {
        this.Nome = pNome;
    }

    public double getPreco() {
        return Preco;
    }

    public void setPreco(double pPreco) {
        this.Preco = pPreco;
    }
    /*Aqui não é preciso passar o código*/
    public int insereProduto() throws SQLException {
        super.pstmtP1.setString(1, this.getNome());
        super.pstmtP1.setInt(2, this.getQuant_estoque());
        super.pstmtP1.setDouble(3, this.getPreco());
        return super.pstmtP1.executeUpdate();/*Retorna verdade ou falso*/
    }
    public int atualizaProduto(int pCodigo) throws SQLException {
        super.pstmtP2.setString(2, this.getNome());
        super.pstmtP2.setInt(1, this.getQuant_estoque());
        super.pstmtP2.setDouble(3, this.getPreco());
        super.pstmtP2.setInt(4, pCodigo);
        return super.pstmtP2.executeUpdate();
    }

    public int deleteProduto(int pCodigo) throws SQLException {
        System.err.println(pCodigo);
        super.pstmtP3.setInt(1, pCodigo);
        return super.pstmtP3.executeUpdate();
    }

    public ResultSet selecionarProduto(int pCodigo) throws SQLException {
        super.pstmtP4.setInt(1, pCodigo);
        return super.pstmtP4.executeQuery();
    }
    
    public ResultSet ListarProduto() throws SQLException {
        return super.pstmtP5.executeQuery();
    }

}
