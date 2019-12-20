package Camarero;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

//Esta interfaz se encarga de realizar las acciones con la base de datos y que realiza la inyeccion de dependencias
//Extiende un repositorio propio de Spring.
//Este repositorio autoimplementa las clases dependiendo del nombre que estas tengan y del dato que devuelvan.
//Ademas proporciona otros metodos como save y update que forman parte del CRUD
//Siempre se puede implementar si se busca realizar alguna tarea especifica.
public interface platoRepository extends MongoRepository<plato, String> {

    //Devuelve una lista de platos cuyo idmesa coincida con nummesa
    List<plato> findByIdmesa(int nummesa);
    //Borra todos los datos cuyo idmesa coincida con nummesa
    void deletePlatoByIdmesa(int nummesa);
  }

//Para saber mas sobre la clase repository de spring consulte la documentación oficioal:
//https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#mongo.repositories