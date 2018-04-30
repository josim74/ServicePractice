package josim74.github.com.servicepractice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Random;

public class MyBoundService extends Service {

    private MyLocalBinder binder = new MyLocalBinder();
    public MyBoundService() {
    }

    public class MyLocalBinder extends Binder{
        MyBoundService getService(){
            return MyBoundService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public int generateRandomNumber(){
        Random random = new Random();
        return 1 + random.nextInt(100);
    }
}
