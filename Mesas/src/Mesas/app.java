package Mesas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Decimos que es una aplicación de SpringBoot
@SpringBootApplication
public class app {
    public static void main(String[] args) {
        //Arrancamos la aplicación
        SpringApplication.run(app.class, args);
    }
}