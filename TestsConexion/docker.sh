#Nos movemos a la raiz y ejecutamos el limpiado, empaquetamiento y ejecucion del dockerfile
cd ../
mvn clean package dockerfile:build
#Arrancamos el contenedor en el puerto 8080
docker run --rm -p 8080:8080 -d antmordhar/restaurantproject:latest
#Dormimos la ejecución para dar tiempo a que se corra el docker, puede variar dependiendo del usuario
sleep 10
#Creamos una mesa con un post
curl \
  --header "Content-type: application/json" \
  --request POST \
  --data '' \
  http://localhost:8080/crearmesa
echo 
#Añadimos un plato con un post.
curl \
  --header "Content-type: application/json" \
  --request POST \
  --data "{\"idmesa\":0,\"nombre\":\"plato0\", \"precio\":1.4, \"cantidad\":1}" \
  http://localhost:8080/hacerpedido
#Ahora vemos que se ha subido el plato con un get. Deberia verse algo como esto "[0,{\"Cantidad\":1,\"Plato\":\"plato0\",\"Precio\":1.4}]"
curl --request GET http://localhost:8080/verpedido/0
echo
#Borramos el plato con un delete
curl \
  --header "Content-type: application/json" \
  --request DELETE \
  --data '' \
  http://localhost:8080/borrapedido/0
echo
# Ahora volvemos a ver el pedido con un get. deberia verse [0]
curl --request GET http://localhost:8080/verpedido/0
echo