package naim.test.beta.recette;

public class Model {
    int image,favori;
    String titre,ingredent,preparation;

    public Model( String titre,int image,int favori, String ingredent, String preparation) {
        this.image = image;
        this.titre = titre;
        this.ingredent = ingredent;
        this.preparation = preparation;
        this.favori=favori;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getFavori() {
        return favori;
    }

    public void setFavori(int favori) {
        this.favori = favori;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getIngredent() {
        return ingredent;
    }

    public void setIngredent(String ingredent) {
        this.ingredent = ingredent;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }
}
