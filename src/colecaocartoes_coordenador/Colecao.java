/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colecaocartoes_coordenador;

import java.io.Serializable;

/**
 *
 * @author davi
 */
public class Colecao implements Serializable {
    
    String descricao;
    String nome_colecionador;
    int qtde;
    double preco;
    boolean trava;

    public Colecao(String descricao, String nome_colecionador, int qtde, double preco) {
        this.descricao = descricao;
        this.nome_colecionador = nome_colecionador;
        this.qtde = qtde;
        this.preco = preco;
        this.trava = false;
    }
    
}
