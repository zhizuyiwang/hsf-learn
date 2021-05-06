package com.hsf.my.netty.batis.sqlSession;

import com.hsf.my.netty.batis.config.BoundSql;
import com.hsf.my.netty.batis.pojo.Configuration;
import com.hsf.my.netty.batis.pojo.MappedStatement;
import com.hsf.my.netty.batis.utils.GenericTokenParser;
import com.hsf.my.netty.batis.utils.ParameterMapping;
import com.hsf.my.netty.batis.utils.ParameterMappingTokenHandler;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SimpleExecutor implements Executor {
    private Connection connection = null;
    @Override
    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object[] params) throws Exception {
        //1、在数据库连接池中获取数据库连接
        connection = configuration.getDataSource().getConnection();
        //2、获取sql语句：select * from user where id = #{id} and username = #{username}
        //还需要转换sql语句： select * from user where id = ? and username = ? ，转换的过程中，还需要对#{}里面的值进行解析存储
        String sql = mappedStatement.getSql();
        //对Sql进行处理
        BoundSql boundSql = getBoundSql(sql);

        //3、获取最终的Sql语句，如select * from user where id = ? and username = ?
        String finalSql = boundSql.getSqlText();
        //4、获取传入参数的类型
        Class<?> parameterType = mappedStatement.getParameterType();
        //5、获取预编译preparedStatement对象
        PreparedStatement preparedStatement = connection.prepareStatement(finalSql);
        List<ParameterMapping> parameterMappingList = boundSql.getParameterMappingList();
        for (int i = 0; i < parameterMappingList.size(); i++){
            ParameterMapping parameterMapping = parameterMappingList.get(i);
            String content = parameterMapping.getContent();

            //反射
            Field declaredField = parameterType.getDeclaredField(content);
            declaredField.setAccessible(true);
            //参数的值
            Object o = declaredField.get(params[0]);
            //给占位符赋值
            preparedStatement.setObject(1 + i, o);
        }

        ResultSet resultSet = preparedStatement.executeQuery();
        Class<?> resultType = mappedStatement.getResultType();
        ArrayList<E> result = new ArrayList<>();
        while (resultSet.next()){
            E object = (E) resultType.newInstance();//目标实体
            //获取元数据
            ResultSetMetaData metaData = resultSet.getMetaData();
            //返回结果的列的个数
            int columnCount = metaData.getColumnCount();
            for(int i = 1; i <= columnCount; i++){
                //字段名
                String columnName = metaData.getColumnName(i);
                //字段值
                Object value = resultSet.getObject(columnName);
                //使用反射或者内省，根据数据库表和实体的对应关系，完成封装
                //创建属性描述器，为属性生成读写方法
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, resultType);
                //获取实体该属性的写方法
                Method writeMethod = propertyDescriptor.getWriteMethod();
                //通过该属性的写方法，把改属性的值写进实体对象中
                writeMethod.invoke(object,value);
            }
            result.add(object);
        }
        return result;
    }

    @Override
    public void close() throws SQLException {
        connection.close();
    }

    /**
     * 完成对#{}的解析工作：1.将#{}使用？进行代替，2.解析出#{}里面的值进行存储
     * @param sql
     * @return
     */
    private BoundSql getBoundSql(String sql){
        //标记处理类：主要是配合通用标记解析器GenericTokenParser类完成对配置文件等的解析
        //工作，其中TokenHandler主要完成处理
        ParameterMappingTokenHandler parameterMappingTokenHandler = new
                ParameterMappingTokenHandler();
        //GenericTokenParser :通用的标记解析器，完成了代码片段中的占位符的解析，然后再根
        //据给定的标记处理器(TokenHandler)来进行表达式的处理
        //三个参数：分别为openToken (开始标记)、closeToken (结束标记)、handler (标记处理器)
        GenericTokenParser genericTokenParser = new GenericTokenParser("# {",
                "}", parameterMappingTokenHandler);
        String parse = genericTokenParser.parse(sql);

        //#{}里面解析出来的参数名称
        List<ParameterMapping> parameterMappings =
                parameterMappingTokenHandler.getParameterMappings();

        BoundSql boundSql = new BoundSql(parse, parameterMappings);

        return boundSql;
    }
}
