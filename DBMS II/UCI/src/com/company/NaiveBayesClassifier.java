package com.company;
import java.io.*;
import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class NaiveBayesClassifier {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("input.csv"));
        Calculator calculator = new Calculator();
        ArrayList<Integer> age = new ArrayList<Integer>();
        ArrayList<String> gender = new ArrayList<String>();
        ArrayList<Integer> numberOfEpisodes = new ArrayList<Integer>();
        ArrayList<Integer> outcome = new ArrayList<Integer>();
        ArrayList<Integer> index = new ArrayList<Integer>();

        int count = 10;

        String line = "";
        int totalData = 0;
        while ((line = br.readLine()) != null)   //returns a Boolean value
        {
            String[] input = line.split(",");
            age.add(Integer.parseInt(input[0]));
            gender.add(input[1]);
            numberOfEpisodes.add(Integer.parseInt(input[2]));
            outcome.add(Integer.parseInt(input[3]));
            //System.out.println(age.get(i) + " " + gender.get(i) + " " + numberOfEpisodes.get(i) + " " + outcome.get(i));
            totalData++;
        }
        int trainEnd = totalData*80/100;

        for(int i = 0; i < totalData; i++){
            index.add(i);
        }

        for(int k = 0; k < count; k++){
            double ageAlive = 0.0, ageNotAlive = 0.0;
            double maleAlive = 0.0, maleNotAlive = 0.0, femaleAlive = 0.0, femaleNotAlive = 0.0;
            double episodesAlive = 0.0, episodesNotAlive = 0.0;
            double totalAlive = 0.0, totalNotAlive = 0.0;

            Collections.shuffle(index);

            //Identify Data
            for(int i = 0; i < trainEnd; i++){
                if(outcome.get(index.get(i))==1){
                    ageAlive+=age.get(i);
                    episodesAlive+=numberOfEpisodes.get(index.get(i));
                    totalAlive++;
                    if(gender.get(index.get(i)).equals("0"))
                        maleAlive++;
                    else
                        femaleAlive++;
                }
                else{
                    ageNotAlive+=age.get(index.get(i));
                    episodesNotAlive+=numberOfEpisodes.get(index.get(i));
                    totalNotAlive++;

                    if(gender.get(index.get(i)).equals("0"))
                        maleNotAlive++;
                    else
                        femaleNotAlive++;
                }
            }

            //Summarize Data
            double meanAgeAlive = ageAlive/totalAlive;
            double meanAgeNotAlive = ageNotAlive/totalNotAlive;
            double sdAgeAlive = calculator.calculateSD(meanAgeAlive, age, outcome, index, 1);
            double sdAgeNotAlive = calculator.calculateSD(meanAgeNotAlive, age, outcome, index, 0);

            double meanEpisodesAlive = episodesAlive/totalAlive;
            double meanEpisodesNotAlive = episodesNotAlive/totalNotAlive;
            double sdEpisodesAlive = calculator.calculateSD(meanEpisodesAlive, numberOfEpisodes,outcome, index,1);
            double sdEpisodesNotAlive = calculator.calculateSD(meanEpisodesNotAlive, numberOfEpisodes, outcome,index, 0);

            double meanMaleAlive = maleAlive/totalAlive;
            double meanMaleNotAlive = maleNotAlive/totalNotAlive;
            double meanFemaleAlive = femaleAlive/totalAlive;
            double meanFemaleNotAlive = femaleNotAlive/totalNotAlive;

    //        System.out.println("Mean Age Alive : " + meanAgeAlive + "\tMeanAgeNotAlive : " + meanAgeNotAlive+
    //                " SD of Age Alive : " + sdAgeAlive + " SD of Age not Alive : " + sdAgeNotAlive);


            System.out.println("Total Alive : " + totalAlive + "\tTotal Dead : " + totalNotAlive);

            //testing
            double probabilityAlive = 0.0, probabilityNotAlive = 0.0;
            double totalAccuracy = 0.0;
            double truePositive=0.0, trueNegative = 0.0;
            double falsePositive=0.0, falseNegative=0.0;
            double accuracy = 0.0;

            System.out.println("Total Data : "+totalData + "\nTrain End : " + trainEnd);

            for(int i = trainEnd; i < totalData; i++){
                double probabilityAgeAlive = calculator.calculateNormalDistribution(age.get(index.get(i)), meanAgeAlive, sdAgeAlive);
                double probabilityAgeNotAlive = calculator.calculateNormalDistribution(age.get(index.get(i)), meanAgeNotAlive, sdAgeNotAlive);

                double probabilityNOEAlive = calculator.calculateNormalDistribution(numberOfEpisodes.get(index.get(i)),meanEpisodesAlive, sdEpisodesAlive);
                double probabilityNOENotAlive = calculator.calculateNormalDistribution(numberOfEpisodes.get(index.get(i)),meanEpisodesNotAlive, sdEpisodesNotAlive);

                double probabilityGenderAlive = 0.0;
                double probabilityGenderNotAlive = 0.0;

                if(gender.get(index.get(i)).equals("0")){
                    //if male
                    probabilityGenderAlive = maleAlive/(maleAlive+maleNotAlive);
                    probabilityGenderNotAlive = maleNotAlive/(maleAlive+maleNotAlive);
                }
                else{
                    probabilityGenderAlive = femaleAlive/(femaleAlive+femaleNotAlive);
                    probabilityGenderNotAlive = femaleNotAlive/(femaleAlive+femaleNotAlive);
                }

                probabilityAlive = totalAlive*probabilityAgeAlive*probabilityNOEAlive*probabilityGenderAlive/totalData;
                probabilityNotAlive = totalNotAlive*probabilityAgeNotAlive*probabilityNOENotAlive*probabilityGenderNotAlive/totalData;

                //System.out.println("Probability of Alive : " +probabilityAlive + "\tProbability of Dead : " + probabilityNotAlive);

                if(probabilityAlive>probabilityNotAlive){
                    if(outcome.get(index.get(i))==1)
                        truePositive++;
                    else
                        falsePositive++;
                }
                else{
                    if(outcome.get(index.get(i))==1)
                        falseNegative++;
                    else
                        trueNegative++;
                }

            }

            System.out.println("True Positive : "+ truePositive +"\tTrue Negative : "+ trueNegative+
                    "\nFalse Postivie : "+falsePositive + "\tFalse Negative :"+falseNegative);

            double precision,recall,fScore;
            precision=truePositive/(truePositive+falsePositive);
            recall=truePositive/(truePositive+falseNegative);
            fScore=(2*precision*recall)/(precision+recall);
            System.out.println("Accuracy : " + fScore*100);
            System.out.println("\n");
        }
    }
}
