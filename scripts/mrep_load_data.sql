INSERT INTO `roads4su_mrep`.`cus_tier`(`cus_tier_id`,`cus_tier_name`)VALUES(1,"HH");
INSERT INTO `roads4su_mrep`.`cus_tier`(`cus_tier_id`,`cus_tier_name`)VALUES(2,"MM");
INSERT INTO `roads4su_mrep`.`cus_tier`(`cus_tier_id`,`cus_tier_name`)VALUES(3,"LL");
INSERT INTO `roads4su_mrep`.`cus_tier`(`cus_tier_id`,`cus_tier_name`)VALUES(4,"HM");
INSERT INTO `roads4su_mrep`.`cus_tier`(`cus_tier_id`,`cus_tier_name`)VALUES(5,"HL");
INSERT INTO `roads4su_mrep`.`cus_tier`(`cus_tier_id`,`cus_tier_name`)VALUES(6,"MH");
INSERT INTO `roads4su_mrep`.`cus_tier`(`cus_tier_id`,`cus_tier_name`)VALUES(7,"ML");
INSERT INTO `roads4su_mrep`.`cus_tier`(`cus_tier_id`,`cus_tier_name`)VALUES(8,"LH");
INSERT INTO `roads4su_mrep`.`cus_tier`(`cus_tier_id`,`cus_tier_name`)VALUES(9,"LM");



INSERT INTO `roads4su_mrep`.`cus_speciality`(`s_id`,`s_name`) VALUES (1,"Cardialoty");
INSERT INTO `roads4su_mrep`.`cus_speciality`(`s_id`,`s_name`) VALUES (2,"Bateny");


INSERT INTO `roads4su_mrep`.`customer_type`(`cus_type_id`,`cus_type_name`)VALUES(1,"Hospital");
INSERT INTO `roads4su_mrep`.`customer_type`(`cus_type_id`,`cus_type_name`)VALUES(2,"Pharmacy");
INSERT INTO `roads4su_mrep`.`customer_type`(`cus_type_id`,`cus_type_name`)VALUES(3,"Office");


INSERT INTO `roads4su_mrep`.`emplyee_type`(`emplyee_type_id`,`emplyee_type_name`)VALUES(1,"Manager");
INSERT INTO `roads4su_mrep`.`emplyee_type`(`emplyee_type_id`,`emplyee_type_name`)VALUES(2,"Admin");
INSERT INTO `roads4su_mrep`.`emplyee_type`(`emplyee_type_id`,`emplyee_type_name`)VALUES(3,"Supervisor");
INSERT INTO `roads4su_mrep`.`emplyee_type`(`emplyee_type_id`,`emplyee_type_name`)VALUES(4,"Rep");




INSERT INTO `roads4su_mrep`.`zone`(`z_name`)VALUES("Zone 1");


INSERT INTO `roads4su_mrep`.`territory`(`tor_name`,`zone_z_id`)
VALUES("tor 1",1);


INSERT INTO `roads4su_mrep`.`brick`
(`b_name`,
`territory_tor_id`)
VALUES
("brick1",1);

insert into `roads4su_mrep`.`line` (l_name) values ('line 2');

INSERT INTO `roads4su_mrep`.`employee`
(`emp_name`,
`emp_username`,
`emp_pass`,
`emp_manager`,
`emp_phone`,
`emp_address`,
`emp_brick`,
`emp_type`,
`l_id`)
VALUES
("Ihab Ahmed","ihab","1234",0,"012344543","new Cairo",1,1, 1);
