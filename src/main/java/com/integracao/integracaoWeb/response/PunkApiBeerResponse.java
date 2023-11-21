package com.integracao.integracaoWeb.response;

import lombok.Data;

@Data
public class PunkApiBeerResponse {
    private long id;
    private String name;
    private String description;
    private long abv;
    private long ibu;
    private long ebc;


    public PunkApiBeerResponse() {
    }

    public PunkApiBeerResponse(long id, String name, String description, long abv, long ibu, long ebc) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.abv = abv;
        this.ibu = ibu;
        this.ebc = ebc;
    }
}
