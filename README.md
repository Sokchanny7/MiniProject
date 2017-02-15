# MiniProject

CREATE TABLE tblProduct(pro_id SERIAL PRIMARY KEY,
pro_name VARCHAR(20),
pro_price float,
pro_qty int,
pro_date VARCHAR(10)
)

CREATE OR REPLACE FUNCTION delete_product(p_id INTEGER) RETURNS Void AS

$BODY$ 

begin 

EXECUTE 'DELETE FROM tblproduct WHERE pro_id='||p_id; 

end; 

$BODY$

LANGUAGE plpgsql VOLATILE;

CREATE OR REPLACE FUNCTION insert_product(

				p_name VARCHAR(20),

				p_price FLOAT,

				p_qty INTEGER,

				p_date VARCHAR(10)) RETURNS VOID AS

$$

BEGIN

    INSERT INTO tblproduct (pro_name,pro_price,pro_qty,pro_date) 

						VALUES (p_name,p_price,p_qty,p_date);

END

$$

  LANGUAGE 'plpgsql';


CREATE OR REPLACE FUNCTION select_products() RETURNS 

SETOF tblproduct AS 'SELECT * FROM tblproduct ORDER BY pro_id;' LANGUAGE 'sql';

CREATE OR REPLACE FUNCTION update_product(

				p_id INTEGER,

				p_name VARCHAR(20),

				p_price FLOAT,

				p_qty INTEGER,

				p_date VARCHAR(10)) RETURNS VOID AS

$$

DECLARE

	t_name VARCHAR(20);

	t_price FLOAT;

	t_qty INTEGER;

	t_date VARCHAR(10);

BEGIN



		SELECT INTO t_name pro_name FROM tblproduct WHERE pro_id=p_id;

		SELECT INTO t_price pro_price FROM tblproduct WHERE pro_id=p_id;

		SELECT INTO t_qty pro_qty FROM tblproduct WHERE pro_id=p_id;

		SELECT INTO t_date pro_date FROM tblproduct WHERE pro_id=p_id;

		

		UPDATE tblproduct SET

			pro_name=(CASE WHEN t_name<>p_name THEN p_name ELSE t_name END),

			pro_price=(CASE WHEN t_price<>p_price THEN p_price ELSE t_price END),

			pro_qty=(CASE WHEN t_qty<>p_qty THEN p_qty ELSE t_price END),

			pro_date=(CASE WHEN t_date<>p_date THEN p_date ELSE t_date END)

			WHERE tblproduct.pro_id=p_id;

END;
$$
LANGUAGE 'plpgsql';

DELETE FROM tblproduct
TRUNCATE TABLE tblproduct RESTART IDENTITY;
