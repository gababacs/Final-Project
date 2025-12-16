# Final-Project
Bike My Type: Bike Rental Management System

Group Name: Pokpok Girls (Bacsal, Glaziela B. and Palma, Angelo Joachim L.)

Course: CMSC 22 - Object-Oriented Programming
Status: In Development

Overview

    Bike my Type is a bike rental management system developed to demonstrate core principles of Object-Oriented Programming (OOP). The application provides an interactive Java Swing-based GUI that allows users to browse a diverse inventory of bikes, manage their rental selection in a cart, and complete a validated checkout process.

    The system is designed to handle all aspects of a rental transaction, from initial inventory display to final receipt generation, using concepts like inheritance and polymorphism to manage different bike types.

Key Features

    This project implements the following core functionalities:

    1. Interactive GUI (Java Swing)

        Main Display: Displays all available bike types, including their name, photo (placeholder image/icon), hourly rate, and available quantity.

        User Interface: Features dedicated panels for browsing the inventory, viewing the cart, and displaying the final receipt.

        Details View: A "View Details" function to display the full description of a selected bike.

    2. Rental and Transaction System

        Cart Management: Users can add multiple bike types to their cart (only once per type) and select the rental duration (number of hours). 

        Input Validation: The system prevents the user from renting more than one (1) unit of a specific bike type. In receipt generation, the user may only input characters in the "Name" field and a total of 10 integers in the "Contact Number" field.

        Cost Calculation: Total rental cost is dynamically calculated using the formula:


        $$\text{Total Cost} = \sum (\text{Rate per Hour} \times \text{Number of Hours} \times \text{Quantity})$$

    3. OOP Implementation

        Inheritance: An abstract Bike class defines common properties, which are extended by concrete subclasses (EBike, MountainBike, etc.).

        Encapsulation: Dedicated classes were used in the system's logic separate to the UI class. The RentalSystem class manages stock, rates, and transaction data separately from the core UI. The Receipt class generates a receipt that displays the user's total fees and rented bike/s. 

    4. Receipt Generation

        Upon confirmation, a detailed receipt is displayed, containing:

            1. Renter's identifying information (Name and Contact Number).

            2. A clear line-item breakdown of each bike rented (quantity, rate, hours, and subtotal).

            3. The final grand total cost.

Project Structure

    The logical architecture separates the core business logic (inventory and rentals) from the UI presentation.

Component and Description

    1. BikeClasses.java (Abstract)

        The base class for all rental items, defining common methods and properties.

    2. EBike.java, MountainBike.java, etc.

        Concrete bike model definitions extending Bike.

    3. RentalSystem.java
        
        Includes boolean values (isRented, returnBike, rentBike) and the calculation method to be called in the main class.

    4. Receipt.java

        A dedicated class that generates the receipt.

    5. CartItem.java

        A dedicated class that deals with rented bikes. 

    6. CartDialog.java

        A dedicated class that generates a JDialog for checking out.

    7. RentalGUI.java

        The primary Java Swing JFrame that orchestrates all panel views (Homepage, Cart, Receipt).


Java Development Kit (JDK) 8+

    Primary programming language.

Java Swing

    Used for building the Graphical User Interface (GUI).

File I/O / Serialization

    (Placeholder: Data Persistence method used to save and load inventory data between sessions.)

Getting Started

    Prerequisites

        Java Development Kit (JDK) 8 or later: Required to compile and run the Java source code.

        Java IDE (Recommended): An IDE like IntelliJ IDEA or Eclipse simplifies project management.

    Running the Application

        Open the Project: Import the cloned directory into your Java IDE.

        Run: Execute the main method located in RentalSystem.java.

Group Members

    This project was completed by the Pokpok Girls group, students of CMSC 22 - Section 1.

    Primary Roles

        Bacsal, Glaziela B.

        Palma, Angelo Joachim L.
