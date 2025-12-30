package com.user.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching; // <-- 1. BU IMPORTU EKLE

@SpringBootApplication
@EnableCaching // <-- 2. BU NOTASYONU EKLE (Cache'i aktifleştirir)
public class UserApplication {

  public static void main(String[] args) {
    SpringApplication.run(UserApplication.class, args);
  }

}
