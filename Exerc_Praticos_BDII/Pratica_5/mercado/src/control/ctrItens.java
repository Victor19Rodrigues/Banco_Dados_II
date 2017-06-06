package control;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Item_venda;
import java.util.ArrayList;
import view.ItemVenda.*;
/**
 *
 * @author carlos
 */
public class ctrItens {
    ArrayList<Item_venda> todasItens;//Lista com todos os produtos
    ctrPrincipal objCtrPrincipal;
    ItemCadastraView objlimItemCadastra;
    ItemRemoveView objlimVendaremove;
    ResultSet prd;
    Item_venda iv1 = new Item_venda();
    
    public ctrItens (ctrPrincipal pCtrPrincipal) throws Exception {
        objCtrPrincipal = pCtrPrincipal;
        
    }
    
    public void insereItem() throws SQLException {
        String aDadosForm[];
        objlimItemCadastra = new ItemCadastraView();
        aDadosForm = objlimItemCadastra.EnviaForm();
        iv1 = new Item_venda(Integer.parseInt(aDadosForm[0]), Integer.parseInt(aDadosForm[1]), Integer.parseInt(aDadosForm[2]));
        iv1.insereItem();
    }
        
    
    public void problemeInsere(SQLException ex) {
        objlimItemCadastra.problemaInsere(ex);
    }

    public void InseridoSucesso() {
        objlimItemCadastra.InseridoComSucesso();
    }
    
    public void RemoveItem() throws SQLException {
        String aDadosForm[];
        objlimVendaremove = new ItemRemoveView();
        aDadosForm = objlimVendaremove.EnviaForm();
        if (iv1.deleteItem(Integer.parseInt(aDadosForm[0]), Integer.parseInt(aDadosForm[1]))> 0) {
            objlimVendaremove.RemovidoComSucesso();
        }
        else {
            objlimVendaremove.FalhaRemocao();
        }
        
    }
    
}