package com.example.tdah;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
private FirebaseAuth mAuth;
private FirebaseUser fUser;
private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        mAuth= FirebaseAuth.getInstance();

        fUser = mAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }
    //conexion de activities
    public void ir_iniciar_sesion(View view){
        Intent ir = new Intent(this,InicioDeSesion.class);
        startActivity(ir);
    }
    public void ir_iniciar_sesion_psicologo(View view){
        Intent ir = new Intent(this,InicioDeSesionPsicologo.class);
        startActivity(ir);
    }


    @Override
    protected void onStart() {
        super.onStart();
            if(mAuth.getCurrentUser()!= null){
                databaseReference.child("Psicologo").orderByKey().equalTo(fUser.getUid()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            String string_url_pdf = snapshot.child("string_perfilProfesional").getValue().toString();
                            if(string_url_pdf.equalsIgnoreCase("-1")){
                                startActivity(new Intent(MainActivity.this, CargaPdf.class));

                            }else {
                                startActivity(new Intent(MainActivity.this, PsicologoPrincipal.class));
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        startActivity(new Intent(MainActivity.this, UsuarioPrincipal.class));


                    }
                });

                finish();
            }
    }
}