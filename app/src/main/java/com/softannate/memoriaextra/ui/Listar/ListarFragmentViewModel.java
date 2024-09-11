package com.softannate.memoriaextra.ui.Listar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.softannate.memoriaextra.MainActivityViewModel;
import java.util.ArrayList;
import java.util.List;

public class ListarFragmentViewModel extends ViewModel {

    private final MutableLiveData<List<String>> notas=new MutableLiveData<>();

    public ListarFragmentViewModel() {

    }

    // Obtener la lista de notas
    public LiveData<List<String>> getNotas() {
        return notas;
    }

    // Limpiar la lista de notas
    public void clearNotas() {
          notas.setValue(new ArrayList<>());
        }
}
