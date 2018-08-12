--2.1
--Task – Select all records from the Employee tabl
SELECT * FROM EMPLOYEE;
--Task – Select all records from the Employee table where last name is King
SELECT * FROM EMPLOYEE
WHERE LASTNAME = 'King';
--Task – Select all records from the Employee table where first name is Andrew and REPORTSTO is NULL.
SELECT * FROM EMPLOYEE
WHERE
FIRSTNAME = 'Andrew'
and
REPORTSTO IS NULL;

--2.2
--Task – Select all albums in Album table and sort result set in descending order by title.
SELECT * FROM ALBUM
ORDER BY TITLE DESC;
--Task – Select first name from Customer and sort result set in ascending order by city
SELECT FIRSTNAME FROM CUSTOMER
ORDER BY CITY ASC;

--2.3
--Task – Insert two new records into Genre table
INSERT INTO Genre (GenreId, Name) VALUES (26, 'Progressive Rock');
INSERT INTO Genre (GenreId, Name) VALUES (27, 'Stoner Metal');
--Task – Insert two new records into Employee table
INSERT INTO Employee (EmployeeId, LastName, FirstName, Title, BirthDate, HireDate, Address, City, State, Country, PostalCode, Phone, Fax, Email)
VALUES
(1793, 'Renzullo', 'Landon', 'Special Agent', TO_DATE('1995-9-23 00:00:00','yyyy-mm-dd hh24:mi:ss'), TO_DATE('1995-9-24 00:00:00','yyyy-mm-dd hh24:mi:ss'),'Hudson River Greenway', 'New York', 'NY', 'United States', '10019', '+1 (860) 782-1146', '+1 (555) 555-5555', 'landonr@secretgovernmentagency5.gov');
INSERT INTO Employee (EmployeeId, LastName, FirstName, Title, ReportsTo, BirthDate, HireDate, Address, City, State, Country, PostalCode, Phone, Fax, Email)
VALUES
(1794, 'Tuers', 'Jared', 'Agency Director', 1793, TO_DATE('1997-9-23 00:00:00','yyyy-mm-dd hh24:mi:ss'), TO_DATE('2016-5-1 00:00:00','yyyy-mm-dd hh24:mi:ss'), 'FBI spy van 5', 'Washington D.C', 'Virginia', 'United States', '06790', '+1 (403) 262-3443', '+1 (403) 262-3322', 'jtuers@mostsecretgovernmentagency3.gov');

--Task – Insert two new records into Customer table
INSERT INTO Customer (CustomerId, FirstName, LastName, Address, City, Country, PostalCode, Phone, Email, SupportRepId)
VALUES
(92, 'Elon', 'Musk', '3500 Deer Creek Road', 'Palo Alto', 'United States', '94304', '+1 (877) 798-3752', 'Elon@Tesla.com', 1);
INSERT INTO Customer (CustomerId, FirstName, LastName, Address, City, Country, PostalCode, Phone, Email, SupportRepId)
VALUES
(61, 'Vitalik', 'Buterin', 'Blockchain', 'Ether', 'Russia', '00000', '+49 114 2583', 'vitalik@ethereum.org', 5);


--2.4 -
--Task – Update Aaron Mitchell in Customer table to Robert Walter
UPDATE CUSTOMER
SET FIRSTNAME = 'Robert', LASTNAME = 'Walter'
WHERE FIRSTNAME = 'Aaron' AND LASTNAME = 'Mitchell';
--Task – Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR”
UPDATE ARTIST
SET NAME = 'CCR'
WHERE NAME = 'Creedence Clearwater Revival';

--2.5--
--Task – Select all invoices with a billing address like “T%
SELECT * FROM INVOICE
WHERE BILLINGADDRESS LIKE 'T%';

--2.6
--Task – Select all invoices that have a total between 15 and 50
SELECT * FROM INVOICE
WHERE TOTAL BETWEEN 15 AND 50;

--Task – Select all employees hired between 1​st​ of June 2003 and 1​st​ of March 2004
SELECT * FROM EMPLOYEE
WHERE HIREDATE BETWEEN  TO_DATE('2003-6-1 00:00:00','yyyy-mm-dd hh24:mi:ss') AND TO_DATE('2004-3-1 23:59:59','yyyy-mm-dd hh24:mi:ss');

--2.7 Task – Delete a record in Customer table where the name is Robert Walter
DELETE FROM INVOICELINE
WHERE INVOICEID = (SELECT INVOICEID FROM INVOICE WHERE ROWNUM <=1 AND CUSTOMERID = (SELECT CUSTOMERID FROM CUSTOMER WHERE FIRSTNAME = 'Robert' AND LASTNAME = 'Walter'));

SELECT INVOICEID FROM INVOICE WHERE CUSTOMERID = 32

DELETE FROM INVOICE
WHERE CUSTOMERID = (SELECT CUSTOMERID FROM CUSTOMER WHERE FIRSTNAME = 'Robert' AND LASTNAME = 'Walter');

DELETE FROM CUSTOMER
WHERE FIRSTNAME = 'Robert' AND LASTNAME = 'Walter';


--3 FUNCTIONS
--3.1
--Task – Create a function that returns the current time
CREATE OR REPLACE FUNCTION TIMENOW
RETURN TIMESTAMP
IS T TIMESTAMP;
BEGIN
    T:= CURRENT_TIMESTAMP();
    RETURN T;
END;

-- Task – create a function that returns the length of name in MEDIATYPE table

CREATE OR REPLACE FUNCTION NAMELEN(X IN MEDIATYPE.MEDIATYPEID%TYPE)
RETURN INTEGER
IS
N INTEGER;
BEGIN
    SELECT LENGTH(NAME) INTO N FROM MEDIATYPE WHERE MEDIATYPEID = X;
    RETURN N;
END;

--3.2
--Task – Create a function that returns the average total of all invoices
CREATE OR REPLACE FUNCTION AVGINVOICETOTAL
RETURN NUMBER
IS N NUMBER(5,2);
BEGIN
    SELECT AVG(TOTAL) INTO N FROM INVOICE;
    RETURN N;
END;
--Task – Create a function that returns the most expensive track
CREATE OR REPLACE FUNCTION MOSTEXPENSIVETRACK
RETURN NUMBER
IS N NUMBER(6,2);
BEGIN
    SELECT MAX(UNITPRICE) INTO N FROM TRACK;
    RETURN N;
END;

--3.3
--Task – Create a function that returns the average price of invoiceline items in the invoiceline table
CREATE OR REPLACE FUNCTION AVG_INVOICELINE_PRICE
RETURN NUMBER
IS N NUMBER(5,2);
BEGIN
    SELECT AVG(UNITPRICE) INTO N FROM INVOICELINE;
    RETURN N;
END;
--3.4
--Task – Create a function that returns all employees who are born after 1968.
CREATE OR REPLACE FUNCTION EMPLOYEES_BORN_AFTER_1968
RETURN SYS_REFCURSOR
IS S SYS_REFCURSOR;
BEGIN
    OPEN S FOR
    SELECT FIRSTNAME, LASTNAME FROM EMPLOYEE WHERE BIRTHDATE > TO_DATE('1968-1-1 00:00:00','yyyy-mm-dd hh24:mi:ss');
    RETURN S;
END;

--4 STORED PROCEDURES

--4.1
--Task – Create a stored procedure that selects the first and last names of all the employees.
CREATE OR REPLACE PROCEDURE FLNAMES_EMPLOYEES(FL OUT SYS_REFCURSOR)
IS S SYS_REFCURSOR;
BEGIN
    OPEN S FOR
    SELECT FIRSTNAME,LASTNAME FROM EMPLOYEE;
    FL:= S;
END;

--4.2
--Task – Create a stored procedure that updates the personal information of an employee.
CREATE OR REPLACE PROCEDURE UPDATEEMPLOYEE(EID IN NUMBER,PAR IN VARCHAR2, VAL VARCHAR2)
IS P VARCHAR2(100);
BEGIN
    P := 'UPDATE EMPLOYEE SET '|| PAR || ' = :1 WHERE EMPLOYEEID = :2';
    EXECUTE IMMEDIATE P USING VAL, EID;
END;

--Task – Create a stored procedure that returns the managers of an employee.
CREATE OR REPLACE PROCEDURE MANAGER_EMPLOYEE(EID IN NUMBER, M OUT EMPLOYEE%ROWTYPE)
IS R NUMBER(1,0);
BEGIN
    SELECT * INTO M FROM EMPLOYEE WHERE EMPLOYEEID = (SELECT REPORTSTO FROM EMPLOYEE WHERE EMPLOYEEID = EID);
    EXCEPTION
        WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('NO MANAGERS FOUND');
END;


--4.3
--Task – Create a stored procedure that returns the name and company of a customer.
CREATE OR REPLACE PROCEDURE CUSTOMER_COMPANY(CID IN NUMBER,S OUT SYS_REFCURSOR)
IS R SYS_REFCURSOR;
BEGIN
    OPEN R FOR
    SELECT FIRSTNAME,LASTNAME,COMPANY FROM CUSTOMER WHERE CUSTOMERID = CID;
    S:= R;
END;

---5
---Task – Create a transaction that given a invoiceId will
---delete that invoice (There may be constraints that rely on
---this, find out how to resolve them).

CREATE OR REPLACE PROCEDURE DELETE_INVOICE(IID IN NUMBER)
IS T NUMBER(1,0);
BEGIN
    SAVEPOINT S;
    DELETE FROM INVOICELINE WHERE INVOICEID = IID;
    DBMS_OUTPUT.PUT_LINE('DELETED SOME');
    DELETE FROM INVOICE WHERE INVOICEID = IID;
    DBMS_OUTPUT.PUT_LINE('DID DELETE');
    EXCEPTION
        WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('DID NOT DELETE');
        ROLLBACK TO S;
END;


--6.1 Task - Create an after insert trigger on the employee table fired after a new record is inserted into the table
CREATE OR REPLACE TRIGGER TR_AFTER_EMPLOYEE_INSERT
AFTER INSERT ON EMPLOYEE
FOR EACH ROW
BEGIN
    DBMS_OUTPUT.PUT_LINE('NEW EMPLOYEE CREATED ' || :NEW.FIRSTNAME);
END;
--Task – Create an after delete trigger on the customer table that fires after a row is deleted from the table.
CREATE OR REPLACE TRIGGER TR_AFTER_CUSTOMER_DELETE
AFTER DELETE ON CUSTOMER
FOR EACH ROW
BEGIN
    DBMS_OUTPUT.PUT_LINE('CUSTOMER DELETED ' || :OLD.FIRSTNAME);
END;

--7 JOINS

--7.1 Task – Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceI
SELECT INVOICEID, CUSTOMER.FIRSTNAME, CUSTOMER.LASTNAME
FROM
CUSTOMER
JOIN
INVOICE ON INVOICE.CUSTOMERID = CUSTOMER.CUSTOMERID;

--7.2 Task – Create an outer join that joins the customer
--and invoice table, specifying the CustomerId, firstname,
--lastname, invoiceId, and total
SELECT INVOICEID,CUSTOMER.CUSTOMERID,CUSTOMER.FIRSTNAME,CUSTOMER.LASTNAME,TOTAL
FROM
CUSTOMER
FULL JOIN
INVOICE ON INVOICE.CUSTOMERID = CUSTOMER.CUSTOMERID
ORDER BY INVOICEID ASC;

--7.3 Task – Create a right join that joins album and artist specifying artist name and title
SELECT ARTIST.NAME,ALBUM.TITLE
FROM
ALBUM
RIGHT JOIN
ARTIST ON ALBUM.ARTISTID = ARTIST.ARTISTID
ORDER BY ARTIST.ARTISTID ASC;

--7.4 Task – Create a cross join that joins album and artist and sorts by artist name in ascending order
SELECT ARTIST.NAME,ALBUM.TITLE
FROM
ALBUM
CROSS JOIN
ARTIST
ORDER BY ARTIST.NAME ASC;
