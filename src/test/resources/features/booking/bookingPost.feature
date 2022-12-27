Feature: Creacion de una reserba

  Rule: Como usuario del aplicativo restful-booker necesito hacer una reserva

    Scenario: 01 - Como usuario realizo una reserva exitoso
      Given que ingreso los datos correctos en el formulario
      When se da click en el boton enviar
      Then se recibira un mensaje de reserva creada exitosamente


    Scenario: 02 - Como usuario del aplicativo restful-booker realizo una reserva no exitosa debido a que la fecha es pasada
      Given que ingresa en el formulario de reserva una fecha pasada "2022-11-06" a la fecha presente "2022-12-30"
      When da click en el boton crear reserva
      Then recibo un mensaje indicando que la fecha de la reserva es incorrecta
