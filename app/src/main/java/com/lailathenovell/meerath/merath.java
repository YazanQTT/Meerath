package com.lailathenovell.meerath;


import android.content.Context;
import android.util.DisplayMetrics;

@SuppressWarnings({"DuplicateCondition", "ConstantConditions"})
public final class merath {
    private static final double mainShare = 1;
    private static boolean isMale, hasFather, hasMother, hasSpouse;
    public static int sonCount, daughterCount, broCount, sisCount;
    public static double fatherShare, motherShare, spouseShare, sonShare, daughterShare, broShare, sisShare;
    private static boolean hasBroSis;
    public static boolean isNoType;

    public static int screenSize;

    private merath() { // private constructor

    }

    //Kalala Type
    // 1: Spouse(Yes) + Bro & Sis (No)
    // 2: Spouse(Yes) + Bro || Sis (Yes)
    // 3: Spouse(No) + Bro || Sis (Yes)
    // 4: Spouse(No) + Bro || Sis (No)
    // 5: Unknown

    public static void initiate(boolean isMale, int sonCount, int daughterCount, boolean hasFather, boolean hasMother, boolean hasSpouse, int broCount, int sisCount) {
        merath.sonCount = sonCount;
        merath.daughterCount = daughterCount;
        merath.broCount = broCount;
        merath.sisCount = sisCount;
        merath.isMale = isMale;
        merath.hasFather = hasFather;
        merath.hasMother = hasMother;
        merath.hasSpouse = hasSpouse;
        merath.hasBroSis = broCount > 0 || sisCount > 0;
    }

    private static boolean isKalala() {
        return sonCount == 0 && daughterCount == 0 && !hasFather && !hasMother;
    }

    private static int getKalalaType() {

        if (!isKalala())
            return 0;

        if (hasSpouse && broCount == 0 && sisCount == 0)
            return 1;
        if (hasSpouse && (broCount > 0 || sisCount > 0))
            return 2;
        if (!hasSpouse && (broCount > 0 || sisCount > 0))
            return 3;
        if (!hasSpouse && broCount == 0 && sisCount == 0)
            return 4;

        return 5;
    }

    public static void calculateShares() {
        setBroSisShare();
        setSpouseShare();
        setFatherShare();
        setMotherShare();
        setSonDaughterShare();
    }

    private static int broSisRelation() {
        int kalalaType = getKalalaType();

        if (kalalaType == 2) {
            if (broCount > 0 && sisCount > 0)
                return 1;
            else if (broCount == 1 && sisCount == 0)
                return 2;
            else if (broCount > 1 && sisCount == 0)
                return 3;
            else if (sisCount == 1 && broCount == 0)
                return 4;
            else if (sisCount > 1 && broCount == 0)
                return 5;
        } else if (kalalaType == 3) {
            if (broCount > 0 && sisCount > 0)
                return 6;
            else if (broCount > 0 && sisCount == 0)
                return 7;
            else if (broCount == 0 && sisCount == 1)
                return 8;
            else if (broCount == 0 && sisCount == 2)
                return 9;
            else if (broCount == 0 && sisCount == 3)
                return 10;
            else if (broCount == 0 && sisCount > 3)
                return 11;
        }

        return 0;
    }

    private static void setBroSisShare() {
        int temp = broSisRelation();

        if (temp == 1) // Kalala 2
        {
            broShare = 1.0 / 6.0 / broCount;
            sisShare = 1.0 / 6.0 / sisCount;
        } else if (temp == 2) {
            broShare = 1.0 / 6.0;
            sisShare = 0;
        } else if (temp == 3) {
            broShare = 1.0 / 3.0 / broCount;
            sisShare = 0;
        } else if (temp == 4) {
            broShare = 0;
            sisShare = 1.0 / 6.0;
        } else if (temp == 5) {
            broShare = 0;
            sisShare = 1.0 / 3.0 / sisCount;
        } else if (temp == 6) // Kalala 3
        {
            broShare = 2.0 / (broCount * 2 + sisCount);
            sisShare = 1.0 / (broCount * 2 + sisCount);
        } else if (temp == 7) {
            broShare = 1.0 / broCount;
            sisShare = 0;
        } else if (temp == 8) {
            broShare = 0;
            sisShare = 1.0 / 2.0;
        } else if (temp == 9) {
            broShare = 0;
            sisShare = 1.0 / 3.0;
        } else if (temp == 10) {
            broShare = 0;
            sisShare = 5.0 / 6.0 / 3.0;
        } else if (temp == 11) {
            broShare = 0;
            sisShare = 1.0 / sisCount;
        } else if (temp == 0) {
            broShare = 0;
            sisShare = 0;
        }
    }

    private static int spouseRelation() {
        if (!hasSpouse)
            return 0;

        if (isMale) {
            if (sonCount > 0 || daughterCount > 0)
                return 1;
            else if (hasFather && hasMother && hasBroSis)
                return 2;
            else if (hasFather && hasMother && !hasBroSis)
                return 3;
            else if (hasFather && !hasMother && hasBroSis)
                return 4;
            else if (hasFather && !hasMother && !hasBroSis)
                return 5;
            else if (!hasFather && hasMother && hasBroSis)
                return 6;
            else if (!hasFather && hasMother && !hasBroSis)
                return 7;
            else if (!hasFather && !hasMother && hasBroSis)
                return 8;
            else if (!hasFather && !hasMother && !hasBroSis)
                return 9;
        } else {
            if (sonCount > 0 || daughterCount > 0)
                return 10;
            else if (hasFather && hasMother && hasBroSis)
                return 11;
            else if (hasFather && hasMother && !hasBroSis)
                return 12;
            else if (hasFather && !hasMother && hasBroSis)
                return 13;
            else if (hasFather && !hasMother && !hasBroSis)
                return 14;
            else if (!hasFather && hasMother && hasBroSis)
                return 15;
            else if (!hasFather && hasMother && !hasBroSis)
                return 16;
            else if (!hasFather && !hasMother && hasBroSis)
                return 17;
            else if (!hasFather && !hasMother && !hasBroSis)
                return 18;
        }


        return 0;
    }

    private static void setSpouseShare() {
        int temp = spouseRelation();

        if (temp == 0)
            spouseShare = 0;
        else if (temp == 1)
            spouseShare = 0.125;
        else if (temp == 2)
            spouseShare = 0.4286;
        else if (temp == 3)
            spouseShare = 0.4286;
        else if (temp == 4)
            spouseShare = 0.6;
        else if (temp == 5)
            spouseShare = 0.6;
        else if (temp == 6)
            spouseShare = 0.6;
        else if (temp == 7)
            spouseShare = 0.6;
        else if (temp == 8)
            spouseShare = 1 - ((broShare * broCount)  + (sisShare * sisCount));
        else if (temp == 9)
            spouseShare = 1;
        else if (temp == 10) //FEMALE
            spouseShare = 0.25;
        else if (temp == 11)
            spouseShare = 0.6;
        else if (temp == 12)
            spouseShare = 0.6;
        else if (temp == 13)
            spouseShare = 0.75;
        else if (temp == 14)
            spouseShare = 0.75;
        else if (temp == 15)
            spouseShare = 0.75;
        else if (temp == 16)
            spouseShare = 0.75;
        else if (temp == 17)
            spouseShare = 1 - ((broShare * broCount)  + (sisShare * sisCount));
        else if (temp == 18)
            spouseShare = 1;
    }

    private static int fatherRelation() {
        if (!hasFather)
            return 0;
        if (sonCount > 0 || daughterCount > 0)
            return 1;

        if (isMale) {
            if (hasMother && hasBroSis && hasSpouse)
                return 2;
            else if (hasMother && hasSpouse && !hasBroSis )
                return 3;
            else if (hasMother && !hasSpouse && hasBroSis)
                return 4;
            else if (hasMother && !hasSpouse && !hasBroSis)
                return 5;
            else if (!hasMother && hasSpouse && hasBroSis)
                return 6;
            else if (!hasMother && hasSpouse && !hasBroSis)
                return 7;
            else if (!hasMother && !hasSpouse && hasBroSis)
                return 8;
            else if (!hasMother && !hasSpouse && !hasBroSis)
                return 9;
            else if (!hasMother && hasSpouse && !hasBroSis && hasBroSis)
                return 10;
        } else {
            if (hasMother && hasBroSis && hasSpouse)
                return 11;
            else if (hasMother && hasSpouse && !hasBroSis)
                return 12;
            else if (hasMother && !hasSpouse && hasBroSis)
                return 13;
            else if (hasMother && !hasSpouse && !hasBroSis)
                return 14;
            else if (!hasMother && hasSpouse && hasBroSis)
                return 15;
            else if (!hasMother && hasSpouse && !hasBroSis)
                return 16;
            else if (!hasMother && !hasSpouse && hasBroSis)
                return 17;
            else if (!hasMother && !hasSpouse && !hasBroSis)
                return 18;
        }

        return 0;
    }

    private static void setFatherShare() {
        int temp = fatherRelation();

        if (temp == 0)
            fatherShare = 0;
        else if (temp == 1)
            fatherShare = 1.0 / 6.0;
        else if (temp == 2)
            fatherShare = 0.2857;
        else if (temp == 3)
            fatherShare = 0.2857;
        else if (temp == 4)
            fatherShare = 0.8333;
        else if (temp == 5)
            fatherShare = 0.6667;
        else if (temp == 6)
            fatherShare = 0.4;
        else if (temp == 7)
            fatherShare = 0.4;
        else if (temp == 8)
            fatherShare = 1;
        else if (temp == 9)
            fatherShare = 1;
        else if (temp == 10)
            fatherShare = 0;
        else if (temp == 11)
            fatherShare = 0.2;
        else if (temp == 12)
            fatherShare = 0.2;
        else if (temp == 13)
            fatherShare = 0.8333;
        else if (temp == 14)
            fatherShare = 0.6667;
        else if (temp == 15)
            fatherShare = 0.25;
        else if (temp == 16)
            fatherShare = 0.25;
        else if (temp == 17)
            fatherShare = 1;
        else if (temp == 18)
            fatherShare = 1;
    }

    private static int motherRelation() {
        if (!hasMother)
            return 0;
        if (sonCount > 0 || daughterCount > 0)
            return 1;

        if (isMale) {
            if (hasFather && hasBroSis && hasSpouse)
                return 2;
            else if (hasFather && hasSpouse && !hasBroSis )
                return 3;
            else if (hasFather && !hasSpouse && hasBroSis)
                return 4;
            else if (hasFather && !hasSpouse && !hasBroSis)
                return 5;
            else if (!hasFather && hasSpouse && hasBroSis)
                return 6;
            else if (!hasFather && hasSpouse && !hasBroSis)
                return 7;
            else if (!hasFather && !hasSpouse && hasBroSis)
                return 8;
            else if (!hasFather && !hasSpouse && !hasBroSis)
                return 9;
            else if (!hasFather && hasSpouse && !hasBroSis && hasBroSis)
                return 10;
        } else {
            if (hasFather && hasBroSis && hasSpouse)
                return 11;
            else if (hasFather && hasSpouse && !hasBroSis)
                return 12;
            else if (hasFather && !hasSpouse && hasBroSis)
                return 13;
            else if (hasFather && !hasSpouse && !hasBroSis)
                return 14;
            else if (!hasFather && hasSpouse && hasBroSis)
                return 15;
            else if (!hasFather && hasSpouse && !hasBroSis)
                return 16;
            else if (!hasFather && !hasSpouse && hasBroSis)
                return 17;
            else if (!hasFather && !hasSpouse && !hasBroSis)
                return 18;
        }

        return 0;
    }

    private static void setMotherShare() {
        int temp = motherRelation();

        if (temp == 0)
            motherShare = 0;
        else if (temp == 1)
            motherShare = 1.0 / 6.0;
        else if (temp == 2)
            motherShare = 0.2857;
        else if (temp == 3)
            motherShare = 0.2857;
        else if (temp == 4)
            motherShare = 0.1667;
        else if (temp == 5)
            motherShare = 0.3333;
        else if (temp == 6)
            motherShare = 0.4;
        else if (temp == 7)
            motherShare = 0.4;
        else if (temp == 8)
            motherShare = 1;
        else if (temp == 9)
            motherShare = 1;
        else if (temp == 10)
            motherShare = 0;
        else if (temp == 11)
            motherShare = 0.2;
        else if (temp == 12)
            motherShare = 0.2;
        else if (temp == 13)
            motherShare = 0.1667;
        else if (temp == 14)
            motherShare = 0.3333;
        else if (temp == 15)
            motherShare = 0.25;
        else if (temp == 16)
            motherShare = 0.25;
        else if (temp == 17)
            motherShare = 1;
        else if (temp == 18)
            motherShare = 1;
    }

    private static int sonDaughterRelation() {
        double dc = daughterCount, sc = sonCount;
        double SOND = sc / dc;
        double DONS = dc / sc;

        if (sonCount == 0 && daughterCount == 0)
            return 0;

        if (sonCount > 0 && daughterCount == 0)
            return 1;
        else if (sonCount == 0 && daughterCount > 0)
            return 2;
        else if (sonCount > 0 && daughterCount > 0) {
            if (sonCount == daughterCount)
                return 3;
            else if (sonCount < daughterCount) {
                if (DONS > 1 && DONS < 2)
                    return 4;
                else if (DONS == 2)
                    return 5;
                else if (DONS > 2)
                    return 6;
            } else {
                if (SOND > 1 && SOND < 2)
                    return 7;
                else if (SOND == 2)
                    return 8;
                else if (SOND > 2)
                    return 9;
            }
        }


        return 0;
    }

    private static void setSonDaughterShare() {
        int temp = sonDaughterRelation();
        double dc = daughterCount, sc = sonCount;
        double SOND = sc / dc;
        double DONS = dc / sc;

        if (temp == 0) {
            sonShare =0;
            daughterShare =0;
        }
        else if (temp == 1)
        {
            sonShare = (mainShare -  fatherShare - motherShare - spouseShare) / sonCount;
            daughterShare =0;
        }
        else if (temp == 2)
        {
            sonShare =0;
            daughterShare = (mainShare - fatherShare - motherShare - spouseShare) / daughterCount;
        }
        else if (temp == 3)
        {
            sonShare =(mainShare - fatherShare - motherShare - spouseShare) / (sonCount + daughterCount);
            daughterShare =(mainShare - fatherShare - motherShare - spouseShare) / (sonCount + daughterCount);
        }
        else if (temp == 4)
        {
            double res = (0.5 + ((DONS - 1) / 6.0)) / sc;
            sonShare = (mainShare - fatherShare - motherShare - spouseShare) * res;
            daughterShare =(1 - (res * sc) ) / dc;
            daughterShare = (mainShare - fatherShare - motherShare - spouseShare) * daughterShare;
        }
        else if (temp == 5)
        {
            double res = 2 / (sc * 2 + dc);
            double resD = 1 / (sc * 2 + dc);

            sonShare = (mainShare - fatherShare - motherShare - spouseShare) * res;
            daughterShare = (mainShare - fatherShare - motherShare - spouseShare) * resD;
        }
        else if (temp == 6)
        {
            double res = 1.0 /3.0 / sc;
            double resD = 2.0 /3.0 / dc;

            sonShare = (mainShare - fatherShare - motherShare - spouseShare) * res;
            daughterShare = (mainShare - fatherShare - motherShare - spouseShare) * resD;
        }
        else if (temp == 7)
        {
            double res = (0.5 + ((SOND - 1) / -6.0)) ;

            sonShare = ((mainShare - fatherShare - motherShare - spouseShare) * res ) / sc;
            daughterShare = ((mainShare - fatherShare - motherShare - spouseShare) * (1 - res) ) / dc;
        }
        else if (temp == 8)
        {
            double x = dc * 2 + sc;

            sonShare = 1.0 / x;
            sonShare = (mainShare - fatherShare - motherShare - spouseShare) * sonShare;
            daughterShare =2.0 / x;
            daughterShare = (mainShare - fatherShare - motherShare - spouseShare) * daughterShare;
        }
        else if (temp == 9)
        {
            double res = 2.0 / 3.0 / sc;
            double resD = 1.0 / 3.0 / dc;

            sonShare = (mainShare - fatherShare - motherShare - spouseShare) * res;
            daughterShare = (mainShare - fatherShare - motherShare - spouseShare) * resD;
        }
    }



    public static void getDeviceDensity(Context context) {
        int density = context.getResources().getDisplayMetrics().densityDpi;
        switch (density)
        {
            case DisplayMetrics.DENSITY_MEDIUM:
                screenSize = 2;
            case DisplayMetrics.DENSITY_HIGH:
                screenSize = 3;
            case DisplayMetrics.DENSITY_LOW:
                screenSize = 1;
            case DisplayMetrics.DENSITY_XHIGH:
                screenSize = 4;
            case DisplayMetrics.DENSITY_XXHIGH:
                screenSize = 5;
            default:
                screenSize = 0;
        }
    }

}