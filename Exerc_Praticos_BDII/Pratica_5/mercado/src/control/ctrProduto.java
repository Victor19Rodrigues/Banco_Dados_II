package control;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Produto;
import java.util.ArrayList;
import view.Produto.*;
/**
 *
 * @author carlos
 */
public class ctrProduto {
    ArrayList<Produto> todosProdutos;//Lista com todos os produtos
    ctrPrincipal objCtrPrincipal;
    ProdutoCadastraView objlimProdutoCadastra;
    ProdutoDeleteView objlimProdutoDelete;
    ProdutoConsultaView objlimProdutoLista;
    ResultSet prd;
    Produto p1 = new Produto();
    
    public ctrProduto(ctrPrincipal pCtrPrincipal) throws Exception {
        objCtrPrincipal = pCtrPrincipal;
        
    }
    
    public void insereProduto() throws SQLException {
        String aDadosForm[];
        objlimProdutoCadastra = new ProdutoCadastraView();
        aDadosForm = objlimProdutoCadastra.EnviaForm();
        p1 = new Produto(Integer.parseInt(aDadosForm[0]), aDadosForm[1],Double.parseDouble(aDadosForm[2]));
        p1.insereProduto();
        objlimProdutoCadastra.InseridoComSucesso();
    }

    public void problemeInsere(SQLException ex) {
        objlimProdutoCadastra.problemaInsere(ex);
    }

    public void removeProduto() throws SQLException {
        String aDadosForm;
        objlimProdutoDelete = new ProdutoDeleteView();
        aDadosForm = objlimProdutoDelete.EnviaForm();
        if (p1.deleteProduto(Integer.parseInt(aDadosForm))> 0)
            objlimProdutoDelete.DeletadoComSucesso();
        else{
            objlimProdutoDelete.naoDeletou();
        }
    }
    
    public void problemeDeletar(SQLException ex) {
        objlimProdutoDelete.problemaDelete(ex);
    }
    
    public void listaProdutos () throws SQLException{
        prd = p1.ListarProduto();
        todosProdutos =  new ArrayList<Produto>();
        while (prd.next()){
            p1 = new Produto(prd.getInt("Codigo"), prd.getInt("Quant_Estoque"), prd.getString("Nome"), prd.getDouble("Preco"));
            todosProdutos.add(p1);
        }
        objlimProdutoLista = new ProdutoConsultaView(todosProdutos);
    }

}
