package babyNames;
import java.util.ArrayList;

/**
 * Object that represents a name for a baby. Includes the sex of the name
 * and birth data for the number of babies born with that name in a
 * particular year.
 * @author kchoy
 */
public class BabyName {

    // TODO 2: Write the code below this line.
    private String name;
    private GenderOfName gender;
    private ArrayList<Integer> birthCounts;
    private ArrayList<Integer> years;

    // TODO 3: Write the code below this line.
    public BabyName(String myName, GenderOfName nameGender)
    {
        name = myName;
        gender = nameGender;
        birthCounts = new ArrayList<Integer>();
        years = new ArrayList<Integer>();
    }

    // TODO 4: Write the code below this line.
    public String getName()
    {
        return name;
    }

    public GenderOfName getGender()
    {
        return gender;
    }

    public ArrayList<Integer> getBirthCounts()
    {
        return birthCounts;
    }

    public ArrayList<Integer> getYears()
    {
        return years;
    }

    public void setGender(GenderOfName gender)
    {
        this.gender = gender;
    }

    //TODO 5: Write the code below this line.
    public void addData(int birthNum, int birthYear)
    {

        if (years.size() == 0)
        {
            birthCounts.add(birthNum);
            years.add(birthYear);
        }

        else{
            for (int i = birthCounts.size()-1; i >= 0; i--)
            {

                if (years.get(i) <= birthYear)
                {
                    birthCounts.add(i+1, birthNum);
                    years.add(i+1, birthYear);
                    break;
                }
            }
        }
    }

    public void addData(ArrayList<Integer> bCount, ArrayList<Integer> bYear)
    {
        for (int i = 0; i < bYear.size(); i++)
        {
            addData(bCount.get(i), bYear.get(i));
        }
    }
    /**
     * Formats the object as a String.
     * @return formatted String
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Name: " + name + "\nSex of Name: " + gender.toString().toLowerCase());
        for (int i = 0; i < years.size(); i++){
            if (i == 0){
                result.append("\nData: ");
            }
            result.append(String.format("(%d, %d), ", birthCounts.get(i), years.get(i)));
            if (i == years.size()-1){
                result.deleteCharAt(result.length()-2); // Remove extra space
            }
        }
        return result.toString();
    }
}