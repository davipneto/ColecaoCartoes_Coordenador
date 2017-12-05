/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colecaocartoes;

import java.rmi.*;

/**
 *
 * @author davi
 */
public interface InterfaceColecionador extends Remote {
    
    public boolean desejaEfetivar() throws RemoteException;
    public void efetivar() throws RemoteException;
    public void abortar() throws RemoteException;
    public boolean removerCartão(Colecao.Cartao cartao, Integer qntd) throws RemoteException;
    public boolean inserirCartão(Colecao.Cartao cartao, Integer qntd) throws RemoteException;
    public Colecao getColecao() throws RemoteException;
    
}
