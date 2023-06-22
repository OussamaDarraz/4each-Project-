package service.functionnal.commercial.operation;

public class ToSum {

    public int calc(int ccf){


       int pods= 20;

        int cal = 0;
        if (ccf==5){

         cal= (int) (pods-(pods*0.05));
       }
       else if(ccf==10){
           cal= (int) (pods-(pods*0.10));
       } else if(ccf==15) {
           cal = (int) (pods -(pods * 0.15));
       }

        return  cal;
    }

}
