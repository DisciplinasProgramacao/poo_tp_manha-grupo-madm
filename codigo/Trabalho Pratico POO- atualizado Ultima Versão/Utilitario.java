package POO.trabalhopraticoPOO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Classe utilitária contendo métodos estáticos para conversão e manipulação de dados.
 */
public class Utilitario {

    /**
     * Converte uma string de data no formato "dd/MM/yyyy" para um objeto LocalDate.
     *
     * @param dataString A string contendo a data a ser convertida.
     * @return O objeto LocalDate correspondente à data convertida.
     */
    public static LocalDate converteParaData(String dataString) {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(dataString, formatador);
    }

    /**
     * Converte um objeto para um tipo específico, verificando sua compatibilidade.
     *
     * @param objeto O objeto a ser convertido.
     * @param tipo   O tipo para o qual o objeto será convertido.
     * @param <T>    O tipo genérico de destino da conversão.
     * @return O objeto convertido para o tipo desejado.
     * @throws IllegalArgumentException se o objeto não for compatível com o tipo desejado.
     */
    public static <T> T converterParaTipo(ConversorParaString objeto, Class<T> tipo) {
        if (tipo.isAssignableFrom(objeto.getClass())) {
            return tipo.cast(objeto);
        } else {
            throw new IllegalArgumentException("Não é compatível .");
        }
    }
    
}
