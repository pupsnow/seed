package com.jadyer.seed.simcoder.helper;

import com.jadyer.seed.comm.util.JadyerUtil;
import com.jadyer.seed.simcoder.SimcoderRun;
import com.jadyer.seed.simcoder.model.Column;
import com.jadyer.seed.simcoder.model.Table;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.resource.ClasspathResourceLoader;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 玄玉<http://jadyer.cn/> on 2017/9/8 23:22.
 */
public class GenerateHelper {
    private static GroupTemplate groupTemplate = null;
    static {
        try {
            groupTemplate = new GroupTemplate(new ClasspathResourceLoader("templates/"), Configuration.defaultConfiguration());
            groupTemplate.setSharedVars(new HashMap<String, Object>(){
                private static final long serialVersionUID = -7774932094711543319L;
                {
                    put("serialVersionUID", JadyerUtil.buildSerialVersionUID());
                    put("PACKAGE_MODEL", SimcoderRun.PACKGET_PREFIX + ".web.model");
                    put("PACKAGE_SERVICE", SimcoderRun.PACKGET_PREFIX + ".web.service");
                    put("PACKAGE_CONTROLLER", SimcoderRun.PACKGET_PREFIX + ".web.controller");
                    put("PACKAGE_REPOSITORY", SimcoderRun.PACKGET_PREFIX + ".web.repository");
                }
            });
        } catch (IOException e) {
            System.err.println("加载Beetl模板失败，堆栈轨迹如下：");
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * 判断给定的表名是否存在于包含列表
     */
    private static boolean isInclude(String tablename, String... includeTablename){
        if(null==includeTablename || includeTablename.length==0){
            return true;
        }
        for(String obj : includeTablename){
            if(obj.equals(tablename)){
                return true;
            }
        }
        return false;
    }


    /**
     * 生成整个数据库的
     * @param databaseName     数据库名
     * @param includeTablename 包含列表，有此值时则以此值为准，即此时只为数据库中的这些表生成代码
     */
    public static void generate(String databaseName, String... includeTablename){
        List<Table> tableList = DBHelper.getTableList(databaseName);
        for(Table table : tableList){
            if(isInclude(table.getName(), includeTablename)){
                generateFromTable(table.getName(), table.getComment());
            }
        }
    }


    /**
     * 生成某张表的
     */
    private static void generateFromTable(String tablename, String tablecomment){
        boolean hasDate = false;
        boolean hasBigDecimal = false;
        boolean hasColumnAnnotation = false;
        boolean hasNotNullAnnotation = false;
        boolean hasNotBlankAnnotation = false;
        boolean hasNotBlankSizeAnnotation = false;
        StringBuilder fields = new StringBuilder();
        StringBuilder methods = new StringBuilder();
        Map<String, String> fieldnameMap = new HashMap<>();
        List<Column> columnList = DBHelper.getColumnList(tablename);
        for(int i=0; i<columnList.size(); i++){
            if(StringUtils.equalsAnyIgnoreCase(columnList.get(i).getName(), "id", "create_time", "update_time")){
                continue;
            }
            ///** 属性注释 */
            if(StringUtils.isNotBlank(columnList.get(i).getComment())){
                fields.append("    /** ").append(columnList.get(i).getComment()).append(" */").append("\n");
            }
            //暂时只对Integer、Long、String三种类型增加校验注解：@NotNull @NotBlank @Size(max=16)
            String javaType = DBHelper.buildJavatypeFromDbtype(columnList.get(i).getType());
            if(!columnList.get(i).isNullable()){
                if("Integer".equals(javaType) || "Long".equals(javaType)){
                    hasNotNullAnnotation = true;
                    fields.append("    @NotNull").append("\n");
                    fields.append("    //@Min(1)").append("\n");
                    fields.append("    //@Max(3)").append("\n");
                }
                if("String".equals(javaType)){
                    hasNotBlankAnnotation = true;
                    fields.append("    @NotBlank").append("\n");
                    if(columnList.get(i).getLength() > 0){
                        hasNotBlankSizeAnnotation = true;
                        //TODO 针对CHAR(6)做处理
                        fields.append("    @Size(max=").append(columnList.get(i).getLength()).append(")").append("\n");
                    }
                }
            }
            //@Column(name="bind_status")
            String fieldname = DBHelper.buildFieldnameFromColumnname(columnList.get(i).getName());
            if(!fieldname.equals(columnList.get(i).getName())){
                hasColumnAnnotation = true;
                fields.append("    @Column(name=\"").append(columnList.get(i).getName()).append("\")").append("\n");
            }
            //private int bindStatus;
            if("Date".equals(javaType)){
                hasDate = true;
            }
            if("BigDecimal".equals(javaType)){
                hasBigDecimal = true;
            }
            fields.append("    private ").append(javaType).append(" ").append(fieldname).append(";").append("\n");
            //getter and setter
            methods.append("    public ").append(javaType).append(" get").append(StringUtils.capitalize(fieldname)).append("() {").append("\n");
            methods.append("        return this.").append(fieldname).append(";").append("\n");
            methods.append("    }").append("\n");
            methods.append("\n");
            methods.append("    public void set").append(StringUtils.capitalize(fieldname)).append("(").append(javaType).append(" ").append(fieldname).append(") {").append("\n");
            methods.append("        this.").append(fieldname).append(" = ").append(fieldname).append(";").append("\n");
            methods.append("    }");
            //方法与方法直接都空一行，并且最后一个setter之后就不用换行了
            if(i+1 != columnList.size()-2){
                methods.append("\n\n");
            }
            //收集属性，供分页查询时作为条件
            fieldnameMap.put(fieldname, javaType);
        }
        /*
         * 用户信息
         * Generated from seed-simcoder by 玄玉<http://jadyer.cn/> on 2017/9/5 14:40.
         */
        if(StringUtils.isNotBlank(tablecomment)){
            if(tablecomment.endsWith("表")){
                tablecomment = tablecomment.substring(0, tablecomment.length()-1);
            }
        }else{
            tablecomment = tablename;
        }
        /*
         * 设置Beetl共享变量（目前2.8.1版本：共享变量只能set一次，第二次set时会冲掉之前所有的，因为源码里是直接改变对象引用的）
         */
        String classname = DBHelper.buildClassnameFromTablename(tablename);
        Map<String, Object> sharedVars = new HashMap<>();
        sharedVars.put("CLASS_NAME", classname);
        sharedVars.put("CLASS_NAME_uncapitalize", StringUtils.uncapitalize(classname));
        sharedVars.put("TABLE_NAME", tablename);
        sharedVars.put("TABLE_NAME_nounderline", (tablename.startsWith("t_") ? tablename.substring(2) : tablename).replaceAll("_", ""));
        sharedVars.put("TABLE_NAME_convertpoint", (tablename.startsWith("t_") ? tablename.substring(2) : tablename).replaceAll("_", "."));
        sharedVars.put("fields", fields.toString());
        sharedVars.put("methods", methods.toString());
        sharedVars.put("tablecomment", tablecomment);
        sharedVars.put("fieldnameMap", fieldnameMap);
        sharedVars.put("hasDate", hasDate);
        sharedVars.put("hasBigDecimal", hasBigDecimal);
        sharedVars.put("hasColumnAnnotation", hasColumnAnnotation);
        sharedVars.put("hasNotNullAnnotation", hasNotNullAnnotation);
        sharedVars.put("hasNotBlankAnnotation", hasNotBlankAnnotation);
        sharedVars.put("hasNotBlankSizeAnnotation", hasNotBlankSizeAnnotation);
        groupTemplate.setSharedVars(sharedVars);
        /*
         * 解析Beetl模板
         */
        try {
            String outBaseDir = FileSystemView.getFileSystemView().getHomeDirectory().getPath() + System.getProperty("file.separator");
            groupTemplate.getTemplate("model.btl").renderTo(FileUtils.openOutputStream(new File(outBaseDir + "model" + System.getProperty("file.separator") + classname + ".java")));
            groupTemplate.getTemplate("service.btl").renderTo(FileUtils.openOutputStream(new File(outBaseDir + "service" + System.getProperty("file.separator") + classname + "Service.java")));
            groupTemplate.getTemplate("controller.btl").renderTo(FileUtils.openOutputStream(new File(outBaseDir + "controller" + System.getProperty("file.separator") + classname + "Controller.java")));
            groupTemplate.getTemplate("repository.btl").renderTo(FileUtils.openOutputStream(new File(outBaseDir + "repository" + System.getProperty("file.separator") + classname + "Repository.java")));
        } catch (IOException e) {
            System.err.println("生成代码时发生异常，堆栈轨迹如下：");
            e.printStackTrace();
        }
    }
}