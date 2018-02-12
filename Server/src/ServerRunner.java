import Rmi.RemoteHelper;

/**
 * Created by Jason on 2017/10/22.
 * Updated by Jason on 2017/11/20.
 */
public class ServerRunner {
    public ServerRunner(){
        new RemoteHelper();
    }

    public static void main(String[] args) {
        new ServerRunner();
    }
}
