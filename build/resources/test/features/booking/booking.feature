Feature: Creacion de una reserba

  Rule: Como usuario del aplicativo restful-booker necesito hacer una reserva

    Scenario: 01 - Como usuario realizo una reserva exitoso
      Given que ingreso los datos correctos en el formulario
      When se da click en el boton enviar
      Then se recibira un mensaje de reserva creada exitosamente


#    Scenario: 02 - Como usuario realizo un metodo POST no exitoso para hacer una reserva
#      Given que ingresa los datos incorrectos en el body en formato Json
#      And tengo un endpoint valido
#      When se da click en el boton send se envia la peticion Post
#      Then recibo un mensaje status 400.
