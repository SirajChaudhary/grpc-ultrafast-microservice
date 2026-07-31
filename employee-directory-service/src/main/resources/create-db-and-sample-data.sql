-- Create Database

CREATE DATABASE employeedirectorydb;

-- Create Employees Table

CREATE TABLE employees
(
    id BIGSERIAL PRIMARY KEY,
    employee_code VARCHAR(20) NOT NULL UNIQUE,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone_number VARCHAR(15),
    department VARCHAR(50) NOT NULL,
    designation VARCHAR(50) NOT NULL,
    office_location VARCHAR(100),
    joining_date DATE NOT NULL,
    status VARCHAR(20) NOT NULL
);

-- Create Indexes

CREATE INDEX idx_employee_code ON employees(employee_code);

CREATE INDEX idx_email ON employees(email);

CREATE INDEX idx_department ON employees(department);

-- Insert Sample Data

INSERT INTO employees (employee_code, first_name, last_name, email, phone_number, department, designation, office_location, joining_date, status) VALUES
('EMP001','Siraj','Chaudhary','siraj.chaudhary@company.com','9876500001','Engineering','Technical Lead','Hyderabad','2020-01-15','ACTIVE'),
('EMP002','Jaun','Elia','jaun.elia@company.com','9876500002','Engineering','Senior Software Engineer','Pune','2021-03-20','ACTIVE'),
('EMP003','Mirza','Ghalib','mirza.ghalib@company.com','9876500003','Engineering','Software Engineer','Hyderabad','2022-05-18','ACTIVE'),
('EMP004','Shahrukh','Khan','shahrukh.khan@company.com','9876500004','Sales','Sales Manager','Mumbai','2019-08-12','ACTIVE'),
('EMP005','Ricky','Ponting','ricky.ponting@company.com','9876500005','Engineering','Principal Engineer','Sydney','2016-12-11','ACTIVE'),
('EMP006','Bill','Gates','bill.gates@company.com','9876500006','Management','Chief Technology Officer','Seattle','2015-10-01','ACTIVE'),
('EMP007','Sundar','Pichai','sundar.pichai@company.com','9876500007','Management','Vice President','California','2016-08-21','ACTIVE'),
('EMP008','Satya','Nadella','satya.nadella@company.com','9876500008','Management','Chief Executive Officer','Redmond','2014-02-04','ACTIVE'),
('EMP009','Elon','Musk','elon.musk@company.com','9876500009','Research','Innovation Lead','Austin','2020-10-10','ACTIVE'),
('EMP010','Mark','Zuckerberg','mark.zuckerberg@company.com','9876500010','Product','Product Director','Menlo Park','2019-03-27','ACTIVE'),
('EMP011','A. P. J. Abdul','Kalam','abdul.kalam@company.com','9876500011','Research','Senior Scientist','Chennai','2018-07-27','ACTIVE'),
('EMP012','Mohammed','Rafi','mohammed.rafi@company.com','9876500012','HR','HR Manager','Hyderabad','2021-05-12','ACTIVE'),
('EMP013','Ayesha','Begum','ayesha.begum@company.com','9876500013','HR','HR Executive','Hyderabad','2023-02-18','ACTIVE'),
('EMP014','Ahmed','Khan','ahmed.khan@company.com','9876500014','Engineering','Software Engineer','Hyderabad','2024-01-11','ACTIVE'),
('EMP015','Fatima','Shaikh','fatima.shaikh@company.com','9876500015','Finance','Financial Analyst','Pune','2022-04-09','ACTIVE'),
('EMP016','Priya','Sharma','priya.sharma@company.com','9876500016','Engineering','Software Engineer','Hyderabad','2022-06-15','ACTIVE'),
('EMP017','Rahul','Verma','rahul.verma@company.com','9876500017','Engineering','Senior Software Engineer','Pune','2020-05-10','ACTIVE'),
('EMP018','John','Smith','john.smith@company.com','9876500018','Sales','Sales Executive','New York','2020-04-17','ACTIVE'),
('EMP019','Emma','Johnson','emma.johnson@company.com','9876500019','Support','Customer Success Manager','London','2019-09-22','ACTIVE'),
('EMP020','Sophia','Wilson','sophia.wilson@company.com','9876500020','Finance','Senior Accountant','Singapore','2021-01-25','INACTIVE');