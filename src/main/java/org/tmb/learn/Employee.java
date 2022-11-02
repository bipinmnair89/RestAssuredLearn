package org.tmb.learn;

import java.util.List;

public class Employee {

    private int id;
    private String first_name;
    private String last_name;
    private String email;
    private List<String> roles;
    private List<RoleTenure> roleTenureList;

    public Employee(int id, String first_name, String last_name, String email, List<String> roles, List<RoleTenure> roleTenureList) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.roles = roles;
        this.roleTenureList = roleTenureList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<RoleTenure> getRoleTenureList() {
        return roleTenureList;
    }

    public void setRoleTenureList(List<RoleTenure> roleTenureList) {
        this.roleTenureList = roleTenureList;
    }
}
