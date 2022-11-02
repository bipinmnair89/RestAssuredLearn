package org.tmb.learn;

public class RoleTenure {

    private String role;
    private String tenure;

    public RoleTenure(String role, String tenure) {
        this.role = role;
        this.tenure = tenure;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTenure() {
        return tenure;
    }

    public void setTenure(String tenure) {
        this.tenure = tenure;
    }
}