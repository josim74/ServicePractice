package josim74.github.com.servicepractice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MyBoundService boundService = null;
    private boolean isBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(MainActivity.this,MyBoundService.class);
        bindService(intent,connection,BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(isBound){
            unbindService(connection);
            isBound = false;
        }
    }

    public void startStartedService(View view) {
        startService(new Intent(MainActivity.this,MyStartedService.class)
        .putExtra("msg","hello android"));
    }

    public void stopStartedService(View view) {
        stopService(new Intent(MainActivity.this,MyStartedService.class));
    }

    public void startIntentService(View view) {
        startService(new Intent(MainActivity.this,MyIntentService.class));
    }


    public void generate(View view) {
        if(isBound){
            int randomNumber = boundService.generateRandomNumber();
            Toast.makeText(this, "random: "+randomNumber, Toast.LENGTH_SHORT).show();
        }
    }

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyBoundService.MyLocalBinder localBinder = (MyBoundService.MyLocalBinder) service;
            boundService = localBinder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
            boundService = null;
        }
    };
}
