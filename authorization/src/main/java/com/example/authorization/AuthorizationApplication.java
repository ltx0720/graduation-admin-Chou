package com.example.authorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class AuthorizationApplication {
    public static void main(String[] args) throws Exception {


        SpringApplication.run(AuthorizationApplication.class, args);


//        Person person = new Person(1    , "1");
//        String jsonString = JSONObject.toJSONString(person);
//        System.out.println(jsonString);
//
////        System.out.println(s);
//
//        Person p = JSONObject.parseObject(jsonString, Person.class);
//
//        System.out.println(p);
//
//        Map<String, Object> keyMap = RSAUtils.genKeyPair();
//        Object publikey = keyMap.get("RSAPublicKey");
//        System.out.println(publikey.toString());

    }
}
