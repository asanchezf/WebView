package com.antonioejemplos.webview;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

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


        txtBuscar=(EditText)findViewById(R.id.txtBuscar);
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
                wbBuscar.loadUrl("http://lema.rae.es/drae/srv/search?val=" + txtBuscar.getText().toString().toLowerCase(Locale.getDefault()));

//                Snackbar.make(v, "Navega por la pantalla para ver el resultado de la búsqueda...", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });


        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                wbBuscar.loadUrl("http://lema.rae.es/drae/srv/search?val=" + txtBuscar.getText().toString().toLowerCase(Locale.getDefault()));

//                Snackbar.make(view, "Navega por la pantalla para ver el resultado de la búsqueda...", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
