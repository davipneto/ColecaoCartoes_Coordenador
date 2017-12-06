package colecaocartoes;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe principal do coordenador
 *
 * @author Davi Pereira Neto
 * @author Geovana Franco Santos
 */
public class ColecaoCartoes_Coordenador {

    /**
     * Método principal.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            //cria uma instância do coordenador
            CoordenadorImpl coordimpl = new CoordenadorImpl();
        } catch (RemoteException ex) {
            Logger.getLogger(ColecaoCartoes_Coordenador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
