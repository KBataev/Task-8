package org.example;

import org.example.JSONToModel.Transform;
import org.example.REST.Request;
import org.example.model.User;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{


    public static void main( String[] args )
    {


        Request.getRequest();
        Request.saveUser();

        Request.updateUser();
        Request.deleteUser();

        String result = Request.cod_1 + Request.cod_2 + Request.cod_3;
        System.out.println(result+" символов: "+ result.length());
    }
}
