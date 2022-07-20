drop table if exists t_account;
create table t_account(
    `id` int primary key auto_increment,
    `user_account_num` varchar(30) not null,
    `user_name` varchar(30) not null,
    `nice_name` varchar(30) not null,
    `age` int not null,
    `password` varchar(30) not null,
    `user_email` varchar(30) not null
);
