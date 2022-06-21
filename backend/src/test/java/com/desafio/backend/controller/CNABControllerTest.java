package com.desafio.backend.controller;

import com.desafio.backend.model.CNAB;
import com.desafio.backend.repository.CNABRepository;
import com.desafio.backend.service.FileService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalTime;
import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CNABController.class)
public class CNABControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    CNABRepository cnabRepository;

    @MockBean
    FileService fileService;

    private Date data = new Date();
    private LocalTime hora = LocalTime.now();

    CNAB cnab = new CNAB("1", data, 100, "374******47", "12345678910", hora, "Dono Loja", "Nome Loja");

    @Test
    public void testGetAllCNABs() throws Exception {
        List<CNAB> records = new ArrayList<>(Arrays.asList(cnab));

        Mockito.when(cnabRepository.findAll()).thenReturn(records);

        mvc.perform(MockMvcRequestBuilders
            .get("/desafio/server/cnab")
            .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].tipo").value("1"))
                .andExpect(jsonPath("$[0].valor").value(100))
                .andExpect(jsonPath("$[0].cpf").value("374******47"))
                .andExpect(jsonPath("$[0].cartao").value("12345678910"))
                .andExpect(jsonPath("$[0].data").exists())
                .andExpect(jsonPath("$[0].hora").exists())
                .andExpect(jsonPath("$[0].donoLoja").value("Dono Loja"))
                .andExpect(jsonPath("$[0].nomeLoja").value("Nome Loja"));
    }

    @Test
    public void testUploadCNAB() throws Exception {
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "cnab.txt",
                "text/plain",
                "This is the file content".getBytes()
        );

        mvc.perform(MockMvcRequestBuilders
                .multipart("/desafio/server/cnab")
                .file(file))
            .andExpect(status().isOk());
    }
}
