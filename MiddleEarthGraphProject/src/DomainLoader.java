import java.io.File;
import java.util.Scanner;

public class DomainLoader {
	
	public DomainLoader(){
		
		//
	}
	
	public void loadDomain(Graph domain){
		
		loadNodes(domain, "src/MiddleEarthNodes.txt");
		
		loadConnections(domain, "src/MiddleEarthConnections.txt");
		
		loadDescriptions(domain, "src/MiddleEarthDescriptions.txt");

	}
	
	private void loadDescriptions(Graph domain, String filePath) {
		
		File domainFile = new File(filePath);
		
		try {
			
			Scanner scanner = new Scanner(domainFile);
			
			while (scanner.hasNext()){
				
				String name = scanner.next();
				String description = scanner.next();
				
				name = removeUnderscore(name);
				description = newLine(description);
				description = removeUnderscore(description);
				
				domain.insertDescription(name, description);
			}
		}
		
		catch (Exception e){
			
			System.out.println("ERROR: Could not load domain nodes");
		}
		
		
	}

	public void loadNodes(Graph domain, String filePath){
		
		File domainFile = new File(filePath);
		
		try {
			
			Scanner scanner = new Scanner(domainFile);
			
			while (scanner.hasNext()){
				
				String name = scanner.next();
				int xCoor = scanner.nextInt();
				int yCoor = scanner.nextInt();
				
				name = removeUnderscore(name);
				
				domain.insert(name, xCoor, yCoor);
			}
		}
		
		catch (Exception e){
			
			System.out.println("ERROR: Could not load domain nodes");
		}
		
	}
	
	public void loadConnections(Graph domain, String filePath){
		
		File domainFile = new File(filePath);
		
		try {
			
			Scanner scanner = new Scanner(domainFile);
			
			while (scanner.hasNext()){
				
				String name1 = scanner.next();
				String name2 = scanner.next();
				double terrainDifficulty = scanner.nextDouble();
				
				name1 = removeUnderscore(name1);
				name2 = removeUnderscore(name2);
				
				domain.connect(name1, name2, terrainDifficulty);
			}
		}
		
		catch (Exception e){
			
			System.out.println("ERROR: Could not load domain connections");
		}
	}
	
	
	
	public String removeUnderscore(String s){
		
		String output = "";
		
		for (int i = 0; i < s.length(); i++){
			
			char c = s.charAt(i);
			
			if (c != '_'){
				
				output += c;
			}
			
			else {
				
				output += " ";
			}
		}
		
		return (output);
	}
	
	private String newLine(String s) {
		String output = "";
		int count = 0;
		int lastSpaceIndex = 0;
		int i = 0;
		
		while(i < s.length()){
			char c = s.charAt(i);
			
			count++;
			
			if (c == '_'){
				lastSpaceIndex = i;
			}
			
			if(count == 35){
				if(c == '_'){
					output += "<br>";
				}
				else{
					while(i!=lastSpaceIndex){
						output = output.substring(0, output.length()-1);
						i--;
					}
					c = s.charAt(i);
					output += "<br>";
				}
				count = 0;
			}
			i++;
			output+=c;
			
		}
		
		return (output);
	}

}
