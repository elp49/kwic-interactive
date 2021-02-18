# KWIC Interactive

The static main method is located in kwic.src.kwic.MasterController.
Note: The sorting feature ignores case and white space.

## IO Configuration:
The file "kwic/config" contains configurations for user output, line IO,
and the storage methods. You may open the config file and set the variables
according to their legal values which are listed in the config file.

## Commands:
    Run with console input    runs the kwic program accepting line input via the console
    Run with file input       runs the kwic program accepting line input via the file
                              which is set in kwic/config by the LINE_INPUT_FILE variable
    Open interactive mode     initializes the interactive GUI
    Quit                      terminates processing

## Interactive GUI:
When the user selects Open interactive mode from the main menu, two
graphical windows will open.

The first window is the line input window where the user may: input a line
and add it to the line storage, clear the line input field, clear the line
storage, and select and delete a line from the line storage.
	
The second window is a graphical display of the line storage. It shows a
live feed of the shifted and sorted lines which are currently in the line
storage. When the user adds a new line via the line input window, the line
will be shifted and then sorted into the existing line storage. When the
user selects a line to be deleted, each of the shifted/sorted lines which
originated from that line will be removed from this window.

## Issues:
- There is no way to configure the user/line output and storage methods from
the console.
- Not setting the configurations properly in the config file could lead to
undefined behavior.
- Updating the config while the program is still running does not 
update user preferences until the program is restarted.  
