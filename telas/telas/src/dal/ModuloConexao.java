
package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

   public class ModuloConexao {
    public static Connection connector(){
        
        java.sql.Connection conexao = null;
        
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/db_escola";
        String user = "root";
        String password = "C@rol2004";   
        
        try{
          Class.forName(driver);
          conexao = DriverManager.getConnection(url, user, password);
          return conexao;
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public static Connection conector() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}



