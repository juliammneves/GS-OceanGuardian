package main.java.br.fiap.turmaw.domain.model.classes;

// Classe SensorPoluicao
public class SensorPoluicao extends Sensor {
    public SensorPoluicao() {
        super("Poluição");
        super.setValor((int) (Math.random() * 1500));
    }

    // Método de sobrescrita retorna uma string com o valor da poluição coletada pelo sensor.
    @Override
    public String ColetarDados() {
        return "Poluição de microplástico por m³: " +this.getValor() + " particulas";
    }

    @Override
    public void AtualizarValor() {
        super.setValor((int) (Math.random() * 1500));
    }
}
