package colecaocartoes;

import java.rmi.*;
import java.util.List;

/**
 * A interface InterfaceCoordenador possui os métodos remotos relacionados ao
 * coordenador.
 *
 * @author Davi Pereira Neto
 * @author Geovana Franco Santos
 */
public interface InterfaceCoordenador extends Remote {

    /**
     * Método que realiza a troca de cartões entre os colecionadores.
     *
     * @param tipo1 com o tipo de cartão a ser trocado
     * @param tipo2 com o tipo de cartão a ser trocado
     * @param qntd1 com a quantidade a ser trocada
     * @param qntd2 com a quantidade a ser trocada
     * @param id_colec1 com o id do colecionador
     * @param id_colec2 com o id do colecionador
     * @throws RemoteException
     */
    public void trocarCartoes(Colecao.Cartao tipo1, Colecao.Cartao tipo2, Integer qntd1, Integer qntd2, Long id_colec1, Long id_colec2) throws RemoteException;

    /**
     * Consulta as Coleções de todos os Colecionadores do Sistema.
     *
     * @return uma lista de colecoes
     * @throws RemoteException
     */
    public List<Colecao> consultarColecoes() throws RemoteException;

    /**
     * Registra o colecionador no sistema.
     *
     * @param col a interface do colecionador
     * @return um id único para o colecionador
     * @throws RemoteException
     */
    public long registraColecionador(InterfaceColecionador col) throws RemoteException;

}
