package main.java.br.fiap.turmaw.domain.model.classes;

// Classe SensorTemperatura
public class SensorTemperatura extends Sensor {

    // Construtor
    public SensorTemperatura() {
        super("Temperatura");
        super.setValor((int) (Math.random() * 40) + 1);
    }

    // Método de sobrescrita retorna uma string com o valor da temperatura coletada pelo sensor.
    @Override
    public String ColetarDados() {
        return "Temperatura: " +this.getValor() + "°C";
    }

    @Override
    public void AtualizarValor() {
        super.setValor((int) (Math.random() * 40) + 1);
    }
}
