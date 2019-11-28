curl \
  --header "Content-type: application/json" \
  --request POST \
  --data '' \
  https://restauranprojectcc.herokuapp.com/crearmesa
echo 
curl \
  --header "Content-type: application/json" \
  --request POST \
  --data "{\"idmesa\":0,\"nombre\":\"plato0\", \"precio\":1.4, \"cantidad\":1}" \
  https://restauranprojectcc.herokuapp.com/hacerpedido

curl --request GET https://restauranprojectcc.herokuapp.com/verpedido/0
echo
curl \
  --header "Content-type: application/json" \
  --request DELETE \
  --data '' \
  https://restauranprojectcc.herokuapp.com/borrapedido/0
echo
curl --request GET https://restauranprojectcc.herokuapp.com/verpedido/0
echo