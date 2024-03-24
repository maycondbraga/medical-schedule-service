CREATE TABLE schedule (
    id serial PRIMARY KEY,
    description varchar(255),
    date_time timestamp,
    creation_date timestamp,
    patient_id integer,
    CONSTRAINT fk_schedule_patient FOREIGN KEY (patient_id) REFERENCES patient(id)
);