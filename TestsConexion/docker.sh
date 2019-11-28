docker pull antmordhar/restaurantproject
export PORT=8080
docker run --rm -p 8080 -d antmordhar/restaurantproject:latest

curl \
  --header "Content-type: application/json" \
  --request POST \
  --data '' \
  http://localhost:8080/crearmesa
echo 
curl \
  --header "Content-type: application/json" \
  --request POST \
  --data "{\"idmesa\":0,\"nombre\":\"plato0\", \"precio\":1.4, \"cantidad\":1}" \
  http://localhost:8080/hacerpedido

curl --request GET http://localhost:8080/verpedido/0
echo
curl \
  --header "Content-type: application/json" \
  --request DELETE \
  --data '' \
  http://localhost:8080/borrapedido/0
echo
curl --request GET http://localhost:8080/verpedido/0
echo