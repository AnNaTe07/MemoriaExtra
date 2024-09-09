package com.softannate.memoriaextra.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.softannate.memoriaextra.MainActivityViewModel;
import com.softannate.memoriaextra.databinding.FragmentCargarBinding;

public class CargarFragment extends Fragment {
    // ViewModel para manejar la lógica de la aplicación
    private MainActivityViewModel vm;
    // Binding para acceder a las vistas del fragmento
    private FragmentCargarBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Infla el layout del fragmento y crea el binding
        binding = FragmentCargarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        // Obtengo una instancia del ViewModel asociado a la actividad
        vm = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
       // vm= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);

        // Configuro el botón para agregar una nota
        binding.btnAgregar.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        // Obtengo el texto del campo de entrada, elimino espacios al inicio y al final
        String nota = binding.nota.getText().toString().trim();
        //si la nota no está vacia
        if (!nota.isEmpty()) {
            vm.agregarNota(nota);// Llamo al método en ViewModel para agregar la nota
            binding.nota.setText("");//limpio campo de entrada
            Toast.makeText(getContext(), "Nota guardada", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Ingrese una nota", Toast.LENGTH_SHORT).show();
        }
    }
});
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;// Limpia el binding cuando la vista del fragmento se destruye
    }
}