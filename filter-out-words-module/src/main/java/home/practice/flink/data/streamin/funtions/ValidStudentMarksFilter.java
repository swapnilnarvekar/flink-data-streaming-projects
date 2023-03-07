package home.practice.flink.data.streamin.funtions;

import org.apache.flink.api.common.functions.FilterFunction;

public class ValidStudentMarksFilter implements FilterFunction<String> {
    @Override
    public boolean filter(String inputString) throws Exception {
        System.out.println(String.format("%s.filter()-inputString is received as %s",
                this.getClass().getName(),inputString));
        long numberOfSeparator = inputString.chars().filter(eachCharacter -> eachCharacter == ',').count();
        if(numberOfSeparator == 1){
            return true;
        }
        return false;
    }
}
