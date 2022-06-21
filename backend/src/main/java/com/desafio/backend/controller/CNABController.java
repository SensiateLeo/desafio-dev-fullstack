package com.desafio.backend.controller;

import java.io.IOException;
import java.util.List;

import com.desafio.backend.model.CNAB;
import com.desafio.backend.repository.CNABRepository;
import com.desafio.backend.service.FileService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/desafio/server")
public class CNABController {

    @Autowired
    private CNABRepository cnabRepository;

    @Autowired
    private FileService fileService;

    // get all CNABs
    @ApiOperation(value = "Retorna uma lista de transações salvas do arquivo CNAB")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de transações"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @RequestMapping(value = "/cnab", method = RequestMethod.GET, produces="application/json")
    public List<CNAB> listCNAB() {
        return cnabRepository.findAll();
    }

    // post CNAB text file
    @ApiOperation(value = "Salva os dados do arquivo de texto CNAB")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Fez o upload do arquivo de texto"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @RequestMapping(value = "/cnab", method =  RequestMethod.POST, produces="application/json", consumes="text/plain")
    public void updateCNAB(@RequestParam("file") MultipartFile file) throws IOException {
        List<CNAB> cnabs = fileService.readFile(file);
        cnabRepository.saveAll(cnabs);
    }
}


