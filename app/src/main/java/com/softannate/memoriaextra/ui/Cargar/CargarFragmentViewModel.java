package com.softannate.memoriaextra.ui.Cargar;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.softannate.memoriaextra.MainActivityViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CargarFragmentViewModel extends ViewModel {

    private final MutableLiveData<String> menToast = new MutableLiveData<>();
    private final MutableLiveData<List<String>> notas = new MutableLiveData<>(new ArrayList<>());

    public CargarFragmentViewModel() {

    }

    // Añadir una nueva nota
    public void agregarNota(String nota) {
        List<String> notasActuales = notas.getValue();
        if (notasActuales != null) {
            if (nota.isEmpty()) {
                menToast.setValue("Ingrese una nota");
                return;
            }
            String notaConAsterisco = "* " + iniciaMayuscula(nota);
            notasActuales.add(notaConAsterisco);
            Collections.sort(notasActuales);
            notas.setValue(notasActuales);
            menToast.setValue("Nota guardada");
            Log.d("CargarFragmentViewModel", "Nota agregada y lista actualizada: " + notasActuales);
        }
    }

    private String iniciaMayuscula(String textoNota) {
        if (textoNota == null || textoNota.isEmpty()) {
            return textoNota;
        }
        return textoNota.substring(0, 1).toUpperCase() + textoNota.substring(1);
    }

    // Obtener el mensaje Toast
    public LiveData<String> getMenToast() {
        return menToast;
    }

    public LiveData<List<String>> getNotas() {
        return notas;
    }

}
