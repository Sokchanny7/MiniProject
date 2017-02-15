/*
this class control all task ot program
 */
package mini.project.control;

import manipulate.DAO;
import mini.project.model.FileOption;
import mini.project.model.RetrieveProduct;
import mini.project.model.SaveProduct;
import mini.project.view.Help;
import share.ConnectionManager;

public class Control extends Thread {

    private boolean useExp = false;
    public Control() {
        /**
         * All initialize
         */
        Pagination.setProducts(Option.products);
        ViewProduct.setProducts(Option.products);
        FileOption.recover();
    	ConnectionManager.open();
    }

    @Override
    public void run() {
        /**
         * It is a thread of importing product
         */
    	System.out.println("not yet have record in your database?");
    	System.out.println("Press key\n\t1) to import record into your database table.\n\t2) to load record and start application.");
    	switch(ReadKey.readInt()){
    	case 1:System.out.println("how many record do you want?");
    			DAO.inserts(ReadKey.readInt());
    			RetrieveProduct.fetch();
    			break;
    	case 2:RetrieveProduct.fetch();break;
    	}
        // SaveProduct.Export();
        // RetrieveProduct.fetch();

        /**
         * Check for temporary file
         */
        this.checkTemporaryData();

        /**
         * Checking for option file , clear screen and start program
         */
        ReadKey.clearConsole();
        startUp();
    }

    private void startUp() {

        Expression ex = null;
        while (true) {
            /**
             * You user try to use expression
             *
             * USE EXP eventhought it is false before perform tasks
             */
            useExp = false;
            String option = Menu.mainMenu();
            if (option.length() > 2) {
                ex = new Expression(option);
                if ((this.useExp = ex.setOption())) {
                    if (ex.isMatchdata()) {
                        option = ex.getOption();
                        /**
                         * There is expression
                         */
                    } else {
                        this.useExp = false;
                        System.out.println("Expression is wrong. Goto help.");
                    }
                }
            }
            /**
             * End expression block
             */
            switch (option.toLowerCase()) {
                case "*":
                    /*
                    Call view function which know as display()
                     */
                    if (ViewProduct.getProducts() != Option.products) {
                        ViewProduct.setProducts(Option.products);               
                    }
                    ViewProduct.displayProductList();
                    break;
                case "w":
                    /*
                    Option of isert new Product into Product list
                     */
                    if (useExp) {
                        InsertProduct.insert(ex.getData());
                        break;
                    }
                    InsertProduct.insert();
                    break;
                case "r":
                    /*
                    Show specific record by searching id
                     */
                    if (useExp) {
                        ViewProduct.view(ex.getData());
                        break;
                    }
                    ViewProduct.view();
                    break;
                case "u":
                    if (useExp) {
                        UpdateProduct.update(ex.getData());
                        break;
                    }
                    UpdateProduct.update();
                    break;
                case "d":
                    if (useExp) {
                        DeletePrduct.delete(Integer.parseInt((String) ex.getData().get(0)));
                        break;
                    }
                    DeletePrduct.delete();
                    break;
                case "s":
                    /**
                     * Search all product by its details
                     */
                    if (useExp) {
                        SearchProduct.search((String) ex.getData().get(0));
                        ViewProduct.displayProductList();
                        break;
                    }                   
                    SearchProduct.search();
                    ViewProduct.displayProductList();
                    break;
                case "b":
                    SaveProduct.backUp();
                    break;
                case "re":
                    /**
                     *
                     */
                    RetrieveProduct.restore();
                    break;
                case "l":
                    Pagination.lastPage();
                    break;
                case "f":
                    Pagination.firstPage();
                    break;
                case "p":
                    Pagination.previousPage();
                    break;
                case "n":
                    Pagination.nextPage();
                    break;
                case "se":
                    if (useExp) {
                        if (Pagination.setRecrdnumber(Integer.parseInt((String)ex.getData().get(0)))) {
                            break;
                        }
                    }
                    Pagination.setRecrdnumber();
                    break;
                case "h" :
                	new Help();
                	break;
                case "sa":
                    SaveProduct.saveOverride();
                    ChekTemporaryProduct.clear();
                    break;
                case "im":
                    //new ImportProduct(products);
                    //ReadKey.load("Importing");
                    break;

                case "g":
                    if (useExp) {
                        if (Pagination.gotoPage((int) ex.getData().get(0))) {
                            break;
                        }
                    }
                    Pagination.gotoPage();
                    break;
                case "e":               
                    exitProgram();
                    break;
                case "c":                	                	
                		System.out.print("Press\n1-> Theme sigle line\n2-> Theme Split line\n3-> Doule line\n Input (1-3)> ");
                		int key=ReadKey.readInt();
                		if(key>0&&key<4){                			
                    		Option.THEME=key;                    		
                		}
                		break;
                	  
                default:
                    System.out.println("Unavaliable Option");
                    break;
            }
        }
    }

    private void checkTemporaryData() {
        if (ChekTemporaryProduct.isHastemporaydata()) {
            ReadKey.clearConsole();
            String message = "Unsave record is found.\n> " + ChekTemporaryProduct.getRecord() + " records."
                    + "\nFrom your previouse work."
                    + "\n\n Do you want to recover it? [y/n] > ";
            if (ReadKey.information(message)) {
                ChekTemporaryProduct.reCoverdata();
            } else {
                /**
                 * whatever it will destroy automatically
                 */
                ChekTemporaryProduct.clear();
            }
        }
    }

    private void exitProgram() {
        if (ReadKey.information("KPS PRODUCT MANAGERMENT SYSTEM IS CLOSING ? [y/n] > ", "", "")) {
            FileOption.save();
            if (!ChekTemporaryProduct.isHastemporaydata()) {
                System.exit(0);
            } else if (ReadKey.information("YOU HAVE UNSAVE RECORD!\n>" + ChekTemporaryProduct.getRecord() + " record." + " press [ y (save and exit)/ n (exit without save) ] > ",
                    "SAVED", "EXIT")) {
                SaveProduct.saveOverride();
                ChekTemporaryProduct.clear();
                System.exit(0);
            } else {
                ChekTemporaryProduct.clear();
                System.exit(0);
            }
        }
    }
}
