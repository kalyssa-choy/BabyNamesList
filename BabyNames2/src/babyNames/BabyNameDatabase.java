package babyNames;

import babyNames.BabyName;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
/**
 * Manages the list of BabyNames as well as reading and writing to the files
 * with the BabyNames.
 * @author kchoy
 */
public class BabyNameDatabase
{

    // TODO 2: Write the code below this line.
    private String databaseFileName;
    private ArrayList<BabyName> records;
    private File fileRecord;

    // TODO 3: Write the code below this line.
    public BabyNameDatabase (String name)
    {
        databaseFileName = name;
        records = new ArrayList<BabyName>();
        fileRecord = new File("databaseFileName");
    }
    // TODO 4: Write the code below this line.
    public ArrayList<BabyName> getRecords()
    {
        return records;
    }
    /**
     * Reads the csv file that holds the baby name birth data and updates
     * the records variable.
     * @param filename name of the file to read from
     * @throws IOException could not find or close file
     */
    public void readRecordsFromBirthDataFile(String filename) throws IOException
    {
        // TODO 2: Write the code below this line.
        //use delimiter for ","
        //scanner for file input
        Scanner in = new Scanner(new File (filename));

        //scanning first line for year
        String firstL = in.nextLine();
        Scanner input = new Scanner(firstL);
        input.useDelimiter(" ");
        int year = 0;

        //why does it never has next INT????!?
        String strOne = input.next();
        String strTwo = input.next();
        String yearStr = input.next();
        year = Integer.parseInt(yearStr);



        //call to process the next line
        while (in.hasNextLine())
        {
            String theLine = in.nextLine();
            Scanner lineScan = new Scanner(theLine);
            lineScan.useDelimiter(",");
            if (lineScan.hasNextInt())
            {
                processLineFromBirthDataFile(lineScan.nextLine(), year);
            }
        }

    }

    /**
     * Processes one formatted line of the csv file into baby names and
     * adds/updates the records array.
     * @param line the string holding the line from the csv file
     * @param year when the data is from
     */
    public void processLineFromBirthDataFile(String line, int year)
    {
        // TODO 3: Write the code below this line.
        //scanning the line
        int theYear = year;
        Scanner in2 = new Scanner(line);
        in2.useDelimiter(",");

        //scanning the rank of the name
        int rank = in2.nextInt();

        //boy name and number of boy name
        String boyName = in2.next();
        int maleNum = 0;
        if (!in2.hasNextInt())
        {
            String num = in2.next();
            Scanner maleCount = new Scanner (num);
            maleCount.useDelimiter("\"");
            int numOne = maleCount.nextInt();
            String numTwo = in2.next();
            Scanner male = new Scanner (numTwo);
            male.useDelimiter("\"");
            int numPtTwo = male.nextInt();
            String theNum = "" + numOne + numPtTwo;
            maleNum = Integer.parseInt(theNum);

        }
        else
        {
            maleNum = in2.nextInt();
        }

        //girl name and number of girl name
        String girlName = in2.next();
        int girlNum = 0;
        if (!in2.hasNextInt())
        {
            String femNum = in2.next();
            Scanner femaleCount = new Scanner (femNum);
            femaleCount.useDelimiter("\"");
            int femNumOne = femaleCount.nextInt();
            String femNumTwo = in2.next();
            Scanner female = new Scanner (femNumTwo);
            female.useDelimiter("\"");
            int femNumPtTwo = female.nextInt();
            String femTheNum = "" + femNumOne + femNumPtTwo;
            maleNum = Integer.parseInt(femTheNum);
        }
        else
        {
            girlNum = in2.nextInt();
        }


        //creating babyname object for boy
        BabyName boy = new BabyName(boyName, GenderOfName.MALE);
        BabyName girl = new BabyName(girlName, GenderOfName.FEMALE);

        boy.addData(maleNum, theYear);
        girl.addData(girlNum, theYear);

        records.add(boy);
        records.add(girl);

        //for checking if the boy name is neutral
        for (int k = 0; k < records.size(); k++)
        {
            String name1 = records.get(records.size()-2).getName();
            String name2 = records.get(k).getName();


            if (name1.equals(name2))
            {
                if (records.get(k).getGender() == GenderOfName.NEUTRAL)
                {
                    break;
                }
                else if (records.get(k).getGender() != records.get(records.size()-2).getGender())
                {
                    records.get(k).setGender(GenderOfName.NEUTRAL);
                    records.remove(records.size()-2);
                }

            }

        }

        //for checking if the girl name is neutral
        for (int k = 0; k < records.size(); k++)
        {
            String name1 = records.get(records.size()-1).getName();
            String name2 = records.get(k).getName();


            if (name1.equals(name2))
            {
                    if (records.get(k).getGender() == GenderOfName.NEUTRAL)
                    {
                        break;
                    }
                    else if(records.get(k).getGender() != records.get(records.size()-1).getGender())
                    {
                    records.get(k).setGender(GenderOfName.NEUTRAL);
                    records.remove(records.size()-1);
                    }
            }

        }

    }
}