/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colecaocartoes;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Davi Pereira Neto
 * @author Geovana Franco Santos
 */
public class CoordenadorImpl extends UnicastRemoteObject implements InterfaceCoordenador {

    public Map<Long, InterfaceColecionador> colecionadores = new HashMap<>();

    public CoordenadorImpl() throws RemoteException {
        //cria um registro do servidor no serviço de nomes na porta 1099
        Registry referenciaServicoNomes = LocateRegistry.createRegistry(1099);
        referenciaServicoNomes.rebind("RefCoordenador", this);
        System.out.println("Servidor funcionando");
    }

    @Override
    public void efetivacaoOk() throws RemoteException {
        System.out.println("Deu certo chamar coordenador");
    }

    @Override
    public void obterDecisao() throws RemoteException {

    }

    @Override
    public List<Colecao> consultarColecoes() throws RemoteException {
        System.out.println("Comecou a transacao");
        List<Colecao> colecoes = new ArrayList();
        List<Thread> threads = new ArrayList();
        for (InterfaceColecionador col : colecionadores.values()) {
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
        for (Thread t : threads) {
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
        long id = System.currentTimeMillis();
        colecionadores.put(id, col);
        return id;
    }

    @Override
    public void trocarCartoes(Colecao.Cartao tipo1, Colecao.Cartao tipo2, Integer qntd1, Integer qntd2, Long id_colec1, Long id_colec2) throws RemoteException {
        Thread t = new Thread() {
            @Override
            public void run() {
                boolean trans1 = false;
                boolean trans2 = false;
                boolean trans3 = false;
                boolean trans4 = false;
                
                for (Long id : colecionadores.keySet()) {
                    if (id.equals(id_colec1)) {
                        try {
                            trans1 = colecionadores.get(id).removerCartão(tipo1, qntd1);
                            trans2 = colecionadores.get(id).inserirCartão(tipo2, qntd2);
                        } catch (RemoteException ex) {
                            Logger.getLogger(CoordenadorImpl.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (id.equals(id_colec2)) {
                        try {
                            trans3 = colecionadores.get(id).removerCartão(tipo2, qntd2);
                            trans4 = colecionadores.get(id).inserirCartão(tipo1, qntd1);
                        } catch (RemoteException ex) {
                            Logger.getLogger(CoordenadorImpl.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                if (!trans1) {
                    System.out.println("Remover 1 deu errado");
                } else {
                    System.out.println("Remover 1 deu certo");
                }
                if (!trans2) {
                    System.out.println("Inserir 1 deu errado");
                } else {
                    System.out.println("Inserir 1 deu certo");
                }
                if (!trans3) {
                    System.out.println("Remover 2 deu errado");
                } else {
                    System.out.println("Remover 2 deu certo");
                }
                if (!trans4) {
                    System.out.println("Inserir 2 deu errado");
                } else {
                    System.out.println("Inserir 2 deu certo");
                }
            }
        };
        t.start();
    }

}
