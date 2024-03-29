
package PV.evolution.models;

import PV.evolution.util.Encryptor;
import java.security.NoSuchAlgorithmException;

public class PasswordStore {
    public String name, username;
    private String password, hashKey;
    private double score;
    private int category;

    public static final int UNCATEGORIZED = 0;
    public static final int CAT_WEBAPP = 1;
    public static final int CAT_MOBILEAPP = 2;
    public static final int CAT_OTHER = 3;

    public PasswordStore(String name, String username, String plainPass){
        try {
            hashKey = Encryptor.generateKey();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error!!" + e.getMessage());
        }
        this.name = name;
        this.username = username;
        setPassword(plainPass);
        setCategory(UNCATEGORIZED);
    }

    public PasswordStore(String name, String username, String plainPass, int category){
        try {
            hashKey = Encryptor.generateKey();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error!!" + e.getMessage());
        }
        this.name = name;
        this.username = username;
        setPassword(plainPass);
        setCategory(category);
    }

    public void setPassword(String plainPass){
        try{
            this.password = Encryptor.encrypt(plainPass, hashKey);
        } catch (Exception e){
            System.out.println("Error!!" + e.getMessage());
        }
        calculateScore(plainPass);

    }

    public String getPassword(){
        try{
            return Encryptor.decrypt(this.password, hashKey);
        } catch (Exception e){
            System.out.println("Error!!" + e.getMessage());
        }
        return "";
    }

    public void setCategory(int category){
        if(category >= 0 && category <= 3){
            this.category = category;
        } else{
            this.category = 0;
        }
    }

    public String getCategory(){
        if (this.category == CAT_WEBAPP) {
            return "Aplikasi Web";
        } else if (this.category == CAT_MOBILEAPP) {
            return "Aplikasi Mobile";
        } else if (this.category == CAT_OTHER) {
            return "Akun Lainnya";
        }
        return "Belum Terkategori";
    }

    private void calculateScore(String plainPass){
        if(plainPass.length() > 15){
            score = 10;
        } else {
            score = (plainPass.length()/15.0) * 10.0;
        }
    }

    @Override
    public String toString(){
        return "Username : " + this.username +
                "\nPassword : " + this.password +
                "\nCategory : " + this.getCategory() +
                "\nScore : " + this.score;
    }
}

