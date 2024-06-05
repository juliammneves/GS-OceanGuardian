package main.java.br.fiap.turmaw.domain.model.classes;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class OceanGuardian {
    private List<Sentinel> sentinels;

    // Construtor
    public OceanGuardian() {
        this.sentinels = new ArrayList<>();
    }

    // Método para adicionar uma sentinela a lista sentinels
    public void AddSentinel(String localizacao, int status, String sensores){
        int id = sentinels.size() + 1;

        Sentinel sentinel = new Sentinel(id, localizacao, status);
        sentinels.add(sentinel);
        if (sensores.contains(",")) {
            // Tratamento para múltiplos sensores
            String[] sensoresArray = sensores.split(",");
            for (String sensor : sensoresArray) {
                FiltrarSensor(sensor.trim(), sentinel);
            }
        } else {
            // Tratamento para um único sensor
            FiltrarSensor(sensores.trim(), sentinel);
        }
        System.out.println("Sentinel adicionado ao sistema.\n");
    }

    // Método para filtrar os sensores que serão adicionados
    private static void FiltrarSensor(String sensor, Sentinel sentinel) {
        switch (sensor) {
            case "1":
                sentinel.AddSensor(new SensorTemperatura());
                break;
            case "2":
                sentinel.AddSensor(new SensorSalinidade());
                break;
            case "3":
                sentinel.AddSensor(new SensorPoluicao());
                break;
            case "4":
                sentinel.AddSensor(new SensorVidaMarinha());
                break;
            case "5":
                List<Sensor> todosSensores = new ArrayList<>();
                todosSensores.add(new SensorTemperatura());
                todosSensores.add(new SensorSalinidade());
                todosSensores.add(new SensorPoluicao());
                todosSensores.add(new SensorVidaMarinha());
                sentinel.AddSensor(todosSensores);
                break;
            default:
                System.out.println("Opção de sensor inválida.");
                break;
        }
    }

    // Coleta e imprime os dados de todos os sentinelas, incluindo data e hora, ID, localização e status do sentinel.
    public void ColetarDados() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String dateTime = formatter.format(date);
        System.out.println("[" + dateTime + "]");

        for (Sentinel sentinel : sentinels) {
            System.out.println("- ID: " + sentinel.getSentinelId() + " / Localização: " + sentinel.getLocalizacao());
            if (sentinel.getStatus() == 2){
                System.out.println("Esta sentinela está inativa.");
            } else if (sentinel.getStatus() == 3) {
                System.out.println("Esta sentinela está em manutenção.");
            } else {
                System.out.println(sentinel.AnalisarDados()); ;
                System.out.println();
            }
        }
    }

    // Gera e imprime um relatório de análise para todos os sentinelas, incluindo ID, localização e status.
    public void GerarRelatorioAnalise() {
        for (Sentinel sentinel : sentinels) {
            System.out.println("- ID: " + sentinel.getSentinelId() + " / Localização: " + sentinel.getLocalizacao());
            if (sentinel.getStatus() == 2){
                System.out.println("Esta sentinela está inativa.");
            } else if (sentinel.getStatus() == 3) {
                System.out.println("Esta sentinela está em manutenção.");
            } else {
                String analise = sentinel.GerarAnalise();
                System.out.println(analise);
            }
        }
    }

    // Método para verificar o status dos sentinels (ativo, inativo ou em manutenção)
    public void VerificarStatusSentinels() {
        for (Sentinel sentinel : sentinels) {
            switch (sentinel.getStatus()){
                case 1:
                    System.out.println("Sentinel ID: " + sentinel.getSentinelId() + "\n- Status: Ativo\n");
                    break;
                case 2:
                    System.out.println("Sentinel ID: " + sentinel.getSentinelId() + "\n- Status: Inativo\n");
                    break;
                case 3:
                    System.out.println("Sentinel ID: " + sentinel.getSentinelId() + "\n- Status: Em manutenção\n");
                    break;
                default:
                    throw new IllegalArgumentException("Status inválido: " + sentinel.getStatus());
            }
        }
    }

    // Método para alterar o status dos sentinels
    public void AlterarStatusSentinel() {
        if (sentinels.isEmpty()) {
            System.out.println("Nenhuma sentinela disponível.");
            return;
        }

        Scanner sc = new Scanner(System.in);

        System.out.println("Sentinelas disponíveis:");
        for (Sentinel s : sentinels) {
            System.out.println("- ID: " + s.getSentinelId() + ", Localização: " + s.getLocalizacao());
        }

        System.out.print("\nDigite o ID da sentinela que deseja alterar o status: ");
        int selectedId = sc.nextInt();

        Sentinel selectedSentinel = sentinels.stream()
                .filter(s -> s.getSentinelId() == selectedId)
                .findFirst()
                .orElse(null);

        if (selectedSentinel != null) {
            // Solicitar ao usuário que informe o novo status
            System.out.println("\nStatus atual do sentinel (ID: " + selectedId + "): " + selectedSentinel.VerificarStatus());
            System.out.println("\nNovos status disponíveis: \n1. Ativo\n2. Inativo\n3. Em manutenção");
            System.out.print("Digite o novo status: ");
            int novoStatus = sc.nextInt();

            try {
                if (novoStatus < 1 || novoStatus > 3) {
                    throw new IllegalArgumentException("Status inválido: " + novoStatus);
                }
                selectedSentinel.setStatus(novoStatus);
                System.out.println("\nStatus atualizado com sucesso para: " + selectedSentinel.VerificarStatus());
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } else {
            System.out.println("Sentinela não encontrada.");
        }
    }

    // Método para verificar e exibir os alertas de cada sentinel
    public void VerificarAlertas() {
        StringBuilder alerts = new StringBuilder();

        if (sentinels == null || sentinels.isEmpty()) {
            System.out.println("Nenhum sentinel disponível.");
            return;
        }

        for (Sentinel sentinel : sentinels) {
            alerts.append("- Sentinel ID: ").append(sentinel.getSentinelId())
                    .append(" / Localização: ").append(sentinel.getLocalizacao()).append("\n");

            if (sentinel.getStatus() == 2){
                alerts.append("Esta sentinela está inativa.\n");
            } else if (sentinel.getStatus() == 3) {
                alerts.append("Esta sentinela está em manutenção.\n");
            } else {
                String alertasSentinel = sentinel.AlertaCondicao();
                if (alertasSentinel.isEmpty()) {
                    alerts.append("Sem alertas.");
                }
                alerts.append(alertasSentinel).append("\n");
            }
        }

        System.out.println(alerts.toString());
    }
}
