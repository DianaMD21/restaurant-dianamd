# Monegro's Restaurant
## Description:
Monegro's Restaurant is a sofware developep to be used in a restaurant in which the client would be able to decide what food he/she wants. The order will go to the cashier to be paid, and then to the kitchen to be prepared in a specific time period that will be calculated depending on the experience and size of the order.
 ## Stack
*What you will need:*
> - Windows Operative System of 64-Bits.
> - Intellij IDE.
> - Java JDK 17.0.2 Development Kit.

## Setup
1. Download the JDK kit for your operative system. This the link of the version used in this project: https://www.oracle.com/java/technologies/javase-jdk16-downloads.html
2. Go to the folder where you installed the JDK development kit and enter the bin folder. By default is Program Files-> Java->bin. Copy that path.
3. Go to Settings->System->Environment variables-> Edit the system environment variables. In the Advanced tab, click Environment Variables. Click Path, and then Edit. Add the path for the Java bin folder, and click Ok.
4. Go to the folder where you installed the JDK development kit and copy that path.
5. Click on "New" in the System Variables section. In the "Variable name" section you can put whatever name you prefer, in all caps, for example: JAVA_HOME. In the "Variable value" paste the path of the JDK folder. Click Ok.
6. Download and install the IntelliJ IDE. This is the link: https://www.jetbrains.com/idea/download/#section=windows
7. The Eclipse IDE is ready to work.

## Project Structure:
This project is conformed by multiple entities.

## Entities:
> - Cashier: Is responsible for charging the order.
> - Chef: Is responsible for cooking the food of the order, it has an attribute called YearsOfExperience, which specifies the years of experience a chef has, because this information will be taken into account when calculating how long it will take to prepare an order.
> - Client: Is responsible for ordering the food and paying.
> - Waiter: Is responsible for taking the order.
> - Employee: It's a general entity that has the properties of User. Every employee of the restaurant has the properties of Employee.
> - FinalProduct: It's responsible to hold a list of products that conforms a plate.
> - Product: Entity that has the information of an specific product. That product can be as simple as a loaf of bread and as complex as a pizza.
> - Menu: Entity that has the menu to be presented to the client so that he/she can choose.
> - MenuItem: Entity that has a specific plate or combos of them.
> - Order: Entity that has all the plates or products that the client wants to order, the id of the order, the cashier,the final price.
> - OrderDetail: Entity that has one plate, with it's name, price, how many of those plates the client wants.
> - Stock: Entity that has the information of each product (ingredient) that the restaurant has.
> - StockProduct: Entity that has the information of ONE product in stock.
> - Person: Entity that has the information of all the different people that eat/work in the restaurant.
    ![Diagrama](https://user-images.githubusercontent.com/68748760/187987141-a85bc977-1ae2-4315-9535-51f9da0cfeb2.png)
    ![product diagram](https://user-images.githubusercontent.com/68748760/189142265-d22ad4a4-9fce-4933-95dc-6db12210e237.png)