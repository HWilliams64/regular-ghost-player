# Regular Ghost Player

This is an empty ghost project where you will develop your regular ghost application.


## Getting started

*It is assumed you are running your code in the courses preconfigured Carbon Workspace*

1) Open your terminal and change the directory to a location you would like to save this project. Use the `cd` command
to change the directory. Below is an example:

    ```shell script
    cd file/path/to/directory/ 
    ```
2) Clone this repository by running the following command:

    ```shell script
    git clone https://github.com/HWilliams64/regular-ghost-player.git
    ```
   
   You will now see a directory by the name of `regular-ghost-player` in the current directory. This is your 
   ghost project.

3) Open the `regular-ghost-player` directory using the `cd` command:
    
    ```shell script
    cd regular-ghost-player
    ```
   
4) Now that your current directory is in `regular-ghost-player`, create a jar file by running the following command:
    
    ```shell script
    gradle :createjar
    ```
   
    You will notice a file by the name of `your_team_name.jar` in your current directory. This is your ghost 
    application.
    
    To change the name of the jar file open the build.gradle file and modify line 24. After changing the name in the 
    build.gradle file, run the `createjar` command from above again. 
    
5) Start the ghost game by running the GhostApp.jar file with your jar file. Run the following command:

    ```shell script
    java -jar GhostApp.jar your_team_name.jar
    ```
   
    On your desktop you will see a window popup where you can begin to play the ghost game. You are done!
    
    **WARNING**: You will not be able to fully play the game until you add the game logic.
    
### Adding Game Logic

1) Add your code from the Ghost Primer assignment we completed last week to the empty 
[FileManager.java](src/main/java/myCode/FileManager.java) class and 
[Dictionary.java](src/main/java/myCode/Dictionary.java) class.

2) Open the [GhostSkeleton](src/main/java/myCode/GhostSkeleton.java) class scroll down to the `gameLogic()` function 
(line 90ish) and add your logic for letter selection. Use the `wordFragment` variable in combination with your 
dictionary to find a word. Once you have selected a word then select a letter.

4) Recreate your jar file by running `gradle :createjar`. Then run Ghostapp.jar file to test your changes.

    Don't forget, you must recreate your jar file each time you change your code.

5) By completing this you have effectively completed the entire project.

Feel free to add as many other classes and files as needed to achieve a winning solution

### Submitting

Submit your jar file. Make sure your jar file is named something other than `your_team_name.jar`. 