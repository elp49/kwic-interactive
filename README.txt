Edward Parrish
SE 311: Software Architecture II
Assignment 3 - KWIC
Professor Boris Valerstein

The static main method is located in kwic.src.kwic.MasterController.
Note: The sorting feature ignores case and white space.

IO Configuration:
	The file "kwic/config" contains configurations for user IO, line IO, and the
	storage methods. You may open the config file and set the variables
	according to their legal values which are listed in the config file.

Commands:
	Add command for adding a new line when typing in 'a'
	Delete command for deleting a line when typing in 'd'
	Print command for printing shifts sorted alphabetically when typing in 'p'
	Quit command for exiting the system when typing in 'q'

Issues:
	- There is no way to configure the IO methods from console.
	- Not setting the configurations properly in the config file could lead to
	undefined behavior.
	- Updating the config while the program is still running does not 
	mmediately update preferences.  
