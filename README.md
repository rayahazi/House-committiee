# House-committiee
Java final-project in client-server and mySql

___
Info will be uploaded soon...


___
# MySQL part 
```sql
create database Committiee;

use Committiee;
create table Ternant(
	Id int primary key,
    Username nvarchar(20),
    Password nvarchar(20),
    FirstName nvarchar(20) not null,
    LastName nvarchar(20) not null,
    ApartmentId int,
    Payment decimal,
    MonthPay nvarchar(30)
);

insert into Ternant(Id,Username, Password,FirstName,LastName,ApartmentId,Payment,MonthPay)
values(1,'A','1234','Alice','Levi',41,1200,'1 2 3 8'),
	  (2,'B','12','Bob','Israel',1,4000,'1 2 3 4 5 6 7 8 9 10 11 12'),
      (3,'C','345','Chris','Cohen',23,7000,'1 5 9');
      
create table Manager(
	Id int primary key,
    Username nvarchar(20),
    Password nvarchar(20),
    FirstName nvarchar(20) not null,
    LastName nvarchar(20) not null,
    Years int
);

insert into Manager(Id,Username, Password,FirstName,LastName,Years)
values(1,'D','1234','John','Malka',4),
	  (2,'E','12','Kate','Brown',2),
      (3,'F','345','Li','Cohen',3)
      
```

