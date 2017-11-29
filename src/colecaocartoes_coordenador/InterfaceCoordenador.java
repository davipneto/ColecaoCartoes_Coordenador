/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colecaocartoes_coordenador;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author davi
 */
public interface InterfaceCoordenador extends Remote {
    
    public void efetivacaoOk() throws RemoteException;
    public void obterDecisao() throws RemoteException;
    
    /**
     * Consulta as Coleções de todos os Colecionadores do Sistema
     * @return uma lista de colecoes
     * @throws RemoteException 
     */
    public List<Colecao> consultarColecoes() throws RemoteException;
    
    /**
     * Registra o colecionador no sistema
     * @param col a interface do colecionador
     * @return um id único para o colecionador
     * @throws RemoteException 
     */
    public long registraColecionador(InterfaceColecionador col) throws RemoteException;
    
}
