package apostropheivre;
public enum ROUTES {
    CONTROLLEUR("/"),
    ACCUEIL("/accueil"),
    INSCRIPTION("/inscription"),
    CONNEXION("/connexion"),
    DECONNEXION("/deconnexion"),
    ADMINISTRATEUR("/administrateur"),
    LIBRAIRE("/libraire"),
    CLIENT("/client"),
    AUTEUR("/auteur"),
    AUTEURS("/auteurs"),
    LIVRE("/livre"),
    LIVRES("/livres");
    private final String path;
    public String getPath() {
        return path;
    }
    public static ROUTES getRoute(String path) {
        for (ROUTES route : values()) {
            if (route.getPath().equals(path)) {
                return route;
            }
        }
        return null;
    }
    ROUTES(String path) {
        this.path = path;
    }
    public static boolean isRoutable(String path) {
        for (ROUTES route : values()) {
            if (route.getPath().equals(path)) {
                return true;
            }
        }
        return false;
    }
}