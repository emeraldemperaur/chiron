package iot.empiaurhouse.chiron.model;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PingObject {

    private String chironStatus;
    private String signature;
    private String localhost;

    public PingObject(){
        String pattern = "E, dd MMM yyyy zzzz";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        this.signature = simpleDateFormat.format(new Date());
        this.chironStatus = "online";
        this.localhost = getLocalhost();

    }

    public String getStatus(){
        return chironStatus;
    }

    public String getSignature(){
        return signature;
    }

    public String getLocalhost(){
        try{
            String hostName = InetAddress.getLocalHost().getHostName();
            String hostAddress = InetAddress.getLocalHost().getHostAddress();
            localhost = hostName + "@" + hostAddress;
            System.out.println("\nCompleted Chiron API status request on " + localhost + "\n");
        }catch (Exception e){
            System.out.println("Exception caught ="+e.getMessage());
        }
        return localhost;
    }


}
