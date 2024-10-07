package oo.Composicao.Desafio.Trabalho2;

import java.util.HashMap;
import java.util.Map;

public class Cardapio {

    public static final Map<DiaSemana, Map<Turno, Refeicao>> map = new HashMap<>();

    public void adicionarRefeicao(DiaSemana diaSemana, Turno turno, Refeicao refeicao) {
        try {
            map.get(diaSemana).put(turno, refeicao);
        } catch (Exception e) {
            map.put(diaSemana, new HashMap<>(Map.of(turno, refeicao)));
        }
    }
}

