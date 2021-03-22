
import java.io.*;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class Servente extends UnicastRemoteObject implements RemoteInterface{

    public Servente() throws RemoteException{
        super();
    }

	// RETORNA OK SE A CLASSE FOR ACESSADA, INFORMANDO ASSIM QUE O SERVIÇO ESTÁ ONLINE
    @Override
    public String getStatus() throws RemoteException {
        return "Ok";
    }

    // ########################## LISTAR AS NOTAS ######################################
    @Override
    public File[] getListFiles(String Name) throws RemoteException {
        File files = new File(Name);
        File listFiles[] = files.listFiles();
        return listFiles;
    }
    
    // ########################## LEITURA DA NOTA ######################################
    @Override
    public String LerNote(String Name) throws RemoteException {
        
            String arquivo = Name;
            String FolderNotes = "NOTES/";
            File teste = new File(FolderNotes+arquivo);
            String linha = null;
            if (teste.exists()) {
		
                try {
                    FileReader fr = new FileReader(teste);
                    BufferedReader br = new BufferedReader(fr);
                                        
                    linha = br.readLine();
                    
                    br.close();
                    fr.close();
                    
                } catch (IOException ex) {
                System.out.println("#################################################\n"
				  +"        ERRO NA LEITURA DE "+arquivo+"      \n"
				  +"#################################################");
                }
            }else{
		System.out.println("#################################################\n"
				  +"        A NOTA "+arquivo+" NÃO EXISTE!      \n"
				  +"#################################################");
            }
        return linha;    
    }
    
    // ########################## ESCRITA NA NOTA ######################################
    @Override
    public boolean EditNote(String Name, String Conteudo) throws RemoteException {
        boolean result = false;

        String arquivo = Name;
        String conteudo = Conteudo;
        String FolderNotes = "NOTES/";
        File teste = new File(FolderNotes+arquivo);

        if (teste.exists()) {
            try {
                FileWriter fw = new FileWriter(teste);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(conteudo);
                bw.newLine();
                
                bw.close();
                fw.close();

                result = true;
            } catch (IOException ex) {
		System.out.println("#################################################\n"
				  +"        ERRO NA EDIÇÃO DE "+arquivo+"        \n"
				  +"#################################################");
            }
        }else{
            System.out.println("#################################################\n"
			      +"        A NOTA "+arquivo+" NÃO EXISTE!      \n"
			      +"#################################################");
        }
    return result;     
    }
    
    // ########################## DELETAR A NOTA #####################################
    @Override
    public boolean DelNote(String Name) throws RemoteException {
        boolean result = false;
        String folderNotes = "NOTES/";
        String arquivo = Name;
        File files = new File(folderNotes+arquivo);
        
            if (files.exists()) {
                files.delete();
                result = true;  
            }else{
                System.out.println("#################################################\n"
				  +"        A NOTA "+arquivo+" NÃO EXISTE!      \n"
				  +"#################################################");
            }
          
        return result;    
    }
    
    // ########################## CRIAR A NOTA ######################################
    @Override
    public boolean CriaNote(String Name) throws RemoteException {
        boolean result = false;
        String folderNotes = "NOTES/";
        String arquivo = Name;
        File files = new File(folderNotes+arquivo);
       
        try {
            files.createNewFile();
            result = true;
        } catch (IOException e) {
		System.out.println("#################################################\n"
				  +"        ERRO NA CRIAÇÃO DA NOTA "+arquivo+"      \n"
				  +"#################################################");
        }    
        return result;
    }

	// CONTAR QUANTIDADE DA LETRA ESCOLHIDA EM UM DOS ARQUIVOS
	public int ContCaracter(String Name, char c) throws RemoteException{

		String folderNotes = "NOTES/";
        	String arquivo = Name;
		char cc = c;
		int cont = 0;
                try{
			FileInputStream input = new FileInputStream(folderNotes+Name);
			//int cont = 0;
			int i = 0;
			
			while((i = input.read()) != -1) { 
				char v = (char) i;
				if(cc == v)
				cont++;
			}
		}	
		catch(IOException e){
			System.out.println("#################################################\n"
					  +"        ERRO NO ARQUIVO "+arquivo+"      \n"
					  +"#################################################");
		}		
		return cont;
	}
	
	// CRIAR OBJETO DO TIPO PESSOA
	public Pessoa TrataPessoa(String nome, int identidade, String nascimento) throws RemoteException{
		
		return new Pessoa(nome, identidade, nascimento);

	}

	// ESCREVER OS ATRIBUTOS DE PESSOA NO ARQUIVO
	public boolean EscrevePessoa(Pessoa pessoa) throws RemoteException{
		boolean op = false;
			try{
				FileOutputStream fos = new FileOutputStream("PESSOA/Pessoa.out."); 
				ObjectOutputStream obos = new ObjectOutputStream(fos); 
				obos.writeObject(pessoa);
				obos.close();
				op = true;
			} catch(IOException e){
				System.out.println("#################################################\n"
						  +"           ERRO NA ESCRITA DE PESSOA     \n"
						  +"#################################################");
			}
		return op;
	}

	// LER OS ATRIBUTOS DE PESSOA QUE ESTÃO NO ARQUIVO
	public String LerPessoa() throws RemoteException{
		String linha = null;
			try{
				ObjectInputStream leitura = new ObjectInputStream(new FileInputStream("PESSOA/Pessoa.out."));

				Pessoa lerPessoa = (Pessoa) leitura.readObject();

				linha = "\n Nome: "+ lerPessoa.getNome()+"\n Identidade: "+lerPessoa.getIdentidade()+"\n Nascimento: "+lerPessoa.getNascimento()+"\n";
				
				leitura.close();
			} catch(IOException e){
				System.out.println("#################################################\n"
						  +"           ERRO NA LEITURA DE PESSOA       \n"
						  +"#################################################");
			}catch ( ClassNotFoundException e ) {
				System.out.println("#################################################\n"
						  +"     ERRO NA LEITURA DE PESSOA OBJETO INVALIDO!      \n"
						  +"#################################################");
			}
		return linha;
	}
}














