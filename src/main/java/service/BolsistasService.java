package service;

import entities.Bolsistas;
import lombok.Getter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class BolsistasService {

    private List<Bolsistas> list;

    public BolsistasService() {//ao instanciar esse objeto, já inicia a lista
        list = new ArrayList<>();
    }

    public void leArquivo(){//função criada para ler o arquivo

        String path = "src\\main\\java\\files\\br-capes-bolsistas-uab.csv";//diretorio

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {//utilizado o try with resources pois ele já fecha os recursos após sair do try
            String line = br.readLine();//lê cada linha do arquivo

            if (line.contains("M_BOLSIS")) {//este if evita que o filereader leia a primeira linha se for o cabeçalho
                line = br.readLine();
            }

            while (line != null) {
                String[] vect = line.split(";");//divide a linha do arquivo em arrays dividos por ;

                String nm_bolsista = vect[0];//para cada coluna, é adicionado ao seu respectivo atributo
                String cpf_bolsista = vect[1];
                String nm_entidade_ensino = vect[2];
                String me_referencia = vect[3];
                String an_referencia = vect [4];
                String sg_diretoria = vect[5];
                String sg_sistema_origem = vect[6];
                String cd_modalidade_sgb = vect[7];
                String ds_modalidade_pagamento = vect[8];
                String cd_moeda = vect[9];
                String vl_bolsista_pagamento = vect[10];

                Bolsistas prod = new Bolsistas(nm_bolsista,cpf_bolsista,nm_entidade_ensino,me_referencia,an_referencia,sg_diretoria,sg_sistema_origem,
                        cd_modalidade_sgb,ds_modalidade_pagamento,cd_moeda,vl_bolsista_pagamento);//instancia o objeto com todos os argumentos

                list.add(prod);//adiciona a lista
                line = br.readLine();
            }
        }
        catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());//mensagem de erro caso falhe ao le arquivo
        }
    }

    public Bolsistas consultaBolsaZeroAno(String year){

        for (Bolsistas p : list){
            if(year.equals(p.getAn_referencia())){
                return p;//retorna o primeiro bolsista do ano passado como parametro
            }
        }
        return null;
    }

    public Bolsistas procuraBolsista(String input){
        for (Bolsistas p : list){
            if(p.getNm_bolsista().contains(input.toUpperCase())){
                return p;//retorna o primeiro bolsista que contém os caracteres passados no parametro
            }
        }
        return null;
    }

    public String codificaNome(String word){//codifica o nome

        word = word.toUpperCase();//seta a string para maiuscula
        word = Normalizer.normalize(word, Normalizer.Form.NFD);//essa função auxilia a manter as letras acentuadas ao usar o "replaceAll"
        word = word.replaceAll("[^\\p{ASCII}]", "");//remove acentos
        word = word.replaceAll("[!@#$'\"%^&*()\\-+]*", "");//remove caracteres especiais

        char first = word.charAt(0);//salva o primeiro character do input
        StringBuilder codedWord = new StringBuilder(word).reverse();//inverte o input
        if (word.length()>3){//em palavras com menos de 4 caracter deve apenas inverter a String
            codedWord.setCharAt(0,word.charAt(0));//seta o primeiro character da String novamente para o primeiro character original
            codedWord.setCharAt(word.length()-1,word.charAt(word.length()-1));//seta o ultimo character da String novamente para o ultimo caracter original
        }
        
        for (int i = 0; i<codedWord.length();i++){//percorre a string coded word
            if (codedWord.charAt(i) == ' ') {//se for um espaço, não fará nada e impedirá de entrar nas outras condições
            }
            else if (codedWord.charAt(i) != 'Z')//testa se o char da posição i é diferente de Z
                codedWord.setCharAt(i, (char) (codedWord.charAt(i)+1));//se for,incrementa um char
            else
                codedWord.setCharAt(i,'A');//se for igual a Z, atribui o A
        }

        return codedWord.toString();//retorna a palavra do tipo StringBuilder toString
    }

    public Double consultaMediaAnual(String year){

        Double sum = 0.0;//variaveis auxiliares
        Double qty = 0.0;
        for (Bolsistas p : list){
            if (p.getAn_referencia().equals(year)){//percorre a lista e compara o ano da lista com o parametro
                sum += Double.parseDouble(p.getVl_bolsista_pagamento());//se for igual, soma e incrementa
                qty++;
            }
        }
        if (qty==0.0){
            return null;
        }
        return sum/qty;
    }

    public List<Bolsistas> rankingBolsaMaisAltos(){//retorna uma lista com os 3 bolsistas com maior valor da bolsa
        int count = 0;//variavel auxiliar
        List<Bolsistas> maisAltos = new ArrayList<>();
        Collections.sort(list);//organiza a lista ordenando pelo valor da bolsa (graças ao comparable na entidade "bolsistas")

        for(Bolsistas p : list){//percorre a lista ordenada
            maisAltos.add(p);
            count++;
            if (count == 3){//sai do for ao encontrar os 3 primeiros
                break;
            }
        }
        return maisAltos;//retorna lista
    }

    public List<Bolsistas> rankingBolsaMaisBaixos(){
        int count = 0;
        List<Bolsistas> maisBaixos = new ArrayList<>();
        Collections.sort(list,Collections.reverseOrder());//se comporta igual a função anterior, porém inverte a lista

        for(Bolsistas p : list){
            maisBaixos.add(p);
            count++;
            if (count == 3){
                break;
            }
        }
        return maisBaixos;
    }
}
