package colecaocartoes;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * A Classe Coleção implementa uma coleção de cartões, com o id do colecionador,
 * um mapa que relaciona os tipos de cartões e a quantidade de cartões que o
 * colecionador possui e uma trava, utilizada para garantir que nenhum dado seja
 * visualizado ou alterado enquanto alguma alteração esteja acontecendo.
 *
 * @author Davi Pereira Neto
 * @author Geovana Franco Santos
 */
public class Colecao implements Serializable {

    public long id_colecionador;            //id do colecionador
    public Map<Cartao, Integer> cartoesQtd; //map que relaciona os cartões e suas quantidades
    public boolean trava;                   //trava da coleção

    /**
     * Construtor que inicializa as variáveis da coleção e associa o id do
     * colecionador com a coleção.
     *
     * @param id_colecionador com o id do colecionador
     */
    public Colecao(long id_colecionador) {
        this.id_colecionador = id_colecionador;
        this.cartoesQtd = new HashMap();

        for (Cartao cartao : Cartao.values()) {
            cartoesQtd.put(cartao, 0);
        }
        this.trava = false;
    }

    /**
     * Enum com os tipos de cartões disponíveis na coleção.
     */
    public enum Cartao {
        CIDADE, ANIMAL, PAISAGEM
    }

    /**
     * Método que retorna o id do colecionador que possui essa coleção.
     *
     * @return do tipo long com o id do colecionador
     */
    public long getId_colecionador() {
        return id_colecionador;
    }

    /**
     * Método que seta o id do colecionador que possui essa coleção.
     *
     * @param id_colecionador com o id do colecionador
     */
    public void setId_colecionador(long id_colecionador) {
        this.id_colecionador = id_colecionador;
    }

    /**
     * Método que retorna o map com as quantidade de cada cartão da coleção.
     *
     * @return com o map que relaciona cartões e suas quantidades
     */
    public Map<Cartao, Integer> getCartoesQtd() {
        return cartoesQtd;
    }

    /**
     * Método que seta o map de quantidade dos cartões
     *
     * @param cartoesQtd com o map de quantidade de cartões
     */
    public void setCartoesQtd(Map<Cartao, Integer> cartoesQtd) {
        this.cartoesQtd = cartoesQtd;
    }

    /**
     * Método que retorna se a coleção está travada ou não
     *
     * @return do tipo boolean com o estado da trava
     */
    public boolean isTrava() {
        return trava;
    }

    /**
     * Método que seta a trava da coleção
     *
     * @param trava com o status da trava a ser setado
     */
    public void setTrava(boolean trava) {
        this.trava = trava;
    }
}
