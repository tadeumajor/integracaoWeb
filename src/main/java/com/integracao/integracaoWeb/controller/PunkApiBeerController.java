package com.integracao.integracaoWeb.controller;

import com.integracao.integracaoWeb.response.PunkApiBeerResponse;
import com.integracao.integracaoWeb.service.PunkApiBeerService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
