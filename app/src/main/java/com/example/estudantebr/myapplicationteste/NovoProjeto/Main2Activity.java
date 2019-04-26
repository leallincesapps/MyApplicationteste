package com.example.estudantebr.myapplicationteste.NovoProjeto;

import android.os.Bundle;
import android.widget.Toast;

import com.example.estudantebr.myapplicationteste.NovoProjeto.Fragments.Fragment_1;
import com.example.estudantebr.myapplicationteste.NovoProjeto.Fragments.Fragment_2;
import com.example.estudantebr.myapplicationteste.NovoProjeto.Fragments.Fragment_3;
import com.example.estudantebr.myapplicationteste.NovoProjeto.Fragments.Fragment_4;
import com.example.estudantebr.myapplicationteste.R;

import androidx.appcompat.app.AppCompatActivity;
import it.sephiroth.android.library.bottomnavigation.BottomNavigation;

public class Main2Activity extends AppCompatActivity implements BottomNavigation.OnMenuItemSelectionListener{



    BottomNavigation mBottomNavigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        getBottomNavigation().setDefaultSelectedIndex(0);

        //fragment inicial
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new Fragment_2())
                .addToBackStack(null)
                .commit();

    }


    @Override
    public void onContentChanged() {
        super.onContentChanged();
        mBottomNavigation = findViewById(R.id.BottomNavigation);
        if (null != mBottomNavigation) {
            //Typeface typeface = Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf");
            mBottomNavigation.setMenuItemSelectionListener(this);
            //mBottomNavigation.setDefaultTypeface(typeface);
        }
    }

    public BottomNavigation getBottomNavigation() {
        if (null == mBottomNavigation) {
            mBottomNavigation = findViewById(R.id.BottomNavigation);
        }
        return mBottomNavigation;
    }



    @Override
    public void onMenuItemReselect(int i, int i1, boolean b) {

        Toast.makeText(this, "Reselect position" + Integer.toString(i1), Toast.LENGTH_LONG).show();

    }

    @Override
    public void onMenuItemSelect(final int itemId, final int position, final boolean fromUser) {

        if (itemId == R.id.bbn_item1) {

            //fragment inicial
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new Fragment_2())
                    .addToBackStack(null)
                    .commit();


        } else if (itemId == R.id.bbn_item2) {

            //fragment inicial
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new Fragment_1())
                    .addToBackStack(null)
                    .commit();


        } else if (itemId == R.id.bbn_item3) {
            //fragment inicial
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new Fragment_3())
                    .addToBackStack(null)
                    .commit();


        } else if (itemId == R.id.bbn_item4) {

            //fragment inicial
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new Fragment_4())
                    .addToBackStack(null)
                    .commit();

        }
    }
}
