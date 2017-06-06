/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import view.*;

/**
 *
 * @author carlos
 */
public class ctrPrincipal {
    viewPrincipal objALimPrincipal;
    ctrProduto objCtrProduto;
    ctrVenda objCtrVenda;
    ctrItens objCtrItem;

    public viewPrincipal getObjALimPrincipal() {
        return objALimPrincipal;
    }

    public void setObjALimPrincipal(viewPrincipal objALimPrincipal) {
        this.objALimPrincipal = objALimPrincipal;
    }

    public ctrProduto getObjCtrProduto() {
        return objCtrProduto;
    }
    
    public ctrItens getObjCtrItem() {
        return objCtrItem;
    }
    
    public ctrVenda getObjCtrVenda() {
        return objCtrVenda;
    }

    public void setObjCtrProduto(ctrProduto objCtrProduto) {
        this.objCtrProduto = objCtrProduto;
    }
    
     public void run() throws Exception {
        objCtrProduto = new ctrProduto(this);
        objCtrVenda = new ctrVenda(this);
        objCtrItem = new ctrItens(this);
        objCtrProduto.p1.inicializaJdbc();
        objALimPrincipal = new viewPrincipal(this);
    }
}
