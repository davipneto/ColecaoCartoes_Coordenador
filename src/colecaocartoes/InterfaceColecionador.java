package colecaocartoes;

import java.rmi.*;

/**
 * A interface InterfaceColecionador possui os métodos remotos relacionados com
 * o acesso ao colecionador.
 *
 * @author Davi Pereira Neto
 * @author Geovana Franco Santos
 */
public interface InterfaceColecionador extends Remote {

    /**
     * Método para verificar se o colecionador deseja efetivar a transação.
     *
     * @param transacao com o número da transação
     * @return boolean com a decisão do colecionador
     * @throws RemoteException
     */
    public boolean desejaEfetivar(int transacao) throws RemoteException;

    /**
     * Método para efetivar uma transação.
     *
     * @param transacao com o número da transação
     * @throws RemoteException
     */
    public void efetivar(int transacao) throws RemoteException;

    /**
     * Método para abortar uma transação.
     *
     * @param transacao com o número da transação
     * @throws RemoteException
     */
    public void abortar(int transacao) throws RemoteException;

    /**
     * Método para remover a quantidade de cartão da coleção
     *
     * @param transacao com o número da transação
     * @param cartao com o tipo de cartão a ser removido
     * @param qntd com a quantidade a ser removida
     * @return boolean com o status final da transação
     * @throws RemoteException
     */
    public boolean removerCartão(int transacao, Colecao.Cartao cartao, Integer qntd) throws RemoteException;

    /**
     * Método para inserir uma quantidade de cartão na coleção
     *
     * @param transacao com o número da transação
     * @param cartao com o tipo de cartão a ser inserido
     * @param qntd com a quantidade a ser inserida
     * @return boolean com o status final da transação
     * @throws RemoteException
     */
    public boolean inserirCartão(int transacao, Colecao.Cartao cartao, Integer qntd) throws RemoteException;

    /**
     * Método que retorna a coleção do colecionador.
     *
     * @param transacao com o número da transação
     * @return com a coleção do colecionador
     * @throws RemoteException
     */
    public Colecao getColecao(int transacao) throws RemoteException;

}
