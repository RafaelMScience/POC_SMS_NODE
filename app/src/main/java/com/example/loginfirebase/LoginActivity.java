package com.example.loginfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private EditText login, senha;

    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.edt_login);
        senha = findViewById(R.id.edt_senha);

        ref = FirebaseDatabase.getInstance().getReference().child("Users");
    }

    public void btn_login(View view){
        String login_user = login.getText().toString();
        final String senha_user = senha.getText().toString();

        try{
            ref.child(login_user).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Users user = dataSnapshot.getValue(Users.class);
                    if(senha_user.equals(user.getSenha())){
                        Intent i = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(i);
                        onStop();
                    }else {
                        Toast.makeText( LoginActivity.this, "fail", Toast.LENGTH_SHORT ).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }catch (Exception e){
            Toast.makeText( this, "Student doesnt exit", Toast.LENGTH_SHORT ).show();
        }
    }

    public void btn_register(View view){
        Intent cadastro = new Intent(this,CadastroActivity.class);
        startActivity(cadastro);
    }
}
