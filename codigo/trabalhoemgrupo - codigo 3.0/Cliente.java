package POO.trabalhoemgrupo;

import java.util.List;

import java.util.ArrayList;

public class Cliente {
    //início dos atributos
    private String nomeUsuario;
    private List<Serie> listaFutura= new ArrayList<>();
    private List<Serie> listaJaViu= new ArrayList<>();
    //fim dos atributos
    /**
     * inicializador cliente que configura nome de usuário, listas futuras, e listas já vistas
     * @param nomeUsuario
     */
    public Cliente(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
        this.listaFutura = new ArrayList<>();
        this.listaJaViu = new ArrayList<>();
    }
    /**
     * método para adicionar série na lista de séries para ver
     * @param serie
     */
    public void addSerie(Serie serie) {
        listaFutura.add(serie);
    }
    /**
     * método para retirar série
     * @param nomeSerie
     */
    public void retirarSerie(String nomeSerie) {
        listaFutura.remove(nomeSerie);
        listaJaViu.remove(nomeSerie);
    }
    /**
     * método para buscar série por nome
     * @param nome
     * @return o resultado
     */
    public List<Serie> buscarSeriePorNome(String nome) {
        List<Serie> resultado = new ArrayList<>();
        for (Serie serie : listaFutura) {
            if (serie.nome.equals(nome)) {
                resultado.add(serie);
            }
        }
        for (Serie serie : listaJaViu) {
            if (serie.nome.equals(nome)) {
                resultado.add(serie);
            }
        }
        return resultado;
    }
    /**
     * método de filtro de série por gêneros
     * @param genero
     * @return os resultados filtrados
     */
    public List<Serie> buscarSeriePorGenero(String genero) {
        List<Serie> resultado = new ArrayList<>();
        for (Serie serie : listaFutura) {
            if (serie.genero.equals(genero)) {
                resultado.add(serie);
            }
        }
        for (Serie serie : listaJaViu) {
            if (serie.genero.equals(genero)) {
                resultado.add(serie);
            }
        }
        return resultado;
    }
    /**
     * método de filtro de série por idioma
     * @param idioma
     * @return os resultados filtrados
     */
    public List<Serie> buscarSeriePorIdioma(String idioma) {
        List<Serie> resultado = new ArrayList<>();
        for (Serie serie : listaFutura) {
            if (serie.idioma.equals(idioma)) {
                resultado.add(serie);
            }
        }
        for (Serie serie : listaJaViu) {
            if (serie.idioma.equals(idioma)) {
                resultado.add(serie);
            }
        }
        return resultado;
    }
    /**
     * método para registrar visualização, remove da lista futura, e adiciona a lista já vista
     * @param serie
     */
    public void registrarVisualizacao(Serie serie) {
        serie.registrarVisualizacao();
        listaFutura.remove(serie.nome);
        listaJaViu.add(serie);
    }

     // método toString
    @Override
    public String toString() {
        return "Cliente [nomeUsuario=" + nomeUsuario + ", listaFutura=" + listaFutura + ", listaJaViu=" + listaJaViu
                + "]";
    }
}
