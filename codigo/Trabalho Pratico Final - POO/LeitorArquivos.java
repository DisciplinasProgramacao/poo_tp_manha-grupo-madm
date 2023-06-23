package POO.trabalhopraticoPOO;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Classe responsável por ler arquivos CSV e converter os dados em objetos do tipo especificado pela implementação de IStringConverter.
 *
 * @param <T> O tipo de objeto que implementa a interface IStringConverter.
 */
public class LeitorArquivos<T extends ConversorParaString> {
    private static String line;

    /**
     * Lê um arquivo CSV e converte os dados em um mapa de objetos do tipo especificado pela implementação de IStringConverter.
     *
     * @param dado        A implementação de IStringConverter para a conversão dos dados.
     * @param arquivoCSV  O caminho do arquivo CSV a ser lido.
     * @return Um mapa de objetos do tipo especificado pela implementação de IStringConverter, onde a chave é obtida através do método getChave().
     */
    public Map<String, T> lerCSV(ConversorParaString dado, String arquivoCSV) {
        Map<String, T> dadosMap = new HashMap<>(1000000);
        try (BufferedReader buffer = new BufferedReader(new FileReader(arquivoCSV))) {
            while ((line = buffer.readLine()) != null) {
                ConversorParaString objeto = dado.converterParaObjeto(line);
                dadosMap.put(objeto.getChave(), (T) Utilitario.converterParaTipo(objeto, dado.getClass()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dadosMap;
    }

    /**
     * Lê um arquivo CSV e retorna os dados como uma lista de strings.
     *
     * @param arquivoCSV O caminho do arquivo CSV a ser lido.
     * @return Uma lista de strings contendo os dados lidos do arquivo CSV.
     */
    public static List<String> lerCSV(String arquivoCSV) {
        List<String> dadosList = new ArrayList<>(1000000);
        try (BufferedReader buffer = new BufferedReader(new FileReader(arquivoCSV))) {
            while ((line = buffer.readLine()) != null) {
                dadosList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dadosList;
    }
}