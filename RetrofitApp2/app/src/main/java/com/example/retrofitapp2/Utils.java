package com.example.retrofitapp2;

import com.example.retrofitapp2.api.APIService;
import com.example.retrofitapp2.api.Client;

public class Utils {
    public static APIService getApi(){
        return Client.getClient().create(APIService.class);
    }
}
