-- Table `FarmDB`.`Animal`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `FarmDB`.`Animal` (
        `Animal_ID` INT NOT NULL AUTO_INCREMENT,
`Animal_Name` VARCHAR(255) NOT NULL,
`Animal_Type` VARCHAR(255) NOT NULL,
`Animal_Breed` VARCHAR(255) NOT NULL,
PRIMARY KEY (`Animal_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `FarmDB`.`Farm`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `FarmDB`.`Farm` (
        `Farm_ID` INT NOT NULL AUTO_INCREMENT,
`Farm_Name` VARCHAR(255) NOT NULL,
`Farm_Address` VARCHAR(255) NOT NULL,
`Farm_Phone` VARCHAR(20) NOT NULL,
PRIMARY KEY (`Farm_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `FarmDB`.`Barn`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `FarmDB`.`Barn` (
        `Barn_ID` INT NOT NULL AUTO_INCREMENT,
`Farm_ID` INT NOT NULL,
`Barn_Name` VARCHAR(255) NOT NULL,
`Barn_Location` VARCHAR(255) NOT NULL,
PRIMARY KEY (`Barn_ID`),
INDEX `Farm_ID` (`Farm_ID` ASC) VISIBLE,
CONSTRAINT `barn_ibfk_1`
        FOREIGN KEY (`Farm_ID`)
REFERENCES `FarmDB`.`Farm` (`Farm_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `FarmDB`.`Crop`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `FarmDB`.`Crop` (
        `Crop_ID` INT NOT NULL AUTO_INCREMENT,
`Crop_Name` VARCHAR(255) NOT NULL,
`Crop_Variety` VARCHAR(255) NOT NULL,
`Crop_Season` VARCHAR(255) NOT NULL,
PRIMARY KEY (`Crop_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `FarmDB`.`Customer`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `FarmDB`.`Customer` (
        `Customer_ID` INT NOT NULL AUTO_INCREMENT,
`Customer_Name` VARCHAR(255) NOT NULL,
`Customer_Address` VARCHAR(255) NOT NULL,
`Customer_Phone` VARCHAR(20) NOT NULL,
PRIMARY KEY (`Customer_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `FarmDB`.`Employee`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `FarmDB`.`Employee` (
        `Employee_ID` INT NOT NULL AUTO_INCREMENT,
`Farm_ID` INT NOT NULL,
`Employee_Name` VARCHAR(255) NOT NULL,
`Employee_Position` VARCHAR(255) NOT NULL,
`Employee_Phone` VARCHAR(20) NOT NULL,
`Employee_Address` VARCHAR(255) NOT NULL,
PRIMARY KEY (`Employee_ID`),
INDEX `Farm_ID` (`Farm_ID` ASC) VISIBLE,
CONSTRAINT `employee_ibfk_1`
        FOREIGN KEY (`Farm_ID`)
REFERENCES `FarmDB`.`Farm` (`Farm_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `FarmDB`.`Equipment`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `FarmDB`.`Equipment` (
        `Equipment_ID` INT NOT NULL AUTO_INCREMENT,
`Equipment_Name` VARCHAR(255) NOT NULL,
`Equipment_Type` VARCHAR(255) NOT NULL,
`Equipment_Serial` VARCHAR(255) NOT NULL,
PRIMARY KEY (`Equipment_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `FarmDB`.`Supplier`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `FarmDB`.`Supplier` (
        `Supplier_ID` INT NOT NULL AUTO_INCREMENT,
`Supplier_Name` VARCHAR(255) NOT NULL,
`Supplier_Address` VARCHAR(255) NOT NULL,
`Supplier_Phone` VARCHAR(20) NOT NULL,
PRIMARY KEY (`Supplier_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `FarmDB`.`Equipment_Supplier`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `FarmDB`.`Equipment_Supplier` (
        `Equipment_ID` INT NOT NULL,
`Supplier_ID` INT NOT NULL,
PRIMARY KEY (`Equipment_ID`, `Supplier_ID`),
INDEX `Supplier_ID` (`Supplier_ID` ASC) VISIBLE,
CONSTRAINT `equipment_supplier_ibfk_1`
        FOREIGN KEY (`Equipment_ID`)
REFERENCES `FarmDB`.`Equipment` (`Equipment_ID`),
CONSTRAINT `equipment_supplier_ibfk_2`
        FOREIGN KEY (`Supplier_ID`)
REFERENCES `FarmDB`.`Supplier` (`Supplier_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

ALTER TABLE `FarmDB`.`Equipment_Supplier`
ADD COLUMN `Supplier_Name` VARCHAR(255) NOT NULL AFTER `Supplier_ID`,
ADD COLUMN `Supplier_Phone` VARCHAR(20) NOT NULL AFTER `Supplier_Name`;


-- -----------------------------------------------------
-- Table `FarmDB`.`Farmer`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `FarmDB`.`Farmer` (
        `Farmer_ID` INT NOT NULL AUTO_INCREMENT,
`Farmer_Name` VARCHAR(255) NOT NULL,
`Farmer_Address` VARCHAR(255) NOT NULL,
`Farmer_Phone` VARCHAR(20) NOT NULL,
PRIMARY KEY (`Farmer_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `FarmDB`.`Field`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `FarmDB`.`Field` (
        `Field_ID` INT NOT NULL AUTO_INCREMENT,
`Farm_ID` INT NOT NULL,
`Field_Name` VARCHAR(255) NOT NULL,
`Field_Size` INT NOT NULL,
`Field_Location` VARCHAR(255) NOT NULL,
PRIMARY KEY (`Field_ID`),
INDEX `Farm_ID` (`Farm_ID` ASC) VISIBLE,
CONSTRAINT `field_ibfk_1`
        FOREIGN KEY (`Farm_ID`)
REFERENCES `FarmDB`.`Farm` (`Farm_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `FarmDB`.`Harvest`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `FarmDB`.`Harvest` (
        `Harvest_ID` INT NOT NULL AUTO_INCREMENT,
`Field_ID` INT NOT NULL,
`Crop_ID` INT NOT NULL,
`Harvest_Date` DATE NOT NULL,
`Harvest_Weight` INT NOT NULL,
PRIMARY KEY (`Harvest_ID`),
INDEX `Field_ID` (`Field_ID` ASC) VISIBLE,
INDEX `Crop_ID` (`Crop_ID` ASC) VISIBLE,
CONSTRAINT `harvest_ibfk_1`
        FOREIGN KEY (`Field_ID`)
REFERENCES `FarmDB`.`Field` (`Field_ID`),
CONSTRAINT `harvest_ibfk_2`
        FOREIGN KEY (`Crop_ID`)
REFERENCES `FarmDB`.`Crop` (`Crop_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `FarmDB`.`Product`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `FarmDB`.`Product` (
        `Product_ID` INT NOT NULL AUTO_INCREMENT,
`Product_Name` VARCHAR(255) NOT NULL,
`Product_Type` VARCHAR(255) NOT NULL,
PRIMARY KEY (`Product_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `FarmDB`.`Sale`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `FarmDB`.`Sale` (
        `Sale_ID` INT NOT NULL AUTO_INCREMENT,
`Product_ID` INT NOT NULL,
`Customer_ID` INT NOT NULL,
`Sale_Date` DATE NOT NULL,
`Sale_Quantity` INT NOT NULL,
PRIMARY KEY (`Sale_ID`),
INDEX `Product_ID` (`Product_ID` ASC) VISIBLE,
INDEX `Customer_ID` (`Customer_ID` ASC) VISIBLE,
CONSTRAINT `sale_ibfk_1`
        FOREIGN KEY (`Product_ID`)
REFERENCES `FarmDB`.`Product` (`Product_ID`),
CONSTRAINT `sale_ibfk_2`
        FOREIGN KEY (`Customer_ID`)
REFERENCES `FarmDB`.`Customer` (`Customer_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `FarmDB`.`Task`
        -- -----------------------------------------------------
        CREATE TABLE IF NOT EXISTS `FarmDB`.`Task` (
        `Task_ID` INT NOT NULL AUTO_INCREMENT,
`Field_ID` INT NOT NULL,
`Task_Name` VARCHAR(255) NOT NULL,
`Task_Description` VARCHAR(255) NOT NULL,
`Task_Date` DATE NOT NULL,
`Task_Status` VARCHAR(20) NOT NULL,
PRIMARY KEY (`Task_ID`),
INDEX `Field_ID` (`Field_ID` ASC) VISIBLE,
CONSTRAINT `task_ibfk_1`
        FOREIGN KEY (`Field_ID`)
REFERENCES `FarmDB`.`Field` (`Field_ID`))
