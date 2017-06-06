package model;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Venda extends DAO {
    
    int Codigo;
    Date Data;
    double Valor_Total;
    String CPF_Vendedor;

    public Venda(int pCodigo, Date pData, double pValor_Total, String pCPF_Vendedor) {
        Codigo = pCodigo;
        Data = pData;
        Valor_Total = pValor_Total;
        CPF_Vendedor = pCPF_Vendedor;
    }
    /*Usar este construtor para inserir*/
    public Venda(Date pData, double pValor_Total, String pCPF_Vendedor) {
        Data = pData;
        Valor_Total = pValor_Total;
        CPF_Vendedor = pCPF_Vendedor;
    }
    //só para as chamadas dos metodos
    public Venda () {
    }

    public int getCodigo() {
        return Codigo;
    }
    /*nunca usar*/
    public void setCodigo(int pCodigo) {
        this.Codigo = pCodigo;
    }

    public Date getData() {
        return Data;
    }

    public void setData(Date pData) {
        this.Data = pData;
    }

    public double getValor_total() {
        return Valor_Total;
    }

    public void setValor_total(double pValor_Total) {
        this.Valor_Total = pValor_Total;
    }

    public String getCpf_vendedor() {
        return CPF_Vendedor;
    }

    public void setCpf_vendedor(String pCPF_Vendedor) {
        this.CPF_Vendedor = pCPF_Vendedor;
    }
    /*Não precisa do codigo*/
    public int insereVenda() throws SQLException {
        super.pstmtV1.setDate(1, new java.sql.Date(this.getData().getTime()));
        super.pstmtV1.setDouble(2, this.getValor_total());
        super.pstmtV1.setString(3, this.getCpf_vendedor());
        return super.pstmtV1.executeUpdate();
    }

    public int atualizaVenda(int pCodigo, Date data, String cpf) throws SQLException {
        super.pstmtV2.setDate(1, new java.sql.Date(data.getTime()));
        super.pstmtV2.setString(2, cpf);
        super.pstmtV2.setInt(3, pCodigo);
        return super.pstmtV2.executeUpdate();
    }
    
    public int deleteVenda(int pCodigo) throws SQLException {
        super.pstmtV3.setInt(1, pCodigo);
        return super.pstmtV3.executeUpdate();
    }
    
    public ResultSet selecionarVenda(int pCodigo) throws SQLException {
        super.pstmtV3.setInt(1, pCodigo);
        return super.pstmtV3.executeQuery();
    }
    
    public ResultSet ListarVenda() throws SQLException {
        return super.pstmtV5.executeQuery();
    }
   
    public ResultSet BuscaVenda(int codigo) throws SQLException
    {
        super.pstmtV4.setInt(1, codigo);
        return super.pstmtV4.executeQuery();
    }
}
