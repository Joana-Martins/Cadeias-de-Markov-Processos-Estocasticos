package cadeiasMarkovTenias_Joana_Angelo;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.lang.String;
import java.lang.Math;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;

public class index {
	
	//Variaveis para armazenar informações
	static int Partidas1GanhadasporA;
	static int Partidas1GanhadasporB;
	static int Partidas2GanhadasporA;
	static int Partidas2GanhadasporB;
	static int Partidas3GanhadasporA;
	static int Partidas3GanhadasporB;
	static int Partidas1GanhadasporA_Controle;
	static int Partidas1GanhadasporB_Controle;
	static int Partidas2GanhadasporA_Controle;
	static int Partidas2GanhadasporB_Controle;
	static int Partidas3GanhadasporA_Controle;
	static int Partidas3GanhadasporB_Controle;
	static int JogosGanhosPorA_Controle;
	static int JogosGanhosPorB_Controle;
	static int JogosGanhosPorA;
	static int JogosGanhosPorB;
	static int AlguemPontuar;
	static int APontuar;
	static int BPontuar;
	static int TerceirasPartidas;
	static int TerceirasPartidas_Controle;
	static int NumeroPartidas;
	static int NumeroPartidas_Controle;
	static double DesvioPadraoVitoriaPartidasA;
	static double DesvioPadraoVitoriaPartidasA_Controle;
	static double DesvioPadraoVitoriaPartidasB;
	static double DesvioPadraoVitoriaPartidasB_Controle;
	
	//Matriz com probabilidades de Markov
	static double[][] matrix_markov;

	//Definir estados
	static Map<Integer,String> estados = new HashMap<>();
	
	
	//Main
	public static void main(String[] args) throws IOException {
		
		PrintWriter ps = new PrintWriter("log.txt");
	        
		
		//Inicializacao de variaveis
		double p1 = 0.7;
		double q1 = 1 - p1;
		double p2 = 0.45;
		double q2 = 1 - p2;
		int ganhador = 0;
		JogosGanhosPorA = 0;
		JogosGanhosPorB = 0;
		Partidas1GanhadasporA = 0;
		Partidas1GanhadasporB = 0;
		Partidas2GanhadasporA = 0;
		Partidas2GanhadasporB = 0;
		Partidas3GanhadasporA = 0;
		Partidas3GanhadasporB = 0;
		Partidas1GanhadasporA_Controle = 0;
		Partidas1GanhadasporB_Controle = 0;
		Partidas2GanhadasporA_Controle = 0;
		Partidas2GanhadasporB_Controle = 0;
		Partidas3GanhadasporA_Controle = 0;
		Partidas3GanhadasporB_Controle = 0;
		JogosGanhosPorA_Controle = 0;
		JogosGanhosPorB_Controle = 0;
		TerceirasPartidas = 0;
		TerceirasPartidas_Controle = 0;
		NumeroPartidas = 0;
		NumeroPartidas_Controle = 0;
		DesvioPadraoVitoriaPartidasA = 0;
		DesvioPadraoVitoriaPartidasA_Controle = 0;
		DesvioPadraoVitoriaPartidasB = 0;
		DesvioPadraoVitoriaPartidasB_Controle = 0;
		AlguemPontuar = 0;
		APontuar = 0;
		BPontuar = 0;
		
		//Declaracao de propiedades estatisticas 
		double ProbabilidadeAGanhar = 0;
		double ProbabilidadeBGanhar = 0;
		double ProbabilidadeAGanharPartida1 = 0;
		double ProbabilidadeBGanharPartida1 = 0;
		double ProbabilidadeAGanharPartida2 = 0;
		double ProbabilidadeBGanharPartida2 = 0;
		double ProbabilidadeAGanhar_Controle = 0;
		double ProbabilidadeBGanhar_Controle = 0;
		double ProbabilidadeAGanharPartida1_Controle = 0;
		double ProbabilidadeBGanharPartida1_Controle = 0;
		double ProbabilidadeAGanharPartida2_Controle = 0;
		double ProbabilidadeBGanharPartida2_Controle = 0;
		double MediaPontosA = 0;
		double MediaPontosB = 0;
		double MediaVitoriaPartidasA = 0;
		double MediaVitoriaPartidasB = 0;
		double MediaVitoriaPartidasA_Controle = 0;
		double MediaVitoriaPartidasB_Controle = 0;

		//Definir estados
		DefinirEstados();
		int contador = 1;
		
		//Simulacao dos 30 jogos
		ps.println("=============LOGS==============");
		while(contador != 21) {
			ps.println("===========================NOVO JOGO================================");
			ps.println("Jogo "+contador);
			ganhador = Partida(p1, q1, p2, q2, contador, ps);
			contador++;
			if(ganhador == 1) {
				JogosGanhosPorA++;
			}else {
				JogosGanhosPorB++;
			}
		}
		
		while(contador != 31) {
			ps.println("===========================NOVO JOGO================================");
			ps.println("Jogo "+contador);
			ganhador = Partida(p1, q1, p2, q2, contador, ps);
			contador++;
			if(ganhador == 1) {
				JogosGanhosPorA++;
				JogosGanhosPorA_Controle++;
			}else {
				JogosGanhosPorB++;
				JogosGanhosPorB_Controle++;
			}
		}
		
		//Calculos de probabilidade
		ProbabilidadeAGanhar = (double)JogosGanhosPorA/30;
		ProbabilidadeBGanhar = (double)JogosGanhosPorB/30;
		ProbabilidadeAGanharPartida1 = (double)Partidas1GanhadasporA/30;
		ProbabilidadeBGanharPartida1 = (double)Partidas1GanhadasporB/30;
		ProbabilidadeAGanharPartida2 = (double)Partidas2GanhadasporA/30;
		ProbabilidadeBGanharPartida2 = (double)Partidas2GanhadasporB/30;
		
		ProbabilidadeAGanhar_Controle = (double)JogosGanhosPorA_Controle/10;
		ProbabilidadeBGanhar_Controle = (double)JogosGanhosPorB_Controle/10;
		ProbabilidadeAGanharPartida1_Controle = (double)Partidas1GanhadasporA_Controle/10;
		ProbabilidadeBGanharPartida1_Controle = (double)Partidas1GanhadasporB_Controle/10;
		ProbabilidadeAGanharPartida2_Controle = (double)Partidas2GanhadasporA_Controle/10;
		ProbabilidadeBGanharPartida2_Controle = (double)Partidas2GanhadasporB_Controle/10;
		
		//Calculo de media
		MediaPontosA = APontuar/30;
		MediaPontosB = BPontuar/30;
		MediaVitoriaPartidasA = (double)(Partidas1GanhadasporA + Partidas2GanhadasporA)/NumeroPartidas;
		MediaVitoriaPartidasB = (double)(Partidas1GanhadasporB + Partidas2GanhadasporB)/NumeroPartidas;
		MediaVitoriaPartidasA_Controle = (double)(Partidas1GanhadasporA_Controle + Partidas2GanhadasporA_Controle)/NumeroPartidas_Controle;
		MediaVitoriaPartidasB_Controle = (double)(Partidas1GanhadasporB_Controle + Partidas2GanhadasporB_Controle)/NumeroPartidas_Controle;
		
		//Calculo Desvio Padrão
		DesvioPadraoVitoriaPartidasA = ((30*Math.abs((double)1-MediaVitoriaPartidasA))/(double)(30-1));
		DesvioPadraoVitoriaPartidasA_Controle = ((10*Math.abs((double)1-MediaVitoriaPartidasA))/(double)(10-1));;
		DesvioPadraoVitoriaPartidasB = ((30*Math.abs((double)1-MediaVitoriaPartidasB))/(double)(30-1));;
		DesvioPadraoVitoriaPartidasB_Controle = ((10*Math.abs((double)1-MediaVitoriaPartidasB))/(double)(10-1));;;
		
		
		//Impressao de relatorios
		ps.println("=========================Relatorios 30 Partidas==============================");
		ps.println("Jogos ganhos por A: "+JogosGanhosPorA);
		ps.println("Jogos ganhos por B: "+JogosGanhosPorB);
		ps.println("Primeiros sets ganhos por A:" +Partidas1GanhadasporA);
		ps.println("Primeiros sets ganhos por B: " +Partidas1GanhadasporB);
		ps.println("Segundos sets ganhos por A: " + Partidas2GanhadasporA);
		ps.println("Segundos sets ganhos por B: " +Partidas2GanhadasporB);
		ps.println("Terceiros sets ganhos por A: " +Partidas3GanhadasporA);
		ps.println("Terceiro sets ganhos por B: " +Partidas3GanhadasporB);
		ps.println("Numero de terceiros sets que teve: " +TerceirasPartidas);
		ps.println();
		ps.println("========Probabilidades=========");
		ps.println("Probabilidade de A ganhar: "+ProbabilidadeAGanhar);
		ps.println("Probabilidade de B ganhar: "+ProbabilidadeBGanhar);
		ps.println("Probabilidade de A ganhar a Partida 1: " +ProbabilidadeAGanharPartida1);
		ps.println("Probabilidade de B ganhar a Partida 1: " +ProbabilidadeBGanharPartida1);
		ps.println("Probabilidade de A ganhar a Partida 2: " +ProbabilidadeAGanharPartida2);
		ps.println("Probabilidade de B ganhar a Partida 2: " +ProbabilidadeBGanharPartida2);
		ps.println();
		ps.println("========Medias========");
		ps.println("Media de pontos de A: "+MediaPontosA);
		ps.println("Media de pontos de B: "+MediaPontosB);
		ps.println("Media de partidas ganhas por A: "+MediaVitoriaPartidasA);
		ps.println("Media de partidas ganhas por B: "+MediaVitoriaPartidasB);
		ps.println();
		ps.println("========Desvio Padrao========");
		ps.println("Desvio de partidas ganhas por A: "+DesvioPadraoVitoriaPartidasA);
		ps.println("Desvio de partidas ganhas por B: " +DesvioPadraoVitoriaPartidasB);
		ps.println();
		ps.println("=========================Relatorios 10 Partidas==============================");
		ps.println("Jogos ganhos por A: "+JogosGanhosPorA_Controle);
		ps.println("Jogos ganhos por B: "+JogosGanhosPorB_Controle);
		ps.println("Primeiros sets ganhos por A:" +Partidas1GanhadasporA_Controle);
		ps.println("Primeiros sets ganhos por B: " +Partidas1GanhadasporB_Controle);
		ps.println("Segundos sets ganhos por A: " + Partidas2GanhadasporA_Controle);
		ps.println("Segundos sets ganhos por B: " +Partidas2GanhadasporB_Controle);
		ps.println("Terceiros sets ganhos por A: " +Partidas3GanhadasporA_Controle);
		ps.println("Terceiro sets ganhos por B: " +Partidas3GanhadasporB_Controle);
		ps.println("Numero de terceiros sets que teve: " +TerceirasPartidas_Controle);
		ps.println();
		ps.println("========Probabilidades=========");
		ps.println("Probabilidade de A ganhar: "+ProbabilidadeAGanhar_Controle);
		ps.println("Probabilidade de B ganhar: "+ProbabilidadeBGanhar_Controle);
		ps.println("Probabilidade de A ganhar a Partida 1: " +ProbabilidadeAGanharPartida1_Controle);
		ps.println("Probabilidade de B ganhar a Partida 1: " +ProbabilidadeBGanharPartida1_Controle);
		ps.println("Probabilidade de A ganhar a Partida 2: " +ProbabilidadeAGanharPartida2_Controle);
		ps.println("Probabilidade de B ganhar a Partida 2: " +ProbabilidadeBGanharPartida2_Controle);
		ps.println();
		ps.println("========Medias========");
		ps.println("Media de partidas ganhas por A: "+MediaVitoriaPartidasA_Controle);
		ps.println("Media de partidas ganhas por B: "+MediaVitoriaPartidasB_Controle);
		ps.println();
		ps.println("========Desvio Padrao========");
		ps.println("Desvio de partidas ganhas por A: "+DesvioPadraoVitoriaPartidasA_Controle);
		ps.println("Desvio de partidas ganhas por B: " +DesvioPadraoVitoriaPartidasB_Controle);
	
		ps.close();
	}
	
	//Faz a simulação de um jogo
	public static int Partida(double p1, double q1,double p2, double q2, int contador,PrintWriter ps){
		int round1 = 0, round2 = 0, round3 = 0;
		double p3 = 0, q3 = 0;
		if(contador < 21){
			ps.println("Primeira Partida:");
			round1 = Set(p1,q1, ps);
			NumeroPartidas++;
			
			if(round1 == 14) {
				Partidas1GanhadasporA++;
			}else {
				Partidas1GanhadasporB++;
			}
		
			ps.println("Segunda Partida:");
			round2 = Set(p2,q2, ps);
			NumeroPartidas++;
			
			if(round2 == 14) {
				Partidas2GanhadasporA++;
			}else {
				Partidas2GanhadasporB++;
			}
		
			if(round1 != round2) {
				NumeroPartidas++;
				TerceirasPartidas++;
				ps.println("Devido ao empate -> Terceira Partida:");
				p3 = 0.48;
				q3 = 1 - p3;
				round3 = Set(p3,q3, ps);
			}
		
			if(round3 == 14) {
				Partidas3GanhadasporA++;
			}else if(round3 == 17){
				Partidas3GanhadasporB++;
			}
		}else{
			ps.println("Primeira Partida:");
			round1 = Set(p1,q1, ps);
			NumeroPartidas++;
			NumeroPartidas_Controle++;
			
			if(round1 == 14) {
				Partidas1GanhadasporA++;
				Partidas1GanhadasporA_Controle++;
			}else {
				Partidas1GanhadasporB++;
				Partidas1GanhadasporB_Controle++;
			}
			
			ps.println("Segunda Partida:");
			round2 = Set(p2,q2, ps);
			NumeroPartidas++;
			NumeroPartidas_Controle++;
			
			if(round2 == 14) {
				Partidas2GanhadasporA++;
				Partidas2GanhadasporA_Controle++;
			}else {
				Partidas2GanhadasporB++;
				Partidas2GanhadasporB_Controle++;
			}
			
			if(round1 != round2) {
				NumeroPartidas++;
				NumeroPartidas_Controle++;
				TerceirasPartidas++;
				TerceirasPartidas_Controle++;
				ps.println("Devido ao empate -> Terceira Partida:");
				p3 = 0.48;
				q3 = 1 - p3;
				round3 = Set(p3,q3,ps);
			}
			
			if(round3 == 14) {
				Partidas3GanhadasporA++;
				Partidas3GanhadasporA_Controle++;
			}else if(round3 == 17){
				Partidas3GanhadasporB++;
				Partidas3GanhadasporB_Controle++;
			}
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
	
	//Simulacao de um set
	public static int Set(double p, double q, PrintWriter ps) {
		int verificar_vencedor = 0;
		int i = 0;
		int estado = 1;
		int ganhador = 0;
		DefinirMatriz(p,q);
		ImprimirEstado(estado, ps);
		while((verificar_vencedor!=14)&&(verificar_vencedor!=17)){
			ganhador = GeradorDeQuemPontuou(p);
			AlguemPontuar++;
			if(ganhador == 1){
				APontuar++;
				for(i = 0; i < 17; i++){
					double aux = matrix_markov[estado-1][i];
					if(aux == p){
						estado = i+1;
						break;
					}
				}	
			}else{
				BPontuar++;
				for(i = 0; i < 17; i++){
					double aux = matrix_markov[estado-1][i];
					if(aux == q){
						estado = i+1;
						break;
					}
				}
			}
			verificar_vencedor = estado;
			ImprimirEstado(estado, ps);
		}
		
		return estado;
	}
	
	//Sorteia quem ganha o ponto na partida
	public static int GeradorDeQuemPontuou(double p) {
		Random rand = new Random();
		int limite = 100;
		int sorteio = rand.nextInt(limite);
		if(sorteio <= p*100) {
			return 1;
		}else {
			return 0;
		}
	}
	
	//Define a matriz de markov
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
	
	//Define os estados os relacfionando com numeros para poder caminhar pela matriz
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

	//Retorna qual estado atual
	public static String QualEstado(int chave){
		String estado_atual = estados.get(chave);
		return estado_atual;
	}
	
	//Imprime o estado atual
	public static void ImprimirEstado(int chave, PrintWriter ps){
		String estado_atual = QualEstado(chave);
		ps.println(estado_atual);
	}

}
