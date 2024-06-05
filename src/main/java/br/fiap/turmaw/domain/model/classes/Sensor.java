package main.java.br.fiap.turmaw.domain.model.classes;

import java.util.Random;

public abstract class Sensor {
    private String tipo;
    private int valor;

    // Getters e Setters
    public void setValor(int valor) {
        this.valor = valor;
    }
    public int getValor() {
        return valor;
    }
    public String getTipo() {
        return tipo;
    }

    // Construtor
    public Sensor( String tipo) {
        this.tipo = tipo;
    }

    // Método abstrato que deve ser implementado por subclasses para coletar dados específicos do sensor.
    public abstract String ColetarDados();

    // Transmite os dados coletados pelo sensor, exibindo uma mensagem indicando o tipo de sensor.
    public abstract void AtualizarValor();
}

