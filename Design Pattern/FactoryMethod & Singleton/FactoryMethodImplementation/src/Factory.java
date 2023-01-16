public class Factory {
    public Sports createSports(String sportsName){

        if(sportsName.equalsIgnoreCase("Cricket")){
            return new Cricket();
        }
        else if(sportsName.equalsIgnoreCase("Football")){
            return new Football();
        }
        return null;
    }
}
