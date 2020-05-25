package com.ucy.firebaseapp;

import android.os.Bundle;
import android.view.WindowManager;

import com.ucy.firebaseapp.Fragments.ForgotFragment;
import com.ucy.firebaseapp.Fragments.LoginFragment;
import com.ucy.firebaseapp.Fragments.ProfileFragment;
import com.ucy.firebaseapp.Fragments.RegisterFragment;
import com.ucy.firebaseapp.Helper.TinyDB;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {

    private TinyDB tinyDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Must be fullscreen!
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        tinyDB = new TinyDB(this);
        goToFragment("login");
    }

    public void goToFragment(String key) {
        Fragment fragment;
        if (key != null) {
            switch (key) {
                case "login":
                    fragment = new LoginFragment();
                    break;
                case "register":
                    fragment = new RegisterFragment();
                    break;
                case "forgot":
                    fragment = new ForgotFragment();
                    break;
                case "profile":
                    fragment = new ProfileFragment();
                    break;
                default:
                    fragment = new LoginFragment();
                    break;
            }
            allOfFragment(fragment);
        }
    }

    public void allOfFragment(Fragment fragment) {
        if (fragment != null) {
            this.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
        }
    }

    public void setTinyData(String nick, String pass, int code){
        if(code == 1){
            tinyDB.putString("nick",nick);
        }else {
            tinyDB.putString("nick",nick);
            tinyDB.putString("pass",pass);
        }


    }

    public String getTinyNick(){
        return tinyDB.getString("nick");
    }

    public String getTinyPass(){
        return tinyDB.getString("pass");
    }

    public void clearTinyData(){
        tinyDB.clear();
    }
}
