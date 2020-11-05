package naim.test.beta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import naim.test.beta.favori.Favori;
import naim.test.beta.recette.Recettes;
import naim.test.beta.special.Special;

public class Menu extends AppCompatActivity {
    CardView recettes,special,favori,plus,menuback,share,rate,moreapp,privacy,about;
    ImageView background,textSplash;
    LinearLayout menu,menu2;
    ProgressBar progressBar;
    Animation animup,animinup,animright,animleft,animinright;



    public static DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        setContentView(R.layout.activity_menu);
        ///////////DATABASE////////////////////
        myDB = new DatabaseHelper(this);

        ////////////This code is called once time while the app is installed////////
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if(!prefs.getBoolean("firstTime", false)) {

            //random background/////////

         recette();




            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstTime", true);
            editor.commit();
        }
        /////////////////////////////////////


        menuback=findViewById(R.id.menuback);
        share=findViewById(R.id.share);
        rate=findViewById(R.id.rateUs);
        moreapp=findViewById(R.id.moreapp);
        about=findViewById(R.id.apropos);
        privacy=findViewById(R.id.privacy);
        progressBar=findViewById(R.id.progressbar);


        background=findViewById(R.id.background);
        recettes=findViewById(R.id.recette);
        special=findViewById(R.id.special);
        favori=findViewById(R.id.favori);
        plus=findViewById(R.id.plus);
        menu=findViewById(R.id.menu);
       menu2=findViewById(R.id.menu3);
        textSplash=findViewById(R.id.textsplash);

       menu2.setVisibility(View.INVISIBLE);
        menu.setVisibility(View.INVISIBLE);
        textSplash.setVisibility(View.INVISIBLE);

        animinright=AnimationUtils.loadAnimation(this,R.anim.inright);
        animright=AnimationUtils.loadAnimation(this,R.anim.right);
        animleft=AnimationUtils.loadAnimation(this,R.anim.left);
        animup=AnimationUtils.loadAnimation(this,R.anim.up);
        animinup=AnimationUtils.loadAnimation(this,R.anim.inup);


        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            public void run() {
                    //
                       textSplash.setVisibility(View.VISIBLE);
                       textSplash.setAnimation(animup);


                Handler handle = new Handler();
                handle.postDelayed(new Runnable() {
                    public void run() {

                        progressBar.setVisibility(View.INVISIBLE);
                        menu.setVisibility(View.VISIBLE);
                        menu.setAnimation(animinup);

                            }
                        }, 700);

                    }
                }, 1700);

        /////////List for background menu///////////////
        final List<Integer> list;
        list=new ArrayList<>();
        list.add(R.drawable.insane1);
        list.add(R.drawable.insane4);
    list.add(R.drawable.insane5);
        list.add(R.drawable.insane2);
      list.add(R.drawable.insane7);

       list.add(R.drawable.insane13);



        int ImageRandom=getRandomElement(list);
        background.setImageResource(ImageRandom);

        /////////////////recette///////////////
        recettes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, Recettes.class);
                startActivity(intent);
            }
        });
        ///////////////Special/////////////////////
        special.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, Special.class);
                startActivity(intent);
            }
        });
        /////////Favori//////////////
        favori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, Favori.class);
                startActivity(intent);
            }
        });
        //////////////////Plus/////////////////
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu.setAnimation(animleft);
                  menu2.setVisibility(View.VISIBLE);
                menu2.setAnimation(animright);
                menu.setVisibility(View.INVISIBLE);

               animinright=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.inright);
                animright=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.right);
                animleft=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.left);
                animup=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.up);
               animinup=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.inup);

            }
        });
        ////////////////////
        background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ImageRandom=getRandomElement(list);
                background.setImageResource(ImageRandom);
            }
        });

        //////////////Menu////////////////
        menuback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu2.setAnimation(animinright);
                menu.setVisibility(View.VISIBLE);
                menu.setAnimation(animinup);
                menu2.setVisibility(View.INVISIBLE);
                animinright=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.inright);
                animright=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.right);
                animleft=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.left);
              animup=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.up);
                animinup=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.inup);
            }
        });

        ///////////////////share/////////////
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_SUBJECT,"Les recettes de Bousta");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "LesRecettesDeBousta");
                shareIntent.setType("text/plain");
                startActivity(shareIntent);
            }
        });
       /////////////RateUS/////////////////
        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Menu.this,"Vous etes sur la version beta, cet option n'est pas implementé encore",Toast.LENGTH_LONG).show();

            }
        });
         /////////////Moreapp///////////////

        moreapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Menu.this,"Il n'y a pas d'autres applications pour l'instant",Toast.LENGTH_LONG).show();
            }
        });
        ///////////Privacy//////////////
        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Privacy about=new Privacy();
                about.show(getSupportFragmentManager(),"Pricacy");
            }
        });
        ////////////About///////////////
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                About about=new About();
                about.show(getSupportFragmentManager(),"About");

            }
        });


    }

    /////////////AJOUTDERECETTE/////////////////////////
    public void ajoutDeRecette(String titre,int image,int favorite,String ingredient,String preparation)
    {
        String Titre=titre;
      boolean check=myDB.checkExist(Titre);
        if (check==true) {
            int ImageInt = image;
            byte[] ImageByte = bigIntToByteArray(ImageInt);
            int Favorite=favorite;
            String Ingredient=ingredient;
            String ppreparation=preparation;
            myDB.insertData(Titre,ImageByte,Favorite,Ingredient,ppreparation);
        }
    }
    private byte[] bigIntToByteArray(int i ) {
        BigInteger bigInt = BigInteger.valueOf(i);
        return bigInt.toByteArray();
    }
    public int getRandomElement(List<Integer> list)
    {
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }


    /////////////////////////////////////
void recette(){
    //////////////////// ADD RECIPES HERE/////////////////////////////////
    ajoutDeRecette("Harira express marocaine",R.drawable.harira_express_marocain,0,
            "1 gros oignon\n½ botte de coriandre\n½ botte de persil (facultatif)\n3 branche de céleri\n1 bâtonnet de cannelle\n150 g de pois chiche\n1 c-a-c gingembre\n1 c-a-c de carvi (ou ras-el-hanout)\n1 c-a-c de curcuma\n80 g de lentilles\n500 ml de coulis de tomates (environ 4 tomates fraiches )\n2 c-a-soupe de concentre de tomate\nfeuilles de coriandre\nfeuille de céleri\n50 g de vermicelle\nsel, poivre\ntadouira (liant)\n180 g de farine\n360 ml d\'eau\n1 c-a-soupe de concentre de tomate\n1 citron",
            "Placer l'oignon, coriandre, persil et les feuilles de céleri (réserver les tiges) dans un mixer.\n" +
                    "Ajouter un verre d'eau et mixer finement.\n" +
                    "Verser le melange dans une cocotte minute.\n" +
                    "A ce stade on rajoute les pois chiche trempés la veille (j'utilise les pois chiche en conserve que je rajoute en fin de cuisson).\n" +
                    "Verser les lentilles préalablement rincées, la viande (si vous l'utilisez), le bâtonnet de cannelle (ou 1 c-a-c), tiges de céleri réservé.\n" +
                    "Placer les tomates fraîches coupées en quartier dans un mixer ajouter un peu d'eau et mixer.\n" +
                    "Verser la tomates mixées dans la cocotte à travers une passoire afin de retirer la peau.\n" +
                    "Ajouter 1 c-a-soupe de concentre de tomate.\n" +
                    "Ajouter les épices (gingembre, curcuma, sel, poivre, carvi ou ras el hanout).\n" +
                    "Verser approximativement 2 Litres d'eau. Fermer la cocotte et laisser cuire jusqu'au sifflement de la soupape, réduire le feu et compter environ 30 à 45 minute tout depend de la cuisson de la viande (si vous l'utilisez).\n" +
                    "Préparer le liant (appelé la Tadouira) :\n" +
                    "Mixer ensemble 2 doses d'eau pour 1 dose de farine, et 1 c-a-soupe de concentré de tomate (j'ai mis ½ c-a-soupe).\n" +
                    "Verser le liant dans la soupe (en quantité suffisante) tout en touillant sans arrêt, il se peut qu'on n'utilise pas toute la quantité (pour ma part je n'ai utilise que la moitie car la harira va continuer a épaissir il ne faut pas qu'elle soit trop liquide (il faut obtenir un aspect veloute lisse).\n" +
                    "Verser les vermicelles et continuer a remuer afin que ca n'attache pas au fond de la marmite.\n" +
                    "Quand tout est cuit rectifier l'assaisonnement en sel et poivre et parsemer de coriandre et céleri ciselés.\n" +
                    "Servir la Harira chaude.");
    //////////////////
    ajoutDeRecette("Enchiladas au poulet",R.drawable.enchiladas_au_poulet,0,
            "4 petites tortillas de blé entier\n" +
                    "500 ml (2 tasses) de lait\n" +
                    "80 ml (1/3 de tasse) de farine tout usage non blanchie\n" +
                    "15 ml (1 c. à soupe) de moutarde de Dijon\n" +
                    "Sel et poivre au goût\n" +
                    "1 poivron découpé en dés \n" +
                    "1 oignon rouge finement émincé \n" +
                    "15 ml (1 c. à soupe) d’huile d’olive\n" +
                    "1 c à café de cumin moulu \n" +
                    "1 c à café de paprika\n" +
                    "100g de maïs \n" +
                    "500 ml (2 tasses) de blanc de poulet coupé en morceaux",
            "Préchauffer le four à 190 °C (375 °F).\n" +
                    "Déposer les tortillas dans les alvéoles dans un plat allant au four et les façonner en coupelles (au besoin, chauffer au préalable les tortillas quelques secondes au micro-ondes pour les rendre plus souples). Faire dorer au four 7 minutes. Retirer du four et laisser tiédir.\n" +
                    "Pour la garniture au poulet :\n" +
                    "\n" +
                    "Dans une grande poêle, mettre à chauffer l’huile d’olive puis y faire revenir le poulet coupé en dès pendant environ 5 min. Saler légèrement.\n" +
                    "Ajouter ensuite l’oignon rouge finement émincé, et poursuivre la cuisson encore quelques minutes.\n" +
                    "Intégrer ensuite le poivron découpé en petits dès et l’ail haché.\n" +
                    "Ajouter également les épices, bien mélanger pour bien enrober le poulet et cuire encore 5 min.\n" +
                    "Finalement, ajouter la tomates découpée en petits morceaux, le mais et cuivre environ 20 min à feu doux.\n" +
                    "Rectifier l’assaisonnement avec le sel et le poivre. Réserver.\n" +
                    "\n" +
                    "Dans une casserole, verser le lait. À l’aide d’un tamis, ajouter la farine en pluie fine sur le lait. Incorporer en fouettant jusqu’à l’obtention d’un mélange lisse. Porter à ébullition en fouettant constamment, jusqu’à ce que la préparation épaississe. Retirer du feu. Incorporer la moutarde, saler et poivrer.\n" +
                    "Dans une poêle antiadhésive,déposez les tortillas,puis la garniture de poulet et recouvrir de sauce béchamel et de fromage râpé.\n" +
                    "Enfourner environ 10 min dans un four préchauffé à 200 °C jusqu'à ce que le fromage soit bien gratiné.Servir chaud");
    //////////////////////////////////////////////////////////////
    ajoutDeRecette("Mafe de bœuf ",R.drawable.mafe_de_boeuf,0,
            "800 g de boeuf coupé en morceaux \n" +
                    "150 g de beurre de cacahuètes non sucré\n" +
                    "150 g de concentré de tomates\n" +
                    "3 bouillons cubes\n" +
                    "2 carottes\n" +
                    "1 morceau de manioc\n" +
                    "1 ou 2 patates douces\n" +
                    "2 petites aubergines africaines (diakhatous)\n" +
                    "une dizaine de gombos \n" +
                    "poivre noir en grains\n" +
                    "1 bel oignon\n" +
                    "3 grosses gousses d'ail\n" +
                    "1 ou 2 piments rouges\n" +
                    "1 piment antillais","Etape 1\n" +
                    "Dans une cocotte, faire chauffer un fond d'huile d'arachide.\n" +
                    "Quand elle est chaude, y saisir les morceaux de bœufs.\n" +
                    "Etape 2\n" +
                    "Quand les morceaux sont bien dorés, ajouter le concentré de tomates dilué dans un litre d'eau.\n" +
                    "Laisser mijoter quelques minutes, le temps de préparer les légumes.\n" +
                    "Etape 3\n" +
                    "Couper les carottes, le manioc, les patates douces, en morceaux (moyens)\n" +
                    "Couper les extrémités des gombos, et réserver.\n" +
                    "Etape 4\n" +
                    "Au bout de 5 minutes, ajouter dans la cocotte le beurre de cacahuètes, et bien remuer.\n" +
                    "Rajouter les légumes en morceaux, rajouter un peu d'eau si besoin, jusqu'à hauteur.\n" +
                    "Etape 5\n" +
                    "Dans un pilon, écraser les gombos jusqu'à obtenir une pâte, puis ajouter dans la cocotte et bien remuer.\n" +
                    "Couvrir et laisser mijoter.\n" +
                    "Etape 6\n" +
                    "Préparer l'assaisonnement : dans le pilon, bien écraser les bouillons cubes, les grains de poivre et le piment. Ajouter l'ail dégermé et l'oignon coupés en petits morceaux. Bien écraser jusqu'à obtention d'une pâte qui sentira merveilleusement bon.\n" +
                    "Ajouter cette pâte dans la cocotte.\n" +
                    "Ajouter la touche finale : 1 bouchon de vinaigre.\n" +
                    "Etape 7\n" +
                    "Poursuivre la cuisson à feu doux jusqu'à ce que la viande soit bien tendre, soit une bonne heure. Si besoin, rajouter un peu d'eau et remuer de temps en temps afin que ça n'attache pas au fond.\n" +
                    "Servir avec un bon riz chaud et du piment en plus pour les palais moins sensibles.\n" +
                    "\n" +
                    "Bon appétit ");
    ///////////////////////////////////////
    ajoutDeRecette("Soupe de légumes au bœuf",R.drawable.soupe_au_lentille,0,
            "300 g viande de bœuf\n" +
                    "3 tomates moyennes (350 g)\n" +
                    "1 carotte et 1/2 (175 g)\n" +
                    "3 grosses pommes de terre (350 g)\n" +
                    "1/3 poireau (100 g)\n" +
                    "1/2 navet (100 g)\n" +
                    "1 oignon de taille moyenne (100 g)\n" +
                    "1 à 2 branches de céleri (75 g)\n" +
                    "2 L d’eau\n" +
                    "sel\n" +
                    "Huile d'olive \n","Lavez et découpez votre viande en gros morceaux (4 cm).\n" +
                    "Commencez par faire braiser votre viande afin de l’attendrir : mettez une grande casserole d’eau (1/2 L) sur le feu (moyen). Rajoutez-y votre viande. Faites mijoter, une première fois, pendant 1 h. Puis, lorsque toute l’eau ou presque s’est évaporée, rajoutez 1/2 L et faites mijoter une deuxième fois pendant 1 h.\n" +
                    "Pendant que la viande cuise, lavez et épluchez tous vos légumes. Ensuite, découpez-les en morceaux de taille moyenne.Lorsque la viande est à peu près tendre, rajoutez-y, en premier, vos tomates, vos pommes de terre et carottes et un peu d'huile d'olive.Puis recouvrez d’1/2 L d’eau.\n" +
                    "Faites cuire pendant 45 min environ.Puis rajoutez les navets et faites cuire pendant 10 min.\n" +
                    "A ce stade, l’eau s’est beaucoup évaporée. Alors rajoutez 1/2 L d’eau. Puis, rajoutez le céleri, l’oignon et le poireau et n’oubliez pas de saler votre soupe. Faites cuire 20 min et c’est prêt !\n" +
                    "Bon appétit");
    /////////////////////////////////////
    ajoutDeRecette("Sauce feuilles de manioc ",R.drawable.sauce_feuille_de_manioc,0,
            "250g de feuilles de manioc pilées ou mélangées,\n" +
                    "250g de viande de bœuf (ou toute autre viande), coupée en cubes\n" +
                    "3 cuillères à soupe de pâte d’arachide\n" +
                    "200 ml d’huile de palme (ou d’huile d’arachide)\n" +
                    "1 poisson, le tilapia ou le maquereau\n" +
                    "2 oignons, finement hachés\n" +
                    "3 gombos, finement coupés\n" +
                    "Du piment, ajustez selon votre goût\n" +
                    "Du sel, selon votre goût\n" +
                    "2 cubes de bouillon (Maggi)\n" +
                    "2 cuillères à soupe de crevettes séchées ou des écrevisses, pilées\n" +
                    "3 tasses d’eau (750ml)","Pour cette recette, je vous recommande d’utiliser une marmite profonde, surtout si c’est votre première fois.\n" +
                    "\n" +
                    "Étape 1: Ajoutez la viande avec le poisson dans la marmite, le sel et 2 tasses d’eau, puis portez à ébullition pendant 5 – 10 minutes. Lorsque le poisson est prêt, vous pouvez le retirer.\n" +
                    "\n" +
                    "Étape 2: Ajoutez les feuilles de manioc dans la casserole. Attendez encore 5 minutes, juste pour faire bouillir les feuilles pendant un petit temps.\n" +
                    "\n" +
                    "Étape 3: Ajoutez une autre tasse d’eau et la pâte d’arachide dans la sauce.\n" +
                    "\n" +
                    "Étape 4: Ajoutez l’huile de palme, l’oignon, le piment et les cubes de bouillon. Puis laissez mijoter pendant 25 minutes.\n" +
                    "\n" +
                    "Étape 5: Retirez les os du poisson déjà préparé et ajoutez dans la sauce, puis salez à votre goût.\n" +
                    "\n" +
                    "Étape 6: Ajoutez les crevettes ou du poisson séché et le gombo. Remuez bien et laissez mijoter pendant 10 ou 15 minutes. Ne pas oublier de remuer de temps en temps jusqu’à la cuisson complète.\n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "Quand c’est prêt, servez avec du riz et Bon Appétit!");
    ///////////////////
    ajoutDeRecette("Sauce gombo à la viande et poisson fumé",R.drawable.sauce_gombo_a_la_viande,0,
            "200g gombos,\n" +
                    "500g de bœuf, coupés en gros morceaux \n" +
                    "1 poisson fumé\n" +
                    "Huile d'arachide \n" +
                    "2 oignons hachés \n" +
                    "2 cubes de bouillon Ou Maggi cube\n" +
                    "3 piments frais (facultatif)\n" +
                    "sel\n" +
                    "1 cuillère à soupe de cumin moulu\n" +
                    "1 cuillère à café d'herbes de province \n" +
                    "1/2 cuillère à café de bicarbonate de sodium \n" +
                    "2 gousses d'ail hachées \n" +
                    "1l d’eau","Apprêtez votre viande et coupez-la en morceaux\n" +
                    "Faites chauffer un peu d’huile dans une casserole et faites y rissoler l’oignon, les gousses d'ail et les épices. Ajoutez la viande, le poisson et salez.\n" +
                    "Laissez brunir jusqu’à ce que le jus produit par la viande tarisse. Ajoutez ensuite de l’eau à hauteur de la viande, couvrez et laissez cuire durant 1 heure. \n" +
                    "Lorsque la viande est cuite, rajoutez de l’eau à hauteur de la viande et ajoutez le bicarbonate de sodium.Ajoutez le gombo(vérifiez le sel), assaisonnez de cubes et laissez cuire 30 minutes sans couvercle.\n" +
                    "Servez avec le fufu de votre choix, ou une boule de mil ou de maïs. \n" +
                    "Je ne vous dirai pas le goût avec du beurre clarifié,une tuerie " +
                    "Bon appétit ");
    ///////////////////////
    ajoutDeRecette("Mini kunafe au Nutella",R.drawable.mini_kunafe_au_nutella,0,
            "200 gr de Tel Kadayıf ou cheveux d’anges\n" +
                    "\n" +
                    "250g de Nutella \n" +
                    "\n" +
                    "4 cuillères à soupe de beurre\n" +
                    "\n" +
                    "150 gr de sucre\n" +
                    "\n" +
                    "3 verres d’eau","Faites d’abord un sirop avec le sucre et l’eau en le faisant bouillir et cuire 5 à 8 minutes. et laissez le refroidir\n" +
                    "\n" +
                    "Émiettez ou effilochez votre tel Kadayıf  et faites le revenir dans le beurre dans une poêle ou casserole large.\n" +
                    "\n" +
                    " Disposez la moitié dans le moule beurré à capcake que vous mettrez au four.\n" +
                    "\n" +
                    "Ajoutez sur chaque mini kunafe 2 c à soupe de chocolat.\n" +
                    "\n" +
                    "Disposez le reste du Tel Kadayıf\n" +
                    "\n" +
                    "Faites griller au four.\n" +
                    "\n" +
                    "Lorsque le dessert à pris une bonne couleur d’un côté, retournez le tout en utilisant un autre plat de même dimension.\n" +
                    "\n" +
                    "Sortez du four et versez le sirop refroidi.\n" +
                    "\n" +
                    "Et servez bien chaud !!\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "Bon appétit");
    ////////////////////////
    ajoutDeRecette("Salade de Riz au Thon et aux Œufs Durs(diététique)",R.drawable.salade_de_riz_au_thon,0,
            "200 g de riz\n" +
                    "160 g de thon au naturel\n" +
                    "120 g de poivrons\n" +
                    "120 g de tomates cerises\n" +
                    "120 g de maïs en conserve\n" +
                    "40 g d’olives vertes en rondelles\n" +
                    "2 œufs durs\n" +
                    "jus de 1/2 ciron\n" +
                    "3 feuilles de menthe hachées\n" +
                    "sel, poivre","Faites cuire le riz dans l’eau bouillante salée selon les indications sur le paquet. Ensuite égouttez-le.\n" +
                    "Lavez les poivrons, puis placez-les sur une feuille de papier cuisson et enfournez pendant environ 20 minutes à 180°C.\n" +
                    "Enfermez les poivrons dans une boîte hermétique ou un sac de congélation puis laissez-les refroidir.\n" +
                    "Ensuite pelez-les et coupez en cubes.\n" +
                    "Lavez le tomates cerises et coupez-les en quartier, puis écalez les œufs et coupez-les morceaux.\n" +
                    "Dans un grand saladier mettez le riz, les œufs, le thon, les tomates cerises, les morceaux de poivrons, le maïs égoutté, la menthe hachée, les olives vertes, le sel, le poivre puis arrosez de jus de citron et mélangez bien le tout.\n" +
                    "Placez ensuite votre salade au frais jusqu’au moment de servir.\n" +
                    "\n" +
                    "Bon appétit ");
    //////////////
    ajoutDeRecette("Dahl de lentille",R.drawable.dahl_de_lentille,0,
            "1 gros oignon, finement ciselé\n" +
                    "1 cuil. à soupe d’huile d’olive\n" +
                    "1 cuil. à café de curry rouge fort + 3 cuil. à café de curry doux\n" +
                    "210g de lentilles corail, rincées\n" +
                    "1 cm de gingembre, râpé\n" +
                    "1/2 cuil. à café de cumin\n" +
                    "100g de noisettes, grossièrement hâchées\n" +
                    "1 cuil. à soupe d’huile de colza + 1\n" +
                    "1 cuil. à café de miel\n" +
                    "20g de beurre doux\n" +
                    "200g de potimarron, coupé en dés\n" +
                    "1 petit bouquet de coriandre fraîche, finement ciselée","Faire revenir les oignons avec l’huile d’olive dans une grande casserole jusqu’à ce qu’ils deviennent translucides.\n" +
                    "Ajouter 1 cuillère à café de curry rouge fort et bien mélanger avec les oignons. Ajouter les lentilles corail et remuer.\n" +
                    "Verser le double de volume de lentilles en eau et porter à ébullition. Réduire le feu et laisser mijoter pendant 10 minutes, en mélangeant régulièrement. Ne pas hésiter à ajouter un peu d’eau si besoin.\n" +
                    "Ajouter les épices (curry, cumin, gingembre), mélanger le tout, puis saler et poivrer.\n" +
                    "Dans une poêle de taille moyenne, faire griller les noisettes grossièrement hâchées avec un peu d’huile de colza. Ajouter le miel, puis réserver.\n" +
                    "Dans une autre poêle, faire chauffer l’huile de colza restante avec un peu de beurre, puis y jeter les dés de potimarron. Les faire revenir pendant 8-10 minutes, jusqu’à ce qu’ils deviennent tendres et légèrement grillés.\n" +
                    "Retirer la poêle du feu, ajouter les noisettes aux dés de potimarron et mélanger.\n" +
                    "Parsemer de feuilles de coriandres fraîches au moment de servir. \n" +
                    "Servir avec du riz basmati (si possible parfumé à la cardamome pour donner au plat encore plus de saveurs).");
    ///////////////////////////////////////////////////////
    ajoutDeRecette("Gratin de patate douce",R.drawable.gratin_patate_douce,0,
            "2 grosses patates douce\n" +
                    "100 g de quinoa\n" +
                    "1/2 c à café de bouillon de légumes\n" +
                    "1 oignon rouge\n" +
                    "20 cl de crème de soja\n" +
                    "150 g de chèvre frais\n" +
                    "Sel, poivre\n" +
                    "1 c à soupe de Persil plat\n" +
                    "1  c à soupe de noix\n" +
                    "Huile d’olive","Faites cuire le quinoa dans 2 fois son volume d’eau avec le bouillon de légumes pendant 10 minutes à feu doux.\n" +
                    "Epluchez les patates douce, coupez les cubes puis faites cuire dans une eau bouillante salée pendant 20 minutes. Egouttez et réservez.\n" +
                    "Epluchez l’oignon, émincez le finement puis faites le revenir rapidement dans une poêle avec un peu d’huile.\n" +
                    "Préchauffez le four à 180°C.\n" +
                    "Fouettez la crème de soja avec le persil plat. Salez et poivrez.\n" +
                    "Ajoutez le quinoa, la patate douce et l’oignon emincé dans le saladier.\n" +
                    "Versez dans un plat à gratin.\n" +
                    "Concassez les noix puis déposez les sur le dessus du gratin.\n" +
                    "Déposez des morceaux de chèvre un peu partout sur l’ensemble du plat puis enfournez pour 20 minutes au four.\n" +
                    "Sortez puis servez aussitôt.");
    ////////////////////
    ajoutDeRecette("kebbeh orientales",R.drawable.kebbeh_oriantales,0,
            "1- Pour la pâte au boulgour\n" +
                    "\n" +
                    "100 gr de boulghour grain fin\n" +
                    "250 à 270 ml d’eau bouillante\n" +
                    "Sel, poivre, paprika\n" +
                    "70 gr de viande hachée\n" +
                    "2- Pour la farce\n" +
                    "\n" +
                    "300 gr de viande hachée\n" +
                    "1 oignon émincé\n" +
                    "Sel\n" +
                    "1/2 cuillère à café de b’har (composé de poivre noir, noix de muscade, clous de girofle, cannelle, gingembre et cardamone)\n" +
                    "un mélange coriandre fraîche et 2 ou 3 feuilles de menthe ciselée un peu de piment en poudre\n" +
                    "Huile de friture","Dans une poêle, faire revenir avec un filet d’huile d’olive, la viande hachée et l’oignon finement émincé avec la coriandre et la menthe pendant 30 minutes environ.\n" +
                    "Ajouter le mélange d’épices, saler. Réserver.\n" +
                    "Faire bouillir l’eau et verser sur le boulgour fin. Couvrir et laisser gonfler pendant 5-10 minutes.\n" +
                    "Mettre le boulgour dans le robot et mixer avec la viande viande hachée non cuite.\n" +
                    "Saler, poivrez et ajouter un peu d’épices de B’har. Mixer pendant 5 minutes environ. On doit obtenir un genre de pâte.\n" +
                    "Sinon vous pouvez malaxer l’ensemble à la main jusqu’à obtenir une pâte. Si celle est trop sèche, vous pourrez ajouter progressivement ajouter de l’eau (cuillerée par cuillerée)\n" +
                    "Garnir avec une bonne cuillère à café de viande hachée cuite.\n" +
                    "Refermer l’extrémité délicatement. Répéter la mise en forme avec le reste de la garniture et la pâte de boulgour<\n" +
                    "Chauffer l’huile de friture dans un poêlon profond.\n" +
                    "Plonger les kebbas dans l’huile chaude et doivent être complètement immergés dans l’huile.\n" +
                    "Cuire et faire dorer les boulettes qui doivent avoir une belle couleur dorée.\n" +
                    "Égouttez sur du papier absorbant.\n" +
                    "Dégustez les kebbeh chaudes ou à température ambiante.");
    /////////////////////////
    ajoutDeRecette("Maklouba riz renversé aux aubergines",R.drawable.maklouba_riz_renverse,0,
            "500 gr de viande hachée\n" +
                    "2 grosses aubergines\n" +
                    "3 oignons\n" +
                    "250 gr de riz basmati\n" +
                    "2,5 tasses de bouillon de viande (bouillon de poulet pour moi ) sinon eau\n" +
                    "50 gr d’amandes émondées entières (pour la décoration que j’ai pas mis)\n" +
                    "Sel\n" +
                    "Composition des épices b’har\n" +
                    "\n" +
                    "5 graines de cardamome (que j’écrase avec un couteau pour libérer les graines)\n" +
                    "1 bâtonnet de Cannelle,\n" +
                    "3 Clous de girofle,\n" +
                    "5 grains de kababa\n" +
                    "Poivre noir","Coupez les aubergines en rondelles (ou en tranches de 1 cm d’épaisseur les saler pour qu’elles dégorgent l’eau.\n" +
                    "Pendant ce temps, rincez le riz puis le tremper dans de l’eau chaude ajoutant quelques graines de cardamome pendant 15 à 20 minutes (le temps de frire les aubergines et cuire la viande hachée).\n" +
                    "Faire frire en premier les aubergines.\n" +
                    "Refaire la même chose avec les rondelles d’oignons puis réservez chaque légume séparément sur du papier absorbant.\n" +
                    "Dans un faitout, faire revenir la viande hachée et les oignons dans un peu d’huile.\n" +
                    "Assaisonnez de tout B’har et de sel puis arrosez de bouillon de poulet et laissez cuire. Conservez de la sauce pour la cuisson du riz.\n" +
                    "Dans un moule allant au four, disposez 1 couche d’aubergines en les faisant chevaucher.\n" +
                    "Ajoutez la viande hachée, tassez un peu puis au tour du riz égoutté, le bouillon restant de la viande.\n" +
                    "Couvrir de papier aluminium et passez le plat au four préchauffé pour 20 min environ jusqu’à absorption complète du bouillon et que le riz soit bien cuit.\n" +
                    "Laissez reposer une dizaine de minutes avant de démouler.\n" +
                    "Une fois retourné, présentez avec les oignons frits sur le dessus et un peu de viande (que j’ai mis de côté), mais chacun et chacune est artiste dans sa cuisine n’est ce pas !!");
    /////////////////////////
    ajoutDeRecette("Brioche chinoise",R.drawable.brioche_chinoise,0,
            "500 g de farine\n" +
                    "50g de sucre\n" +
                    "1 sachet de levure de boulanger\n" +
                    "1 c. à café de sel\n" +
                    "2 oeufs + du lait = 300 ml\n" +
                    "100 g de beurre mou\n" +
                    "200 g de chocolat Nutella ","Mélangez (soit à la main, soit au robot ou a la machine à pain) la farine, le sucre, le sel, le beurre, le lait et les oeufs et pour finir la levure. ATTENTION: si vous utilisez une machine à pain, mettez tous les ingrédients liquides d’abord puis le sucre, beurre et sel et recouvrez de la farine\n" +
                    "Laissez lever la pâte dans votre four éteint, avec un bol d’eau chaude (pour créer une étuve) pendant 1 heure\n" +
                    "Aplatissez la pâte sur un plan de travail fariné et couper des cercles.\n" +
                    " suivez les indications des images suivantes. (étalez le chocolat)\n" +
                    "(Voir image)disposez dans le plat et dorez avec un oeuf battu. Laissez lever à nouveau une heure, puis enfournez à 180°C pendant 30 minutes.");
    /////////////////////////
    ajoutDeRecette("Gâteau aux oranges",R.drawable.gateau_a_lorange,0,
            "2 oeufs\n" +
                    "2 oranges bio\n" +
                    "150 g de farine\n" +
                    "70 g de sucre\n" +
                    "30 g de miel\n" +
                    "60 g d’huile neutre (tournesol, coco, …)\n" +
                    "50 g de crème végétale (ou crème fraîche…)\n" +
                    "1 pincée de fleur de sel\n" +
                    "1 sachet de levure chimique","Dans un grand bol mélangez les oeufs, le sucre jusqu'à l'obtention d'un mélange crémeux puis le miel, ainsi que l’huile et la crème végétale\n" +
                    "Ajoutez le zeste et le jus d’une orange\n" +
                    "Enfin ajoutez les ingrédients secs : sel, farine, levure .\n" +
                    "Versez dans un moule de 20 cm environ et enfournez à four chaud 180°C pour 40 minutes\n" +
                    "Si vous souhaitez, pressez le jus de la seconde orange et versez le sur le cake à la sortie du four\n" +
                    "Petit glaçage possible : mélangez 4 cuil. à soupe de sucre glace, 1 cuil. à soupe de jus d’orange, et du zeste d’orange");
    ///////////////////
    ajoutDeRecette("Gâteau royal",R.drawable.gateau_royal,0,
            "Biscuit amande \n" +
                    "60 g de Poudre d'amande\n" +
                    "20 g de Farine ou Maïzéna\n" +
                    "3 Blancs d'oeufs\n" +
                    "125 g de sucre\n" +
                    "Praliné croustillant\n" +
                    "20 crêpes dentelles (attention, il y en a 2 par emballage)\n" +
                    "150 g de pralinoise ou chocolat praliné (rayon tablettes de chocolat en général)\n" +
                    "Mousse au chocolat\n" +
                    "200 g de chocolat noir ou au lait\n" +
                    "75 g de sucre\n" +
                    "3 jaunes (ce qui fait 3 oeufs entiers au final pour la recette)\n" +
                    "30 cl de crème liquide entière (30% de mat. grasse)","Réaliser le biscuit : Séparez les blancs des jaunes, réservez les 3 jaunes pour après\n" +
                    "Fouettez les blancs en neige, lorsqu'ils commencent à être mousseux, ajoutez la moitié du sucre en filet, continuez à battre. Ajoutez lorsqu'ils sont fermes, le reste du sucre, et finissez de battre 30 secondes, ils doivent être lisses et brillants\n" +
                    "Dans un bol, Mettez la farine et la poudre d'amande (idéalement, tamisez les), puis ajoutez les blancs progressivement, en soulevant la pâte, qui doit conserver un maximum de bulles d'air\n" +
                    "Etaler sur une plaque recouverte de papier cuisson, sur une épaisseur égale, et enfourner 10 min à 200°C\n" +
                    "Réalisez le croquant : faites fondre la pralinoise (au bain marie ou micro-ondes), puis versez la dans un bol avec les crêpes dentelles écrasées, mélangez avec une spatule.\n" +
                    "Dans votre cercle à pâtisserie (ou moule à gâteau à charnière), découpez un cercle de biscuit et placez le au fond. Venez répartir le mélange pralinoise sur le biscuit, lissez la surface et mettez au frigo pour que cela durcisse\n" +
                    "Réalisez la crême : Dans une casserole, faites un sirop avec le sucre et l'eau (2 cuil. à soupe), portez à ébullition et faites épaissir 2 minutes\n" +
                    "Fouettez les jaunes d'oeuf dans un bol en versant le sirop en filet, vous obtenez un mélange mousseux clair\n" +
                    "Faites fondre le chocolat au micro-ondes ou bain marie, et ajoutez le à cette préparation, laissez le tout baisser à température ambiante\n" +
                    "Fouettez la crème liquide, qu'elle soit bien ferme, puis ajoutez au mélange chocolaté, versez dans le cercle, lissez la surface et laissez prendre au frigo 4 heures avant de démouler\n" +
                    "Saupoudrez de cacao en poudre ou de décorations chocolatées");
    //////////////////////////
    ajoutDeRecette("Brioche buchty",R.drawable.brioche_de_buchy,0,
            "500 g de farine type 45 \n" +
                    "20 g de levure de boulangere fraiche (j'ai remplacé par 8 g de levure sèche (1-c-a-thé))\n" +
                    "200 ml de crème fraiche épaisse (n'ayant pas de crème fraiche j'ai remplacé par de la crème sure)\n" +
                    "2 oeufs et ajouter du lait pour obtenir 200 g (2 oeufs + lait = 200g)\n" +
                    "1 c-a-c de sel\n" +
                    "60 g de sucre\n" +
                    "chocolat au lait pour fourrer (j'ai oublié)\n" +
                    "Sucre glace pour saupoudrer à la sortie du four","1. Diluer la levure sèche dans un peu d’eau tiède (pas chaud) en y ajoutant un peu de sucre. Laisser mousser environ 10 min.\n" +
                    "\n" +
                    "2. Verser la farine dans un récipient, ajouter le sel et mélanger.\n" +
                    "\n" +
                    "3. Verser la levure diluée, l’oeuf, le lait tiède (pas chaud), la crème fraiche épaisse et le sucre.\n" +
                    "\n" +
                    "4. Pétrir la pâte jusqu’à obtenir une pâte lisse (un peu collante), j’ai du rajouter un peu de farine s’était un peu liquide, tout dépend de l’absorption et de la qualité de la farine.\n" +
                    "\n" +
                    "5. Couvrir d’un film alimentaire, ajouter un torchon et laisser lever 1 h à peu près dans un endroit à l’abri de l’air (four).\n" +
                    "\n" +
                    "6. Dégazer la pâte et diviser en boule de 40g à peu près, placer au fur et à mesure dans un cercle ou un rectangle (j’ai\n" +
                    "utilisé un cercle et un moule rectangulaire) en veillant à ne pas coller les boules. Si vous utilisez un cercle veillez à recouvrir la plaque de papier sulfurisé.\n" +
                    "\n" +
                    "7. Laisser lever 35-40 min à peu pres (vous remarquerez que les boules prendront du volumes et rempliront l’espace\n" +
                    "vide).\n" +
                    "\n" +
                    "8. Préchauffer le four à 160 C  et enfourner durant 25 à 30 min (la brioche sera juste dorée).\n" +
                    "\n" +
                    "9. Laisser refroidir complètement la brioche avant de saupoudrer de sucre glace.");
    ///////////////////////
    ajoutDeRecette("Ghee (beurre clarifié) ",R.drawable.beurre_clarifie,0,
            "500 g de beurre doux ou demi sel\n" +
                    "1 cuillère à café de gros sel (facultatif)","Sortir le beurre demi sel ou du beurre doux du réfrigérateur puis le découper en morceaux. Prendre une casserole de préférence à fond épais puis mettre à fondre le beurre demi sel avec le sel doucement à feu doux en remuant de temps à autre . Il faut compter une vingtaine de minutes de cuisson pour qu’il soit complètement fondu.\n" +
                    "Une fois complètement fondu, continuer la cuisson en écumant la surface du beurre avec une louche ou une écumoire pour le débarrasser de son eau ou proprement dit le petit lait, cela lui permettra de bien se conserver . Continuer à retirer les particules blanches formées à la surface tout en laissant le feu très doux afin de ne pas brûler le Ghee (beurre clarifiè) .\n" +
                    "Une fois qu’il ne restent plus de mousse blanche à la surface, passer le beurre clarifié dans une passoire ou un filtre à café, le ghee obtenu est d’une couleur or ancrée . Verser le dans un bocal en verre puis fermer le et laisser refroidir.\n" +
                    "Le ghee va se figer c’est tout à fait normal .Il se conserve très bien au réfrigérateur ou dans un placard à l’abri de la lumière et de la chaleur .");
    ////////////////////////
    ajoutDeRecette("Bœuf sauté à la chinoise",R.drawable.boeuf_saute_alachinois,0,
            "200 g de viande de boeuf tendre (filet ou faux filet)\n" +
                    "200 g d’oignons jaunes\n" +
                    "1 c. à soupe de sauce soja claire (la plus couramment trouvée)\n" +
                    "1 c. à café de sauce soja foncée ou épaisse\n" +
                    "2 c. à soupe d’huile de tournesol\n" +
                    "1/2 c. à café de poivre noir\n" +
                    "2 piments rouges\n" +
                    "1 c. à soupe de sauce soja claire\n" +
                    "1 c. à café de maizéna\n" +
                    "1 c. à café d’huile de tournesol","Pour la marinade\n" +
                    "Coupez le bœuf en lamelles très fines, puis dans un grand bol, ajoutez la sauce soja,et l’huile.\n" +
                    "Ajoutez ensuite la maizéna et mélangez bien. Réservez au frais au moins 30 minutes.\n" +
                    "Pour la cuisson \n" +
                    "\n" +
                    "Coupez les oignons en lamelles fines\n" +
                    "Faites chauffer 1 c. à soupe d’huile de tournesol dans une sauteuse ou un wok, à feu moyen, et ajoutez le bœuf que vous mélangez avec une spatule pour bien séparer les lamelles\n" +
                    "Dès que la viande commence à se colorer, réservez le boeuf et gardez l’huile dans la sauteuse. Ajoutez-y les oignons, éventuellement 2 c. à soupe d’eau, puis la sauce soja foncée. Les oignons sont bien colorés. Ajoutez alors la sauce soja claire puis remuez\n" +
                    "Quand les oignons commencent à fondre, ajoutez le boeuf, puis en mélangeant bien, poursuivez la cuisson 2 minutes,ajoutez le poivre,le piment et servez aussitôt !");
    /////////////////////
    ajoutDeRecette("Pigeon farci (Hamam Mahchi)",R.drawable.pigeon_farci,0,
            "2 pigeons frais prêts pour la cuisson\n" +
                    "150g de viande hachée\n" +
                    "2 oignons\n" +
                    "1/2 tasse de riz à grain rond\n" +
                    "1/4 tasst de noix de pin grillées\n" +
                    "1/3 tasse de pistaches décortiquées et grillées\n" +
                    "1/3 tasse d'amandes mondées et grillées\n" +
                    "1 cuil. à soupe de samneh (beurre clarifié)\n" +
                    "1/4 cuit. à café de canelle en poudre\n" +
                    "2 feuilles de laurier\n" +
                    "4 graines de cardamome\n" +
                    "sel et poivre au goût ","Placer les pigeons dans un plat de service préchauffé\n" +
                    "Mettre environ le 3/4 des condiments en poudre dans la cavité intérieur et sur l'extérieur des pigeons\n" +
                    "Faites chauffez la samneh dans une casserole et y fait revenir la viande hachée et le reste des condiments pour quelques minutes.\n" +
                    "Ajoutez le riz et 500ml d'eau, baisser le feu et laissez cuire le riz à feu doux jusqu'à évaporation de l'eau.\n" +
                    "Lorsque le riz est prêt, ajouter les graines de pin, les amandes et les pistaches.\n" +
                    "Farcir les pigeons avec cette farce et fermer à l'aide d'un corde\n" +
                    "Remettre dans une casserole et ajouter l'oignon, la cardamome, 1l d'eau et  les feuilles de laurier et 2 verres d'eau.\n" +
                    "Couvrir et amener à ébullition puis baisser le feu et laissez mijoter à feu doux pendant 50 min.\n" +
                    "Retirez les pigeons et badigonner avec du samneh\n" +
                    "Mettre dans un plat allant au four et cuire à 250°C pour 15 minutes");
    ////////////////
    ajoutDeRecette("Gâteau au chocolat (diététique)",R.drawable.gateau_chocolat_dietetique,0,
            "125g de farine complète \n" +
                    "280g de yaourt nature\n" +
                    "2 œufs \n" +
                    "170g de miel ou maple syrup \n" +
                    "25g de cacao non sucré \n" +
                    "6g de baking powder ou levure chimique \n" +
                    "1/2 cuillère à café de bicarbonate de sodium \n" +
                    "Pincée de sel\n" +
                    "Arôme vanillé \n" +
                    "\n" +
                    "Pour la sauce au chocolat \n" +
                    "\n" +
                    "2 pots de yaourt \n" +
                    "3 cuillères à soupe de miel \n" +
                    "3 cuillères à soupe de cacao\n","1) Préchauffer le four à 180°C \n" +
                    "\n" +
                    "2) Dans un bol,battre les œufs,l'arôme vanillé et le miel jusqu'à l'obtention d'un mélange crémeux puis ajouter le yaourt.\n" +
                    "\n" +
                    "3Tamiser ensuite les ingrédients secs (farine,cacao,sel,bicarbonate,levure) et les incorporer au mélange œuf miel et yaourt.\n" +
                    "\n" +
                    "4) Mettre dans un moule 18cm (si vous avez un plus grand doubler les ingrédients) et enfournez à 180°C pendant 20 à 25min (une fois sorti du four vérifier la cuisson à l'aide d'une cure-dent).Et laisser refroidir.\n" +
                    "\n" +
                    "La sauce : \n" +
                    "\n" +
                    "Battre dans un bol le yaourt,cacao et miel jusqu'à l'obtention d'un mélange crémeux et mettre au-dessus du gâteau refroidi.");
    //////////////////
    ajoutDeRecette("Gigot d agneau rôti et riz oriental",R.drawable.gigot_riz,0,
            "1 gigot d'agneau de 1.5kg\n" +
                    "1 pot de yaourt nature \n" +
                    "1 c à s de cumin\n" +
                    "1 c à café d'herbes de province \n" +
                    "2 ½ tasses de riz oriental basmati ou 500 g, trempées dans l'eau pendant 1 heure\n" +
                    "250 g d'agneau haché\n" +
                    "8 gousses de cardamome\n" +
                    "1 oignon moyen ou 125 g, haché\n" +
                    "¼ de cuillère à café de poivre noir\n" +
                    "3 cubes mouton Bouillon\n" +
                    "4 ½ tasses d'eau ou 1125 ml, quantité supplémentaire\n" +
                    "2 cuillères à soupe d'huile végétale\n" +
                    "noix frites","Placez le gigot d'agneau(assaisonner avec le sel, le poivre,yaourt nature,herbes de province et le peu d'huile végétale) au four à rôtir à 200 ° C préchauffé pendant 30min minutes ou jusqu'à ce qu'il soit bien doré .\n" +
                    "Ajouter l'eau, puis couvrir de papier d'aluminium et remettez dans le four. Conserver dans le four de 2 ¼ heure ou jusqu'à ce que la viande soit tendre.\n" +
                    "\n" +
                    "Pendant ce temps, chauffer l'huile dans une grande casserole, ajouter les oignons et cuire en remuant pendant 5 minutes .\n" +
                    "\n" +
                    "Ajouter l'agneau haché et cuire jusqu'à ce qu'il change de couleur, puis ajoutez la cardamome, des épices et les Bouillon cubes et remuer pendant quelques secondes.\n" +
                    "\n" +
                    "Ajouter le riz et remuer pendant 2 minutes puis ajouter l'eau. Remuez de temps en temps ensuite couvrir et laisser mijoter à feu doux pour 20-25 minutes ou jusqu'à ce que le riz soit cuit.\n" +
                    "\n" +
                    "Présenter le riz en plat de service. Retirez l'agneau, désosser l'épaule, et disposez-les sur le riz et garnir de noix frites et de servir avec du yogourt nature .");
    //////////////
    ajoutDeRecette("Crevette Tempura",R.drawable.crevette_tempura,0,
            "180g de farine (1 tasse et 1/2)\n" +
                    "1 c à café de bicarbonate de soude \n" +
                    "250ml d'eau glacée (1 tasse)\n" +
                    "1 c à soupe de vinaigre de riz\n" +
                    "450g (16-20) crevettes décortiquées  avec la queue,décongelées et épongées  \n" +
                    "90 g de céréales de riz (3 tasses)\n" +
                    "Paprika (au choix)\n" +
                    "Sel \n" +
                    "Huile pour la friture ","Préchauffer l’huile de la friteuse à 180 °C (350 °F). Déposer une grille sur une plaque ou la tapisser de papier absorbant. Préchauffer le four à 100 °C (200 °F) pour réserver les crevettes au chaud au fur et à mesure de la cuisson.\n" +
                    "Dans un bol, mélanger 120 g (1 tasse) de la farine, le sel et le bicarbonate. Ajouter l’eau et le vinaigre graduellement en fouettant jusqu’à ce que la pâte soit tout juste homogène.\n" +
                    "Dans une grande assiette creuse, répartir le reste de la farine (60 g/1/2 tasse). Dans une autre assiette creuse, répartir les céréales de riz.\n" +
                    "Enrober les crevettes de farine et les secouer pour en retirer l’excédent. Tremper les crevettes dans la pâte, une à la fois, en les tenant par le bout de la queue. Les enrober aussitôt de céréales de riz. Les plonger dans la friteuse en remuant légèrement le panier afin qu’elles n’y collent pas. Cuire 5 crevettes à la fois, de 2 à 3 minutes, jusqu’à ce qu’elles soient bien dorées. Égoutter sur la plaque.\n" +
                    "Saupoudrer les crevettes de paprika et de sel. Servir les crevettes avec la sauce aigre-douce au poivron (voir recette)");
    ////////////////////////
    ajoutDeRecette("Wok de poisson et légumes",R.drawable.wok_de_poisson_et_legume,0,
            "4 cuillères à soupe de sauce soja\n" +
                    " sel\n" +
                    "2 c. à café de cumin en poudre\n" +
                    " pincée de curry\n" +
                    "1  noisette de gingembre frais, râpé\n" +
                    " le jus de 1 citron\n" +
                    "1 poisson lavé,essuyé et coupé en morceaux (vous pouvez utiliser de filet et le poisson de votre choix)\n" +
                    "2 c. à café d’huile de sésame\n" +
                    "2 carottes, pelées et coupées en morceaux \n" +
                    "1  morceau de poivron (environ 40 g), coupé en minces bâtonnets\n" +
                    "1  branche de céleri, coupée en minces bâtonnets\n" +
                    "2  champignons shiitake, émincés finement\n" +
                    "75 ml (environ 1/3 tasse) de pois mange-tout, coupés en deux\n" +
                    "1/4 tasse de fèves germées, rincées à l’eau chaude et égouttées\n" +
                    "50 ml (1/4 tasse) de bouillon de volaille\n" +
                    "1 c. à soupe de coriandre, hachée\n" +
                    "100g de Choux fleur\n" +
                    "100g de brocolis ","Mélanger la sauce soja avec le sel, le cumin, le curry, le gingembre et le jus de citron.\n" +
                    "Y faire macérer le poisson 10 minutes.\n" +
                    "Dans un wok antiadhésif ou une grande poêle, chauffer l’huile de sésame et y faire revenir le poisson, à feu vif, 4 minutes, sans cesser de remuer.\n" +
                    "Retirer le poisson de la poêle et réserver.\n" +
                    "Dans la même poêle, ajouter le reste de l’huile et y faire sauter tous les légumes 3 minutes.\n" +
                    "Incorporer les fèves germées et faire mijoter 1 minute.\n" +
                    "Verser le bouillon et laisser réduire 4 minutes.\n" +
                    "Rectifier l’assaisonnement et relever avec de la coriandre.\n" +
                    "Remettre le poisson dans la casserole et laisser reposer quelques minutes\n" +
                    "\n" +
                    "Servir avec du riz.");
    //////////////////////
    ajoutDeRecette("Kabsa au poulet",R.drawable.kabsa_au_poulet,0,
            "Pour le mélange d'épices \"Kabsa\" :\n" +
                    "\n" +
                    "1 pincée de cumin\n" +
                    "2 pincées de cannelle\n" +
                    "1 pincée de coriandre\n" +
                    "1 pincée de cardamone\n" +
                    "2 clous de girofle moulus\n" +
                    "Pour le plat :\n" +
                    "\n" +
                    "1 poulet entier bien lavé (avec du vinaigre blanc et de l'eau)\n" +
                    "2 tomates émondées, épépinées et coupées en dés\n" +
                    "2 oignons hachés\n" +
                    "4 gousses d'ail émincées \n" +
                    "1 piment vert\n" +
                    "4 càs d'huile d'olive\n" +
                    "1 boîte de 70 g de concentré de tomate\n" +
                    "1 petit citron séché\n" +
                    "5 graines de cardamone et 2 clous de girofle mis ensemble dans une cuillère-filtre à thé\n" +
                    "2 pincées de muscade\n" +
                    "2 bâtons de cannelle\n" +
                    "100 g de raisins secs\n" +
                    "400 g de riz basmati\n" +
                    "Sel, poivre\n" +
                    "Pour la décoration du plat :\n" +
                    "\n" +
                    "100 g de pignons de pin grillés\n" +
                    "2 oignons coupés en lamelles frits\n" +
                    "50g d'amande ","Mélangez les épices \"Kabsa\".\n" +
                    "Faites chauffer l'huile dans une cocotte et colorez les oignons puis le poulet.\n" +
                    "Ajoutez l'ail, les épices \"Kabsa\", salez, poivrez et faites rissoler pendant 5 min.\n" +
                    "Ajoutez le citron séché, les tomates, le concentré de tomate, le piment vert.\n" +
                    "Couvrez d'eau froide, mettez-y les bâtons de cannelle ainsi que la cuillère-filtre à thé contenant les clous de girofle et les graines de cardamome.\n" +
                    "Laissez mijoter à couvert jusqu'à ce que le poulet se détache à la fourchette.\n" +
                    "Retirez les bâtons de cannelle et la cuillère-filtre à thé.\n" +
                    "Retirez le poulet,mettez le 15min au four préchauffé à 200°C pour être doré et réservez au chaud.\n" +
                    "Rectifiez l'assaisonnement.\n" +
                    "Ajoutez les raisins secs.\n" +
                    "Ajoutez le riz basmati qui doit être recouvert de 2 à 3 cm de sauce (à défaut, ajoutez de l'eau et réajustez l'assaisonnement).\n" +
                    "Couvrez et laissez cuire à feu doux jusqu'à ce que le riz soit tendre.\n" +
                    "Mettez le riz sur un plat de service, posez le poulet au-dessus et garnissez d'oignons frits,d'amande et de pignons de pin grillés.");
    //////////////
    ajoutDeRecette("Riz à l agneau recette saoudienne",R.drawable.riz_a_l_agneau,0,
            "4 c. à soupe huile d’olive\n" +
                    "2 gros oignons, coupés grossièrement\n" +
                    "1 c. à soupe de gingembre, haché\n" +
                    "600g de la viande d'agneau \n" +
                    "1 c. à soupe sel\n" +
                    "1 c. à soupe d'épices à kabsa, moulues\n" +
                    "1½ à soupe pâte de tomate\n" +
                    "4 tasses riz long (basmati) \n" +
                    "4 tasses eau\n" +
                    "½ tasse d'amande \n" +
                    "½ tasse de raisins secs\n" +
                    "1 c à soupe de curcuma \n" +
                    "2 gousses d'ail écrasées \n" +
                    "1 c à café clou de girofle moulu \n" +
                    "1 c à café de cannelle moulue ","1\n" +
                    "Préchauffer le four à 200°C, rincer le riz à l'eau courante et le laisser égoutter.\n" +
                    "\n" +
                    "2\n" +
                    "Chauffer 3 c. à soupe d’huile d’olive dans une casserole, à feu moyen. Faire sauter les oignons 5 minutes, jusqu’à ce qu’ils soient translucides. Ajouter le gingembre, mélanger et faire sauter une minute. \n" +
                    "\n" +
                    "3\n" +
                    "Ajouter les morceaux de l'agneau dans la casserole et bien mélanger. Ajouter le sel,les gousses d'ail,les épices et bien mélanger. Ajouter la pâte de tomate, mélanger de nouveau et laisser cuire 2-3 minutes.\n" +
                    "\n" +
                    "4\n" +
                    "Verser l’eau dans la casserole et bien mélanger, en s’assurant que la viande soit presque complètement submergée. Couvrir et réduire le feu.\n" +
                    "\n" +
                    "5\n" +
                    "Laisser mijoter jusqu’à ce que la viande soit cuite, environ 30 minutes. Retirer et la mettre sur une plaque.\n" +
                    "\n" +
                    "6\n" +
                    "Ajouter le riz et le curcuma à la casserole et couvrir. Cuire à feu doux jusqu’à ce que le riz ait absorbé tout le liquide.\n" +
                    "\n" +
                    "7\n" +
                    "Entretemps, chauffer la c. à soupe d’huile restante dans une poê Ajouter les raisins secs et cuire jusqu’à ce qu’ils soient dorés et gonflés. Retirer et réserver. Répéter avec les amandes.\n" +
                    "\n" +
                    "8\n" +
                    "Quand le riz est presque cuit, mettre la viande au four, Cuire quelques minutes pour qu'elle soit dorée.\n" +
                    "\n" +
                    "9\n" +
                    "Disposer le riz dans un grand plat de service. Mettre la viande sur le riz et garnir avec les amandes et les raisins.");
    //////////////////
    ajoutDeRecette("Falafel ",R.drawable.falafil,0,
            "1 l d'huile de friture \n" +
                    "500 g de pois chiches\n" +
                    "4 gousses d'ail\n" +
                    "1 bouquet de persil plat\n" +
                    "1 oignon (petit)\n" +
                    "2 c. à c. de coriandre en poudre ou coriandre moulue\n" +
                    "2 c. à c. de cumin en poudre\n" +
                    "2 c. à c. de sel ou sel fin\n" +
                    "1 c. à c. de bicarbonate de sodium ou levure chimique \n" +
                    "0,25 c. à c. de piment de Cayenne ou poivre de Cayenne","ÉTAPE 1 :\n" +
                    "Faites tremper vos pois chiches pendant 12 heures, égouttez-les, séchez-les dans un torchon puis avec du papier absorbant. Le fait de les faire tremper va les rendre beaucoup plus tendres et les faire gonfler.\n" +
                    "\n" +
                    "ÉTAPE 2 :\n" +
                    "\n" +
                    "Lavez les herbes, séchez-les puis coupez tout le haut du bouquet avec les branches les plus fines et les pluches Pelez l'oignon,coupez-le en morceaux,pelez les gousses d'ail,coupez-les en deux puis mixez l'ensemble grossièrement.\n" +
                    "\n" +
                    "ÉTAPE 3 :\n" +
                    "Ajoutez la moitié des pois chiches et les herbes.Ajouter les épices,les herbes et le bicarbonate ou la levure.Mixez d'abord par à-coups puis en continu.\n" +
                    "\n" +
                    "ÉTAPE 6 :\n" +
                    "Raclez de temps en temps les bords. Le mélange doit s'amalgamer. Si vous en prenez un peu entre vos mains et que vous pressez,vous devez pouvoir former une boule. Dès que c'est bon, ne mixez pas plus.Versez dans un bol, mixez le reste des ingrédients de la même manière et ajoutez dans le bol.Mélangez et laissez reposer au frais pendant 1 heure.\n" +
                    "\n" +
                    "ÉTAPE 7 :\n" +
                    "Prélevez des boules de la taille d'une grosse noix.Roulez entre vos mains.Puis aplatissez-les légèrement.Déposez-les au fur et à mesure sur une assiette puis réservez au frais, le temps de faire chauffer l'huile.\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "  Verser votre huile de friture dans une casserole et faites chauffer à 180°C. Déposez 4 falafels dans le bain de friture et faites cuire entre 5 à 6 minutes, afin qu'ils soient bruns. Retournez-le à mi-cuisson.\n" +
                    "\n" +
                    "ÉTAPE 12 :\n" +
                    "Déposez-les au fur et à mesure sur une assiette recouverte de papier absorbant et continuez la cuisson avec le reste des falafels.\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "ÉTAPE 13 :\n" +
                    "Servez dans un pain pitta avec des crudités et une sauce au yaourt (yaourt, huile d'olive, sel, poivre du moulin et persil plat ou une sauce au tahiné (Mélangez 4 cuillères à soupe de tahiné, 4 cuillères à soupe de jus de citron verte, une gousse d'ail pelée et râpée, 1 cuillère à soupe d'huile d'olive, du sel et assez d'eau chaude pour que la sauce ait la consistance d'un fromage blanc un peu épais). Vous pouvez aussi les servir dans une assiette avec une salade.");
    ////////////////
    ajoutDeRecette("Soupe au lentilles corail ",R.drawable.soupe_au_lentille,0,
            "1 l d'eau\n" +
                    "3 carottes\n" +
                    "180 g de lentilles corail\n" +
                    "1 oignon\n" +
                    "60 g de crème fraîche épaisse\n" +
                    "1 cube de bouillon\n" +
                    "Huile d'olive\n" +
                    "1 gousse d'ail\n" +
                    "1 tomate fraîche \n" +
                    "Sel ou sel fin\n" +
                    "Poivre","ÉTAPE 1 :\n" +
                    "Emincez l'oignon,la tomate et coupez les carottes en rondelles, hachez l'ail. Faites-les revenir légèrement dans une casserole, Ajoutez l'eau, les lentilles et le bouillon cube.\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "ÉTAPE 2 :Laissez cuire 25 min, vérifiez la cuisson des lentilles rajoutez la crème fraîche, gardez-en pour décorer, et mixez.\n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "ÉTAPE 3 :Rectifiez l'assaisonnement et décorez l'assiette avec de crème fraîche,un peu de piment et herbe aromatique à votre choix.");
    ///////////////////////
    ajoutDeRecette("Pastitsio ",R.drawable.pastitsio,0,
            "300 g de macaronis\n" +
                    "\n" +
                    "2 oeufs légèrement battus\n" +
                    "\n" +
                    "30 g de parmesan\n" +
                    "\n" +
                    "30 g de fromage râpé\n" +
                    "\n" +
                    "3 cuillères à soupe de chapelure\n" +
                    "\n" +
                    "Du sel\n" +
                    "\n" +
                    " Pour la sauce à la viande :\n" +
                    "\n" +
                    "2 cuillères à soupe d'huile d'olive\n" +
                    "\n" +
                    "750 g de boeuf haché\n" +
                    "\n" +
                    "2 oignons rouges\n" +
                    "\n" +
                    "400 g de tomates pelées \n" +
                    "\n" +
                    "70 g de concentré de tomate\n" +
                    "\n" +
                    "125 ml de bouillon de boeuf\n" +
                    "\n" +
                    "Une cuillère à café de cannelle \n" +
                    "\n" +
                    "1 oeuf légèrement battu\n" +
                    "\n" +
                    "Sel, poivre\n" +
                    "\n" +
                    "Herbes aromatiques \n" +
                    "\n" +
                    " Pour la béchamel au fromage :\n" +
                    "\n" +
                    "90 g de beurre\n" +
                    "\n" +
                    "75 g de farine\n" +
                    "\n" +
                    "875 de lait demi écrémé\n" +
                    "\n" +
                    "30 g de parmesan râpé \n" +
                    "\n" +
                    "150g mozzarella \n" +
                    "\n" +
                    "2 jaunes d'oeuf\n" +
                    "\n" +
                    "Du Poivre\n" +
                    "\n" +
                    "15 g de beurre pour le moule","Commencez par préparez la sauce à la viande.\n" +
                    "\n" +
                    "Préparez 125 ml de bouillon de boeuf.Épluchez et émincez vos oignons rouges.Dans un wok, faites chauffez l'huile d'olive.Ajoutez les oignons et faites-les revenir jusqu'à ce qu'ils prennent une belle couleur dorée.Ajoutez ensuite la viande hachée. Assaisonnez et laissez cuire quelques minutes.\n" +
                    "\n" +
                    "Quand le boeuf hachée est cuit, ajoutez les tomates pelées, le bouillon de boeuf, la cannelle,les herbes aromatiques et laissez mijoter le tout pendant 20 minutes environ.Laissez refroidir et ajoutez l'oeuf légèrement battu.\n" +
                    "\n" +
                    " Préparez ensuite la béchamel au fromage.\n" +
                    "\n" +
                    "Dans une grande casserole, faites fondre le beurre.Incorporez la farine en remuant bien et laissez épaissir.Ajoutez le lait petit à petit sans cesser de remuer.Laissez cuire jusqu'à ce que la sauce ait épaissi.Incorporez enfin la mozzarella, mélangez et laissez refroidir 5 minutes avant d'incorporez les jaunes d'oeufs.Poivrez la sauce selon vos goûts.\n" +
                    "\n" +
                    " Préparez enfin vos pâtes.\n" +
                    "\n" +
                    "Dans une grande casserole d'eau bouillante et salée, faites cuire vos macaronis.Une fois cuite, égouttez-les et mélangez-les au fromage et aux oeufs.Beurrez un grand plat à gratin. Déposez au fond les macaronis.Recouvrez de la sauce à la viande puis de la béchamel au fromage.\n" +
                    "\n" +
                    "Recouvrez de parmesan et enfournez 30min à 180°C (uniquement le bas du four et 5min le haut afin que le pastitsio prenne une couleur dorée)\n" +
                    "\n" +
                    "Laissez reposer 10 minutes avant de servir avec une salade verte par exemple..");
    /////////////////////
    ajoutDeRecette("Aubergines farcies au bœuf haché",R.drawable.aubergines_farcis,0,
            "450 g de boeuf haché\n" +
                    "2 aubergines\n" +
                    "2 tomates\n" +
                    "1 oignon\n" +
                    "2 gousses d'ail\n" +
                    "75 g de parmesan râpé \n" +
                    "75 g de gruyère rapé\n" +
                    "2 cl d'huile","ÉTAPE 1 :\n" +
                    "Lavez les aubergines, ôtez le pédoncule et coupez en 2 dans le sens de la longueur. Creusez la chair, pour pouvoir les farcir. Coupez en dès les morceaux récupérés.\n" +
                    "\n" +
                    "ÉTAPE 2 :\n" +
                    "\n" +
                    "Dans une poêle, versez l'huile, ajoutez l'oignon émincé et l'ail pilé et faites revenir. Ajoutez l'aubergine en dès, la tomate coupée en dès et en dernier la viande, faites revenir 5 minutes. Assaisonnez.\n" +
                    "\n" +
                    "ÉTAPE 3 :\n" +
                    "\n" +
                    "Disposez les aubergines dans un plat à gratin, les garnir avec la farce et saupoudrez du mélange de fromage. Enfournez 40 minutes à 180°C \n" +
                    "\n" +
                    "Servez chaud");
    ////////////////
    ajoutDeRecette("Nems",R.drawable.nems,0,
            "Pâte :\n" +
                    "1 œuf\n" +
                    "230 g de farine\n" +
                    "1 pincée de sel\n" +
                    "120 g d'eau\n" +
                    "maïzena\n" +
                    "Farce :\n" +
                    "350 g de porc maigre\n" +
                    "2 tranches de gingembre frais coupés en dés \n" +
                    "80 g de pousses de bambou égouttées\n" +
                    "8 champignons chinois séchés\n" +
                    "3 oignons nouveaux\n" +
                    "3 cuil. à soupe d'huile\n" +
                    "1 cuil. à café de sel\n" +
                    "2 cuil. à soupe de sauce soja\n" +
                    "120 g de pousses de soja\n" +
                    "1 cuil. à soupe de maïzena\n" +
                    "1 œuf battu\n" +
                    "huile d'arachide pour la friture ","Pâte :\n" +
                    "Tamiser la farine dans une terrine. Ajouter le sel. Former un puits et y casser un œuf. Commencer à mélanger tout en rajoutant l'eau afin d'obtenir une pâte lisse. Renverser cette pâte sur un plan de travail fariné avec de la maïzena. Travailler la pâte énergiquement avec les mains pendant 10 minutes jusqu'à ce qu'elle soit lisse et qu'elle ne colle plus.\n" +
                    "Couvrir la pâte et la laisser reposer 30 minutes.\n" +
                    "Former un boudin avec la pâte et la détailler en tranches de 4 à 5 cm d'épaisseur. Fariner les rondelles de pâte de maïzena, les aplatir à la main et les étaler au rouleau en galettes très minces.\n" +
                    "Fariner les galettes et les empiler. Couvrir d'un linge humide.\n" +
                    "Farce :\n" +
                    "Laisser tremper les champignons secs dans de l'eau pendant 25 minutes.\n" +
                    "Egoutter les champignons, les presser et les tailler en fines lamelles.\n" +
                    "Couper les oignons en deux puis en morceaux de 1 cm.\n" +
                    "Faire chauffer 3 cuil. à soupe d'huile dans une poêle. Ajouter le gingembre coupé en dés, les champignons, la viande et saler. Faire sauter à feu vif pendant 5 minutes tout en mélangeant.\n" +
                    "Ajouter les oignons, le bambou, les germes de soja, la sauce soja et laisser cuire 1 minute de plus.\n" +
                    "Verser la maïzena qui aura préalablement été dissoute dans 2 cuil. à soupe d'eau froide. Mélanger. Débarrasser la préparation dans un récipient et laisser refroidir.\n" +
                    "Montage :\n" +
                    "Poser une feuille de pâte sur le plan de travail et disposer 2 cuil. à soupe de farce sur le centre de la feuille. Replier la feuille de façon à former un rouleau. Préparer de même les autres nems.\n" +
                    "Cuisson :\n" +
                    "Chauffer l'huile de friture et plonger les nems par 3 ou 4 à la fois. Au bout de 5 mn, les rouleaux seront dorés. Les placer sur un sopalin(essuie tout) pour les égoutter. Consommer tant que c'est chaud et croustillant.\n" +
                    "Info :\n" +
                    "Il est possible de les conserver 30 mn dans un four chaud.");
    //////////////////////////

    ////////////////
    ajoutDeRecette("Ragout de boeuf",R.drawable.ragoutdeboeuf,0,
            "- 750 g de tranches de bœuf\n" +
                    "- huile, beurre,\n" +
                    "- 3 oignons\n" +
                    "- sel\n" +
                    "- poivre\n" +
                    "- 1/2 litre d’eau\n" +
                    "- 3 cubes d’extrait de viande\n" +
                    "- 750 g de carottes\n" +
                    "- 1 petite boîte d’extrait de tomates\n" +
                    "- 500 g de pommes de terre.\n","Etape : 1\n" +
                    "Coupez en gros dés le bœuf et faites dorer en cocotte avec un mélange d’huile et de beurre.\n" +
                    "Etape : 2\n" +
                    "Ajoutez les oignons préalablement coupés en lamelles et laissez mijoter jusqu’à ce que les oignons soient légèrement dorés. Assaisonnez de sel et de poivre\n" +
                    "Etape : 3\n" +
                    "Arrosez avec l’eau dans laquelle vous avez fait dissoudre les cubes.\n" +
                    "Etape : 4\n" +
                    "Portez à cuisson pendant 20 minutes.\n" +
                    "Etape : 5\n" +
                    "Incorporez, pour suivre, les carottes préalablement coupées en rondelle et l’extrait de tomates.\n" +
                    "Etape : 6\n" +
                    "Reportez à cuisson pendant 20 minutes.\n" +
                    "Etape : 7\n" +
                    "Ajoutez pour terminer les pommes de terre pelées et coupées en quartiers. Terminez la cuisson sur feu doux pendant 30 minutes\n");

    ajoutDeRecette("Accras de poulet",R.drawable.accras_depoulet,0,"- 300g de chair de blanc de poulet cru\n" +
            "- 150g de farine\n" +
            "- 1/2 sachet de levure\n" +
            "- 1 œuf\n" +
            "- 1 petit oignon\n" +
            "- 1 cuillère à café de cumin\n" +
            "- 1/2 botte de ciboulette\n" +
            "- 8cl d’eau\n" +
            "- 1 gousse d’ail\n" +
            "- 1cm de gingembre frais\n" +
            "- sel poivre\n" +
            "- piment d’espelette\n" +
            "- 1 litre d’huile de friture\n","Etape : 1\n" +
            "Mélangez la farine, la levure et un œuf. Ajoutez 8 cl d’eau en fouettant.\n" +
            "Etape : 2\n" +
            "Mixez l’ail, le gingembre, la ciboulette, l’oignon et le poulet. Salez et poivrez.\n" +
            "Etape : 3\n" +
            "Ajoutez cette farce à la pâte précédente, mélangez bien. Rectifier assaisonnement et saupoudrez de cumin et piment d’espelette.\n" +
            "Etape : 4\n" +
            "Faites chauffer l’huile de friture dans une casserole, lorsqu’elle est bien chaude, déposez des boules de pâte et laissez les dorer 5 minutes.\n" +
            "Etape : 5\n" +
            "Égouttez les ensuite sur du papier absorbant.\n" +
            "Etape : 6\n" +
            "Dégustez bien chaud avec un peu de fleur de sel\n");

    /////////////////////////////////

    ajoutDeRecette("Poulet à la crème et aux champignons",R.drawable.pouletalacremeauchampignon,0,
            "- 1 poulet découpé en morceaux\n" +
                    "- 2 cuillères à soupe d’huile d’olive\n" +
                    "- 2 oignons\n" +
                    "- 250g de champignons de Paris\n" +
                    "- 1 cuillère à café de concentré de tomates\n" +
                    "- 30cl de Vin blanc\n" +
                    "- 40 cl de bouillon de volaille\n" +
                    "- 2 cuillères à café de fond de volaille déshydratée\n" +
                    "- 1/2 bouquet de cerfeuil\n" +
                    "- 1/2 bouquet d’estragon\n" +
                    "- Sel\n" +
                    "- Poivre\n","Etape : 1\n" +
                    "Assaisonner le poulet de sel et de poivre.\n" +
                    "\n" +
                    "Etape : 2\n" +
                    "Chauffer l’huile d’olive dans une sauteuse ou une cocotte. Poêler le poulet à feu moyen-vif, en tournant, jusqu’à ce qu’il soit doré.\n" +
                    "\n" +
                    "Etape : 3\n" +
                    "Retirer de la poêle et réserver.\n" +
                    "\n" +
                    "Etape : 4\n" +
                    "Émincer les oignons, les faire revenir dans la cocotte du poulet.\n" +
                    "\n" +
                    "Etape : 5\n" +
                    "Laver et couper les champignons en deux, quatre si ils sont gros, ajouter aux oignons et faire revenir.\n" +
                    "\n" +
                    "Etape : 6\n" +
                    "Ajouter la tomate et le vin blanc, puis le bouillon et le fond de volaille, saler, poivrer.\n" +
                    "\n" +
                    "Etape : 7\n" +
                    "Ajouter le poulet dans la casserole et porter à ébullition, baisser le feu, couvrir et laisser mijoter 1 heure.\n" +
                    "\n" +
                    "Etape : 8\n" +
                    "Hacher finement les herbes.\n" +
                    "\n" +
                    "Etape : 9\n" +
                    "Servir chaud parsemé d’herbes\n");

    ///////////////////////////////


    ////////////////////////////////
    ajoutDeRecette("Poulet sauté au charizo",R.drawable.pouletalolive,0,
            "- Blancs de poulet : 500g\n" +
                    "- Tomates : 400g\n" +
                    "- Chorizo : 100g\n" +
                    "- Olives : 24\n" +
                    "- Oignon rouge : 1\n" +
                    "- Herbes de Provence\n" +
                    "- Huile d’olive, sel, poivre\n","Etape : 1\n" +
                    "Pelez les tomates, le chorizo et l’oignon.\n" +
                    "Etape : 2\n" +
                    "Concassez les tomates, émincez l’oignon et coupez le chorizo en rondelles. Coupez les blancs de poulets en petits morceaux\n" +
                    "Etape : 3\n" +
                    "Dans une sauteuse huilée à feu moyen, faites cuire l’oignon 5 minutes.\n" +
                    "Etape : 4\n" +
                    "Ajoutez le poulet et faites-le dorer à feu vif 2 minutes.\n" +
                    "Etape : 5\n" +
                    "Ajoutez le chorizo, les olives, les herbes et la tomate, salez et poivrez.\n" +
                    "Etape : 6\n" +
                    "Couvrez la sauteuse et laissez mijoter à feu doux environ 15 minutes\n" +
                    "Etape : 7\n" +
                    "Servez bien chaud et accompagnez de riz, pâtes ou légumes.\n");
    /////////////////////////////////////
    ajoutDeRecette("Beignets de pomme de terre",R.drawable.beignetsdepommeterre,0,"- 6 pommes de terre\n" +
            "- 2 oeufs\n" +
            "- 1 cuillère à soupe de farine\n" +
            "- Sel, poivre\n" +
            "- Ciboulette\n" +
            "- Crème fraîche\n","Pour réussir les beignets de pommes de terre de ma grand-mère :\n" +
            "1/   Râper les pommes de terre crues et ajouter les oeufs battus, le sel, le poivre et la farine tamisée.\n" +
            "2/   Bien mélanger le tout.\n" +
            "3/   Dans une poêle, mettre un peu d'huile.\n" +
            "4/   Avec une cuillère à soupe prendre la préparation et faites des galettes, de la taille de votre paume.\n" +
            "5/   Mettre à cuire et faire bien dorer de chaque coté (environ 3 minutes chaque face).\n" +
            "6/   Disposer sur du papier absorbant pour les égoutter et enlever l'excédent de gras.\n" +
            "7/  Saupoudrer de ciboulette hachée et accompagner de crème fraîche.\n" +
            "Servis avec une salade verte, les beignets de pommes de terre sont prêts à être dégustés !\n");
    ///////////////////////////////////////////////////

    ajoutDeRecette("Oeufs espagnoles", R.drawable.oeufs_espagnole, 0, "– 400 ml de tomates concassées\n Pour 4 personnes\n" +
            "– 8 petits oeufs\n" +
            "– 1 gros poivron rouge\n" +
            "– 1/2 poivron jaune\n" +
            "– 1 oignon jaune de 100 g environ\n" +
            "– 2 gousses d’ail\n" +
            "– 3 cuil. à soupe ou à table d’olives vertes en rondelles ou 30 g\n" +
            "– 1 cuil. à soupe ou à table d’huile d’olives\n" +
            "– 1 cuil. à soupe ou à table de concentré ou pâte de tomate\n" +
            "– 1 cuil. à soupe ou à table d’origan séché\n" +
            "– 1 cuil. à café ou à thé de paprika doux\n" +
            "– 1/2 cuil. à soupe ou à table de coriandre hachée\n" +
            "– 1/2 cuil. à café ou à thé de paprika fumé\n" +
            "– poivre au goût", "Etape: 1\n" +
            "Epluchez l’oignon et taillez-le en dés.\n" +
            "Etape: 2\n" +
            "Lavez, épluchez et taillez les poivrons en dés.\n" +
            "Etape: 3\n" +
            "Dans une grande poêle, faites chauffer l’huile à feu moyen. Pendant qu’elle\n" +
            "chauffe, épluchez l’ail et taillez-la en petits dés.\n" +
            "Etape: 4\n" +
            "Ajoutez l’oignon, les poivrons et l’ail et faites revenir à feu moyen pendant 2\n" +
            "à 3 minutes jusqu’à ce que l’oignon devienne transparent.\n" +
            "Etape: 5\n" +
            "Ajoutez les tomates concassées, le concentré de tomates, le paprika, le\n" +
            "paprika fumé, l’origan, Mélangez et faites mijoter 2 minutes.\n" +
            "Etape: 6\n" +
            "Ajoutez le poivre et les olives. Mélangez.\n" +
            "Etape: 7\n" +
            "Faites 4 trous dans la préparation pour y mettre les oeufs. Cassez 2 oeufs\n" +
            "dans chaque trou.\n" +
            "Etape: 8\n" +
            "Couvrez la poêle et faites cuire 4 minutes. Enlevez le couvercle et cuisez\n" +
            "encore 1 à 2 minutes selon la puissance de chauffe jusqu’à ce que le blanc\n" +
            "soit coagulé");

    ///////////////////////////////////////////////////////
    ajoutDeRecette("Baguette magique", R.drawable.baguette_magique, 0, "Pour 2 baguettes :\n" +
            "-375 g | 3 tasses de farine T55 (note 1)\n" +
            "-3 g | ½ cuillerée à café de sel\n" +
            "-5 g | 1,5 cuillerée à café de levure de boulangerie sèche (note 2)\n" +
            "-300 ml d’eau tiède\n" +
            "Pour la finition :\n" +
            "Graines de pavots, sésame, tournesol ou autres (pour saupoudrer sur les\n" +
            "baguettes), facultatif", "Versez la farine dans un grand saladier.\n" +
            "Ajoutez le sel, mélangez bien.\n" +
            "Ajoutez la levure sèche, mélangez.\n" +
            "Ajoutez l’eau tout en mélangeant avec une cuillère en silicone.\n" +
            "une pâte collante ! Ne vous en faites pas, c’est normal.\n" +
            "Filmez et laissez la pâte lever dans un endroit chaud pendant 1 à 2 heures\n" +
            "(jusqu’à ce que la pâte double de volume).\n" +
            "Vous pouvez aussi utiliser le four, surtout si vous faites ces baguettes en\n" +
            "hiver.\n" +
            "Allumez votre four à 35°C | 95°F, et laissez lever votre pâte dedans. Elle\n" +
            "doit doubler de volume.\n" +
            "Quelques minutes avant la fin de la levée, déposez un récipient sous la\n" +
            "grille dans le four.\n" +
            "Et préchauffez votre four à 250°C | 480°F.\n" +
            "Façonnage et cuisson des baguettes :\n" +
            "Farinez généreusement 2 empreintes à baguettes (moule à baguette), et\n" +
            "versez la moitié de la pâte dans chaque empreinte.\n" +
            "Façonnez chaque pâton, du mieux que vous pouvez, en utilisant une\n" +
            "spatule en silicone, en forme de baguette (comme la pâte est collante, la\n" +
            "forme ne sera pas jolie mais c’est pas important).\n" +
            "Éventuellement, saupoudrez le dessus des baguettes avec de la farine.\n" +
            "Ou parsemez le dessus avec des graines de pavot ou autres graines au\n" +
            "choix.\n" +
            "Lorsque le four est bien chaud, enfournez les baguettes et versez\n" +
            "immédiatement un peu d’eau dans le récipient sous la grille du four. Cela\n" +
            "permet de créer un coup de buée.\n" +
            "C’est ce qui va donner une belle croûte aux baguettes.\n" +
            "Faites cuire vos baguettes environ 17 minutes.\n" +
            "Laissez tièdir un peu sur une grille. Elles sont magnifiques encore chaudes\n" +
            "et croustillantes. Bon appétit !\n" +
            "Notes sur la recette\n" +
            "1. La farine :\n" +
            "La farine T55 est équivalente à la farine tout usage aux USA (All purpose\n" +
            "flour).\n" +
            "Si vous êtes aux Etats Unis, utilisez de préférence une farine tout usage\n" +
            "non blanchie (Unbleached All Purpose Flour).\n" +
            "Manue dit qu’elle a utilisée de la farine T55, car contrairement à la T45, elle\n" +
            "est panifiable, donc prévue pour faire du pain.\n" +
            "Vous trouverez la farine T55 dans certaines grandes surfaces, ou en\n" +
            "magasins bio.\n" +
            "2. La levure de boulanger :\n" +
            "Vous pouvez utiliser de la levure fraîche à la place de la levure sèche.\n" +
            "Personnellement, j’ai utilisé une levure sèche.\n" +
            "En principe, le poids de la levure fraîche équivalent est le double du poids\n" +
            "de la levure sèche.\n" +
            "Ici 5 g de levure sèche ou 10 g de levure fraîche.\n" +
            "3. Congélation des baguettes :\n" +
            "Ces baguettes se congèlent très bien (jusqu’à 3 mois).\n" +
            "Il suffit de les couper en deux et de les mettre dans un sac de congélation\n" +
            "que vous fermez hermétiquement (ou bien utilisez un papier aluminium).\n" +
            "Pour les utiliser, il suffit de les réchauffer au four à 150°C | 300°F pendant 8\n" +
            "minutes.");
    //////////////////////////////////////////////
    ajoutDeRecette("Boulette de surimi",R.drawable.boulettesdesurimi1,0
            ,"200 g de miettes de surimi saveur crabe\n" +
                    "12 portions de kiri\n" +
                    "2 cuillères à soupe de jus de citron\n" +
                    "6 brins de ciboulette (facultatif)\n" +
                    "Aneth (facultatif)","Ecrasez les portions de kiri à la fourchette,\n" +
                    "Ajoutez-y les miettes de surimi, le jus de citron et la ciboulette ciselée\n" +
                    "Mélangez afin d’obtenir une pâte.\n" +
                    "Réservez ensuite au réfrigérateur une heure.\n" +
                    "Façonnez des boulettes et piquez-les avec des cure-dents ou autres piques\n" +
                    "Parsemez d’aneth et remettez un peu au frais avant de servir.");
    /////////////////////
    ajoutDeRecette("Brioche de banane chocolaté",R.drawable.brioche_perdue_banne,0,"8 TRANCHES DE BRIOCHE UN PEU RASSISE\n" +
            "2 Oeufs\n" +
            "75G De beurre\n" +
            "30CL De lait\n" +
            "200G De chocolat noir\n" +
            "80G De sucres roux\n" +
            "1 C. À cafe DE cannelle en poudre","Préchauffez le four à th. 6 – 180 °C. Battez les œufs avec 50 g de sucre\n" +
            "roux et la cannelle en poudre.\n" +
            "Versez le lait en continuant de mélanger.\n" +
            "Trempez les tranches de brioche, recto verso dans le mélange, puis\n" +
            "saupoudrez l’une des deux faces de sucre.\n" +
            "Dans une grande poêle, faites fondre une partie du beurre. Lorsqu’il \n" +
            "mousse, posez une première série de tranches de brioche, côté sucré dans\n" +
            "la poêle.\n" +
            "Laissez dorer pour obtenir une légère caramélisation, saupoudrez de\n" +
            "nouveau de sucre et retournez les tranches.\n" +
            "Faites ainsi cuire toutes les tranches de brioche et réservez-les sur du\n" +
            "papier sulfurisé.\n" +
            "Cassez le chocolat en petits morceaux et faites-le fondre au bain-marie.\n" +
            "Répartissez-le ensuite généreusement sur la moitié des tranches dorées\n" +
            "et recouvrez d’une seconde tranche.\n" +
            "Enfournez pour 8 minutes les sandwichs de pain perdu au chocolat.\n" +
            "Servez dès la sortie du four.\n");
    ///////////////////
    ajoutDeRecette("Pain brioché",R.drawable.briochestylepainperdu,0,"8 tranches de pain brioché\n" +
            "10 cl de lait\n" +
            "80 g de sucre en poudre\n" +
            "3 oeufs\n" +
            "100 grammes de beurre","ÉTAPE 1\n" +
            "Faites chauffer le lait tout doucement dans une casserole, puis versez-le\n" +
            "dans une assiette creuse.\n" +
            "Ajoutez alors le sucre en poudre et le sucre vanillé.\n" +
            "ÉTAPE 2\n" +
            "Battez les oeufs en omelette dans un bol, puis versez-les dans une assiette\n" +
            "également.\n" +
            "ÉTAPE 3\n" +
            "Commencez à préparer les tranches de brioche : trempez-les d’abord dans\n" +
            "le lait sucré puis dans les oeufs battus.\n" +
            "ÉTAPE 4\n" +
            "Faites chauffer le beurre dans la poêle et faites-y dorer les tranches de\n" +
            "brioche quelques minutes de chaque côté.\n" +
            "ÉTAPE 5\n" +
            "Servez aussitôt.\nASTUCES\n" +
            "Le pain perdu brioché s’accompagne de mille choses : du sucre en poudre,\n" +
            "de la cassonade, du sucre glace, du sucre vanillé, du miel, du sirop\n" +
            "d’érable, de la confiture, mais aussi des fruits frais et de la crème anglaise\n" +
            "pour un super dessert, ou un caramel au beurre salé, du chocolat fondu, un\n" +
            "coulis de fruits…\n" +
            "Un seul mot d’ordre : faites-vous plaisir, c’est tellement bon pour le moral !\n" +
            "Et si vous voulez ajouter de l’arôme directement dans le lait, vous pouvez y\n" +
            "ajouter de la cannelle, de la fleur d’oranger, ou de l’extrait de vanille");
    ///////////////////
    ajoutDeRecette("Cheesecake aux fraises",R.drawable.cheesecake_aux_fraise,0,
            "185 g de petits-beurre\n" +
                    "80 g de beurre ramolli\n" +
                    "½ feuille de gélatine\n" +
                    "2 c. à s. d’eau chaude\n" +
                    "500 g de cream cheese à température ambiante\n" +
                    "400 g de lait concentré sucré\n" +
                    "300 ml de crème fraîche\n" +
                    "150 g de chocolat blanc fondu\n" +
                    "500 g de fraises coupées en deux ou en quatre\n" +
                    "80 g de confiture de fraises\n" +
                    "1 c. à s. de jus de citron","Beurrez un moule à charnière rond.\n" +
                    "Mixez les petits-beurre pour obtenir une chapelure fine, puis ajoutez le\n" +
                    "beurre,\n" +
                    "Travaillez le mélange à la main pour obtenir une pâte grumeleuse\n" +
                    "Étalez-la au fond du moule en tassant bien, couvrez et laissez raffermir 30\n" +
                    "minutes au réfrigérateur.\n" +
                    "Mettez la gélatine dans l’eau chaude, dans un petit récipient, puis faites-la\n" +
                    "fondre au bain-marie, au-dessus d’une petite casserole d’eau frémissante.\n" +
                    "Laissez refroidir 5 minutes.\n" +
                    "Fouettez le cream cheese et le lait concentré pour obtenir un mélange\n" +
                    "léger.\n" +
                    "Battez séparément la crème, jusqu’à ce que des petits pics se forment à la\n" +
                    "surface.\n" +
                    "Incorporez la gélatine dissoute au cream cheese battu, avant d’ajouter la\n" +
                    "crème et le chocolat blanc fondu\n" +
                    "Étalez ce mélange sur le fond de biscuits, couvrez et réfrigérez toute une\n" +
                    "nuit.\n" +
                    "Au moment de servir, démoulez le cheesecake, puis garnissez le dessus\n" +
                    "de quartiers de fraise.\n" +
                    "Faites tiédir la confiture et le jus de citron à feu doux, puis nappez-en le\n" +
                    "gâteau.\n" +
                    "Servez aussitôt");
    //////////////////
    ajoutDeRecette("Chinois map",R.drawable.chinois_map,0,"Pour la brioche :\n" +
            "100g de beurre pommade\n" +
            "300ml de lait\n" +
            "1 càc de sel\n" +
            "80g de sucre\n" +
            "500g de farine\n" +
            "1 cas de levure déshydratée.\n" +
            "Pour la crème pâtissière :\n" +
            "2 jaunes d’oeufs\n" +
            "50g de farine\n" +
            "1/2 litre de lait\n" +
            "3 cas de sucre\n" +
            "une gousse de vanille.\n" +
            "200 de raisin sec\n" +
            "Pour la dorure :\n" +
            "1 jaune d’oeuf\n" +
            "1 cas d’eau et une càc de sucre","Mettre dans la cuve de votre MAP tous les ingrédients dans l’ordre indiqué,\n" +
            "sélectionnez le programme “pâte”, c’est parti pour 1h30.\n" +
            "Pendant ce temps je prépare la crème pâtissière je fend la gousse de\n" +
            "vanille en 2 je gratte les graines les mets dans\n" +
            "une casserole avec le lait et je fais boullir le tout puis je fouette les jaunes\n" +
            "d’oeufs et le sucre puis une fois que le\n" +
            "mélange a blanchi, ajouter la farine puis j’ajoute petit à petit le lait chaud en\n" +
            "fouettant bien pour ne pas avoir de grumeaux.\n" +
            "Puis je transvase le tout dans la casserole que je remets sur feu doux et je\n" +
            "remu jusqu’à ce que le mélange épaississe, je réserve.\n" +
            "Une fois que ma brioche est pétrie, je la dispose sur mon plan de travail\n" +
            "fariné je la coupe en 4.\n" +
            "Je prend un quart que j’étale à l’aide d’un rouleau à pâtisserie, je\n" +
            "badigeonne de crème pâtissière je saupoudre de\n" +
            "raisins et je roule en boudin, je coupe ensuite des tronçons de 8 cm\n" +
            "environs (selon la hauteur de votre moule).\n" +
            "Puis je dispose chaque troçon dans un moule en silicone jusqu’à\n" +
            "épuisement de la pâte.\n" +
            "Je laisse pousser la pâte dans un endroit chaud et humide pendant 30 min.\n" +
            "Préchauffez votre four à 180°C, préparez votre dorure en mélangeant le\n" +
            "jaune d’oeuf, l’eau et le sucre, badigeonner\n" +
            "votre brioche du mélange, au pinceau et enfournez le chinois pour 30 à 40\n" +
            "min.\n");
    /////////////////
    ajoutDeRecette("Cheesecake au chocolat",R.drawable.chocolat_cheesecake,0,
            "900 g de philadelphia ou fromage frais\n" +
                    "250g de sucre en poudre\n" +
                    "1 c à c d’extrait de vanille\n" +
                    "4 oeufs\n" +
                    "200 g de chocolat noir\n" +
                    "Pour la base il faut:\n" +
                    "200 g de biscuits petits beurre, ou palet breton.\n" +
                    "2 cs de cacao\n" +
                    "150 g de beurre fondu.\n"," faut tout d’abord écraser les biscuits avec le cacao, tout en mélangeant\n" +
                    "au robot ajouter le beurre fondu.\n" +
                    "Mettre le mélange dans un moule de 22 cm de diamètre.\n" +
                    "Mettre au réfrigérateur le temps de préparer le reste de la recette.\n" +
                    "Mettre le fromage frais, le sucre et l’extrait de vanille dans un saladier et\n" +
                    "battre doucement jusqu’à obtenir un\n" +
                    "mélange épais et lisse. ajouter les oeufs un a un tout en mélangeant.\n" +
                    "Le mélange doit être crémeux. Mélanger plus rapidement a la fin, ne pas\n" +
                    "trop mixer ou le fromage peut cailler.\n" +
                    "Faire fondre le chocolat au bain marie.\n" +
                    "Y ajouter le mélange de fromage tout doucement a cause de la\n" +
                    "température.\n" +
                    "Retirer le biscuit du réfrigérateur et y ajouter le maligne a base de fromage\n" +
                    "Faire cuire le cheesecake au bain marie.\n" +
                    "Faire cuire a 150 degrés pendant 40-50 mn.\n" +
                    "Une fois cuit le laisser refroidir puis le mettre toute la nuit au frigo.\n" +
                    "Décorer avec une ganache au chocolat.");
    ////////////////
    ajoutDeRecette("Matefaim aux pommes",R.drawable.crepes_matefaimauxpommeset_au_caramel,0
            ,"• 3 œufs\n" +
                    "• 240 g de farine\n" +
                    "• 40 cl de lait\n" +
                    "• 20 g de beurre\n" +
                    "• 110 g de sucre semoule\n" +
                    "• 2 cuil. à soupe de rhum\n" +
                    "• 4 pommes\n" +
                    "• 5 cl de caramel liquide\n" +
                    "• 8 capsules de cardamome (facultatif)\n" +
                    "• 2 cuil. à soupe de sucre glace\n" +
                    "• 2 cuil. à soupe d’huile\n","1 Récupérez les graines de cardamome et écrasez-les finement.\n" +
                    "2 Faites tiédir le lait avec le beurre dans une casserole et ajoutez les\n" +
                    "épices. Laissez infuser 10 min. Réservez.\n" +
                    "3 Séparez le blanc des jaunes.\n" +
                    "4 Dans un saladier, mélangez la farine avec 60 g de sucre. Faites un puits\n" +
                    "et ajoutez les jaunes.\n" +
                    "Mélangez en incorporant peu à peu la farine puis versez le lait en plusieurs\n" +
                    "fois.\n" +
                    "Ajoutez le caramel, le rhum puis les blancs montés en neige ferme. Laissez\n" +
                    "reposer à température ambiante.\n" +
                    "5 Pendant ce temps, épluchez les pommes, ôtez le cœur et émincez-les en\n" +
                    "fines lamelles.\n" +
                    "Saupoudrez-les avec le sucre restant.\n" +
                    "6 Dans une poêle à crêpes antiadhésive bien chaude et légèrement huilée,\n" +
                    "versez une belle louche de pâte et disposez des lamelles de pommes.\n" +
                    "Laissez cuire 4/5 min puis retournez la crêpe.\n" +
                    "Poursuivez la cuisson 2 min. Poursuivez jusqu’à épuisement des\n" +
                    "ingrédients.\n" +
                    "7 Servez les crêpes bien chaudes saupoudrées de sucre glace.");
    ///////////////
    ajoutDeRecette("Crousti-crameux",R.drawable.crosti_cremeux,0,
            "1 pâte feuilletée maison ou du commerce\n" +
                    "2 blancs de poireaux moyens\n" +
                    "2 c. à soupe d’huile d’olive\n" +
                    "2 belles cuillères à soupe de mascarpone (ou à défaut de crème fraîche)\n" +
                    "2 pavés de saumon sans peau (environ 300 g)\n" +
                    "Fromage rapé\n" +
                    "1 jaune d’oeuf\n" +
                    "sel poivre\n","Préchauffer le four à 190 °C.\n" +
                    "Laver les blancs de poireaux et les couper en fines rondelles.\n" +
                    "Mettre les rondelles de poireaux dans le bol, et ajouter l’huile d’olive.\n" +
                    "Mettre le saumon dans le VAROMA (côté peau dessous), puis programmer\n" +
                    "10 minutes / Varoma / vitesse 1.\n" +
                    "Les poireaux doivent être fondants. Si ce n’est pas le cas, prolonger la\n" +
                    "cuisson d’une ou deux minutes.\n" +
                    "Ajouter le mascarpone aux poireaux Saler, poivrer, puis mélanger 30\n" +
                    "secondes / vitesse 1. Laisser tiédir.\n" +
                    "Dérouler la pâte feuilletée du commerce ou étaler celle faite maison en un\n" +
                    "disque d’environ 30 cm. La poser sur une plaque de cuisson garnie de\n" +
                    "papier sulfurisé.\n" +
                    "Au centre de la pâte, disposer le saumon (retirer la peau) coupé en\n" +
                    "morceaux en une bande d’une largeur de 10 cm maximum.\n" +
                    "Recouvrir de la fondue de poireaux, puis de fromage râpé.\n" +
                    "Couper des bandes de pâte de part et d’autre de la partie centrale où se\n" +
                    "trouvent le saumon et la fondue de poireaux.\n" +
                    "Rabattre les extrémités, puis les bandes une par une, en alternant les\n" +
                    "côtés, de manière à ce que les bandes se croisent.\n" +
                    "Dorer la surface du feuilleté avec l’oeuf battu, puis enfourner pour 30\n" +
                    "minutes.\n");
    //////////////////
    ajoutDeRecette("Escalopes de dingue",R.drawable.escalopes_dedindeausaintagur,0,"4,5 g de persil\n" +
            "90 g d’échalotes soit 4\n" +
            "35 g de beurre allégé ou entier\n" +
            "350 g d’escalopes de dinde soit 3\n" +
            "130 g d’eau tiède\n" +
            "90 g de crème fraîche entière liquide\n" +
            "0,5 g de piment d’espelette\n" +
            "65 g de fromage persillé Saint Agur","Éplucher les échalotes, couper en 2. Laver le persil, éponger dans un\n" +
            "papier absorbant.\n" +
            "Dans un petit robot hachoir, placer persil et échalotes, hacher.\n" +
            "Couper les escalopes en morceaux, dans la poêle, faire fondre le beurre et\n" +
            "dès qu’il mousse, ajouter les morceaux de dinde.\n" +
            "Cuire à feu vif, la viande pendant 5 min, mélanger régulièrement.\n" +
            "Dès coloration, ajouter les échalotes et persil, cuire pendant 2 min.\n" +
            "Détendre avec l’eau, la crème, le piment d’Espelette. Ajouter le fromage\n" +
            "saint-Agur coupé en morceaux.\n" +
            "Cuire à feu doux pendant 5 min, votre sauce doit napper la cuillère.\n" +
            "Goûter et rectifier l’assaisonnement.\n" +
            "Dresser la viande et parsemer de persil.\n" +
            "Servir chaud.");
    /////////////////////
    ajoutDeRecette("Galette bretonne",R.drawable.galette_bretonne,0
            ,"2 pommes\n" +
                    "2 oeufs\n" +
                    "2 cas de farine\n" +
                    "3 cas de sucre\n" +
                    "100 ml de lait\n" +
                    "20 g de beurre\n" +
                    "sucre vanillé","Eplucher, épépiner et couper en tranches les pommes.\n" +
                    "Les faire revenir dans une poêle avec du beurre pendant 10 minutes à feu\n" +
                    "doux.\n" +
                    "Battre les oeufs dans un bol puis ajouter successivement la farine, le sucre,\n" +
                    "le lait.\n" +
                    "Verser sur les pommes.\n" +
                    "Couvrir et laisser cuire pendant 10 minutes à feu doux.\n" +
                    "Retourner la galette à l’aide d’une assiette et cuire l’autre face pendant 5\n" +
                    "minutes.\n" +
                    "Saupoudrer de sucre vanillé. Déguster aussitôt.\n" +
                    "Astuces:\n" +
                    "il est possible de faire ce dessert avec des poires… voire des prunes…\n");
    ///////////////
    ajoutDeRecette("Oeufs farcis",R.drawable.oeuf_farci,0,"• 12 oeufs durs\n" +
            "• 1 petite boite de thon\n" +
            "• 1 petite barquette de salade piémontaise\n" +
            "• 1 petit sachet de carottes râpées\n" +
            "• 1 bocal de mayonnaise\n" +
            "• Quelques olives noires\n" +
            "• Une petite boite de poivrons rouges\n" +
            "• 1 petit oignon blanc","Quoi de plus joyeux et de plus économique qu’un plat d’oeufs farcis pour\n" +
            "une entrée de repas de Noël.\n" +
            "La recette facilissime par excellence qui plaira à toute la famille sans vous\n" +
            "ruiner, c’est celle-ci !\n" +
            "Modifiable à l’infini selon ce qu’on décide de mélanger, elle en surprendra\n" +
            "plus d’un.\n" +
            "Couper les oeufs en deux dans le sens de la longueur.\n" +
            "Retirer les jaunes et les mettre dans un bol pour les écraser avec une belle\n" +
            "cuiller de mayonnaise et l’oignon haché finement.\n" +
            "Ajouter à cette préparation les ingrédients de la liste selon ce qui vous\n" +
            "convient le mieux (vous pouvez en utiliser d’autres comme, par exemple, le\n" +
            "saumon fumé, les oeufs de lump, les petits dés de jambon, des légumes,\n" +
            "du fromage frais, etc.)\n" +
            "Remplissez donc généreusement les moitiés d’oeufs à votre goût.\n" +
            "Prendre grand soin à la présentation en intercalant les demi-oeufs suivant\n" +
            "la décoration choisie et les présenter sur un grand plat décoré ou non de\n" +
            "laitue, de tomates cherry ou d’herbes fraîches.\n" +
            "CONSEIL BONUS:\n" +
            "Avant de farcir les oeufs, couper une fine lamelle de blanc sous chaque\n" +
            "demi-oeuf. Cela leur permettra de rester bien calés et sans rouler lorsque\n" +
            "vous les poserez dans le plat\n");
    ////////////////////
    ajoutDeRecette("Pomme de terre farcies",R.drawable.pomme_deterrefarcielardons,0,
            "3 pommes de terre à chaire farineuse (bintje…) de taille moyenne\n" +
                    "2 à 3 blancs de poireaux (avec un peu de vert)\n" +
                    "+/- 120 g de jambon cuit en dés\n" +
                    "2 à 3 cuillères à soupe de crème épaisse\n" +
                    "un peu de fromage râpé\n" +
                    "beurre\n" +
                    "muscade\n" +
                    "sel et poivre noir","1 Nettoyez convenablement les pommes de terre si vous laissez la peau.\n" +
                    "Faites-les cuire à l’eau bouillante salée pendant 20 à 25 minutes ou plus si\n" +
                    "elles sont très grosses.\n" +
                    "Égouttez-les et laissez un peu refroidir.\n" +
                    "2 Lavez les blancs de poireaux et émincez-les.\n" +
                    "Faites-les fondre dans une grande poêle avec un peu de beurre pendant 10\n" +
                    "minutes à feu moyen.\n" +
                    "Salez légèrement, poivrez et muscadez…\n" +
                    "Ajoutez la crème épaisse, les dés de jambon, mélangez et laissez encore 2\n" +
                    "minutes sur feu doux.\n" +
                    "Pour finir\n" +
                    "Coupez les pommes de terre en 2 et évidez-les légèrement.\n" +
                    "Saupoudrez-les de fromage râpé, ajoutez la fondue de poireaux et terminez\n" +
                    "avec encore du fromage râpé…\n" +
                    "Enfournez à 200°C pendant quelques minutes, le temps que cela soit\n" +
                    "gratiné selon votre goût…\n" +
                    "Servez de suite");
    ///////////////////////
    ajoutDeRecette("Pomme de terre gratinées",R.drawable.pommesdeterregratineeesauparmesan,0,

            "8 assez grosses pommes de terre, épluchées, coupées en 2 dans le sens\n" +
                    "de la longueur\n" +
                    "1 gousse d’ail épluchée et pressée\n" +
                    "2 branche de thym\n" +
                    "2 branches de romarin\n" +
                    "20cl de bouillon de volaille (fait maison ou avec 1/4 de cube type Maggi)\n" +
                    "fleur de sel, poivre, piment d’espelette\n" +
                    "40g de parmesan\n","Dans une large sauteuse qui va au four, porter à ébullition le bouillon de\n" +
                    "volaille avec les herbes et\n" +
                    "l’ail.\n" +
                    "Déposer les demi pommes de terre les unes à côté des autres, face coupée\n" +
                    "vers le haut.\n" +
                    "Couvrir et laisser cuire pendant une trentaine de minutes.\n" +
                    "Vérier la hauteur du liquide et ajouter un peu d’eau si nécessaire (si vous\n" +
                    "utilisez AMC, utiliser la sauteuse de 3.5L et\n" +
                    "cuire selon les règles de cuisson sans eau. La quantité de bouillon est alors\n" +
                    "sufsante).\n" +
                    "Au terme de la cuisson, les pommes de terre doivent être fondantes. Les\n" +
                    "poivrer,répartir le\n" +
                    "parmesan.\n" +
                    "Faire gratiner au four préchauffé à 180°C une dizaine de minutes pour\n" +
                    "obtenir une belle\n" +
                    "coloration (si vous possédez l’atmosphéra, le mettre en position retournée,\n" +
                    "en activant la grande\n" +
                    "zone de cuisson, puissance 7, pendant 7-8 minutes).");
    ///////////////////////
    ajoutDeRecette("Quiche de saumon",R.drawable.quiche_tarama_saumon,0,

            "150 g de saumon fumé\n" +
                    "2 courgettes moyennes\n" +
                    "100 g de farine\n" +
                    "2 œufs\n" +
                    "400 g de lait écrémé\n" +
                    "100 g de crème fraîche à 4% de mg\n" +
                    "30 g de parmesan râpé\n","Lavez et râpez les courgettes puis faites-les revenir à la poêle jusqu’à ce\n" +
                    "qu’elles aient rendu toute leur eau.\n" +
                    "Ensuite coupez les tranches de saumon en morceaux.\n" +
                    "Dans un saladier battez les oeufs avec la crème et le lait puis ajoutez la\n" +
                    "farine, le sel et le poivre et mélangez.\n" +
                    "Tapissez un moule à tarte de papier sulfurisé puis disposez les courgettes\n" +
                    "et les morceaux de saumon et versez la pâte.\n" +
                    "Parsemez de fromage râpé puis enfournez 30 à 35 minutes");
    /////////////////////
    ajoutDeRecette("Salade aux lentilles",R.drawable.salade_aux_lentilles,0,
            "150g de lentilles vertes crues\n" +
                    "1 courgette (jaune ou verte)\n" +
                    "1 poivron\n" +
                    "2 tomates\n" +
                    "1 avocat\n" +
                    "le jus d’un demi citron\n" +
                    "quelques feuilles de basilic\n" +
                    "huile d’olive\n" +
                    "sel, poivre\n" +
                    "3 œufs","Faire cuire les lentilles dans une casserole d’eau froide, sans ajout de sel,\n" +
                    "pendant 20 à 30 minutes après l’ébullition. Bien les égoutter.\n" +
                    "Laver la courgette et le poivron et les couper en petits carrés, les faire\n" +
                    "revenir environ 15 minutes dans une poêle avec un filet d’huile d’olive.\n" +
                    "Pendant ce temps, laver les tomates et les couper en petits carrés ainsi que\n" +
                    "l’avocat qu’il faudra arroser d’un peu jus de citron afin d’éviter qu’il ne\n" +
                    "noircisse.\n" +
                    "Hacher finement les feuilles de basilic.\n" +
                    "Dans un saladier, mélanger les lentilles, courgette, poivron, tomate, avocat\n" +
                    "et basilic, puis assaisonner.\n" +
                    "Avant de servir déposer un œuf mollet ou dur coupé en deux sur chaque\n" +
                    "assiette si désiré.\n" +
                    "Pour l’œuf mollet:\n" +
                    "Plonger les œufs dans une casserole d’eau bouillante et cuire 5 minutes à\n" +
                    "partir de la reprise de l’ébullition.\n" +
                    "Plonger ensuite les œufs dans de l’eau très froide afin de stopper la\n" +
                    "cuisson et écaler délicatement les œufs");
    ///////////////////////
    ajoutDeRecette("Salade de tortellinis",R.drawable.salade_de_tortellinis,0,"De la vinaigrette italienne maison* ou du commerce\n" +
            "1 sac de 20 onces (environ 565 g) de tortellinis au fromage réfrigéré\n" +
            "1 concombre pelé, tranché et coupé en quartiers\n" +
            "1 paquet de tomates cerises coupées en deux\n" +
            "1 pot de 6 onces (170 g) d’olives noires égouttées et coupées en deux\n" +
            "1 poivron orange haché\n" +
            "2 cuillères à soupe (30 g) de pepperoncini haché\n" +
            "8 onces (225 g environ) de boulettes de mozzarella fraîches coupées en\n" +
            "quatre\n" +
            "8 onces (225 g environ) de salami en tranches hachées\n" +
            "Optionnel : fromage feta, parmesan, persil…\n" +
            "Ingrédients supplémentaires facultatifs :\n" +
            "Feta, zucchini, pepperoni, saucisse italienne, bacon croquant épais,\n" +
            "crevettes, poulet grillé ou haché\n","Faire cuire les pâtes selon les instructions du paquet, les égoutter et les\n" +
            "laisser refroidir.\n" +
            "Pendant que les pâtes cuisent, couper les légumes et le fromage.\n" +
            "Mélanger tous les ingrédients.\n" +
            "C’est tout!\n" +
            "Quelques conseils :\n" +
            "Il ne faut pas trop cuire les tortellinis, pour qu’ils ne se désagrègent pas.\n" +
            "Pour que vos pâtes cessent de cuire une fois retirées du feu, il faut les\n" +
            "rincer à l’eau froide et les égoutter.\n" +
            "Une fois les pâtes égouttées, ajoutez-les immédiatement dans un grand\n" +
            "bol, puis mélangez-les avec la moitié de la vinaigrette.\n" +
            "Ainsi, elles auront une saveur encore plus piquante et cela empêchera les\n" +
            "tortellinis de rester collés.\n" +
            "Si vous servez immédiatement, vous pouvez ajouter toutes vos friandises\n" +
            "hachées et la quantité désirée de vinaigrette restante.\n" +
            "Cependant, on recommande fortement de refroidir au moins 30 minutes.\n" +
            "Une salade fraîche a un goût bien supérieur à celui d’une température\n" +
            "ambiante, ce qui laisse un peu de temps pour que les saveurs se fondent.\n" +
            "Cette salade de pâtes italiennes est excellente le lendemain.\n" +
            "En la préparant, mélangez délicatement tous les ingrédients avec la moitié\n" +
            "de la vinaigrette, couvrez et réfrigérez.\n" +
            "Lors du service, ajoutez la quantité désirée de vinaigrette restante et\n" +
            "mélangez.\n" +
            "Couvrez les restes de salade de pâtes italiennes froides d’une pellicule\n" +
            "plastique ou les transférer dans un récipient hermétique.\n" +
            "Conservez au réfrigérateur pendant 2-3 jours.\n" +
            "Pour faire une vinaigrette maison :\n" +
            "Mélangez de l’huile d’olive, du vinaigre de vin rouge, du parmesan, du jus\n" +
            "de citron et des épices italiennes.\n" +
            "Bon appétit!\n");
    ////////////////////////
    ajoutDeRecette("Salade de macaroni",R.drawable.salade_de_macaroni,0,
            "1 tasse (250 ml) de macaroni\n" +
                    "1/2 tasse (125 ml) de mayonnaise\n" +
                    "1/2 tasse (125 ml) de yogourt Grec, sans gras\n" +
                    "1 cuillère à soupe et demie de vinaigre de vin blanc\n" +
                    "2 cuillères à thé de moutarde de Dijon\n" +
                    "2 cuillères à thé de miel\n" +
                    "Sel et poivre du moulin\n" +
                    "2 œufs à la coque, pelé et coupé en morceaux\n" +
                    "1 tasse (250 ml) de poivron vert coupé en dés\n" +
                    "1 tasse (250 ml) de carottes râpées\n" +
                    "3/4 de tasse (190 ml) de céleri coupé en dés\n" +
                    "1/4 de tasse (65 ml) d’oignon rouge coupé en dés\n" +
                    "1 cuillère à soupe de persil frais",
            "Faire cuire le macaroni dans de l’eau salée selon les instructions du\n" +
                    "paquet.\n" +
                    "Égouttez et rincez à l’eau froide.\n" +
                    "Dans un bol de taille moyenne, mélanger la mayonnaise, le yogourt grec, le\n" +
                    "vinaigre, la moutarde, le miel et assaisonner de sel et de poivre au goût.\n" +
                    "Ajouter les macaronis dans le bol ainsi que les œufs, les poivrons, carottes,\n" +
                    "céleri et oignons.\n" +
                    "Garnir de persil.\n" +
                    "Et voilà!");
    /////////////////
    ajoutDeRecette("Salade de riz",R.drawable.salade_derizcomplete,0,
            "120 g de riz blanc\n" +
                    "3 tomates\n" +
                    "2 carottes\n" +
                    "1 boite de petits pois\n" +
                    "1 boite de maïs\n" +
                    "2 saucisses de Francfort\n" +
                    "4 œufs\n" +
                    "8 olives noires\n" +
                    "10 brins de ciboulette\n" +
                    "2 c. à soupe de vinaigre balsamique\n" +
                    "2 c. à soupe d’huile\n" +
                    "1 c. à soupe de moutarde\n" +
                    "sel, poivre du moulin","ÉTAPE 1\n" +
                    "Faites cuire votre riz, en veillant à ce qu’il reste légèrement croquant.\n" +
                    "Égouttez-le et laissez-le refroidir puis mettez-le dans un grand saladier.\n" +
                    "ÉTAPE 2\n" +
                    "Lavez les carottes et coupez-les en julienne.\n" +
                    "Égouttez les petits pois et le maïs.\n" +
                    "Coupez les tomates en dés puis coupez les saucisses de Francfort en\n" +
                    "rondelles.\n" +
                    "Ajoutez le tout sur le riz et réservez. Mélangez le vinaigre avec le sel,\n" +
                    "ajoutez l’huile, la moutarde et le poivre.\n" +
                    "Mélangez bien le tout et versez la vinaigrette sur la salade.\n" +
                    "ÉTAPE 3\n" +
                    "Faites cuire les œufs dans de l’eau bouillante pendant une dizaine de\n" +
                    "minutes, jusqu’à ce qu’ils soient cuits durs.\n" +
                    "Écaillez-les et coupez-les en quartiers. Ajoutez-les sur le dessus de la\n" +
                    "salade.\n" +
                    "ÉTAPE 4\n" +
                    "Décorez votre salade, si vous le souhaitez, avec les olives noires et la\n" +
                    "ciboulette et mettez au frais quelques minutes.\n" +
                    "ASTUCES\n" +
                    "L’assaisonnement des salades composées peut varier selon les goûts et\n" +
                    "les envies…\n" +
                    "N’hésitez pas à adapter cette recette et à plutôt opter pour un\n" +
                    "assaisonnement à base de mayonnaise,\n" +
                    "ou encore pour une vinaigrette au jus de citron.\n");
    /////////////////////
    ajoutDeRecette("Succès aux noisette",R.drawable.succes_aux_noisette,0,
            "125 g de poudre d’amandes\n" +
                    "125 g de poudre de noisettes\n" +
                    "125 g de noisettes mondées\n" +
                    "8 blancs d’œufs\n" +
                    "500 g de sucre en poudre\n" +
                    "180 g de beurre mou\n" +
                    "sucre glace pour le décor","Préchauffez le four à 180° (th 6).\n" +
                    "Préparez le biscuit :\n" +
                    "battez 6 blancs d’œufs en neige ferme avec 20 g de sucre puis ajoutez 230\n" +
                    "g de sucre à la fin.\n" +
                    "Mélangez cette neige avec les poudres d’amandes et de noisettes en\n" +
                    "soulevant la préparation à la spatule.\n" +
                    "Mettez la pâte dans une poche à douille munie d’une douille lisse (1 cm de\n" +
                    "diamètre) et formez 2 disques égaux d’environ 25 cm de diamètre sur la\n" +
                    "plaque du four tapissée de papier sulfurisé.\n" +
                    "Enfournez pour 15 à 20 min, puis laissez refroidir.\n" +
                    "Concassez grossièrement les noisettes au couteau, puis faites-les griller à\n" +
                    "sec dans une poêle.\n" +
                    "Dans une petite casserole, préparez un caramel blond avec 120 g de sucre\n" +
                    "et 3 cuillerées à soupe d’eau.\n" +
                    "Ajoutez alors les noisettes, laissez cuire 1 à 2 min puis étalez les noisettes\n" +
                    "sur du papier sulfurisé et laissez-les refroidir.\n" +
                    "Réservez quelques morceaux de noisettes caramélisées, et mixez les\n" +
                    "autres.\n" +
                    "Préparez la crème : dans une casserole, faites bouillir 100 g de sucre avec\n" +
                    "4 cuillerées à soupe d’eau pendant 2 min.\n" +
                    "Montez 2 blancs d’œufs en neige en ajoutant le reste de sucre à la fin.\n" +
                    "Versez le sirop bouillant en mince filet sur les blancs sans cesser de\n" +
                    "fouetter jusqu’à ce qu’ils soient tièdes.\n" +
                    "Réservez.\n" +
                    "Fouettez le beurre mou avec 50 g de noisettes mixées, ajoutez la neige au\n" +
                    "sirop et fouettez à petite vitesse pendant 5 min pour que la crème soit\n" +
                    "légère et onctueuse.\n" +
                    "Posez un disque de biscuit sur un plat, étalez la crème, parsemez du reste\n" +
                    "de noisettes mixées, posez le second disque en appuyant un peu,\n" +
                    "saupoudrez de sucre glace et réservez 12 h au réfrigérateur.\n" +
                    "Au moment de servir, décorez des noisettes réservées.\n" +
                    "Conseils\n" +
                    "Pour réussir au mieux cette recette, utilisez des blancs d’œufs légèrement\n" +
                    "“vieillis”, c’est-à dire conservés au frais quelques jours dans une boîte\n" +
                    "hermétique. Sortez-les du frigo 1 h à l’avance afin qu’ils soient à\n" +
                    "température ambiante pour la recette.");
    //////////////////
    ajoutDeRecette("Tarte au thon",R.drawable.tarte_authoneta_la_tomate,0,
            "Une pâte feuilletée\n" +
                    "2 petites courgettes\n" +
                    "1 boite de thon au naturel de 190g\n" +
                    "1 grosse tomate\n" +
                    "1 belle échalotte\n" +
                    "3 Oeufs\n" +
                    "20 cl de crème\n" +
                    "20 g de comté rapé","Laver les courgettes et la tomate.\n" +
                    "Couper en fines rondelles les courgettes.\n" +
                    "Faire chauffer une casserole d’eau, lorsqu’elle boue blanchir pendant 1\n" +
                    "minute les rondelles de courgette.\n" +
                    "Évider les tomates et les couper en cubes. Émincer l’échalote. Réserver.\n" +
                    "Dans un bol, battre les œufs avec la crème, saler poivrer.\n" +
                    "Étaler la pâte dans un moule, déposer les courgettes, les tomates,\n" +
                    "l’échalote et émietter le thon.\n" +
                    "Verser par dessus le mélange œuf/crème. Saupoudrer de comté râpé.\n" +
                    "Faire cuire au four 30 minutes à 200°.");
    //////////////////
    ajoutDeRecette("Tarte aux poires",R.drawable.tarte_auxpoireset_aux_amandes,0, "1 pâte sucrée\n" +
            "50g de sucre\n" +
            "50g de poudre d’amandes\n" +
            "3 poires au sirop\n" +
            "1 œuf\n" +
            "1 gousse de vanille\n" +
            "Un peu de rhum\n" +
            "Un peu de maïzena ou de farine\n" +
            "Quelques amandes effilées\n" +
            "Nappage abricot\n" +
            "Sucre glace\n","ÉTAPE 1 :\n" +
            "Foncez votre cercle ou moule à tarte avec votre pâte sucrée.\n" +
            "ÉTAPE 2 :\n" +
            "Réalisez la crème d’amandes en mélangeant le beurre pommade, le sucre,\n" +
            "l’oeuf, la poudre d’amandes, la farine ou\n" +
            "fécule, le rhum (facultatif) et la gousse de vanille.\n" +
            "ÉTAPE 3 :\n" +
            "Versez la crème d’amandes sur le fond de votre tarte.\n" +
            "ÉTAPE 4 :\n" +
            "Épluchez et coupez en deux vos poires.\n" +
            "Emincez-les et déposez-les sur la crème d’amandes.\n" +
            "ÉTAPE 5 :\n" +
            "Rajoutez les amandes effilées sur la crème d’amandes et faites cuire 30 à\n" +
            "35 minutes à 180°C.\n" +
            "ÉTAPE 6 :\n" +
            "Laissez refroidir la tarte sur une grille et badigeonnez avec le nappage\n" +
            "abricot (ou la confiture) avec un pinceau\n");
    ////////////////////
    ajoutDeRecette("Tarte de noix de coco",R.drawable.tarte_noix_de_coco,0,
            "Rouleau de pâte brisée toute prête : 1\n" +
                    "Noix de coco râpée : 150 g\n" +
                    "Crème épaisse : 20 cl\n" +
                    "Sucre en poudre : 100 g\n" +
                    "Gros oeufs : 2\n" +
                    "Maïzena : 1 cuil. à café\n" +
                    "Rhum : 1 cuil. à soupe\n" +
                    "Vanille en poudre : 0,5 cuil. à café","Déroulez la pâte.\n" +
                    "Foncez-en un moule à tarte de 24 cm de diamètre.\n" +
                    "Piquez le fond à la fourchette. Faites-le cuire pendant 8 min à blanc dans le\n" +
                    "four préchauffé th. 7 (210 °C)\n" +
                    "Entre-temps, fouettez les œufs avec le sucre, la crème et la Maïzena.\n" +
                    "Incorporez 100 g de noix de coco râpée.\n" +
                    "Parfumez de rhum et vanille.\n" +
                    "Versez cette crème dans le fond de tarte précuit.\n" +
                    "Faites cuire 20 min dans le four sur th. 6 (180 °C).\n" +
                    "A mi-cuisson, parsemez du reste de noix de coco râpée\n" +
                    "Démoulez et servez encore tiède ou froid.\n");

    ///////////////////////////////
    ajoutDeRecette("Gateau de banane",R.drawable.gateau_banane,0,"3 bananes\n" +
            "250 g de farine\n" +
            "175 g de beurre\n" +
            "125 g de sucre\n" +
            "3 oeufs\n" +
            "1 paquet de levure chimique\n" +
            "1 bouchon de rhum\n" +
            "1 pincée de sel","1Travailler le beurre en pommade. Ajouter le sucre petit à petit ainsi que le sel. Travailler le jusqu'à ce qu'il soit onctueux.\n" +
            "2Incorporer les œufs entiers l'un après l'autre. Verser la farine d'un seul coup, puis ajouter les bananes écrasées avec le rhum.\n" +
            "3Ajouter la levure et mélanger intimement les ingrédients.\n" +
            "4Beurrer le moule. Verser la pâte.\n" +
            "Pour finir\n" +
            "Faire cuire dans le four à 210°C (thermostat 7) pendant 10 minutes, puis réduire la température à 150°C (thermostat 5) et laisser cuire pendant 35 minutes. Démouler à la sortie du four et laisser refroidir sur une grille.");
    //////////////////////////////
    ajoutDeRecette("Croustillant de pomme de terre",R.drawable.croustillantdepommesdeterre,0,
            "600 g de pommes de terre Charlotte\n" +
                    "1 cuillère à soupe d’huile\n" +
                    "150 g de Bresse Bleu\n" +
                    "1 pincée de sel\n" +
                    "1 pincée de muscade\n","1.Épluchez et râpez les pommes de terre.\n" +
                    "2.Salez et ajoutez la muscade, ensuite Dans une poêle huilée, disposez la moitié des pommes de terre râpées et laissez colorer sur feu doux jusqu’à obtention d’une couleur dorée\n" +
                    "3.Disposez au centre de la galette des fines tranches de Bresse Bleu.\n" +
                    "4.Disposez ensuite l’autre moitié de pommes de terre râpées sur l’ensemble, retournez le croustillant en le faisant glisser sur une assiette\n" +
                    "5.Cuire l’autre face jusqu’à obtenir une couleur dorée.\n" +
                    "6.Démoulez-le croustillant dans un plat à tarte et finir sa cuisson au four 10 minutes à 180°C (Thermostat 6) puis taillez en parts égales et servez\n");
    /////////////////////////////////
    ajoutDeRecette("Poulet au four et au citron",R.drawable.pouletaufouraucitron,0,"4 cuisses de poulet avec la peau\n" +
            "sel\n" +
            "poivre\n" +
            "paprika\n" +
            "le jus d’un gros citron\n" +
            "1 cuillère a soupe de moutarde\n" +
            "romarin frais\n" +
            "1 verre d’eau ou de bouillon de poulet\n","- Dans un bol, préparer un mélange avec la moutarde, le jus de citron, le romarin, le sel et le poivre et le paprika.\n" +
            "- Ajouter 1/2 verre d’eau ou de bouillon de poulet au mélange obtenu et le laisser mariner de 2 heures à 6 heures.\n" +
            "- Ajouter le reste d’eau/bouillon à votre marinade et la mettre avec les cuisses de poulet dans un moule a gratin.\n" +
            "- Couvrir de papier aluminium, et cuire au four à 180 degrés pendant 20 minutes.\n" +
            "- Retirer le papier aluminium au bout des 20 minutes et continuer la cuisson jusqu’à ce que la surface soit dorée.\n");

    /////////////////////////////////
    ajoutDeRecette("Boeuf bourguignon",R.drawable.boeufbourguignon,0,
            "- 1,2 kg de bœuf\n" +
                    "- 2 carottes\n" +
                    "- 250 g de champignons\n" +
                    "- 2 oignons\n" +
                    "- 3 c. à soupe de farine\n" +
                    "- 1,5 l de vin rouge\n" +
                    "- 2 c. à soupe d'huile d'olive\n" +
                    "- 100 g de lardons\n" +
                    "- 1 gousse d'ail\n" +
                    "- 1 bouquet garni\n" +
                    "- sel, poivre\n","Etape : 1\n" +
                    "Commencez par dégraisser la viande en retirant un maximum de gras au couteau et retaillez-la en morceaux de taille moyenne. Vous pouvez demander à votre boucher de réaliser cette étape pour vous.\n" +
                    "Etape : 2\n" +
                    "Faites ensuite chauffer 2 c. à soupe d'huile d'olive dans une grande cocotte et faites-y revenir la viande de boeuf. Il est impératif que la viande de votre bœuf bourguignon reste bleue à l'intérieur pour éviter une sur-cuisson et lui permettre de mijoter plus lentement. Cependant, toutes les faces de vos morceaux de boeuf doivent être légèrement colorées, d'où la nécessité de remuer de manière constante.\n" +
                    "Etape : 3\n" +
                    "Pendant ce temps, lavez et épluchez les carottes puis découpez-les en morceaux ou en rondelles selon la manière dont vous avez l'habitude de manger votre bœuf bourguignon. Une fois la viande correctement saisie, ajoutez-y les lardons et faites cuire 1 minute. Ajoutez ensuite les oignons ciselés et les carottes et laissez cuire 2 minutes. Enfin, ajoutez la farine par intermittence en prenant soin de bien remuer entre chaque cuillerée pour éviter la formation de grumeaux. Rajouter alors le vin, et le bouquet garni. Salez et poivrez le tout à votre convenance.\n" +
                    "Étape : 4\n" +
                    "Laissez mijoter dans une cocote minute le tout pendant environ 2h30 à 2h45 à feu doux en ajoutant les champignons au bout d'une heure et remuant régulièrement pour ne pas que la viande attache dans le fond de la cocotte. Enfin, servez votre bœuf bourguignon cuit accompagné de quelques pommes de terre ou de tagliatelles.\n");

//////////////////////////////////////////////
    ajoutDeRecette("Aubergine à la mozzarella",R.drawable.rouladesdauberginesalamozzarella,0,
            "- 2 grosses aubergines\n" +
                    "- 150 g de jambon de Parme en tranches fines\n" +
                    "- 200 g de mozzarella\n" +
                    "- 3 cuillères à soupe de parmesan fraîchement râpé\n" +
                    "- 250 g de pulpe de tomates\n" +
                    "- 1 cuillère à soupe d'huile d'olive\n" +
                    "- 10 cl d'huile\n" +
                    "- 2 pincées de sucre\n" +
                    "- sel et poivre\n","Etape : 1\n" +
                    "Coupez les aubergines en tranches d'1 demi-cm d'épaisseur, dans la longueur, sans les peler.\n" +
                    "\n" +
                    "Etape : 2\n" +
                    "Réservez seulement 9 tranches au centre des aubergines. Salez des deux côtés de chaque tranche et laissez dégorger 30 min.\n" +
                    "\n" +
                    "Etape : 3\n" +
                    "Pendant ce temps, coupez la mozzarella en 18 bâtonnets de 2,5 cm de long. Puis, rincez et épongez les aubergines.\n" +
                    "\n" +
                    "Etape : 4\n" +
                    "Faites dorer les aubergines de chaque côté pendant 3 à 4 minutes avec de l'huile d'arachide dans une poêle anti-adhésive, sur feu doux. Égouttez chaque tranche au fur et à mesure sur un papier absorbant.\n" +
                    "\n" +
                    "Etape : 5\n" +
                    "Allumez le four, à 250°C (thermostat 9).\n" +
                    "\n" +
                    "Etape : 6\n" +
                    "Mettre une tranche de jambon sur chaque tranche d'aubergine. Placer un bâtonnet de mozzarella à l'extrémité de chaque tranche. Roulez les tranches d'aubergines tapissées de jambon autour du fromage.\n" +
                    "\n" +
                    "Etape : 7\n" +
                    "Placez ces paupiettes dans un plat à four. Couvrez-les de pulpe de tomates et pour finir, saupoudrez de quelques pincées de parmesan.\n" +
                    "\n" +
                    "Etape : 8\n" +
                    "Laissez cuire au four 10 min.\n" +
                    "\n" +
                    "Etape : 9\n" +
                    "Servez les paupiettes toutes chaudes dans le plat de cuisson ou encore tièdes en entrée.\n");
    //////////////////////////////////

    //////////////////////////
    ajoutDeRecette("Aubergines gratinées",R.drawable.auberginegratine,0,"2 grosses aubergines\n" +
            "1 gros oignon\n" +
            "1 belle gousse d'ail\n" +
            "1 tomate\n" +
            "2 cs de persil haché\n" +
            "2 cs de chapelure maison\n" +
            "Origan, romarin\n" +
            "1 poignée d'olives noires à l'huile\n" +
            "1 œuf\n" +
            "1 mozzarelle\n" +
            "2 cs de parmesan râpé\n" +
            "Huile d'olive\n" +
            "Poivre \n","Entailler le pourtour des aubergines coupées en deux et quadriller la chair. Enfourner à 200° pendant 20 bonnes minutes. \n" +
            "Pendant ce temps faire revenir l'oignon émincé et l'ail coupé à l'huile. Ajouter la tomate mondée et détaillée en cubes, les herbes fraîches et sèches, les olives dénoyautées et coupées, bien poivrer mais ne pas saler à cause des olives. Retirer les aubergines du four, laisser tiédir et ôter la majeure partie de la chair des aubergines sans entamer la peau. \n" +
            "Ajouter la chair à la poêlée et laisser réduire. Incorporer hors du feu, la chapelure et l’œuf, bien mélanger puis garnir les aubergines rangées dans un plat à gratin. \n" +
            "Recouvrir de mozzarelle et poudrer de parmesan. Enfourner 30 min à 200° et servir avec une salade verte.\n");
    ////////////////////////
    ajoutDeRecette("Tian de légumes",R.drawable.tiandelegumes,0,"2 courgettes\n" +
            "5 tomates\n" +
            "5/6 pommes de terre moyennes\n" +
            "2 gousses d’ail\n" +
            "5 cuillères à soupe de Parmesan ou pourquoi pas des Toastinettes\n" +
            "1 filet d’huile d’olive\n" +
            "1 cuillère à soupe d’herbes de Provence\n" +
            "1 filet de crème fraîche\n" +
            "1 pincée de sel,\n" +
            "1 pincée de poivre\n","Coupez tous les légumes en rondelles. Dans un plat en terre (l’idéal), disposez les légumes en les alternant. Au milieu mettez les gousses d’ail émincées et des herbes de Provence.\n" +
            "Salez, poivrez, versez le filet de crème fraîche et l’huile d’olive. Saupoudrez de Parmesan.\n" +
            "Enfournez dans un four préchauffé à 180°C pendant environ 1 heure. Surveillez bien pour que le tian ne sèche pas. Ne pas hésitez à couvrir d’un papier aluminium en fin de cuisson\n" +
            "Vous pouvez décorer à la sortie du four avec quelques pluches de basilic.\n" +
            "Enjoy !\n");
    /////////////////////////////
    ajoutDeRecette("Légumes mijotés",R.drawable.pommedeterreavectomate,0,"400 à 500 g de petits pois surgelés (ou frais)\n" +
            "2 cuillerées à soupe d'huile d'olive\n" +
            "1 oignon finement haché ou coupé en lanières\n" +
            "2 gousses d'ail hachées\n" +
            "3 carottes\n" +
            "12 petites pommes de terre (ou 6 moyennes)\n" +
            "1 cuillerée à soupe de paprika doux\n" +
            "1 boîte moyenne de tomates pelées entières ou en dés, avec le jus\n" +
            "Environ 1 tasse | 250 ml d'eau ou de bouillon de légumes ou de poulet\n" +
            "1 bouquet garni (voir note 4)\n" +
            "Sel, poivre selon votre goût\n" +
            "Persil ou coriandre fraîche ciselée pour servir (facultatif)\n","Préparez vos ingrédients \n" +
            "Epluchez votre oignon et vos gousses d'ail. Coupez-les en lanières ou hachez-les.\n" +
            "Epluchez vos pommes de terre et vos carottes et coupez-les en morceaux.\n" +
            "Concassez vos tomates (moi je le fais avec les doigts, c'est facile, sinon vous pouvez les passer rapidement au blender ou mixer).\n" +
            "Les légumes en sauce tomate :\n" +
            "Dans une grande cocotte ou poêle, faites chauffer l'huile d'olive.\n" +
            "Ajoutez l'oignon, l'ail et les carottes et faites revenir le tout pendant quelques minutes.\n" +
            "Ajoutez les petits pois surgelés et les épices. Mélangez et laissez cuire 5 minutes.\n" +
            "Ajoutez les tomates concassées avec leur jus et les pommes de terre (voyez la note 2 ci-dessous). Ajoutez le bouquet garni (moi j'ai mis une feuille de laurier et du persil), et l'eau ou le bouillon (il faut que ça arrive à hauteur des légumes).\n" +
            "Salez, poivrez selon votre goût.\n" +
            "Portez à ébullition, mettez le couvercle, puis baissez le feu et laissez cuire à couvert sur feu doux, environ 25 minutes.\n" +
            "C'est prêt lorsque les légumes sont bien cuits et fondants (vérifiez avec la pointe d’un couteau).\n" +
            "En cours de cuisson, ajoutez de l'eau chaude si besoin. La sauce doit épaissir un peu. Si nécessaire, faites-la réduire à feu vif en fin de cuisson.\n" +
            "Servez vos légumes mijotés bien chauds, avec de la viande, du poulet, des oeufs au plat ou tout simplement avec du pain pour saucer dedans. Parsemez avec du persil ou de la coriandre hachée.\n" +
            "Bon appétit!\n");
    ////////////////////////
    ajoutDeRecette("Poulet à la moutarde",R.drawable.pouletmoutarde,0,"2 blancs de poulet\n" +
            "220 g de champignons\n" +
            "1 cube de bouillon de volaille\n" +
            "1/2 verre d’eau\n" +
            "3 échalotes\n" +
            "2 c.à.c de moutarde\n" +
            "2 c.à.c de crème fraîche\n" +
            "3 c.à.c d’estragon\n" +
            "2 c.à.c d’huile d’olive\n" +
            "sel\n" +
            "poivre\n","D’abord émincez vos échalotes, et faites diluer le cube de bouillon dans le verre d’eau\n" +
            "Ensuite faites revenir les échalotes émincés dans une poêle avec l’huile d’olive pendant 3 minutes, remuez sans les faire roussir.\n" +
            "Puis ajoutez les champignons et prolongez la cuisson pour 2 minutes de plus toujours en remuant.\n" +
            "Ajoutez en dessus votre bouillon de volaille puis laissez cuire 10 encore minutes.\n" +
            "Faites revenir les blancs de poulet dans une autre poêle antiadhésive, veillez à ce que les blancs de poulet soient dorés des deux cotés.\n" +
            "Ensuite ajoutez les à la préparation du champignons avec sel et poivre et laissez cuire pour 10 minutes.\n" +
            "Puis et après les 10 minutes, retirez le poulet et ajoutez la moutarde, crème fraiche et estragon et mélangez bien.\n" +
            "Ensuite mettez vos blancs de poulet dans une assiette et versez en dessus votre sauce, saupoudrez avec de persil haché.\n" +
            "Avec la recette du poulet moutarde champignons, faites votre plat et régalez vous bien … Bon appétit !\n");
    ///////////////////////////
    ajoutDeRecette("Blanquette de voeu",R.drawable.blaquettedevoeu,0,"1 kg d’épaule de veau\n" +
            "1 carotte\n" +
            "3 blancs de poireaux\n" +
            "1 oignon\n" +
            "3 échalotes\n" +
            "1 gousse d’ail\n" +
            "1 brin de céleri\n" +
            "400 g de champignons de Paris\n" +
            "3 jaunes d’œufs\n" +
            "1 citron\n" +
            "6 c. à soupe de crème fraîche\n" +
            "2 c. à soupe de farine\n" +
            "60 g de beurre\n" +
            "1 bouquet garni (persil, thym, laurier)\n" +
            "1 bouquet de persil\n","Peler la carotte, l’ail, les échalotes et l’oignon. Hacher ce dernier ainsi que les blancs de poireaux ; couper les échalotes et la carotte en deux.\n" +
            "Porter à ébullition 2 l d’eau dans un grand faitout, y plonger les morceaux de viande pendant environ 1 min pour les blanchir. Puis, égoutter la viande, rincer-la sous l’eau froide et jeter l’eau de cuisson.\n" +
            "Remettre la viande dans le faitout rincé, ajouter l’oignon et les poireaux hachés, les carottes, les échalotes, l’ail, le céleri et le bouquet garni.\n" +
            "Saler, poivrer puis ajouter de l’eau et couvrir.\n" +
            "Porter à ébullition et laisser cuire 1 h 30.\n" +
            "Cuire dans une poêle les champignons coupés et citronnés avec 20 g de beurre, environ 10 mn.\n" +
            "Préparer un roux blond : fondre le reste du beurre dans une casserole, saupoudrer-le avec la farine, mélanger vivement au fouet, puis laisser refroidir.\n" +
            "Quand la viande est cuite, la mettre dans une passoire avec les légumes et récupérer le bouillon de cuisson.\n" +
            "Délayer le roux avec ce bouillon et amener à ébullition en fouettant.\n" +
            "Remettre la viande et tous les légumes dans le faitout après avoir retiré le bouquet garni, l’ail, le céleri et la carotte.\n" +
            "Ajouter les champignons, verser la sauce et réchauffer le tout 10 à 15 min.\n" +
            "Juste avant de servir mélanger la crème et les jaunes d’œufs, les incorporer à la sauce en tournant sans laisser bouillir. Ajouter quelques gouttes de jus de citron.\n" +
            "Servir dans un plat creux avec du persil.\n");
    ///////////////////////////
    ajoutDeRecette("Pomme de terre suédoise",R.drawable.pommedeterresuedoise,0,"– 1 dizaine de pommes de terre longues pour bien pouvoir les trancher\n" +
            "– épices de votre choix : herbes de Provence, paprika doux, épices spéciale grillades.\n" +
            "– 1 ou 2 gousses d’ail écrasées ou 1 càc d’ail en poudre\n" +
            "– huile d’olive\n" +
            "– sel et poivre\n" +
            "– 1 poulet\n","Lavez les pommes de terre et bien les essuyer (ne pas les éplucher)\n" +
            "Entaillez-les à l’aide d’un couteau sans les trancher complètement\n" +
            "Déposez-les dans un plat allant au four\n" +
            "Salez et poivrez\n" +
            "Ajoutez les épices et l’ail\n" +
            "Arrosez d’huile d’olive\n" +
            "Cuire 1h au four entre 200°/210°\n" +
            "Vous pouvez également les cuire avec une viande ou un poulet comme ici\n" +
            "Épicez et salez la viande, ajoutez ail, épices poulet, sel et poivre et arroser d’huile d’olive\n" +
            "Cuire en même temps que les pommes de terre\n");
    /////////////////////////
    ajoutDeRecette("Poulet aux légumes",R.drawable.pouletauxlegumes,0,"– 4 poitrines de poulet (ou escalopes)\n" +
            "– 2 poivrons rouges\n" +
            "– 1 poivrons verts\n" +
            "– 1 poivron jaune\n" +
            "– 1 bouillon de volaille\n" +
            "– 12 petites pommes de terre (ou 4 grosses pommes de terre coupées en morceaux)\n" +
            "– 1 gros oignon (ou 2 oignons moyens)\n" +
            "– 4 gousses d’ail\n" +
            "– 1 cuillerée à café de piment d’espelette\n" +
            "– 2 feuilles de laurier\n" +
            "– 1 cuillerée à café d’origan\n" +
            "– 1 cuillerée à café de paprika\n" +
            "– Un peu d’huile d’olive\n" +
            "– Sel au goût\n" +
            "– 5 baies\n","Lavez et coupez vos poivrons en lanières après avoir levé les graines et le parois blanches. Emincez les oignons, écrasez les gousses d’ail.\n" +
            "Pelez les pommes de terre. Si elles sont grosses coupez-les en morceaux.\n" +
            "Le plat :\n" +
            "Dans une grande poêle avec ouvert, ou une cocotte, faites dorer rapidement vos poitrines de poulet dans un peu d’huile d’olive. Retirez-les et réservez-les.\n" +
            "A la place des poitrines de poulet, dans la même poêle, faites revenir les poivrons et les oignons.\n" +
            "Ajoutez l’ail et remettez le poulet. Ajoutez le bouillon de volaille émietté et 30 cl d’eau. Salez, ajoutez le piment d’espelette, le laurier, l’origan et le paprika.\n" +
            "Faites cuire 10 mn environ à feu doux après ébullition. Ajouter les pommes de terre et cuire 25 à 30 minutes.\n" +
            "Au moment de servir, dans l’assiette, saupoudrez de 5 baies.\n");
    ///////////////////////
    ajoutDeRecette("Poulet italienne",R.drawable.pouletalitaliennejpg,0,"4 blancs de poulet\n" +
            "400 g de champignons de Paris\n" +
            "200 g de tomates cerises100 g de chorizo\n" +
            "100 g d’olives noires\n" +
            "1 boîte de tomates concassées\n" +
            "(480 g) 20 cl de bouillon de légumes\n" +
            "10 cl de vin rouge\n" +
            "2 c. à soupe de mascarpone\n" +
            "2 c. à soupe de concentré de tomates\n" +
            "2 c. à soupe de farine\n" +
            "1,5 c. à soupe de paprika\n" +
            "5 brins de thym\n" +
            "3 brins de romarin huile d’olive sel et poivre\n","1. Retirez la peau du chorizo et détaillez-le en rondelles. Coupez le bout terreux des champignons. Nettoyez-les et émincez-les. Lavez les tomates cerises.\n" +
            "2. Coupez les blancs de poulet en 2. Saupoudrez-les de paprika, puis de farine. Faites-les dorer 5 mn dans une sauteuse avec 2 c. à soupe d’huile d’olive. Retirez-les et remplacez-les par les champignons et le chorizo. Laissez cuire 5 mn.\n" +
            "3. Ajoutez les tomates cerises, faites-les revenir quelques minutes. Versez le bouillon et le vin rouge. Portez à ébullition. Ajoutez les tomates concassées, les olives et le concentré de tomates. Remettez les morceaux de poulet. Salez et poivrez.\n" +
            "4. Lavez, effeuillez et hachez le thym et le romarin. Mélangez les herbes avec le mascarpone. Incorporez cette préparation dans la sauce tomate. Laissez cuire encore 30 mn. Servez le poulet avec de la polenta, par exemple.\n");
    /////////////////////////////
    ajoutDeRecette("Navarin",R.drawable.navarinjpeg,0,"1 kg de mouton dans la poitrine, l’épaule ou le collet\n" +
            "125 g d’oignons\n" +
            "200 g de carottes\n" +
            "150 g de navets\n" +
            "500 g de pommes de terre\n" +
            "50 g de beurre\n" +
            "30 g de farine\n" +
            "1 dl de vin blanc\n" +
            "1 dl de fond blanc\n" +
            "3 gousses d’ail\n" +
            "1 bouquet garni\n" +
            "3 c à s d’huile\n" +
            "Sel fin\n" +
            "Poivre du moulin\n","Épluchez les carottes, les navets et les pommes de terre.\n" +
            "Coupez les pommes de terre et navets en 4 et émincez les carottes.\n" +
            "Pelez et émincez les oignons.\n" +
            "Pelez et hachez l’ail. \n" +
            "Préparation du navarin de mouton \n" +
            "Dans une cocotte, faites revenir dans l’huile chaude les morceaux de mouton, salez et poivrez.\n" +
            "Retirez la viande et faites dorer les oignons et carottes émincés.\n" +
            "Retirez-les, ôtez le surplus de graisse et déglacez avec le vin blanc.\n" +
            "Remettez les morceaux de viande et les légumes, ajoutez le hachis d’ail et saupoudrez de farine.\n" +
            "Mélangez et mouillez à hauteur avec le fond blanc.\n" +
            "Ajoutez le bouquet garni et faites cuire à feu doux 1 heure.\n" +
            "Ajoutez les morceaux de pommes de terre et les navets, portez à ébullition, baissez le feu et faites cuire de nouveau\n" +
            "à feu doux 20-25 mn.\n" +
            "Servez chaud dans la cocotte ou dans un plat de service.\n");

    /////////////////////////
    ajoutDeRecette("Soupe de poulet aux torlennis",R.drawable.soupepouletautorlenni,0,"1 lb et demi de poitrines de poulet\n" +
            "3 carottes de taille moyenne coupées en dés\n" +
            "3 branches de céleri\n" +
            "1 oignon de taille moyenne coupé en dés\n" +
            "3 gousses d’ail\n" +
            "6 tasses (1500 ml) de bouillon de poulet\n" +
            "1 tasse (250 ml) d’eau\n" +
            "2 feuilles de laurier\n" +
            "1 cuillère à thé d’épices italiennes\n" +
            "2 tasses (500 ml) de tortellini au fromage (déjà cuit et gelé)\n" +
            "Persil frais\n" +
            "Sel et poivre au goût\n","Ajouter tous les ingrédients à la mijoteuse sauf les tortellinis.\n" +
            "Faites cuire à LOW pendant 6 heures.\n" +
            "Retirer le poulet de la mijoteuse. Effilocher ou couper en petits cubes. Remettre à la mijoteuse.\n" +
            "Ajoutez les tortellinis et faites cuire un autre 15 minutes. Juste assez pour les chauffer, car ils sont déjà cuits.\n" +
            "Retirer les feuilles de laurier. Assaisonné de sel et de poivre.\n" +
            "Servir avec du persil frais.\n");

    ///////////////////////////
    ajoutDeRecette("Purée de petit pois",R.drawable.pureedepetitpois,0,"1 kg de petits pois\n" +
            "150 g de pommes de terre\n" +
            "1 dl de crème fraîche\n" +
            "Sel\n" +
            "Poivre\n","Ecossez les petits pois.\n" +
            "Lavez et épluchez les pommes de terre, coupez-les en petits en petits cubes.\n" +
            "Faites cuire les petits pois dans une cocotte d'eau bouillante salée pendant 25 minutes, égouttez-les.\n" +
            "Faites cuire les dés de pommes de terre dans l'eau bouillante salée, égouttez-les.\n" +
            "Réduisez les petits pois et les pommes de terre en purée en ajoutant un peu de liquide de cuisson, la crème fraîche, sel, poivre.\n" +
            "Servir chaud\n");
    ////////////////////
    ajoutDeRecette("Poulet fermier farci",R.drawable.pouletfermierfarci,0,"1 poulet fermier label rouge, St-Sever pour moi\n" +
            "1 boite de châtaignes sous vide (720 ml / 420 g)\n" +
            "100 g de jambon de Bayonne\n" +
            "10 brins de persil\n" +
            "2 gousses d’ail\n" +
            "1 échalote\n" +
            "1 oeuf\n" +
            "50 g de pain rassis\n" +
            "125 ml de lait\n" +
            "10 cerneaux de noix\n" +
            "30 g de raisins secs\n" +
            "4 cuillères à soupe d’Armagnac\n" +
            "30 g de beurre\n","Coupez le pain rassis, mettez-le dans un bol et arrosez-le avec le lait. Versez le raisin sec dans un petit ramequin et arrosez-les d’Armagnac. Laissez-les gonfler au moins 15 minutes.\n" +
            "Lavez le persil, séchez-le. Epluchez les gousses d’ail et dégermez-les. Epluchez l’échalote et coupez la grossièrement.\n" +
            "Préparez la farce : Dans le bol du robot mettez le jambon de Bayonne, 6 châtaignes, les feuilles de persil, l’ail, l’échalote, l’oeuf, le pain qui a trempé dans le lait et mixez grossièrement. Il ne faut pas que cela soit une purée, il doit rester des morceaux.\n" +
            "Ajoutez les cerneaux de noix grossièrement concassées et les raisins secs égouttés. Mélangez.\n" +
            "Garnissez la cavité du poulet de cette farce. Personnellement je ne ferme pas ensuite avec du fil mais c’est à votre discrétion.\n" +
            "Enfournez et faites cuire 1H15 à 190°C en arrosant régulièrement. J’utilise la fonction circosteam de mon four vapeur qui combine chaleur tournante et ajout de vapeur.\n" +
            "Juste avant la fin de la cuisson du poulet, faites fondre le beurre dans un poêle. Faites-y colorer à feu vif les châtaignes restantes. Servez-les avec le poulet.\n" +
            "Accompagnez de pommes fruits cuites au four ou à la poêle. Succulent.\n");
    //////////////////////////
    ajoutDeRecette("Couscous maison",R.drawable.couscousmaison,0,"•\t2 kg de semoule à couscous\n" +
            "•\t2 verres d'huile d'olive\n" +
            "•\t1 verre d'huile d'argane\n" +
            "•\t1 petite boîte de concentré de tomates\n" +
            "•\t1 boîte de pois chiches\n" +
            "•\t2 kg de bœuf (tous les morceaux conviennent, mais préférer les os)\n" +
            "•\t3 oignons\n" +
            "•\t500 g de carottes\n" +
            "•\t500 g de courgettes\n" +
            "•\t600 g de potiron\n" +
            "•\t3 aubergines moyennes\n" +
            "•\t400 g de navets\n" +
            "•\t1 botte de coriandre\n" +
            "•\t3 branches de céleri\n" +
            "•\tQuelques branches de persil\n" +
            "•\t1/2 chou blanc\n" +
            "•\t3 piments verts\n" +
            "•\t1 poivron vert\n" +
            "•\t2 poivrons rouges\n" +
            "•\t200 g de fèves\n" +
            "•\tSel, piment doux, poivre, ras el Hannut (épice marocaine), cumin, safran\n","1.\tCoupez la viande en petits morceaux, la mettre dans la partie basse de la couscoussière, puis émincez les oignons.\n" +
            "2.\tAjoutez la coriandre et le persil hachés, puis assaisonnez avec tous les épices.\n" +
            "3.\tMélangez le tout à mains nues.\n" +
            "4.\tMettez sur le feu, laissez 15 mn à feu doux.\n" +
            "5.\tAjoutez un litre d'eau (préalablement bouillie), puis mettez dans l'eau les légumes.\n" +
            "6.\tDans l'ordre, mettez les carottes (les découper en quartiers), puis ajoutez les choux en gros morceaux, ainsi que les navets, les courgettes, le potiron et les poivrons, les aubergines (en quartiers également), le céleri en morceaux, l'intérieur des fèves ouvertes, ainsi que le concentré de tomates.\n" +
            "7.\tAjoutez les pois chiches et le piment.\n" +
            "8.\tEn fonction du goût, ajoutez du sel.\n" +
            "9.\tLaissez mijoter tout en préparant le couscous.\n" +
            "10.\tVersez le couscous dans un grand plat qu'on hydrate en aspergeant d'eau froide et massant de façon à ce que la semoule ne colle pas aux mains pendant 5 minutes.\n" +
            "11.\tIl ne faut pas beaucoup d'eau.\n" +
            "12.\tDisposez la semoule sans la verser dans la partie haute de la couscoussière\n" +
            "13.\tAttendez que la vapeur apparaisse dans la partie supérieure de la couscoussière, puis versez le couscous d'un coup dans un plat puis l'aérez avec une louche.\n" +
            "14.\tAspergez d'eau et massez une seconde fois comme la fois précédente pendant 5 mn, puis remettez dans la partie haute de la couscoussière.\n" +
            "15.\tLaissez jusqu'à ce que la vapeur remonte (environ 10mn) et répétez l'opération de verser et massez avec de l'eau, mais cette fois-ci l'eau est mélangée avec du sel.\n" +
            "16.\tRemettez dans la couscoussière.\n" +
            "17.\tLaissez revenir la vapeur.\n" +
            "18.\tReversez dans le plat, massez avec l’huile d'argane ou avec de l'huile d'olive.\n" +
            "19.\tC'est prêt !\n");
    /////////////////////
    ajoutDeRecette("Tarte aux pomme de terre",R.drawable.tarteauxpommesdeterre,0,"•\t5 pommes de terre\n" +
            "•\tCrème fraîche épaisse\n" +
            "•\t2 pâtes brisées\n" +
            "•\tSel\n" +
            "•\tPoivre\n","1.\tPréchauffez le four à 210°C (thermostat 7).\n" +
            "2.\tEpluchez et coupez en lamelles les pommes de terre.\n" +
            "3.\tMettez une pâte dans un moule à tarte, disposez les rondelles de pommes de terre dessus, salez.\n" +
            "4.\tRecouvrez de l'autre pâte et faites une cheminée au centre.\n" +
            "5.\tFaites cuire au four 40 minutes à 210°C.\n" +
            "6.\tSortez du four, découpez le dessus, garnissez de crème fraiche, puis recouvrez et remettez au four 5 min.\n");
    //////////////////////////////
    ajoutDeRecette("Purée",R.drawable.pureealacienne,0,"•\t1 kg de pommes de terre\n" +
            "•\t25 cl de lait\n" +
            "•\t100 gr de beurre\n" +
            "•\t1 jaune d'œuf\n" +
            "•\tNoix de muscade\n" +
            "•\tSel, poivre\n","1.\t  Épluchez les pommes de terre, coupez-les en morceaux et faites-les cuire à l'eau 30 minutes. Égouttez-les en gardant un peu de jus de cuisson.\n" +
            "2.\t  Écrasez-les avec une presse purée et ajoutez le lait. Si la purée est trop dure, ajoutez du jus de cuisson.\n" +
            "3.\t  Incorporez le beurre et assaisonnez avec le sel, le poivre et la muscade. Pour finir, incorporez rapidement le jaune d'œuf.\n");
    ///////////////////////////////
    ajoutDeRecette("Millefeuille aux pomme de terre",R.drawable.millefeuilleauxpomme,0,"8 grosses pommes de terre\n" +
            "12 tranches de lard fumé, ou jambon fumé ou bacon fumé suivant goût\n" +
            "120 g de comté\n" +
            "4 Cuillère à soupe d’huile d’olive\n" +
            "20 cl de bouillon de volaille\n" +
            "1 gousse d’ail\n" +
            "2 branches de romarin\n" +
            "sel, poivre\n","Lavez les pommes de terre, pelez-les en conservant la peau sur une des faces. Emincez-les en fines rondelles sans jamais les détacher complètement de la base côté peau\n" +
            "Huilez la cuve et frottez-la avec la gousse d’ail coupée en 2\n" +
            "Chauffez le bouillon de volaille dans une casserole ou au micro-ondes\n" +
            "Disposez les pommes de terre dans la cuve après avoir mis en alternant entre les rondelles, les tranches de lard et le comté\n" +
            "Salez, poivrez et arrosez avec le bouillon\n" +
            "Parsemez de romarin, et cuire sous pression minimum 20 mn suivant la grosseur des pommes de terre\n" +
            "Faîtes dorer 10 mn au four\n" +
            "Servir chaud avec salade verte\n" +
            "Nombre de couverts 4 personnes\n" +
            "Prêt en : 1 Minutes\n");
    //////////////////////////////////
    ajoutDeRecette("Couscous royal marocain",R.drawable.couscousroyalmarocain,0,"1 kg de semoule à couscous calibre moyen\n" +
            "1 kg d’épaule d’agneau\n" +
            "1 kg de pilons de poulet\n" +
            "1 kg de merguez\n" +
            "150 g pois chiches\n" +
            "500 g de navets\n" +
            "500 g de carottes\n" +
            "500 g de courgettes\n" +
            "2 oignons\n" +
            "1 tomate râpée\n" +
            "1 petite cuillère à café de safran\n" +
            "1 cuillère à café de piment doux\n" +
            "1 bouquet de coriandre\n" +
            "200 g de beurre\n" +
            "sel et poivre\n","Découper la viande d’agneau en gros morceaux.\n" +
            "Les mettre dans la marmite à couscous. Couvrir de 5 litres d’eau froide.\n" +
            "Ajouter les oignons coupés en morceaux, le safran, le piment doux, le sel, le poivre et les pois chiches mis à tremper depuis la veille.\n" +
            "Porter à ébullition et laisser cuire à petits bouillons pendant une heure.\n" +
            "Mettre la semoule à couscous dans un saladier et verser dessus un peu d’eau tiède salée. Mélanger et détacher les grains avec une fourchette.\n" +
            "Mettre la semoule dans le panier du couscoussier et le poser sur la marmite. Dès que la vapeur traverse la semoule, la remettre dans le saladier.\n" +
            "Laver les navets et les couper en quartiers.\n" +
            "Laver et gratter les carottes et les couper en tronçons.\n" +
            "Laver les courgettes et les couper en rondelles de 3 cm d’épaisseur.\n" +
            "Au bout d’une heure de cuisson du bouillon, ôtez la viande d’agneau et mettez là de côté, ajouter les pilons de poulet dans la sauce du poulet, les navets et les carottes. Laisser cuire pendant 20 minutes puis ajouter les courgettes, la tomate râpée et la coriandre hachée.\n" +
            "Mettre la semoule dans le panier du couscoussier.\n" +
            "Laisser cuire pendant 20 minutes.\n" +
            "Faites griller les merguez au four ou à la poêle.\n" +
            "Quand la semoule est cuite, la mettre dans un plat. Y ajouter du beurre coupé en morceau.\n" +
            "Servir le Couscous Royal Marocain ainsi :\n" +
            "Creuser un puits au centre de la semoule et y disposer les légumes et les viandes.\n" +
            "Présenter le bouillon à part dans une soupière.\n");
    ///////////////////////
    ajoutDeRecette("Ragout de poulet",R.drawable.ragoutdepouletalancienne,0,"4 filets de poulet\n" +
            "400g de champignon de paris\n" +
            "1 oignon\n" +
            "150g de lardons\n" +
            "3 brins de persils\n" +
            "200ml de vin blanc sec\n" +
            "150ml de crème liquide\n" +
            "1 cuil à soupe d’huile d’olive\n" +
            "Fleur de sel, poivre\n","Pelez et émincez l’oignon, lavez et coupez les champignons en morceaux. Lavez et ciselez le persil.\n" +
            "Dans une cocotte ou une poêle faites dorer les filets de poulets 2 minutes de chaque coté à feu vif, puis baissez le feu et finissez de les faire cuire 10 minutes en les retournant au bout de 5 minutes. Réservez.\n" +
            "Faites revenir l’oignon et les lardons dans la poêle, ajoutez les champignons au bout de 5 minutes de cuisson et poursuivez encore 5 à 10 minutes à feu doux. Réservez avec les filets de poulets.\n" +
            "Déglacez la poêle avec le vin blanc en grattant le fond avec une spatule en bois. Versez la crème salez, poivrez et mélangez pour bien homogénéisez la sauce.\n" +
            "Remettez les filets de poulet et la garniture dans la poêle, mélangez et laissez quelques minutes sur le feu. Parsemez de persil et servez\n");
    ///////////////////////////}

}}