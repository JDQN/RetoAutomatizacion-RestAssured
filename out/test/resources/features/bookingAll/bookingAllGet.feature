Feature: Ver todas las reservas creadas por los usuarios

  Rule: Como administrador del aplicativo restful-booker necesito poder ver todas las reservas realizadas

  Scenario: 02 - Como admin reviso todas las reservas realizadas
    Given que administrador del aplicativo restful booker
    When doy click en el boton listar reservas
    Then el restful-booker sistema lista todas las reservas encontradas.
