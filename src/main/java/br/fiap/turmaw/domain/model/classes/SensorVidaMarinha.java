package main.java.br.fiap.turmaw.domain.model.classes;

public class SensorVidaMarinha extends Sensor {

    // Construtor
    public SensorVidaMarinha() {
        super("Vida marinha");
        super.setValor((int) (Math.random() * 5));
    }

    // Método de sobrescrita verifica se há presença de vida marinha com base no valor do sensor.
    @Override
    public String ColetarDados() {
        if (this.getValor() > 0) {
            return "Presença de vida marinha: Sim";
        } else {
            return "Presença de vida marinha: Não";
        }

    }

    public void AtualizarValor() {
        super.setValor((int) (Math.random() * 5));
    }
}
