package ui;

import entities.Bolsistas;
import service.BolsistasService;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    static Scanner scan = new Scanner(System.in);
    BolsistasService dfs = new BolsistasService();//instanciado com o construtor vazio, iniciando a lista

    public UserInterface() {
        dfs.leArquivo(); //leitura do arquivo já no construtor
    }

    public void mainMenu(){//interface do console para interação com usuário

        System.out.println("Escolha a opcao desejada: \n");//menu principal
        System.out.println("1 - Consultar bolsa zero/Ano");
        System.out.println("2 - Codificar nomes");
        System.out.println("3 - Consultar media anual");
        System.out.println("4 - Ranking valores de bolsa");
        System.out.println("5 - Sair\n");

        String option = scan.nextLine(); //le o input do usuario com a opção do menu desejada

        if (!option.matches("[0-9]*")){//valida o input para que seja apenas digitos
            System.out.println("\nVoce deve digitar um numero!\n\n");
            finaliza();
        }

        switch (option){
            case "1":

                System.out.println("\nDigite o ano para encontrar o primeiro bolsista do ano digitado: \n");

                String year = scan.nextLine();
                Bolsistas df = dfs.consultaBolsaZeroAno(year);

                if(df != null){//testa o retorno do metodo
                    System.out.println("\nO primeiro bolsista do ano de "+year+" eh:");
                    System.out.println("Nome: "+df.getNm_bolsista());
                    System.out.println("CPF: "+df.getCpf_bolsista());
                    System.out.println("Entidade de Ensino: "+df.getNm_entidade_ensino());
                    System.out.println("Valor da Bolsa: R$"+df.getVl_bolsista_pagamento()+",00\n\n");
                }
                else{
                    if (year.matches("[0-9]{4}")){//valida se o input é um ano verificando se há 4 números
                        System.out.println("\nNao ha bolsistas nesse ano!"+"\n\n");//se o df for null, mas o input é um ano correto
                    }
                    else {
                        System.out.println("\nAno invalido!\n\n");//se o df for null e o input não é um ano
                    }
                }

                finaliza();
                break;

            case "2":

                System.out.println("\nDigite o nome ou parte dele para retornar o nome codificado e os dados:");
                System.out.println("(insira, de preferencia, o nome completo para que sua busca seja precisa.");
                System.out.println("Sera retornado o primeiro registro que contenha caracteres inseridos)\n");

                String input = scan.nextLine();
                Bolsistas bolsista = dfs.procuraBolsista(input);

                if (bolsista!=null){
                    System.out.println("\nO resultado encontrado para sua pesquisa foi:\n");//diferente de null retorna as informações do objeto retornado
                    System.out.println("Nome codificado: "+dfs.codificaNome(bolsista.getNm_bolsista()));
                    System.out.println("Ano: "+bolsista.getAn_referencia());
                    System.out.println("Entidade de Ensino: "+bolsista.getNm_entidade_ensino());
                    System.out.println("Valor da bolsa: R$"+bolsista.getVl_bolsista_pagamento()+",00\n\n");
                }
                else {
                    System.out.println("\nNao foi encontrado nenhum resultado!\n\n");//lista null
                }

                finaliza();
                break;

            case "3":

                System.out.println("\nInsira um ano e sera retornado a media dos valores das bolsas deste ano:\n");

                year = scan.nextLine();

                if (!year.matches("[0-9]{4}")){//verifica se o ano é invalido
                    System.out.println("\nAno invalido!\n\n");
                    finaliza();
                    break;
                }

                Double result = dfs.consultaMediaAnual(year);

                if (result != null){
                    System.out.print("\nA media dos valores das bolsas do ano de "+year);
                    System.out.printf(" foi: R$%.2f\n\n",result);//retorna o valor do metodo
                }
                else {
                    System.out.println("\nNao foram encontrados registros nesse ano!\n\n");//se o metodo retornou null
                }

                finaliza();
                break;

            case "4":

                System.out.println("\nOs tres alunos com os valores da bolsa mais altos:\n");
                List<Bolsistas> bolsistasList = dfs.rankingBolsaMaisAltos();//retorna o método que ordena a lista pelo valor da bolsa
                for (Bolsistas p : bolsistasList){//imprime os dados
                    System.out.println("Nome: "+p.getNm_bolsista());
                    System.out.println("Valor da bolsa: "+p.getVl_bolsista_pagamento());
                }
                dfs.leArquivo();//lê arquivo original novamente para retornar à ordenação padrão

                System.out.println("\n\nOs tres alunos com os valores da bolsa mais baixos:\n");//método igual, porém retorna os 3 mais baixos
                bolsistasList = dfs.rankingBolsaMaisBaixos();
                for (Bolsistas p : bolsistasList){
                    System.out.println("Nome: "+p.getNm_bolsista());
                    System.out.println("Valor da bolsa: "+p.getVl_bolsista_pagamento());
                }
                dfs.leArquivo();

                System.out.println("\n");

                finaliza();
                break;

            case "5":

                System.out.println("\nTchau!");//fecha o programa
                break;

            default:

                System.out.println("Opcao invalida!\n\n");//se o input de opções for número, porém não está entre as opções
                finaliza();
                break;
        }
    }


    public void finaliza() {//método para finalizar cada funcionalidade
        System.out.println("Digite enter para continuar");
        scan.nextLine();
        limpaConsole();
        mainMenu();
    }

    // https://stackoverflow.com/questions/2979383/java-clear-the-console
    public void limpaConsole(){//recurso encontrado para limpar console.
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c",
                        "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException e) {
            System.out.println("Erro! "+e.getMessage());
        }
    }
}
