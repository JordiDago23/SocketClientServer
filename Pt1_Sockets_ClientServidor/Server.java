package Pt1_Sockets_ClientServidor;
import java.net.*;
import java.io.*;

public class Server {
public static void main(String[] args) throws IOException{
		
		int port = 1234;
		String keywordServer = "cleopatra";
		String keywordClient = "";
		
		try {
			
			ServerSocket ss = new ServerSocket(port);  // crear el socket servidor con su puerto
			
			
			//prints de inicio de Sesion
			System.out.println(" ____________________________________________  \n"
					  		  +"| PORT_SERVIDOR: "+port+"                        | \n"
					  		  +"| PARAULA_CLAU_SERVIDOR: "+keywordServer+"           | \n"
					  		  +"|____________________________________________| \n");
			
			//prints de inicio de Sesion
			System.out.println("> Server chat at port " + port );
			System.out.println("\n> Inicializing server... OK");
			
			Socket s = ss.accept(); 
			System.out.println("\n> Connection from client... OK");
			System.out.println("\n> Inicializing chat... OK\n");
			
			//para obtener los mensajes del Cliente
			BufferedReader entrada = new BufferedReader(new InputStreamReader(s.getInputStream()));
			//para enviar los mensajes al Cliente
			PrintWriter salida = new PrintWriter(s.getOutputStream());
			//para obtener el texto des de los inputs del teclado
			BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
			
			//String para almacenar mensajes
			String mensaje;
			
			//obtener constraseña y enviar contraseña cliente
			salida.println(keywordServer);
			salida.flush();
			
			//obtener la palabra clave del Servidor
			keywordClient = entrada.readLine();
			
			//while para no cerar el Socker asta que salga 
			//la palabra clave sea mencionada en el chat
			while(true){
				
				mensaje = entrada.readLine();
				
				//Condicion para cerrar el servidor si el socker cliente se cierra
				if( mensaje == null || mensaje.toLowerCase().contains(keywordClient) ){
					System.out.println("\n> Client keyword detected!");
					break;
				}
				else if(mensaje.toLowerCase().contains(keywordServer)){
					System.out.println("\n> Server keyword detected!");
					break;
				}
				
				System.out.println("\n# Rebut del cliente: " + mensaje);
				
				
				//logica para enviar mensaje al Cliente
				System.out.print("\n# Enviar al Cliente: ");
				mensaje = teclado.readLine();
				salida.println(mensaje.trim());
				salida.flush();
				
				
				//Condicion para cerrar el servidor si el servidor
				//dice la palabra clave
				if(mensaje.toLowerCase().contains(keywordServer)){
					System.out.println("\n> Server keyword detected!");
					break;
				}
			}
						
			//prints de cierre de Sesion
			System.out.println("\n"+"> Closing chat... OK");
			System.out.println("\n> Closing server... OK");
			System.out.println("\n> Bye! \n");
			
			//cerra socker, outPuts y inPuts lectura/ecritura
			entrada.close();
			salida.close();
			teclado.close();
			ss.close(); 
			
		}catch( Exception e){
			System.out.println("Error inesperado : " + e);
		}
	}
}