package POO.trabalhoemgrupo;

import java.util.Scanner;
import java.io.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //início dos atributos
        Scanner entrada= new Scanner(System.in);
        char a;
        //List<Cliente> clientes = new ArrayList<>();
        //fim dos atributos
        System.out.println("Qual banco de arquivos você quer ver? 1-séries 2-espectadores 3-audiência");
        //scanner para escolha de qual banco de dados deseja acessar
        a=entrada.next().charAt(0);

    //switch para escolha de arquivos    
    switch (a) {
        case '1':

    
    try {
        Scanner scanner = new Scanner(new File("C:/Users/Diogo/Desktop/vscode/.vscode/POO/trabalhoemgrupo/POO_Series.csv"));

        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            System.out.println(linha);
        }

        scanner.close();

    } catch (FileNotFoundException e) {
        System.out.println("Arquivo não encontrado!");
        e.printStackTrace();
    } 
    break;
    case '2':
    try {
        Scanner scanner = new Scanner(new File("C:/Users/Diogo/Desktop/vscode/.vscode/POO/trabalhoemgrupo/POO_Espectadores.csv"));

        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            System.out.println(linha);
        }

        scanner.close();

    } catch (FileNotFoundException e) {
        System.out.println("Arquivo não encontrado!");
        e.printStackTrace();
    } 
    break;
case '3':
try {
    Scanner scanner = new Scanner(new File("C:/Users/Diogo/Desktop/vscode/.vscode/POO/trabalhoemgrupo/POO_Audiencia.csv"));

    while (scanner.hasNextLine()) {
        String linha = scanner.nextLine();
        System.out.println(linha);
    }

    scanner.close();

} catch (FileNotFoundException e) {
    System.out.println("Arquivo não encontrado!");
    e.printStackTrace();
} 
break;
} }
}
