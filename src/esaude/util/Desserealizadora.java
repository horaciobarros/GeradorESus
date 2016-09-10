package esaude.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;

import br.gov.saude.esus.cds.transport.generated.thrift.cadastrodomiciliar.CadastroDomiciliarThrift;

public class Desserealizadora {
	
	public static void main (String args[]) {
		
		//1 - Crie um objeto FileInputStream
		 FileInputStream fileStream;
		try {
			fileStream = new FileInputStream("d:/TEMP/serializado/cadastro_domiciliar_2612003-00156e1f-2ae8-4227-b9bf-8149a753caaa.txt");
			 //2 - Crie um ObjectInputStream
			 ObjectInputStream os = new ObjectInputStream(fileStream);
			 //3 - Leia os objetos
			 Object um = os.readObject();
			 //4 - Converta os objetos
			 CadastroDomiciliarThrift cd   = (CadastroDomiciliarThrift) um;
			 
			 //5 - Feche ObjectInputStream
			 os.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
