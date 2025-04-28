package application;

import java.util.Random;

public class EventosAleatorios {
    private static final Random random = new Random();
    private static boolean chuva = false;
    private static int horaUltimaChuva = -1;
    private static int diasSemChuva = 0;

    public static void gerarEventoClimatico(int horaAtual, int diaAtual) {
        if (diaAtual > horaUltimaChuva / 24) {
            chuva = random.nextInt(100) < 20;
            if (chuva) {
                horaUltimaChuva = horaAtual;
                diasSemChuva = 0;
            }
        } else if (horaAtual % 24 == 0 && diasSemChuva >= 2) {
            chuva = random.nextInt(100) < 20;
            if (chuva) {
                horaUltimaChuva = horaAtual;
                diasSemChuva = 0;
            }
        }

        if (chuva && horaAtual - horaUltimaChuva < 3) {
            chuva = true;
        } else {
            chuva = false;
        }

        diasSemChuva++;
    }

    public static boolean houveChuva() {
        return chuva;
    }

    public static boolean comidaVenenosa() {
        return random.nextInt(100) < 10;
    }

    public static boolean brincarNaChuvaEPegarResfriado(int horaAtual) {
        return houveChuva() && random.nextInt(100) < 50 && horaAtual >= 12 && horaAtual <= 18; // Durante a tarde
    }

    public static boolean houveTempestade() {
        return true;
    }
}
