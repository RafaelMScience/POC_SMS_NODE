package com.example.loginfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginfirebase.adapters.MensagemAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference ref;

    private MensagemAdapter adapter;
    private RecyclerView recyclerView;
    private ArrayList<Users> mensageList = new ArrayList<>();

    private EditText phone;
    private String textView = "mensagem de teste \n ooi teste \n hacker";

    TextView textsms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView =  findViewById(R.id.recyclerViewMensajes);

        ref = FirebaseDatabase.getInstance().getReference();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getMensagemFromFirebase();


        //send sms
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS, Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS}, PackageManager.PERMISSION_GRANTED);
        phone = findViewById(R.id.edt_number);
    }

    private void getMensagemFromFirebase(){
        ref.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot ds: dataSnapshot.getChildren()){
                        String login = (String) ds.child("login").getValue();
                        String senha = (String) ds.child("senha").getValue();
                        String telefone = (String) ds.child("telefone").getValue();
                        mensageList.add(new Users(login,senha, telefone, null));
                    }
                    adapter = new MensagemAdapter(mensageList, R.layout.mensagem_view);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1000) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "garantido", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "nao garantido", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void sendSMS(View view) {
        String number = phone.getText().toString();

        SmsManager mySmsManager = SmsManager.getDefault();
        mySmsManager.sendTextMessage(number, null, textView, null, null);
    }
}
