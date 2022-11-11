package org.tmb.lomboktest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
//@JsonInclude(value = JsonInclude.Include.NON_NULL)   //will not pass the parameter if null
@JsonInclude(value = JsonInclude.Include.NON_EMPTY) //will not pass the parameter if empty or null
//@JsonPropertyOrder(alphabetic = true)
@JsonPropertyOrder(value = {"first_name","last_name","id","roles","email"})
@JsonIgnoreProperties(value = {"first_name","last_name","email"})
public class EmpLombok {
    private int id;
//    @JsonIgnore private String first_name;
//    @JsonIgnore private String last_name;
    private String first_name;
    private String last_name;
    private String email;
    private List<String> roles;
}