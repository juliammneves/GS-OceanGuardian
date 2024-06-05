import main.java.br.fiap.turmaw.domain.model.classes.OceanGuardian;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        OceanGuardian oceanGuardian = new OceanGuardian();

        Scanner sc = new Scanner(System.in);
        int opcao;
        do{
            System.out.println("\n--------- Menu principal ---------");
            System.out.println("1. Adicionar Sentinel");
            System.out.println("2. Coletar dados");
            System.out.println("3. Gerar relatório de análise");
            System.out.println("4. Verificar alertas");
            System.out.println("5. Verificar status dos Sentinels");
            System.out.println("6. Alterar status do sentinel");
            System.out.println("0. Sair");
            System.out.println("----------------------------------");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            switch (opcao){
                case 0:
                    System.out.println("\n\nVocê escolheu sair.");
                    break;
                case 1:
                    Scanner sc2 = new Scanner(System.in);
                    System.out.println("\n\n---- Adicionar Sentinel ----");
                    System.out.print("- Localização: ");
                    String localizacao = sc2.nextLine();

                    System.out.print("- Status: \n  1. Ativo\n  2. Inativo\n  3. Em manutenção\n  Selecione a opção desejada: ");
                    int status = sc2.nextInt();
                    sc2.nextLine();

                    System.out.print("- Sensores: \n  1. Temperatura\n  2. Salinidade\n  3. Poluição\n  4. Vida marinha\n  5. Todos" +
                            "\n  Selecione a opção desejada (Para adicionar mais de um separe os números com vírgula): ");
                    String sensores = sc2.nextLine();
                    System.out.println();

                    oceanGuardian.AddSentinel(localizacao, status, sensores);
                    break;
                case 2:
                    System.out.println("\n\n---- Dados coletados ----");
                    oceanGuardian.ColetarDados();
                    break;
                case 3:
                    System.out.println("\n\n---- Relatório de Análise ----");
                    oceanGuardian.GerarRelatorioAnalise();
                    break;
                case 4:
                    System.out.println("\n\n---- Alertas ----");
                    oceanGuardian.VerificarAlertas();
                    break;
                case 5:
                    System.out.println("\n\n---- Status dos Sentinels ----");
                    oceanGuardian.VerificarStatusSentinels();
                    break;
                case 6:
                    System.out.println("\n\n---- Alterar status ----");
                    oceanGuardian.AlterarStatusSentinel();
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }while(opcao!=0);

    }
}
