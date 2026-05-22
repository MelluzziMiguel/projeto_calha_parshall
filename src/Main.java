import java.util.Scanner;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        float consumo;
        float quantidadeDia;
        float quantidadeSeg;
        float quantidadeL;
        double altura;
        double alturaE;
        double porcentagem = 0;
        int posicao = 0;
        int posicao2 = 0;
        int populacao;
        BigDecimal valor;

        String[] polegadas = {
                "3'",
                "6'",
                "9'",
                "1",
                "5.5",
                "2",
                "3",
                "4",
                "5"
        };
        double[] vazoesMaximas = {
                53.8,
                110.4,
                251.9,
                455.6,
                696.2,
                936.7,
                1426.0,
                1921.0,
                2422.0
        };

        double[] k = {
                0.1771,
                0.3812,
                0.5354,
                0.6909,
                1.0560,
                1.4290,
                2.1640,
                2.9630,
                3.7320
        };

        double[] n = {
                1.5407,
                1.5300,
                1.5300,
                1.5220,
                1.5380,
                1.5500,
                1.5666,
                1.5738,
                1.5870
        };

        String[] coluna1 = {
                "1'",
                "3'",
                "6'",
                "9'",
                "1",
                "1.5",
                "2",
                "3",
                "4",
                "5",
                "6",
                "7",
                "8",
                "10"
        };

        double[] colunaE = {
                22.9,
                38.1,
                45.7,
                61.0,
                91.5,
                91.5,
                91.5,
                91.5,
                91.5,
                91.5,
                91.5,
                91.5,
                91.5,
                122.0
        };


        System.out.println("Insira a população (P)");
        populacao = scan.nextInt();

        System.out.println("Insira o consumo (q)");
        consumo = scan.nextFloat();

            quantidadeDia = populacao * consumo;

            quantidadeSeg = quantidadeDia / 86400;

            valor = new BigDecimal(quantidadeSeg).setScale(2, RoundingMode.HALF_UP);

            System.out.println("Quantidade por segundo: " + valor);

            for (int i = 0; i < vazoesMaximas.length; i++) {
                if (quantidadeSeg < vazoesMaximas[i]) {
                    posicao = i;
                    break;
                }
            }

            while (true) {

            quantidadeL = Math.round((quantidadeSeg / 1000f) * 100f) / 100f;

            altura = Math.pow((quantidadeL / k[posicao]), (1 / n[posicao]));

            altura = Math.floor(altura * 100) / 100;

            System.out.println("Altura H: " +altura);

            for (int i = 0; i < coluna1.length; i++) {
                if (polegadas[posicao].equals(coluna1[i])) {
                    posicao2 = i;
                    break;
                }
            }

            alturaE = colunaE[posicao2];
            System.out.println("Altura E: " + alturaE);

            porcentagem = (double) (altura * 10000) / alturaE;
            System.out.println("Porcentagem: " + Math.round(porcentagem) + "%");

                if (porcentagem <= 70) {
                    break;
                }

                posicao++;

                if (posicao >= polegadas.length) {
                    System.out.println("Nenhuma calha atende.");
                    break;
                }
            }

        System.out.println("Calha escolhida: " + polegadas[posicao]);
    }
}