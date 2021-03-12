package com.inventario.sistemainv.util;

import lombok.var;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptPass {
    public static void main(String[] args) {
        var password = "user";
        System.out.println("PASS:" + password);
        System.out.println("ENCRYPTADO:  " + Encriptar(password));
    }

    public static String Encriptar(String pass) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(pass);
    }
}
