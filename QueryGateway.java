package sample;

import py4j.GatewayServer;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class QueryGateway {

    public static Query query;

    public QueryGateway(String arguments) {

        query = new Query();
        query.push(arguments);

    }

    public static class filePath {

        String file_path;

        public void setPath(String file) {

            ClassLoader classLoader = QueryGateway.class.getClassLoader();
            String path = classLoader.getResource(file).getPath();

            file_path = path;

        }
    }

    public static Query getQuery() { return query; }

    public Query executeQuery(String arguments) throws IOException {

        GatewayServer queryGateway = new GatewayServer(new QueryGateway(arguments), 19993);
        queryGateway.start();

        filePath query = new filePath();
        query.setPath("query-implementation.py");

        Desktop desktop = Desktop.getDesktop();
        desktop.open(new File(query.file_path));

        Query queryStack = getQuery();
        queryStack.getQueryList();

        queryGateway.shutdown();

        return queryStack;

    }

    public static void main(String[] args) throws IOException {
        // dont use for now
        GatewayServer gatewayServer = new GatewayServer(new QueryGateway("abc"), 19993);
        gatewayServer.start();
        System.out.println("Started query gateway server on JVM side.");

        filePath query = new filePath();
        query.setPath("query-implementation.py");

        Desktop desktop = Desktop.getDesktop();
        desktop.open(new File(query.file_path));


    }
}
