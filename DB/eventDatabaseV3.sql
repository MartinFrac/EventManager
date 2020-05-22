--
-- File generated with SQLiteStudio v3.2.1 on Fri May 22 17:34:24 2020
--
-- Text encoding used: System
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

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
                        5,
                        2,
                        1
                    );

INSERT INTO BOOKING (
                        id,
                        user_id,
                        event_id
                    )
                    VALUES (
                        14,
                        3,
                        2
                    );

INSERT INTO BOOKING (
                        id,
                        user_id,
                        event_id
                    )
                    VALUES (
                        15,
                        3,
                        3
                    );

INSERT INTO BOOKING (
                        id,
                        user_id,
                        event_id
                    )
                    VALUES (
                        16,
                        3,
                        4
                    );


-- Table: description
DROP TABLE IF EXISTS description;

CREATE VIRTUAL TABLE description USING FTS5 (
    id,
    body
);

INSERT INTO description (
                            id,
                            body
                        )
                        VALUES (
                            1,
                            'some description'
                        );

INSERT INTO description (
                            id,
                            body
                        )
                        VALUES (
                            2,
                            'another description'
                        );

INSERT INTO description (
                            id,
                            body
                        )
                        VALUES (
                            3,
                            'some text'
                        );

INSERT INTO description (
                            id,
                            body
                        )
                        VALUES (
                            4,
                            'other description'
                        );


-- Table: description_config
DROP TABLE IF EXISTS description_config;

CREATE TABLE description_config (
    k  PRIMARY KEY,
    v
)
WITHOUT ROWID;

INSERT INTO description_config (
                                   k,
                                   v
                               )
                               VALUES (
                                   'version',
                                   4
                               );


-- Table: description_content
DROP TABLE IF EXISTS description_content;

CREATE TABLE description_content (
    id INTEGER PRIMARY KEY,
    c0,
    c1
);

INSERT INTO description_content (
                                    id,
                                    c0,
                                    c1
                                )
                                VALUES (
                                    1,
                                    1,
                                    'some description'
                                );

INSERT INTO description_content (
                                    id,
                                    c0,
                                    c1
                                )
                                VALUES (
                                    2,
                                    2,
                                    'another description'
                                );

INSERT INTO description_content (
                                    id,
                                    c0,
                                    c1
                                )
                                VALUES (
                                    3,
                                    3,
                                    'some text'
                                );

INSERT INTO description_content (
                                    id,
                                    c0,
                                    c1
                                )
                                VALUES (
                                    4,
                                    4,
                                    'other description'
                                );


-- Table: description_data
DROP TABLE IF EXISTS description_data;

CREATE TABLE description_data (
    id    INTEGER PRIMARY KEY,
    block BLOB
);

INSERT INTO description_data (
                                 id,
                                 block
                             )
                             VALUES (
                                 1,
                                 X'040408'
                             );

INSERT INTO description_data (
                                 id,
                                 block
                             )
                             VALUES (
                                 10,
                                 X'000000000101010001010101'
                             );

INSERT INTO description_data (
                                 id,
                                 block
                             )
                             VALUES (
                                 137438953473,
                                 X'0000006D0230310102020101320202020101330302020101340402020107616E6F746865720206010102010B6465736372697074696F6E01060101030106010103020601010301056F7468657204060101020104736F6D6501060101020206010102010474657874030601010304060606060E1C0C10'
                             );


-- Table: description_docsize
DROP TABLE IF EXISTS description_docsize;

CREATE TABLE description_docsize (
    id INTEGER PRIMARY KEY,
    sz BLOB
);

INSERT INTO description_docsize (
                                    id,
                                    sz
                                )
                                VALUES (
                                    1,
                                    X'0102'
                                );

INSERT INTO description_docsize (
                                    id,
                                    sz
                                )
                                VALUES (
                                    2,
                                    X'0102'
                                );

INSERT INTO description_docsize (
                                    id,
                                    sz
                                )
                                VALUES (
                                    3,
                                    X'0102'
                                );

INSERT INTO description_docsize (
                                    id,
                                    sz
                                )
                                VALUES (
                                    4,
                                    X'0102'
                                );


-- Table: description_idx
DROP TABLE IF EXISTS description_idx;

CREATE TABLE description_idx (
    segid,
    term,
    pgno,
    PRIMARY KEY (
        segid,
        term
    )
)
WITHOUT ROWID;

INSERT INTO description_idx (
                                segid,
                                term,
                                pgno
                            )
                            VALUES (
                                1,
                                NULL,
                                2
                            );


-- Table: EVENT
DROP TABLE IF EXISTS EVENT;

CREATE TABLE EVENT (
    id                INTEGER       PRIMARY KEY AUTOINCREMENT
                                    UNIQUE
                                    NOT NULL,
    repetition_id     INTEGER,
    type_id           INTEGER       NOT NULL
                                    REFERENCES TYPE (id),
    title             VARCHAR (50)  NOT NULL,
    description_id    INT,
    start             DATETIME      NOT NULL,
    space_limitations INTEGER,
    place             VARCHAR (250) NOT NULL,
    description       VARCHAR (255),
    available_spaces  INT,
    [end]             DATETIME      NOT NULL,
    FOREIGN KEY (
        repetition_id
    )
    REFERENCES REPETITION (id) 
);

INSERT INTO EVENT (
                      id,
                      repetition_id,
                      type_id,
                      title,
                      description_id,
                      start,
                      space_limitations,
                      place,
                      description,
                      available_spaces,
                      [end]
                  )
                  VALUES (
                      1,
                      NULL,
                      1,
                      'event',
                      1,
                      '2020-08-10 20:00',
                      30,
                      'discord: rsp43hfs',
                      'some description',
                      30,
                      '2020-08-10 22:00'
                  );

INSERT INTO EVENT (
                      id,
                      repetition_id,
                      type_id,
                      title,
                      description_id,
                      start,
                      space_limitations,
                      place,
                      description,
                      available_spaces,
                      [end]
                  )
                  VALUES (
                      2,
                      NULL,
                      2,
                      'event2',
                      2,
                      '2020-07-24 14:30',
                      100,
                      'Luton, university of bedfordshire',
                      'another description',
                      99,
                      '2020-07-24 15:30'
                  );

INSERT INTO EVENT (
                      id,
                      repetition_id,
                      type_id,
                      title,
                      description_id,
                      start,
                      space_limitations,
                      place,
                      description,
                      available_spaces,
                      [end]
                  )
                  VALUES (
                      3,
                      1,
                      2,
                      'event3',
                      3,
                      '2020-04-23 12:00',
                      15,
                      'Luton, university of bedfordshire',
                      'some text',
                      14,
                      '2020-04-23 14:00'
                  );

INSERT INTO EVENT (
                      id,
                      repetition_id,
                      type_id,
                      title,
                      description_id,
                      start,
                      space_limitations,
                      place,
                      description,
                      available_spaces,
                      [end]
                  )
                  VALUES (
                      4,
                      NULL,
                      1,
                      'event4',
                      4,
                      '2020-10-12 13:00',
                      40,
                      'discord: fdshfjj343',
                      'other description',
                      39,
                      '2020-10-12 18:00'
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

INSERT INTO EVENT_ORGANISING (
                                 id,
                                 user_id,
                                 event_id
                             )
                             VALUES (
                                 4,
                                 4,
                                 4
                             );


-- Table: REPETITION
DROP TABLE IF EXISTS REPETITION;

CREATE TABLE REPETITION (
    id               INTEGER       PRIMARY KEY AUTOINCREMENT
                                   UNIQUE
                                   NOT NULL,
    time_of_the_day  VARCHAR (255),
    day_of_the_week  VARCHAR (255),
    day_of_the_month VARCHAR (255) 
);

INSERT INTO REPETITION (
                           id,
                           time_of_the_day,
                           day_of_the_week,
                           day_of_the_month
                       )
                       VALUES (
                           1,
                           '19:00',
                           NULL,
                           '1'
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

INSERT INTO ROLE (
                     id,
                     name
                 )
                 VALUES (
                     3,
                     'USER'
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
    password VARCHAR (255) NOT NULL,
    role_id  INT           REFERENCES ROLE (id) 
                           NOT NULL
);

INSERT INTO USER (
                     id,
                     name,
                     surname,
                     password,
                     role_id
                 )
                 VALUES (
                     1,
                     'user',
                     'user',
                     'password',
                     1
                 );

INSERT INTO USER (
                     id,
                     name,
                     surname,
                     password,
                     role_id
                 )
                 VALUES (
                     2,
                     'user2',
                     'user2',
                     'password',
                     2
                 );

INSERT INTO USER (
                     id,
                     name,
                     surname,
                     password,
                     role_id
                 )
                 VALUES (
                     3,
                     'user3',
                     'user3',
                     'password',
                     3
                 );

INSERT INTO USER (
                     id,
                     name,
                     surname,
                     password,
                     role_id
                 )
                 VALUES (
                     4,
                     'user4',
                     'user4',
                     'password',
                     2
                 );


-- Table: variables
DROP TABLE IF EXISTS variables;

CREATE TABLE variables (
    var VARCHAR (255) 
);


COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
