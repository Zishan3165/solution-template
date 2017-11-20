package com.tigerit.exam;

import static com.tigerit.exam.IO.*;


/**
 * All of your application logic should be placed inside this class.
 * Remember we will load your application from our custom container.
 * You may add private method inside this class but, make sure your
 * application's execution points start from inside run method.
 */
public class Solution implements Runnable
{

    @Override
    public void run()
    {

       TestCase [] myTestcases;
        Table[] tableArray;
        Query[] Queries;
        Boolean error = false;
        int totalQueries = 0;
        myTestcases=new TestCase[0];

        int testCaseNumber = readLineAsInteger();
        if (testCaseNumber < 1 || testCaseNumber > 10)
        {
            System.out.println("Error! TestCase number not in range!");
            error = true;

        }
        else
        {

            myTestcases=new TestCase[testCaseNumber];
        }


        if (error != true)
        {


            for (int k = 0; k < testCaseNumber; k++)
            {
                  error=false;
                Queries = new Query[1];

                int numberOfTables = readLineAsInteger();
                if (numberOfTables < 2 || numberOfTables > 10)
                {

                    System.out.println("Error! Number of tables are not in range!");
                    error = true;
                }

                tableArray = new Table[numberOfTables];
                for (int j = 0; j < numberOfTables; j++)
                {
                    String tableName = readLine();
                    String[] columnsAndRows = readLine().split(" ");
                    int numberOfColumns = Integer.parseInt(columnsAndRows[0]);
                    int numberOfRows = Integer.parseInt(columnsAndRows[1]);
                    if (numberOfRows < 2 || numberOfRows > 100 || numberOfColumns < 2 || numberOfColumns > 100)
                    {

                        System.out.println("Error! Number of records or column are not in range!");
                        error = true;
                        break;

                    }
                    if (error != true)
                    {


                        String columnNames = readLine();
                        tableArray[j] = new Table(numberOfColumns, numberOfRows, tableName, columnNames);  //creation of table

                        for (int x = 0; x < numberOfRows; x++)
                        {

                            String dataLine = readLine();
                            tableArray[j].addData(dataLine);
                        }

                        if (j == numberOfTables - 1)
                        {

                            totalQueries = readLineAsInteger();
                            Queries = new Query[totalQueries];
                            if(totalQueries>=0 && totalQueries<=50)
                            {
                                for (int z = 0; z < totalQueries; z++)
                                {
                                    String line1 = readLine();
                                    String line2 = readLine();
                                    String line3 = readLine();
                                    String line4 = readLine();
                                    Queries[z] = new Query(line1, line2, line3, line4, tableArray);   //creation of query


                                }
//                                System.out.println("Test: "+k);
//                                for(int z=0;z<totalQueries;z++)
//                                {
//                                    Queries[z].processQuery();
//                                }
                            }
                            else
                            {
                                System.out.println("Query limit exceeded!");
                                error= true;
                                break;
                            }
                        }


                    }


                }
                if(error!=true) {
                    myTestcases[k] = new TestCase(Queries, totalQueries);  ///insertion of data and query in each test case

                }
            }
            if(error!=true)
            {
                for (int z = 0; z < testCaseNumber; z++)
                {
                    System.out.println("Test: " + (z + 1));


                    myTestcases[z].RunTestCases(); //execution of each test case after all inputs taken

                }
            }
        }
    }
}