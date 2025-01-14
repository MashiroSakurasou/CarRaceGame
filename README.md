
Car Simulator Program Readme
Overview
This program implements a simple interactive car game simulator using Java Swing. The simulator involves controlling a "car" to avoid collisions with randomly moving obstacles, accumulating points for successful maneuvering.


Name: Tiancheng Zhou, Zhiyuan Xiao
The date last modified : December 2nd

Features
User Interaction: Control the movement of a car using arrow keys.
Score Tracking: Keep track of the user's score based on successful navigation.
Color Selection: Choose from different colored cars.
Obstacle Avoidance: Dodge randomly moving obstacles to prevent collisions.
Score Ranking: Display the highest scores achieved.

Components
Control Panel: Contains buttons for starting the game, selecting a car color, and displaying score-related information.
View Panel: Renders the game environment, showing the user's car and randomly moving obstacles.
Listeners: Key listeners enable car movement, and action listeners manage button clicks and score updates.
How to Use
Start Games: Click the "Start games" button to initiate the game. If you do not start the game, the car will not move and no scores will be recorded. Every time you choose a different team, you need to start a game.
Choose the Team: Select a car color by clicking one of the numbered buttons (1-4).
Gameplay: Use the arrow keys to navigate the car and avoid collisions with the randomly moving obstacles.
Score: The score is displayed in the "Your Score" field.
Highest Score: The highest scores achieved during the game session are displayed in the "Highest score" field.
Game End: The game ends if the user's car collides with an obstacle or reaches the boundary of the game area.

Notes
Obstacle Collision: If the user's car collides with an obstacle or reaches the game area's boundary, the game ends, and the highest scores are updated and displayed.
Score Ranking: The program stores the top three highest scores attained during the game session.

Flourish
Non-stationaryobjects
Randomized stationary obstacles that destroy the cars
High Score List

How to Run
Compile: Compile the Java file containing the Simulator class.
Run: Execute the compiled file to launch the simulator.
Game Interface: The graphical interface will appear, allowing interaction with the game controls.
Start game: Press the "start" botton

Development Environment
Language: Java
Framework: Java Swing
