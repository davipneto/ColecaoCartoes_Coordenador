
package colecaocartoes;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Davi Pereira Neto
 * @author Geovana Franco Santos
 */
public class Colecao implements Serializable {
    
    public long id_colecionador;
    public Map<Cartao,Integer> cartoesQtd;
    public boolean trava;

    public Colecao(long id_colecionador) {
        this.id_colecionador = id_colecionador;
        this.cartoesQtd = new HashMap();
        
        for (Cartao cartao: Cartao.values()) {
            cartoesQtd.put(cartao, 0);
        }
        this.trava = false;
    }
    
    public enum Cartao {
        CIDADE, ANIMAL, PAISAGEM
    }

    public long getId_colecionador() {
        return id_colecionador;
    }

    public void setId_colecionador(long id_colecionador) {
        this.id_colecionador = id_colecionador;
    }

    public Map<Cartao, Integer> getCartoesQtd() {
        return cartoesQtd;
    }

    public void setCartoesQtd(Map<Cartao, Integer> cartoesQtd) {
        this.cartoesQtd = cartoesQtd;
    }
    
    public boolean isTrava() {
        return trava;
    }

    public void setTrava(boolean trava) {
        this.trava = trava;
    }
}
