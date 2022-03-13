package cadeiasMarkovTenias_Joana_Angelo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.lang.String;

public class index {
	
	static int Partidas1GanhadasporA;
	static int Partidas1GanhadasporB;
	static int Partidas2GanhadasporA;
	static int Partidas2GanhadasporB;
	static int JogosGanhosPorA;
	static int JogosGanhosPorB;
	static double[][] matrix_markov;
	static Map<Integer,String> estados = new HashMap<>();
	
	public static void main(String[] args) {
		
		//System.out.println("Digite a porcentagem do jogador 1 no primeiro jogo:");
		
		double p1 = 0.7;
		double q1 = 1 - p1;
		double p2 = 0.45;
		double q2 = 1 - p2;
		int ganhador = 0;
		JogosGanhosPorA = 0;
		JogosGanhosPorB = 0;
		DefinirEstados();
		//DefinirMatriz(p1,q1);
		//Set(p1,q1);
		int contador = 1;
		System.out.println("=============LOGS==============");
		while(contador != 31) {
			System.out.println("===========================NOVO JOGO================================");
			System.out.println("Jogo "+contador);
			ganhador = Partida(p1, q1, p2, q2);
			contador++;
			if(ganhador == 1) {
				JogosGanhosPorA++;
			}else {
				JogosGanhosPorB++;
			}
		}
		
		System.out.println("===========================Relatórios================================");
		System.out.println("Jogos ganhos por A: "+JogosGanhosPorA);
		System.out.println("Jogos ganhos por B: "+JogosGanhosPorB);
	}
	
	public static int Partida(double p1, double q1,double p2, double q2){
		int round1 = 0, round2 = 0, round3 = 0;
		double p3 = 0, q3 = 0;
		System.out.println("Primeira Partida:");
		round1 = Set(p1,q1);
		System.out.println("Segunda Partida:");
		round2 = Set(p2,q2);
		
		if(round1 != round2) {
			System.out.println("Devido ao empate -> Terceira Partida:");
			p3 = 0.5;
			q3 = 1 - 0.5;
			round3 = Set(p3,q3);
		}
		
		if(round3 == 0) {
			if(round1 == 14) {
				return 1;
			}
		}else{
			if(round3 == 14) {
				return 1;
			}
		}
		return 2;
	}
	
	public static int Set(double p, double q) {
		int verificar_vencedor = 0;
		int i = 0;
		int estado = 1;
		int ganhador = 0;
		DefinirMatriz(p,q);
		ImprimirEstado(estado);
		while((verificar_vencedor!=14)&&(verificar_vencedor!=17)){
			ganhador = GeradorDeQuemPontuou(p);
			//System.out.println("Ganhador"+ganhador);
			if(ganhador == 1){
				for(i = 0; i < 17; i++){
					double aux = matrix_markov[estado-1][i];
					if(aux == p){
						estado = i+1;
						break;
					}
				}	
			}else{
				for(i = 0; i < 17; i++){
					double aux = matrix_markov[estado-1][i];
					if(aux == q){
						estado = i+1;
						break;
					}
				}
			}
			verificar_vencedor = estado;
			ImprimirEstado(estado);
		}
		
		return estado;
	}
	
	
	public static int GeradorDeQuemPontuou(double p) {
		Random rand = new Random();
		int limite = 100;
		int sorteio = rand.nextInt(limite);
		//System.out.println("P: "+ p*10);
		//System.out.println("Sorteio:"+ sorteio);
		if(sorteio <= p*100) {
			return 1;
		}else {
			return 0;
		}
	}
	
	public static void DefinirMatriz(double p, double q) {
		matrix_markov = new double[][]{ {0,p,q,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
										{0,0,0,p,q,0,0,0,0,0,0,0,0,0,0,0,0},
										{0,0,0,0,p,q,0,0,0,0,0,0,0,0,0,0,0},
										{0,0,0,0,0,0,p,q,0,0,0,0,0,0,0,0,0},
										{0,0,0,0,0,0,0,p,q,0,0,0,0,0,0,0,0},
										{0,0,0,0,0,0,0,0,p,q,0,0,0,0,0,0,0},
										{0,0,0,0,0,0,0,0,0,0,q,0,0,p,0,0,0},
										{0,0,0,0,0,0,0,0,0,0,p,q,0,0,0,0,0},
										{0,0,0,0,0,0,0,0,0,0,0,p,q,0,0,0,0},
										{0,0,0,0,0,0,0,0,0,0,0,0,p,0,0,0,q},
										{0,0,0,0,0,0,0,0,0,0,0,0,0,p,q,0,0},
										{0,0,0,0,0,0,0,0,0,0,0,0,0,0,p,q,0},
										{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,p,q},
										{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
										{0,0,0,0,0,0,0,0,0,0,0,q,0,p,0,0,0},
										{0,0,0,0,0,0,0,0,0,0,0,p,0,0,0,0,q},
										{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0} };
	}
	
	public static void DefinirEstados(){
		estados.put(1, "0-0");
		estados.put(2, "15-Love");
		estados.put(3, "Love-15");
		estados.put(4, "30-Love");
		estados.put(5, "15-15");
		estados.put(6, "Love-30");
		estados.put(7, "40-Love");
		estados.put(8, "30-15");
		estados.put(9, "15-30");
		estados.put(10, "Love-40");
		estados.put(11, "40-15");
		estados.put(12, "Deuce");
		estados.put(13, "15-40");
		estados.put(14, "A wins");
		estados.put(15, "Adv. A");
		estados.put(16, "Adv. B");
		estados.put(17, "B wins");
		
	}
	
	public static String QualEstado(int chave){
		String estado_atual = estados.get(chave);
		return estado_atual;
	}
	
	public static void ImprimirEstado(int chave){
		String estado_atual = QualEstado(chave);
		System.out.println(estado_atual);
	}

}
