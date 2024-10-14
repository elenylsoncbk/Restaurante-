package oo.Composicao.Desafio.Trabalho2;

import java.util.*;

public class Menu {
    static Scanner entrada = new Scanner(System.in);
    static List<PratoPrincipal> pratoPrincipal = new ArrayList<>();
    static List<Acompanhamento> acompanhamento = new ArrayList<>();
    static List<Salada> salada = new ArrayList<>();
    static final List<Refeicao> refeicoes = new ArrayList<>();
    static final Cardapio cardapio = new Cardapio();
    public static void main(String[] args) {
        // inicializarListas();
        String comand;
        System.out.println("\n   -Inicio do Programa-   ");
        do {
            System.out.println("\n      -MENU-      ");
            System.out.print("""
                    1 - CADATRAR SALADA:
                    2 - CADATRAR PRATO PRINCIPAL:
                    3 - CADATRAR ACOMPANHAMENTO:
                    4 - CADATRAR REFEIÇÃO:
                    5 - PUBLICAR ALIMENTO E REFEIÇÕES CADATRADAS:
                    6 - ADICIONAR REFEIÇAO AO CARDAPIO:
                    7 - EXIBIR CARDAPIO:
                    0 - SAIR:
                    """);
            comand = entrada.nextLine().trim();
            switch (comand) {
                case "1" -> {
                    AdicoinarSalada();
                }
                case "2" -> {
                    AdicoinarPratoPrincipal();
                }
                case "3" -> {
                    AdicionarAcompanhamento();
                }
                case "4" -> {
                    CadrastrarRefeicao();
                }
                case "5" -> {
                    exibirRefeicao();
                }
                case "6" -> {
                    try {
                    adicionarRefeicaoCardapio();
                    }catch (Exception e){
                        System.out.println("REFEIÇÃO INVALIDA");
                        entrada.nextLine();
                    }
                }
                case "7" -> {
                    exibirCardapio();
                }
                case "0" -> {
                }
                default -> {
                    System.out.println("Opção Invalida!");
                    System.out.println("----------------\n");
                }
            }
        } while (!comand.equalsIgnoreCase("0"));

        System.out.println("-----FIM DO PROGRAMA!-----");
    }
    static void AdicoinarPratoPrincipal() {
        System.out.print("DIGITE O PRATO PRINCIPAL: ");
        String tarefa = entrada.nextLine();
        pratoPrincipal.add(new PratoPrincipal(tarefa));
        System.out.println("---ADICIONADO---");
    }
    static void AdicionarAcompanhamento() {
        System.out.print("DIGITE ACOMPANHAMENTO: ");
        String tarefa = entrada.nextLine();
        acompanhamento.add(new Acompanhamento(tarefa));
        System.out.println("---ADICIONADA---");
    }
    static void AdicoinarSalada() {
        System.out.print("DIGITE A SALADA: ");
        String tarefa = entrada.nextLine();
        salada.add(new Salada(tarefa));
        System.out.println("---ADICIONADO---");
    }
    static void CadrastrarRefeicao() {
        System.out.println("\n------- CADASTRAR REFEICAO -------\n");
        if (pratoPrincipal.isEmpty()) {
            System.out.println("- NENHUM PRATO PRINCIPAL CADATRADO!");
            return;
        }
        if (acompanhamento.isEmpty()) {
            System.out.println("- NENHUM ACOMPANHAMENTO CADATRADO!");
            return;
        }
        if (salada.isEmpty()) {
            System.out.println("- NENHUMA SALADA CADASTRADA!");
            return;
        }
        exibirSaladas();
        exibirAcompanhamento();
        exibirPratoPrincipal();

        System.out.println("\nDIGITE A DESCRIÇÃO ");
        var dnome = entrada.nextLine();
        int idePraPrin = -1;
        do {
            System.out.println("\nSELECIONE O PRATO PRINCIPAL:");
            idePraPrin = entrada.nextInt();

            if (idePraPrin < 1 || idePraPrin > pratoPrincipal.size()) {
                System.out.println("INDICE INVALIDO!");
            }
        } while (idePraPrin < 1 || idePraPrin > pratoPrincipal.size());

        int ideAcom = -1;
        do {
            System.out.println("\nSELECIONE O ACOMPANHAMENTO:");
            ideAcom = entrada.nextInt();

            if (ideAcom < 1 || ideAcom > acompanhamento.size()) {
                System.out.println("INDICE INVALIDO!");
            }
        } while (ideAcom < 1 || ideAcom > acompanhamento.size());

        int ideSal = -1;
        do {
            System.out.println("\nSELECIONE A SALADA:");
            ideSal = entrada.nextInt();

            if (ideSal < 1 || ideSal > salada.size()) {
                System.out.println("INDICE INVALIDO!");
            }
        } while (ideSal < 1 || ideSal > salada.size());
        refeicoes.add(new Refeicao(dnome,
                pratoPrincipal.get(idePraPrin - 1),
                acompanhamento.get(ideAcom - 1),
                salada.get(ideSal - 1)
        ));
        entrada.nextLine();
    }
    static void exibirRefeicao() {
        System.out.println("---ITENS CADASTRADO---");
        System.out.println("----------------------------");
        exibirSaladas();
        System.out.println("----------------------------");
        exibirPratoPrincipal();
        System.out.println("----------------------------");
        exibirAcompanhamento();
        System.out.println("----------------------------");
        int key =1;
        for (Refeicao r : refeicoes) {
            System.out.println("NUMERO DA REFEIÇÃO: " + key);
            System.out.print(r.getDescricao());
            System.out.println();
            System.out.println(r.getSalada().getNome());
            System.out.println(r.getPratoPrincipal().getNome());
            System.out.println(r.getAcompanhamento().getNome());
            System.out.println("----------------------------");
            key++;
        }
    }
    static void adicionarRefeicaoCardapio() {
        System.out.println("\n--- ADICIONAR REFEICAO A CARDAPIO ---\n");
        exibirRefeicao();
        int ideRef = -1;
        do {
            System.out.println("SELECIONE DESCRIÇÃO:");
            ideRef = entrada.nextInt();
            entrada.nextLine();
            if (ideRef < 1 || ideRef > refeicoes.size()) {
                System.out.println("INDICE INVALIDO!");
                ideRef = -1;
            }
            System.out.println("---ADICIONDO---");
        } while (ideRef == -1);
        System.out.println("SELECIONE UM DIA DA SEMANA: ");
        for (DiaSemana diaSemana : DiaSemana.values()) {
            System.out.println((diaSemana.ordinal()+1) + " - " + diaSemana);
        }
        int ideSem = -1;
        do {
            ideSem = entrada.nextInt();
            if (ideSem < 1 || ideSem > DiaSemana.values().length) {
                System.out.println("INDICE INVALIDO!");
                ideSem = -1;
            }
            System.out.println("---ADICIONADO---");
        } while (ideSem == -1);
        System.out.println("\nSELECIONE UM TURNO: ");
        for (Turno turno : Turno.values()) {
            System.out.println((turno.ordinal()+1) + " - " + turno);
        }
        int ideTur = -1;
        do {
            ideTur = entrada.nextInt();
            entrada.nextLine();
            if (ideTur < 1 || ideTur > Turno.values().length) {
                System.out.println("INDICE INVALIDO!");
                ideTur = -1;
            }
            System.out.println("---ADICIONADO---");
        } while (ideTur == -1);
        cardapio.adicionarRefeicao(
                DiaSemana.values()[ideSem - 1],
                Turno.values()[ideTur - 1],
                refeicoes.get(ideRef - 1)
        );
        System.out.println("\nREFEIÇÃO ADICIONDA AO CARDAPIO!\n");
    }
    public static void exibirCardapio() {
        for (DiaSemana dia : Cardapio.map.keySet()) {
            System.out.println("----------------------------");
            System.out.println(dia);
            var mapInterno = cardapio.map.get(dia);
            for (Turno refe : mapInterno.keySet()) {
                    System.out.println(refe);
                var r = mapInterno.get(refe);
                System.out.println(r.getDescricao());
                System.out.println(r.getSalada().getNome());
                System.out.println(r.getPratoPrincipal().getNome());
                System.out.println(r.getAcompanhamento().getNome());
                System.out.println("----------------------------");
            }
        }
    }
    static void exibirSaladas () {
        System.out.println("\nSALADAS");
        for (int i = 0; i < salada.size(); i++) {
            System.out.println((i + 1) + " - " + salada.get(i).nome);
        }
    }
    static void exibirAcompanhamento () {
        System.out.println("\nACOMPANHAMENTO");
        for (int i = 0; i < acompanhamento.size(); i++) {
            System.out.println((i + 1) + " - " + acompanhamento.get(i).nome);
        }
    }
    static void exibirPratoPrincipal () {
        System.out.println("\nPRATO PRINCIPAL");
        for (int i = 0; i < pratoPrincipal.size(); i++) {
            System.out.println((i + 1) + " - " + pratoPrincipal.get(i).nome);
        }
    }
    static void inicializarListas () {
        pratoPrincipal.addAll(List.of(
                new PratoPrincipal("Carne Assada"),
                new PratoPrincipal("Frango Grelhado"),
                new PratoPrincipal("File de Salmao")
        ));

        acompanhamento.addAll(List.of(
                new Acompanhamento("Pure de Batatas"),
                new Acompanhamento("Legumes Grelhados"),
                new Acompanhamento("Arroz a Grega")
        ));

        salada.addAll(List.of(
                new Salada("Salada de Quinoa"),
                new Salada("Salada Caprese"),
                new Salada("Salada Caesar")
        ));
    }
}
