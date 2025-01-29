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
    public static boolean isRoutable(ROUTES route) {
        for (ROUTES routing : values()) {
            if (routing == route) {
                return true;
            }
        }
        return false;
    }
    public static boolean forVisiteur(ROUTES route) {
        return route == CONTROLLEUR
                || route == ACCUEIL
                || route == INSCRIPTION
                || route == CONNEXION
                || route == DECONNEXION
                || route == LIVRES
                || route == LIVRE;
    }
    public static boolean forClient(ROUTES route) {
        return forVisiteur(route) || route == ACCUEIL;
    }
    public static boolean forLibraire(ROUTES route) {
        return forVisiteur(route)
                || forClient(route)
                || route == LIBRAIRE;
    }
    public static boolean forAdministrateur(ROUTES route) {
        return forVisiteur(route)
                || forClient(route)
                || forLibraire(route)
                || route == ADMINISTRATEUR;
    }
    public static  boolean isStaticFile(String path) {
        return path.endsWith(".css") || path.endsWith(".js") || path.endsWith(".jpg") || path.endsWith(".jpeg")
                || path.endsWith(".png") || path.endsWith(".svg") || path.endsWith(".ico");
    }
}