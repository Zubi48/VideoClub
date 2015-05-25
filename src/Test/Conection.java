
package Test;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author Zubi
 */
public class Conection{

private Connection conexion;

    

public Connection getConexion() {

    return conexion;

}    public void setConexion(Connection conexion) {

    this.conexion = conexion;

} 

public Conection conectar() {

    try {

        Class.forName("oracle.jdbc.OracleDriver");

        String BaseDeDatos = "jdbc:oracle:thin:@localhost:1521/XE";

        conexion = DriverManager.getConnection(BaseDeDatos, "carlos","guero32");    


        if (conexion != null) {

            System.out.println("Conexion exitosa!");

        } else {

            System.out.println("Conexion fallida!");

        }

    } catch (Exception e) {

        e.printStackTrace();

    }        return this;

}



public boolean escribir(String sql) {

        try {

            Statement sentencia;

            sentencia = getConexion().createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

            sentencia.executeUpdate(sql);

            getConexion().commit();

            sentencia.close();

            

        } catch (SQLException e) {

            e.printStackTrace();

            System.out.print("ERROR SQL");

            return false;

        }        

        return true;

    }



public ResultSet consultar(String sql) {

        ResultSet resultado = null;

        try {

            Statement sentencia;

            sentencia = getConexion().createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

            resultado = sentencia.executeQuery(sql);

            

        } catch (SQLException e) {

            e.printStackTrace();

            return null;

        }        return resultado;

    }



}


