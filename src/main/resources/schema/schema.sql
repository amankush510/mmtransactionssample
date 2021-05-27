CREATE TABLE User (
    id varchar(255) NOT NULL,
    name varchar(255) NOT NULL,
    email varchar(255) NOT NULL,
    phone varchar(10) NOT NULL,
    createdAt bigint(25) NOT NULL,
    updatedAt bigint(20) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE Transaction (
    id varchar(255) NOT NULL,
    source varchar(255) NOT NULL,
    destination varchar(255) NOT NULL,
    type varchar(10) NOT NULL,
    amount float(25) NOT NULL,
    timestamp bigint(20) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (source) REFERENCES User(id),
    FOREIGN KEY (destination) REFERENCES User(id)
);

CREATE TABLE UserToTransaction (
    userId varchar(255) NOT NULL,
    transactionId varchar(255) NOT NULL,
    UNIQUE KEY (transactionId),
    FOREIGN KEY (transactionId) REFERENCES Transaction(id),
    FOREIGN KEY (userId) REFERENCES User(id)
);