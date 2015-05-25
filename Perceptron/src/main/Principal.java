package main;

import neuron.*;
import java.io.*;
import java.util.*;

public class Principal {
	
	public static void criarDataset(){
		ArrayList<InputModel> ds = new ArrayList<InputModel>();
		List<Double> features = new ArrayList<Double>();	
		
		//Definindo que sera tigre se for 1
		int tigre = 1;
		int pinguim = -1;
		
		//Teste 1
		
		features.add(.9);
		features.add(.1);
		features.add(.83);
		ds.add(new InputModel(features, pinguim));
		features.clear();
		
		
		//Teste 2
		features.add(0, .8);
		features.add(.12);
		features.add(.77);
		ds.add(new InputModel(features, pinguim));
		features.clear();
						
		//Teste 3
		features.add(.17);
		features.add(.91);
		features.add(.23);
		ds.add(new InputModel(features, tigre));
		
		System.out.println(ds.get(0).getFeatures().get(0));
		System.out.println(ds.get(1).getFeatures().get(0));
		System.out.println(ds.get(2).getFeatures().get(0));
		System.out.println(ds.size());
			
		System.out.println(ds.get(0).getTarget());
		System.out.println(ds.get(1).getTarget());
		System.out.println(ds.get(2).getTarget());
		//return ds;
	}

	public static void main(String[] args) {
		List<InputModel> dataset;
		
		//TODO: Ler Arquivo de dataset
		
		criarDataset();
		/*for(InputModel in : dataset){
			System.out.println("Features: ");
			for(double f : in.getFeatures())
				System.out.print(f+" ");
			System.out.println("Target: "+in.getTarget());
			
		}
		System.out.println(dataset.get(0).getFeatures().get(0));
		System.out.println(dataset.get(1).getFeatures().get(0));
		System.out.println(dataset.get(2).getFeatures().get(0));
		*/
	}
	
	

}
