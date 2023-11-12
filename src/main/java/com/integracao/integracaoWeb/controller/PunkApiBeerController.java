package com.integracao.integracaoWeb.controller;

import com.integracao.integracaoWeb.response.PunkApiBeerResponse;
import com.integracao.integracaoWeb.service.PunkApiBeerService;
import com.integracao.integracaoWeb.xmlFactory.XmlFactory;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/beer")
public class PunkApiBeerController {

    private final PunkApiBeerService punkApiBeerService;

    @Operation(summary = "Busca todas cervejas, disponiveis no catalogo")
    @GetMapping("/allBeers")
    public List<PunkApiBeerResponse> getAllBeers(){
        return  punkApiBeerService.getAllBeers();
    }

    @GetMapping("/relatorio-cervejas")
    public ResponseEntity<byte[]> convertExcel() throws IOException {

        var cervejas = punkApiBeerService.getAllBeers();

        // Conversão para bytes
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Workbook workbook = XmlFactory.createExcellWorkbook(cervejas);

        workbook.write(outputStream);
        workbook.close();

        byte[] excelContent = outputStream.toByteArray();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "Cervejas.xlsx");

        return new ResponseEntity<>(excelContent, headers, HttpStatus.OK);

    }
}
