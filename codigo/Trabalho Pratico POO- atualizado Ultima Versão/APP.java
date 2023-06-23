package POO.trabalhopraticoPOO;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class APP {
    public static Scanner scanner = new Scanner(System.in);
    private static Streaming plataforma;
    private static Cliente cliente;
    public static void limparTela() {

        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void timer(int segundos) throws Exception {
        TimeUnit.SECONDS.sleep(segundos);
    }

    public static void main(String[] args) throws Exception {
        limparTela();
        plataforma = new Streaming();
        int operacao;
        fazerLogin();

        limparTela();
        do {
            operacao = exibirMenuCliente();
            OpçoesExecutadas(operacao);
        } while (operacao != 0);
    }


    

    private static void OpçoesExecutadas(int opcao) throws IOException {

        switch (opcao) {
            case 1:
                addMidiaAlista(true);
                break;

            case 2:
                addMidiaAlista(true);
                break;

            case 3:
                buscarMidiaNaLista(true);
                break;

            case 4:
                buscarMidiaNaLista(false);
                break;

            case 5:
                limparTela();
                avaliarMidia();
                break;

            case 6:
                modoRelatorio();
                break;

            case 7:
                exibirListaMidia(true);
                break;

            case 8:
                exibirListaMidia(false);
                break;
                case 9:
                buscarMidias();
                break;
                case 0:
                System.out.println("Obrigado por acessar o Prime Streaming!");
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
                //resultado = plataforma.filtrarPorNome(valor);
                break;
            default:
                break;}
        }

    private static void avaliarMidia() {
        System.out.println("Informe o nome da mídia: ");
        String midia = scanner.nextLine();
        try {
            Midia midiaSelecionada = plataforma.buscarMidiaNaLista(midia, true);
            plataforma.avaliar(midiaSelecionada);
            System.out.println("Mídia avaliada com sucesso!");

        } catch (Exception e) {
            System.out.printf("Erro: %s", e.getMessage());
        }
    }

    private static void buscarMidiaNaLista(boolean ehAssistida) {
        System.out.println("Informe o nome da mídia: ");
        String midia = scanner.nextLine();
        try {
            Midia midiaSelecionada = plataforma.buscarMidiaNaLista(midia, ehAssistida);
            System.out.printf("Resultado: %s \n", midiaSelecionada.toString());

        } catch (Exception e) {
            System.out.printf("Erro: %s", e.getMessage());
        }
    }


    private static void addMidiaAlista(boolean jaAssistida) {
        System.out.println("Informe o nome da mídia: ");
        String midia = scanner.nextLine();
        try {
            plataforma.adicionarMidiaALista(midia, jaAssistida);

            System.out.println("Operação realizada com sucesso!");
        } catch (Exception e) {
            System.out.printf("Erro", e.getMessage());
        }
    }

    private static int exibirMenuCliente() {
        try {
            int op = -1;
            do {
                System.out.println("Menu do Cliente");
                System.out.println("1. Adicionar mídia assistida");
                System.out.println("2. Adicionar mídia à lista de interesses");
                System.out.println("3. Buscar mídia assistida");
                System.out.println("4. Busca mídia na lista de interesse");
                System.out.println("5. Avaliar mídia");
                System.out.println("6. Menu de Relatórios");
                System.out.println("7. Exibir midias assistidas");
                System.out.println("8. Exibir midias que interessou");
                System.out.println("9. Buscar mídia(apenas para ver)");
                System.out.println("0. Sair");
                System.out.print("Escolha uma opção:");
                op = Integer.parseInt(scanner.nextLine());

                if (op < 0 || op > 9) {
                    limparTela();
                    System.out.println("Opção informada não existe. Tente novamente");
                    timer(2);
                }

            } while (op < 0 || op > 9);

            return op;
        } catch (Exception e) {
            System.out.println("A opção não existe");
            return -1;
        }
    }

    public static void fazerLogin() throws Exception {
        limparTela();
        System.out.println("== Menu Principal ==");
        System.out.println("Informe o login: ");
        String login = scanner.nextLine();
        System.out.println("Informe a senha: ");
        String senha = scanner.nextLine();

        if (plataforma.login(login, senha)) {
            System.out.println("Login realizado com sucesso!");
        } else {
            limparTela();
            System.out.println("Usuário ou senha inválidos. Tente novamente.");
            timer(2);
            fazerLogin();
        }
    }

    private static void exibirListaMidia(boolean listaAssistida) {
        List<String> midias = plataforma.exibirListaMidia(listaAssistida);
        if (listaAssistida) {
            System.out.println("Midias assitidas: ");
        } else {
            System.out.println("Midias para assistir futuramente: ");
        }
        midias.forEach(midia -> System.out.printf("%s\n", midia));
    }
    private static void modoRelatorio() throws IOException {
        limparTela();
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
               // System.out.print("Qual gênero você deseja? ");
                //String genero = scanner.nextLine();
                //System.out.println(plataforma.MidiasMaisVistasPorGenero(genero));
                break;
            case 7:
                //System.out.print("Qual gênero você deseja? ");
               // genero = scanner.nextLine();
                //System.out.println(plataforma.MidiasMaisAvaliadasPorGenero(genero));
                break;
            case 0:
                break;
            default:
                System.out.println("Opção inválida!");
                break;

        }
    }
    
}