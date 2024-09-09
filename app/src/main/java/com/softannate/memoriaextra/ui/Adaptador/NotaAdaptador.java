package com.softannate.memoriaextra.ui.Adaptador;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softannate.memoriaextra.R;
import com.softannate.memoriaextra.databinding.ItemNotaBinding;

import java.util.List;

public class NotaAdaptador extends RecyclerView.Adapter<NotaAdaptador.NotaViewHolder> {
    // Lista de notas que se muestra en el RecyclerView
    private List<String> listaNotas;
    // Inflater para inflar las vistas de los ítems
    private LayoutInflater li;

    // Constructor del adaptador que recibe la lista de notas y el LayoutInflater
    public NotaAdaptador(List<String> listaNotas, LayoutInflater li) {
        this.listaNotas = listaNotas;
        this.li = li;
    }

    @NonNull
    @Override
    public NotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Infla el layout para un ítem de la lista
       View view=li.inflate(R.layout.item_nota, parent,false);
        // Creo una nueva instancia de NotaViewHolder y paso la vista inflada
        return new NotaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotaViewHolder holder, int position) {
        // Obtengo la nota en la posición actual
        String nota= listaNotas.get(position);
        Log.d("NotaAdaptador", "Vinculando nota en posición " + position + ": " + nota);
        // seteo el texto de la nota en el TextView del ViewHolder
        holder.itemNota.setText(nota);

    }

    @Override
    public int getItemCount() {
        // Retorno el número total de ítems en la lista de notas
        return listaNotas.size();
    }

    public void modificarNotas(List<String> nuevaNota) {
        // Limpio la lista actual de notas
        this.listaNotas.clear();
        // Agrego todas las nuevas notas a la lista
        this.listaNotas.addAll(nuevaNota);
        Log.d("NotaAdaptador", "Lista de notas actualizada: " + listaNotas);
        // Notifico al adaptador que los datos cambiaron para actualizar la vista
        notifyDataSetChanged();
    }


    public class NotaViewHolder extends RecyclerView.ViewHolder{
        // TextView que mostrará el texto de la nota
            TextView itemNota;

              public NotaViewHolder(@NonNull View itemView) {
                super(itemView);
                // Inicializo el TextView del viewHolder
                itemNota = itemView.findViewById(R.id.itemNota);
            }
        }
    }

