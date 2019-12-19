package Camarero;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class platoRepositoryTest {

    @Autowired
    private platoRepository repository;

    @Test
    public void SaveAndFind() {
        plato pedido = new plato(69, "plato", 1.4, 1);
        repository.save(pedido);
        assertThat("Guarda correctamente",repository.findByIdmesa(69).toString(),is(not("[]")));
    }
    @Test
    public void Delete() {
        plato pedido = new plato(69, "plato", 1.4, 1);
        repository.save(pedido);
        repository.deletePlatoByIdmesa(69);
        assertThat("Borra correctamente",repository.findByIdmesa(69).toString(),is("[]"));
    }
    
}