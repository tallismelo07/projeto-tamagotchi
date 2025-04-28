package application;

import java.util.Random;

public class Tamagotchi {
    private String nome;
    private int fome, felicidade, energia, saude, higiene, socializacao, idade;
    private String fase;
    private boolean doente;
    private boolean vivo;
    private Random random;
    private boolean resfriado = false;
    private RelogioDoJogo relogio = new RelogioDoJogo();

    public Tamagotchi(String nome) {
        this.nome = nome;
        this.fome = 50;
        this.felicidade = 50;
        this.energia = 50;
        this.saude = 50;
        this.higiene = 50;
        this.socializacao = 50;
        this.idade = 0;
        this.fase = "Bebê";
        this.doente = false;
        this.vivo = true;
        this.random = new Random();
    }

    public void alimentar() {
        if (!vivo) return;
        fome = Math.max(0, fome - 20);
        higiene = Math.max(0, higiene - 10);
        passarTempo(1);
        System.out.println("--------------------------------");
        System.out.println("Você está alimentando o " + nome);
        System.out.println("FOME: " + this.fome);
        System.out.println("IDADE: " + idade + " FASE: " + fase);
        System.out.println("--------------------------------");
        envelhecer();
    }

    public void brincar() {
        if (!vivo) return;
        felicidade = Math.min(100, felicidade + 20);
        energia = Math.max(0, energia - 15);
        fome = Math.min(100, fome + 10);
        socializacao = Math.min(100, socializacao + 15);

        if (EventosAleatorios.brincarNaChuvaEPegarResfriado(relogio.getHora())) {
            resfriado = true;
            saude = Math.max(0, saude - 20);
            System.out.println(nome + " pegou um resfriado durante a brincadeira na chuva!");
        }

        passarTempo(1);
        System.out.println("--------------------------------");
        System.out.println("Você está brincando com " + nome);
        System.out.println("FELICIDADE: " + this.felicidade);
        System.out.println("IDADE: " + idade + " FASE: " + fase);
        System.out.println("--------------------------------");
        envelhecer();
    }

    public void dormir() {
        if (!vivo) return;
        energia = Math.min(100, energia + 30);
        felicidade = Math.max(0, felicidade - 5);
        passarTempo(2);
        System.out.println("--------------------------------");
        System.out.println("Seu Tamagotchi está dormindo: " + nome);
        System.out.println("ENERGIA: " + this.energia);
        System.out.println("IDADE: " + idade + " FASE: " + fase);
        System.out.println("--------------------------------");
        envelhecer();
    }

    public void tomarBanho() {
        if (!vivo) return;
        higiene = Math.min(100, higiene + 30);
        saude = Math.min(100, saude + 10);
        passarTempo(1);
        System.out.println("--------------------------------");
        System.out.println("Você está dando banho no " + nome);
        System.out.println("HIGIENE: " + this.higiene);
        System.out.println("SAÚDE: " + this.saude);
        System.out.println("IDADE: " + idade + " FASE: " + fase);
        System.out.println("--------------------------------");
        envelhecer();
    }

    public void darMedicamento() {
        if (!vivo) return;
        if (doente || resfriado) {
            saude = Math.min(100, saude + 30);
            doente = false;
            resfriado = false;
        }
        passarTempo(1);
        System.out.println("--------------------------------");
        System.out.println("Você está dando remédio para o " + nome);
        System.out.println("SAÚDE: " + this.saude);
        System.out.println("IDADE: " + idade + " FASE: " + fase);
        System.out.println("--------------------------------");
        envelhecer();
    }

    public void interagir() {
        if (!vivo) return;
        socializacao = Math.min(100, socializacao + 20);
        felicidade = Math.min(100, felicidade + 10);
        passarTempo(1);
        System.out.println("--------------------------------");
        System.out.println("Você está interagindo com o " + nome);
        System.out.println("SOCIALIZAÇÃO: " + this.socializacao);
        System.out.println("FELICIDADE: " + this.felicidade);
        System.out.println("IDADE: " + idade + " FASE: " + fase);
        System.out.println("--------------------------------");
        envelhecer();
    }

    private void passarTempo(int horas) {
        relogio.avancarHoras(horas);
        EventosAleatorios.gerarEventoClimatico(relogio.getHora(), relogio.getDia());


        if (EventosAleatorios.houveChuva()) {
            System.out.println("Está chovendo! " + nome + " pode ficar doente...");
            if (!resfriado && random.nextInt(100) < 30) {
                resfriado = true;
                System.out.println(nome + " pegou um resfriado!");
            }
        }

        if (resfriado) {
            saude = Math.max(0, saude - 5);
            System.out.println(nome + " está resfriado. Saúde atual: " + saude);
        }
    }

    private void envelhecer() {
        idade++;
        definirFase();
        verificarDoenca();
        verificarEventosAleatorios();
        verificarCondicoesDeMorte();
    }

    private void definirFase() {
        if (idade < 5) fase = "Bebê";
        else if (idade < 12) fase = "Criança";
        else if (idade < 18) fase = "Adolescente";
        else if (idade < 60) fase = "Adulto";
        else fase = "Idoso";
    }

    private void verificarDoenca() {
        if (random.nextInt(100) < 10 || higiene < 20) {
            doente = true;
            saude = Math.max(0, saude - 20);
        }
    }

    private void verificarEventosAleatorios() {
        int evento = random.nextInt(100);
        if (evento < 10) {
            energia = Math.max(0, energia - 15);
        } else if (evento < 20) {
            felicidade = Math.max(0, felicidade - 10);
        } else if (evento < 25) {
            fome = Math.min(100, fome + 15);
        }
    }

    private void verificarCondicoesDeMorte() {
        if (fome >= 100 || saude <= 0 || idade >= 100) {
            morrer();
        }
    }

    private void morrer() {
        vivo = false;
        System.out.println(nome + " faleceu por " + causaMorte());
    }

    private String causaMorte() {
        if (fome >= 100) return "fome";
        if (saude <= 0) return "doença";
        return "idade avançada";
    }

    public boolean isVivo() {
        return vivo;
    }

    public int getFome() { return fome; }
    public int getFelicidade() { return felicidade; }
    public int getEnergia() { return energia; }
    public int getSaude() { return saude; }
    public int getHigiene() { return higiene; }
    public int getSocializacao() { return socializacao; }
    public int getIdade() { return idade; }
    public String getFase() { return fase; }
    public String getNome() { return nome; }
    public boolean estaResfriado() { return resfriado; }
    public RelogioDoJogo getRelogio() { return relogio; }
}


