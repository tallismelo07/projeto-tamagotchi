package application;

public class RelogioDoJogo {
    private int hora;
    private int dia;
    private static final int HORA_INICIO = 8;
    private static final int HORA_DIA = 6;
    private static final int HORA_NOITE = 18;

    public RelogioDoJogo() {
        this.hora = HORA_INICIO;
        this.dia = 1;
    }

    public void avancarHoras(int horas) {
        hora += horas;
        while (hora >= 24) {
            hora -= 24;
            dia++;
            if (hora == 0) {
                EventosAleatorios.gerarEventoClimatico(dia, hora);
            }
        }
    }

    public int getHora() {
        return hora;
    }

    public int getDia() {
        return dia;
    }

    public boolean isDia() {
        return hora >= HORA_DIA && hora < HORA_NOITE;
    }

    public boolean isNoite() {
        return !isDia();
    }

    @Override
    public String toString() {
        String periodo = isDia() ? "🌞 Dia" : "🌙 Noite";
        return String.format("%s - Dia %d, %02d:00", periodo, dia, hora);
    }
}
