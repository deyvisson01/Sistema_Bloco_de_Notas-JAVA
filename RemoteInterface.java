
import java.io.*;
import java.rmi.*;

public interface RemoteInterface extends Remote{

	// RETORNA O STATUS DO SERVIÇO
    public String getStatus() throws RemoteException;
    
	// LISTAR AS NOTAS DO DIRETÓRIO CORRESPONDENTE
    public File[] getListFiles(String Name) throws RemoteException;
    
	// BLOCO DE MÉTODOS DE ALTERAÇÃO DAS NOTAS
    public boolean CriaNote(String Name) throws RemoteException;
    public String LerNote(String Name) throws RemoteException;
    public boolean EditNote(String Name, String Conteudo) throws RemoteException;
    public boolean DelNote(String Name) throws RemoteException;
    public int ContCaracter(String Name, char c) throws RemoteException;


    public Pessoa TrataPessoa(String nome, int identidade, String nascimento) throws RemoteException;
    public boolean EscrevePessoa(Pessoa pessoa) throws RemoteException;
    public String LerPessoa() throws RemoteException;
}

