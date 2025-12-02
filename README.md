[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/J_c8sizy)
# MassiveMotion
CS 245 Project 02 

 - This project is a 2D animation of a star system, built in Java using Swing and AWT. The goal is to implement and test four different versions of the List data structure.

     The simulation reads all configuration (like window size, star position, and comet generation probability) from a properties file. It features a single stationary star and randomly generated comets that are added to and removed from the active list.

  - Link to ScreenRecording: https://drive.google.com/file/d/1HaAlMnUwRfeSAf8R6GLsB1WKjmTDhnLD/view?usp=sharing

Construction
- 1. cd project02-massivemotion-AidanF321/src
- - Change directory into file source
- 3. javac src/*.java
-  - compile all files
- 4. java MassiveMotion MassiveMotion.txt
- - run code with input parameters from a txt as the first argument


Structure

- MassiveMotion.java
    - It extends JPanel to act as the drawing canvas and implements ActionListener to handle the animation loop. It is responsible for reading the properties file, managing the List of bodies, and handling all simulation logic.

- CelestialBody.java
    - A simple data class that holds the state of a single object (position, velocity, size, and color)

- List.java
    - The provided interface that all our data structures must implement.

Lists
- ArrayList
    - Is an array-based list. 
        - It features an O(1) get() operation but an O(n) remove(int) and add(int, T) due to element shifting.
- LinkedList.java
    - A singly-linked list implementation with head and tail pointers. It has an O(1) add(T) (add to end) but O(n) for get(), remove(), and add(int, T)
- DoublyLinkedList
    - A doubly-linked list with head and tail pointers. It has O(1) add(T) and an optimized O(n) get() by searching from the head or tail, whichever is closer.
- DummyHeadLinkedList
    - A singly-linked list that uses a "sentinel" or "dummy" head node. This simplifies the add(int, T) and remove(int) methods by removing the special case for index == 0.

Which of these classes is optimal for this problem?
- The ArrayList is the most optimal implementation for this project 
    - The simulation's actionPerformed method, which runs on every frame, contains a loop that iterates through every body in the list. Inside this loop, it must call bodies.get(i) to check the body's position. The ArrayList's get(i) operation is O(1) (constant time). All LinkedList variants have an O(n) or O(n/2) get(i) operation, which requires traversing the list therefore less efficient.