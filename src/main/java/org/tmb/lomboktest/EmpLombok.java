package org.tmb.lomboktest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class EmpLombok {
    private int id;
    private String first_name;
    private String last_name;
    private String email;
    private List<String> roles;
}