package com.softannate.memoriaextra;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.softannate.memoriaextra.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Configuración de la barra de aplicaciones y el menú de navegación
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;// Binding para acceder a las vistas de la actividad principal

    public static ArrayList<String> notas = new ArrayList<>();// Lista estática para almacenar notas

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflo el layout de la actividad principal y creo el binding para acceder a las vistas
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Configuro la barra de herramientas de la actividad principal
        setSupportActionBar(binding.appBarMain.toolbar);

        //DrawerLayout y NavigationView para la navegación de la aplicación
        DrawerLayout drawer = binding.drawerLayout;
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        // Configuracion de la navegación usando los IDs de los elementos del menú
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_cargar, R.id.nav_listar, R.id.nav_salir)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);

        // Configuracion de el ActionBar para trabajar con el NavController
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);

        // Configuracion de el NavigationView para trabajar con el NavController
        NavigationUI.setupWithNavController(navigationView, navController);

        //para sacar el color que trae por defecto cada item del menú
        navigationView.setItemIconTintList(null);


        // Ajusto los íconos y títulos
        Menu menu = navigationView.getMenu();

        for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);

            // Configuro la vista personalizada para el ítem
            View actionView = LayoutInflater.from(this).inflate(R.layout.custom_menu_item, null);
            ImageView icon = actionView.findViewById(R.id.menu_icon);
            TextView title = actionView.findViewById(R.id.menu_title);

            // Establezco el ícono y el título
            icon.setImageDrawable(item.getIcon());
            title.setText(item.getTitle());

            // Para que no haya texto duplicado en la vista
            item.setActionView(actionView);

            // Elimina el texto predeterminado del ítem de menú y el icono
            item.setTitle("");
            item.setIcon(null);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflo el menú de opciones; esto agrega ítems a la barra de acción si está presente
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        // Permite la navegación hacia arriba en el NavController, usando la configuración de AppBar
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}