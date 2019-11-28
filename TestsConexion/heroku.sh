#Creamos una mesa con un post
curl \
  --header "Content-type: application/json" \
  --request POST \
  --data '' \
  https://restauranprojectcc.herokuapp.com/crearmesa
echo 
#Añadimos un plato con un post.
curl \
  --header "Content-type: application/json" \
  --request POST \
  --data "{\"idmesa\":0,\"nombre\":\"plato0\", \"precio\":1.4, \"cantidad\":1}" \
  https://restauranprojectcc.herokuapp.com/hacerpedido
#Ahora vemos que se ha subido el plato con un get. Deberia verse algo como esto "[0,{\"Cantidad\":1,\"Plato\":\"plato0\",\"Precio\":1.4}]"
curl --request GET https://restauranprojectcc.herokuapp.com/verpedido/0
echo
#Borramos el plato con un delete
curl \
  --header "Content-type: application/json" \
  --request DELETE \
  --data '' \
  https://restauranprojectcc.herokuapp.com/borrapedido/0
echo
# Ahora volvemos a ver el pedido con un get. deberia verse [0]
curl --request GET https://restauranprojectcc.herokuapp.com/verpedido/0
echo