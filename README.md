--------------------------------------------------------------
Super Robot Wars GC Script Editor by Dashman
--------------------------------------------------------------

What is this?
-----------------

This is a program that allows you to edit the script files of the game Super Robot Wars GC. 

To get access to those files, you'll need to extract the file add01dat.bin from the ISO and extract its contents with the splitter program (SRW GC Bin Splitter). The result will be more than 3000 files containing the story text of the game. With this program you can edit most of those files.


How do I use the program?
--------------------------

First of all, you'll need to have Java installed in your computer.

Double-click on the jar file to execute it.

Go to File -> Open... and open one of the bin files extracted from add01dat.bin.

If it's a valid file (with script text in it), the Navigation section will be available to move through the different dialogues inside the file.

To the left is the original script, on the right is where you'll write your edit / translation.

You'll notice the "Speaker" in the Edit section is automatically translated, so you only have to care about translating the text.

...and some other stuff.

The other stuff
-----------------

No, don't worry, I was just gonna tell you what are the buttons and other stuff for. Nothing complicated. Just long. Like, very. Okay, read:

You'll notice there's some indicators on top of the original text. 

"Sex Route" and "Robot Route" are related to your character (male or female) and robot (super or real) choice in the game. When one of these indicators says something different than "COMMON", you know the dialogue you're editting only appears in game if you're in the specified route.

"Voiced Line" indicates if this line plays a voice clip (like when Trider launches in the first stage or when RaijinOh combines shortly after). "Remote Line" indicates if the character portrait is shown through a communicator-like screen.

Next to the Speaker in the Edit area, you see "Use alternative text for female". This can *only* be accessed if you're in a COMMON sex route. If you enable this, the text area under it will be enabled and will copy the text from the text area to its left. Now the text you write on the left will be shown only if you're playing the male character and the one on the right will be for the female.

Why is this necessary? Sometimes people talk about the MC, and Japanese can be rather genderless at times. If you find that a line speaks of the MC and you need to use a he / she, Mr. / Miss, his / her and so on, check this option.

Notice the Check buttons under the text areas? When you click on them, it tells you the maximum width in pixels that your text has. The max width for a dialogue seems to be 324 pixels, which is 18 or 19 characters with the game's normal font. UPDATE: These buttons no longer exist.

The option "Use VWF to calculate width" is related to the previous one, but is not available yet. Once VWF font is implemented in the game, this option will let you check the width using the VWF's width values (you'll be able to write wider lines). UPDATE: This option is now enabled by default.

"Number of lines per dialogue" lets you specify how many lines in your edited text will be considered a new dialogue. What this means is you can EXPAND any dialogue. If you write 4 lines in a dialogue and the number of lines is set to 3, when you save the file that dialogue will be stored as 2 dialogues (one with 3 lines and another one with 1). If you write 7 you'll get 3 dialogues, and so on. NOTE: If you expand a dialogue with Voice, the Voice command won't be present in the expanded dialogues (just the first one). The Remote property is inherited by all the expanded dialogues.

Why the option to choose between 3 and 4? Well, 3 lines gives very little space to write. As it is now, the game only displays 3 lines, but there might come a time in which it becomes hacked to display 4. When that happens, set it to 4. UPDATE: We have means to use 4 lines now, so the default value is 4.

If you disable "Convert keystrokes to SJIS", you'll write normal ASCII characters. The game actually recognizes ASCII characters (as long as you write an even number of them) and has a smaller font for them. The problem with this is that some pairs of ASCII characters are actually codes that the game uses for different effects. For a list of the ones we know, either press F1 or go to ? -> Help.

Now, if you wrote a bunch of ASCII stuff or just pasted it there (like from the text files we were using to translate the game with textures), you can go to "Tools -> Conver LEFT / RIGHT textarea to SJIS", which I think is pretty clear about what it does.

And finally, if you didn't notice, there's the "File -> Save as..." option to save what you've edited into another file. Please, don't forget to keep backups of the original files. Overwriting an existing file is not recommended (I think that if the overwritten file is bigger than what you write, there can be some data left from it after saving). Also keep in mind that only the Edit part is saved in the new file - when you open the saved file, your old Edit will become the Original!! Don't forget that!


--------------------------------------------------------------
Contents of extracted BIN files
--------------------------------------------------------------
* 0000: Unused ending? There's no speaker names in the dialogue, so the first line will be recognize as the speaker.

* 0001: Probably the codes for the scrolling text at the beginning. (has no text)

* 0002: Test script.

* 0003 - 0005: Dummy scripts.

* 0006: ??? (has no text)

* 0007 - 0022: Suspend game dialogue (after saving and choosing not to continue).

* 0023: Most probably, black screen text at the beginning of the intro ("somewhere at the far end of the galaxy" or something like that).

* 0024: Stage 01 intro part 1.
* 0025: Stage 01 intro part 2 (Watta's school).
* 0026: Stage 01 scenario pre-battle dialogue.
* 0027: Stage 01 event 1 dialogue (Akudamas die)
* 0028: Stage 01 event 2 dialogue (Mecharobots die)
* 0029: Stage 01 scenario post-battle dialogue.
* 0030: Stage 01 outro part 1.
* 0031: Stage 01 outro part 2 (Watta's company).

* 0032: Stage 02 intro.
* 0033: Stage 02 scenario pre-battle dialogue.
* 0034: Stage 02 event 1 dialogue.
* 0035: Stage 02 event 2 dialogue.
* 0036: Stage 02 event 3 dialogue (Mecharobots die?).
* 0037: Stage 02 event 4 dialogue (Jack arrives).
* 0038: Stage 02 scenario post-battle dialogue.
* 0039: Stage 02 outro.

And so on. I won't list the contents of the 3000+ files here, you get how it goes well enough with that.