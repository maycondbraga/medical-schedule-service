CREATE TABLE schedule (
    id serial PRIMARY KEY,
    description varchar(255),
    date_time timestamp,
    creation_date timestamp,
    id_patient integer,
    CONSTRAINT fk_schedule_patient FOREIGN KEY (id_patient) REFERENCES patient(id)
);