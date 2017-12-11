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
import javax.swing.JOptionPane;

/**
 * A classe CoordenadorImpl implementa os métodos remotos da
 * InterfaceCoordenador.
 *
 * @author Davi Pereira Neto
 * @author Geovana Franco Santos
 */
public class CoordenadorImpl extends UnicastRemoteObject implements InterfaceCoordenador {

    //map de ids e colecionadores participantes
    public Map<Long, InterfaceColecionador> colecionadores = new HashMap<>();
    //numero para controle das transações
    public int trans = 0;

    /**
     * Construtor da classe que cria um registro e o deixa disponível.
     *
     * @throws RemoteException
     */
    public CoordenadorImpl() throws RemoteException {
        //cria um registro do servidor no serviço de nomes na porta 1099
        Registry referenciaServicoNomes = LocateRegistry.createRegistry(1099);
        referenciaServicoNomes.rebind("RefCoordenador", this);
        System.out.println("Servidor funcionando");
    }

    /**
     * Método que efetiva uma transação.
     *
     * @param transacao com o número da transação
     * @throws RemoteException
     */
    @Override
    public void efetivacaoOk(int transacao) throws RemoteException {

    }

    /**
     * Método que pergunta aos colecionadores se pode ou não efetivar transação.
     *
     * @param transacao com o número da transação
     * @throws RemoteException
     */
    @Override
    public void obterDecisao(int transacao) throws RemoteException {

    }

    /**
     * Consulta as Coleções de todos os Colecionadores do Sistema.
     *
     * @param transacao com o número da transação
     * @return uma lista de colecoes
     * @throws RemoteException
     */
    @Override
    public List<Colecao> consultarColecoes(int transacao) throws RemoteException {
        List<Colecao> colecoes = new ArrayList();
        List<Thread> threads = new ArrayList();
        for (InterfaceColecionador col : colecionadores.values()) {
            Thread t = new Thread() {
                @Override
                public void run() {
                    System.out.println("Start Get colecao Interface " + col.toString());
                    try {
                        colecoes.add(col.getColecao(transacao));
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

    /**
     * Registra o colecionador no sistema.
     *
     * @param transacao com o número da transação
     * @param col a interface do colecionador
     * @return um id único para o colecionador
     * @throws RemoteException
     */
    @Override
    public long registraColecionador(int transacao, InterfaceColecionador col) throws RemoteException {
        //cria um id com base no tempo de sistema
        long id = System.currentTimeMillis();
        //adiciona colecionador e id no map
        colecionadores.put(id, col);
        //retora o id criado
        return id;
    }

    /**
     * Método que realiza a troca de cartões entre os colecionadores.
     *
     * @param transacao com o número da transação
     * @param tipo1 com o tipo de cartão a ser trocado
     * @param tipo2 com o tipo de cartão a ser trocado
     * @param qntd1 com a quantidade a ser trocada
     * @param qntd2 com a quantidade a ser trocada
     * @param id_colec1 com o id do colecionador
     * @param id_colec2 com o id do colecionador
     * @throws RemoteException
     */
    @Override
    public void trocarCartoes(int transacao, Colecao.Cartao tipo1, Colecao.Cartao tipo2, Integer qntd1, Integer qntd2, Long id_colec1, Long id_colec2) throws RemoteException {
        //cria thread que tentará realizar troca de cartões
        System.out.println("Id Colecionador 1: " + id_colec1);
        System.out.println("Id colecionador 2: " + id_colec2);
        Thread t = new Thread() {
            @Override
            public void run() {
                //coloca status de todas as transações criadas como falso
                boolean trans1 = false;
                boolean trans2 = false;
                boolean trans3 = false;
                boolean trans4 = false;
                InterfaceColecionador colecionador1 = null;
                InterfaceColecionador colecionador2 = null;
                //para todo colecionador
                for (Long id : colecionadores.keySet()) {
                    //se o id é igual ao colecionador 1
                    if (id.equals(id_colec1)) {
                        colecionador1 = colecionadores.get(id);
                    }//se o id é igual ao colecionador 2
                    else if (id.equals(id_colec2)) {
                        colecionador2 = colecionadores.get(id);
                    }
                }
                if (colecionador1 != null) {
                    try {
                        //pergunta ao colecionador 1 se deseja efetivar a transação
                        //remove cartão 1 do colecionador 1 e atualiza status da transação
                        trans1 = colecionador1.desejaEfetivar(transacao, "remover", tipo1, qntd1);
                        //inserte cartão 2 no colecionado 1 e atualiza status da transação
                        trans2 = colecionador1.desejaEfetivar(transacao, "inserir", tipo2, qntd2);
                    } catch (RemoteException ex) {
                        Logger.getLogger(CoordenadorImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } //se o id é igual ao colecionador 2
                else{
                    System.out.println("nao achou 1");
                }
                if (colecionador2 != null) {
                    try {
                        //remove cartão 2 do colecionador 2 e atualiza status da transação
                        trans3 = colecionador2.desejaEfetivar(transacao, "remover", tipo2, qntd2);
                        //inserte cartão 1 no colecionado 2 e atualiza status da transação
                        trans4 = colecionador2.desejaEfetivar(transacao, "inserir", tipo1, qntd1);
                    } catch (RemoteException ex) {
                        Logger.getLogger(CoordenadorImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    System.out.println("nao achou 2");
                }
               

                //se alguma das transações deram errado
                if (!trans1 || !trans2 || !trans3 || !trans4) {
                    System.out.println("Alguma transação deu errado\nDesfazer últimas transações!");
                    if (colecionador1 != null) {
                        try {
                            colecionador1.abortar(transacao);
                        } catch (RemoteException ex) {
                            Logger.getLogger(CoordenadorImpl.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if (colecionador2 != null) {
                        try {
                            colecionador2.abortar(transacao);
                        } catch (RemoteException ex) {
                            Logger.getLogger(CoordenadorImpl.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Transação não efetivada");
                } else {
                    if (colecionador1 != null) {
                        try {
                            colecionador1.efetivar(transacao);
                        } catch (RemoteException ex) {
                            Logger.getLogger(CoordenadorImpl.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if (colecionador2 != null) {
                        try {
                            colecionador2.efetivar(transacao);
                        } catch (RemoteException ex) {
                            Logger.getLogger(CoordenadorImpl.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Transação efetivada");
                }

            }
        };

        //inicia thread
        t.start();
    }

}
