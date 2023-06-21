package POO.trabalhoemgrupo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.naming.AuthenticationException;

public class Streaming {
    // Atributos
    private static final String FILMES_CSV = "C:/Users/Diogo/Desktop/vscode/.vscode/POO/trabalhoemgrupo/banco/POO_Filmes.csv";
    private static final String SERIES_CSV = "C:/Users/Diogo/Desktop/vscode/.vscode/POO/trabalhoemgrupo/banco/POO_Series.csv";
    private static final String ESPECTADORES_CSV = "C:/Users/Diogo/Desktop/vscode/.vscode/POO/trabalhoemgrupo/banco/POO_Espectadores.csv";
    private static final String AUDIENCIA_CSV = "C:/Users/Diogo/Desktop/vscode/.vscode/POO/trabalhoemgrupo/banco/POO_Audiencia.csv";

    private Cliente clienteLogado;
    private String nome;
    public HashMap<Integer, Midia> midias = new HashMap<>();
    public HashMap<String, Cliente> clientes = new HashMap<>();
    private Cliente clienteAtual;
    // Fim dos atributos

    /**
     * Construtor da classe Streaming.
     *
     * @param nome O nome do streaming.
     * @throws IOException Exceção lançada se ocorrer um erro de leitura/escrita.
     */
    public Streaming(String nome) throws IOException {
        this.nome = nome;
    }

    /**
     * Construtor vazio da classe Streaming.
     */
    public Streaming() {
    }

    /**
     * Realiza o login de um cliente.
     *
     * @param login O nome de login do cliente.
     * @param senha A senha do cliente.
     * @return O objeto Cliente correspondente ao login bem-sucedido.
     * @throws IOException             Exceção lançada se ocorrer um erro de
     *                                 leitura/escrita.
     * @throws AuthenticationException Exceção lançada se as credenciais de login
     *                                 forem inválidas.
     */
    public Cliente login(String login, String senha) throws IOException, AuthenticationException {
        FileReader fileReader = new FileReader(
                ESPECTADORES_CSV);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String linha;
        while ((linha = bufferedReader.readLine()) != null) {

            String[] campos = linha.split(";");

            if (campos.length == 3 && campos[1].equals(login) && campos[2].equals(senha)) {
                bufferedReader.close();
                Cliente cliente = new Cliente(login);
                clientes.putIfAbsent(login, cliente);
                return cliente;
            }
        }

        bufferedReader.close();
        throw new AuthenticationException("Credenciais de login inválidas.");
    }

    /**
     * Adiciona uma série ao streaming.
     *
     * @param serie A instância da série a ser adicionada.
     */
    public void adicionarSerie(Midia serie) {
        this.midias.put(serie.getId(), serie);
        adicionarMidiaNoCSV(serie, SERIES_CSV);

    }

    /**
     * Avalia uma mídia especificada por seu ID, atribuindo uma nota a ela.
     * O método inicia filtrando a mídia por ID, primeiro com o filtro "f" e depois
     * com o filtro "s" caso a mídia não tenha sido encontrada anteriormente.
     * Se a mídia for encontrada, a avaliação é adicionada a ela, exibindo uma
     * mensagem de sucesso.
     * Se o cliente for encontrado, a nota é adicionada à sua lista de notas,
     * e se essa lista atingir o tamanho 5, é exibida uma mensagem que você entrou
     * na categoria de clientes especialista.
     * 
     * @param login      O login do cliente que está avaliando a mídia.
     * @param idMidia    O ID da mídia a ser avaliada.
     * @param nota       A nota atribuída à mídia.
     * @param arquivoCSV O nome do arquivo CSV que contém as informações das mídias.
     * @return A mídia avaliada, ou null se a mídia não for encontrada.
     */
    public Midia avaliarMidia(String login, int idMidia, int nota, String arquivoCSV) {

        Midia midia = filtrarPorId(idMidia, arquivoCSV, "f");
        if (midia == null)
            midia = filtrarPorId(idMidia, arquivoCSV, "s");

        if (midia != null) {
            midia.adicionarAvaliacao(login, nota);
            System.out.println("Avaliação adicionada com sucesso.");
            Cliente cliente = clientes.get(login);
            if (cliente != null) {
                cliente.getListaNotas().put(idMidia, nota);
                if (cliente.getListaNotas().size() == 5) {
                    System.out.println("Parabéns! Você se tornou um cliente especialista.");
                }
            } else {
                System.out.println("Cliente não encontrado!");
            }
        } else {
            System.out.println("Mídia não encontrada.");
        }
        return midia;
    }

    /**
     * Filtra uma mídia pelo ID.
     *
     * @param idMidia    O ID da mídia a ser filtrada.
     * @param arquivoCSV O caminho do arquivo CSV contendo as informações das
     *                   mídias.
     * @param tipoMidia  O tipo de mídia ("f" para filme, qualquer outro valor para
     *                   série).
     * @return A mídia filtrada ou null se não encontrada.
     */
    public Midia filtrarPorId(int idMidia, String arquivoCSV, String tipoMidia) {
        BufferedReader bufferedReader = null;

        Midia m = midias.get(idMidia);

        if (m != null) {
            return m;
        } else {

            try {
                FileReader fileReader = new FileReader(arquivoCSV);
                bufferedReader = new BufferedReader(fileReader);

                String linha;
                while ((linha = bufferedReader.readLine()) != null) {
                    linha = linha.replaceAll("\uFEFF", ""); // Remove o BOM da linha

                    String[] midia = linha.split(";");

                    if (Integer.parseInt(midia[0]) == idMidia) { // filme[0] é o id
                        System.out.println("Mídia encontrada: " + midia[1]); // filme[1] é o nome
                        if (tipoMidia.equalsIgnoreCase("f")) {
                            Filme filme = new Filme(idMidia, midia[1], null, 0);
                            midias.put(idMidia, filme);
                            return filme;
                        } else {
                            Serie serie = new Serie(idMidia, midia[1], null, 0);
                            midias.put(idMidia, serie);
                            return serie;
                        }
                    }
                }

            } catch (IOException e) {
                System.out.println("Erro ao ler o arquivo CSV: " + e.getMessage());
            } finally {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        System.out.println("Erro ao fechar o BufferedReader: " + e.getMessage());
                    }
                }
            }
        }

        return null; // Retorna -1 se a mídia não for encontrada
    }

    /**
     * Adiciona um novo cliente ao sistema.
     *
     * @param nomeUsuario O nome de usuário do cliente.
     * @param login       O login do cliente.
     * @param senha       A senha do cliente.
     * @throws IOException             Se ocorrer um erro de I/O durante a leitura
     *                                 ou escrita do arquivo.
     * @throws AuthenticationException Se o nome de usuário já estiver sendo usado
     *                                 por outro cliente.
     */
    public void adicionarCliente(String nomeUsuario, String login, String senha)
            throws IOException, AuthenticationException {
        FileReader fileReader = new FileReader(
                ESPECTADORES_CSV);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String linha;
        while ((linha = bufferedReader.readLine()) != null) {
            String[] campos = linha.split(";");
            if (campos.length >= 3 && campos[1].equals(nomeUsuario)) {
                bufferedReader.close();
                throw new AuthenticationException("Nome de usuário já existente");
            }
        }

        FileWriter fileWriter = new FileWriter(
                ESPECTADORES_CSV, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        String novaLinha = nomeUsuario + ";" + login + ";" + senha;

        // Adiciona a nova linha no arquivo
        bufferedWriter.newLine(); // Pula uma linha antes de adicionar os dados do novo cliente
        bufferedWriter.write(novaLinha);

        bufferedWriter.close();

        // Crie um novo objeto Cliente com as informações
        Cliente cliente = new Cliente(nomeUsuario, login, senha);

        // Adicione o cliente à lista de clientes
        clientes.put(nomeUsuario, cliente);
        bufferedReader.close();
    }

    /**
     * Adiciona um filme ao sistema.
     *
     * @param filme O objeto Filme a ser adicionado.
     */
    public void adicionarFilme(Filme filme) {
        this.midias.put(filme.getId(), filme);
        adicionarMidiaNoCSV(filme, FILMES_CSV);
    }

    /**
     * 
     * Filtra as mídias por gênero.
     * 
     * @param genero O gênero pelo qual as mídias serão filtradas.
     * @return Uma lista contendo as mídias filtradas pelo gênero.
     */
    public List<Midia> filtrarPorGenero(Genero genero) {
        return midias.values().stream()
                // .filter(midia -> midia.getGenero() == genero)
                .collect(Collectors.toList());
    }

    /**
     * 
     * Filtra as mídias por idioma.
     * 
     * @param idioma O idioma pelo qual as mídias serão filtradas.
     * @return Uma lista contendo as mídias filtradas pelo idioma.
     */
    public List<Midia> filtrarPorIdioma(String idioma) {
        return this.midias.values().stream()
                .filter(s -> ((String) s.getIdioma()).equalsIgnoreCase(idioma))
                .collect(Collectors.toList());
    }

    /**
     * 
     * Filtra as mídias pelo nome.
     * 
     * @param nome O nome pelo qual as mídias serão filtradas.
     * 
     * @return Uma lista contendo as mídias filtradas pelo nome.
     */
    public List<Midia> filtrarPorNome(String nome) {
        try (BufferedReader seriesReader = Files.newBufferedReader(Paths.get(SERIES_CSV), StandardCharsets.ISO_8859_1);
                BufferedReader filmesReader = Files.newBufferedReader(Paths.get(FILMES_CSV),
                        StandardCharsets.ISO_8859_1)) {

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            return Stream.concat(seriesReader.lines(), filmesReader.lines())
                    .map(line -> {
                        String[] s = line.split(";");
                        if (s.length == 3) {
                            return new Serie(Integer.parseInt(s[0]), s[1], LocalDate.parse(s[2], dateFormatter), 0);
                        } else {
                            return new Filme(Integer.parseInt(s[0]), s[1], LocalDate.parse(s[2], dateFormatter),
                                    Integer.parseInt(s[3]));
                        }
                    })
                    .filter(midia -> midia.getNome().toLowerCase().contains(nome.toLowerCase()))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * Registra a audiência do cliente atual
     * 
     * @param midia
     */
    public void registrarAudiencia(Midia midia) {
        Cliente cliente = this.clienteAtual;
        if (cliente != null) {
            cliente.registrarAudiencia(midia);
        }
    }

    /**
     * get para lista de clientes
     * 
     * @return clientes
     */
    public HashMap<String, Cliente> getClientes() {
        return this.clientes;
    }

    /**
     * calcula a média de notas
     * 
     * @param id
     * @return lista de midias com avaliação media
     */
    public double calcularMediaNotas(int id) {
        Midia midias = encontrarMidiaPorId(id);
        if (midias == null) {
            throw new IllegalArgumentException("Streaming não encontrado.");
        }
        return midias.getMediaAvaliacao();
    }

    /**
     * encontra mídia pelo id da mídia
     * 
     * @param id
     * @return
     */
    public Midia encontrarMidiaPorId(int id) {
        for (Map.Entry<Integer, Midia> entry : midias.entrySet()) {
            Midia midia = entry.getValue();
            if (midia.getId() == id) {
                return midia;
            }
        }
        return null;
    }

    /**
     * relatório do cliente que viu mais mídias
     * 
     * @return relatorio
     */
    public String ClienteViuMaisMidias() {

        String modorelatorio = "";
        int midiasvistas = 0;
        Cliente ClienteViuMaisMidias = null;

        ClienteViuMaisMidias = clientes.values().stream()
                .max(Comparator.comparing(c -> ((Cliente) c).getTamanhoListaJaViu()))
                .orElse(null);

        midiasvistas = ClienteViuMaisMidias.getTamanhoListaJaViu();

        modorelatorio = "Qual cliente assistiu mais mídias " + ClienteViuMaisMidias.getDados()
                + "\n Número de mídias assistidas: " + midiasvistas;
        return modorelatorio;
    }

    public String ClientePossuiMaisAvaliacao() {
        Map<Midia, Long> contAvaliacoes = midias.values().stream()
                .flatMap(midia -> midia.getAvaliadores().stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        Map.Entry<Midia, Long> entry = contAvaliacoes.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElse(null);

        String relatorio = "";
        if (entry != null) {
            relatorio = "Cliente com mais avaliações: " + entry.getKey() + "\nNúmero de mídias avaliadas: "
                    + entry.getValue();
        } else {
            relatorio = "Nenhum cliente avaliou.";
        }
        return relatorio;
    }

    /**
     * relatório da porcentagem de clientes com 15+ avaliações
     * 
     * @return relatório
     */
    public String PorcentagemCliente15Avaliacoes() {
        Map<Midia, Long> contAvaliacoes = midias.values().stream()
                .flatMap(midia -> midia.getAvaliadores().stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        long totalClientes = clientes.size();
        long clientesComMaisDe15Avaliacoes = contAvaliacoes.values().stream()
                .filter(contador -> contador >= 15)
                .count();

        double porcentagem = (double) clientesComMaisDe15Avaliacoes / totalClientes * 100;
        String modorelatorio = "A media de clientes com mais que 15 avaliações é" + porcentagem;
        return modorelatorio;
    }

    /**
     * relatório das mídias mais vistas pelo usuário
     * 
     * @return relatório
     */
    public String MidiasMaisVistas() {
        String modorelatorio = midias.values().stream()
                .sorted(Comparator.comparing(Midia::getAudiencia).reversed())
                .limit(10)
                .map(midia -> "Midia: " + midia.getNome() + " - Audiencia: " + midia.getAudiencia())
                .collect(Collectors.joining("\n"));
        return modorelatorio;
    }

    /**
     * relatório de mídias mais avaliadas pelos usuários
     * 
     * @return relatório
     */
    public String MidiasMaisAvaliadas() {
        String modorelatorio = midias.values().stream()
                .sorted(Comparator.comparingDouble(Midia::calcularNotaMedia).reversed())
                .limit(10)
                .map(midia -> "Midia: " + midia.getNome() + " - Nota Media: " + midia.calcularNotaMedia())
                .collect(Collectors.joining("\n"));
        return modorelatorio;
    }

    /**
     * relatório de mídias mais vistas por gênero
     * 
     * @param genero
     * @return relatório
     */
    public String MidiasMaisVistasPorGenero(String genero) {
        String modorelatorio = midias.values().stream()
                .filter(midia -> midia.getGenero().equals(genero))
                .sorted(Comparator.comparingDouble(Midia::getAudiencia).reversed())
                .limit(10)
                .map(midia -> "Midia do genero " + genero + ": " + midia.getNome() + " - Audiencia: "
                        + midia.getAudiencia())
                .collect(Collectors.joining("\n"));
        if (modorelatorio.isEmpty()) {
            modorelatorio = "Esse genero nao existe nessa midia";
        }
        return modorelatorio;
    }

    /**
     * relatório de mídias mais avaliadas por gênero
     * 
     * @param genero
     * @return relatório
     */
    public String MidiasMaisAvaliadasPorGenero(String genero) {
        String modorelatorio = midias.values().stream()
                .filter(midia -> midia.getGenero().equals(genero))
                .sorted(Comparator.comparingDouble(Midia::calcularNotaMedia).reversed())
                .limit(10)
                .map(midia -> "Midia do genero " + genero + ": " + midia.getNome() + " - Nota Media: "
                        + midia.calcularNotaMedia())
                .collect(Collectors.joining("\n"));
        if (modorelatorio.isEmpty()) {
            modorelatorio = "Esse genero nao existe nessa midia";
        }
        return modorelatorio;
    }

    /**
     * adiciona o cliente cadastrado no arquivo POO_Espectadores.csv
     * 
     * @param cliente
     */
    public void adicionarClienteNoCSV(Cliente cliente) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(
                AUDIENCIA_CSV, true))) {
            writer.println(cliente.Formatador());
            writer.flush();
        } catch (IOException e) {
            System.out.println("Erro ao adicionar cliente no arquivo CSV: " + e.getMessage());
        }
    }

    /**
     * adiciona a mídia(série ou filme) no arquivo POO_Series.csv e POO_Filmes.csv
     * 
     * @param midia
     * @param arquivoCSV
     */
    public void adicionarMidiaNoCSV(Midia midia, String arquivoCSV) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(arquivoCSV, true))) {
            writer.println(midia.Formatador());
            writer.flush();
        } catch (IOException e) {
            System.out.println("Erro ao adicionar mídia no arquivo CSV: " + e.getMessage());
        }
    }

    /**
     * get lista hashmap com as mídias
     * 
     * @return lista de mídias
     */
    public HashMap<Integer, Midia> getMidias() {
        return midias;
    }

}
