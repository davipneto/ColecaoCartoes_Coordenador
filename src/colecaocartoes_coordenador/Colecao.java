/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colecaocartoes_coordenador;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author davi
 */
public class Colecao implements Serializable {
    
    long id_colecionador;
    Map<Cartao,Integer> cartoesQtd;
    Map<Cartao,Double> cartoesPreco;
    boolean trava;

    public Colecao(long id_colecionador) {
        this.id_colecionador = id_colecionador;
        this.cartoesQtd = new HashMap();
        this.cartoesPreco = new HashMap();
        Random random = new Random();
        for (Cartao cartao: Cartao.values()) {
            cartoesQtd.put(cartao, 0);
            double preco = random.nextDouble() * 10;
            cartoesPreco.put(cartao, preco);
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

    public Map<Cartao, Double> getCartoesPreco() {
        return cartoesPreco;
    }

    public void setCartoesPreco(Map<Cartao, Double> cartoesPreco) {
        this.cartoesPreco = cartoesPreco;
    }

    public boolean isTrava() {
        return trava;
    }

    public void setTrava(boolean trava) {
        this.trava = trava;
    }
}
