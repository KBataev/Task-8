package org.example.JSONToModel;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.User;

import java.util.List;

public class Transform {

    public static List<User> ToUser(String json) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<User> users = mapper.readValue(json, new TypeReference<List<User>>() {});
            users.forEach(System.out::println);
            return users;
        } catch (Exception e) {
            System.out.println("error " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
