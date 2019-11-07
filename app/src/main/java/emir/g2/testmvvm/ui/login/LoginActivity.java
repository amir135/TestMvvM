package emir.g2.testmvvm.ui.login;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


import io.netopen.hotbitmapgg.library.view.RingProgressBar;
import emir.g2.testmvvm.R;
import emir.g2.testmvvm.ui.main.MainActivity;

public class LoginActivity extends AppCompatActivity {


    RingProgressBar mRingProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_login);
      //  DatabaseInitializer.populateAsync(AppDatabase.getAppDatabase(this));




        Button btnlogin=findViewById(R.id.btnLogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog loadingDialog = new Dialog(LoginActivity.this);
                loadingDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                loadingDialog.setContentView(R.layout.loading_popup);
                mRingProgressBar = (RingProgressBar) loadingDialog.findViewById(R.id.progress_bar_2);
                mRingProgressBar.setProgress(0);
                mRingProgressBar.setOnProgressListener(new RingProgressBar.OnProgressListener(){
                    @Override
                    public void progressToComplete()
                    {
                       /* LoginActivities loginActivities=new LoginActivities();
                        loginActivities.setEmail("UserEmail");
                        Date currentTime = Calendar.getInstance().getTime();
                        SimpleDateFormat simpleDate =  new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                        String stringDateTime = simpleDate.format(currentTime);
                        loginActivities.setLoginDate(stringDateTime);
                        loginActivities.save();*/



                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }
                });
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mRingProgressBar.setProgress(mRingProgressBar.getProgress()+ 1);
                        if(mRingProgressBar.getProgress()<100)
                            handler.postDelayed(this, 50);

                    }
                }, 1);


                loadingDialog.show();
                setDialogSizeWithScreen(loadingDialog);
            }
        });
    }
    private void setDialogSizeWithScreen(Dialog object){
        WindowManager.LayoutParams lp=new WindowManager.LayoutParams();
        Window window=object.getWindow();
        lp.copyFrom(window.getAttributes());
        lp.width=WindowManager.LayoutParams.MATCH_PARENT;
        lp.height=WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
    }
}
