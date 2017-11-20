package com.tigerit.exam;


public class Table {
    String tname;
     String [] tableColumnNames;
    int [] [] data;
    int  columnNumbers;
    int rowNumbers;
    int currentRow=0;
    public Table(int cNumber,int dNumber,String name,String colName)
    {
        columnNumbers=cNumber;
        rowNumbers=dNumber;

        tname=name;
        tableColumnNames=colName.split(" ");
//        for(int i=0;i<cNumber;i++)
//        {
//
//            //System.out.println(tableColumnNames[i]);
//        }

        data=new int[dNumber][cNumber];


    }
    public void addData(String datas)
    {
       String [] values= datas.split(" ");

        if(currentRow<rowNumbers)
        {
            for (int i = 0; i < columnNumbers; i++) {
                data[currentRow][i] = Integer.parseInt(values[i]);  //fill up data
              //  System.out.println(data[currentRow][i]);


            }
            currentRow++;
        }



    }
    public void printall()
    {


        for(int i=0;i<rowNumbers;i++)
        {
            for(int j=0;j<columnNumbers;j++)
            {
                System.out.print(data[i][j]+" ");

            }
            System.out.println();

        }

    }
}
