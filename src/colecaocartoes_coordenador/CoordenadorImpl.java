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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author davi
 */
public class CoordenadorImpl extends UnicastRemoteObject implements InterfaceCoordenador {
    
    List<InterfaceColecionador> colecionadores;
    
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
        System.out.println("Comecou a transacao");
        List<Colecao> colecoes = new ArrayList();
        List<Thread> threads = new ArrayList();
        for (InterfaceColecionador col: colecionadores) {
            Thread t = new Thread() {
                @Override
                public void run() {
                    System.out.println("Start Get colecao Interface " + col.toString());
                    try {
                        colecoes.add(col.getColecao());
                        System.out.println("Finish Get colecao Interface " + col.toString());
                    } catch (RemoteException ex) {
                        Logger.getLogger(CoordenadorImpl.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println("Erro Get colecao Interface " + col.toString());
                    }
                }
            };
            threads.add(t);
            t.start();
        }
        for (Thread t: threads) {
            try {
                t.join(5000);
            } catch (InterruptedException ex) {
                System.out.println("Timeout");
                return null;
            }
        }
        if (colecoes.size() == colecionadores.size()) {
            System.out.println("Transacao Efetivada");
            return colecoes;
        }
        System.out.println("Transacao nao efetivada");
        return null;
    }

    @Override
    public long registraColecionador(InterfaceColecionador col) throws RemoteException {
        colecionadores.add(col);
        return System.currentTimeMillis();
    }
    
}
