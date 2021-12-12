ShoppingApplication

Tables:<br />
ITEM_DETAILS:<br />
  &nbsp;**ITEM_ID**<br />
  &nbsp;ITEM_NAME <br />
  &nbsp;ITEM_PRICE<br />
  &nbsp;AVAILABLE_QUANTITY<br />
 <br />
USER_DETAILS:<br />
  &nbsp;**USER_ID** <br /> 
  &nbsp;FIRST_NAME <br />
  &nbsp;LAST_NAME <br />
  &nbsp;USER_TYPE : ADMIN/USER<br />

MySQL Syntax to create database: 
 
```
CREATE DATABASE TEST

USE TEST

CREATE TABLE ITEM_DETAILS (ITEM_ID INT,ITEM_NAME VARCHAR(225),ITEM_PRICE DECIMAL(7,2),AVAILABLE_QUANTITY INT)

CREATE TABLE USER_DETAILS (USER_ID INT,FIRST_NAME VARCHAR(20) ,LAST_NAME VARCHAR(20),USER_TYPE VARCHAR(8))
```


Controllers : \
  /users  
  &nbsp; /all \
 &nbsp;&nbsp;Getting all the users available\
  &nbsp; /{userId}\
     &nbsp;&nbsp;Getting a particular user based on USER_ID\
  &nbsp; /addUser\
    &nbsp;&nbsp;Adding a new user into the database\
   
  /item \
   &nbsp;/all \
      &nbsp;&nbsp;Getting all the items available\
   &nbsp;/{itemId} \
      &nbsp;&nbsp;Getting a particular item based on ITEM_ID \
   &nbsp;/orderItem \
     &nbsp;&nbsp;User can order items if the available quantites are more than or equal to the available quantities.\
   &nbsp;/manageItems\
      &nbsp;&nbsp;Admins can update the items available or create a new records for new items in the inventory.\
      
