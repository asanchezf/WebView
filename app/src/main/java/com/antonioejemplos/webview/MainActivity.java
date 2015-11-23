package com.antonioejemplos.webview;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    /*http://www.vinidsl.com/android/collapsing-toolbar-layout/
    Lo primero las dependencias:
compile 'com.android.support:design:22.2.0'
compile 'com.android.support:appcompat-v7:22.2.0'
compile 'com.android.support:cardview-v7:22.2.0'
    El primero en ser presentado será el CoordinatorLayout, android design library introduce este l
    ayout que provee un nivel adicional de control sobre los eventos touch entre los child’s(hijos),
    algo que muchos componentes de la librería aprovechan de gran manera. Este sera el layout principal,
    y dentro de el armaremos todo el resto:


El segundo en ser presentado será el AppBarLayout, que en si no es mas que un vertical LinearLayout que implementa muchos de los conceptos de material design de app bar como ser los gestos de scroll.
Esta vista depende extremadamente de usar un “child”(hijo) directo (como el ScrollView) con un CoordinatorLayout, si se utiliza otro tipo de ViewGroup, la mayor parte de su funcionalidad no funcionará.

Cuando agregamos un Toolbar a un AppBarLayout se nos da acceso a los flags de scroll, para este ejemplo usaremos dos de ellos:

exitUntilCollapse: Este flag hace que el AppBar realice un scrollOff hasta que llegue al tamaño del toolbar, ahí se detendrá y dejara de hacer scroll quedando solamente nuestro toolbar.
scroll: Este flag debe estar en todas las vistas que van a hacer un “offScreen”, las que no contengan este flag, se mantendran en la parte superio de la pantalla.
Además de esto necesitaremos indicar como van a reaccionar algunas vistan cuando se haga el scroll, para eso usaremos CollapsingToolbarLayout.

CollapsingToolbarLayout es una envoltura (wrapper) para el Toolbar que implementa las funcionalidades de colapsar el AppBar, esta destinado a ser usado como “child”(hijo) directo del AppBarLayout.
Esta configuración del Toolbar app:layout_collapseMode=”pin” indica que el toolbar estara siempre visible.

Cuando se utiliza el Toolbar junto al CollapsingToolbarLayout, el titulo automáticamente aparece largo cuando se hace scroll maximo hacia abajo, y se acomoda al toolbar clavado cuando se colapsa al hacer scroll hacia arriba.
En la configuración del ImageView app:layout_collapseMode=”parallax” indica que cuando el toolbar se colapse, la imagen lo haga en modo parallax, es decir que lo haga a una velocidad diferente para darle una animación mas elegante.

Necesitamos declarar el contenido que estará debajo el toolbar, para esto, la vista que estará debajo debe llevar una configuración especial:
La configuración app:layout_behavior=”@string/appbar_scrolling_view_behavior” le indica al CoordinatorLayout que esta vista es la que estará bajo el AppBar.

Para finalizar agregamos un FloatingActionButton al AppBarLayout.
La configuración layout_anchor y layout_anchorGravity se utilizan para colocar componentes flotantes, como en este caso el FloatingActionButton relativo hacia otra view, en este caso nuestro AppBar, al realizar esto, podremos apreciar que cuando el AppBar se colapsa, este botón desaparece.



    * */

    private EditText txtBuscar;
    private Button btnBuscar;
    private WebView wbBuscar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(getString(R.string.app_name));


        txtBuscar=(EditText)findViewById(R.id.txtbuscar);
//        InputMethodManager imm = (InputMethodManager)getSystemService(
//                Context.INPUT_METHOD_SERVICE);
//        //check if no view has focus:
//        View view = this.getCurrentFocus();
//        if (view == null)
//            return;
//        imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);


        btnBuscar=(Button)findViewById(R.id.btnBuscar);
        wbBuscar=(WebView)findViewById(R.id.webViewBuscar);


        //Configuramos el navegador:utf-8, javascript...
        //String htmlString="html"; //**Your html string**
        WebSettings configura=wbBuscar.getSettings();
        configura.setJavaScriptEnabled(true);
        configura.setDefaultTextEncodingName("utf-8");

        //String base64 = Base64.encodeToString(htmlString.getBytes(), Base64.DEFAULT);
        //wbBuscar.loadData(base64, "text/html; charset=utf-8", "base64");

//txtBuscar.getText().toString().toLowerCase(Locale.getDefault());
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                devolverBusqueda();
            }
        });


        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                devolverBusqueda();


            }
        });


    }

    public void devolverBusqueda(){

        wbBuscar.loadUrl("http://lema.rae.es/drae/srv/search?val=" + txtBuscar.getText().toString().toLowerCase(Locale.getDefault()));


        // Forzamos el webview para que abra los enlaces internos dentro de la la APP
        wbBuscar.setWebViewClient(new WebViewClient());
        // Forzamos el webview para que abra los enlaces externos en el navegador
        wbBuscar.setWebViewClient(new MyAppWebViewClient());

        //Lineas para ocultar el teclado virtual (Hide keyboard)
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(txtBuscar.getWindowToken(), 0);
        txtBuscar.setText("");

//                Snackbar.make(view, "Navega por la pantalla para ver el resultado de la búsqueda...", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();


    }





}
