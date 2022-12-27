Feature: Ver todas las reservas creadas por los usuarios

  Rule: Como administrador del aplicativo restful-booker necesito poder ver todas las reservas realizadas

  Scenario: 03 - Como admin reviso todas las reservas realizadas
    Given que administrador del aplicativo restful booker
    When doy click en el boton listar reservas
    Then el restful-booker sistema lista todas las reservas encontradas.

  Scenario: 04 - Como administrador necesito consultar las reservar hechas por cada usuario
    Given que como administrador del aplicativo restful booker necesito validar dichas reservas
    When doy click en el boton ver reserva
    Then el aplicativo restful-booker muestra las reservas encontradas.
