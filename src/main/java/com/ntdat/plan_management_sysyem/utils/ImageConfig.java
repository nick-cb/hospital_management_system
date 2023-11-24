package com.ntdat.plan_management_sysyem.utils;

public class ImageConfig {
    public static String defaultImage = AppConfig.root_dir + "/src/main/resources/com/ntdat/plan_management_sysyem/images/default.png";
    public static String formatUnix(String path) {
        return path.replace("\\", "/").replace("//", "/");
    }

    public static String formatPathRead(String path) {
        return AppConfig.root_dir + ImageConfig.formatUnix(path);
    }

    public static String formatPathWrite(String path) {
        return formatUnix(path).replace(AppConfig.root_dir, "");
    }

    public static String formatCopy(String path) {
        var unixPath = formatUnix(path);
        return AppConfig.root_dir + "/src/main/resources/com/ntdat/plan_management_sysyem/images/"
                + unixPath.substring(unixPath.lastIndexOf("/") + 1);

    }
}
