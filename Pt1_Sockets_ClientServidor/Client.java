package Pt1_Sockets_ClientServidor;
import java.net.*;
import java.io.*;

public class Client {


public static void main(String[] args) throws IOException{
		
		int port = 1234;
		String keywordClient = "tutancamon";
		String keywordServer = "";
		
		try {
			
			Socket s = new Socket("localhost", port); //crear el soquet con el puerto de acceso y el localhost
			Socket sc = null; 
			
			//prints de inicio de Sesion
			System.out.println(" ____________________________________________  \n"
							  +"| PORT_SERVIDOR: "+port+"                        | \n"
							  +"| PARAULA_CLAU_CLIENT: "+keywordClient+"            | \n"
							  +"|____________________________________________| \n");
			
			//prints de inicio de Sesion
			System.out.println("> Client chat to port " + port );
			System.out.println("\n> Inicializing client... OK");
			
			System.out.println("\n> Inicializing chat... OK\n");
			
			//logica para leer texto de el Servidor
			BufferedReader entrada = new BufferedReader(new InputStreamReader(s.getInputStream()));
			//logica para enviar el texto al servidor
			PrintWriter salida = new PrintWriter(s.getOutputStream());
			//logica para obtener los inputs del teclado y obtener el texto que escribes
			BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
			
			//obtener constraseña y enviar contraseña cliente
			salida.println(keywordClient);
			salida.flush();
			
			//obtener la palabra clave del Servidor
			keywordServer = entrada.readLine();
			
			//String para almacenar mensajes
			String mensaje; 
			
			//while para no cerar el Socker asta que salga 
			//la palabra clave sea mencionada en el chat
			while(true){
				
				System.out.print("\n# Enviar al Servidor: ");
				mensaje = teclado.readLine();
				salida.println(mensaje.trim());
				salida.flush();
				
				//Condicion para cerrar el servidor si el cliente
				//dice la palabra clave
				if(mensaje.toLowerCase().contains(keywordClient)){
					System.out.println("\n> Cient keyword detected!");
					break;
				}
				
				//mostrar la informacio que envia el servidor
				mensaje = entrada.readLine();
				
				//Condicion para cerrar el cliente si el Servidor se cierra
				if(mensaje == null || mensaje.toLowerCase().contains(keywordServer)){
					System.out.println("\n> Server keyword detected!");
					break;
				}
				else if(mensaje.toLowerCase().contains(keywordClient)){
					System.out.println("\n> Client keyword detected!");
					break;
				}
				
				System.out.println("\n# Rebut del servidor: " + mensaje);
				
			}
			
			//prints de cierre de Sesion
			System.out.println("\n> Closing chat... OK");
			System.out.println("\n> Closing client... OK");
			System.out.println("\n> Bye! \n");
			
			//cerra socker, outPuts y inPuts lectura/ecritura
			entrada.close();
			salida.close();
			teclado.close();
			s.close();
			
		}catch(Exception e){
			System.out.println("Error inesperado : " + e);
		}
	}
}