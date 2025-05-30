--------------------------------------------------------
-- Archivo creado  - martes-mayo-20-2025   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table CRUD_PRODUCTOS
--------------------------------------------------------

  CREATE TABLE "DB_CURSO"."CRUD_PRODUCTOS" 
   (	"ID_PRODUCTO" NUMBER(19,0), 
	"ESTADO" NUMBER(1,0), 
	"CANTIDAD" NUMBER(10,0), 
	"DESCRIPCION" VARCHAR2(255 CHAR), 
	"NOMBRE" VARCHAR2(255 CHAR), 
	"PRECIO" FLOAT(53)
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index SYS_C007870
--------------------------------------------------------

  CREATE UNIQUE INDEX "DB_CURSO"."SYS_C007870" ON "DB_CURSO"."CRUD_PRODUCTOS" ("ID_PRODUCTO") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  Constraints for Table CRUD_PRODUCTOS
--------------------------------------------------------

  ALTER TABLE "DB_CURSO"."CRUD_PRODUCTOS" MODIFY ("ID_PRODUCTO" NOT NULL ENABLE);
  ALTER TABLE "DB_CURSO"."CRUD_PRODUCTOS" ADD CHECK (estado in (0,1)) ENABLE;
  ALTER TABLE "DB_CURSO"."CRUD_PRODUCTOS" ADD PRIMARY KEY ("ID_PRODUCTO")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS"  ENABLE;
  
  CREATE OR REPLACE PROCEDURE INSERTAR_EDITAR_PRODUCTOS(
    P_ID_PRODUCTO IN NUMBER,
    P_NOMBRE IN VARCHAR2,
    P_DESCRIPCION IN VARCHAR2,
    P_CANTIDAD IN VARCHAR2,
    P_PRECIO IN NUMBER,
    P_ESTADO IN NUMBER
    )
    AS
    BEGIN
    IF P_ID_PRODUCTO IS NULL THEN
    INSERT INTO CRUD_PRODUCTOS (ID_PRODUCTO,ESTADO,CANTIDAD,DESCRIPCION,NOMBRE,PRECIO) 
    VALUES (P_ID_PRODUCTO,P_ESTADO,P_CANTIDAD,P_DESCRIPCION,P_NOMBRE,P_PRECIO);
    ELSE
    UPDATE CRUD_PRODUCTOS
    SET
        ESTADO = P_ESTADO,
        CANTIDAD = P_CANTIDAD,
        DESCRIPCION = P_DESCRIPCION,
        NOMBRE = P_NOMBRE,
        PRECIO = P_PRECIO
    WHERE
        ID_PRODUCTO = P_ID_PRODUCTO;
    END IF;
    COMMIT;
END;
/

CALL INSERTAR_EDITAR_PRODUCTOS('',1,4,'ASDHKHSKDA','FERNANDO',120);

---ELIMINAR VALORES PRODUCTOS
    CREATE OR REPLACE PROCEDURE ELIMINAR_PRODUCTOS(
    P_ID_PRODUCTO IN NUMBER
)
AS
BEGIN
    DELETE FROM CRUD_PRODUCTOS WHERE
        ID_PRODUCTO = P_ID_PRODUCTO;
        
    COMMIT;

END;
/
CALL ELIMINAR_PRODUCTOS(3);
  
