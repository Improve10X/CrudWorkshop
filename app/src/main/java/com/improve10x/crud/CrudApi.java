package com.improve10x.crud;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CrudApi {

    public CrudService createCrudService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CrudService crudService = retrofit.create(CrudService.class);
        return crudService;
    }
}
