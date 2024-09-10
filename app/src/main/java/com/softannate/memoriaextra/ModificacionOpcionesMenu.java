package com.softannate.memoriaextra;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class ModificacionOpcionesMenu {
    private Context context;
    private NavigationView navigationView;

    public ModificacionOpcionesMenu(Context context, NavigationView navigationView) {
        this.context = context;
        this.navigationView = navigationView;
    }

    public void customizeMenu() {
        // Obtengo el menú de la NavigationView
        Menu menu = navigationView.getMenu();

        // Itero sobre los elementos del menú
        for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);

            // Configuro la vista personalizada para el ítem
            View actionView = LayoutInflater.from(context).inflate(R.layout.custom_menu_item, null);
            ImageView icon = actionView.findViewById(R.id.menu_icon);
            TextView title = actionView.findViewById(R.id.menu_title);

            // Seteo el ícono y el título
            icon.setImageDrawable(item.getIcon());
            title.setText(item.getTitle());

            // Para que no haya texto duplicado en la vista
            item.setActionView(actionView);

            // Elimino el texto predeterminado del ítem de menú y el icono
            item.setTitle("");
            item.setIcon(null);
        }
    }
}
