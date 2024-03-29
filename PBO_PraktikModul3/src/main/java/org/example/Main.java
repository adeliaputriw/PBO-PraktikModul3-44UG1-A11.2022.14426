
package org.example;

import PV.evolution.models.PasswordStore;

public class Main {
    public static void main(String[] args) {
        PasswordStore pass1 = new PasswordStore("Akun BCA", "1122334455", "RahasiaNegara");
        pass1.setCategory(PasswordStore.CAT_WEBAPP);
        System.out.println(pass1);
        System.out.println("\nUSERNAME: "+pass1.username+" \nPASSWORD: "+pass1.getPassword());
    }
}
