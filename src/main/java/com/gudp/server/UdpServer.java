package com.gudp.server;

import java.io.IOException;
import java.net.*;

public class UdpServer {

    private static byte[] buf = new byte[1024];  //定义byte数组
    private static DatagramSocket datagramSocket;
    private static DatagramPacket datagramPacket;

    /**
     * 创建DatagramSocket对象
     */
    public UdpServer(int port,InetAddress ip) throws UnknownHostException, SocketException {
        datagramSocket = new DatagramSocket(port,ip != null ? ip : InetAddress.getLocalHost());
        datagramPacket = new DatagramPacket(buf, buf.length);
    }

    /**
     * 通过套接字接收数据
     * @throws IOException
     */
    public void receive() throws IOException {
        datagramSocket.receive(datagramPacket);
        System.out.println(new String(datagramPacket.getData()));
    }

    /**
     * 封装返回给客户端的数据
     * @throws IOException
     */
    public void send(String data) throws IOException {
        DatagramPacket sendPacket = new DatagramPacket(data.getBytes(), data.length(), datagramPacket.getSocketAddress());
        datagramSocket.send(sendPacket);
    }

    public void close(){
        if (datagramSocket != null){
            datagramSocket.close();
        }
    }
    public static void main(String[] args) throws IOException {
        while (true){
            //创建DatagramSocket对象
            UdpServer socket = new UdpServer(9090, InetAddress.getLocalHost());
            socket.receive();  //通过套接字接收数据
            socket.send("hello 咸鱼！");  //通过套接字反馈服务器数据
            socket.close();  //关闭套接字
        }

    }
}
