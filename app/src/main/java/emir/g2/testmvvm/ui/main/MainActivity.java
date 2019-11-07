package emir.g2.testmvvm.ui.main;

import android.os.Bundle;

import emir.g2.testmvvm.R;
import emir.g2.testmvvm.base.BaseActivity;
import emir.g2.testmvvm.ui.breed.BreedFragment;


public class MainActivity extends BaseActivity {

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().add(R.id.screenContainer, new BreedFragment()).commit();
    }
}
