package icm.utils.auxiliardoinstrumentista;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

/**
 * Created by lucas on 14/03/18.
 */

public class SplashScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                irCriaLista();
            }
        }, 500);

    }
/*
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return true;
    }
*/
    private void irCriaLista() {
        Intent intento = new Intent(this, CriaLista.class);
        startActivity(intento);
        finish();
    }
}
