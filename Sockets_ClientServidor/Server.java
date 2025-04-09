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
			
			Socket s = ss.accept();// añadir al socket Cliente
			
			System.out.println("\n> Connection from client... OK");
			System.out.println("\n> Inicializing chat... OK\n");
			
			//para obtener los mensajes del Cliente
			BufferedReader entrada = new BufferedReader(new InputStreamReader(s.getInputStream()));
			//para enviar los mensajes al Cliente
			PrintWriter salida = new PrintWriter(s.getOutputStream());
			//para obtener el texto des de los inputs del teclado
			BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
			
			
			
			//obtener constraseña y enviar contraseña cliente
			salida.println(keywordServer);
			salida.flush();
			
			//obtener la palabra clave del Servidor
			keywordClient = entrada.readLine();
			
			
			//String para almacenar mensajes
			String mensaje;
			boolean activo = true;
			
			//while para no cerar el Socker asta que salga 
			//la palabra clave sea mencionada en el chat
			while( activo ){
				
				mensaje = entrada.readLine();
				
				if( mensaje == null) {
					System.out.println("\n> Client keyword detected!");
					activo = false;
					continue;
				}
				
				System.out.println("\n# Rebut del cliente: " + mensaje);
				
				//Condicion para cerrar el servidor si el socker cliente se cierra
				if( mensaje.toLowerCase().contains(keywordClient) ){
					System.out.println("\n> Client keyword detected!");
					activo = false;
					continue;
				}
				else if( mensaje.toLowerCase().contains(keywordServer) ){
					System.out.println("\n> Server keyword detected!");
					activo = false;
					continue;
				}
				
				//logica para enviar mensaje al Cliente
				System.out.print("\n# Enviar al Cliente: ");
				mensaje = teclado.readLine();
				salida.println(mensaje.trim());
				salida.flush();
				
				
				//Condicion para cerrar el servidor si el servidor
				//dice la palabra clave
				if(mensaje.toLowerCase().contains(keywordServer)){
					System.out.println("\n> Server keyword detected!");
					activo = false;
				}
			}
						
			//prints de cierre de Sesion
			System.out.println("\n> Closing chat... OK");
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