package com.desafio.backend.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CNABModelTest {

    @Test
    public void testCreateCNAB() {

        CNAB cnab = new CNAB();

        cnab.setTipo("1");
        assertEquals("1", cnab.getTipo());

        cnab.setValor(100);
        assertEquals(100, cnab.getValor());

        cnab.setCpf("374******47");
        assertEquals("374******47", cnab.getCpf());

        cnab.setCartao("12345678910");
        assertEquals("12345678910", cnab.getCartao());

        cnab.setDonoLoja("Dono Loja");
        assertEquals("Dono Loja", cnab.getDonoLoja());

        cnab.setNomeLoja("Nome Loja");
        assertEquals("Nome Loja", cnab.getNomeLoja());

        Date date = new Date();
        cnab.setData(date);
        assertEquals(date, cnab.getData());

        LocalTime hora = LocalTime.now();
        cnab.setHora(hora);
        assertEquals(hora, cnab.getHora());

    }
}
