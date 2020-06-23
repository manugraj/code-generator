package org.ibs.cds.gode.util;

import org.springframework.util.SocketUtils;

public class NetworkUtil {
    public static int nextFreePort() {
       return SocketUtils.findAvailableTcpPort();
    }
}
