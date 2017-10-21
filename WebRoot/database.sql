create database library;
drop table `user`;
create table `user`(
       `userId` int(11) primary key AUTO_INCREMENT,
       `userName` varchar(30) unique not null,
       `password` varchar(10) not null,
       `isAdmin`  int(1) default '0',
       constraint isAdmin check(isAdmin in ('0','1'))
)default charset="utf8";

create table `book`(

       `bookId` int(11) primary key AUTO_INCREMENT,
       `bookNo` varchar(20) not null unique,
       `bookName` varchar(50) not null,
       `bookAuthor` varchar(10) default null,
       `bookPrice` varchar(10) default null,
       `bookInfo` varchar(255) default null,
       `bookTotalCount` int(5) default '0',
       `bookBorrowedCount` int(5) default '0'   

)default charset="utf8";
drop table `ub`;
create table `ub`(
       
       `id` int(11) primary key AUTO_INCREMENT,
       `userId` int(11) not null,
       `bookId` int(11) not null,
       `bookName` varchar(50) not null,
       `bookCount` int(1) default '0',
       foreign key(`userId`) references `user`(`userId`),
       foreign key(`bookId`) references `book`(`bookId`)
       
)default charset='utf8';