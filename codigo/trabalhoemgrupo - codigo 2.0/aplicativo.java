package POO.trabalhoemgrupo;
import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class aplicativo {
    //início dos atributos
    private List<Serie> listaSistema;
    private String email;
    private String senha;
    //fim dos atributos
    /**
     * construtor aplicativo
     */
    public aplicativo() {
        this.listaSistema = new ArrayList<>();
        this.email = "";
        this.senha = "";
    }
    /**
     * inicializador para cadastrar via email e senha
     * @param email
     * @param senha
     */
    public void cadastrar(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }
    /**
     * método para logar utilizando email e senha
     * @param email
     * @param senha
     */
    public void logar(String email, String senha) {
        if (this.email.equals(email) && this.senha.equals(senha)) {
            System.out.println("Login efetuado com sucesso!");
        } else {
            System.out.println("Email ou senha incorretos.");
        }
    }
    /**
     * método para adicionar série ao catálogo
     * @param serie
     */
    public void adicionarSerie(Serie serie) {
        listaSistema.add(serie);
    }
    /**
     *  método que lê o arquivo que disponibiliza os espectadores
     * @param caminhoArquivo
     * @return espectadores
     * @throws IOException
     */
    public static List<Cliente> lerEspectadores() throws IOException {
        List<Cliente> espectadores = new ArrayList<>();
        BufferedReader leitor = new BufferedReader(new FileReader("C:/trabalhoemgrupo/POO_Series.csv"));
        String linha = leitor.readLine(); // lê a primeira linha (cabeçalho)
        while ((linha = leitor.readLine()) != null) {
            String[] campos = linha.split(";");
            String nome = campos[0];
            String login = campos[1];
            String senha = campos[2];
            espectadores.add(new Cliente(nome));
        }
        leitor.close();
        return espectadores;
    }
    /**
     * método que lê o arquivo que disponibiliza a audiência
     * @param caminhoArquivo
     * @return audiência
     * @throws IOException
     */
    public static List<Serie> lerAudiencia(String caminhoArquivo) throws IOException {
        List<Serie> audiencia = new ArrayList<>();
        BufferedReader leitor = new BufferedReader(new FileReader("C:/trabalhoemgrupo/POO_Audiencia.csv"));
        String linha = leitor.readLine(); // lê a primeira linha (cabeçalho)
        while ((linha = leitor.readLine()) != null) {
            String[] campos = linha.split(";");
            String login = campos[0];
            char fa = campos[1].charAt(0);
            int idSerie = Integer.parseInt(campos[2]);
            audiencia.add(new Serie(login, fa, idSerie));
        }
        leitor.close();
        return audiencia;
    }
    
    

    // outros métodos para manipular a lista de séries, se necessário
}