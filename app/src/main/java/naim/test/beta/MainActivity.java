/*package naim.test.beta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
ImageView splash, logo,bonneprep,yummy;
Animation animSplash,animup,animright,animleft,animinup,animinright,animinleft,animinsplash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        splash=findViewById(R.id.splash);
        logo=findViewById(R.id.logo);
        bonneprep=findViewById(R.id.bonneprep);
        yummy=findViewById(R.id.yummy);

        bonneprep.setVisibility(View.INVISIBLE);
        yummy.setVisibility(View.INVISIBLE);
        logo.setVisibility(View.INVISIBLE);

        //////////////////////////////////////////////////////////////
        animright=AnimationUtils.loadAnimation(this,R.anim.right);
        animleft=AnimationUtils.loadAnimation(this,R.anim.left);
        animSplash=AnimationUtils.loadAnimation(this,R.anim.splash);
        animup=AnimationUtils.loadAnimation(this,R.anim.up);
        //////////////////////////////////////////////////////////
        animinleft=AnimationUtils.loadAnimation(this,R.anim.inleft);
        animinright=AnimationUtils.loadAnimation(this,R.anim.inright);
        animinup=AnimationUtils.loadAnimation(this,R.anim.inup);
        animinsplash=AnimationUtils.loadAnimation(this,R.anim.insplash);


        ////////////////////////////////////////////////////////////
        splash.setAnimation(animSplash);
        ///////////////////////////////////////////////////////////////////////////////////////
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {

                logo.setVisibility(View.VISIBLE);
                logo.setAnimation(animup);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        bonneprep.setVisibility(View.VISIBLE);
                        bonneprep.setAnimation(animright);

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                               yummy.setVisibility(View.VISIBLE);
                                yummy.setAnimation(animleft);


                                Handler handle = new Handler();
                                handle.postDelayed(new Runnable() {
                                    public void run() {
                                        //////////////////////////
                                        logo.setAnimation(animinup);
                                        yummy.setAnimation(animinup);
                                        bonneprep.setAnimation(animinup);
                                        ///////////////////////////////
                                        logo.setVisibility(View.INVISIBLE);
                                        yummy.setVisibility(View.INVISIBLE);
                                        bonneprep.setVisibility(View.INVISIBLE);
                                        Handler handle = new Handler();
                                        handle.postDelayed(new Runnable() {
                                            public void run() {
                                              Intent intent=new Intent(MainActivity.this,Menu.class);
                                              startActivity(intent);
                                            }
                                        }, 1000);

                                    }
                                }, 3000);
                            }
                        }, 1200);


                    }
                }, 1200);

            }
        }, 1200);


    }

}

 */
/*   ////////////WAIT WITH ANIMATION//////////////////////////
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                logo.setVisibility(View.VISIBLE);
                logo.setAnimation(animup);
                Handler handle = new Handler();
                handle.postDelayed(new Runnable() {
                    public void run() {
                        bonneprep.setVisibility(View.VISIBLE);
                        bonneprep.setAnimation(animright);
                        Handler handle = new Handler();
                        handle.postDelayed(new Runnable() {
                            public void run() {
                                yummy.setVisibility(View.VISIBLE);
                                yummy.setAnimation(animleft);
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                    constraintLayout.setBackground(drawable);
                                }


                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    public void run() {

                                        Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            public void run() {
                                                logo.setAnimation(animinup);
                                                yummy.setAnimation(animinup);
                                                bonneprep.setAnimation(animinup);
                                                splash.setAnimation(animinsplash);

                                                splash.setVisibility(View.INVISIBLE);


                                                menu.setVisibility(View.INVISIBLE);
                                                bonneprep.setVisibility(View.INVISIBLE);
                                                yummy.setVisibility(View.INVISIBLE);
                                                logo.setVisibility(View.INVISIBLE);

                                                Handler handler = new Handler();
                                                handler.postDelayed(new Runnable() {
                                                    public void run() {

                                                        linearLayout.setVisibility(View.VISIBLE);
                                                        menu.setVisibility(View.VISIBLE);

                                                    }
                                                }, 1000);




                                            }
                                        }, 1000);

                                    }
                                }, 1000);
                            }
                        }, 1000);

                    }
                }, 1000);


            }
        }, 1000);*/


/*


    <ImageView
        android:id="@+id/splash"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="@drawable/splash"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        tools:visibility="invisible" />

    <LinearLayout
        android:padding="20dp"
        android:id="@+id/linear"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_marginTop="50dp"
        android:gravity="center"

        android:orientation="vertical"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.333"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.809">

        <Button

            android:id="@+id/btnrecette"
            style="@style/Widget.AppCompat.Button"
            android:layout_width="match_parent"
            android:layout_height="70dp"
            android:layout_marginTop="10dp"
            android:background="@drawable/btn"
            android:drawableLeft="@drawable/ic_recette1"

            android:paddingLeft="30dp"
            android:text="Toutes les recettes"
            android:textAllCaps="false"
            android:textSize="18sp"
            android:textStyle="bold" />

        <Button
            android:id="@+id/btnspecial"
            style="@style/Widget.AppCompat.Button"
            android:layout_width="match_parent"
            android:layout_height="70dp"
            android:layout_marginTop="10dp"
            android:background="@drawable/btn"
            android:drawableLeft="@drawable/ic_special"
            android:paddingLeft="30dp"
            android:text="Les recettes spéciales"
            android:textAllCaps="false"
            android:textSize="18sp"
            android:textStyle="bold" />

        <Button
            android:id="@+id/btnfavori"
            style="@style/Widget.AppCompat.Button"
            android:layout_width="match_parent"
            android:layout_height="70dp"
            android:layout_marginTop="10dp"
            android:background="@drawable/btn"
            android:drawableLeft="@drawable/ic_baseline_favorite_24"
            android:paddingLeft="30dp"
            android:text="Mes favoris"
            android:textAllCaps="false"
            android:textSize="18sp"
            android:textStyle="bold" />

        <Button
            android:id="@+id/btnplus"
            style="@style/Widget.AppCompat.Button"
            android:layout_width="match_parent"
            android:layout_height="70dp"
            android:layout_marginTop="10dp"
            android:background="@drawable/btn"
            android:drawableLeft="@drawable/ic_apropos"
            android:paddingLeft="30dp"
            android:text="A propos"
            android:textAllCaps="false"
            android:textSize="18sp"
            android:textStyle="bold" />

        <Button
            android:id="@+id/btninfo"
            style="@style/Widget.AppCompat.Button"
            android:layout_width="match_parent"
            android:layout_height="70dp"
            android:layout_marginTop="10dp"
            android:background="@drawable/btn"
            android:drawableLeft="@drawable/ic_plus"
            android:text="Plus"
            android:textAllCaps="false"
            android:textSize="18sp"
            android:textStyle="bold"
            android:paddingLeft="30dp" />

    </LinearLayout>

    <TextView
        android:id="@+id/textmenu"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:background="#EAEAEA"
        android:gravity="center"
        android:text="MENU"
        android:textColor="#000000"
        android:textSize="40dp"
        android:textStyle="bold"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.498"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />


    <ImageView
        android:id="@+id/logo"
        android:layout_width="163dp"
        android:layout_height="145dp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:srcCompat="@drawable/logo"
        tools:visibility="invisible" />

    <ImageView
        android:id="@+id/bonneprep"
        android:layout_width="242dp"
        android:layout_height="116dp"
        android:layout_marginTop="4dp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.556"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/logo"
        app:srcCompat="@drawable/bonne_preparation"
        tools:visibility="invisible" />

    <ImageView
        android:id="@+id/yummy"
        android:layout_width="151dp"
        android:layout_height="109dp"
        android:layout_marginTop="12dp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/bonneprep"
        app:srcCompat="@drawable/yummy"
        tools:visibility="invisible" />



        /*
        menu=findViewById(R.id.textmenu);
        recettes=findViewById(R.id.btnrecette);
        special=findViewById(R.id.btnspecial);
        favori=findViewById(R.id.btnfavori);
        plus=findViewById(R.id.btnplus);
        apropos=findViewById(R.id.btninfo);
        constraintLayout=findViewById(R.id.constrainte);
        linearLayout=findViewById(R.id.linear);

        splash=findViewById(R.id.splash);
        logo=findViewById(R.id.logo);
        bonneprep=findViewById(R.id.bonneprep);
        yummy=findViewById(R.id.yummy);


        linearLayout.setVisibility(View.INVISIBLE);

        menu.setVisibility(View.INVISIBLE);
        bonneprep.setVisibility(View.INVISIBLE);
        yummy.setVisibility(View.INVISIBLE);
        logo.setVisibility(View.INVISIBLE);


        animright= AnimationUtils.loadAnimation(this,R.anim.right);
        animleft=AnimationUtils.loadAnimation(this,R.anim.left);
        animSplash=AnimationUtils.loadAnimation(this,R.anim.splash);
        animup=AnimationUtils.loadAnimation(this,R.anim.up);
        animinup=AnimationUtils.loadAnimation(this,R.anim.inup);
        animinsplash=AnimationUtils.loadAnimation(this,R.anim.insplash);




              recettes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, Recettes.class);
                startActivity(intent);

            }
        });
        //////////////////////////////
        special.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, Special.class);
                startActivity(intent);
            }
        });
        //////////////////Favori////////////
        favori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, Favori.class);
                startActivity(intent);
            }
        });

 */

