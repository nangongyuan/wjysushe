/**
 * Copyright (C), 2015-2019, 虹软
 * FileName: DBHelper
 * Author:   yuan
 * Date:     2019/2/20 11:25
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * yuan           修改时间           版本号              描述
 */
package com.wjysushe.utils;


import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author ly6333
 * @create 2019/2/20
 * @since 1.0.0
 */
public class DBHelper {
    private static final String DRIVENAME = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/wjysushe?useUnicode=true&characterEncoding=utf8&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private Connection conn = null;
    private Statement st = null;
    private PreparedStatement ppst = null;
    private ResultSet rs = null;

    /**
     * 加载驱动
     */
    static{
        try {
            Class.forName(DRIVENAME).newInstance();
        } catch (Exception e) {
            System.out.println("驱动加载失败："+e.getMessage());
        }
    }


    /**
     * 连接数据库
     * @return
     */
    private Connection getConn(){
        try {
            conn =  DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (SQLException e) {
            System.out.println("数据库连接失败："+e.getMessage());
        }
        return conn;
    }


    /**
     * 获取结果集（无参）
     * @param sql
     * @return
     */
    private ResultSet getRs(String sql){
        conn = this.getConn();
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("查询（无参）出错:"+e.getMessage());
        }
        return rs;
    }


    /**
     * 获取结果集
     * @param sql
     * @param params
     * @return
     */
    private ResultSet getRs(String sql,Object[] params){
        conn = this.getConn();
        try {
            ppst = conn.prepareStatement(sql);
            if(params!=null){
                for(int i = 0;i<params.length;i++){
                    ppst.setObject(i+1, params[i]);
                }
            }
            rs = ppst.executeQuery();
        } catch (SQLException e) {
            System.out.println("查询出错："+e.getMessage());
        }

        return rs;
    }


    /**
     * 查询
     * @param sql
     * @param params
     * @return
     */
    public List<Map<String, Object>> query(String sql, Object[] params){

        List<Map<String, Object>> list = new ArrayList<>();
        ResultSet rs = null;
        if(params!=null){
            rs = getRs(sql, params);
        }else{
            rs = getRs(sql);
        }
        ResultSetMetaData rsmd = null;
        int columnCount = 0;

        try {
            rsmd = rs.getMetaData();
            columnCount = rsmd.getColumnCount();
            while(rs.next()){
                Map<String, Object> map = new HashMap<String, Object>();
                for(int i = 1;i<=columnCount;i++){
                    map.put(rsmd.getColumnLabel(i), rs.getObject(i));
                }
                list.add(map);
            }
        } catch (SQLException e) {
            System.out.println("结果集解析出错："+e.getMessage());
        } finally {
            closeConn();
        }
        return list;
    }


    /**
     * 保存
     * @param sql
     */
    public int save(String sql,Object... params){
        int affectedLine = 0;
        conn = this.getConn();
        try {
            ppst = conn.prepareStatement(sql);
            if(params!=null){
                for(int i = 0;i<params.length;i++){
                    ppst.setObject(i+1, params[i]);
                }
            }
            affectedLine = ppst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("保存失败："+e.getMessage());
        } finally {
            closeConn();
        }
        return affectedLine;
    }


    /**
     * 更新
     * @param sql
     * @param params
     * @return
     */
    public int update(String sql,Object... params){
        int affectedLine = 0;
        conn = this.getConn();
        try {
            ppst = conn.prepareStatement(sql);
            if(params!=null){
                for(int i = 0;i<params.length;i++){
                    ppst.setObject(i+1, params[i]);
                }
            }
            affectedLine = ppst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("更新失败："+e.getMessage());
        } finally {
            closeConn();
        }
        return affectedLine;
    }


    /**
     * 保存
     * @param sql
     */
    public int delete(String sql){
        int affectedLine = 0;
        conn = this.getConn();
        try {
            st = conn.createStatement();
            affectedLine = st.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("删除失败："+e.getMessage());
        } finally {
            closeConn();
        }
        return affectedLine;
    }


    private void closeConn(){

        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        if(st!=null){
            try {
                st.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        if(ppst!=null){
            try {
                ppst.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }


}