package ca.cal.tp2;

import org.h2.tools.Server;
import java.sql.SQLException;

public class TcpServer {
    private static boolean serveurEnMarche = false;

    public static void demarrerTcpServer() throws SQLException {
        if (serveurEnMarche) {
            return;
        }

        serveurEnMarche = true;
        final Server tcpServer = Server.createTcpServer(
                "-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
        tcpServer.start();
    }

}
