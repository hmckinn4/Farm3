
-- create the database
CREATE DATABASE FarmDB;

-- use the database
USE FarmDB;

-- create the Farm table
        CREATE TABLE Farm (
        Farm_ID INT NOT NULL AUTO_INCREMENT,
        Farm_Name VARCHAR(255) NOT NULL,
        Farm_Address VARCHAR(255) NOT NULL,
        Farm_Phone VARCHAR(20) NOT NULL,
        PRIMARY KEY (Farm_ID)
);

-- create the Field table
        CREATE TABLE Field (
        Field_ID INT NOT NULL AUTO_INCREMENT,
        Farm_ID INT NOT NULL,
        Field_Name VARCHAR(255) NOT NULL,
        Field_Size INT NOT NULL,
        Field_Location VARCHAR(255) NOT NULL,
        PRIMARY KEY (Field_ID),
        FOREIGN KEY (Farm_ID) REFERENCES Farm(Farm_ID)
);

-- create the Crop table
        CREATE TABLE Crop (
        Crop_ID INT NOT NULL AUTO_INCREMENT,
        Crop_Name VARCHAR(255) NOT NULL,
        Crop_Variety VARCHAR(255) NOT NULL,
        Crop_Season VARCHAR(255) NOT NULL,
        PRIMARY KEY (Crop_ID)
);

-- create the Field_Crop table
        CREATE TABLE Field_Crop (
        Field_ID INT NOT NULL,
        Crop_ID INT NOT NULL,
        Start_Date DATE NOT NULL,
        End_Date DATE NOT NULL,
        PRIMARY KEY (Field_ID, Crop_ID),
FOREIGN KEY (Field_ID) REFERENCES Field(Field_ID),
FOREIGN KEY (Crop_ID) REFERENCES Crop(Crop_ID)
);

-- create the Employee table
        CREATE TABLE Employee (
        Employee_ID INT NOT NULL AUTO_INCREMENT,
        Farm_ID INT NOT NULL,
        Employee_Name VARCHAR(255) NOT NULL,
        Employee_Position VARCHAR(255) NOT NULL,
        Employee_Phone VARCHAR(20) NOT NULL,
        Employee_Address VARCHAR(255) NOT NULL,
        PRIMARY KEY (Employee_ID),
        FOREIGN KEY (Farm_ID) REFERENCES Farm(Farm_ID)
);

-- create the Equipment table
        CREATE TABLE Equipment (
        Equipment_ID INT NOT NULL AUTO_INCREMENT,
        Equipment_Name VARCHAR(255) NOT NULL,
        Equipment_Type VARCHAR(255) NOT NULL,
        Equipment_Serial VARCHAR(255) NOT NULL,
        PRIMARY KEY (Equipment_ID)
);

-- create the Equipment_Task table
        CREATE TABLE Equipment_Task (
        Task_ID INT NOT NULL,
        Equipment_ID INT NOT NULL,
        Task_Description VARCHAR(255) NOT NULL,
        Task_Date DATE NOT NULL,
        Task_Status VARCHAR(20) NOT NULL,
        PRIMARY KEY (Task_ID, Equipment_ID),
FOREIGN KEY (Equipment_ID) REFERENCES
        Equipment(Equipment_ID)
);

-- create the Task table
        CREATE TABLE Task (
        Task_ID INT NOT NULL AUTO_INCREMENT,
        Field_ID INT NOT NULL,
        Task_Name VARCHAR(255) NOT NULL,
        Task_Description VARCHAR(255) NOT NULL,
        Task_Date DATE NOT NULL,
        Task_Status VARCHAR(20) NOT NULL,
        PRIMARY KEY (Task_ID),
        FOREIGN KEY (Field_ID) REFERENCES Field(Field_ID)
);

-- create the Employee_Task table
        CREATE TABLE Employee_Task (
        Employee_ID INT NOT NULL,
        Task_ID INT NOT NULL,
        PRIMARY KEY (Employee_ID, Task_ID),
FOREIGN KEY (Employee_ID) REFERENCES Employee(Employee_ID),
FOREIGN KEY (Task_ID) REFERENCES Task(Task_ID)
);

-- create the Harvest table
        CREATE TABLE Harvest (
        Harvest_ID INT NOT NULL AUTO_INCREMENT,
        Field_ID INT NOT NULL,
        Crop_ID INT NOT NULL,
        Harvest_Date DATE NOT NULL,
        Harvest_Weight INT NOT NULL,
        PRIMARY KEY (Harvest_ID),
        FOREIGN KEY (Field_ID) REFERENCES Field(Field_ID),
        FOREIGN KEY (Crop_ID) REFERENCES Crop(Crop_ID)
);

-- create the Supplier table
        CREATE TABLE Supplier (
        Supplier_ID INT NOT NULL AUTO_INCREMENT,
        Supplier_Name VARCHAR(255) NOT NULL,
        Supplier_Address VARCHAR(255) NOT NULL,
        Supplier_Phone VARCHAR(20) NOT NULL,
        PRIMARY KEY (Supplier_ID)
);

-- create the Equipment_Supplier table
        CREATE TABLE Equipment_Supplier (
        Equipment_ID INT NOT NULL,
        Supplier_ID INT NOT NULL,
        PRIMARY KEY (Equipment_ID, Supplier_ID),
FOREIGN KEY (Equipment_ID) REFERENCES Equipment(Equipment_ID),
FOREIGN KEY (Supplier_ID) REFERENCES Supplier(Supplier_ID)
);

-- create the Crop_Supplier table
        CREATE TABLE Crop_Supplier (
        Crop_ID INT NOT NULL,
        Supplier_ID INT NOT NULL,
        PRIMARY KEY (Crop_ID, Supplier_ID),
FOREIGN KEY (Crop_ID) REFERENCES Crop(Crop_ID),
FOREIGN KEY (Supplier_ID) REFERENCES Supplier(Supplier_ID)
);



// Path: src/main/resources/db/creation/insert.sql

