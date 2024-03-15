package com.example.apisearchpracticebase.Configuration;

import com.smattme.MysqlExportService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.*;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "scheduler.enabled", matchIfMissing = true)
public class BackupConfiguration {
    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String password;
    @Value("${spring.datasource.database.name}")
    String databaseName;
    @Value("${data.database.path.backup}")
    String backupPath;

    @Scheduled(fixedDelay = 1500*60*60*4)
    public void createBackUpDataBase(){
        try {
            Properties properties = new Properties();
            properties.setProperty(MysqlExportService.DB_NAME, databaseName);
            properties.setProperty(MysqlExportService.DB_USERNAME, username);
            properties.setProperty(MysqlExportService.DB_PASSWORD, password);
            properties.setProperty(MysqlExportService.TEMP_DIR, new File("external").getPath());

            MysqlExportService mysqlExportService = new MysqlExportService(properties);
            mysqlExportService.export();
            backupPath += mysqlExportService.getSqlFileName();
            PrintWriter writer = new PrintWriter(backupPath, "utf-8");
            writer.println(mysqlExportService.getGeneratedSql());
            writer.close();
            mysqlExportService.clearTempFiles();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}