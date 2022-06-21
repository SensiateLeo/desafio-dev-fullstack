package com.desafio.backend.service;

import com.desafio.backend.model.CNAB;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class FileServiceTest {
    @Autowired
    FileService fileService;

    @Test
    public void testReadFile() throws IOException, ParseException {
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "cnab.txt",
                "text/plain",
                "3201903010000014200096206760174753****3153153453JOAO MACEDO   BAR DO JOAO ".getBytes()
        );

        String dateFormat = "yyyyMMdd";
        SimpleDateFormat dateFormatter = new SimpleDateFormat(dateFormat);

        List<CNAB> cnabs = fileService.readFile(file);
        assertEquals(1, cnabs.size());
        assertEquals("3", cnabs.get(0).getTipo());
        assertEquals(dateFormatter.parse("20190301"), cnabs.get(0).getData());
        assertEquals(Integer.valueOf("0000014200") / 100, cnabs.get(0).getValor());
        assertEquals("09620676017", cnabs.get(0).getCpf());
        assertEquals("4753****3153", cnabs.get(0).getCartao());
        assertEquals(LocalTime.parse("153453", DateTimeFormatter.ofPattern("HHmmss")), cnabs.get(0).getHora());
        assertEquals("JOAO MACEDO   ", cnabs.get(0).getDonoLoja());
        assertEquals("BAR DO JOAO ", cnabs.get(0).getNomeLoja());
    }
}
