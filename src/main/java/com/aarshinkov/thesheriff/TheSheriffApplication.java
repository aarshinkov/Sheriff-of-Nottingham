package com.aarshinkov.thesheriff;

import com.aarshinkov.thesheriff.utils.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;

@SpringBootApplication
public class TheSheriffApplication
{
  public static void main(String[] args)
  {
    SpringApplication.run(TheSheriffApplication.class, args);
  }
  
  @Bean
  public Memory memory() {
    return new Memory();
  }
}
