package br.com.neuron;

import java.util.*;

public class InputModel {

	List<Double> features;
	int target;
	List<Double> weights;

	InputModel(int size){
		this.weights = new ArrayList<Double>(size);
		
		for(int i = 0; i < size; i++)
			this.weights.add(i, 0.0);
	}
	
	void newInput(List<Double> i){
		this.features = i;
	}

}
