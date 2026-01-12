package com.tugalsan.java.core.jetty.embedded.server;

import java.nio.file.Path;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.component.LifeCycle;
import org.eclipse.jetty.webapp.WebAppContext;

public class ServerMe {

    public static void main(String[] args) throws Exception {
        var webResourceBase = Path.of("C:\\me\\codes\\com.tugalsan\\tst\\hello-world-embedded-jetty-maven\\static");
        var port = 8080;
        System.err.println("Using BaseResource: " + webResourceBase);

        var server = new Server(port);
        var handler = new WebAppContext(webResourceBase.toAbsolutePath().toString(), "/");
        handler.setParentLoaderPriority(true);
        server.setHandler(handler);
        server.addEventListener(new LifeCycle.Listener() {
            @Override
            public void lifeCycleStarted(LifeCycle lifeCycle) {
                System.out.println("Server %s has been started".formatted(lifeCycle));
            }

            @Override
            public void lifeCycleFailure(LifeCycle lifeCycle, Throwable failure) {
                System.out.println("Server %s has been failed".formatted(lifeCycle));
            }

            @Override
            public void lifeCycleStopped(LifeCycle lifeCycle) {
                System.out.println("Server %s has been stopped".formatted(lifeCycle));
            }
        });
        server.start();
        server.join();
    }

//        URI webResourceBase = findWebResourceBase(server.getClass().getClassLoader());
//    private static URI findWebResourceBase(ClassLoader classLoader) {
//        String webResourceRef = "WEB-INF/web.xml";
//
//        try {
//            // Look for resource in classpath (best choice when working with archive jar/war file)
//            URL webXml = classLoader.getResource('/' + webResourceRef);
//            if (webXml != null) {
//                URI uri = webXml.toURI().resolve("..").normalize();
//                System.err.printf("WebResourceBase (Using ClassLoader reference) %s%n", uri);
//                return uri;
//            }
//        } catch (URISyntaxException e) {
//            throw new RuntimeException("Bad ClassPath reference for: " + webResourceRef, e);
//        }
//
//        // Look for resource in common file system paths
//        try {
//            Path pwd = new File(System.getProperty("user.dir")).toPath().toRealPath();
//            FileSystem fs = pwd.getFileSystem();
//
//            // Try the generated maven path first
//            PathMatcher matcher = fs.getPathMatcher("glob:**/embedded-servlet-*");
//            try (DirectoryStream<Path> dir = Files.newDirectoryStream(pwd.resolve("target"))) {
//                for (Path path : dir) {
//                    if (Files.isDirectory(path) && matcher.matches(path)) {
//                        // Found a potential directory
//                        Path possible = path.resolve(webResourceRef);
//                        // Does it have what we need?
//                        if (Files.exists(possible)) {
//                            URI uri = path.toUri();
//                            System.err.printf("WebResourceBase (Using discovered /target/ Path) %s%n", uri);
//                            return uri;
//                        }
//                    }
//                }
//            }
//
//            // Try the source path next
//            Path srcWebapp = pwd.resolve("src/main/webapp/" + webResourceRef);
//            if (Files.exists(srcWebapp)) {
//                URI uri = srcWebapp.getParent().toUri();
//                System.err.printf("WebResourceBase (Using /src/main/webapp/ Path) %s%n", uri);
//                return uri;
//            }
//        } catch (Throwable t) {
//            throw new RuntimeException("Unable to find web resource in file system: " + webResourceRef, t);
//        }
//
//        throw new RuntimeException("Unable to find web resource ref: " + webResourceRef);
//    }
}
