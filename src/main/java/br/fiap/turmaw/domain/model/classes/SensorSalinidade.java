package main.java.br.fiap.turmaw.domain.model.classes;

// Classe SensorSalinidade
public class SensorSalinidade extends Sensor {

    // Construtor
    public SensorSalinidade() {
        super("Salinidade");
        super.setValor((int) (Math.random() * 40) + 1);
    }

    // Método de sobrescrita retorna uma string com o valor da salinidade coletada pelo sensor.
    @Override
    public String ColetarDados() {
        return "Salinidade: " + this.getValor() + "ppt";
    }

    @Override
    public void AtualizarValor() {
        super.setValor((int) (Math.random() * 40) + 1);
    }
}
