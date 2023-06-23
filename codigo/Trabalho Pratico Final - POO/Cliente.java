package POO.trabalhopraticoPOO;

import java.util.List;
import java.util.ArrayList;

public class Cliente implements ConversorParaString{

/**
 * A classe Cliente representa um cliente do sistema.
 * Cada cliente possui um login, nome, senha, e pode realizar avaliações, visualizar mídias, entre outras ações.
 */
private String login;
	private String nome;
	private String senha;
	private int visualizacoes = 0;
	private ArrayList<Midia> midiasQueAvaliei = new ArrayList<Midia>();
	private List<Midia> listaJaViu = new ArrayList<Midia>(50);
	private List<Midia> listaFutura = new ArrayList<Midia>(50);
	private int quantAvaliacoesMes;

/**
 * Construtor da classe Cliente
 * @param nome  O nome do cliente
 * @param login O login do cliente
 * @param senha A senha do cliente
 */
public Cliente(String nome, String login, String senha) {
		this.nome = nome;
		this.login = login;
		this.senha = senha;
}

/**
 * Retorna a quantidade de avaliações feitas pelo cliente no mês
 * @return A quantidade de avaliações no mês
 */
public int getQuantidadeAvaliacoes() {
		return quantAvaliacoesMes;
}





/**
 * Construtor vazio da classe Cliente
 */
public Cliente() {}

/**
 * Verifica se o cliente já avaliou uma determinada mídia
 * @param midia A mídia a ser verificada
 * @return true se o cliente já avaliou a mídia, false caso contrário
 */
public boolean verificaSeAvaliou(Midia midia) {
		for (Midia m : midiasQueAvaliei) {
			return m.getChave().equals(midia.getChave()) ? true : false;
		}
		return false;
}

/**
 * Adiciona uma mídia à lista de mídias que o cliente avaliou
 * @param midia A mídia a ser adicionada
 */
public void addAvaliadas(Midia midia) {
		this.midiasQueAvaliei.add(midia);
}

/**
 * Retorna o login do cliente
 * @return O login do cliente
 */public String getLogin() {
		return login;
}

/**
 * Define o login do cliente
 * @param login O novo login do cliente
 */
public void setLogin(String login) {
		this.login = login;
}

/**
 * Retorna a senha do cliente
 * @return A senha do cliente
 */
public String getSenha() {
		return senha;
}

/**
 * Define a senha do cliente
 * @param senha A nova senha do cliente
 */
public void setSenha(String senha) {
		this.senha = senha;
}

/**
 * Retorna a lista de mídias assistidas pelo cliente
 * @return A lista de mídias assistidas
 */
public List<Midia> getAssistidas() {
		return listaJaViu;
}

/**
 * Define a lista de mídias assistidas pelo cliente
 * @param assistidas A nova lista de mídias assistidas
 */
public void setAssistidas(List<Midia> listaJaViu) {
		this.listaJaViu = listaJaViu;
}

/**
 * Retorna a lista de mídias de interesse do cliente
 * @return A lista de mídias de interesse
 */
public List<Midia> getlistaFutura() {
		return listaFutura;
}

/**
 * Define a lista de mídias de interesse do cliente
 * @param interesses A nova lista de mídias de interesse
 */
public void setlistaJaViu(List<Midia> listaFutura) {
		this.listaFutura = listaFutura;
}

/**
 * Verifica se uma mídia já foi adicionada a uma lista de mídias
 * @param midia      A mídia a ser verificada
 * @param listaMidia A lista de mídias
 * @return true se a mídia já foi adicionada à lista, false caso contrário
 */
	public boolean midiaJaAdicionada(Midia midia, List<Midia> listaMidia) {
		return listaJaViu.stream().filter(midiaVista -> midiaVista.getID().equals(midia)).findAny().isPresent();
	}


/**
 * Registra uma mídia como assistida pelo cliente
 * @param midia A mídia a ser registrada como assistida
 */
public void registraJaViu(Midia midia) {
    this.listaJaViu.add(midia);
    this.atualizaVisualizacao();
}
	/**
 * Registra uma mídia como interesse do cliente
 * @param midia A mídia a ser registrada como interesse
 */
public void registraAFutura(Midia midia) {
    this.listaFutura.add(midia);
}

/**
 * Adiciona uma mídia à lista de mídias assistidas pelo cliente
 * @param midia A mídia a ser adicionada
 * @return true se a mídia foi adicionada com sucesso, false caso contrário
 * @throws Exception se a mídia já tiver sido adicionada anteriormente
 */
public boolean adicionarJaViu(Midia midia) throws Exception {
    boolean resultado = true;
    if (!midiaJaAdicionada(midia, listaJaViu)) {
        this.listaJaViu.add(midia);
        this.atualizaVisualizacao();
        midia.incrementarReproducoes();
    } else {
        resultado = false;
        throw new Exception("Mídia já adicionada");
    }
    return resultado;
}
/**
 * Adiciona uma mídia à lista de interesses do cliente
 * @param midia A mídia a ser adicionada
 * @return true se a mídia foi adicionada com sucesso, false caso contrário
 * @throws Exception se a mídia já tiver sido adicionada anteriormente
 */
	public boolean adicionarAFutura(Midia midia) throws Exception {
		boolean resultado = true;
		if (!midiaJaAdicionada(midia, listaFutura)) {
			this.listaFutura.add(midia);
		} else {
			resultado = false;
			throw new Exception("Mídia já adicionada");
		}
		return resultado;
	}

/**
 * Busca uma mídia assistida pelo cliente pelo nome
 * @param nome O nome da mídia a ser buscada
 * @return A mídia assistida encontrada
 */
public Midia buscarMidiaJaViu(String nome) {
    return listaJaViu.stream().filter(midia -> midia.getNome().equals(nome)).findAny().get();
}

/**
 * Busca uma mídia de interesse do cliente pelo nome
 * @param nome O nome da mídia a ser buscada
 * @return A mídia de interesse encontrada
 */
public Midia buscaMidiaFutura(String nome) {
    return listaFutura.stream().filter(midia -> midia.getNome().equals(nome)).findAny().get();
}

/**
 * Atualiza o contador de visualizações da série
 */
private void atualizaVisualizacao() {
    this.visualizacoes++;
}

@Override
public ConversorParaString converterParaObjeto(String dados) {
    String[] valores = dados.split(";");
    return new Cliente(valores[0], valores[1], valores[2]);
}

@Override
public String getChave() {
    return login;
}



/**
 * Retorna o nome do cliente.
 * @return O nome do cliente.
 */
public String getNome() {
    return nome;
}

/**
 * Retorna a lista de mídias que foram avaliadas pelo cliente.
 * @return A lista de mídias avaliadas.
 */
public ArrayList<Midia> getMidiasQueAvaliei() {
    return midiasQueAvaliei;
}

/**
 * Retorna a lista de nomes de mídias com base na lista fornecida.
 * @param listaAssistida Indica se a lista de mídias é a lista de mídias assistidas.
 * @return A lista de nomes de mídias.
 */
public List<String> getListaMidia(boolean listaAssistida) {
    List<String> nomeMidias;
    if (listaAssistida) {
        nomeMidias = mapListaMidias(listaJaViu);
    } else {
        nomeMidias = mapListaMidias(listaFutura);
    }
    return nomeMidias;
}

/**
 * Mapeia a lista de mídias para uma lista de nomes de mídias.
 * @param midias A lista de mídias a ser mapeada.
 * @return A lista de nomes de mídias.
 */
private List<String> mapListaMidias(List<Midia> midias) {
    List<String> nomeMidias = new ArrayList<>();
    for (Midia midia : midias) {
        nomeMidias.add(midia.getNome());
    }
    return nomeMidias;
}}
