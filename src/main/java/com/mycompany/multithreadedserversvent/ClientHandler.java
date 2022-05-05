/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.multithreadedserversvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Studente
 */
class ClientHandler implements Runnable {
        private final Socket clientSocket;
  
        // Constructor
        public ClientHandler(Socket socket)
        {
            this.clientSocket = socket;
        }
  
        public void run()
        {
            PrintWriter out = null;
            BufferedReader in = null;
            try {
                    
                  // get the outputstream of client
                out = new PrintWriter(
                    clientSocket.getOutputStream(), true);
  
                  // get the inputstream of client
                in = new BufferedReader(
                    new InputStreamReader(
                        clientSocket.getInputStream()));
  
                String line;
                while ((line = in.readLine()) != null) {
  
                    // writing the received message from
                    // client
                    System.out.printf(
                        " Sent from the client: %s\n",
                        line);
                    out.println(line);
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                    if (in != null) {
                        in.close();
                        clientSocket.close();
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

