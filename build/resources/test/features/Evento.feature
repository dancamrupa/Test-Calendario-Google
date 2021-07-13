Feature: HU-001 Calendario Google
         Yo como usuario de Google
         quiero crear, modificar y eliminar un evento
         en la pagina del calendario de google

  Background:
    Given ingreso el usuario y password en la pagina de calendario de google con la url https://calendar.google.com/

  Scenario: Crear un evento en el calendario de Google
    When creo un nuevo evento en el calendario
      |username                       |password   |
      |estoessolounaprueba3@gmail.com |Pru3ba2021 |
    Then podre ver el evento en pantalla

  Scenario: modificar un evento en el calendario de Google
    When modifico un evento creado
      |username                       |password   |
      |estoessolounaprueba3@gmail.com |Pru3ba2021 |
    Then podre ver el evento en pantalla

  Scenario: Eliminar un cita en el calendario de Google
    When elimino un evento creado
      |username                       |password   |
      |estoessolounaprueba3@gmail.com |Pru3ba2021 |
    Then no podre ver el evento en pantalla