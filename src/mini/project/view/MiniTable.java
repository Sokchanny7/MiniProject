package mini.project.view;

import java.util.Vector;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import mini.project.control.Option;
import mini.project.model.Product;

public class MiniTable {
	private static char LEFT_CONNER_DOWN; 
	private static char LEFT_CONNER_UP;
	private static char RIGHT_CONNER_DWON;
	private static char RIGHT_CONNER_UP;
	private static char STRAIGHTLINE;
	private static char VERTICALLINE1;
	private static char SPLIT_COLUMN_DOWN;
	private static char SPLIT_COLUMN_UP;
	private static char ROW_BREAK_LEFT;
	private static char ROW_BREAK_RIGHT;
	private static char CROSSLINE;
	
	private static char[]singleLine={
			'\u250C',
			'\u2514',
			'\u2510',
			'\u2518',
			'\u2500',
			'\u2502',
			'\u252C',
			'\u2534',
			'\u251C',
			'\u2524',
			'\u253C'
			};
	private static char[]splitLine={
			'\u250C',
			'\u2514',
			'\u2510',
			'\u2518',
			'\u2500',
			'|',
			'\u252C',
			'\u2534',
			'\u251C',
			'\u2524',
			'\u253C'
	};
	private static char[]doubleLine={
			'\u2554',
			'\u255A',
			'\u2557',
			'\u255D',
			'\u2550',
			'\u2551',
			'\u2566',
			'\u2569',
			'\u2560',
			'\u2563',
			'\u256C'
	};
	
		
	private static int theme;
	private static void init(int theme){
		MiniTable.theme=theme;
		
		if(theme==1){
			LEFT_CONNER_DOWN=singleLine[0]; 
			LEFT_CONNER_UP=singleLine[1];
			RIGHT_CONNER_DWON=singleLine[2];
			RIGHT_CONNER_UP=singleLine[3];
			STRAIGHTLINE=singleLine[4];
			VERTICALLINE1=singleLine[5];
			SPLIT_COLUMN_DOWN=singleLine[6];
			SPLIT_COLUMN_UP=singleLine[7];
			ROW_BREAK_LEFT=singleLine[8];
			ROW_BREAK_RIGHT=singleLine[9];
			CROSSLINE=singleLine[10];
		}else if(theme==2){
			LEFT_CONNER_DOWN=splitLine[0]; 
			LEFT_CONNER_UP=splitLine[1];
			RIGHT_CONNER_DWON=splitLine[2];
			RIGHT_CONNER_UP=splitLine[3];
			STRAIGHTLINE=splitLine[4];
			VERTICALLINE1=splitLine[5];
			SPLIT_COLUMN_DOWN=splitLine[6];
			SPLIT_COLUMN_UP=splitLine[7];
			ROW_BREAK_LEFT=splitLine[8];
			ROW_BREAK_RIGHT=splitLine[9];
			CROSSLINE=splitLine[10];
		}else{
			LEFT_CONNER_DOWN=doubleLine[0]; 
			LEFT_CONNER_UP=doubleLine[1];
			RIGHT_CONNER_DWON=doubleLine[2];
			RIGHT_CONNER_UP=doubleLine[3];
			STRAIGHTLINE=doubleLine[4];
			VERTICALLINE1=doubleLine[5];
			SPLIT_COLUMN_DOWN=doubleLine[6];
			SPLIT_COLUMN_UP=doubleLine[7];
			ROW_BREAK_LEFT=doubleLine[8];
			ROW_BREAK_RIGHT=doubleLine[9];
			CROSSLINE=doubleLine[10];
		}
	}

    private static final void printChar(int code) {
        System.out.printf("%c", getAscii(code));
    }

    private static final char getAscii(int code) {
        if (code >= 0x80 && code <= 0xFF) {
            return Option.EXTENDED[code - 0x7F];
        }
        return (char) code;
    }

    public static void tableHeader(String[] str) {
    	init(Option.THEME);
        for (int i = 0; i < 3; i++) {
            if (i == 0) {
                for (int j = 0; j <= Option.COL_LENGH * str.length; j++) {
                	if (j == Option.COL_LENGH * str.length) {                    	                        
                		printChar(RIGHT_CONNER_DWON);
                	}else if (j % Option.COL_LENGH == 0 && j != 0) {                        
                    	printChar(SPLIT_COLUMN_DOWN);
                    } else if (j == 0) {
                        printChar(LEFT_CONNER_DOWN);
                    }else {
                    		printChar(STRAIGHTLINE);
                    }                    
                }
                System.out.printf("%n");
            } else if (i == 1) {                
                printChar(VERTICALLINE1);               
                for (int k = 0; k < str.length; k++) {
                    System.out.print(alignCenter(str[k]));
                    printChar(VERTICALLINE1);
                }
                System.out.printf("%n");
            } else {
                for (int j = 0; j < Option.COL_LENGH * str.length + 1; j++) {
                	
                    if (j == 0) {                    	
                    	printChar(ROW_BREAK_LEFT);
                    }else if(j==Option.COL_LENGH*str.length){                    	
                        printChar(ROW_BREAK_RIGHT);
                    } else if (j % Option.COL_LENGH == 0) {
                        printChar(CROSSLINE);                             
                    } else if (j == Option.COL_LENGH * str.length + 1) {
                        printChar(CROSSLINE);                        
                    } else {
                        printChar(STRAIGHTLINE); 
                    }
                }
                System.out.printf("%n");
            }
        }
    }

    public static void tableBody(Vector<Product> products) {    	
        Product p;
        for (long index = ((Option.PAGE_NUMBER - 1) * Option.RECORD_NUMBER) + Option.RECORD_NUMBER - 1;
                index > ((Option.PAGE_NUMBER - 1) * Option.RECORD_NUMBER) - 1;
                index--) {        
            if (index >= Option.RECORD_SIZE) {
                index=Option.RECORD_SIZE;
                continue;
            }
            if (index < 0) {
                continue;
            }
           p = products.get((int) (index));
            if (p != null) {
                for (int i = 0; i < 2; i++) {
                    if (i == 0) {
                        printChar(VERTICALLINE1);
                        System.out.print(alignLeft(p.getId() + ""));                        
                        printChar(VERTICALLINE1);
                        System.out.print(alignLeft(p.getName()));                        
                        printChar(VERTICALLINE1);
                        System.out.print(alignRight(p.getUnitprice() + ""));
                        printChar(VERTICALLINE1);
                        System.out.print(alignRight(p.getStockquantity() + ""));
                        printChar(VERTICALLINE1);
                        System.out.print(alignCenter(p.getImporteddate()));
                        printChar(VERTICALLINE1);
                        System.out.printf("%n");
                    } else if(i==1){
                        for (int j = 0; j <= Option.COL_LENGH * 5; j++) {
                            if (j == 0) {
                            	printChar(ROW_BREAK_LEFT);
                            }else if (j == Option.COL_LENGH *5) {
                                printChar(ROW_BREAK_RIGHT);
                            }else if(j%Option.COL_LENGH==0){
                            	if(index!=1){
                            		printChar(CROSSLINE);
                            	}else{
                                	printChar(SPLIT_COLUMN_UP);
                            	}
                            } else {                            	                                                                                           
                                printChar(STRAIGHTLINE);                                
                            }
                        }
                        System.out.printf("%n");
                    }
                }
            }                    
        }
    }

    public static void tableFooter() {
        for (int i = 0; i < 2; i++) {
            if (i == 0) {
            	//printChar(VERTICALLINE1);
            	String str=("\t\tPage : " + Option.PAGE_NUMBER + "/" + Option.MAX_PAGE
                        + "\t\t\t\t\t\tTotal Record : " 
                		+ (Option.RECORD_SIZE));
            	System.out.print(alignLeftLong(str));
                //printChar(VERTICALLINE1);
                System.out.printf("%n");
            } else {
                
                for (int j = 0; j < Option.COL_LENGH * 5 + 1; j++) {
                    if (j == 0) {
                        printChar(LEFT_CONNER_UP);                        
                    } else if (j == Option.COL_LENGH * 5) {                        
                        System.out.print(RIGHT_CONNER_UP);
                    } else {                        
                        System.out.print(STRAIGHTLINE);
                    }
                }                
                System.out.printf("%n");
            }
        }
    }

    private static String alignLeft(String str1) {
    	String str;
    	if(str1.length()>17){
    		str=str1.substring(0,17);
    		str+="....";
    	}else{
    		str=str1;
    	}
        int minsize = (Option.COL_LENGH - str.length() - 1);
        for (int i = 0; i < minsize; i++) {
            str += " ";
        }
        return str;
    }

    private static String alignCenter(String str1) {
    	String str;
    	if(str1.length()>17){
    		str=str1.substring(0,17);
    		str+="....";
    	}else{
    		str=str1;
    	}
    	int left = (Option.COL_LENGH- str.length()) / 2;
	    int right = Option.COL_LENGH - left - str.length()-1;
	    String repeatedChar = " ";
	    StringBuilder buff = new StringBuilder();
	    for (int i = 0; i < left; i++) {
	        buff.append(repeatedChar);
	    }
	    buff.append(str);
	    for (int i = 0; i < right; i++) {
	        buff.append(repeatedChar);
	    }
	    return buff.toString();
    }
    private static String alignCenterLong(String str) {
    	int left = ((Option.COL_LENGH*5) - str.length()) / 2;
	    int right = (Option.COL_LENGH*5) - left - str.length()-1;
	    String repeatedChar = " ";
	    StringBuilder buff = new StringBuilder();
	    for (int i = 0; i < left; i++) {
	        buff.append(repeatedChar);
	    }
	    buff.append(str);
	    for (int i = 0; i < right; i++) {
	        buff.append(repeatedChar);
	    }
	    return buff.toString();
    }
    private static String alignLeftLong(String str) {
        int minsize = ((Option.COL_LENGH * 5) - str.length());
        
        String temp = "";
        for (int i = 0; i < (minsize/3)-1; i++) {            
            str += " ";
        }
        return str;
    }

    private static String alignRight(String str) {
        int minsize = (Option.COL_LENGH - str.length() - 1);
        String temp = "";
        for (int i = 0; i < minsize; i++) {
            temp += " ";
        }
        return temp + str;
    }

    public static void box(String[] menu) {
        for (int j = 0; j < Option.COL_LENGH * 5; j++) {
            if (j == 0) {               
                System.out.print("+");
            } else if (j == Option.COL_LENGH * 5) {                
                System.out.print("+");
            } else {                
                System.out.print("-");
            }
        }
        System.out.printf("%n");
        for (int i = 0; i < menu.length; i++) {
            if (i % 5 == 0 && i != 0) {
                System.out.println("\n");
            }
            System.out.print(alignLeft(menu[i]));
        }
        System.out.printf("%n");

        for (int j = 0; j < Option.COL_LENGH * 5; j++) {
            if (j == 0) {                
                System.out.print("+");
            } else if (j == Option.COL_LENGH * 5) {                
                System.out.print("+");
            } else {                
                System.out.print("-");
            }
        }
    }      
}
