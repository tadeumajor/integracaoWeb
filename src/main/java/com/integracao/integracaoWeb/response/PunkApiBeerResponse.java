package com.integracao.integracaoWeb.response;

import lombok.Data;

@Data
public class PunkApiBeerResponse {
    private long id;
    private String name;
    private String description;
    private long abv;
    private long ibu;
}
