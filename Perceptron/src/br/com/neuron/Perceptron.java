package br.com.neuron;

import java.util.*;

/* Perceptron definition: y = f(z),  denotes the output for an input vector z
 * b is the bias
 * 
 */
public class Perceptron {

	// Entrada do Perceptron
	private InputModel input;
	
	// Bias
	private double bias;
	private double biasWeight;
	
	//Learning Rate
	private double learnRate = 1;
	
	// Quantidade de features
	private int size;
	
	// @param features: quantidade de features
	// @param bias: valor do bias
	// @param learnRate: taxa de aprendizado
	Perceptron(int features, double bias, double learnRate){
		this.size = features;
		this.bias = bias;
		this.biasWeight = 0;
		this.learnRate = learnRate;
		
		this.input = new InputModel(this.size);
	}
	
	//Funcao para treinar o Perceptron, passando um dataset para atualizar os pesos
	void treinar(List<InputModel> dataset){
		List<Integer> output = new ArrayList<Integer>(this.size);
		int aux;
		for(int i = 0; i < this.size; i++){
			this.input.newInput(dataset.get(i).features);
			aux = activationFunction(dataset.get(i));
			output.add(i, aux);
			if(aux != dataset.get(i).target)
				updateWeights(aux, dataset.get(i).target);
		}	
	}
	
	// Interpretacao pela funcao de ativacao
	int activationFunction(InputModel input){
		double aux = 0;
		for(int i = 0; i < this.size; i++){
			aux += input.features.get(i) * input.weights.get(i);
		}
		aux += this.bias * this.biasWeight;
		
		if(aux >= 0)
			return 1;
		return -1;
	}
	
	//Atualizacao dos pesos
	void updateWeights(int output, int target){
		double aux = 0;
		double localerror;
		localerror = target - output;
		
		for(int i = 0; i < this.size; i++){
			aux = this.input.weights.get(i) + (this.learnRate * localerror * this.input.features.get(i) ); 
			this.input.weights.add(i, aux);
		}
		this.biasWeight = this.biasWeight + (this.learnRate * localerror * this.bias);
	}
	
	//Funcao para executar o perceptron
	int execute(InputModel input){
		return activationFunction(input);
	}
}
