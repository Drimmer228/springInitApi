package com.example.apisearchpracticebase.Controllers;

import com.smattme.MysqlExportService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Properties;

@RestController
@RequestMapping("/backup")
public class BackupController {
    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String password;
    @Value("${spring.datasource.database.name}")
    String databaseName;
    @Value("${data.database.path.backup}")
    String backupPath;
    @GetMapping("/create")
    public void createBackUpDataBase(){
        try {
            Properties properties = new Properties();
            properties.setProperty(MysqlExportService.DB_NAME, databaseName);
            properties.setProperty(MysqlExportService.DB_USERNAME, username);
            properties.setProperty(MysqlExportService.DB_PASSWORD, password);
            properties.setProperty(MysqlExportService.PRESERVE_GENERATED_ZIP, "true");
            properties.setProperty(MysqlExportService.TEMP_DIR, new File("external").getPath());

            MysqlExportService mysqlExportService = new MysqlExportService(properties);
            mysqlExportService.export();
            File file = mysqlExportService.getGeneratedZipFile();
            Path directoryPath = Paths.get(backupPath);
            Path targetPath = directoryPath.resolve(file.getName());
            Files.move(file.toPath(), targetPath);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}