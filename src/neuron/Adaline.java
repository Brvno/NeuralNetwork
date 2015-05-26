package neuron;

import java.util.List;

public class Adaline extends Perceptron {

	private double errorRate = 0.1;
	
	public Adaline(int features, double bias, double learnRate,
			int iterationNumber) {
		super(features, bias, learnRate, iterationNumber);
	}
	
	public Adaline(int features, double learnRate,
			int iterationNumber) {
		super(features, learnRate, iterationNumber);
	}
	
	//@Override
	// Interpretacao pela funcao de ativacao
	double activationFunction(InputModel input){
		double aux = 0;
		for(int i = 0; i < this.featNumbers; i++){
			aux += input.getFeatures().get(i) * this.input.getWeights().get(i);
		}
		
		aux += this.bias *this.biasWeight;
		return aux;
		
	}
	
	//@Override
	//Funcao para treinar o Adaline, passando um dataset para atualizar os pesos
	public void treinar(List<InputModel> dataset){
		System.out.println("Iniciando Treinamento...");
		int count = 0;
		//Para cada numero de iteracoes
		for(int i = 0; i < this.iterationNumber; i++){
			System.out.println("==> Iteration: "+ i);
			count = 0;
			
			//Salvando a mudanca dos pesos
			for(int j = 0; j < this.input.getWeights().size(); j++)
				this.input.addWeightOverTime(this.input.getWeights().get(j), j);
			
			//Para cada entrada no dataset calcular pesos
			for(InputModel in: dataset){
				count++;
				double out = activationFunction(in);
				if(this.errorRate <= Math.sqrt(Math.pow(out - in.getTarget(), 2) ) ){
					System.out.println("Precisa atualizar "+count);
					updateWeights(out, in.getTarget(), in.getFeatures());
				}
			}
		}
		System.out.println("Treinamento Concluido!");
	}
		

}
