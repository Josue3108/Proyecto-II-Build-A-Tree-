package Server;

public class SendToken {
    public String tipo;
    public String arbol;
    public String valor;

    public SendToken(String arbol, String valor){
        this.tipo = "token";
        this.arbol = arbol;
        this.valor = valor;
    }
}
