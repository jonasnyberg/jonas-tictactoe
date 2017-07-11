# Implementation of Tic-Tac-Toe-Tomek

_https://code.google.com/codejam/contest/2270488/dashboard_

---
## How to Install:
1. Clone the project
2. Build with Maven: **mvn clean package**
3. Open the _target_ directory and run:
	1. Small dataset (10 games): **java -jar jonas-tictactoe.jar < data-files/A-small-practice.in**
	2. Large dataset (1000 games): **java -jar jonas-tictactoe.jar < data-files/A-large-practice.in**

## Running from inside Eclipse:
If you want to test it from within Eclipse, simple follow below steps:

1. Clone the project
2. Inside Eclipse, choose **File > Import... > Existing Maven Projects** and select the root directory of this project.
3. You can now try either of the supplied datasets using one of the two _Eclipse launch_ files in the project: 
	1. Eclipse-dataset_small.launch (mapped to => A-small-practice.in)
	2. Eclipse-dataset_large.launch (mapped to => A-large-practice.in)
