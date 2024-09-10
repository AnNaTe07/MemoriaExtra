package com.softannate.memoriaextra;

import android.os.Bundle;
import android.view.Menu;
import com.google.android.material.navigation.NavigationView;
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

        // Configuración de la barra de herramientas de la actividad principal
        setSupportActionBar(binding.appBarMain.toolbar);

        //DrawerLayout y NavigationView son para la navegación de la aplicación
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

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

        // Personalizo el menú usando la clase
        ModificacionOpcionesMenu menu = new ModificacionOpcionesMenu(this, navigationView);
        menu.customizeMenu();

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