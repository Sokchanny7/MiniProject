package mini.project.view;

public class Help {
	private static final char LEFT_CONNER_DOWN='\u2554';//╔ 
	private static final char LEFT_CONNER_UP='\u255A';//╚
	private static final char RIGHT_CONNER_DWON='\u2557';//╗
	private static final char RIGHT_CONNER_UP='\u255D';//╝
	private static final char STRAIGHTLINE='\u2550';//═
	private static final char VERTICALLINE1='\u2551';// ║
	private static final char VERTICALLINE2='|';//|
	
	private StringBuilder menu=new StringBuilder();
	private static final int menuSize=89;
	public Help() {		
		menu.append(LEFT_CONNER_DOWN);
		duplicated(STRAIGHTLINE);
		menu.append(RIGHT_CONNER_DWON).append("\n");		
		menu.append("      I- EXPRESSION");				
		menu.append("      1-W#[NAME-PRICE-STOCK-DATE] -> Search sort cut.");
		menu.append("\n");		
		menu.append("      2-R#[ID] -> View.");
		menu.append("\n");	
		menu.append("      3-D#[ID] -> Delet product.");		
		menu.append("\n");
		menu.append("      4-Se#[NUMBER] -> Set view record number.");
		menu.append("\n");
		menu.append("      5-S#[SOME TEXT] -> Search.");		
		menu.append("\n");
		menu.append("      6-U#[ID-NAME-PRICE-STOCK-DATE] -> Update.");		
		menu.append("\n");							
		menu.append(LEFT_CONNER_UP);
		duplicated(STRAIGHTLINE);
		menu.append(RIGHT_CONNER_UP+"\n");
		System.out.println(menu.toString());
	}
	
	private void duplicated(Object obj){
		for(int i=0;i<menuSize;i++)
			menu.append(obj);
	}
}
