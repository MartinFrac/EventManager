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

INSERT INTO BOOKING (
                        id,
                        user_id,
                        event_id
                    )
                    VALUES (
                        1,
                        4,
                        1
                    );

INSERT INTO BOOKING (
                        id,
                        user_id,
                        event_id
                    )
                    VALUES (
                        2,
                        3,
                        2
                    );

INSERT INTO BOOKING (
                        id,
                        user_id,
                        event_id
                    )
                    VALUES (
                        3,
                        3,
                        3
                    );

INSERT INTO BOOKING (
                        id,
                        user_id,
                        event_id
                    )
                    VALUES (
                        4,
                        3,
                        3
                    );

INSERT INTO BOOKING (
                        id,
                        user_id,
                        event_id
                    )
                    VALUES (
                        5,
                        2,
                        1
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

INSERT INTO EVENT (
                      id,
                      repetition_id,
                      type,
                      title,
                      description,
                      date_time,
                      space_limitations,
                      place
                  )
                  VALUES (
                      1,
                      NULL,
                      1,
                      'event',
                      'some description',
                      '2020-08-10 20:00',
                      30,
                      'discord: rsp43hfs'
                  );

INSERT INTO EVENT (
                      id,
                      repetition_id,
                      type,
                      title,
                      description,
                      date_time,
                      space_limitations,
                      place
                  )
                  VALUES (
                      2,
                      NULL,
                      2,
                      'event2',
                      'another description',
                      '2020-07-24 14:30',
                      100,
                      'Luton, university of bedfordshire'
                  );

INSERT INTO EVENT (
                      id,
                      repetition_id,
                      type,
                      title,
                      description,
                      date_time,
                      space_limitations,
                      place
                  )
                  VALUES (
                      3,
                      1,
                      2,
                      'event3',
                      'some text',
                      '2020-04-23',
                      15,
                      'Luton, university of bedfordshire'
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

INSERT INTO EVENT_ORGANISING (
                                 id,
                                 user_id,
                                 event_id
                             )
                             VALUES (
                                 1,
                                 2,
                                 1
                             );

INSERT INTO EVENT_ORGANISING (
                                 id,
                                 user_id,
                                 event_id
                             )
                             VALUES (
                                 2,
                                 2,
                                 2
                             );

INSERT INTO EVENT_ORGANISING (
                                 id,
                                 user_id,
                                 event_id
                             )
                             VALUES (
                                 3,
                                 4,
                                 3
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

INSERT INTO REPETITION (
                           id,
                           time_of_the_day,
                           day_of_the_week,
                           day_of_the_month,
                           day_of_the_year
                       )
                       VALUES (
                           1,
                           '19:00',
                           'Thursday',
                           NULL,
                           NULL
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

INSERT INTO USER (
                     id,
                     name,
                     surname,
                     password
                 )
                 VALUES (
                     1,
                     'user',
                     'user',
                     'password'
                 );

INSERT INTO USER (
                     id,
                     name,
                     surname,
                     password
                 )
                 VALUES (
                     2,
                     'user2',
                     'user2',
                     'password'
                 );

INSERT INTO USER (
                     id,
                     name,
                     surname,
                     password
                 )
                 VALUES (
                     3,
                     'user3',
                     'user3',
                     'password'
                 );

INSERT INTO USER (
                     id,
                     name,
                     surname,
                     password
                 )
                 VALUES (
                     4,
                     'user4',
                     'user4',
                     'password'
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

INSERT INTO USER_TO_ROLE (
                             id,
                             user_id,
                             role_id
                         )
                         VALUES (
                             1,
                             1,
                             1
                         );

INSERT INTO USER_TO_ROLE (
                             id,
                             user_id,
                             role_id
                         )
                         VALUES (
                             2,
                             2,
                             2
                         );

INSERT INTO USER_TO_ROLE (
                             id,
                             user_id,
                             role_id
                         )
                         VALUES (
                             3,
                             4,
                             2
                         );
