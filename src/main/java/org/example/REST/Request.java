package org.example.REST;

import org.example.JSONToModel.Transform;
import org.example.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class Request {
    public static final String url = "http://94.198.50.185:7081/api/users";
    public static RestTemplate restTemplate = new RestTemplate();
    public static String sessionId;
    public static String cod_1,cod_2,cod_3;
    public static HttpHeaders headers = new HttpHeaders();

    public static void getRequest() {

        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                String.class
        );

        HttpHeaders responseHeaders = response.getHeaders();
        List<String> cookies = responseHeaders.get("Set-Cookie");
        if (cookies != null && !cookies.isEmpty()) {
            sessionId = cookies.get(0).split(";")[0];
            System.out.println("Session ID: " + sessionId);
        } else {
            System.out.println("Не удалось получить Session ID.");
            return;
        }

        List<User> users = Transform.ToUser(response.getBody());
    }

    public static void saveUser(){
        User user = new User(3L, "James", "Brown", (byte) 25);

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Cookie", sessionId);

        HttpEntity<User> entity = new HttpEntity<>(user,headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        cod_1 = response.getBody();
        System.out.println(cod_1);
    }

    public static void updateUser() {
        User updatedUser = new User(3L, "Thomas", "Shelby", (byte) 30);

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Cookie", sessionId);

        HttpEntity<User> entity = new HttpEntity<>(updatedUser, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                entity,
                String.class
        );

        cod_2 = response.getBody();
        System.out.println(cod_2);
    }

    public static void deleteUser() {
        headers.add("Cookie", sessionId);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        String deleteUrl = url + "/3";

        ResponseEntity<String> response = restTemplate.exchange(
                deleteUrl,
                HttpMethod.DELETE,
                entity,
                String.class
        );
        cod_3 = response.getBody();
        System.out.println(cod_3);
    }



}
