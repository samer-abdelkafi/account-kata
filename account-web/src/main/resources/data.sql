delete from BANK_OPERATION;
delete from BANK_ACCOUNT;
insert into BANK_ACCOUNT (ID, NAME, BALANCE) values (1, 'Current account', 0);
insert into BANK_ACCOUNT (ID, NAME, BALANCE) values (2, 'Savings account', 0);
commit ;
