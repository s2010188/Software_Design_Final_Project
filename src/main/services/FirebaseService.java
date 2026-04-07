
// ---FINAL---

package main.services;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FirebaseService {

    private static DatabaseReference getDatabase(){
        return FirebaseDatabase
                .getInstance()
                .getReference();
    }

    private static String getTimestamp(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new Date());
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


    public static void createBank(String bank){
        try{

            getDatabase()
                    .child("banks")
                    .child(bank)
                    .child("created")
                    .setValueAsync(getTimestamp());

            getDatabase()
                    .child("banks")
                    .child(bank)
                    .child("savings")
                    .setValueAsync(0);

            getDatabase()
                    .child("banks")
                    .child(bank)
                    .child("total")
                    .setValueAsync(0);

            System.out.println("Bank created: " + bank);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void updateSavings(String bank, double previous, double amount){
        try{

            getDatabase()
                    .child("banks")
                    .child(bank)
                    .child("savings")
                    .setValueAsync(amount);

            getDatabase()
                    .child("history")
                    .child(bank)
                    .child("savings")
                    .child("previous")
                    .setValueAsync(previous);

            getDatabase()
                    .child("history")
                    .child(bank)
                    .child("savings")
                    .child("new")
                    .setValueAsync(amount);

            getDatabase()
                    .child("history")
                    .child(bank)
                    .child("savings")
                    .child("date")
                    .setValueAsync(getTimestamp());

            getDatabase()
                    .child("history")
                    .child(bank)
                    .child("savings")
                    .child("type")
                    .setValueAsync("savings_update");

            System.out.println("Savings updated: " + bank);

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

            getDatabase()
                    .child("history")
                    .child(bank)
                    .child("subAccounts")
                    .child(name)
                    .child("amount")
                    .setValueAsync(amount);

            getDatabase()
                    .child("history")
                    .child(bank)
                    .child("subAccounts")
                    .child(name)
                    .child("status")
                    .setValueAsync("active");

            getDatabase()
                    .child("history")
                    .child(bank)
                    .child("subAccounts")
                    .child(name)
                    .child("date")
                    .setValueAsync(getTimestamp());

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

            getDatabase()
                    .child("history")
                    .child(bank)
                    .child("subAccounts")
                    .child(name)
                    .child("status")
                    .setValueAsync("deleted");

            getDatabase()
                    .child("history")
                    .child(bank)
                    .child("subAccounts")
                    .child(name)
                    .child("date")
                    .setValueAsync(getTimestamp());

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

    public static void removeBank(String bank){
        try{


            getDatabase()
                    .child("banks")
                    .child(bank)
                    .removeValueAsync();

            getDatabase()
                    .child("history")
                    .child(bank)
                    .child("status")
                    .setValueAsync("deleted");

            getDatabase()
                    .child("history")
                    .child(bank)
                    .child("deletedDate")
                    .setValueAsync(getTimestamp());

            System.out.println("Bank removed: " + bank);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}