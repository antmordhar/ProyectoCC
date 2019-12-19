package Camarero;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

//Esta interfaz se encarga de realizar las acciones con la base de datos y que hace la inyeccion de dependencias
//Extiende un repositorio propio de Spring.
//Siempre se puede implementar si se busca realizar alguna tarea especifica.
//Este repositorio autoimplementa las clases dependiendo del nombre que estas tengan y del dato que devuelvan.
//Ademas proporciona otros metodos como save y update que forman parte del CRUD
public interface platoRepository extends MongoRepository<plato, String> {

    List<plato> findByIdmesa(int nummesa);
    void deletePlatoByIdmesa(int nummesa);
  
  }