package net.essalmi.patientsapp.service;

import net.essalmi.patientsapp.security.Entities.AppRole;
import net.essalmi.patientsapp.security.Entities.AppUser;

public interface AccountService {

    AppUser addNewUser(String username, String password, String email, String confirmedPassword);
    AppRole addNewRole(String role);
    void addRoleToUser(String username, String role);
    void removeRoleToUser(String username, String role);
    AppUser loadUserByUsername(String username);
}
