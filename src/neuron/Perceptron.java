package neuron;

import java.util.*;

import javax.swing.JFrame;

/* Perceptron definition: y = f(z),  denotes the output for an input vector z
 * b is the bias
 * 
 */
public class Perceptron {

	// Entrada do Perceptron
	protected InputModel input;
	
	// Bias
	protected double bias;
	protected double biasWeight;
	
	//Learning Rate
	protected double learnRate = 1;
	
	//Numero de iteracoes
	protected int iterationNumber;
	
	// Quantidade de features
	protected int featNumbers;
	
	// @param features: quantidade de features
	// @param bias: valor do bias
	// @param learnRate: taxa de aprendizado ou eta
	// @param iterationNumber: Numero de iteracoes para o treinamento
	public Perceptron(int features, double learnRate, int iterationNumber){
		this.featNumbers = features;
		this.bias = 1;
		this.biasWeight = 0.5;
		this.learnRate = learnRate;
		this.iterationNumber = iterationNumber;
		
		this.input = new InputModel(features);
	}
	
	public Perceptron(int features, double bias, double learnRate, int iterationNumber){
		this.featNumbers = features;
		this.bias = bias;
		this.biasWeight = 0.5;
		this.learnRate = learnRate;
		this.iterationNumber = iterationNumber;
		
		this.input = new InputModel(features);
	}
	
	//Funcao para treinar o Perceptron, passando um dataset para atualizar os pesos
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
				if(out != in.getTarget()){
					System.out.println("Precisa atualizar "+count);
					updateWeights(out, in.getTarget(), in.getFeatures());
				}
			}
		}
		System.out.println("Treinamento Concluido!");
	}
	
	// Interpretacao pela funcao de ativacao
	double activationFunction(InputModel input){
		double aux = 0;
		for(int i = 0; i < this.featNumbers; i++){
			aux += input.getFeatures().get(i) * this.input.getWeights().get(i);
		}
		
		aux += this.bias *this.biasWeight;
		
		if(aux >= 0)
			return 1;
		return -1;
	}
	
	//Atualizacao dos pesos
	public void updateWeights(double output, int target, List<Double> features){
		double aux = 0;
		double localerror;
		localerror = target - output;
		
		//Para cada feature j, fazer: Wj = Wj + learnRate * localError * Xj 
		for(int i = 0; i < this.featNumbers; i++){
			aux = this.input.getWeights().get(i) + (this.learnRate * localerror * features.get(i) ); 
			this.input.getWeights().set(i, aux);
		}
		this.biasWeight = this.biasWeight + (this.learnRate * localerror * this.bias);
	}
	
	//Funcao para executar o perceptron
	public double execute(InputModel input){
		return activationFunction(input);
	}
	
	public void plot(int feature){
		//Plot Graphic
		JFrame f = new JFrame("W"+feature);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new GraphicData(this.input.getWeightOverTime(feature)));
        f.setSize(640,480);
        f.setLocation(200,200);
        f.setVisible(true);
	}
}
