package com.ntdat.plan_management_sysyem.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ImageConfig {
    public static String defaultImage = AppConfig.root_dir + "/src/main/resources/com/ntdat/plan_management_sysyem/images/default-user.jpeg";
    public static String formatUnix(String path) {
        return path.replace("\\", "/").replace("//", "/");
    }

    public static String formatPathRead(String path) {
        return AppConfig.root_dir + ImageConfig.formatUnix(path).replace(AppConfig.root_dir, "");
    }

    public static String formatPathWrite(String path) {
        return formatUnix(path).replace(AppConfig.root_dir, "");
    }

    public static String formatCopy(String path) {
        var unixPath = formatUnix(path);
        return AppConfig.root_dir + "/src/main/resources/com/ntdat/plan_management_sysyem/images/"
                + unixPath.substring(unixPath.lastIndexOf("/") + 1);
    }

    public static String writeToDisk(String path) throws IOException {
        Path transfer = Paths.get(path);
        var copyPath = Paths.get(formatCopy(path));
        if (transfer.equals(copyPath)) {
            return formatPathWrite(copyPath.toAbsolutePath().toString());
        }
        Files.copy(transfer, copyPath, StandardCopyOption.REPLACE_EXISTING);
        return formatPathWrite(copyPath.toAbsolutePath().toString());
    }
}
