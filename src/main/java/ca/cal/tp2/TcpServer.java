package ca.cal.tp2;

import org.h2.tools.Server;
import java.sql.SQLException;

public class TcpServer {

    public static void demarrerTcpServer() throws SQLException {
        final Server tcpServer = Server.createTcpServer(
                "-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
        tcpServer.start();
    }

}
