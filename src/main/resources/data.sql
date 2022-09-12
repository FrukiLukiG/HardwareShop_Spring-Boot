DELETE FROM hardware;

INSERT INTO hardware VALUES (1, '4053156801', 'ASUS ROG Strix 570-E', 2649.00, 'MBO', 21);
INSERT INTO hardware VALUES (2, '7603721210', 'AMD Ryzen 7 5800X', 2892.99, 'CPU', 43);
INSERT INTO hardware VALUES (3, '5201307907', 'Nvidia GeForce RTX 3080', 8427.00, 'GPU', 2);
INSERT INTO hardware VALUES (4, '9247483505', 'Crucial Ballistix 32GB DDR4', 2537.50, 'RAM', 32);
INSERT INTO hardware VALUES (5, '4549854912', 'Samsung SSD 4TB 870 EVO', 1022.40, 'STORAGE', 51);
INSERT INTO hardware VALUES (6, '4746171138', 'Gigabyte B560M DS3H V2', 739.99, 'MBO', 27);
INSERT INTO hardware VALUES (7, '1237740127', 'Intel Core i5-12600K', 2537.50, 'CPU', 38);
INSERT INTO hardware VALUES (8, '0581947777', 'AMD Radeon RX 6800 XT', 9286.00, 'GPU', 1);
INSERT INTO hardware VALUES (9, '3198110391', 'WD Blue SSD 1TB SN550', 795.00, 'STORAGE', 49);
INSERT INTO hardware VALUES (10, '1104857058', 'Kingston HyperX Fury Beast 8GB DDR4', 415.00, 'RAM', 28);
INSERT INTO hardware VALUES (11, '9749230089', 'Samsung LC27G55TQWRXEN monitor', 1999.99, 'OTHER', 67);
INSERT INTO hardware VALUES (12, '8896658986', 'RAZER Viper Ultimate', 721.54, 'OTHER', 123);

DELETE FROM review;

INSERT INTO review VALUES (1, 1, 'Best motherboard', 'The best motherboard on the market', 'FIVE');
INSERT INTO review VALUES (2, 2, 'Best AMD processor', 'The best AMD processor on the market', 'FIVE');
INSERT INTO review VALUES (3, 2, 'Amazing AMD processor', 'The most amazing AMD processor, but a bit pricey', 'FOUR');
INSERT INTO review VALUES (4, 3, 'Best Nvidia GPU', 'The best graphics card on the market', 'FIVE');
INSERT INTO review VALUES (5, 3, 'Flagship Nvidia GPU too expensive', 'Good graphics, but expensive', 'THREE');
INSERT INTO review VALUES (6, 3, 'Bad Nvidia GPU for that price', 'This GPU is just too expenisve, buy cheaper version', 'ONE');
INSERT INTO review VALUES (7, 4, 'Average RAM', 'RAM just like any RAM, nothing special', 'THREE');
INSERT INTO review VALUES (8, 5, 'Best SSD from Samsung', 'Best SSD on the market that you can buy', 'FIVE');
INSERT INTO review VALUES (9, 5, 'Good SSD, good price', 'Pretty good SSD for the price, not complaining', 'FOUR');
INSERT INTO review VALUES (10, 5, 'Overrated SSD', 'Just like every SSD, there are cheaper alternatives', 'TWO');
INSERT INTO review VALUES (11, 6, 'Average motherboard', 'Just like every motherboard, nothing special', 'THREE');
INSERT INTO review VALUES (12, 7, 'Best INTEL processor', 'The best INTEL processor on the market', 'FIVE');
INSERT INTO review VALUES (13, 7, 'Amazing INTEL processor', 'The most amazing INTEL processor, but a bit pricey', 'FOUR');
INSERT INTO review VALUES (14, 8, 'Best AMD GPU', 'The best AMD graphics card on the market', 'FIVE');
INSERT INTO review VALUES (15, 8, 'Flagship AMD GPU too expensive', 'Good graphics, but expensive GPU', 'THREE');
INSERT INTO review VALUES (16, 8, 'Bad AMD GPU for that price', 'This GPU is just too expensive, buy cheaper version', 'ONE');
INSERT INTO review VALUES (17, 9, 'Good SSD', 'Pretty good SSD for the price, ok performance', 'FOUR');
INSERT INTO review VALUES (18, 10, 'RAM', 'Its just a RAM', 'THREE');
INSERT INTO review VALUES (19, 11, 'A monitor', 'Its a monitror, like every other', 'TWO');
INSERT INTO review VALUES (20, 12, 'Best RAZER mouse', 'Best RAZER mouse to buy, buy NOW', 'FIVE');
INSERT INTO review VALUES (21, 12, 'Worst RAZER mouse', 'Worst RAZER mouse to buy, dont buy ever', 'ONE');

DELETE FROM authority;

INSERT INTO authority VALUES (1, 'ROLE_ADMIN');
INSERT INTO authority VALUES (2, 'ROLE_USER');
INSERT INTO authority VALUES (3, 'ROLE_NONE');

DELETE FROM users;

INSERT INTO users VALUES (1, 'adminUser', '$2a$12$L2DcHJR2OtSBIMNsK1wMq.8T8sAd47pqA4EPYLJtf5Ejw/R6ZtEsi');
INSERT INTO users VALUES (2, 'regularUser', '$2a$12$mzpI.66c7S1CxfPTZSeqH.U.Usjmw08KIpRgVnB9PzZg3pUqvxq3a');
INSERT INTO users VALUES (3, 'guestUser', '$2a$12$8v6j.rkcCrp9L7PDX5DBu.LYF3g0I00iQJlzwS4maf8oB/Y4B0eyG');


DELETE FROM users_authority;

INSERT INTO users_authority VALUES (1, 1, 1);
INSERT INTO users_authority VALUES (2, 2, 2);
INSERT INTO users_authority VALUES (3, 3, 3);