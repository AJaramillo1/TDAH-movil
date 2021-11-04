package com.example.tdah;

import com.google.firebase.database.FirebaseDatabase;

public class FirebaseApp extends android.app.Application {
    /**
     * Habilita la persistencia de datos en la aplicación
     */
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
