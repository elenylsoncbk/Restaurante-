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
        System.out.println("\n   - Início do Programa -   ");

        do {
            System.out.println("\n      - MENU -      ");
            System.out.print("""
                    1 - CADASTRAR SALADA:
                    2 - CADASTRAR PRATO PRINCIPAL:
                    3 - CADASTRAR ACOMPANHAMENTO:
                    4 - CADASTRAR REFEIÇÃO:
                    5 - PUBLICAR ALIMENTO E REFEIÇÕES CADASTRADAS:
                    6 - ADICIONAR REFEIÇÃO AO CARDÁPIO:
                    7 - EXIBIR CARDÁPIO:
                    0 - SAIR:
                    """);
            comand = entrada.nextLine().trim();

            switch (comand) {
                case "1" -> AdicionarSalada();
                case "2" -> AdicionarPratoPrincipal();
                case "3" -> AdicionarAcompanhamento();
                case "4" -> CadastrarRefeicao();
                case "5" -> exibirRefeicao();
                case "6" -> {
                    try {
                        adicionarRefeicaoCardapio();
                    } catch (Exception e) {
                        System.out.println("REFEIÇÃO INVÁLIDA");
                        entrada.nextLine();
                    }
                }
                case "7" -> exibirCardapio();
                case "0" -> { }
                default -> {
                    System.out.println("Opção Inválida!");
                    System.out.println("----------------\n");
                }
            }
        } while (!comand.equalsIgnoreCase("0"));

        System.out.println("----- FIM DO PROGRAMA! -----");
    }

    static void AdicionarPratoPrincipal() {
        System.out.print("DIGITE O PRATO PRINCIPAL: ");
        String tarefa = entrada.nextLine();
        pratoPrincipal.add(new PratoPrincipal(tarefa));
        System.out.println("----- ADICIONADO -----");
    }

    static void AdicionarAcompanhamento() {
        System.out.print("DIGITE ACOMPANHAMENTO: ");
        String tarefa = entrada.nextLine();
        acompanhamento.add(new Acompanhamento(tarefa));
        System.out.println("----- ADICIONADO -----");
    }

    static void AdicionarSalada() {
        System.out.print("DIGITE A SALADA: ");
        String tarefa = entrada.nextLine();
        salada.add(new Salada(tarefa));
        System.out.println("----- ADICIONADO -----");
    }

    static void CadastrarRefeicao() {
        System.out.println("\n------- CADASTRAR REFEIÇÃO -------");

        if (pratoPrincipal.isEmpty()) {
            System.out.println("- NENHUM PRATO PRINCIPAL CADASTRADO!");
            return;
        }

        if (acompanhamento.isEmpty()) {
            System.out.println("- NENHUM ACOMPANHAMENTO CADASTRADO!");
            return;
        }

        if (salada.isEmpty()) {
            System.out.println("- NENHUMA SALADA CADASTRADA!");
            return;
        }

        exibirSaladas();
        exibirAcompanhamento();
        exibirPratoPrincipal();

        System.out.println("----------------------------");
        System.out.println("\nDIGITE O NOME DA REFEIÇÃO: ");
        var dnome = entrada.nextLine();

        int idePraPrin;
        do {
            System.out.println("\nSELECIONE O PRATO PRINCIPAL:");
            idePraPrin = entrada.nextInt();

            if (idePraPrin < 1 || idePraPrin > pratoPrincipal.size()) {
                System.out.println("ÍNDICE INVÁLIDO!");
            }
        } while (idePraPrin < 1 || idePraPrin > pratoPrincipal.size());

        int ideAcom;
        do {
            System.out.println("\nSELECIONE O ACOMPANHAMENTO:");
            ideAcom = entrada.nextInt();

            if (ideAcom < 1 || ideAcom > acompanhamento.size()) {
                System.out.println("ÍNDICE INVÁLIDO!");
            }
        } while (ideAcom < 1 || ideAcom > acompanhamento.size());

        int ideSal;
        do {
            System.out.println("\nSELECIONE A SALADA:");
            ideSal = entrada.nextInt();

            if (ideSal < 1 || ideSal > salada.size()) {
                System.out.println("ÍNDICE INVÁLIDO!");
            }
        } while (ideSal < 1 || ideSal > salada.size());

        refeicoes.add(new Refeicao(dnome,
                pratoPrincipal.get(idePraPrin - 1),
                acompanhamento.get(ideAcom - 1),
                salada.get(ideSal - 1)
        ));

        System.out.println("----------------------------");
        entrada.nextLine();
    }

    static void exibirRefeicao() {
        System.out.println("----- ITENS CADASTRADOS -----");
        exibirSaladas();
        System.out.println("----------------------------");
        exibirPratoPrincipal();
        System.out.println("----------------------------");
        exibirAcompanhamento();
        System.out.println("----------------------------");

        int key = 1;
        for (Refeicao r : refeicoes) {
            System.out.println("\nNÚMERO DA REFEIÇÃO: " + key);
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
        System.out.println("\n--- ADICIONAR REFEIÇÃO AO CARDÁPIO ---\n");
        exibirRefeicao();

        int ideRef;
        do {
            System.out.println("SELECIONE O NÚMERO DA REFEIÇÃO:");
            ideRef = entrada.nextInt();
            entrada.nextLine();
            if (ideRef < 1 || ideRef > refeicoes.size()) {
                System.out.println("ÍNDICE INVÁLIDO!");
                System.out.println("DIGITE NOVAMENTE:");
                ideRef = -1;
            } else {
                System.out.println("----- ADICIONANDO -----");
            }
        } while (ideRef == -1);

        System.out.println("SELECIONE UM DIA DA SEMANA: ");
        for (DiaSemana diaSemana : DiaSemana.values()) {
            System.out.println((diaSemana.ordinal() + 1) + " - " + diaSemana);
        }

        int ideSem;
        do {
            ideSem = entrada.nextInt();
            if (ideSem < 1 || ideSem > DiaSemana.values().length) {
                System.out.println("ÍNDICE INVÁLIDO!");
                System.out.println("DIGITE NOVAMENTE:");
                ideSem = -1;
            } else {
                System.out.println("----- ADICIONADO -----");
            }
        } while (ideSem == -1);

        System.out.println("\nSELECIONE UM TURNO: ");
        for (Turno turno : Turno.values()) {
            System.out.println((turno.ordinal() + 1) + " - " + turno);
        }

        int ideTur;
        do {
            ideTur = entrada.nextInt();
            entrada.nextLine();
            if (ideTur < 1 || ideTur > Turno.values().length) {
                System.out.println("ÍNDICE INVÁLIDO!");
                System.out.println("DIGITE NOVAMENTE:");
                ideTur = -1;
            } else {
                System.out.println("----- ADICIONADO -----");
            }
        } while (ideTur == -1);

        cardapio.adicionarRefeicao(
                DiaSemana.values()[ideSem - 1],
                Turno.values()[ideTur - 1],
                refeicoes.get(ideRef - 1)
        );
        System.out.println("\nREFEIÇÃO ADICIONADA AO CARDÁPIO!\n");
        System.out.println("----------------------------");
    }

    public static void exibirCardapio() {
        for (DiaSemana dia : Cardapio.map.keySet()) {
            System.out.println("----------------------------");
            System.out.println("        " + dia);
            System.out.println("----------------------------");
            var mapInterno = cardapio.map.get(dia);
            for (Turno refe : mapInterno.keySet()) {
                System.out.println("        " + refe);
                var r = mapInterno.get(refe);
                System.out.println(r.getDescricao());
                System.out.println(r.getSalada().getNome());
                System.out.println(r.getPratoPrincipal().getNome());
                System.out.println(r.getAcompanhamento().getNome());
                System.out.println("----------------------------");
            }
        }
    }

    static void exibirSaladas() {
        System.out.println("\nSALADAS");
        for (int i = 0; i < salada.size(); i++) {
            System.out.println((i + 1) + " - " + salada.get(i).nome);
        }
    }

    static void exibirAcompanhamento() {
        System.out.println("\nACOMPANHAMENTO");
        for (int i = 0; i < acompanhamento.size(); i++) {
            System.out.println((i + 1) + " - " + acompanhamento.get(i).nome);
        }
    }

    static void exibirPratoPrincipal() {
        System.out.println("\nPRATO PRINCIPAL");
        for (int i = 0; i < pratoPrincipal.size(); i++) {
            System.out.println((i + 1) + " - " + pratoPrincipal.get(i).nome);
        }
    }

    static void inicializarListas() {
        pratoPrincipal.addAll(List.of(
                new PratoPrincipal("Carne Assada"),
                new PratoPrincipal("Frango Grelhado"),
                new PratoPrincipal("Filé de Salmão")
        ));

        acompanhamento.addAll(List.of(
                new Acompanhamento("Purê de Batatas"),
                new Acompanhamento("Legumes Grelhados"),
                new Acompanhamento("Arroz à Grega")
        ));

        salada.addAll(List.of(
                new Salada("Salada de Quinoa"),
                new Salada("Salada Caprese"),
                new Salada("Salada Caesar")
        ));
    }
}
