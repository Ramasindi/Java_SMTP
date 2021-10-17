package acsse.csc2b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Thalukanyo
 * @version P03
 */
public class Email{
	static PrintWriter printWriter = null;
	static BufferedReader bufferedReader = null;
	static Socket smtpSocket = null;
	/**
	 * Default constructor
	 */
	public Email() {
		
	}
	
	/**
	 * Read response
	 * @param bufferedReader is for reading the response
	 * @return it returns the read message
	 * */
	private static String response(BufferedReader bufferedReader)
	{
		String responseMessage = "";
		try
		{
			responseMessage = bufferedReader.readLine();
		}catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
		System.out.println(responseMessage);
		return responseMessage;
	}
	
	/**
	 * Write message
	 * @param out is the output of the print writer
	 * @param message is the message to be written by the print writer
	 * */
	private static void writeMessage(PrintWriter out, String message)
	{
		out.println(message);
		out.flush();
	}
	/**
	 * Establish connection
	 * @param portNumber is the port number for the smtp protocol
	 * @param host is the host to connect to
	 * @return will return connection status
	 * */
	public static String establishConnection(String host,int portNumber)
	{
		
		String Connection = "Not Connected";
		try{
			smtpSocket = new Socket(host,portNumber);
			Connection = "Connected";

		}catch(IOException e){
			Connection = "Connection Refused, Check your input";
			
			//e.printStackTrace();
		}
		return Connection;
	}
	/**
	 * Add email details
	 * @param sender is the sender name
	 * @param recipient is the recipient email
	 * @param message is the mail data
	 * @param subject is the mail subject
	 * @return will return message status
	 * */
	public static String addDetails(String sender, String recipient, String message, String subject)
	{
		String status = "Not Sent";
		try
		{
			printWriter = new PrintWriter(smtpSocket.getOutputStream());
			bufferedReader = new BufferedReader(new InputStreamReader(smtpSocket.getInputStream()));
		
			response(bufferedReader);
			writeMessage(printWriter,"HELO PaperCut");
			response(bufferedReader);
		
			writeMessage(printWriter,"MAIL FROM: <"+sender+">");
			response(bufferedReader);
			writeMessage(printWriter,"RCPT TO: <"+recipient+">");
			response(bufferedReader);
			writeMessage(printWriter,"DATA");
			response(bufferedReader);
			writeMessage(printWriter,"FROM: <"+sender+">");		
			writeMessage(printWriter,"TO: <"+recipient+">");		
			writeMessage(printWriter,"Subject: " + subject );		
			writeMessage(printWriter,"");		
			writeMessage(printWriter,message);
			writeMessage(printWriter, ".");
			response(bufferedReader);
			writeMessage(printWriter,"QUIT");
			response(bufferedReader);
			status = "Sent Successfully";
		}catch(IOException e) {
			status = "Not sent";
		}
		return status;
	}
}
