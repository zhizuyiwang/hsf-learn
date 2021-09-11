package com.hsf.my.batis.sqlSession;

import com.hsf.my.batis.pojo.Configuration;
import com.hsf.my.batis.pojo.MappedStatement;

import java.lang.reflect.*;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class DefaultSqlSession implements SqlSession {
    private Configuration configuration;
    //处理器对象
    private SimpleExecutor simpleExecutor = new SimpleExecutor();
    public DefaultSqlSession(Configuration configuration){
        this.configuration = configuration;
    }

    //内部调用selectList
    @Override
    public <E> E selectOne(String statementId, Object... params) throws Exception {

        List<Object> objects = selectList(statementId, params);
        if(objects.size() == 1){
            return (E) objects.get(0);
        }else{
            throw new RuntimeException("获取的结果不是一个");
        }
    }

    @Override
    public <E> List<E> selectList(String statementId, Object... params) throws Exception {
        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementId);

        List<E> result = simpleExecutor.query(configuration, mappedStatement, params);

        return result;

    }

    @Override
    public void close() throws SQLException {
        simpleExecutor.close();
    }

    @Override
    public <T> T getMapper(Class<?> mapperClass) {

        // 使用JDK动态代理来为Dao接口生成代理对象，并返回
        T o = (T) Proxy.newProxyInstance(mapperClass.getClassLoader(), new Class<?>[]{mapperClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 底层都还是去执行JDBC代码 //根据不同情况，来调用selectList或者selectOne
                // 准备参数 1：statementId :sql语句的唯一标识：namespace.id= 接口全限定名.方法名
                // 方法名：findAll
                String methodName = method.getName();
                String className = method.getDeclaringClass().getName();
                String statementId = className + "." + methodName;

                // 准备参数2：params:args
                // 获取被调用方法的返回值类型
                Type genericReturnType = method.getGenericReturnType();
                // 判断是否进行了泛型类型参数化
                if(genericReturnType instanceof ParameterizedType){
                    return selectList(statementId, args);
                }else{
                    return selectOne(statementId, args);
                }
            }
        });
        return o;
    }
}
