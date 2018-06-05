# Training Tensorflow Model on Android

Train your custom tensorflow model on android. This example serves as a representative example of how a custom model can be trained on Android. Eventually I will demonstrate it with a neural network.

## Creating Tensorflow Model

The first step in training tensorflow model is to define a model. Here we create a simple model for learning the slope and constant of a simple line equation represented by the equation Y = Mx + C.

"model.py" contains the model definition. The weights (slope and constant) are initialized with a random value which is eventually fine tuned during training accoring to the training data. Once we define the model, we freeze the model into a graph structure ( protocol buffers ) as "graph.pb". This model can be deployed on android and used for training and inference.

## Deploying Tensorflow Model on Android

Include the produced graph in assets directory of the android application. "Training.java" contains code for loading the graph and feeding it with training data. Training data is generated randomly based on the slope and constant value input by the user. The model then tries to learn the weights (slope and constant ) from the training data. The goal for the model is to learn the slope and constant value input by the user.

### Tutorial
A detailed tutorial will be coming up soon on Medium! 




