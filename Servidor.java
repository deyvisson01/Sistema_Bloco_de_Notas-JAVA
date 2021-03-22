
import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;
import java.rmi.server.UnicastRemoteObject;
public class Servidor {
    public static String NameService = "ServidorBlocoNotas";

    public static void main(String[] args) {
        System.setSecurityManager(new SecurityManager());

        try{
            int port = 8512;
            RemoteInterface ri = new Servente();
            Registry registry = LocateRegistry.createRegistry(port);
            Naming.rebind(NameService, ri);
            System.out.println("#  EXECUTANDO "+NameService+"! || TRABALHO 03 - POO MANIPULAÇÃO DE ARQUIVOS!#");

        }catch(MalformedURLException | RemoteException e){
            System.out.println(e.getMessage());
        }
    }
}
