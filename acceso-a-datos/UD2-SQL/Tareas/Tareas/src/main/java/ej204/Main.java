package ej204;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        // 1. Los datos completos de los empleados.
        consulta("SELECT * FROM empleados.emp;");

        // 2. Los datos completos de los departamentos.
        consulta("SELECT * FROM empleados.depto;");

        // 3. Los datos de los empleados con puesto CONTABLE.
        consulta("SELECT * FROM empleados.emp WHERE puesto LIKE 'CONTABLE'");

        // 4. Los datos de los empleados con puesto CONTABLE, ordenados por el nombre.
        consulta("SELECT * FROM empleados.emp WHERE puesto LIKE 'CONTABLE' ORDER BY nomemp;");

        // 5. Los datos de los empleados con puesto CONTABLE, ordenados por el nombre en orden inverso al alfabético.
        consulta("SELECT * FROM empleados.emp WHERE puesto LIKE 'CONTABLE' ORDER BY nomemp DESC;");

        // 6. El nombre y salario de los empleados.
        consulta("SELECT nomemp, sal FROM empleados.emp;");

        // 7. Los nombres de los departamentos.
        consulta("SELECT nomdep FROM empleados.depto;");

        // 8. Los nombres de los departamentos, ordenados por localidad.
        consulta("SELECT nomdep FROM empleados.depto ORDER BY localidad;");

        // 9. El nombre y puesto de los empleados, ordenados por salario (de mayor a menor).
        consulta("SELECT nomemp FROM empleados.emp ORDER BY sal DESC;");

        // 10. El nombre y puesto de los empleados, ordenados por empleo y salario.
        consulta("SELECT nomemp, puesto FROM empleados.emp ORDER BY puesto, sal;");

        // 11. Los salarios y las comisiones de los empleados del departamento 3.
        consulta("SELECT sal, comision FROM empleados.emp WHERE numdep = 3;");

        // 12. Los salarios y las comisiones de los empleados del departamento 3, ordenados por comisión.
        consulta("SELECT sal, comision FROM empleados.emp WHERE numdep = 3 ORDER BY comision;");

        // 13. Las diferentes comisiones de los empleados.
        consulta("SELECT comision FROM empleados.emp GROUP BY comision;");

        // 14. Los nuevos salarios que resultarían de sumar a los empleados del departamento 3 un bonus de 1000 euros.
        consulta("SELECT (sal + 1000) FROM empleados.emp WHERE numdep = 3;");

        // 15. Los empleados que tienen una comisión superior a la mitad de su salario.
        consulta("SELECT * FROM empleados.emp WHERE comision > (sal/2);");

        // 16. Los empleados cuya comisión es menor o igual que el 25% de su sueldo.
        consulta("SELECT * FROM empleados.emp WHERE comision <= (sal*0.25) OR comision IS NULL;");

        // 17. El salario y la comisión de los empleados cuyo número de empleado supere el 7500.
        consulta("SELECT sal, comision FROM empleados.emp WHERE numemp > 7500;");

        // 18. Aquellos empleados que no tienen comisión, ordenados por número de empleado.
        consulta("SELECT * FROM empleados.emp WHERE comision IS NULL ORDER BY numemp;");

        // 19. El salario, la comisión y el salario total de todos los empleados.
        consulta("SELECT sal, comision, SUM(sal) FROM empleados.emp GROUP BY numemp;");

        // 20. Los empleados del departamento 1 cuyo nombre no contiene la cadena LA.
        consulta("SELECT * FROM empleados.emp WHERE numdep = 1 AND nomemp NOT LIKE '%LA%'");

        // 21. Los nombres de los departamentos que no sean de VENTAS ni de ADMINISTRACIÓN, ordenados por localidad.
        consulta("SELECT nomdep FROM empleados.depto WHERE nomdep NOT IN ('VENTAS','ADMINISTRACION') ORDER BY localidad;");

        // 22. El nombre y el departamento de los comerciales que no trabajan en el departamento 1 y cuyo salario
        // es superior a 800, ordenados por la fecha de incorporación.
        consulta("SELECT nomemp, numdep FROM empleados.emp WHERE numdep != 1 AND sal > 800 ORDER BY feccont;");

        // 23. Los nombres de empleados que tengan al menos 5 caracteres.
        consulta("SELECT nomemp FROM empleados.emp WHERE LENGTH(nomemp) >= 5;");

        // 24. El salario máximo de la empresa, el total destinado a comisiones y el número total de empleados.
        consulta("SELECT MAX(sal) AS SALARIO_MAX, SUM(COALESCE(comision, 0)) AS TOTAL_COMISIONES, COUNT(*) AS NUM_EMPLEADOS FROM empleados.emp;");

        // 25. Los departamentos que tienen más de 3 empleados.
        consulta("SELECT d.numdep, d.nomdep, COUNT(e.numemp) AS CANTIDAD_EMPLEADOS FROM empleados.depto d JOIN empleados.emp e ON d.numdep = e.numdep GROUP BY d.numdep, d.nomdep HAVING COUNT(e.numdep) > 3;");

        // 26. Los 2 empleados con mayor salario.
        consulta("SELECT * FROM empleados.emp ORDER BY sal DESC LIMIT 2;");

        // 27. Los empleados cuyo salario supera o coincide con la media del salario de la empresa.
        consulta("SELECT * FROM empleados.emp WHERE sal >= (SELECT AVG(sal) FROM empleados.emp);");

        // 28. Los empleados cuyo salario supera al de sus compañeros de departamento.
        // TODO
        /*SELECT * FROM empleados.emp e GROUP BY numdep HAVING MAX(sal);*/

        // 29. Los departamentos que no tienen empleados.
        consulta("SELECT * FROM empleados.depto d WHERE numdep NOT IN (SELECT numdep FROM empleados.emp e GROUP BY numdep) GROUP BY numdep;");

        // 30. El departamento cuya suma de salarios sea la más alta.
        consulta("SELECT numdep FROM empleados.emp e GROUP BY numdep ORDER BY sum(sal) DESC LIMIT 1;");

        // 31. Los empleados que no son supervisados por ningún otro.
        consulta("SELECT e.* FROM empleados.emp e, empleados.depto d WHERE e.numemp = d.numjefe;");

        // 32. Los empleados que trabajen en SANTIAGO o VILAGARCIA.
        consulta("""
                    SELECT *
                    FROM empleados.emp e\s
                    WHERE e.numdep IN (
                    	SELECT  numdep\s
                    	FROM empleados.depto\s
                    	WHERE localidad IN ('SANTIAGO','VILAGARCIA')
                    );
                """);

        // 33. Los empleados con mayor salario de cada departamento.
        consulta("SELECT  numdep, max(sal) AS maximo_salario FROM empleados.emp e GROUP BY numdep;");

        /* ==== MODIFICACIONES ====*/
        // 1. Cambia el nombre del empleado con número de empleado 7499 a JORGE.
        modificacion("UPDATE empleados.emp SET nomemp = 'JORGE' WHERE numemp = 7499;");

        // 2. Cambia la localidad del departamento OPERACIONES a MADRID.
        modificacion("UPDATE empleados.depto SET localidad = 'MADRID' WHERE nomdep LIKE 'OPERACIONES';");

        // 3. Ponle un salario de 3000 euros a todos los empleados con código superior a 7800.
        modificacion("UPDATE empleados.emp SET sal = 3000 WHERE numemp > 7800;");

        // 4. Borra los datos del empleado FORD.
        modificacion("DELETE FROM empleados.emp WHERE emp.nomemp like 'FORD';");

        // 5. Borra los datos del empleado 7934.
        //modificacion("DELETE FROM empleados.emp WHERE numemp = 7934;");

        //??? 6. Borra los datos del departamento número 3.
        /*???*/
    }

    public static void consulta(String sql) {
        final String URL = "jdbc:mysql://127.0.0.1:3306/";
        final String USER = "root";
        final String PASS = "abc123.";
        try (Connection con = DriverManager.getConnection(URL, USER, PASS); Statement stmt = con.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(sql);
            System.out.printf("~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ %20s ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n", sql);
            printResultSet(resultSet);
            for (int i = 0; i < 75; i++) {
                System.out.print("- ");
            }
            System.out.println("\n");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void modificacion(String sql) {
        final String URL = "jdbc:mysql://127.0.0.1:3306/";
        final String USER = "root";
        final String PASS = "abc123.";
        try (Connection con = DriverManager.getConnection(URL, USER, PASS); Statement stmt = con.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("SE HA REALIZADO LA MODIFICACION: " + sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void printResultSet(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        // Imprimir encabezados en mayúsculas
        for (int i = 1; i <= columnCount; i++) {
            if (i > 1) {
                System.out.print("   ");
            }
            System.out.printf("%-20s", metaData.getColumnName(i).toUpperCase());
        }
        System.out.println();
        for (int i = 0; i < 75; i++) {
            System.out.print("- ");
        }
        System.out.println();
        // Imprimir datos
        while (resultSet.next()) {
            for (int i = 1; i <= columnCount; i++) {
                if (i > 1) {
                    System.out.print("   ");
                }
                System.out.printf("%-20s", resultSet.getString(i));
            }
            System.out.println();
        }
    }
}
