/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colecaocartoes_coordenador;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author davi
 */
public class CoordenadorImpl extends UnicastRemoteObject implements InterfaceCoordenador {
    
    List<ColecaoColecionador> colecoes;
    
    public CoordenadorImpl() throws RemoteException {
        //cria um registro do servidor no serviço de nomes na porta 1099
        Registry referenciaServicoNomes = LocateRegistry.createRegistry(1099);
        referenciaServicoNomes.rebind("RefCoordenador", this);
    }

    @Override
    public void efetivacaoOk() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void obterDecisao() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Colecao> consultarColecoes() throws RemoteException {
        List<Colecao> cols = new ArrayList();
        for (ColecaoColecionador cc: colecoes)
            cols.add(cc.colecao);
        return cols;
    }
    
}
