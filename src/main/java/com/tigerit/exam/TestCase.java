package com.tigerit.exam;


public class TestCase {
    Query [] myQueries;
    int number;
    public TestCase(Query [] queries, int numberofqueries)
    {
        myQueries=queries;
        number=numberofqueries;
    }
    public void RunTestCases()
    {

        for(int i=0;i<number;i++)
        {

            myQueries[i].processQuery();

        }

    }
}
