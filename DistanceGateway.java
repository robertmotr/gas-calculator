package sample;

import py4j.GatewayServer;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class DistanceGateway {

    public static Distance distance;

    public DistanceGateway(String origin, String destination) {

        distance = new Distance();
        distance.push(origin);
        distance.push(destination);

    }

    public static Distance getDistance() { return distance;}

    public static class filePath {

        String file_path;

        public void setPath(String file) {

            ClassLoader classLoader = DistanceGateway.class.getClassLoader();
            String path = classLoader.getResource(file).getPath();

            file_path = path;

        }

    }

    public Distance executeDistance(String origin, String destination) throws IOException {

        GatewayServer gatewayServer = new GatewayServer(new DistanceGateway(origin, destination), 19992);
        gatewayServer.start();

        filePath distancePath = new filePath();
        distancePath.setPath("distance-implementation.py");

        Desktop desktop = Desktop.getDesktop();
        desktop.open(new File(distancePath.file_path));

        Distance distanceStack = getDistance();
        distanceStack.getDistanceList();

        gatewayServer.shutdown();

        return distanceStack;

    }

}
