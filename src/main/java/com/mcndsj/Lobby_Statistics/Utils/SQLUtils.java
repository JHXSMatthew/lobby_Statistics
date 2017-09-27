package com.mcndsj.Lobby_Statistics.Utils;

import com.mcndsj.JHXSMatthew.Shared.GameManager;
import com.mcndsj.Lobby_Statistics.Cache.Data;
import com.mcndsj.Lobby_Statistics.Cache.DataType;


import java.sql.*;

/**
 * Created by Matthew on 2016/4/14.
 */
public class SQLUtils {

    public static Data getFullRow(String key, DataType type){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            connection = GameManager.getInstance().getConnection();
            if(connection == null || connection.isClosed()){
                return null;
            }
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM `"+type.getSQLTableName()+"` Where `"+type.getSQLKeyName()+"`='"+key+"';");
            //System.out.print("SELECT * FROM `"+type.getSQLTableName()+" Where `"+type.getSQLKeyName()+"`='"+key+"';");
            if(!resultSet.next()){
               return null;
        }
            Data data = new Data(type);
            ResultSetMetaData meta = resultSet.getMetaData();
            int count = meta.getColumnCount();

            for(int i = 1; i <= count ; i ++){
                data.put(meta.getColumnName(i),resultSet.getObject(i));
            }
            return data;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
        finally {
            if (resultSet != null) try { resultSet.close(); } catch (SQLException e) {e.printStackTrace();}
            if (statement != null) try { statement.close(); } catch (SQLException e) {e.printStackTrace();}
            if (connection != null) try { connection.close(); } catch (SQLException e) {e.printStackTrace();}
        }
    }

    public static Data getFullRow(String key, DataType type, Data data){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            connection = GameManager.getInstance().getConnection();
            if(connection == null || connection.isClosed()){
                return null;
            }
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM `"+type.getSQLTableName()+"` Where `"+type.getSQLKeyName()+"`='"+key+"';");
            if(!resultSet.next()) {
                return null;
            }
            ResultSetMetaData meta = resultSet.getMetaData();
            int count = meta.getColumnCount();
            for(int i = 1; i <= count ; i ++){
                data.put(meta.getColumnName(i),resultSet.getObject(i));
            }
            return data;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
        finally {
            if (resultSet != null) try { resultSet.close(); } catch (SQLException e) {e.printStackTrace();}
            if (statement != null) try { statement.close(); } catch (SQLException e) {e.printStackTrace();}
            if (connection != null) try { connection.close(); } catch (SQLException e) {e.printStackTrace();}
        }
    }
    
}
