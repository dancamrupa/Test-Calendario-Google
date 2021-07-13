Feature: HU-003 Calendario Google
         Yo como usuario de Google
         quiero crear, modificar y eliminar un recordatorio
         en la pagina del calendario de google

  Background:
    Given digitar el usuario y password en la pagina de calendario de google con la url https://calendar.google.com/

  @Manual
  Scenario: Crear un recordatorio en el calendario de Google
    When creo un recordatorio en el calendario
      |username                       |password   |
      |estoessolounaprueba3@gmail.com |Pru3ba2021 |
    Then podre ver el recordatorio en pantalla

  @Manual
  Scenario: Modificar una tarea en el calendario de Google
    When modifico un recordatorio creado
      |username                       |password   |
      |estoessolounaprueba3@gmail.com |Pru3ba2021 |
    Then podre ver el recordatorio en pantalla

  @Manual
  Scenario: Eliminar una tarea en el calendario de Google
    When elimino un recordatorio creado
      |username                       |password   |
      |estoessolounaprueba3@gmail.com |Pru3ba2021 |
    Then no podre ver el recordatorio en pantalla