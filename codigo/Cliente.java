package POO.trabalhoemgrupo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;

public class Cliente {
    // início dos atributos
    private String idCliente;
    private String nomeUsuario;
    public static Cliente clienteLogado;
    private String senha;
    private String nomeCompleto;
    private List<Midia> listaFutura;
    private List<Midia> listaJaViu;
    public NivelCliente nivel;
    public HashMap<Integer, Integer> listaNotas;
    // fim dos atributos

    /**
     * construtor cliente com nome de usuário, senha de id do cliente
     * 
     * @param nomeUsuario
     * @param senha
     * @param idCliente
     */
    public Cliente(String nomeUsuario, String senha, String idCliente) {
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
        this.listaFutura = new ArrayList<>();
        this.listaJaViu = new ArrayList<>();
        this.idCliente = idCliente;

    }

    /**
     * construtor com idcliente e lista de notas, para registrar na lista de notas a
     * nota e o id do cliente
     * 
     * @param idCliente
     */
    public Cliente(String idCliente) {
        this.idCliente = idCliente;
        this.listaNotas = new HashMap<>();
    }

    /**
     * construtor cliente com o nome de usuario e id do cliente, para validação
     * 
     * @param nomeUsuario
     * @param idCliente
     */
    public Cliente(String nomeUsuario, String idCliente) {
        this.nomeUsuario = nomeUsuario;
        this.idCliente = idCliente;
    }

    /** construtor vazio cliente */
    public Cliente() {
    }

    /**
     * adiciona mídia á lista futura
     * 
     * @param midia
     */
    public void adicionarMidia(Midia midia) {
        listaFutura.add(midia);
    }

    /**
     * remove uma série da lista de séries para assistir do cliente.
     * 
     * @param serie a série a ser removida da lista.
     */
    public void retirarDaLista(Midia midia) {
        this.listaFutura.remove(midia);
    }

    /**
     * get para pegar o nome de usuário
     * 
     * @return nome de usuário
     */
    public String getNomeUsuario() {
        return nomeUsuario;
    }

    /**
     * get para pegar o id do cliente
     * 
     * @return id do cliente
     */
    public String getIdCliente() {
        return idCliente;
    }

    /**
     * coloca a mídia avaliada
     * 
     * @param idMidia id da midia no arquivo csv
     * @param nota    nota colocada pelo cliente
     */
    public void avaliarMidia(int idMidia, int nota) {
        listaNotas.put(idMidia, nota);
    }

    /**
     * registra a visualização, colocando na lista que já viu e retirando da lista
     * futura
     * 
     * @param midia objeto que possui a midia
     */
    public void registrarVisualizacao(Midia midia) {
        midia.registrarVisualizacao();
        listaFutura.remove(midia);
        listaJaViu.add(midia);
    }

    // toString para formatar informações do usuário
    @Override
    public String toString() {
        return "Cliente [nomeUsuario=" + nomeUsuario + ", listaFutura=" + listaFutura + ", listaJaViu=" + listaJaViu
                + "]";
    }

    /**
     * get para pegar a senha
     * 
     * @return senha
     */
    public String getSenha() {
        return this.senha;
    }

    /**
     * get para pegar a lista futura
     * 
     * @return lista futura
     */
    public List<Midia> getListaFutura() {
        return this.listaFutura;
    }

    /**
     * get para pegar a lista de midias que ja viu
     * 
     * @return lista do que ja viu
     */
    public List<Midia> getListaJaViu() {
        return this.listaJaViu;
    }

    /**
     * método ainda não implementado para registrar audiência
     * 
     * @param midia objeto da midia
     */
    public void registrarAudiencia(Midia midia) {
        // Abre printWriter do AUDIENCIA_CSV
        // println(cliente.login;A/F;midia.id)
    }

    /**
     * get para ver o tamanho da lista de mídias que já viu
     * 
     * @return tamanho da lista
     */
    public int getTamanhoListaJaViu() {
        return listaJaViu.size();
    }

    /**
     * get para conseguir os dados do usuário
     * 
     * @return os dados, formatados igualmente no arquivo
     */
    public String getDados() {
        String login = getNomeUsuario();
        String senha = getSenha();
        String nome = getNomeCompleto();
        return ("\n" + nome + ";" + login + ";" + senha);
    }

    /**
     * get para o nome completo
     * 
     * @return nome completo
     */
    private String getNomeCompleto() {
        return nomeCompleto;
    }

    /**
     * formatar os dados do cliente em formato CSV
     * 
     * @return dados formatados
     */
    public String Formatador() {
        // Formatar os dados do cliente em formato CSV
        return String.format("%s;%s;%s", nomeCompleto, nomeUsuario, senha);
    }

    /**
     * get id do cliente logado no menu
     * 
     * @return cliente logado no menu
     */
    public static Object getClienteLogado() {
        return clienteLogado;
    }

    /**
     * get para lista de notas
     * 
     * @return lista de notas do cliente
     */
    public HashMap<Integer, Integer> getListaNotas() {
        return listaNotas;
    }

}
