package DoodleJump.GameLogic;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.fazecast.jSerialComm.SerialPort;

public class ArduinoListener {

    private static Serial serial = new Serial();
    private static MyRunnable RUN = new MyRunnable(serial);
    private static Thread thread = new Thread(RUN);
    public static int Xspeed = 0;
    public static int portNumber = 5;
    public static Boolean Status = true;
    public static Boolean running = false;

    public static void start() {

        // if(Serial.commPorts.length > 0)
         try{
            Serial.OpenPort(portNumber);
            running = true;
            thread.start();
         }
         catch(java.lang.ArrayIndexOutOfBoundsException ex){
            System.out.println("port not found");
            running = false;
            Status = false;
         }
            // initialize serial port communication
        // start the thread

    }
}

class MyRunnable implements Runnable {
    private Serial serial;
    private boolean interrupt = false;

    public MyRunnable(Serial ser) {
        this.serial = ser;
    }


    @Override
    public void run() {
        // Code to be executed in the thread
        System.out.println("Thread is running");
        while (true) {
            try {
                if(ArduinoListener.running == true){
                    int x = serial.listenToPort(ArduinoListener.portNumber) - 512;
                
                if(x > 600 || x < -600){
                    x = 0;
                }
                System.out.println(x);
                if (x > 50){
                    ArduinoListener.Xspeed = (x/50);
                } else if (x < -50){
                    ArduinoListener.Xspeed = (x/50);
                } else {
                    ArduinoListener.Xspeed = 0;
                }
                }
                

            } catch (IOException ex) {
                Logger.getLogger(MyRunnable.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(MyRunnable.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (interrupt) { // break the loop to stop the thread
                break;
            }
        }
    }

    public void stop(boolean i) { // Use this method to stop the thread
        interrupt = i;
    }
}



class Serial {

    public static SerialPort[] commPorts = SerialPort.getCommPorts();
    private String descriptivePortName[] = new String[commPorts.length];
    private String systemPortName[] = new String[commPorts.length];
    private int PortBaudRate[] = new int[commPorts.length];

    public static boolean OpenPort(int NO) {
        boolean CASE = commPorts[NO].openPort();
        commPorts[NO].setComPortParameters(9600, 8, 1, 0);
        commPorts[NO].setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);
        // Ensure the port is open
        if (CASE == true) {
            System.out.println("Port is open :)");
        } else {
            System.out.println("Failed to open port :(");
        }
        return CASE;
    }

    public int getAvailablePorts() {
        System.out.println("Available Ports are: ");

        for (int i = 0; i < commPorts.length; i++) {
            descriptivePortName[i] = commPorts[i].getDescriptivePortName();
            systemPortName[i] = commPorts[i].getSystemPortName();
            PortBaudRate[i] = commPorts[i].getBaudRate();
        }

        for (int i = 0; i < commPorts.length; i++) {
            System.out.print("descriptivePortName[" + i + "]     ");
            System.out.println(descriptivePortName[i]);
            System.out.print("systemPortName[" + i + "]     ");
            System.out.println(systemPortName[i]);
            System.out.print("BaudRate[" + i + "]     ");
            System.out.println(commPorts[i].getBaudRate());
            System.out.println("");
        }

        if (commPorts.length == 0) {
            System.out.println("No Ports Found :(");
            return 0;
        } else {
            return 1;
        }

    }

    public void writeToPort(int portNO, byte value) throws IOException, InterruptedException {
        System.out.println(value);
        commPorts[portNO].getOutputStream().write(value);   // send data to serial
        commPorts[portNO].getOutputStream().flush(); // wait until the serial buffer is empty to ensure completion of sending data
        System.out.println("Sent number: " + value);
    }

    public int listenToPort(int portNO) throws IOException, InterruptedException {
        while (commPorts[portNO].bytesAvailable() < 4) {
        }
        byte[] readBuffer = new byte[commPorts[portNO].bytesAvailable()];   // Create Array with size of available bytes
        int numBytes = commPorts[portNO].readBytes(readBuffer, readBuffer.length);  // Store the bytes in the array "readBuffer" and return number of bytes
        String receivedData = new String(readBuffer, 0, numBytes);  // Store the received bytes to string
        
        int val = 0;
        Scanner input = new Scanner(receivedData);
        if (input.hasNextLine()) {
            val = input.nextInt();
        }
        input.close();
        return val;
    }
}