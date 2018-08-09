package com.example.quiz.util;

import java.text.DecimalFormat;
import java.util.Random;

public class MathUtilsFunc {


        public static double truncatedRandomDouble(){
            double number;
            Random random = new Random();
            number = random.nextDouble();
            number=roundTwoDecimals(number);
            return number;
        }

        public static double roundTwoDecimals(double d) {
            DecimalFormat twoDForm = new DecimalFormat("#.##");
            return Double.valueOf(twoDForm.format(d));
        }

}
