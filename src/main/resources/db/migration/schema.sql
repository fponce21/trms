CREATE TABLE users (
         id BIGSERIAL PRIMARY KEY,
         username VARCHAR(255) NOT NULL UNIQUE,
         password VARCHAR(60) NOT NULL,
         role VARCHAR(255) NOT NULL,
         email VARCHAR(255) NOT NULL UNIQUE
     );

     CREATE TABLE reimbursement_requests (
         id BIGSERIAL PRIMARY KEY,
         user_id BIGINT NOT NULL,
         amount DOUBLE PRECISION NOT NULL,
         description TEXT NOT NULL,
         status VARCHAR(255) NOT NULL,
         CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id)
     );