package com.integracao.integracaoWeb.client;

import com.integracao.integracaoWeb.response.PunkApiBeerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "PunkApiBeerClient", url = "https://api.punkapi.com/v2/")
public interface PunkApiBeerClient {

    @GetMapping(value = "beers/random")
    List<PunkApiBeerResponse> getRandomBeer();

    @GetMapping(value = "beers")
    List<PunkApiBeerResponse> getAllBeers();

    // Este endpoint permitiria aos usuários pesquisar cervejas dentro de uma faixa
    // ABV específica. Por exemplo, um usuário pode pesquisar cervejas com ABV entre
    // 4% e 6%.
    @GetMapping(value = "beers?abv_gt={min}&abv_lt={max}")
    List<PunkApiBeerResponse> getBeersbyABVRange(@PathVariable("min") int min, @PathVariable("max") int max);

    // Este endpoint permitiria aos usuários pesquisar cervejas dentro de uma faixa
    // específica de IBU. Por exemplo, um usuário pode pesquisar cervejas com IBU
    // entre 20 e 40.
    @GetMapping(value = "beers?ibu_gt={min}&ibu_lt={max}")
    List<PunkApiBeerResponse> getBeersbyIBURange(@PathVariable("min") int min, @PathVariable("max") int max);

    // Este endpoint permitiria aos usuários pesquisar cervejas dentro de uma faixa
    // EBC específica. Por exemplo, um usuário pode pesquisar cervejas com um EBC
    // entre 10 e 20.
    @GetMapping(value = "beers?ebc_gt={min}&ebc_lt={max}")
    List<PunkApiBeerResponse> getBeersbyEBCRange(@PathVariable("min") int min, @PathVariable("max") int max);
}
