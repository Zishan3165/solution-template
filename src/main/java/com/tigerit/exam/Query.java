package com.tigerit.exam;


public class Query {
    String select;
    String table1Name;
    String table2Name;
    String table1NameAlias=" ";
    String table2NameAlias=" ";
    String joinCol1;
    String joinCol2;
    String Line1;
    String Line2;
    String Line3;
    String Line4;
    Table [] Tables;
    int joinCol1Index;
    int joinCol2Index;
    String [] string2;

    Table table1;
    Table table2;

    public Query(String l1,String l2,String l3,String l4,Table [] tableArray)
    {
        Line1=l1;
        Line2=l2;
        Line3=l3;
        Line4=l4;
        Tables=tableArray;
        string2=Line2.split(" ");
        String [] string3=Line3.split(" ");
        Line4=Line4.replaceFirst("ON","");
        Line4=Line4.replaceFirst("=",",");


        String [] firstString4=Line4.split(" ");
        String [] secondString4=firstString4[1].split("\\.");
        String [] thirdString4=firstString4[3].split("\\.");
        joinCol1=secondString4[1];
        table1Name=string2[1];
        table2Name=string3[1];

        joinCol2=thirdString4[1];


        if(string2.length==3)
        {
            table1NameAlias=string2[2];

        }
        if(string3.length==3)
        {
            table2NameAlias=string3[2];
        }

        for(int i=0;i<tableArray.length;i++)        //fetch datas of table1
        {
            if(table1Name.equals(Tables[i].tname))
            {
                table1=Tables[i];


                break;
            }

        }
        for(int i=0;i<tableArray.length;i++)       //fetch datas of table2
        {
            if(table2Name.equals(tableArray[i].tname))
            {
                table2=tableArray[i];

                break;
            }

        }
        if(string3.length==3)            //if it contains alias names
        {

            if (secondString4[0].equals(table1NameAlias))
            {     //if the tables are in order

                for (int i = 0; i < table1.columnNumbers; i++)
                {        //if order is same in 3rd line of query
                    if (joinCol1.equals(table1.tableColumnNames[i]))
                    {

                        joinCol1Index = i;
                        // System.out.println("index of table 1 is "+i);
                        break;
                    }


                }
                for (int i = 0; i < table2.columnNumbers; i++)
                {
                    if (joinCol2.equals(table2.tableColumnNames[i]))
                    {

                        joinCol2Index = i;
                        //System.out.println("index of table 2 is "+i);
                        break;
                    }


                }
            }

            else                                                            //if order is reversed in 3rd line of query
            {
                for (int i = 0; i < table2.columnNumbers; i++)
                {
                    if (joinCol1.equals(table2.tableColumnNames[i]))
                    {

                        joinCol1Index = i;
                        // System.out.println("index of table 1 is "+i);
                        break;
                    }


                }
            }

        }
        else {
                 if (secondString4[0].equals(table1Name))
                 {     //if the tables are in order

                    for (int i = 0; i < table1.columnNumbers; i++)
                    {        //if order is same in 3rd line of query
                         if (joinCol1.equals(table1.tableColumnNames[i]))
                         {

                            joinCol1Index = i;
                            // System.out.println("index of table 1 is "+i);
                            break;
                    }


                }
                for (int i = 0; i < table2.columnNumbers; i++)
                {
                    if (joinCol2.equals(table2.tableColumnNames[i]))
                    {

                        joinCol2Index = i;
                        //System.out.println("index of table 2 is "+i);
                        break;
                    }


                }
            }

                else                                                            //if order is reversed in 3rd line of query
                    {
                        for (int i = 0; i < table2.columnNumbers; i++)
                        {
                            if (joinCol1.equals(table2.tableColumnNames[i]))
                            {

                                joinCol1Index = i;
                                // System.out.println("index of table 1 is "+i);
                                break;
                            }


                        }
                            }
                for (int i = 0; i < table1.columnNumbers; i++)
                {
                    if (joinCol2.equals(table1.tableColumnNames[i]))
                    {

                        joinCol2Index = i;
                        //System.out.println("index of table 2 is "+i);
                        break;
                    }


                }

        }
       // processQuery();

    }

    public void processQuery()
    {
      if(Line1.contains("*"))
      {
          for(int i=0;i<table1.columnNumbers;i++)
          {
              System.out.print(table1.tableColumnNames[i]+" ");

          }
          for(int i=0;i<table2.columnNumbers;i++)
          {
              System.out.print(table2.tableColumnNames[i]+" ");

          }
          System.out.println();
          for(int i=0;i< table1.rowNumbers;i++)
          {
            for(int j=0;j<table2.rowNumbers;j++)
            {
                if(table1.data[i][joinCol1Index]==table2.data[j][joinCol2Index])
                {
                    for(int x=0;x<table1.columnNumbers;x++)
                    {
                        System.out.print(table1.data[i][x]+" ");

                    }
                    for(int x=0;x<table2.columnNumbers;x++)
                    {
                        System.out.print(table2.data[j][x]+" ");

                    }
                    System.out.println();



                }


            }


          }
          System.out.println();

      }
      else
      {
          int counterForColumn=0;
        Line1=Line1.replaceFirst("SELECT ","");

          String [] colVariables=Line1.split(", ");
          String [] columnNamesforPrint=new String[colVariables.length];
          int []columnIndex=new int[colVariables.length];
          int []tableNumber=new int[colVariables.length];

          for(int i=0;i<colVariables.length;i++)
          {
              String [] string1=colVariables[i].split("\\.");

              if(string1[0].equals(table1NameAlias))
              {
                  tableNumber[i]=1;
                  for(int x=0;x<table1.tableColumnNames.length;x++)
                  {
                     if(string1[1].equals(table1.tableColumnNames[x]))
                     {

                         columnNamesforPrint[counterForColumn]=string1[1];
                         columnIndex[counterForColumn]=x;
                         counterForColumn++;
                     }

                  }


              }
              else
              {
                  tableNumber[i]=2;
                  for(int x=0;x<table2.tableColumnNames.length;x++)
                  {
                      if(string1[1].equals(table2.tableColumnNames[x]))
                      {
                          columnNamesforPrint[counterForColumn]=string1[1];
                          columnIndex[counterForColumn]=x;
                          counterForColumn++;
                      }

                  }

              }



          }

          for(int i=0;i<colVariables.length;i++)
          {
              System.out.print(columnNamesforPrint[i]+" ");

          }
          System.out.println();

          for(int i=0;i< table1.rowNumbers;i++)
          {
              for(int j=0;j<table2.rowNumbers;j++)
              {
                  if(table1.data[i][joinCol1Index]==table2.data[j][joinCol2Index])
                  {

                     for(int x=0;x<columnIndex.length;x++)
                     {
                         if(tableNumber[x]==1)
                         {
                             System.out.print(table1.data[i][columnIndex[x]]+" ");

                         }
                         else
                         {
                             System.out.print(table2.data[j][columnIndex[x]]+" ");

                         }


                     }
                      System.out.println();


                  }

              }


          }
          System.out.println();

      }

    }

}
