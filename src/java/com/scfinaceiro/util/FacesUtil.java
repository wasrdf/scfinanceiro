/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scfinaceiro.util;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Was
 */
public class FacesUtil {
    
    public static void saveMessage(String pMsg,String pTipo) {
        if(pTipo.equals("Sucesso")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, pMsg, ""));
        } else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, pMsg, ""));
        }
     
    }
    
    
     public static void mudarPagina(String pPage) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(ctx.getExternalContext().getRequestContextPath() + pPage);
        } catch (IOException ex) {
           throw  new RuntimeException(ex.getMessage());
        }
    }
    
}
