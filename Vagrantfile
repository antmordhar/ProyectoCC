
#Documentación
#https://www.vagrantup.com/docs/
#https://blog.scottlowe.org/2017/12/11/using-vagrant-with-azure/
#https://medium.com/@JohnFoderaro/how-to-set-up-a-local-linux-environment-with-vagrant-163f0ba4da77
#https://github.com/Azure/vagrant-azure
#https://docs.microsoft.com/es-es/cli/azure/install-azure-cli-apt?view=azure-cli-latest
#

#Le indicamos a vagrant que usaremos la version 2 de la configuración
Vagrant.configure("2") do |config|

  ################################################################################################################################################
  #Configuración para despliegue local
  ################################################################################################################################################
  # #Config para Maquina en local
  # config.vm.define "local" do |local|
  #   # Le decimos la Vagrant Box que queremos usar. En Nuestro caso se usara una Box de base virtual Box y de SO Ubuntu Server 19.04
  #   local.vm.box = "ubuntu/xenial64"
  # end

  # # Mapeamos los puertos por los que se podra entrar a la maquina virtual.
  # config.vm.network "forwarded_port", guest: 8080, host: 8080
  # config.vm.network "forwarded_port", guest: 8081, host: 8081
  # config.vm.network "forwarded_port", guest: 8082, host: 8082
  # config.vm.network "forwarded_port", guest: 8069, host: 8069

  # #Configuramos las cpus y memoria que tendra nuestra maquina virtual, en nuestro caso le pondremos 2 cores y 2048 Mb de memoria.
  # config.vm.provider "virtualbox" do |vb|
  #   vb.cpus = 2
  #   vb.memory = 2048
  # end

  # #Le decimos que use el playbook.yml para provisionar y el path al inventory
  # config.vm.provision "ansible" do |ansible|
  #   ansible.playbook = "./provisioning/provision.yml"
  #   ansible.inventory_path="./provisioning/hosts.ini"
  # end
  ################################################################################################################################################
  ################################################################################################################################################
  #Configuración para despliegue en azure
  ################################################################################################################################################
  #Usamos la imagen azure-dummy para poder desplegar en azure
  config.vm.box = 'azure-dummy'
  # #Especificamos la ruta de la clave privada para la conexión ssh
  config.ssh.private_key_path = '~/.ssh/id_rsa'
  #Configuramos la maquina en azure
  config.vm.provider 'azure' do |az, override|
		# Variables necesarias para desplegar en AZURE
    az.tenant_id = ENV['AZURE_TENANT_ID'] 
    az.client_id = ENV['AZURE_CLIENT_ID']
    az.client_secret = ENV['AZURE_CLIENT_SECRET']  
    az.subscription_id = ENV['AZURE_SUBSCRIPTION_ID'] 
   #Parametros de la maquina virtual que se desplegará en azure
    #Nombre de la maquina virtual
    az.vm_name = 'restaurant'
    #Grupo al que pertenecera
    az.resource_group_name = 'vagrantrestaurant'
    #Tamaño de los recursos que les daremos, se usaran la versión mas parecida a la usada en local
    #2 Cores 4 Gb de RAM
    az.vm_size = 'Standard_B2s'
    #Imagen del SO, usaremos el mismo que en la versión local
    az.vm_image_urn = 'Canonical:UbuntuServer:16.04-LTS:latest'
		# Localización donde se situara nuestro server
		az.location = "westeurope"
    #Abrimos los puertos que necesitaran los servicios.
    az.tcp_endpoints = '8000-9000'

    
  end 
  #Le decimos que use el playbook.yml para provisionar
  config.vm.provision "ansible" do |ansible|
    ansible.playbook = "./provisioning/provision.yml"
  end
  ################################################################################################################################################

end
