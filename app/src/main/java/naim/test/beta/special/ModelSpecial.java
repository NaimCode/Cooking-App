package naim.test.beta.special;

import java.util.ArrayList;

public class ModelSpecial {
    String Titre,Ingredient,Preparation;
    int Image;
    ArrayList<Integer> ImageS;
    int Video;

    public ModelSpecial(String titre,int image, int videoS, ArrayList<Integer> imageS,String ingredient, String preparation) {
        Titre = titre;
        Ingredient = ingredient;
        Preparation = preparation;
        Image = image;
        ImageS = imageS;
        Video = videoS;
    }

    public String getTitre() {
        return Titre;
    }

    public void setTitre(String titre) {
        Titre = titre;
    }

    public String getIngredient() {
        return Ingredient;
    }

    public void setIngredient(String ingredient) {
        Ingredient = ingredient;
    }

    public String getPreparation() {
        return Preparation;
    }

    public void setPreparation(String preparation) {
        Preparation = preparation;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public ArrayList<Integer> getImageS() {
        return ImageS;
    }

    public void setImageS(ArrayList<Integer> imageS) {
        ImageS = imageS;
    }

    public int getVideoS() {
        return Video;
    }

    public void setVideoS(int videoS) {
        Video = videoS;
    }
}
