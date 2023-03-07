package home.practice.flink.data.streamin.examples;

/**
 *
 * I am displaymethodof displayclass
 *
 *
 *
 */
public class ForLoopIllustrator {

    public void display(){
//    for(initialization ;condition ; increament / decrement)

        for (int i=1 ;true ; i++) {

            if(i <= 100){
                System.out.println(i);
            }else {
                break;
            }

        }//for loop
    }

    public static void main(String[] args) {
        ForLoopIllustrator forLoopIllustrator = new ForLoopIllustrator();
        forLoopIllustrator.display();
    }
}
