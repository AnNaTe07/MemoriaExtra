package com.softannate.memoriaextra.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.softannate.memoriaextra.MainActivityViewModel;
import com.softannate.memoriaextra.databinding.FragmentListarBinding;
import com.softannate.memoriaextra.ui.Adaptador.NotaAdaptador;

import java.util.ArrayList;
import java.util.List;

public class ListarFragment extends Fragment {

    // Binding para acceder a las vistas del fragmento
    private FragmentListarBinding binding;
    private MainActivityViewModel vm;// ViewModel para manejar la lógica de la aplicación
    private NotaAdaptador adaptador;// Adaptador para manejar la visualización de notas en RecyclerView

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // Inflo el layout del fragmento y creo el binding para acceder a las vistas
        binding = FragmentListarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

       // Obtengo una instancia del ViewModel asociado a la actividad
        vm = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
       //vm= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);

        // Creo una instancia del adaptador para RecyclerView con una lista vacía inicial
        adaptador=new NotaAdaptador(new ArrayList<>(), LayoutInflater.from(getContext()));
        binding.rv.setAdapter(adaptador);// Configuro el RecyclerView con el adaptador
        //LayoutManager para el RecyclerView para la disposición vertical de elementos
        binding.rv.setLayoutManager(new LinearLayoutManager(getContext()));

        // Observador para los cambios en la lista de notas desde el ViewModel
        vm.getNotas().observe(getViewLifecycleOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> notas) {
                Log.d("ListarFragment", "Notas observadas: " + notas);
                adaptador.modificarNotas(notas);// Actualizo el adaptador con la nueva lista de notas
            }
        });

        // Retorna la vista raíz del fragmento
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // Limpio el binding cuando la vista del fragmento se destruye
    }
}