package POO.trabalhoemgrupo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import javax.naming.AuthenticationException;

public class APP {

    private static final String SERIES_CSV = "C:/Users/Diogo/Desktop/vscode/.vscode/POO/trabalhoemgrupo/banco/POO_Series.csv";
    private static final String FILMES_CSV = "C:/Users/Diogo/Desktop/vscode/.vscode/POO/trabalhoemgrupo/banco/POO_Filmes.csv";
    private static Streaming plataforma;
    private static Scanner scanner;

    public static void main(String[] args) throws IOException, AuthenticationException {

        plataforma = new Streaming("Prime Streaming");
        scanner = new Scanner(System.in);
        limparTela();
        exibirMenuPrincipal();

        scanner.close();
    }

    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void exibirMenuPrincipal() {
        int v = -1;
        limparTela();
        try {
            while (v != 0) {
                System.out.println("== Menu Principal ==");
                System.out.println("1. Cadastrar Cliente");
                System.out.println("2. Login");
                System.out.println("3. Sair");
                System.out.print("Escolha uma opção: ");
                int opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        cadastrarCliente();
                        break;
                    case 2:
                        Cliente logado = fazerLogin();
                        if (logado != null) {
                            exibirMenuCliente(logado);
                        }
                        break;
                    case 3:
                        System.out.println("Saindo do Prime Streaming. Volte Sempre!");
                        v = 0;
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }

                System.out.println();
            }
        } catch (InputMismatchException e) {
            System.out.println("Insira um valor válido");
        } catch (Exception invalid) {
            invalid.printStackTrace();
        }
        scanner.close();
    }

    private static void exibirMenuCliente(Cliente logado) throws IOException {
        limparTela();
        int v = -1;
        while (v != 0) {
            System.out.println("Menu do Cliente:");
            System.out.println("1. Buscar Mídia");
            System.out.println("2. Adicionar mídia");
            System.out.println("3. Menu de relatórios");
            System.out.println("4. Avaliar Mídia");
            System.out.println("5. Adicionar Mídia á Lista");
            System.out.println("6. Ver sua Lista");
            System.out.println("0. Sair");

            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            switch (opcao) {
                case 1:
                    buscarMidias();
                    break;
                case 2:
                    cadastrarMidia();
                    break;
                case 3:
                    modoRelatorio();
                    break;
                case 4:
                    avaliarMidia(logado);
                    break;
                case 5:
                    adicionarListaFutura(null);
                    break;
                case 6:
                    System.out.println("Lista Futura:");
                    for (Midia midia : logado.getListaFutura()) {
                        System.out.println(midia);
                    }
                    break;
                case 0:
                    v = 0;
                    System.out.println("Desconectado com sucesso!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void buscarMidias() {
        limparTela();
        List<Midia> resultado = null;
        System.out.println("Deseja buscar por \nn - Nome g - Gênero  i - Idioma");
        char op = scanner.next().toLowerCase().charAt(0);
        System.out.println("Informe a sua busca:");
        String valor = scanner.next();
        System.out.println("");
        switch (op) {
            case 'n':
                System.out.println("== Buscar Séries por Nome ==");
                resultado = plataforma.filtrarPorNome(valor);
                break;
            default:
                break;
        }
        resultado.stream().forEach(m -> System.out.println(m.toString()));
    }

    private static void avaliarMidia(Cliente logado) throws IOException {
        System.out.println("== Avaliar Mídia ==");

        String tipoMidia;
        System.out.println("Digite o tipo de mídia (f para filme ou s para série):");
        tipoMidia = scanner.nextLine();

        String arquivoCSV;
        if (tipoMidia.equalsIgnoreCase("f")) {
            arquivoCSV = FILMES_CSV;
        } else if (tipoMidia.equalsIgnoreCase("s")) {
            arquivoCSV = SERIES_CSV;
        } else {
            System.out.println("Tipo de mídia inválido.");
            return;
        }

        System.out.print("Digite o ID da mídia: ");
        int idMedia = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        Midia midia = plataforma.filtrarPorId(idMedia, arquivoCSV, tipoMidia);

        if (midia != null) {
            int nota = -1;
            while (nota < 0 || nota > 5) {
                System.out.print("Digite a nota (de 0 a 5): ");
                nota = scanner.nextInt();
                scanner.nextLine(); // Consumir a quebra de linha

                if (nota < 0 || nota > 5) {
                    System.out.println("Nota inválida. Digite novamente.");
                }
            }

            String idCliente = logado.getIdCliente();
            plataforma.avaliarMidia(idCliente, idMedia, nota, arquivoCSV);
            System.out.println("Avaliação registrada com sucesso!");
        } else {
            System.out.println("Mídia não encontrada.");
        }

        // Voltar ao menu do cliente
        try {
            Thread.sleep(4000); // Aguardar 4 segundos antes de voltar ao menu
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        exibirMenuCliente(logado);
    }

    private static void adicionarListaFutura(Midia retorno) {
        System.out.println("== Adicionar Mídia Futura ==");
        String tipoMidia;
        System.out.println("Digite o tipo de mídia (f para filme ou s para série):");
        tipoMidia = scanner.nextLine();

        String arquivoCSV;
        if (tipoMidia.equalsIgnoreCase("f")) {
            arquivoCSV = FILMES_CSV;
        } else if (tipoMidia.equalsIgnoreCase("s")) {
            arquivoCSV = SERIES_CSV;
        } else {
            System.out.println("Tipo de mídia inválido.");
            return;
        }

        System.out.print("Digite o ID da mídia: ");
        int idMedia = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        retorno = plataforma.filtrarPorId(idMedia, arquivoCSV, tipoMidia);
        Cliente cliente = new Cliente();
        // plataforma.registrarAudiencia(idMedia);
        System.out.println("Mídia adicionada com sucesso.");

    }

    private static void cadastrarCliente() throws AuthenticationException, IOException {
        System.out.println("Digite o login do cliente:");
        String idCliente = scanner.nextLine();

        System.out.println("Digite o nome de usuário do cliente:");
        String nomeUsuario = scanner.nextLine();

        System.out.println("Digite a senha do cliente:");
        String senha = scanner.nextLine();

        plataforma.adicionarCliente(nomeUsuario, idCliente, senha);
        System.out.println("Cliente adicionado com sucesso!");

    }

    private static Cliente fazerLogin() throws IOException, AuthenticationException {
        limparTela();

        System.out.println("== Login ==");

        System.out.print("Login: ");
        String idCliente = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        return plataforma.login(idCliente, senha);
    }

    private static void cadastrarMidia() throws IOException {

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Escolha a mídia:");
        System.out.println("1. Adicionar Série");
        System.out.println("2. Adicionar Filme");

        System.out.print("Digite a opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner

        switch (opcao) {
            case 1:
                System.out.println("Digite o ID da série:");
                int id = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do scanner

                System.out.println("Digite o nome da série:");
                String nome = scanner.nextLine();

                System.out.println("Digite a data de lançamento no formato dd/MM/yyyy:");
                LocalDate dataLancamento = LocalDate.parse(scanner.nextLine(), dateFormatter);

                System.out.println("Digite o número de episódios:");
                int qtdEpisodios = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do scanner

                Serie serie = new Serie(id, nome, dataLancamento, qtdEpisodios);
                plataforma.adicionarSerie(serie);
                System.out.println("Série adicionada com sucesso!");
                break;

            case 2:
                System.out.println("Digite o ID do filme:");
                int idFilme = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do scanner

                System.out.println("Digite o nome do filme:");
                String nomeFilme = scanner.nextLine();

                System.out.println("Digite a data de lançamento no formato dd/MM/yyyy:");
                LocalDate dataLancamentoFilme = LocalDate.parse(scanner.nextLine(), dateFormatter);

                System.out.println("Digite a duração do filme:");
                int duracao = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do scanner

                Filme filme = new Filme(idFilme, nomeFilme, dataLancamentoFilme, duracao);
                plataforma.adicionarFilme(filme);
                System.out.println("Filme adicionado com sucesso!");

                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }

    private static void modoRelatorio() throws IOException {
        int opcao = -1;

        System.out.println("=========== Relatórios ===============");
        System.out.println("Selecione a opção que deseja ver : ");
        System.out.println("1 - Qual cliente assistiu mais mídias ");
        System.out.println("2 - Qual cliente possui mais avaliações ");
        System.out.println("3 - Qual a porcentagem de clientes com pelo menos 15 avaliações ");
        System.out.println("4 - Quais as 10 mídias que foram mais vistas ");
        System.out.println("5 - Quais as 10 mídias que foram mais avaliadas ");
        System.out.println("6 - Quais as 10 mídias que foram mais vistas em cada gênero ");
        System.out.println("7 - Quais as 10 mídias que foram mais avaliadas em cada gênero ");
        System.out.println("0 - Voltar");

        System.out.print("Qual opção deseja ver : ");
        opcao = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner

        switch (opcao) {
            case 1:
                System.out.println(plataforma.ClienteViuMaisMidias());
                break;
            case 2:
                System.out.println(plataforma.ClientePossuiMaisAvaliacao());
                break;
            case 3:
                System.out.println(plataforma.PorcentagemCliente15Avaliacoes());
                break;
            case 4:
                System.out.println(plataforma.MidiasMaisVistas());
                break;
            case 5:
                System.out.println(plataforma.MidiasMaisAvaliadas());
                break;
            case 6:
                System.out.print("Qual gênero você deseja? ");
                String genero = scanner.nextLine();
                System.out.println(plataforma.MidiasMaisVistasPorGenero(genero));
                break;
            case 7:
                System.out.print("Qual gênero você deseja? ");
                genero = scanner.nextLine();
                System.out.println(plataforma.MidiasMaisAvaliadasPorGenero(genero));
                break;
            case 0:
                break;
            default:
                System.out.println("Opção inválida!");
                break;

        }
    }
}
