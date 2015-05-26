package neuron;

import java.util.*;

public class InputModel {

	private List<Double> features;
	private int target;
	private List<Double> weights;
	private List<List<Double>> weightsOverTime; //w[feat][t]
	
	
	public InputModel(List<Double> features){
		this.features = features;
		this.weightsOverTime = new ArrayList<List<Double>>();
		this.weights = new ArrayList<Double>(features.size());
		
		double initialWeight = 0.5;
		
		for(int i = 0; i < features.size(); i++){
			this.weightsOverTime.add(new ArrayList<Double>());
			this.weights.add(i, initialWeight);
			this.weightsOverTime.get(i).add(initialWeight);
				
		}
	}
	public InputModel(List<Double> features, int target){
		this.features = features;
		this.target = target;
		this.weights = new ArrayList<Double>(features.size());
		this.weightsOverTime = new ArrayList<List<Double>>(features.size());
		
		double initialWeight = 0.5;

		for(int i = 0; i < features.size(); i++){
			this.weightsOverTime.add(new ArrayList<Double>());
			this.weights.add(i, initialWeight);
			this.weightsOverTime.get(i).add(initialWeight);
		}
	}
	
	public InputModel(int featSize){
		this.weights = new ArrayList<Double>(featSize);
		this.weightsOverTime = new ArrayList<List<Double>>(featSize);
		
		double initialWeight = 0.5;

		for(int i = 0; i < featSize; i++){
			this.weightsOverTime.add(new ArrayList<Double>());
			this.weights.add(i, initialWeight);
			this.weightsOverTime.get(i).add(initialWeight);
		}	
	}
	
	
	public List<Double> getFeatures() {
		return features;
	}

	public void setFeatures(
			List<Double> features) {
		this.features = features;
	}

	public int getTarget() {
		return target;
	}

	public void setTarget(int target) {
		this.target = target;
	}

	public List<Double> getWeights() {
		return weights;
	}

	public void setWeights(List<Double> weights) {
		this.weights = weights;
	}

	//Adiciona w na feature em t+1
	public void addWeightOverTime(Double w, int feature){
		this.weightsOverTime.get(feature).add(w);
	}
	
	public List<List<Double>> getWeightOverTime(){
		return this.weightsOverTime;
	}
	
	//Retorna uma lista com todos os pesos ao longo do tempo de uma determinada feature
	public List<Double> getWeightOverTime(int feature){
		List<Double> f = new ArrayList<Double>();
		return this.weightsOverTime.get(feature);
	}
	

}
