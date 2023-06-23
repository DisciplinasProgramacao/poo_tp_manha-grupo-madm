package POO.trabalhopraticoPOO;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Classe que representa um serviço de streaming.
 */
public class Streaming {

    private static final String SERIES_CSV = "C:/Users/Diogo/Desktop/vscode/.vscode/POO/trabalhopraticoPOO/banco/POO_Series.csv";
    private static final String FILMES_CSV = "C:/Users/Diogo/Desktop/vscode/.vscode/POO/trabalhooraticoPOO/banco/POO_Filmes.csv";
    private static final String ESPECTADORES_CSV = "C:/Users/Diogo/Desktop/vscode/.vscode/POO/trabalhopraticoPOO/banco/POO_Espectadores.csv";
    private static final String AUDIENCIA_CSV = "C:/Users/Diogo/Desktop/vscode/.vscode/POO/trabalhopraticoPOO/banco/POO_Audiencia.csv";

    private Map<String, Cliente> clientes = new HashMap<>(10000);
    private String nome;
    private Map<String, Midia> midias = new HashMap<>(10000);
    private Cliente clienteLogado;

    /**
     * Construtor da classe `Streaming`.
     * Inicializa o serviço de streaming carregando os dados e preenchendo a
     * audiência.
     */
    public Streaming() {
        inicializar();
    }

    /**
     * Inicializa o serviço de streaming carregando os dados e preenchendo a
     * audiência.
     */
    public void inicializar() {
        carregarDados();
        List<String> audiencia = carregarAudiencia();
        preencherAudiencia(audiencia);
    }

    /**
     * Carrega os dados dos clientes e mídias a partir dos arquivos CSV
     * correspondentes.
     */
    private void carregarDados() {
        LeitorArquivos leitor = new LeitorArquivos<ConversorParaString>();

        clientes = leitor.lerCSV(new Cliente(), ESPECTADORES_CSV);
        midias = leitor.lerCSV(new Serie(), SERIES_CSV);
        midias.putAll(leitor.lerCSV(new Filme(), FILMES_CSV));
    }

    /**
     * Carrega os dados da audiência a partir do arquivo CSV correspondente.
     *
     * @return Uma lista de strings contendo os dados da audiência.
     */
    private List<String> carregarAudiencia() {
        return LeitorArquivos.lerCSV(AUDIENCIA_CSV);
    }

    /**
     * Preenche a audiência do serviço de streaming com base nos dados fornecidos.
     *
     * @param dadosAudiencia Os dados da audiência a serem preenchidos.
     */
    private void preencherAudiencia(List<String> dadosAudiencia) {
        String[] valores;
        for (String audiencia : dadosAudiencia) {
            valores = audiencia.split(";");
            Cliente cliente = clientes.get(valores[0]);
            Midia midia = midias.get(valores[2]);
            if (cliente != null && midia != null) {
                if (valores[1].equals("A")) {
                    registrarAudiencia(cliente, midia);
                } else {
                    cliente.registraAFutura(midia);
                }
            }
        }
    }

    /**
     * Registra uma visualização de uma mídia por um cliente.
     *
     * @param cliente O cliente que realizou a visualização.
     * @param midia   A mídia que foi visualizada.
     */
    private void registrarAudiencia(Cliente cliente, Midia midia) {
        cliente.registraJaViu(midia);
    }

    /**
     * Realiza a autenticação do cliente com base no login e senha fornecidos.
     *
     * @param login O login do cliente.
     * @param senha A senha do cliente.
     * @return true se a autenticação for bem-sucedida, false caso contrário.
     */
    public boolean login(String login, String senha) {
        boolean autenticado = false;

        if (clientes.containsKey(login)) {
            if (clientes.get(login).getSenha().equals(senha)) {
                clienteLogado = clientes.get(login);
                autenticado = true;
            }
        }
        return autenticado;
    }

    /**
     * Busca e retorna uma lista de mídias com base no gênero especificado.
     *
     * @param genero O gênero das mídias a serem buscadas.
     * @return Uma lista de mídias que possuem o gênero especificado.
     */
    public List<Midia> buscarMidiasPorGenero(String genero) {
        return this.midias.values().stream()
                .filter(serie -> genero.equalsIgnoreCase(serie.getNome())).collect(Collectors.toList());
    }

    /**
     * Busca e retorna uma lista de mídias com base no idioma especificado.
     *
     * @param idioma O idioma das mídias a serem buscadas.
     * @return Uma lista de mídias que possuem o idioma especificado.
     */
    public List<Midia> buscarMidiasPorIdioma(String idioma) {
        return this.midias.values().stream()
                .filter(serie -> idioma.equalsIgnoreCase(serie.getIdioma())).collect(Collectors.toList());
    }

    /**
     * Busca e retorna uma lista de séries assistidas pelo cliente especificado.
     *
     * @param cliente O cliente para o qual as séries assistidas serão buscadas.
     * @return Uma lista de séries que foram assistidas pelo cliente.
     */
    public List<Serie> buscarSeriesJaViu(Cliente cliente) {
        return this.buscarSeriesJaViu(cliente);
    }

    /**
     * Busca e retorna uma lista de séries de interesse do cliente especificado.
     *
     * @param cliente O cliente para o qual as séries de interesse serão buscadas.
     * @return Uma lista de séries que são de interesse do cliente.
     */
    public List<Serie> buscarSeriesFutura(Cliente cliente) {
        return this.buscarSeriesFutura(cliente);
    }

    /**
     * Busca e retorna uma mídia na lista do cliente com base no nome fornecido e no
     * status de ser assistida ou de interesse.
     *
     * @param nome        O nome da mídia a ser buscada.
     * @param ehAssistida Indica se a mídia deve ser buscada na lista de mídias
     *                    assistidas (true) ou na lista de mídias de interesse
     *                    (false).
     * @return A mídia encontrada ou null se não for encontrada na lista
     *         especificada.
     */
    public Midia buscarMidiaNaLista(String nome, boolean ehAssistida) {
        Midia midia;
        if (ehAssistida) {
            midia = clienteLogado.buscarMidiaJaViu(nome);
        } else {
            midia = clienteLogado.buscaMidiaFutura(nome);
        }
        return midia;
    }

    /**
     * Adiciona uma mídia à lista do cliente logado, seja na lista de mídias
     * assistidas ou na lista de mídias de interesse.
     *
     * @param nome        O nome da mídia a ser adicionada.
     * @param jaAssistida Indica se a mídia já foi assistida (true) ou é de
     *                    interesse (false).
     * @throws Exception se a mídia não for encontrada.
     */
    public void adicionarMidiaALista(String nome, boolean jaViu) throws Exception {
        Midia midia = this.midias.get(nome);
        if (midia == null) {
            throw new Exception("Mídia não encontrada\n");
        }

        if (jaViu) {
            clienteLogado.adicionarJaViu(midia);
        } else {
            clienteLogado.adicionarAFutura(midia);
        }
    }

    /**
     * Adiciona uma série à lista de interesse do cliente logado.
     *
     * @param nome O nome da série a ser adicionada.
     */
    public void temInteresse(String nome) {
        Midia midia = this.midias.get(nome);
        this.clienteLogado.getlistaFutura().add(midia);
    }

    /**
     * Remove uma série da lista de interesse do cliente especificado.
     *
     * @param cliente O cliente para o qual a série será removida da lista de
     *                interesse.
     */
    public void naoTemInteresse(Cliente cliente) {
        Midia midia = this.midias.get(nome);
        this.clienteLogado.getlistaFutura().remove(midia);
    }

    /**
     * Avalia uma mídia, registrando a avaliação do cliente logado para a mídia
     * específica.
     *
     * @param midia A mídia a ser avaliada.
     */
    public void avaliar(Midia midia) {
        avaliarMidia(clienteLogado, midia);
    }

    /**
     * Avalia uma mídia específica pelo cliente especificado, registrando a
     * avaliação na mídia e no cliente.
     * Se o cliente já tiver avaliado a mídia, uma mensagem será exibida.
     *
     * @param cliente O cliente que está realizando a avaliação.
     * @param midia   A mídia a ser avaliada.
     */
    public void avaliarMidia(Cliente cliente, Midia midia) {
        if (!cliente.verificaSeAvaliou(midia)) {
            System.out.print("Digite a nota (de 0 a 5): ");
            int nota = Integer.parseInt(APP.scanner.nextLine());
            Avaliacao avaliacao = new Avaliacao(nota);

            cliente.addAvaliadas(midia);
            midia.avaliarMidia(avaliacao);
            if (cliente.getMidiasQueAvaliei().size() == 5) {
                System.out.println("Parabéns! Você se tornou um cliente especialista.");
            }
        } else {
            System.out.println("Esta mídia já foi avaliada por " + cliente.getNome());
        }
    }

    /**
     * Exibe a lista de mídias do cliente logado, seja a lista de mídias assistidas
     * ou a lista de mídias de interesse.
     *
     * @param listaAssistida Indica se a lista a ser exibida é a de mídias
     *                       assistidas (true) ou de mídias de interesse (false).
     * @return Uma lista de strings contendo as informações das mídias da lista
     *         especificada.
     */
    public List<String> exibirListaMidia(boolean listaAssistida) {
        return clienteLogado.getListaMidia(listaAssistida);
    }

    /**
     * relatório do cliente que viu mais mídias
     * @return qual cliente viu mais mídias e quantas
     */
    public String ClienteViuMaisMidias() {
        String modorelatorio = "";
        int midiasvistas = 0;
        Cliente ClienteViuMaisMidias = null;

        ClienteViuMaisMidias = clientes.values().stream()
                .max(Comparator.comparingInt(c -> ((Cliente) c).getTamanhoListaJaViu()))
                .orElse(null);

        if (ClienteViuMaisMidias != null) {
            midiasvistas = ClienteViuMaisMidias.getTamanhoListaJaViu();

            modorelatorio = "Qual cliente assistiu mais mídias: " + ClienteViuMaisMidias.getNome()
                    + "\nNúmero de mídias assistidas: " + midiasvistas;
        } else {
            modorelatorio = "Nenhum cliente encontrado.";
        }

        return modorelatorio;
    }

    /**
     * relatório do cliente que possui mais avaliação
     * @return cliente com mais avaliações e o número de mídias avaliadas por ele
     */
    public String ClientePossuiMaisAvaliacao() {
        Map<Cliente, Long> contAvaliacoes = clientes.values().stream()
                .collect(Collectors.toMap(
                        cliente -> cliente,
                        cliente -> (long) cliente.getMidiasQueAvaliei().size()));

        Map.Entry<Cliente, Long> entry = contAvaliacoes.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElse(null);

        String relatorio = "";
        if (entry != null) {
            relatorio = "Cliente com mais avaliações: " + entry.getKey().getNome() + "\nNúmero de mídias avaliadas: "
                    + entry.getValue();
        } else {
            relatorio = "Nenhum cliente avaliou.";
        }

        return relatorio;
    }

    /**
     * relatório de porcentagem de clientes com mais ou igual a 15 avaliações
     * @return porcentagem de clientes
     */
    public String PorcentagemCliente15Avaliacoes() {
        long totalClientes = clientes.size();
        long clientesComMaisDe15Avaliacoes = clientes.values().stream()
                .filter(cliente -> cliente.getQuantidadeAvaliacoes() >= 15)
                .count();

        double porcentagem = (double) clientesComMaisDe15Avaliacoes / totalClientes * 100;
        String relatorio = "A porcentagem de clientes com mais de 15 avaliações é " + porcentagem + "%.";

        return relatorio;
    }


    /**
     * relatório do top 10 mídias mais vistas
     * @return mídias mais vistas
     */
    public String MidiasMaisVistas() {
        String modorelatorio = midias.values().stream()
                .sorted(Comparator.comparingDouble(Midia::getVisualizacoes).reversed())
                .limit(10)
                .map(midia -> "Mídia: " + midia.getNome())
                .collect(Collectors.joining("\n"));

        return modorelatorio;
    }

    /**
     * relatório de mídias mais avaliadas
     * @return mídias mais avaliadas
     */
    public String MidiasMaisAvaliadas() {
        String modorelatorio = midias.values().stream()
                .sorted(Comparator.comparingDouble(Midia::getVisualizacoes).reversed())
                .limit(10)
                .map(midia -> "Mídia: " + midia.getNome())
                .collect(Collectors.joining("\n"));

        return modorelatorio;
    }

}

/**
 * OBS: Relatórios que não conseguimos implementar
 * 
 *         public String MidiasMaisVistasPorGenero(String genero) {
 *         String modorelatorio = midias.values().stream()
 *         .filter(midia -> midia.getGenero().equals(genero))
 *         .sorted(Comparator.comparingDouble(Midia::getAudiencia).reversed())
 *         .limit(10)
 *         .map(midia -> "Midia do genero " + genero + ": " + midia.getNome() +
 *         " - Audiencia: "
 *         + midia.getAudiencia())
 *         .collect(Collectors.joining("\n"));
 *         if (modorelatorio.isEmpty()) {
 *         modorelatorio = "Esse genero nao existe nessa midia";
 *         }
 *         return modorelatorio;
 *         }
 *         public String MidiasMaisAvaliadasPorGenero(String genero) {
 *         String modorelatorio = midias.values().stream()
 *         .filter(midia -> midia.getGenero().equals(genero))
 *         .sorted(Comparator.comparingDouble(Midia::calcularNotaMedia).reversed())
 *         .limit(10)
 *         .map(midia -> "Midia do genero " + genero + ": " + midia.getNome() +
 *         " - Nota Media: "
 *         + midia.calcularNotaMedia())
 *         .collect(Collectors.joining("\n"));
 *         if (modorelatorio.isEmpty()) {
 *         modorelatorio = "Esse genero nao existe nessa midia";
 *         }
 *         return modorelatorio;
 *         }
 */
