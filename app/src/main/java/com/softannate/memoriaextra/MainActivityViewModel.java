package com.softannate.memoriaextra;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivityViewModel extends ViewModel {

    // MutableLiveData para mantener la lista de notas
    private final MutableLiveData<List<String>> notas = new MutableLiveData<>(new ArrayList<>());

    private final MutableLiveData<String> menToast = new MutableLiveData<>();

    // Añado una nueva nota a la lista
    public void agregarNota(String nota) {
        // Obtengo la lista actual de notas
        List<String> notasActuales = notas.getValue();
        if (notasActuales == null) {
            notasActuales = new ArrayList<>();
        }
        if (nota.isEmpty()) {
            menToast.setValue("Ingrese una nota");
            return;
        }

                String notaConAsterisco = "* " + iniciaMayuscula(nota);//añado asterisco al inicio yllamo a fncion para comenzar nota en mayúscula
                notasActuales.add(notaConAsterisco);// Agrego la nota modificada a la lista de notas
                Collections.sort(notasActuales); // Ordeno alfabéticamente
                notas.setValue(notasActuales); // Actualizo el LiveData con la lista modificada
                menToast.setValue("Nota guardada");
                Log.d("MainActivityViewModel", "Nota agregada y lista actualizada: " + notasActuales);
            }


    //método para que la nota inicie en mayúscula
    private String iniciaMayuscula(String textoNota) {
        if (textoNota == null || textoNota.isEmpty()) {
            return textoNota;// Si el texto es null o está vacío, lo retorno
        }
        return textoNota.substring(0, 1).toUpperCase() + textoNota.substring(1);// Convierto la primera letra a mayúscula y concateno el resto
    }

    // Obtengo la lista de notas
    public LiveData<List<String>> getNotas() {
        return notas;
    }
    //obtengo el mensaje Toast
    public LiveData<String> getMenToast(){
        return menToast;
    }

    // Limpia la lista de notas, seteo a una nueva vacia
    public void clearNotas() {
        notas.setValue(new ArrayList<>());
    }
}
