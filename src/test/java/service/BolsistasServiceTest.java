package service;

import entities.Bolsistas;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class BolsistasServiceTest {

    BolsistasService service;

    @BeforeEach
    public void instanciaBolsistaService(){
        service = new BolsistasService();
        service.leArquivo();
    }

    @Test
    void leArquivo() {
        List<Bolsistas> bolsistas = service.getList();
        Assertions.assertTrue(bolsistas.size() > 0);
    }

    @Test
    void consultaBolsaZeroAno() {
        Assertions.assertEquals(service.consultaBolsaZeroAno("0000"),null);
        Assertions.assertTrue(service.consultaBolsaZeroAno("2016").getNm_bolsista().equals("ALEXANDRE RIBEIRO NETO"));
    }

    @Test
    void procuraBolsista() {
        Assertions.assertEquals(service.procuraBolsista("0000"),null);
    }

    @Test
    void codificaNome() {
        Assertions.assertEquals(service.codificaNome("PERIGO"),"QHJSFP");
        Assertions.assertEquals(service.codificaNome("FUGA"),"GHVB");
        Assertions.assertEquals(service.codificaNome("PAZ"),"ABQ");
    }

    @Test
    void consultaMediaAnual() {
        Assertions.assertEquals(service.consultaMediaAnual("2014"),936.2399769529937);
    }

    @Test
    void rankingBolsaMaisAltos() {
        Assertions.assertTrue(service.rankingBolsaMaisAltos().size()==3);
    }

    @Test
    void rankingBolsaMaisBaixos() {
        Assertions.assertTrue(service.rankingBolsaMaisBaixos().size()==3);
    }
}