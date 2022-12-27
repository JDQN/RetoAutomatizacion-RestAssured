package co.com.restfulbooker.model;

import lombok.Data;

@Data
public class UsuarioModel {

    private String firstName;
    private String lastName;
    private float totalPrice;
    private Boolean depositpaid;
    private UsuarioModelDates bookingDates;
    private String additionalNeeds;

}
