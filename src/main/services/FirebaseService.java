package main.services;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseService {

    private static DatabaseReference getDatabase(){

        return FirebaseDatabase
                .getInstance("https://sofdes-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference();
    }

    public static void testConnection(){

        try{
            getDatabase()
                    .child("test")
                    .setValueAsync("Hello Firebase");

            System.out.println("Firebase Test Success");

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void updateSavings(String bank, double amount){

        try{
            getDatabase()
                    .child("banks")
                    .child(bank)
                    .child("savings")
                    .setValueAsync(amount);

            System.out.println("Uploaded savings: " + bank);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void updateSubAccount(String bank,String name,double amount){

        try{
            getDatabase()
                    .child("banks")
                    .child(bank)
                    .child("subAccounts")
                    .child(name)
                    .setValueAsync(amount);

            System.out.println("Uploaded sub account: " + name);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void removeSubAccount(String bank,String name){

        try{
            getDatabase()
                    .child("banks")
                    .child(bank)
                    .child("subAccounts")
                    .child(name)
                    .removeValueAsync();

            System.out.println("Removed sub account: " + name);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void updateBank(String bank,double total){

        try{
            getDatabase()
                    .child("banks")
                    .child(bank)
                    .child("total")
                    .setValueAsync(total);

            System.out.println("Uploaded bank total: " + bank);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void updateGrandTotal(double total){

        try{
            getDatabase()
                    .child("grandTotal")
                    .setValueAsync(total);

            System.out.println("Uploaded grand total");

        }catch(Exception e){
            e.printStackTrace();
        }
    }

}