package Mesas;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

//Test de la clase repository
//Lanza el servicio y le da un puerto aleatorio
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class platoRepositoryTest {

    //Le decimo a spring que autoinstancie la clase cuando arranque
    @Autowired
    private platoRepository repository;

    //Metodo para comprobar que guarda adecuandamente y que no devuelve null al buscar
    @Test
    public void SaveAndFind() {
        plato pedido = new plato(69, "plato", 1.4, 1);
        repository.save(pedido);
        assertThat("Guarda correctamente",repository.findByIdmesa(69).toString(),is(not("[]")));
    }
    //Añade un plato, lo borra y comprueba que no devuelve nada
    @Test
    public void Delete() {
        plato pedido = new plato(69, "plato", 1.4, 1);
        repository.save(pedido);
        repository.deletePlatoByIdmesa(69);
        assertThat("Borra correctamente",repository.findByIdmesa(69).toString(),is("[]"));
    }
    
}