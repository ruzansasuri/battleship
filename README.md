# battleship
2 player battleship using Java Swing and RMI

This is a small two player battleship game I built for a Java assignment in RIT. It uses Java Swing for its GUI and RMI to establish a connection between two remote machines.

To start the game, you should download the executable folder and either battleship.bat(for Windows) or battleship.sh(for Linux/Mac OS).
Then run the either of the last to mentioned files.

The user must then enter his opponents IP address, and two identifying characters, one of himself and the second as hasa been entered by their opponent.
Users must then start by building their fleet. To accomplish this you must start by placing a ship on the grid by clicking the square. click multiple times to change the orientation of the ships. The ship being placed can be changed using the radio buttons. Once all 4 ships are placed the user will have the opttion to connect to their opponent. 
Once a connection is established, the user may now play the game. Rules for the game follow the standard rules as written on Wikipedia, except that no ships can be placed on ajacent squares. The game ends if either user destroys all their opponents ships or if either player disconnnects from their opponent, which is an automatic forfeit.

Have fun!
