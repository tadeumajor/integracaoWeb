package com.integracao.integracaoWeb.service;

import com.integracao.integracaoWeb.response.PunkApiBeerResponse;
import com.integracao.integracaoWeb.client.PunkApiBeerClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PunkApiBeerService {
    private final PunkApiBeerClient punkApiBeerClient;

    public List<PunkApiBeerResponse> getAllBeers() {
        return punkApiBeerClient.getAllBeers();
    }

    public List<PunkApiBeerResponse> getBeersbyABVRange(int min, int max) {
        return punkApiBeerClient.getBeersbyABVRange(min, max);
    }

    public List<PunkApiBeerResponse> getBeersbyIBURange(int min, int max) {
        return punkApiBeerClient.getBeersbyIBURange(min, max);
    }

    public List<PunkApiBeerResponse> getBeersbyEBCRange(int min, int max) {
        return punkApiBeerClient.getBeersbyEBCRange(min, max);
    }
}
