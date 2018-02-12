package Runner;

import Rmi.RemoteHelper;

public class ServerRunner {
    public ServerRunner() {
        new RemoteHelper();
        System.out.println("服务器已打开");
    }

    public static void main(String[] args){
        new ServerRunner();
    }


}
