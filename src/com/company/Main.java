package com.company;

import java.sql.*;
import java.util.Scanner;

public class Main {

    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";

    public static void main(String[] args) throws Exception {

        boolean flag=true;
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;
        Scanner scanner=new Scanner(System.in);
        String tableName=null;
        String query=null;
        String query2=null;
        int numColumn=0;
        String tableNameAlready=null;

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\user1\\Desktop\\DATA.db");
            if (connection != null) {
                System.out.println("Connection OK");
                statement = connection.createStatement();

                do {
                    System.out.println("Press 0 to READ ME");
                    System.out.println("Press 1 to CREATE Table ");
                    System.out.println("Press 2 to INSERT into Table ");
                    System.out.println("Press 3 to UPDATE into Table ");
                    System.out.println("Press 4 to DELETE from Table ");
                    System.out.println("Press 5 to HOW many rows in the Table");
                    System.out.println("Press 6 to SORT by DESC ");
                    System.out.println("Press 7 to SORT by ASC");
                    System.out.println("Press 8 to MAX value");
                    System.out.println("Press 9 to MIN value");
                    System.out.println("Press 10 to if you have already table");
                    System.out.println("Press 11 to EXIT");
                    System.out.println("Enter your choice");
                    int choice=Integer.parseInt(scanner.nextLine());

                    switch(choice) {
                        case 0:
                            System.out.println(
                                    ANSI_GREEN+"welcome :) \n" +
                                            "Through this application, you can create tables and perform operations.\n" +
                                            "Note: This program is designed to create one table and perform operations.\n " +
                                            "If you want to create more than one table, you can do that\n" +
                                            "but you must restart the application and start over.\n" +
                                            "Enjoy : )"+ANSI_BLUE);
                            break;
                        case 1:
                                System.out.println("insert your table name ");
                                tableName = scanner.nextLine();
                                System.out.println("insert the names of ech column of table "+ tableName);
                                System.out.println("hint: just name of column , ex: name varchar,age int,.... ");
                                query=scanner.nextLine();
                                statement.execute("CREATE TABLE " + tableName + " ("+query+")");
                                System.out.println("Table created with name " + tableName+" your column is/are "+query);
                            break;

                        case 2:
                            System.out.println("plz insert your data ->  "+query);
                            query2=scanner.nextLine();
                            statement.execute("Insert into "+tableName+ " values("+query2+" )");
                            System.out.println("row inserted");
                            break;
                        case 3:
                            System.out.println("what you want to update, hint: cheek in your data->"+query);
                            System.out.println("input what you want to update and where , ex: name ='ahmad' where id=10  ");
                            String update=scanner.nextLine();
                            statement.execute("update  "+ tableName+" set "+ update);
                            System.out.println("row updated are: "+update);
                            break;
                        case 4:
                            System.out.println("what you want to delete, hint: cheek in your data->"+query);
                            System.out.println("input what you want to delete and where , ex: name ='ahmad' ");
                            String delete=scanner.nextLine();
                            statement.execute("delete from "+ tableName +" where "+delete);
                            System.out.println("row delete are: "+delete);
                            break;
                        case 5:
                            statement.execute("select count(*) from "+tableName);
                            resultSet = statement.executeQuery("select count(*) from "+tableName);
                            while(resultSet.next()) {
                                System.out.println(
                                        "Number of column is : "+resultSet.getInt(1)
                                );
                            }
                            break;
                        case 6:
                            System.out.println("input just your column to (order by)");
                            String namescolumn=scanner.nextLine();
                            resultSet = statement.executeQuery("select * from "+tableName+ " order by " +namescolumn+ " DESC");
                            while(resultSet.next()) {
                                System.out.println(
                                        resultSet.getString(2)
                                );
                            }
                            break;
                        case 7:
                            System.out.println("input just your column to (order by)");
                            String namesColumn=scanner.nextLine();
                            resultSet = statement.executeQuery("select * from "+tableName+ " order by " +namesColumn+ " ASC");
                            while(resultSet.next()) {
                                System.out.println(
                                        resultSet.getString(2)
                                );
                            }
                            break;
                        case 8:
                            System.out.println("input just your column to show the MAX value");
                            String nameColumn=scanner.nextLine();
                            resultSet = statement.executeQuery("select * from "+tableName+" where "+ nameColumn+" in (select max("+nameColumn+") from " +tableName+")");
                            while(resultSet.next()) {
                                System.out.println(
                                        resultSet.getString(2)
                                );
                            }
                            break;
                        case 9:
                            System.out.println("input just your column to show the MIN value");
                            String namecolumn=scanner.nextLine();
                            resultSet = statement.executeQuery("select * from "+tableName+" where "+ namecolumn+" in (select min("+namecolumn+") from " +tableName+")");
                            while(resultSet.next()) {
                                System.out.println(
                                        resultSet.getString(2)
                                );
                            }
                            break;
                        case 10:
                            System.out.println("what your table name ?");
                            tableNameAlready=scanner.nextLine();
                            if(tableNameAlready!=null)
                            {
                                tableName=tableNameAlready;
                                System.out.println("you can now show the table with name "+tableName);
                            }
                            break;
                        case 11:
                            System.out.println("bye :)");
                            System.exit(1);
                            break;

                        default:
                            System.out.println("wrong input");

                    }
                    System.out.println("do u want to continue(yes /no)");
                    String str=scanner.nextLine();
                    if(str.equalsIgnoreCase("yes")|| str.equalsIgnoreCase("y"))
                        flag=true;
                    if(str.equalsIgnoreCase("no")||str.equalsIgnoreCase("n"))
                        flag=false;

                }
                while(flag);

                /*
                connection.close();
                statement.close();
                resultSet.close();

                 */
            }
        } catch (Exception PSQLException) {
            System.out.println(PSQLException.getMessage());
        }

    }
}
