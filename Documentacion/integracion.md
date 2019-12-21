# Gestión Restaurante 
[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)
[![Language](https://img.shields.io/badge/Language-java-red.svg)](https://www.java.com/)
[![Framework](https://img.shields.io/badge/Framework-spring-green.svg)](https://spring.io/)
[![Build Status](https://travis-ci.org/antmordhar/ProyectoCC.svg?branch=master)](https://travis-ci.org/antmordhar/ProyectoCC)
[![codecov](https://codecov.io/gh/antmordhar/ProyectoCC/branch/master/graph/badge.svg)](https://codecov.io/gh/antmordhar/ProyectoCC)

## Integración continua

Para la integracion continua se usara **Travis-CI** y **GitHub-Actions**.

* **Travis-CI** : Pasa los tests unitarios y de cobertura al Proyecto. Ejecuta los tests para Open JDK 8 en la distro trusty. Y finalmente envia los datos del test de cobertura a **CodeCov**

Para más información vea [.travis.yml](https://github.com/antmordhar/ProyectoCC/blob/master/.travis.yml)

* **GitHub-Actions** : Permite la rápida configuración de test de integración sin necesidad de software de terceros. En nuestro caso estará pasando los tests para las versiones del Open JDK 8 para las ultima version de Linux.

Para más información vea [maven.yml](https://github.com/antmordhar/ProyectoCC/blob/master/.github/workflows/maven.yml)


[Volver al Index](https://antmordhar.github.io/ProyectoCC/)