package com.example.demo2.dao;

import lombok.SneakyThrows;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.ShellRunner;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.internal.DefaultCommentGenerator;
import org.springframework.util.ObjectUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;


/**
 * 自定义mybatis注释生成实现类
 *
 * @author 860120014
 * @date 2021-07-21
 */
public class MyCommentGenerator extends DefaultCommentGenerator {

    /**
     * 通过maven:plugins:mybatis-generator运行生成代码会报错:Cannot instantiate object xxx.MyCommentGenerator
     * 请运行此方法生成代码
     *
     * @param args
     */
    public static void main(String[] args) {
        args = new String[] { "-configfile", "src\\main\\resources\\generatorConfig.xml", "-overwrite" };
        ShellRunner.main(args);
    }

    private final Properties systemPro;
    private final String currentDateStr;

    public MyCommentGenerator() {
        super();
        systemPro = System.getProperties();
        currentDateStr = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
    }

    @Override
    public void addFieldComment(Field field,
                                IntrospectedTable introspectedTable,
                                IntrospectedColumn introspectedColumn) {

        if (introspectedColumn.getRemarks() != null && !"".equals(introspectedColumn.getRemarks())) {
            field.addJavaDocLine("/**");
            field.addJavaDocLine(" * " + "对应字段："+introspectedColumn.getActualColumnName() );
            field.addJavaDocLine(" * " + "字段含义："+ introspectedColumn.getRemarks());
            field.addJavaDocLine(" */");
        }
    }

    @SneakyThrows
    @Override
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

        StringBuilder sb = new StringBuilder();
        topLevelClass.addJavaDocLine("/**");
        sb.append(" * ");
        if (ObjectUtils.isEmpty(introspectedTable.getRemarks())){
            // 表名
            sb.append(introspectedTable.getFullyQualifiedTable());
        }else {
            // 表注释
            sb.append(introspectedTable.getRemarks());
        }
        topLevelClass.addJavaDocLine(sb.toString());

        sb.setLength(0);
        sb.append(" * ");
        topLevelClass.addJavaDocLine(sb.toString());

        // 添加作者
        sb.setLength(0);
        sb.append(" * @author ").append(systemPro.getProperty("user.name"));
        topLevelClass.addJavaDocLine(sb.toString());

        // 添加时间
        sb.setLength(0);
        sb.append(" * @date ").append(currentDateStr);
        topLevelClass.addJavaDocLine(sb.toString());

        topLevelClass.addJavaDocLine(" */");
    }

//    private Map<ActualTableName, List<IntrospectedColumn>> getColumns(IntrospectedTable introspectedTable) throws SQLException {
//
//        String localTableName = introspectedTable.getFullyQualifiedTable().getIntrospectedTableName();
//        TableConfiguration tc = introspectedTable.getTableConfiguration();
//
//        Connection connection = DriverManager.getConnection(
//                introspectedTable.getContext().getJdbcConnectionConfiguration().getConnectionURL(),
//                introspectedTable.getContext().getJdbcConnectionConfiguration().getUserId(),
//                introspectedTable.getContext().getJdbcConnectionConfiguration().getPassword());
//
//        Logger logger = LoggerFactory.getLogger(this.getClass());
//        /**
//         * 数据库类型判断，如果是sqlServer则执行下面语句,做到对其他数据库的兼容
//         */
//        ResultSet sqlServerResultSet = null;
//        boolean isSqlServer = connection.getMetaData().getDriverName().toUpperCase()
//                .indexOf("SQL SERVER") != -1;
//        if (isSqlServer) {
//            //sqljdbc与sqljdbc4不同，sqlserver中间有空格
//            String sql = "SELECT cast(value as varchar(500))  AS REMARKS FROM sys.extended_properties WHERE minor_id in (SELECT column_id FROM sys.columns WHERE object_id = (SELECT object_id FROM sys.tables WHERE name = ?))";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setString(1, localTableName);
//            sqlServerResultSet = ps.executeQuery();
//        }
////        while (sqlServerResultSet.next()){
////            String s = new String(sqlServerResultSet.getString(1));
////            System.out.println(s);
////        }
//
//        Map<ActualTableName, List<IntrospectedColumn>> answer = new HashMap<>();
//
//        while (sqlServerResultSet.next()) {
//            IntrospectedColumn introspectedColumn = ObjectFactory
//                    .createIntrospectedColumn(introspectedTable.getContext());
//            if (isSqlServer){
//                sqlServerResultSet.next();
//                introspectedColumn.setRemarks(sqlServerResultSet.getString("REMARKS"));
//            }else {
//                introspectedColumn.setRemarks(sqlServerResultSet.getString("REMARKS"));
//            }
//            introspectedColumn.setTableAlias(tc.getAlias());
//            introspectedColumn.setJdbcType(sqlServerResultSet.getInt("DATA_TYPE"));
//            introspectedColumn.setLength(sqlServerResultSet.getInt("COLUMN_SIZE"));
//            introspectedColumn.setActualColumnName(sqlServerResultSet.getString("COLUMN_NAME"));
//            introspectedColumn
//                    .setNullable(sqlServerResultSet.getInt("NULLABLE") == DatabaseMetaData.columnNullable);
//            introspectedColumn.setScale(sqlServerResultSet.getInt("DECIMAL_DIGITS"));
//
//            introspectedColumn.setDefaultValue(sqlServerResultSet.getString("COLUMN_DEF"));
//
//            if ("YES".equals(sqlServerResultSet.getString("IS_AUTOINCREMENT"))) {
//                introspectedColumn.setAutoIncrement(
//                        "YES".equals(sqlServerResultSet.getString("IS_AUTOINCREMENT")));
//            }
//
//            if ("YES".equals(sqlServerResultSet.getString("IS_GENERATEDCOLUMN"))) {
//                introspectedColumn.setGeneratedColumn(
//                        "YES".equals(sqlServerResultSet.getString("IS_GENERATEDCOLUMN")));
//            }
//
//            ActualTableName atn = new ActualTableName(
//                    sqlServerResultSet.getString("TABLE_CAT"),
//                    sqlServerResultSet.getString("TABLE_SCHEM"),
//                    sqlServerResultSet.getString("TABLE_NAME"));
//
//            List<IntrospectedColumn> columns = answer.get(atn);
//            if (columns == null) {
//                columns = new ArrayList<IntrospectedColumn>();
//                answer.put(atn, columns);
//            }
//
//            columns.add(introspectedColumn);
//
//            if (logger.isDebugEnabled()) {
//                logger.debug(getString(
//                        "Tracing.2",
//                        introspectedColumn.getActualColumnName(), Integer
//                                .toString(introspectedColumn.getJdbcType()),
//                        atn.toString()));
//            }
//        }
//        return null;
//    }
}
