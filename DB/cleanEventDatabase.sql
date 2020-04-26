-- Table: BOOKING
DROP TABLE IF EXISTS BOOKING;

CREATE TABLE BOOKING (
    id       INTEGER PRIMARY KEY AUTOINCREMENT
                     UNIQUE
                     NOT NULL,
    user_id  INTEGER NOT NULL,
    event_id INTEGER NOT NULL,
    FOREIGN KEY (
        user_id
    )
    REFERENCES USER (id),
    FOREIGN KEY (
        event_id
    )
    REFERENCES EVENT (id) 
);


-- Table: EVENT
DROP TABLE IF EXISTS EVENT;

CREATE TABLE EVENT (
    id                INTEGER       PRIMARY KEY AUTOINCREMENT
                                    UNIQUE
                                    NOT NULL,
    repetition_id     INTEGER,
    type              INTEGER       NOT NULL
                                    REFERENCES TYPE (id),
    title             VARCHAR (50)  NOT NULL,
    description       VARCHAR (255),
    date_time         DATETIME,
    space_limitations INTEGER       NOT NULL,
    place             VARCHAR (250) NOT NULL,
    FOREIGN KEY (
        repetition_id
    )
    REFERENCES REPETITION (id) 
);


-- Table: EVENT_ORGANISING
DROP TABLE IF EXISTS EVENT_ORGANISING;

CREATE TABLE EVENT_ORGANISING (
    id       INTEGER PRIMARY KEY AUTOINCREMENT
                     UNIQUE
                     NOT NULL,
    user_id  INTEGER NOT NULL,
    event_id INTEGER NOT NULL,
    FOREIGN KEY (
        user_id
    )
    REFERENCES USER (id),
    FOREIGN KEY (
        event_id
    )
    REFERENCES EVENT (id) 
);


-- Table: REPETITION
DROP TABLE IF EXISTS REPETITION;

CREATE TABLE REPETITION (
    id               INTEGER PRIMARY KEY AUTOINCREMENT
                             UNIQUE
                             NOT NULL,
    time_of_the_day  TIME,
    day_of_the_week  DATE,
    day_of_the_month DATE,
    day_of_the_year  DATE
);


-- Table: ROLE
DROP TABLE IF EXISTS ROLE;

CREATE TABLE ROLE (
    id   INTEGER       UNIQUE
                       NOT NULL
                       PRIMARY KEY AUTOINCREMENT,
    name VARCHAR (255) UNIQUE
                       NOT NULL
);

INSERT INTO ROLE (
                     id,
                     name
                 )
                 VALUES (
                     1,
                     'ADMIN'
                 );

INSERT INTO ROLE (
                     id,
                     name
                 )
                 VALUES (
                     2,
                     'EVENT_ORGANISER'
                 );


-- Table: TYPE
DROP TABLE IF EXISTS TYPE;

CREATE TABLE TYPE (
    id   INTEGER       PRIMARY KEY AUTOINCREMENT
                       UNIQUE
                       NOT NULL,
    name VARCHAR (255) UNIQUE
                       NOT NULL
);

INSERT INTO TYPE (
                     id,
                     name
                 )
                 VALUES (
                     1,
                     'ONLINE'
                 );

INSERT INTO TYPE (
                     id,
                     name
                 )
                 VALUES (
                     2,
                     'PHYSICAL'
                 );


-- Table: USER
DROP TABLE IF EXISTS USER;

CREATE TABLE USER (
    id       INTEGER       PRIMARY KEY
                           UNIQUE
                           NOT NULL,
    name     VARCHAR (255) NOT NULL,
    surname  VARCHAR (255) NOT NULL,
    password VARCHAR (255) NOT NULL
);


-- Table: USER_TO_ROLE
DROP TABLE IF EXISTS USER_TO_ROLE;

CREATE TABLE USER_TO_ROLE (
    id      INTEGER PRIMARY KEY AUTOINCREMENT
                    UNIQUE
                    NOT NULL,
    user_id INTEGER NOT NULL,
    role_id INTEGER NOT NULL,
    FOREIGN KEY (
        user_id
    )
    REFERENCES USER (id),
    FOREIGN KEY (
        role_id
    )
    REFERENCES ROLE (id) 
);
