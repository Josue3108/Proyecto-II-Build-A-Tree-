package Server;

public class StartChallengeMessage {
    public String tipo;
    public String arbol;

    public StartChallengeMessage(String arbol){
        this.tipo = "start challenge";
        this.arbol = arbol;
    }
}
