package main.java.br.fiap.turmaw.domain.model.classes;

import java.util.ArrayList;
import java.util.List;

public class Sentinel {
    private int SentinelId;
    private String localizacao;
    private List<Sensor> sensores;
    private int status;

    // Getters e Setters
    public int getSentinelId() {
        return SentinelId;
    }
    public String getLocalizacao() {
        return localizacao;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }

    // Construtor
    public Sentinel(int sentinelId, String localizacao, int status) {
        SentinelId = sentinelId;
        this.localizacao = localizacao;
        this.status = status;
        this.sensores = new ArrayList<>();
    }

    // Método para adicionar um sensor individual
    public void AddSensor(Sensor sensor) {
        sensores.add(sensor);
        System.out.println("Sensor de "+ sensor.getTipo() + " adicionado.");
    }

    // Método sobrecarregado para adicionar uma lista de sensores
    public void AddSensor(List<Sensor> sensores) {
        this.sensores.addAll(sensores);
        System.out.println("Todos os sensores foram adicionados.");
    }

    // Gera uma análise coletando dados de cada sensor e retornando-os como uma string.
    public String AnalisarDados() {
        StringBuilder analysis = new StringBuilder();
        for (Sensor sensor : sensores) {
            analysis.append(sensor.ColetarDados()).append("\n");
        }
        return analysis.toString();
    }

    // Gera uma análise dos dados dos sensores, calculando as médias de temperatura, salinidade, poluição, e a presença de vida marinha.
    public String GerarAnalise() {
        StringBuilder analise = new StringBuilder();
        int totalTemperatura = 0;
        int totalSalinidade = 0;
        int totalPoluicao = 0;
        int totalVidaMarinha = 0;
        int countTemperatura = 0;
        int countSalinidade = 0;
        int countPoluicao = 0;
        int countVidaMarinha = 0;

        for (Sensor sensor : sensores) {
            if (sensor instanceof SensorTemperatura) {
                totalTemperatura += sensor.getValor();
                countTemperatura++;
            } else if (sensor instanceof SensorSalinidade) {
                totalSalinidade += sensor.getValor();
                countSalinidade++;
            } else if (sensor instanceof SensorPoluicao) {
                totalPoluicao += sensor.getValor();
                countPoluicao++;
            } else if (sensor instanceof SensorVidaMarinha) {
                totalVidaMarinha += sensor.getValor();
                countVidaMarinha++;
            }
        }

        if (countTemperatura > 0) {
            analise.append("Temperatura média: ").append(totalTemperatura / countTemperatura).append("°C\n");
        }
        if (countSalinidade > 0) {
            analise.append("Salinidade média: ").append(totalSalinidade / countSalinidade).append("ppt\n");
        }
        if (countPoluicao > 0) {
            analise.append("Poluição média: ").append(totalPoluicao / countPoluicao).append(" partículas por m³\n");
        }
        if (countVidaMarinha > 0) {
            analise.append("Presença de vida marinha detectada em ").append(totalVidaMarinha).append(" de ").append(countVidaMarinha).append(" amostras\n");
        }

        return analise.toString();
    }

    // Verifica as condições dos dados coletados por cada sensor e exibe alertas caso algum valor esteja fora dos parâmetros normais.
    public String AlertaCondicao() {
        StringBuilder alertas = new StringBuilder();

        for (Sensor sensor : sensores) {
            switch (sensor.getTipo()){
                case "Temperatura":
                    if (sensor.getValor() > 30){
                        alertas.append("Alerta: Temperatura alta! ").append(sensor.getValor()).append("°C detectados.\n");
                    }
                    break;
                case  "Salinidade":
                    if (sensor.getValor() > 30 || sensor.getValor() < 10 ){
                        alertas.append("Alerta: Salinidade anormal! ").append(sensor.getValor()).append("ppt detectados.\n");
                    }
                    break;
                case  "Poluição":
                    if (sensor.getValor() > 900){
                        alertas.append("Alerta: Poluição de microplastico por m³ alta! ").append(sensor.getValor()).append("particulas detectadas.\n");
                    }
                    break;
                case  "Vida marinha":
                    if (sensor.getValor() < 1){
                        alertas.append("Alerta: Não foi possível detectar presença de vida marinha na área.\n");
                    }
                    break;
                default:
                    break;
            }
        }
        return alertas.toString();
    }

    // Verifica o status atual da Sentinela. Retorna uma string indicando se o status está "Ativo", "Inativo" ou "Em manutenção".
    public String VerificarStatus() {
        switch (status) {
            case 1:
                return "Ativo";
            case 2:
                return "Inativo";
            case 3:
                return "Em manutenção";
            default:
                throw new IllegalArgumentException("Status inválido: " + status);
        }
    }
}
