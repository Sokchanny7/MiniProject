package mini.project.view;

public class Box {
	/*
	private static final char LEFT_CONNER_DOWN='\u2554';//╔ 
	private static final char LEFT_CONNER_UP='\u255A';//╚
	private static final char RIGHT_CONNER_DWON='\u2557';//╗
	private static final char RIGHT_CONNER_UP='\u255D';//╝
	private static final char STRAIGHTLINE='\u2550';//═
	private static final char VERTICALLINE1='\u2551';// ║
	private static final char VERTICALLINE2='|';//|
	*/	
	
		private static char LEFT_CONNER_DOWN;
		private static char LEFT_CONNER_UP;
		private static char RIGHT_CONNER_DWON;
		private static char RIGHT_CONNER_UP;
		private static char STRAIGHTLINE;
		private static char VERTICALLINE1;
		private static char VERTICALLINE2;
		
		private char[]singleLine={
				'\u250C',
				'\u2514',
				'\u2510',
				'\u2518',
				'\u2500',
				'\u2502'
		};
		private char[]doubleLine={
				'\u2554',
				'\u255A',
				'\u2557',
				'\u255D',
				'\u2550',
				'\u2551'
		};
		
		private StringBuilder menu=new StringBuilder();
		private final int menuSize=89;
		public Box(int theme) {
			
			if(theme==1){
				 LEFT_CONNER_DOWN=singleLine[0];
				 LEFT_CONNER_UP=singleLine[1];
				 RIGHT_CONNER_DWON=singleLine[2];
				 RIGHT_CONNER_UP=singleLine[3];
				 STRAIGHTLINE=singleLine[4];
				 VERTICALLINE1=singleLine[5];
			}
			else if(theme==2){
				LEFT_CONNER_DOWN=doubleLine[0];
				 LEFT_CONNER_UP=doubleLine[1];
				 RIGHT_CONNER_DWON=doubleLine[2];
				 RIGHT_CONNER_UP=doubleLine[3];
				 STRAIGHTLINE='\u2261';
				 VERTICALLINE1=doubleLine[5];
				
			}else{
				 LEFT_CONNER_DOWN=doubleLine[0];
				 LEFT_CONNER_UP=doubleLine[1];
				 RIGHT_CONNER_DWON=doubleLine[2];
				 RIGHT_CONNER_UP=doubleLine[3];
				 STRAIGHTLINE=doubleLine[4];
				 VERTICALLINE1=doubleLine[5];
			}
			menu.append(LEFT_CONNER_DOWN);
			duplicated(STRAIGHTLINE);
			menu.append(RIGHT_CONNER_DWON).append("\n");
			menu.append(VERTICALLINE1);
			menu.append("   *)Display | W)rite | R)ead | U)pdate | D)elete | F)irst | P)revious | N)ext | L)ast");
			menu.append("	  ").append(VERTICALLINE1).append("\n");
			menu.append(VERTICALLINE1);
			duplicated(" ");
			menu.append(VERTICALLINE1+"\n");
			menu.append(VERTICALLINE1);
			menu.append(" S)earch | G)o to | Se)t row | Sa)ve | B)ackup | Re)store C)hange Theme | H)elp | E)xit");
			menu.append("  "+VERTICALLINE1).append("\n");
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
