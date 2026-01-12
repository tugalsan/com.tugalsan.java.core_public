package com.tugalsan.java.core.annotation.server;

import java.io.*;

final public class TS_AnnotationSearch {

    private TS_AnnotationSearch() {

    }

    private static <T> ClassLoader getClassLoader(Class<T> clazz) {
        var cl = clazz.getClassLoader();
        if (cl == null) {
            return Thread.currentThread().getContextClassLoader();
        }
        return cl;
    }

//    public static void main(String[] args) {
//        sniffPackage("controllers").stream().forEach(path -> System.out.println(path));
//    }
    public static <T> File getResourceDirectory(Class<T> clazz, String packageName) {
        return new File(getClassLoader(clazz).getResource(packageName.replace('.', '/')).getFile());
    }

    public static String getFileNameLabel(File file) {
        var fileName = file.getName();
        return fileName.substring(0, fileName.indexOf('.'));
    }

    public static boolean isFileClass(File file) {
        return file.getName().endsWith(".class");
    }

//    public static List<String> sniffPackage(String packageName) {
//        var mappings = new ArrayList<String>();
//        var pkgFile = getResourceDirectory(TS_AnnotationSearch.class, packageName);
//        if (!pkgFile.exists()) {
//            return mappings;
//        }
//        Arrays.stream(pkgFile.listFiles()).forEach(file -> {
//            if (file.isDirectory()) {
//                var subPackage = packageName + "." + file.getName();
//                mappings.addAll(sniffPackage(subPackage));
//            } else if (isFileClass(file)) {
//                var className = packageName + '.' + getFileNameLabel(file);
//                mappings.addAll(sniffClass(className));
//            }
//        });
//        return mappings;
//    }
//
//    private static List<String> sniffClass(String className, List<Class> annotationsToFind) {
//        var values = new ArrayList<String>();
//        try {
//            var cls = Class.forName(className);
//            if (annotationsToFind.stream().anyMatch(a -> cls.isAnnotationPresent(a))) {
//                Annotation[] annotationArray = cls.getAnnotationsByType(RequestMapping.class);
//
//                if (annotationArray.length < 1) {
//                    values.addAll(scanMethods(cls, ""));
//                }
//                for (Annotation annotation : cls.getAnnotationsByType(RequestMapping.class)) {
//                    Class<? extends Annotation> type = annotation.annotationType();
//                    for (Method classMethod : type.getDeclaredMethods()) {
//                        if (classMethod.getName().equals("value")) {
//                            String[] valueArray = (String[]) classMethod.invoke(annotation, null);
//                            for (String rootPath : valueArray) {
//                                values.addAll(scanMethods(cls, rootPath));
//                            }
//                        }
//                    }
//                }
//            }
//        } catch (?Exception e) {
//            TGS_FuncUtils.throwIfInterruptedException(e);
//        }
//        return values;
//    }
//    
//    
//
//    private static List<String> scanMethods(Class cls, String rootPath) {
//        List<String> values = new ArrayList<>();
//        for (Method method : cls.getMethods()) {
//            RequestMapping mapping = method.getAnnotation(RequestMapping.class);
//
//            if (mapping != null) {
//                for (String mappingValue : mapping.value()) {
//                    for (RequestMethod requestMethod : mapping.method()) {
//                        String entry = cls.getCanonicalName() + "." + method.getName() + "(): " + rootPath + mappingValue + " " + requestMethod;
//                        values.add(entry);
//                    }
//                }
//            }
//        }
//
//        return values;
//    }
}
