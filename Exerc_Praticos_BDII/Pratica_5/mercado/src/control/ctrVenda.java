package control;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import model.Venda;
import java.util.ArrayList;
import view.Venda.*;
/**
 *
 * @author carlos
 */
public class ctrVenda {
    ArrayList<Venda> todasVendas;//Lista com todos os produtos
    ctrPrincipal objCtrPrincipal;
    VendaCadastraView objlimVendaCadastra;
    VendaDeleteView objlimVendaDelete;
    VendaConsultaView objlimVendaLista;
    VendaAtualizaView objlimVendaatualiza;
    ResultSet prd;
    Venda v1 = new Venda();
    
    public ctrVenda(ctrPrincipal pCtrPrincipal) throws Exception {
        objCtrPrincipal = pCtrPrincipal;
        
    }
    
    public void insereVenda() throws SQLException {
        String aDadosForm[];
        objlimVendaCadastra = new VendaCadastraView();
        aDadosForm = objlimVendaCadastra.EnviaForm();
        Date d;
        d = converteData (aDadosForm[0]);
        if (d == null){
            objlimVendaCadastra.problemaInsereData();
        } else {
            v1 = new Venda(d, 0, aDadosForm[1]);
            v1.insereVenda();
            objlimVendaCadastra.InseridoComSucesso();
        }
        
    }
    
    public Date converteData (String dataST) {
        Date date = null;
        try {
           DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
           date = df.parse(dataST); 
        } catch (Exception e) {
        }
        return date;
    }
    
    public void problemeInsere(SQLException ex) {
        objlimVendaCadastra.problemaInsere(ex);
    }

    public void removeVenda() throws SQLException {
        String aDadosForm;
        objlimVendaDelete = new VendaDeleteView();
        aDadosForm = objlimVendaDelete.EnviaForm();
        if (v1.deleteVenda(Integer.parseInt(aDadosForm))> 0)
            objlimVendaDelete.DeletadoComSucesso();
        else{
            objlimVendaDelete.naoDeletou();
        }
    }
    
     public void problemeDeletar(SQLException ex) {
        objlimVendaDelete.problemaDelete(ex);
    }
     
    public void listaVenda() throws SQLException{
        prd = v1.ListarVenda();
        todasVendas =  new ArrayList<Venda>();
        while (prd.next()){
            v1 = new Venda(prd.getInt("Codigo"), prd.getDate("Data"), prd.getFloat("Valor_Total"), prd.getString("CPF_Vendedor"));
            todasVendas.add(v1);
        }
        objlimVendaLista = new VendaConsultaView(todasVendas);
    }
    
    public void atualizaVenda() throws SQLException
    {
        objlimVendaatualiza = new VendaAtualizaView();
        String codigoSt = objlimVendaatualiza.getText();
        
        prd = v1.BuscaVenda(Integer.parseInt(codigoSt));
        
        if ( !prd.next())
        {
            objlimVendaatualiza.erroAoEncontrar();
        }
        else
        {
            objlimVendaatualiza.atualiza(prd.getDate("Data"),prd.getString("CPF_Vendedor"));
            String campos[] = objlimVendaatualiza.getCampos();
            
            v1.atualizaVenda(Integer.parseInt(codigoSt),converteData(campos[0]),campos[1]);
            objlimVendaatualiza.confirmacao();
        }
    }
}