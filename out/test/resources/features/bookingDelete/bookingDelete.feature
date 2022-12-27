Feature: Las reservas podran ser eliminadas

  Rule: Como usuario del aplicativo restful-booker necesito poder eliminar
        las reservas realizadas para poder gestionar nueva reservas

    Scenario: 05 - Como usuario del aplicativo necesito poder eliminar una reserva
      Given que el usuario del aplicativo restful booker tiene reservas cradas
      When doy click en el boton eliminar listar reservas
      Then el aplicativo restful-booker muestra n mensaje de reserva eliminada