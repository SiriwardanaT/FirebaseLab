package com.example.firebaselab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.firebaselab.modal.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    EditText username;
    EditText userID;
    EditText Address;
    EditText ContactNo;

    Button insert, update,delete, show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.name);
        userID = findViewById(R.id.Uid);
        Address = findViewById(R.id.uadd);
        ContactNo = findViewById(R.id.conatctNo);

        insert = findViewById(R.id.insert);
        update = findViewById(R.id.update);
        show = findViewById(R.id.show);
        delete = findViewById(R.id.delete);

       //insert method
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean isValidate = Validation(username.getText().toString(),userID.getText().toString(),Address.getText().toString(),ContactNo.getText().toString());
                if(isValidate){
                    User user = new User();
                    user.setUsername(username.getText().toString());
                    user.setID(userID.getText().toString());
                    user.setAddress(Address.getText().toString());
                    user.setContactNo(ContactNo.getText().toString());

                    FirebaseDatabase.getInstance().getReference("user").child(user.getID()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isComplete()){
                                clearControl();
                                Toast.makeText(MainActivity.this, "Data Insert succesfully", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(MainActivity.this, "Something wrong!", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                }
            }

        });
//retrieve function
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase
                        .getInstance()
                        .getReference()
                        .child("user").child("sId1")
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChildren()){
                                username.setText(snapshot.child("username").getValue().toString());
                                userID.setText(snapshot.child("id").getValue().toString());
                                Address.setText(snapshot.child("address").getValue().toString());
                                ContactNo.setText(snapshot.child("contactNo").getValue().toString());
                            }
                            else{
                                Toast.makeText(MainActivity.this, "No data found", Toast.LENGTH_SHORT).show();
                            }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    //update function
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setUsername(username.getText().toString());
                user.setID(userID.getText().toString());
                user.setAddress(Address.getText().toString());
                user.setContactNo(ContactNo.getText().toString());

                FirebaseDatabase.getInstance().getReference().child("user").child("sId1").setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isComplete()){
                            clearControl();
                            Toast.makeText(MainActivity.this, "Updated succesfully", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(MainActivity.this, "something wrong!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
        //delete function
       delete.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               FirebaseDatabase.getInstance().getReference("user").child("sId1").removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                   @Override
                   public void onComplete(@NonNull Task<Void> task) {
                       if(task.isComplete()){
                           clearControl();
                           Toast.makeText(MainActivity.this, "Deleted succesfully !", Toast.LENGTH_SHORT).show();
                       }
                       else{
                           Toast.makeText(MainActivity.this, "Something wrong!", Toast.LENGTH_SHORT).show();
                       }
                   }
               });
           }
       });





    }
    public void clearControl(){
        username.setText("");
        userID.setText("");
        Address.setText("");
        ContactNo.setText("");

    }
    public boolean Validation(String name,String id , String address,String contactNo){

        try{
            if(name.isEmpty()){
                Toast.makeText(this, "Enter name", Toast.LENGTH_SHORT).show();
                return false;

            }
            else if(id.isEmpty()){
                Toast.makeText(this, "Enter ID", Toast.LENGTH_SHORT).show();
                return false;
            }

            else if(address.isEmpty()){
                Toast.makeText(this, "address not empty", Toast.LENGTH_SHORT).show();
                return false;
            }
            else  if(contactNo.isEmpty()){
                Toast.makeText(this, "Enter contact no", Toast.LENGTH_SHORT).show();
                return false;
            }
            else {
                return true;
            }

        }
        catch (NumberFormatException n){
            Toast.makeText(this, "Plase eEnter Valid contact Number", Toast.LENGTH_SHORT).show();
            return false;
        }



    }


}