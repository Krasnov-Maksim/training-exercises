package org.example.cat_years_dog_years;

/*
 * DESCRIPTION:
 * This is related to my other Kata about cats and dogs.
 *
 * Kata Task
 * I have a cat and a dog which I got as kitten / puppy.
 *
 * I forget when that was, but I do know their current ages as catYears and dogYears.
 *
 * Find how long I have owned each of my pets and return as a list [ownedCat, ownedDog]
 *
 * NOTES:
 *
 * Results are truncated whole numbers of "human" years
 *
 * Cat Years
 * 15 cat years for first year
 * +9 cat years for second year
 * +4 cat years for each year after that
 *
 * Dog Years
 * 15 dog years for first year
 * +9 dog years for second year
 * +5 dog years for each year after that
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(getCatYears(3));
        System.out.println(getDogYears(4));
    }

    private static int getCatYears(int humanYears) {
        int catYears = 0;
        for (int i = 1; i <= humanYears; i++) {
            if (i == 1) {
                catYears += 15;
            } else if (i == 2) {
                catYears += 9;
            } else {
                catYears += 4;
            }
        }
        return catYears;
    }

    private static int getDogYears(int humanYears) {
        int dogYears = 0;
        for (int i = 1; i <= humanYears; i++) {
            if (i == 1) {
                dogYears += 15;
            } else if (i == 2) {
                dogYears += 9;
            } else {
                dogYears += 5;
            }
        }
        return dogYears;
    }
}
