Research Subject: Africa - Water is a Basic Human Need.

Reason for Choosing MVC Design Pattern:
MVC is an useful option for applications that involves the requirements of the assignment such as user interface (GUI) for example and it separates the system into three components that for me represents a very organized approaching:
Model: manages object creation, deletion, and serialization.

View: displays data in the GUI.

Controller: handles user input and coordinates between the model and view. This separation simplifies debugging, future updates, and scalability.

Implementation Plan:
Model:
Create a Research class that implements Serialisable.
Implement a ResearchController class for CRUD operations and file handling.

RMI Server:
RMIInterface with shared methods like  for example addResearch,
Implementation of the server (RMIServer) and client (RMIClient).

GUI (Swing):
UI for user interaction and to display research data.
Forms and buttons for creating, editing, and deleting research.

Serialization:
Use ObjectOutputStream and ObjectInputStream to save and load the research list.

Application Flow:
1 - Server Initialisation.

2 - Client Connection.

3 - Gui Interactions.

4 - Serialization.
