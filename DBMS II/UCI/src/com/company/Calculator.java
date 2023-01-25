package com.company;

import java.util.ArrayList;

public class Calculator {
    public double calculateMean(ArrayList<Integer> data, ArrayList<Integer> index){
        double total = 0.0;
        for(int i = 0; i < data.size()*80/100; i++)
            total += data.get(index.get(i));
        return total/(data.size()+1);
    }

    public double calculateSD(double mean, ArrayList<Integer> data, ArrayList<Integer> outcome,ArrayList<Integer> index, int status){
        double standardDeviation = 0.0;
        for(int i = 0; i < data.size()*80/100;i++){
            if(status==outcome.get(index.get(i)))
                standardDeviation += Math.pow(data.get(index.get(i)) - mean, 2);
        }

        return Math.sqrt(standardDeviation/(data.size()+1));
    }

    public double calculateNormalDistribution(int age, double mean, double sd){
        double ans = 1/(2*3.1416);
        ans=Math.pow(ans, 0.5);
        ans=ans/sd;
        double e=( (double)age-mean);
        e=(e/sd);
        e=Math.pow(e, 2);
        e=-0.5*e;
        e=Math.pow(2.718, e);
        ans=ans*e;
        return ans;
    }
}
