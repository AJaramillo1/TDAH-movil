package com.example.tdah.usuario.ajustes;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tdah.MainActivity;
import com.example.tdah.R;
import com.google.firebase.auth.FirebaseAuth;

public class AjustesFragment extends Fragment {
    private AjustesViewModel AjustesViewModel;
    private Button btn_cerrar_sesion;
    private FirebaseAuth mAuth;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AjustesViewModel =
                new ViewModelProvider(this).get(AjustesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_ajustes, container, false);
        final TextView textView = root.findViewById(R.id.text_ajustes);

        btn_cerrar_sesion = (Button) root.findViewById(R.id.btn_cerrar_sesion);
        btn_cerrar_sesion.setOnClickListener(v -> {
            mAuth.signOut();
            ir_a_main(inflater,container);
        });
        AjustesViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    private void ir_a_main(LayoutInflater inflater, ViewGroup container) {
        AjustesViewModel =
                new ViewModelProvider(this).get(AjustesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_ajustes, container, false);
        startActivity(new Intent(root.getContext(),MainActivity.class));
        getActivity().finish();
    }
}
