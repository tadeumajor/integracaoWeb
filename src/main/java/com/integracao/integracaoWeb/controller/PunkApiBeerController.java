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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class PunkApiBeerController {

    private final PunkApiBeerService punkApiBeerService;

    @Operation(summary = "Busca todas cervejas, disponiveis no catalogo")
    @GetMapping("/beer/allBeers")
    public List<PunkApiBeerResponse> getAllBeers() {
        return punkApiBeerService.getAllBeers();
    }

    @GetMapping("/beer/relatorio-cervejas")
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

    // Este endpoint permitiria aos usuários pesquisar cervejas dentro de uma faixa
    // ABV específica. Por exemplo, um usuário pode pesquisar cervejas com ABV entre
    // 4% e 6%.
    // O ABV, ou teor alcoólico, é uma medida da quantidade de álcool em uma bebida.
    @GetMapping("/beers/byABVRange/{min}/{max}")
    public List<PunkApiBeerResponse> getBeersbyABVRange(@PathVariable("min") int min, @PathVariable("max") int max) {
        return punkApiBeerService.getBeersbyABVRange(min, max);
    }

    // Este endpoint permitiria aos usuários pesquisar cervejas dentro de uma faixa
    // específica de IBU. Por exemplo, um usuário pode pesquisar cervejas com IBU
    // entre 20 e 40.
    // IBU (International Bitterness Units) é uma escala mundial que mede quanto um
    // rótulo UMA CERVEJA é amarga.
    @GetMapping("/beers/byIBURange/{min}/{max}")
    public List<PunkApiBeerResponse> getBeersbyIBURange(@PathVariable("min") int min, @PathVariable("max") int max) {
        return punkApiBeerService.getBeersbyIBURange(min, max);
    }

    // Este endpoint permitiria aos usuários pesquisar cervejas dentro de uma faixa
    // EBC específica. Por exemplo, um usuário pode pesquisar cervejas com um EBC
    // entre 10 e 20.
    // O EBC (European Brewery Convention) é uma escala de cor utilizada na
    // indústria de cervejas para medir a cor de uma cerveja.
    @GetMapping("/beers/byEBCRange/{min}/{max}")
    public List<PunkApiBeerResponse> getBeersbyEBCRange(@PathVariable("min") int min, @PathVariable("max") int max) {
        return punkApiBeerService.getBeersbyEBCRange(min, max);
    }
}
