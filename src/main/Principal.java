package main;

import neuron.*;

import java.io.*;
import java.util.*;

import com.sun.corba.se.spi.orbutil.fsm.Input;


public class Principal {

	public static void printDS(List<InputModel> dataset){
		int count = 0;
		for(InputModel in : dataset){
			System.out.println("--- Item "+ ++count);
			System.out.println("Target: " + in.getTarget());
			System.out.print("Features: ");
			for(double f : in.getFeatures()){
				System.out.print(f+" ");
			}
			System.out.println();
		}
	}
	
	public static List<InputModel> datasetRead(String datasetPath) throws FileNotFoundException{
		List<InputModel> dataset = new ArrayList<InputModel>();
		
		Scanner scan = new Scanner(new FileReader(datasetPath)).useDelimiter("\\||\\n");
		
		int inSize = 0;
		//Le enquanto tiver uma linha
		ArrayList<Double> feats = new ArrayList<Double>();
		while(scan.hasNext()){
			String linha = scan.next();
			
			//Separa a linha nos espacos
			String[] sep = linha.split(" ");
			inSize = sep.length;
			
			
			//Identifica as features
			for(int i = 0; i < sep.length - 1; i++){
				feats.add(Double.parseDouble(sep[i]));
			}
			//Identifica o target
			int type = 1;
			if(sep[inSize- 1].equals("pinguim") || sep[inSize-1].equals("-1") )
				type = -1;
			else if(sep[inSize- 1].equals("tigre") || sep[inSize-1].equals("1") )
				type = 1;

			//Adiciona no dataset
			dataset.add(new InputModel(feats, type));
			
			feats.clear();
		}
		
		return dataset;
	}

	public static void main(String[] args) throws IOException {
		List<InputModel> dataset;
		
		//Ler Dataset de um arquivo
		//Scanner ler = new Scanner(System.in);
		//System.out.println("Nome do Dataset: ");
		String path = "/home/brvno/Documentos/FURG/4o Ano/Inteligentes/NeuralNetwork/Perceptron/Dataset/";
		
		
		dataset = datasetRead(path+"TigreXPinguim.ds");
		List<InputModel> dsAND = datasetRead(path+"AND.ds");
		List<InputModel> dsOR = datasetRead(path+"OR.ds");

		int featNumber = dataset.get(0).getFeatures().size();
		double learnRate = 0.01;
		int itNumber = 10000;
		//Perceptron para reconhecer Tigre ou Pinguim
		Perceptron perceptronTP = new Perceptron(featNumber,learnRate, itNumber);
		perceptronTP.treinar(dataset);
		
		//Percepetron para reconhecer AND
		Perceptron perceptronAND = new Perceptron(featNumber, learnRate, itNumber);
		perceptronAND.treinar(dsAND);
		
		//Adaline para reconhecer OR
		Adaline adalineTP =  new Adaline(featNumber,learnRate, itNumber);
		//adalineTP.treinar(dsOR);
		
		//Plot Graphics
		/*perceptronTP.plot(0);
		perceptronTP.plot(1);
		perceptronTP.plot(2);
		*/
	}
}
