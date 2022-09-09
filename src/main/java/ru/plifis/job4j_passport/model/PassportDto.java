package ru.plifis.job4j_passport.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PassportDto {
    @JsonProperty("Series")
    private Integer series;
    @JsonProperty("Number")
    private Integer number;
    @JsonProperty("Expired")
    private Timestamp expired;
    @JsonProperty("Surname")
    private String surname;
    @JsonProperty("Firstname")
    private String firstname;
    @JsonProperty("Address")
    private String address;

}