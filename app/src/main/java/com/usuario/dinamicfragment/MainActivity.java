package com.usuario.dinamicfragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.usuario.dinamicfragment.fragments.BFragment;
import com.usuario.dinamicfragment.fragments.FragmentA;

public class MainActivity extends Activity implements FragmentA.FragmentIterationListener{
    private FragmentA fragmentA;
    private BFragment fragmentB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //El siguiente bloque hay que hacerlo para que cuando cambiemos el estado de orientación de
        //la pantalla no se duplique el fragment.
        //En cada fragmente en onCreateView hay que poner "setRetainInstance(true);"
        android.app.FragmentManager fm = getFragmentManager();
        fragmentA = (FragmentA) fm.findFragmentByTag(FragmentA.TAG_FRAGMENTA);
        if (fragmentA == null) {
            fragmentA = new FragmentA();
            getFragmentManager().beginTransaction().add(R.id.activity_main, fragmentA, FragmentA.TAG_FRAGMENTA).commit();
        }
    }

    @Override
    public void onFragmentIterationListener(String text, int size) {
        Bundle bundle = new Bundle();
        bundle.putString(BFragment.TEXT_KEY, text);
        bundle.putInt(BFragment.SIZE_KEY, size);
        fragmentB = BFragment.newInstance(bundle);

        //Se empieza la transacción del FragmentA al FragmentB.
        android.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity_main, fragmentB);
        //Esto hace restauración de fragment cuando se quiera volver, se pone null pero puede ser
        //el nombre de la transacción. Guarda en la pila antes del commit.
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
