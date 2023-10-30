package com.integracao.integracaoWeb.client;


import com.integracao.integracaoWeb.response.PunkApiBeerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name ="PunkApiBeerClient", url = "https://api.punkapi.com/v2/beers")
public interface PunkApiBeerClient {

    @GetMapping(value = "random")
    List<PunkApiBeerResponse> getRandomBeer();

    @GetMapping
    List<PunkApiBeerResponse> getAllBeers();

}
