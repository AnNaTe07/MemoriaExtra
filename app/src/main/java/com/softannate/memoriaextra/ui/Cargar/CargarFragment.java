package com.softannate.memoriaextra.ui.Cargar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.softannate.memoriaextra.databinding.FragmentCargarBinding;

public class CargarFragment extends Fragment {
    // ViewModel para manejar la lógica de la aplicación
    private CargarFragmentViewModel cargarvm;
    // Binding para acceder a las vistas del fragmento
    private FragmentCargarBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Infla el layout del fragmento y crea el binding
        binding = FragmentCargarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        // Obtengo una instancia del ViewModel asociado a la actividad
        cargarvm = new ViewModelProvider(this).get(CargarFragmentViewModel.class);
       // vm= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);

        // Configuro el botón para agregar una nota
        binding.btnAgregar.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        // Obtengo el texto del campo de entrada, elimino espacios al inicio y al final
        String nota = binding.nota.getText().toString().trim();
        cargarvm.agregarNota(nota);
       binding.nota.setText("");
    }
});
        //observo el LiveData para los mensajes Toast
        cargarvm.getMenToast().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String mensaje) {
                if(mensaje != null && mensaje.isEmpty()){
                    Toast.makeText(getContext(),mensaje,Toast.LENGTH_SHORT).show();
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