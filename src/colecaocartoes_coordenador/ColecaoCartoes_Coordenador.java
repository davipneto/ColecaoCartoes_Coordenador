/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colecaocartoes_coordenador;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author davi
 */
public class ColecaoCartoes_Coordenador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            CoordenadorImpl coordimpl = new CoordenadorImpl();
        } catch (RemoteException ex) {
            Logger.getLogger(ColecaoCartoes_Coordenador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
