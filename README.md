Wordel
------
A simple (and messy) mockup and take on the famous word game, Wordle, using JavaFX.
Supports a two-player mode for competitive play.

<img width="310" height="766" alt="image" src="https://github.com/user-attachments/assets/061c9644-c66f-41a5-a9ba-14f9c3d354e6" />
<img width="310" height="766" alt="image" src="https://github.com/user-attachments/assets/b413e2e1-14d6-47a7-986b-a158625726aa" />
<img width="310" height="910" alt="image" src="https://github.com/user-attachments/assets/aee673c4-e107-446e-a4f6-15721a2d101d" />
<img width="310" height="814" alt="image" src="https://github.com/user-attachments/assets/ac0956b9-6a50-4740-9d83-d92f7734a450" />

Final project for CISC190 at San Diego Miramar College

-----

Course Topics:
-----
- Java overview, JVM, OOP concepts
  
This project uses a very simplistic and barebones approach to object oriented programming, containing the majority of the code packaged into various classes, interacting between each other and using methods.

<img width="151" height="128" alt="image" src="https://github.com/user-attachments/assets/30991ca2-36ec-4c7b-84fd-579dc1bb91a1" />

---
- Variables, types, input/output

Used throughout the project of course.

<img width="955" height="318" alt="image" src="https://github.com/user-attachments/assets/cffbaa1a-4e28-4a9c-88fc-6e270b30e0f7" />

A myriad of fields in `UI.java` from lines 45 to 62.

---
- Control flow: if, switch, loops

Also implemented intensively throughout the whole project.

Some standout examples are this for each loop at 122 in `UI.java`

<img width="652" height="184" alt="image" src="https://github.com/user-attachments/assets/aa712e6e-186a-4f1e-bfda-b902c6f81916" />

and this switch case statement at 190 in that same file.

<img width="467" height="369" alt="image" src="https://github.com/user-attachments/assets/e35e3f5e-808c-405d-b000-92f473555edf" />

---
- Exceptions (intro), debugging

Debugging has been implemented in the project through output in the console to regulate connection between the user and interface. One example in `Controller.java`, beginning at 83:

<img width="788" height="402" alt="image" src="https://github.com/user-attachments/assets/f0fd8479-36f5-49e0-9924-05a5b1f1e951" />

Exceptions are also used inside of the project, mainly used to prevent any issues that could lead to a termination of the program, like missing files. `UI.java`, lines 86 - 94.

<img width="1026" height="240" alt="image" src="https://github.com/user-attachments/assets/b7b3ab80-9f42-494a-b634-847af1c604a6" />

---
- Methods, parameters, blocks, scope

There is a large number of different methods for various purposes. Here are some in `Controller.java`.
<img width="722" height="309" alt="image" src="https://github.com/user-attachments/assets/9e294dcd-0dbe-42fa-89c4-e776a153194b" />
<img width="204" height="328" alt="image" src="https://github.com/user-attachments/assets/e89e473f-645e-467c-8b20-1ae9e85c3386" />
<img width="889" height="302" alt="image" src="https://github.com/user-attachments/assets/58aa28fa-3fbe-477e-816d-468ce05de1e9" />

Here's the constructor of the whole class too.

<img width="1170" height="168" alt="image" src="https://github.com/user-attachments/assets/3a481b2e-abb3-4ab8-8ff7-b2747975dd62" />

---
- 	Arrays & ArrayLists

This project hinged heavily on the utilization of arrays. Arrays were mainly used to store the characters of word guesses and UI elements. Here are some examples, mainly in `UI.java`.

<img width="374" height="89" alt="image" src="https://github.com/user-attachments/assets/a30552eb-5901-4e93-8f48-2ffa03abb906" />
<img width="809" height="114" alt="image" src="https://github.com/user-attachments/assets/624986ee-9226-4e75-bea4-412f415eae9e" />
<img width="904" height="101" alt="image" src="https://github.com/user-attachments/assets/40727545-f0bb-4e8c-b8d5-857dff1a7513" />

---
- Objects & classes

Albeit this project has rather few projects, they do make use of each other. These classes are `UI.java`, `Controller.java`, `WordBank.java`, and (technically) `Constants.java`.
Here are some instances of objects being created and called upon.

<img width="726" height="194" alt="image" src="https://github.com/user-attachments/assets/635012a7-7e2c-4363-8e93-253c9547f361" />
<img width="507" height="194" alt="image" src="https://github.com/user-attachments/assets/9eddd80f-5ba5-4a81-b5b8-69be1fa36f48" />
<img width="458" height="150" alt="image" src="https://github.com/user-attachments/assets/d0157f88-cbe8-4ba6-b8f5-87416d8939a7" />

---
- 	Abstract classes & interfaces

This project only briefly uses inheritance through having our Timer subclass extend Thread, in order to override `run()` for our timer to run on background threads. The main UI class also extends Application in order to override the `start()` method and get the user interface working.

<img width="358" height="65" alt="image" src="https://github.com/user-attachments/assets/4b529add-c5fa-4193-b828-dbf56bb96dac" />
<img width="500" height="75" alt="image" src="https://github.com/user-attachments/assets/1cc59a05-825d-4d8e-89e2-6a76363261f5" />
<img width="902" height="340" alt="image" src="https://github.com/user-attachments/assets/99a2a9ee-029c-4bdf-bad0-dce50d03c218" />

---
- Files

The bulk of the system to verify if user word input is valid is through evaluating through two `.txt` files in order to get a match or pick a random word. `WordBank.java` best demonstrates this, using InputStreamReader and BufferedReader to scan those large files.

<img width="1185" height="457" alt="image" src="https://github.com/user-attachments/assets/316c4859-e5f6-4926-878e-8bdf5992b90b" />

---
- JavaFX

GUI was done by manual creation of nodes and etc, while using their event handlers to create communication throughout the program. Animation was also added through the use of JavaFX's animation package. The majority of the JavaFX code is in `UI.java`, but there is admittedly a lot of other bits and pieces in `Controller.java`.

<img width="653" height="288" alt="image" src="https://github.com/user-attachments/assets/6e355916-a17b-4fa7-a60e-b73f21c01846" />
<img width="614" height="262" alt="image" src="https://github.com/user-attachments/assets/77efd5ed-6c7a-444d-be79-89af7c753ec4" />

---
- Robustness & coding standards

There is an abundance of bits and pieces of comments notating on parts of the program. Classes use upper camel case, methods and typical variables use lower camel case, enums or constants use screaming snake case.

<img width="740" height="242" alt="image" src="https://github.com/user-attachments/assets/14cce92e-e536-4d8b-9ac9-262f60c49ff5" />
<img width="317" height="127" alt="image" src="https://github.com/user-attachments/assets/bec193a9-abde-4dd6-af5c-7382f2274e7e" />
<img width="832" height="384" alt="image" src="https://github.com/user-attachments/assets/f9848671-af88-4ad4-8405-add8a1826076" />

Robustness is demonstrated mainly through defensive programming with various if statements to evaluate variable or object states and with exceptions to prevent any improper inputs.

<img width="930" height="181" alt="image" src="https://github.com/user-attachments/assets/380ac4e2-9593-4022-a625-299dd6f695a3" />
<img width="854" height="347" alt="image" src="https://github.com/user-attachments/assets/a0f27a35-3813-4a33-97ed-da4a2202aa7f" />
<img width="726" height="215" alt="image" src="https://github.com/user-attachments/assets/e97ac38b-9555-47ad-a2d2-8cbb47d10779" />

---
- Multithreading

Multithreading was used for the timer in the Two Person mode. This is so the timer can run independently of the UI, responding without freezing. The timer updates an on screen Label to show the time, but this can only be done on the JavaFX thread, else IllegalStateException will be thrown. Platform.runlater(...) is used to queue the code to run on the JavaFX App Thread and prevent conflicting desync or other issues.

Here is the entirity of the Timer subclass:

<img width="590" height="631" alt="image" src="https://github.com/user-attachments/assets/f49d15cd-76e7-4e9e-906d-27ade5dcd709" />

===





