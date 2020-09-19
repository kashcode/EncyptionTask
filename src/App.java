import java.util.Scanner;

public class App {	
		
	private static final char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray(); //26
	private static String password = "";
	private static String data = "";
	private static char selectEncryptDecrypt;
	private static char[][] matrix = new char[alphabet.length][alphabet.length];	

	public static void main(String[] args) {		
		prepareMatrix();	
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter password:");
		password = scanner.nextLine().toUpperCase();
		if(!isAlpha(password)){
			System.out.println("Password can only contain letters!");
			return;
		} 
		
		System.out.println("Encrypt (E) or Decrypt (D): ");
		String select = scanner.nextLine();
		switch (select.toUpperCase()) {
		case "E":
				selectEncryptDecrypt = select.toUpperCase().charAt(0);
			break;
		case "D":
				selectEncryptDecrypt = select.toUpperCase().charAt(0);
			break;
		default:
			System.out.println("Invalid choice!");
			break;
		}
		
		System.out.println("Enter data:");
		data = scanner.nextLine().toUpperCase();
		if(!isAlpha(data)){
			System.out.println("Input data without numbers, spaces or any special char.");
			return;
		}
		
		System.out.println("DATA:\t" + data);
		System.out.println("Passw:\t" + generatePassw(data, password));
		System.out.print("\t");
		for (int i = 0; i < data.length(); i++) {
			System.out.print("-");
		}
		System.out.println("");
		
		switch (selectEncryptDecrypt) {
		case 'E':
			System.out.println("Result:\t" + encrypt(data, generatePassw(data, password)));
			break;
		case 'D':
			System.out.println("Result:\t" + decrypt(data, generatePassw(data, password)));
			break;		
		}
	}
	
	//Kodē datus
	private static String encrypt(String data, String passwd){
		StringBuilder result = new StringBuilder();
		int n=0;
		for (int i=0; i < data.length(); i++) {
			int dataChIdx = String.valueOf(alphabet).indexOf(data.toCharArray()[n]);
			int passChIdx = String.valueOf(alphabet).indexOf(passwd.toCharArray()[n]);
			result.append(matrix[dataChIdx][passChIdx]);
			n++;
		}
		return result.toString();
	}
	
	//Atkodē datus
	private static String decrypt(String data, String passwd){		
		StringBuilder result = new StringBuilder();
		int n=0;
		for (int i=0; i < passwd.length(); i++) {
			int colIdx = String.valueOf(alphabet).indexOf(passwd.toCharArray()[n]);
			int alphabetIdx = 0;
			for(int j = 0; j <matrix[colIdx].length; j++){
				char matrixCh = matrix[colIdx][j];
				char dataCh = data.toCharArray()[i];
				if(matrixCh == dataCh){
					result.append(alphabet[alphabetIdx]);
				}
				++alphabetIdx;
		    }
			n++;
		}
		return result.toString();
	}
	
	//Pārbauda vai tikai burti
	private static boolean isAlpha(String s){
		return s.matches("[a-zA-Z]+");
	}
	
	//Sagatavo matricu priekš kodēšanas
	private static void prepareMatrix(){
		int n = 1;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				if(n==alphabet.length){
					n=0;
				}				
				matrix[i][j] = alphabet[n];
				n++;
			}
			n = n + 1;
		}
	}
	
	//Atkārtojam paroli tik reizes cik nepieciešams lieko atmetam, ja pa daudz tad arī atmetam
	private static String generatePassw(String data, String password){
		StringBuilder passw = new StringBuilder();
		do {
			passw.append(password);
		} while (!(passw.length() > data.length()));
		passw.setLength(data.length());
		
		return passw.toString();
	}
}
