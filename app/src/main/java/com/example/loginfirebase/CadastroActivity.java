package com.example.loginfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CadastroActivity extends AppCompatActivity {

    private EditText login, senha;

    private Users user = new Users();

    FirebaseDatabase database;
    DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        login = findViewById(R.id.edt_login);
        senha = findViewById(R.id.edt_senha);


        database = FirebaseDatabase.getInstance();
        ref = database.getReference();
    }

    public void btn_registrar(View view) {
        user.setLogin(login.getText().toString());
        user.setSenha(senha.getText().toString());

        ref.child("Users").child(user.getLogin()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    Toast.makeText(CadastroActivity.this, "existe", Toast.LENGTH_SHORT).show();
                }else{
                    ref.child("Users").child(user.getLogin()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText( CadastroActivity.this, "created successfull", Toast.LENGTH_SHORT ).show();
                            } else {
                                Toast.makeText( CadastroActivity.this, "created failed", Toast.LENGTH_SHORT ).show();
                            }
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
