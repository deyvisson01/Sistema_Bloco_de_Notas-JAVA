
import java.io.*;
import java.net.MalformedURLException;
import java.rmi.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        System.setSecurityManager(new SecurityManager());        
        RemoteInterface ri = null;

        Scanner scanner = new Scanner(System.in);
	Scanner scan = new Scanner(System.in);

        try {            
		// TENTA BUSCAR O SERVIÇO INICIADO NA INTERFACE
            System.out.println(Arrays.toString(Naming.list("ServidorBlocoNotas")));
            	// INSTANCIA UM OBJETO COM BASE NO SERVIÇO DESEJADO
            ri=(RemoteInterface)Naming.lookup("ServidorBlocoNotas");
               // SE O STATUS DO SERVIDOR NÃO ESTIVER OK SIGNIFICA QUE NÃO FOI ENCONTRADO E DA UM EXIT NO SISTEMA
            if(!ri.getStatus().equals("Ok")){
                System.out.println("#################################################\n"
				  +"              SERVICE NOT FOUND!                 \n"
				  +"#################################################");
                System.exit(0);
            }
            
            System.out.println("#  BLOCO DE NOTAS OPERANTE! TRABALHO 03 - POO - MANIPULAÇÃO DE ARQUIVOS!  # \n");
            
		// INICIAMOS O WHILE QUE DARÁ USABILIDADE AO MENU
            while(true){
                System.out.println("################ BLOCO DE NOTAS ################ \n"
                                + "#             1 - Listar anotações              #\n"
                                + "#             2 - Criar nova anotação           #\n"
                                + "#             3 - Abrir anotação                #\n"
                                + "#             4 - Editar anotação               #\n"
                                + "#             5 - Apagar anotação               #\n"
				+ "#             6 - Contar letra da anotação      #\n"
				+ "#             7 - Adicionar Pessoa              #\n"
                                + "#             8 - Fechar bloco de notas         #\n\n"
				+ "            Entre com a opção desejada: ");
                
                int choice = scanner.nextInt();
                
                switch (choice){
			// EXIBIÇÃO DE NOTAS EXISTENTES
                    case 1:
                        System.out.println("##################### NOTES ##################### \n");

                        for(File file:ri.getListFiles("NOTES/")){
                         System.out.println("                   "+file.getName()+"               ");
                        }
                        System.out.println("\n################################################# \n");

                    break;
                        
                    case 2:  
			// CRIAÇÃO DE UMA NOVA NOTA  
            
                        System.out.println("           Digite o nome da anotação: ");
                        String CriaAnotacao = scan.nextLine();
                        
                        if (ri.CriaNote(CriaAnotacao)) {
						
                            System.out.println("\n\n################## Nota criada! #################\n");
                        }
			
			// INSERINDO CONTEÚDO NA NOVA NOTA

                        System.out.println("           Digite a anotação: \n");
                        String conteudo = scan.nextLine();
                        ri.EditNote(CriaAnotacao, conteudo);
                    break;
                        
                    case 3:
			// EXIBIR O CONTEÚDO DE UMA NOTA EXISTENTE

                        System.out.println("            Digite o nome da anotação: ");
                        String LerAnotacao = scan.nextLine();

                        System.out.println("\n################## CONTEUDO ######################\n\n");
			System.out.println(ri.LerNote(LerAnotacao)+"\n\n");
                        
                        System.out.println("################ Fim da leitura! ################\n");
                    break;

                    case 4:
                        // ALTERAR O CONTEÚDO DE UMA NOTA EXISTENTE

                        System.out.println("           Digite o nome da anotação: ");
                        
			String editaNote = scan.nextLine();
                        
                        System.out.println(ri.LerNote(editaNote));

                        System.out.println("################ Fim da leitura! ################\n");
                        
                        System.out.println("\n"+"  Digite o novo conteúdo desejado para a nota "+editaNote);
                        String novoconteudo = scan.nextLine();
                        
                        if (ri.EditNote(editaNote, novoconteudo)) {
				    System.out.println("\n\n######## Nota "+editaNote+" editada! ##############\n");
                        }else{
				System.out.println("\n\n#################################################\n"
					         + "            ERRO NA EDIÇÃO DO ARQUIVO! \n"
					         +  "#################################################\n\n");
                        }
                    break;
                    
                    case 5:
			// DELETAR UMA NOTA 

                        System.out.println("           Digite o nome da anotação: ");
                        String DelAnotacao = scan.nextLine();
                        if (ri.DelNote(DelAnotacao)) {
				System.out.println("\n\n###### A anotação "+DelAnotacao+" foi deletda! ###########\n");
                        }else{
				System.out.println("\n\n#################################################\n"
					         + "                ERRO NA OPERAÇÃO! \n"
					         + "#################################################\n\n");
                        }
                    break;

                    case 6:
			// CONTAR A QUANTIDADE DE VEZES QUE UMA LETRA APARECE NO ARQUIVO
			System.out.println("           Digite o nome da anotação: ");
                        String ContAnotacao = scan.nextLine();
			System.out.println("           Digite a letra desejada: ");
                        char c = scan.nextLine().charAt(0);
			int vezes = ri.ContCaracter(ContAnotacao, c);

			System.out.println("O caracter '"+c+"' apareceu "+vezes+" vezes!");

                    break;

		    case 7:
			// ADICIONAR E LER PESSOA DO ARQUIVO
			

			System.out.println("           Digite o nome da Pessoa: ");
                        String nome = scan.nextLine();

			System.out.println("           Digite a identidade: ");
                        int identidade = scanner.nextInt();

			System.out.println("           Digite a data de nascimento: ");
                        String nascimento = scan.nextLine();

			Pessoa pessoa = ri.TrataPessoa(nome, identidade, nascimento);
			
			if(ri.EscrevePessoa(pessoa)){
				System.out.println("\n################ Objeto gravado! ################\n!");
			}
			else{
				System.out.println("\n\n#################################################\n"
					         + "                ERRO NA OPERAÇÃO! \n"
					         + "#################################################\n\n");
				break;		
			}
			
			System.out.println("\n #     Leitura do Objeto em arquivo:       #\n");
			System.out.println(ri.LerPessoa());

                    break;

		    case 8:
			// SAIR DO SISTEMA

                        System.exit(0);
                    break;
                }
            }
        } catch (MalformedURLException | NotBoundException | RemoteException e){
            System.out.println("SERVER NOT FOUND!");
        }
    }
}
