Feature: HU-002 Calendario Google
         Yo como usuario de Google
         quiero crear, modificar y eliminar una tarea
         en la pagina del calendario de google

  Background:
    Given ingresar el usuario y password en la pagina de calendario de google con la url https://calendar.google.com/

  Scenario: Crear una tarea en el calendario de Google
    When creo una tarea en el calendario
      |username                       |password   |
      |estoessolounaprueba3@gmail.com |Pru3ba2021 |
    Then podre ver la tarea en pantalla

  Scenario: Modificar una tarea en el calendario de Google
    When modifico una tarea creada
      |username                       |password   |
      |estoessolounaprueba3@gmail.com |Pru3ba2021 |
    Then podre ver la tarea en pantalla

  Scenario: Eliminar una tarea en el calendario de Google
    When elimino una tarea creada
      |username                       |password   |
      |estoessolounaprueba3@gmail.com |Pru3ba2021 |
    Then no podre ver la tarea en pantalla